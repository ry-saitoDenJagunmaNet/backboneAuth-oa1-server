package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.util.beans.Beans;
import net.jagunma.common.values.model.operator.OperatorCriteria;
import org.springframework.stereotype.Component;

/**
 * オペレーターエントリーパック登録
 */
@Component
public class OperatorEntryPackForStoreDataSource implements
	OperatorEntryPackRepositoryForStore {

	private final OperatorEntityDao operatorEntityDao;
	private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
	private final OperatorHistoryEntityDao operatorHistoryEntityDao;
	private final PasswordHistoryEntityDao passwordHistoryEntityDao;

	// コンストラクタ
	public OperatorEntryPackForStoreDataSource(
		OperatorEntityDao operatorEntityDao,
		OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao,
		OperatorHistoryEntityDao operatorHistoryEntityDao,
		PasswordHistoryEntityDao passwordHistoryEntityDao) {

		this.operatorEntityDao = operatorEntityDao;
		this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
		this.operatorHistoryEntityDao = operatorHistoryEntityDao;
		this.passwordHistoryEntityDao = passwordHistoryEntityDao;
	}

	/**
	 * オペレーターエントリーパックをインサートします
	 *
	 * @param operatorEntryPack オペレーターエントリーパック
	 */
	public void insert(OperatorEntryPack operatorEntryPack) {

		// オペレーター（コード）の重複チェック
		if (isDuplicate(operatorEntryPack.getOperatorCode())) { return; }

		// オペレーターをインサートします
		OperatorEntity operatorEntity = insertOperator(operatorEntryPack);

		// オペレーター履歴ヘッダーをインサートします
		OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = insertOperatorHistoryHeader(operatorEntryPack, operatorEntity);

		// オペレーター履歴をインサートします
		insertOperatorHistory(operatorHistoryHeaderEntity, operatorEntity);

		// パスワード履歴をインサートします
		insertPasswordHistory(operatorEntryPack, operatorEntity);
	}

	/**
	 * オペレーター（コード）の重複チェックを行います。
	 *
	 * @param operatorCode オペレーターコード
	 * @return true: 重複あり / false: 重複なし
	 */
	private boolean isDuplicate(String operatorCode) {
		OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();

		operatorEntityCriteria.getOperatorCodeCriteria().setEqualTo(operatorCode);

		int cnt = operatorEntityDao.countBy(operatorEntityCriteria);

		return cnt != 0;
	}

	/**
	 * オペレーターをインサートします
	 *
	 * @param operatorEntryPack オペレーターエントリーパック
	 * @return オペレーターエンティティ
	 */
	private OperatorEntity insertOperator(OperatorEntryPack operatorEntryPack) {
		OperatorEntity operatorEntity = new OperatorEntity();

		operatorEntity.setOperatorCode(operatorEntryPack.getOperatorCode());
		operatorEntity.setOperatorName(operatorEntryPack.getOperatorName());
		operatorEntity.setMailAddress(operatorEntryPack.getMailAddress());
		operatorEntity.setExpirationStartDate(operatorEntryPack.getExpirationStartDate());
		operatorEntity.setExpirationEndDate(operatorEntryPack.getExpirationEndDate());
		operatorEntity.setIsDeviceAuth(false);
		operatorEntity.setJaId(operatorEntryPack.getJaId());
		operatorEntity.setJaCode(operatorEntryPack.getJaCode());
		operatorEntity.setTempoId(operatorEntryPack.getTempoId());
		operatorEntity.setTempoCode(operatorEntryPack.getTempoCode());
		operatorEntity.setAvailableStatus(AvailableStatus.Available.getCode());

		operatorEntityDao.insert(operatorEntity);

		return operatorEntity;
	}

	/**
	 * オペレーター履歴ヘッダーをインサートします
	 *
	 * @param operatorEntryPack オペレーターエントリーパック
	 * @param operatorEntity オペレーターエンティティ
	 * @return オペレーター履歴ヘッダーエンティティ
	 */
	private OperatorHistoryHeaderEntity insertOperatorHistoryHeader(OperatorEntryPack operatorEntryPack, OperatorEntity operatorEntity) {
		OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = new OperatorHistoryHeaderEntity();

		operatorHistoryHeaderEntity.setOperatorId(operatorEntity.getOperatorId());
		operatorHistoryHeaderEntity.setChangeDateTime(operatorEntity.getCreatedAt());
		operatorHistoryHeaderEntity.setChangeCause(operatorEntryPack.getChangeCause());

		operatorHistoryHeaderEntityDao.insert(operatorHistoryHeaderEntity);

		return operatorHistoryHeaderEntity;
	}

	/**
	 * オペレーター履歴をインサートします
	 *
	 * @param operatorHistoryHeaderEntity オペレーター履歴ヘッダーエンティティ
	 * @param operatorEntity オペレーターエンティティ
	 */
	private void insertOperatorHistory(OperatorHistoryHeaderEntity operatorHistoryHeaderEntity, OperatorEntity operatorEntity) {

		OperatorHistoryEntity operatorHistoryEntity = Beans.createAndCopy(OperatorHistoryEntity.class, operatorEntity).execute();

		operatorHistoryEntity.setOperatorHistoryId(operatorHistoryHeaderEntity.getOperatorHistoryId());

		operatorHistoryEntityDao.insert(operatorHistoryEntity);
	}

	/**
	 * パスワード履歴をインサートします
	 *
	 * @param operatorEntryPack オペレーターエントリーパック
	 * @param operatorEntity オペレーターエンティティ
	 */
	private void insertPasswordHistory(OperatorEntryPack operatorEntryPack, OperatorEntity operatorEntity) {
		PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();

		passwordHistoryEntity.setOperatorId(operatorEntity.getOperatorId());
		passwordHistoryEntity.setChangeDateTime(operatorEntity.getCreatedAt());
		passwordHistoryEntity.setPassword(operatorEntryPack.getPassword());
		passwordHistoryEntity.setChangeType(PasswordChangeType.Initial.getCode());

		passwordHistoryEntityDao.insert(passwordHistoryEntity);
	}
}
