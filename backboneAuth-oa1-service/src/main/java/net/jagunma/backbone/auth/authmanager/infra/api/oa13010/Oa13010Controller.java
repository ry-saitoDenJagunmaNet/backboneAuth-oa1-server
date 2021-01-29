package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.commandService.EntrySignInTrace;
import net.jagunma.backbone.auth.authmanager.application.queryService.Authentication;
import net.jagunma.backbone.auth.authmanager.infra.api.oa13020.Oa13020Presenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class Oa13010Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa13010Controller.class);

    private final Authentication authentication;
    private final EntrySignInTrace entrySignInTrace;

    // コンストラクタ
    public Oa13010Controller(Authentication authentication,
        EntrySignInTrace entrySignInTrace) {

        this.authentication = authentication;
        this.entrySignInTrace = entrySignInTrace;
    }

//    /**
//     * 認証を行います
//     *
//     * @param operatorCode オペレータコード
//     * @param password     パスワード
//     * @return 認証したオペレーター情報
//     */
//    @PostMapping(path = "getAccessible/{operatorCode}/{password}")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
//        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
//        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
//        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
//        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
//    public ResponseEntity<OperatorInfoResult> authenticate(@PathVariable("operatorCode") String operatorCode,
//        @PathVariable("password") String password) {
//
//        LOGGER.debug("operatorCode:" + operatorCode);
//
//
//    }

}
