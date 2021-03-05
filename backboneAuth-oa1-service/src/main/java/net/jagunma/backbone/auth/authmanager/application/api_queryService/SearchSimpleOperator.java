package net.jagunma.backbone.auth.authmanager.application.api_queryService;

import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import org.springframework.stereotype.Service;

/**
 * オペレーター（簡略版）検索サービス
 */
@Service
public class SearchSimpleOperator {

    private final OperatorRepository operatorRepository;

    // コンストラクタ
    public SearchSimpleOperator(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    /**
     * オペレーター（簡略版）を検索します
     *
     * @param request  オペレーター（簡略版）検索サービス Request
     * @param response オペレーター（簡略版）検索サービス Response
     */
    public void execute(SimpleOperatorSearchRequest request, SimpleOperatorSearchResponse response) {

        // ToDo: 本当は、IDで検索する？（アクセストークンと何が結びつくのか不明）
        response.setOperator(operatorRepository.findOneByCode(request.getOperatorCode()));
    }
}
