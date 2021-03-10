package net.jagunma.backbone.auth.authmanager.infra.web.oa10000;

import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.util.HttpSendUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.dateProvider.DateProvider;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;
import net.jagunma.common.values.model.operator.SimpleOperator.SimpleOperatorBuilder;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa10000ControllerTest {

    // 実行既定値
    private final String SESSION_KEY_ACCES_TOKEN = "session_key_access_token";
    private final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1";
    private final ConcurrentModel model = new ConcurrentModel();
    private final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
    private final Long operatorId = 1018L;
    private final String operatorCode = "yu001009";
    private final String operatorName = "ｙｕ００１００９";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "店舗００１";
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "ＪＡ００６";
    private final String remoteAddr = "001.002.003.004";
    private SimpleOperator simpleOperator = createSimpleOperator();

    //検証値
    private String actualPath;
    private Object actualRequest;
    private Class<?> actualClazz;

    // AuditInfoHolderデータの作成
    private void createAuditInfoHolder() {
        AuditInfoHolder.set(AuthInf.createFrom(
            simpleOperator.getBranch().getJaAtMoment().getJaAttribute().getJaCode().getValue(),
            simpleOperator.getBranch().getBranchAttribute().getBranchCode().getValue(),
            simpleOperator.getIdentifier().longValue(),
            simpleOperator.getOperatorName(),
            remoteAddr),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            simpleOperator.getBranch().getJaAtMoment(),
            simpleOperator.getBranch(),
            simpleOperator);
    }
    // SimpleOperatorデータの作成
    private SimpleOperator createSimpleOperator() {
        return new SimpleOperatorBuilder()
            .withIdentifier(operatorId)
            .withOperatorCode(OperatorCode.of(operatorCode))
            .withOperatorName(operatorName)
            .withBranchIdentifier(branchId)
            .withBranch(createBranchAtMoment())
            .build();
    }
    // BranchesAtMomentデータの作成
    private BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment())
            .withBranchAttribute(
                BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName(branchName).build())
            .build();
    }
    // JaAtMomentデータの作成
    private JaAtMoment createJaAtMoment() {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    // メニュー（テスト対象クラス）
    private Oa10000Controller createOa10000Controller() {
        return new Oa10000Controller();
    }
    // Http送信 ユーティリティのスタブ
    private HttpSendUtil createHttpSendUtil() {
        BackboneAuthConfig backboneAuthConfig = new BackboneAuthConfig();
        return new HttpSendUtil(backboneAuthConfig) {
            public Object postBackboneAuthOa2(String path, Object request, Class<?> clazz) {
                actualPath = path;
                actualRequest = request;
                actualClazz = clazz;
                if (Strings2.isBlank(request.toString())) {
                    throw new GunmaRuntimeException("EOA10001");
                }
                return simpleOperator;
            }
        };
    }

    /**
     * {@link Oa10000Controller#get(HttpServletRequest, Model)}テスト
     *  ●パターン
     *    正常
     *    ・AuditInfoHolder.AuthInfが無い場合でサインイン画面にリダイレクト
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // テスト対象クラス生成
        Oa10000Controller controller = createOa10000Controller();

        // 実行値
        AuditInfoHolder.clear();

        // 期待値
        String expected = String.format("redirect:/ed01000/get?redirect_uri=%1$s://%2$s:%3$s/oa10000/showForm",
            mockHttpServletRequest.getScheme(), mockHttpServletRequest.getServerName(), mockHttpServletRequest.getServerPort());

        // 実行
        String actual = controller.get(mockHttpServletRequest, model);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link Oa10000Controller#get(HttpServletRequest, Model)}テスト
     *  ●パターン
     *    正常
     *    ・AuditInfoHolder.AuthInfがありメニュー画面（自画面）を表示
     *
     *  ●検証事項
     *  ・戻り値
     *  ・Http送信 ユーティリティの引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {

        // テスト対象クラス生成
        Oa10000Controller controller = createOa10000Controller();

        // 実行値
        controller.setHttpServletRequest(new MockHttpServletRequest());
        controller.setHttpSession(new MockHttpSession());
        createAuditInfoHolder();
        controller.setSessionAttribute(SESSION_KEY_ACCES_TOKEN, accessToken);
        controller.setHttpSendUtil(createHttpSendUtil());

        // 期待値
        String expected = "oa10000";
        String expectedPath = "/oa31010/getSimpleOperator";

        // 実行
        String actual = controller.get(mockHttpServletRequest, model);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualPath).isEqualTo(expectedPath);
        assertThat(actualRequest.toString()).isEqualTo(accessToken);
        assertThat(actualClazz).isEqualTo(SimpleOperator.class);
    }

    /**
     * {@link Oa10000Controller#get(HttpServletRequest, Model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {

        // テスト対象クラス生成
        Oa10000Controller controller = createOa10000Controller();

        // 実行値
        controller.setHttpServletRequest(new MockHttpServletRequest());
        controller.setHttpSession(new MockHttpSession());
        createAuditInfoHolder();
        // Http送信の引数（アクセストークン）空白にしてHttp送信スタブでGunmaRuntimeExceptionをthrowする
        controller.setSessionAttribute(SESSION_KEY_ACCES_TOKEN, "");
        controller.setHttpSendUtil(createHttpSendUtil());

        // 期待値
        String expected = "oa10000";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actual = controller.get(mockHttpServletRequest, model);
        BaseOfVo actualVo = (BaseOfVo) model.getAttribute("form");

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa10000Controller#get(HttpServletRequest, Model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test3() {

        // テスト対象クラス生成
        Oa10000Controller controller = createOa10000Controller();

        // 実行値
        controller.setHttpServletRequest(new MockHttpServletRequest());
        controller.setHttpSession(new MockHttpSession());
        createAuditInfoHolder();
        controller.setSessionAttribute(SESSION_KEY_ACCES_TOKEN, accessToken);
        controller.setHttpSendUtil(createHttpSendUtil());
        // nullを設定してsetAuthInfでRuntimeExceptionを発生させる
        simpleOperator = null;

        // 期待値
        String expected = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actual = controller.get(mockHttpServletRequest, model);
        BaseOfVo actualVo = (BaseOfVo) model.getAttribute("form");

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}