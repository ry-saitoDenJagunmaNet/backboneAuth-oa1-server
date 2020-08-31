package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター検索 DataSource
 */
@Component
public class OperatorRepositoryDataSource implements OperatorRepository {

    private final OperatorEntityDao operatorEntityDao;

    // コンストラクタ
    public OperatorRepositoryDataSource(OperatorEntityDao operatorEntityDao) {
        this.operatorEntityDao = operatorEntityDao;
    }

    /**
     * オペレーターの条件検索を行います。
     *
     * @param operatorCriteria オペレーターの検索条件
     * @return オペレーター
     */
    @Override
    public Operator findOneBy(OperatorCriteria operatorCriteria) {
        OperatorEntity operatorEntity = operatorEntityDao.findOneBy(operatorCriteria);
        return Operator.of(operatorEntity);
    }
}
