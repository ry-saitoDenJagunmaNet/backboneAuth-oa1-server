package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.role.bizTranRole.composition.bizTranGrp;

import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引グループ検索 DataSource
 */
@Component
public class BizTranGrpRepositoryDataSource implements BizTranGrpRepository {

	private final BizTranGrpEntityDao bizTranGrpEntityDao;

	// コンストラクタ
	public BizTranGrpRepositoryDataSource(BizTranGrpEntityDao bizTranGrpEntityDao) {
		this.bizTranGrpEntityDao = bizTranGrpEntityDao;
	}

	/**
	 * 取引グループの条件検索を行います。
	 *
	 * @param bizTranGrpCriteria 取引グループの検索条件
	 * @return 取引グループ
	 */
	@Override
	public BizTranGrp findOneBy(BizTranGrpCriteria bizTranGrpCriteria) {
		BizTranGrpEntity bizTranGrpEntity = bizTranGrpEntityDao.findOneBy(bizTranGrpCriteria);
		return BizTranGrp.of(bizTranGrpEntity);
	}
}
