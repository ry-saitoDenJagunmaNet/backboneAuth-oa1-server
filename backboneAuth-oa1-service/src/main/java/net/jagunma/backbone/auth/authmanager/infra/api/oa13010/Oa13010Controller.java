package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.jagunma.backbone.auth.authmanager.application.commandService.EntrySignInTrace;
import net.jagunma.backbone.auth.authmanager.application.queryService.Authentication;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OA13010コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA13010
 * 機能名：業務オペレーター認証
 * サービスID：OA13010
 * サービス名：OA13010サービス
 * -------------------------------------------------
 * </pre>
 */
@RestController
@RequestMapping(value = "oa13010")
public class Oa13010Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa13010Controller.class);

    private final Authentication authentication;
    private final EntrySignInTrace entrySignInTrace;

    // コンストラクタ
    public Oa13010Controller(Authentication authentication,
        EntrySignInTrace entrySignInTrace) {

        this.authentication = authentication;
        this.entrySignInTrace = entrySignInTrace;
    }

    /**
     * サインインを行います
     *
     * @param arg     サインイン Arg
     * @return 認証結果
     */
    @PostMapping(path = "/signIn")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
    public ResponseEntity<Oa13010SignInResult> signIn(
        @RequestBody Oa13010SignInArg arg) {

        // ToDo: テストサインイン情報セット
        setAuthInf();

        // サインイン
        return signIn(arg, SignInCause.サインイン);
    }

    /**
     * 継続サインインを行います
     *
     * @param arg     サインイン Arg
     * @return 認証結果
     */
    @PostMapping(path = "/continuedSignIn")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
    public ResponseEntity<Oa13010SignInResult> continuedSignIn(
        @RequestBody Oa13010SignInArg arg) {

        // ToDo: テストサインイン情報セット
        setAuthInf();

        // サインイン
        return signIn(arg, SignInCause.継続サインイン);
    }

    /**
     * サインインを行います
     *
     * @param arg         サインイン Arg
     * @param signInCause サインイン起因
     * @return 認証結果
     */
    private ResponseEntity<Oa13010SignInResult> signIn(
        Oa13010SignInArg arg,
        SignInCause signInCause) {

        LOGGER.debug("operatorCode:" + arg.getOperatorCode());

        try {
            Oa13010AuthenticationConverter authenticationConverter = Oa13010AuthenticationConverter.with(arg.getOperatorCode(), arg.getPassword());
            Oa13010AuthenticationPresenter authenticationPresenter = new Oa13010AuthenticationPresenter();

            // 認証
            authentication.execute(authenticationConverter, authenticationPresenter);

            // サインイン証跡登録
            Oa13010EntryConverter entryConverter = Oa13010EntryConverter.with(
                arg.getClientIpaddress(),
                arg.getOperatorCode(),
                signInCause,
                authenticationPresenter.getSignInResult());
            entrySignInTrace.execute(entryConverter);

            Oa13010SignInResult result = Oa13010SignInResult.createFrom(authenticationPresenter.getSignInResult());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException re) {
            //ToDo: 例外時の戻りが不明（APIの戻りの型はこれでよいのか？）
            //ToDo: catchする例外は RuntimeException でよい？ GunmaRuntimeExceptionは？ 「@ApiResponse」との関係は？
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
