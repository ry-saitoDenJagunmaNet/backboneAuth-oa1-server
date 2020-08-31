package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * オペレーター コマンドサービス Validator
 */
class OperatorCommandValidator {

	private final OperatorEntryRequest request;

	/**
	 * コンストラクタ
	 */
	OperatorCommandValidator(OperatorEntryRequest request) {
		this.request = request;
	}

	public static OperatorCommandValidator with(OperatorEntryRequest request) {
		return new OperatorCommandValidator(request);
	}

	/**
	 * リクエストの検証を行います。
	 */
	public void entryValidate() {

		// 未入力チェック
		Preconditions.checkNotNull(request.getOperatorCode6(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));

		// オペレーター存在チェック（キーダブリチェックは先に行う）
//		OperatorEntity operatorEntity = operatorEntityDao.findOneBy(request.genOperatorEntityCriteria(request));
//		Preconditions.checkNotNull(operatorEntity, () -> new GunmaRuntimeException("EOA10000", "オペレーター"));

		// 未入力チェック
		Preconditions.checkNotNull(request.getOperatorName(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getMailAddress(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getExpirationEndDate(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));
		Preconditions.checkNotNull(request.getTempoCode(), () -> new GunmaRuntimeException("EOA10000", "オペレーター"));

		// 有効期限 大小チェック
		if (request.getExpirationStartDate().compareTo(request.getExpirationEndDate()) == 1) {
			throw new GunmaRuntimeException("EOA10000", "オペレーター");

		}
	}
}
