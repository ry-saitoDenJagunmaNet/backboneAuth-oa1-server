package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPackRepositoryForStore;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * オペレーター登録サービス
 */
@Service
@Transactional
public class EntryOperator {

	private final OperatorEntryPackRepositoryForStore operatorEntryPackRepositoryForStore;
	private final BranchAtMomentRepository branchAtMomentRepository;

	public EntryOperator(OperatorEntryPackRepositoryForStore operatorEntryPackRepositoryForStore,
		BranchAtMomentRepository branchAtMomentRepository) {

		this.operatorEntryPackRepositoryForStore = operatorEntryPackRepositoryForStore;
		this.branchAtMomentRepository = branchAtMomentRepository;
	}

	/**
	 * オペレーターの登録を行います。
	 *
	 * @param request オペレーター登録サービス Request
	 */
	public void execute(OperatorEntryRequest request) {

		// パラメーターの検証
		EntryOperatorValidator.with(request).validate();

		// 店舗の取得を行います
		BranchAtMoment branchAtMoment = getBranchAtMoment(request.getTempoId());

		// 店舗が当JAに属するかのチェックを行います
		checkBranchBelongJa(branchAtMoment);

		// オペレーターエントリーパックの生成を行います
		OperatorEntryPack operatorEntryPack = createOperatorEntryPack(request,
			AuditInfoHolder.getJa().getIdentifier(),
			AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue(),
			branchAtMoment.getBranchAttribute().getBranchCode().getValue());

		// オペレーターエントリーパックをインサートします
		operatorEntryPackRepositoryForStore.insert(operatorEntryPack);
	}

	/**
	 * 店舗の取得を行います。
	 *
	 * @param tempoId 店舗ID
	 * @return branchAtMoment 店舗
	 */
	private BranchAtMoment getBranchAtMoment(Long tempoId) {
		BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();

		criteria.getIdentifierCriteria().setEqualTo(tempoId);

		return branchAtMomentRepository.findOneBy(criteria);
	}

	/**
	 * 店舗が当JAに属するかのチェックを行います。
	 *
	 * @param branchAtMoment 店舗
	 */
	private void checkBranchBelongJa (BranchAtMoment branchAtMoment) {
		if (!branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode().sameValueAs(
			AuditInfoHolder.getJa().getJaAttribute().getJaCode())) {
			throw new GunmaRuntimeException("EOA12001");
		}
	}

	/**
	 * オペレーターエントリーパックの生成を行います。
	 *
	 * @param request オペレーター登録サービス Request
	 * @param jaId ＪＡID
	 * @param jaCode ＪＡコード
	 * @param tempoCode 店舗コード
	 * @return オペレーターエントリーパック
	 */
	private OperatorEntryPack createOperatorEntryPack(OperatorEntryRequest request,
		Long jaId,
		String jaCode,
		String tempoCode) {

		return OperatorEntryPack.createFrom(
			request.getOperatorCode(),
			request.getOperatorName(),
			request.getMailAddress(),
			request.getExpirationStartDate(),
			request.getExpirationEndDate(),
			jaId,
			jaCode,
			request.getTempoId(),
			tempoCode,
			request.getChangeCause(),
			request.getPassword(),
			request.getConfirmPassword());
	}
}
