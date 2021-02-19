package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.jagunma.backbone.auth.authmanager.application.commandService.StoreSignInTrace;
import net.jagunma.backbone.auth.authmanager.application.queryService.Authentication;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.common.common.constant.SpecialOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OA31010コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA3
 * 機能グループ名：認可API
 * 機能ID：OA31010
 * 機能名：業務オペレーター認証
 * サービスID：OA31010
 * サービス名：OA31010サービス
 * -------------------------------------------------
 * </pre>
 */
@RestController
@RequestMapping(value = "oa31010")
public class Oa31010Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa31010Controller.class);

    private final Authentication authentication;
    private final StoreSignInTrace storeSignInTrace;

    // コンストラクタ
    public Oa31010Controller(Authentication authentication,
        StoreSignInTrace storeSignInTrace) {

        this.authentication = authentication;
        this.storeSignInTrace = storeSignInTrace;
    }

    /**
     * 認証を行います
     *
     * @param arg 認証 Arg
     * @return 認証結果
     */
    @PostMapping(path = "/authentication")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
    public ResponseEntity<Oa31010SignInResult> authentication(
        @RequestBody Oa31010SignInArg arg) {

        // ToDo: 未ログインオペレータを設定（Oa1サーバで設定した情報が引き継がれるはずなので、このコードは削除される予定）
        setAuditInfoHolder(SpecialOperator.NON_LOGIN_OPERATOR.simpleOperator());

        // サインイン
        return authentication(arg, SignInCause.サインイン);
    }

    /**
     * 継続認証を行います
     *
     * @param arg 認証 Arg
     * @return 認証結果
     */
    @PostMapping(path = "/continuedAuthentication")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
    public ResponseEntity<Oa31010SignInResult> continuedAuthentication(
        @RequestBody Oa31010SignInArg arg) {

        // ToDo: 未ログインオペレータを設定（Oa1サーバで設定した情報が引き継がれるはずなので、このコードは削除される予定）
        setAuditInfoHolder(SpecialOperator.NON_LOGIN_OPERATOR.simpleOperator());

        // サインイン
        return authentication(arg, SignInCause.継続サインイン);
    }

    /**
     * 認証を行います
     *
     * @param arg         認証 Arg
     * @param signInCause サインイン起因
     * @return 認証結果
     */
    private ResponseEntity<Oa31010SignInResult> authentication(
        Oa31010SignInArg arg,
        SignInCause signInCause) {

        LOGGER.debug("operatorCode:" + arg.getOperatorCode());

        try {
            Oa31010AuthenticationConverter authenticationConverter = Oa31010AuthenticationConverter
                .with(arg.getOperatorCode(), arg.getPassword());
            Oa31010AuthenticationPresenter authenticationPresenter = new Oa31010AuthenticationPresenter();

            // 認証
            authentication.execute(authenticationConverter, authenticationPresenter);

            // サインイン証跡登録
            Oa31010StoreConverter entryConverter = Oa31010StoreConverter.with(
                arg.getClientIpaddress(),
                arg.getOperatorCode(),
                signInCause,
                authenticationPresenter.getSignInResult());
            storeSignInTrace.execute(entryConverter);

            Oa31010SignInResult result = Oa31010SignInResult.createFrom(authenticationPresenter.getSignInResult());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException re) {
            //ToDo: 例外時の戻りが不明（APIの戻りの型はこれでよいのか？）
            //ToDo: catchする例外は RuntimeException でよい？ GunmaRuntimeExceptionは？ 「@ApiResponse」との関係は？
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
