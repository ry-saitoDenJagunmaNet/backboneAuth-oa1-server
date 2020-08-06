package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.model.types.ConditionsExpirationSelect;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Validator
 */
class OperatorReferenceValidator {
	private OperatorSearchRequest request;

	/**
	 * コンストラクタ
	 */
	OperatorReferenceValidator(OperatorSearchRequest request) {
		this.request = request;
	}

	public static OperatorReferenceValidator with(OperatorSearchRequest request) {
		return new OperatorReferenceValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {
		// 有効期限のチェック
		if (ConditionsExpirationSelect.状態指定日.getCode().equals(request.getExpirationSelect())) {
			Preconditions.checkNotNull(request.getExpirationStatusDate(),
				() -> new GunmaRuntimeException("EOA10002", "有効期限の状態指定日"));
		}

		if (ConditionsExpirationSelect.条件指定.getCode().equals(request.getExpirationSelect())) {
			if (request.getExpirationStartDateFrom() == null &&
				request.getExpirationStartDateTo() == null &&
				request.getExpirationEndDateFrom() == null &&
				request.getExpirationStartDateTo() == null) {
				throw new GunmaRuntimeException("EOA10002", "有効期限の条件指定");
			}
			if (request.getExpirationStartDateFrom() != null &&
				request.getExpirationStartDateTo() != null) {
				if (request.getExpirationStartDateFrom().compareTo(request.getExpirationStartDateTo()) > 0) {
					throw new GunmaRuntimeException("EOA10003", "有効期限開始の条件指定");
				}
			}
			if (request.getExpirationEndDateFrom() != null &&
				request.getExpirationStartDateTo() != null) {
				if (request.getExpirationEndDateFrom().compareTo(request.getExpirationStartDateTo()) > 0) {
					throw new GunmaRuntimeException("EOA10003", "有効期限終了の条件指定");
				}
			}
		}

		// サブシステムロールのチェック
		request.getSubSystemRoleList().forEach(a -> {
			if (Oa11010Vo.CHECKBOX_TRUE.equals(a.getSubSystemRoleSelected())) {
				if (ConditionsExpirationSelect.状態指定日.getCode().equals(a.getExpirationSelect())) {
					Preconditions.checkNotNull(a.getExpirationStatusDate(),
						() -> new GunmaRuntimeException("EOA10002", "サブシステムロール " + a.getSubSystemRoleCode() + "の状態指定日"));
				}

				if (ConditionsExpirationSelect.条件指定.getCode().equals(a.getExpirationSelect())) {
					if (a.getExpirationStartDateFrom() == null &&
						a.getExpirationStartDateTo() == null &&
						a.getExpirationEndDateFrom() == null &&
						a.getExpirationEndDateTo() == null) {
						throw new GunmaRuntimeException("EOA10002", "サブシステムロール " + a.getSubSystemRoleCode() + "の条件指定");
					}
					if (a.getExpirationStartDateFrom() != null &&
						a.getExpirationStartDateTo() != null) {
						if (a.getExpirationStartDateFrom().compareTo(a.getExpirationStartDateTo()) > 0) {
							throw new GunmaRuntimeException("EOA10003", "サブシステムロール " + a.getSubSystemRoleCode() + "の条件指定");
						}
					}
					if (a.getExpirationEndDateFrom() != null &&
						a.getExpirationEndDateTo() != null) {
						if (a.getExpirationEndDateFrom().compareTo(a.getExpirationEndDateTo()) > 0) {
							throw new GunmaRuntimeException("EOA10003", "サブシステムロール " + a.getSubSystemRoleCode() + "の条件指定");
						}
					}
				}
			}
		});

		// 取引ロールのチェック
		request.getBizTranRoleList().forEach(a -> {
			if (Oa11010Vo.CHECKBOX_TRUE.equals(a.getBizTranRoleSelected())) {
				if (ConditionsExpirationSelect.状態指定日.getCode().equals(a.getExpirationSelect())) {
					Preconditions.checkNotNull(a.getExpirationStatusDate(),
						() -> new GunmaRuntimeException("EOA10002", "取引ロール " + a.getBizTranRoleCode() + "の状態指定日"));
				}

				if (ConditionsExpirationSelect.条件指定.getCode().equals(a.getExpirationSelect())) {
					if (a.getExpirationStartDateFrom() == null &&
						a.getExpirationStartDateTo() == null &&
						a.getExpirationEndDateFrom() == null &&
						a.getExpirationStartDateTo() == null) {
						throw new GunmaRuntimeException("EOA10002", "取引ロール " + a.getBizTranRoleCode() + "の条件指定");
					}
					if (a.getExpirationStartDateFrom() != null &&
						a.getExpirationStartDateTo() != null) {
						if (a.getExpirationStartDateFrom().compareTo(a.getExpirationStartDateTo()) > 0) {
							throw new GunmaRuntimeException("EOA10003", "取引ロール " + a.getBizTranRoleCode() + "の条件指定");
						}
					}
					if (a.getExpirationEndDateFrom() != null &&
						a.getExpirationEndDateTo() != null) {
						if (a.getExpirationEndDateFrom().compareTo(a.getExpirationEndDateTo()) > 0) {
							throw new GunmaRuntimeException("EOA10003", "取引ロール " + a.getBizTranRoleCode() + "の条件指定");
						}
					}
				}
			}
		});

		// その他 最終パスワード変更日のチェック
		if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getPasswordHistoryCheck())) {
			if (request.getPasswordHistoryLastChangeDate() == null) {
				throw new GunmaRuntimeException("EOA10002", "最終パスワード変更日の日数");
			}

			if (request.getPasswordHistoryLastChangeDateStatus() == null ||
				request.getPasswordHistoryLastChangeDateStatus().length() == 0) {
				throw new GunmaRuntimeException("EOA10002", "最終パスワード変更日の変更有無");
			}
		}

		// その他 最終サインイン試行日のチェック
		if (request.getSignintraceTrydateFrom() != null &&
			request.getSignintraceTrydateTo() != null) {
			if (request.getSignintraceTrydateFrom().compareTo(request.getSignintraceTrydateTo()) > 0) {
				throw new GunmaRuntimeException("EOA10003", "最終サインイン試行日");
			}
		}

	}
}
