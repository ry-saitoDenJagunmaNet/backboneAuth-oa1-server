package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperatorCommandService {

	private final OperatorEntityDao operatorEntityDao;
	private final PasswordHistoryEntityDao passwordHistoryEntityDao;
//	private final OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService;
//	private final OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService;

	public OperatorCommandService(
		OperatorEntityDao operatorEntityDao,
		PasswordHistoryEntityDao passwordHistoryEntityDao) {
//		OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService,
//		OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService) {

		this.operatorEntityDao = operatorEntityDao;
		this.passwordHistoryEntityDao = passwordHistoryEntityDao;
//		this.operatorSubSystemRoleReferenceService = operatorSubSystemRoleReferenceService;
//		this.operatorBizTranRoleReferenceService = operatorBizTranRoleReferenceService;
	}

	/**
	 * オペレーターを登録します。
	 * @param request 登録内容
	 */
	public void entry(OperatorEntryRequest request) {

		OperatorEntity operatorEntity = operatorEntityDao.findOneBy(request.genOperatorEntityCriteria(request));

		// パラメーターの検証
		OperatorCommandValidator.with(request).entryValidate();

		// 登録
//		operatorEntityDao.insert();
	}
}
