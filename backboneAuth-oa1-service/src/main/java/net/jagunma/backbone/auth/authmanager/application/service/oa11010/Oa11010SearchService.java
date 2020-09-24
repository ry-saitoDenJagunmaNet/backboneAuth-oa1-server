package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import net.jagunma.backbone.auth.authmanager.application.queryService.OperatorReferenceService;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA11010 オペレーター＜一覧＞ 検索 サービス
 */
@Service
@Transactional
public class Oa11010SearchService {

    private final OperatorReferenceService operatorReferenceService;

    // コンストラクタ
    public Oa11010SearchService(OperatorReferenceService operatorReferenceService) {
        this.operatorReferenceService = operatorReferenceService;
    }

    /**
     * オペレーター検索を行います。
     *
     * @param vo       オペレーター＜一覧＞View Object
     * @param response 検索結果（オペレーターテーブル、Pagination Html）
     */
    public void search(Oa11010Vo vo,
        Oa11010SearchResponseVo response) {

        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();

        // オペレーターリスト取得
        operatorReferenceService.getOperators(converter, presenter);

        presenter.bindTo(response);
    }
}