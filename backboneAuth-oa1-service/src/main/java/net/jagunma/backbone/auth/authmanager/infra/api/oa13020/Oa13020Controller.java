package net.jagunma.backbone.auth.authmanager.infra.api.oa13020;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessible;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OA13020コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA13020
 * 機能名：権限取得
 * サービスID：OA13020
 * サービス名：OA13020サービス
 * -------------------------------------------------
 * </pre>
 */
@RestController
@RequestMapping(value = "oa13020")
public class Oa13020Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa13020Controller.class);

    private final SearchAccessible searchAccessible;

    // コンストラクタ
    public Oa13020Controller(SearchAccessible searchAccessible) {
        this.searchAccessible = searchAccessible;
    }

    /**
     * 可能取引を取得します
     *
     * @param operatorId オペレータID
     * @return
     */
    @PostMapping(path = "getAccessible/{operatorId}}", name = "可能取引を返却する")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
    public ResponseEntity<Map<String, List<String>>> getAccessible(@PathVariable("operatorId") Long operatorId) {

        LOGGER.debug("operatorId:" + operatorId);

        Oa13020Presenter presenter = new Oa13020Presenter();
        try {
            Oa13020Converter converter = Oa13020Converter.with(operatorId);

            searchAccessible.execute(converter, presenter);
        } catch (RuntimeException re) {
            //ToDo: 例外時の戻りが不明（APIの戻りの型はこれでよいのか？）
            //ToDo: catchする例外は RuntimeException でよい？ GunmaRuntimeExceptionは？ 「@ApiResponse」との関係は？
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(presenter.getResponse(), HttpStatus.OK);
    }
}
