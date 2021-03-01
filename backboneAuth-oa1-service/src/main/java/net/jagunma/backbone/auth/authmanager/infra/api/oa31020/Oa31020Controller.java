package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
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
 * OA31020コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA3
 * 機能グループ名：認可API
 * 機能ID：OA31020
 * 機能名：権限取得
 * サービスID：OA31020
 * サービス名：OA31020サービス
 * -------------------------------------------------
 * </pre>
 */
@RestController
@RequestMapping(value = "oa31020")
public class Oa31020Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa31020Controller.class);

    private final SearchAccessible searchAccessible;

    // コンストラクタ
    public Oa31020Controller(SearchAccessible searchAccessible) {
        this.searchAccessible = searchAccessible;
    }

    /**
     * 可能取引を取得します
     *
     * @param operatorId オペレータID
     * @return 可能取引リスト
     */
    @PostMapping(path = "getAccessible/{operatorId}", name = "可能取引を返却する")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "業務処理成功(GunmaBusinessRuntimeExceptionもここ)"),
        @ApiResponse(responseCode = "401", description = "認証情報が特定できない場合"),
        @ApiResponse(responseCode = "403", description = "認証情報は特定できたが処理を認可できない場合"),
        @ApiResponse(responseCode = "404", description = "対象URLが存在しない場合"),
        @ApiResponse(responseCode = "500", description = "GunmaRuntimeExceptionはここ")})
    public ResponseEntity<List<Oa31020AccessibleResult>> getAccessible(@PathVariable("operatorId") Long operatorId) {

        LOGGER.debug("operatorId:" + operatorId);

        Oa31020Presenter presenter = new Oa31020Presenter();
        try {
            Oa31020Converter converter = Oa31020Converter.with(operatorId);
            searchAccessible.execute(converter, presenter);
        } catch (RuntimeException re) {
            //ToDo: 例外時の戻りが不明（APIの戻りの型はこれでよいのか？）
            //ToDo: catchする例外は RuntimeException でよい？ GunmaRuntimeExceptionは？ 「@ApiResponse」との関係は？
            return new ResponseEntity<>(newArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Oa31020AccessibleResult> list = newArrayList();
        presenter.bindTo(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
