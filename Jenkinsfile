pipeline {
    agent {
        label "backbone"
    }
    // 定数や変数を定義する
    environment {
        reportDir = 'build/reports'
        javaDir = 'src/main/java'
        resourcesDir = 'src/main/resources'
        testReportDir = 'build/test-results/test'
        jacocoReportDir = 'build/reports/jacoco'
        stepCountReportDir = 'build/reports/stepCount'
        javadocDir = 'build/docs/javadoc'
        mvnHome = '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven3.6.1'
        libsDir = 'build/libs'
        appName = 'backBoneApp'
        appVersion = '20.0.0'
        oa1ServerProps = null
    }

    // stagesブロック中に一つ以上のstageを定義する
    stages {
        stage('Preparation') {
            // 実際の処理はstepsブロック中に定義する
            steps {
                cleanWs()

                // このJobをトリガーしてきたGithubのプロジェクトをチェックアウト
                checkout scm

                // ジョブ失敗の原因調査用にJenkinsfileとbuild.gradleは最初に保存する
                archiveArtifacts "Jenkinsfile"
                archiveArtifacts "build.gradle"

                // scriptブロックを使うと従来のScripted Pipelinesの記法も使える
                script {
                    // Permission deniedで怒られないために実行権限を付与する
                    if (isUnix()) {
                        sh 'chmod +x gradlew'
                        sh 'git config core.filemode false'
                    }
                }
                gradlew 'clean'
            }
        }
        stage('Check commit message') {
            when { allOf { branch 'master'; changelog '.*\\[Gradle Release Plugin\\].*' } }
            steps {
                script {
                    currentBuild.result = 'NOT_BUILT'
                }
                error('Skipping release build')
            }
        }
        // stage('dependencyUpdate') {
        //     when {
        //         branch 'develop'
        //     }
        //     steps {
        //         //gradlewのバージョンアップ
        //         gradlew 'wrapper'

        //         //依存関係バージョン更新
        //         gradlew 'useLatestVersions'

        //         sh script: "git add -A"
        //         sh script: "git diff --cached --exit-code --quiet || git commit -m '[gradle-use-latest-versions-plugin]use-latest-version'"
        //         //Push
        //         withCredentials([usernameColonPassword(credentialsId: 'keizai-dev', variable: 'GIT_USER_PASSWORD')]) {
        //             sh script: "git push https://${GIT_USER_PASSWORD}@github.com/jagunma/backboneAuth-oa1-server.git develop:develop"
        //         }
        //     }
        // }
        stage('Compile') {
            steps {
                gradlew 'classes testClasses'
            }

            // postブロックでstepsブロックの後に実行される処理が定義できる
            post {
                // alwaysブロックはstepsブロックの処理が失敗しても成功しても必ず実行される
                always {

                    // JavaDoc生成時に実行するとJavaDocの警告も含まれてしまうので
                    // Javaコンパイル時の警告はコンパイル直後に収集する
                    recordIssues(enabledForFailure: true, tools: [java()])
                }
            }
        }

        stage('Static code analyze') {
            steps {
                // 並列処理の場合はparallelメソッドを使う
                gradlew 'check -x test -x classes -x testClasses'

            }
            post {
                always {
                    recordIssues(tools: [taskScanner(highTags: 'FIXME', ignoreCase: true, includePattern: '**/src/main/java/**/*.java', lowTags: 'XXX', normalTags: 'TODO')])
                    recordIssues enabledForFailure: true, tool: spotBugs(pattern: '**/build/reports/spotbugs/main.xml')
                }
            }
        }
        stage('small test') {
            when {
                not {
                    branch 'PR-*'
                }
            }
            steps {
                gradlew 'test jacocoTestReport -x classes -x testClasses'
                junit allowEmptyResults: true, testResults: "**/${testReportDir}/*.xml"
                archiveArtifacts allowEmptyArchive: true, artifacts: "**/${testReportDir}/*.xml"
                // カバレッジレポートを生成（テストクラスを除外）
                echo 'JacocoReportアーカイブ 開始'
                jacoco exclusionPattern: '**/*Test*.class,**/*Mock*.class'
                echo 'JacocoReportアーカイブ 終了'
            }
        }
        stage('full test') {
            when {
                branch 'PR-*'
            }
            steps {
                gradlew 'testSlow jacocoTestReport -x classes -x testClasses'
                junit allowEmptyResults: true, testResults: "**/${testReportDir}/*.xml"
                archiveArtifacts allowEmptyArchive: true, artifacts: "**/${testReportDir}/*.xml"
                // カバレッジレポートを生成（テストクラスを除外）
                echo 'JacocoReportアーカイブ 開始'
                jacoco exclusionPattern: '**/*Test*.class,**/*Mock*.class'
                echo 'JacocoReportアーカイブ 終了'
            }
        }
        stage('Deploy') {
            // whenブロックでstageを実行する条件を指定できる
            when {
                allOf {
                    branch 'develop'
                    // 静的コード解析とテスト失敗時はデプロイしない
                    expression { currentBuild.currentResult == 'SUCCESS' }
                }
            }
            steps {
                gradlew 'jar -x classes -x test -x check'
                gradlew 'bootJar -x classes -x test -x check'

            }
        }

        stage('Release application to dev1') {
            when {
                allOf {
                    branch 'develop'
                    // 静的コード解析とテスト失敗時はリリースしない
                    expression { currentBuild.currentResult == 'SUCCESS' }
                }
            }
            steps {
                archiveArtifacts "**/*.jar"
            }
        }
        stage('Release application to staging') {
            when {
                allOf {
                    branch 'master'
                    // 静的コード解析とテスト失敗時はリリースしない
                    expression { currentBuild.currentResult == 'SUCCESS' }
                }
            }
            steps {
                //バージョン読み取り(gradle.properties読み取り)
                script {
                    oa1ServerProps = readProperties file: "gradle.properties"
                }

                //認証情報ファイルを使用するようにconfig設定
                withCredentials([usernameColonPassword(credentialsId: 'keizai-dev', variable: 'GIT_USER_PASSWORD')]) {
                    sh "echo https://${GIT_USER_PASSWORD}@github.com > ~/.git-credentials"
                }
                sh "git config credential.helper store"

                //リリースリポジトリへアップロード
                gradlew '-Prelease.useAutomaticVersion=true release'

                //認証情報ファイル後始末
                sh "git config credential.helper ''"
                sh "echo '' > ~/.git-credentials"

                slackSend channel: "#jenkins-build", color: "#2196F3", message: "Release backboneAuth-oa1-server - ${oa1ServerProps.version.replace('-SNAPSHOT', '')} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", tokenCredentialId: 'slack-integration-token'
            }
        }
        stage('kick job depended on') {
            when {
                branch 'master'
            }
            steps {
                build job: 'backboneAuth-oa1-server-increment-to-next-version', wait: false
            }
        }
    }
    post {
        always {
            script {
                if (currentBuild.result != 'NOT_BUILT') {
                    def finalResult = currentBuild.currentResult
                    def colorCode = "#00ff7f"
                    if (finalResult == "FAILURE") {
                        colorCode = "#ff0000"
                    } else if (finalResult == "UNSTABLE") {
                        colorCode = "#ffd900"
                    } else if (finalResult == "ABORTED") {
                        colorCode = "#797979"
                    }
                    slackSend channel: "#jenkins-build", color: colorCode, message: "Build ${finalResult} - ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", tokenCredentialId: 'slack-integration-token'
                }
            }
        }
    }

}


// Gradlewコマンドを実行する
def gradlew(command) {
    if (isUnix()) {
        sh "./gradlew ${command} --stacktrace --daemon --warning-mode all --refresh-dependencies "
    } else {
        bat "./gradlew.bat ${command} --stacktrace --daemon --warning-mode all --refresh-dependencies "
    }
}

// Mavenコマンドを実行する
def mvn(mvnHome, command, prefix) {
    withEnv(["MVN_HOME=${mvnHome}"]) {
        if (isUnix()) {
            sh "${prefix} $MVN_HOME/bin/mvn ${command} -B -e"
        } else {
            bat(/"${prefix} %MVN_HOME%\bin\mvn ${command} -B -e"/)
        }
    }
}

// デプロイする
// args.warDir warの格納ディレクトリ
// args.appName アプリ名
// args.appVersion アプリのバージョン
//def deploy(Map args) {
//    // 秘密鍵のパス ※Tomcatサーバにファイル転送するので事前にJenkinsサーバのどこかに秘密鍵を格納しておく必要がある
//    def keyDir = '/var/lib/jenkins/.ssh/xxx'
//    // Tomcatサーバのアドレスとユーザ名
//    def webServerAddress = 'ecX-XX-XXX-X-X.xx-xxxx-x.xxxxxxxx'
//    def webServerUser = 'hoge-user'
//    def webServer = "${webServerUser}@${webServerAddress}"

//    def srcWar = "${args.appName}-${args.appVersion}.war"
//    def destWar = "${args.appName}.war"

//    // ファイル転送してTomcatのwebappsにwarを配置する
//    sh "sudo -S scp -i ${keyDir} ./${args.warDir}/${srcWar} ${webServer}:/home/ec2-user"
//    sh "sudo -S ssh -i ${keyDir} ${webServer} \"sudo cp /home/ec2-user/${srcWar} /usr/share/tomcat8/webapps/${destWar}\""
//}
