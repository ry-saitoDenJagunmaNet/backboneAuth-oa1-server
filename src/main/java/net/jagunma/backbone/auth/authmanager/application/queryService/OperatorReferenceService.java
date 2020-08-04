package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.model.types.AccountLockLockStatus;
import net.jagunma.backbone.auth.authmanager.application.model.types.ConditionsSelect;
import net.jagunma.backbone.auth.authmanager.application.model.types.ConditionsExpirationSelect;
import net.jagunma.backbone.auth.authmanager.application.model.types.PasswordHistoryChangeType;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorBizTranRoleReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorSubSystemRoleReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010SearchConverterOperatorBizTranRole;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010SearchConverterOperatorSubSystemRole;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntityDao;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperatorReferenceService {
	/**
	 * オペレーター一覧の１ページ当たりの行数
	 */
	private final int PAGE_SIZE = 10;

	private final OperatorEntityDao operatorEntityDao;
	private final AccountLockEntityDao accountLockEntityDao;
	private final PasswordHistoryEntityDao passwordHistoryEntityDao;
	private final SignInTraceEntityDao signInTraceEntityDao;
	private final SignOutTraceEntityDao signOutTraceEntityDao;
	private final OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService;
	private final OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService;
	private final TempoReferenceService tempoReferenceService;

	public OperatorReferenceService(OperatorEntityDao operatorEntityDao,
		AccountLockEntityDao accountLockEntityDao,
		PasswordHistoryEntityDao passwordHistoryEntityDao,
		SignInTraceEntityDao signInTraceEntityDao,
		SignOutTraceEntityDao signOutTraceEntityDao,
		OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService,
		OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService,
		TempoReferenceService tempoReferenceService) {

		this.operatorEntityDao = operatorEntityDao;
		this.accountLockEntityDao = accountLockEntityDao;
		this.passwordHistoryEntityDao = passwordHistoryEntityDao;
		this.signInTraceEntityDao = signInTraceEntityDao;
		this.signOutTraceEntityDao = signOutTraceEntityDao;
		this.operatorSubSystemRoleReferenceService = operatorSubSystemRoleReferenceService;
		this.operatorBizTranRoleReferenceService = operatorBizTranRoleReferenceService;
		this.tempoReferenceService = tempoReferenceService;
	}

	/**
	 * オペレーターリストを取得します。
	 * @param request 条件
	 * @return オペレーターリスト
	 */
	public List<OperatorReferenceDto> getOperatorList(OperatorSearchRequest request) {

		// オペレーター検索
		Orders orders = Orders.empty()
			.addOrder("TempoCode")
			.addOrder("OperatorCode");
		List<OperatorEntity> operatorEntitys = operatorEntityDao.findBy(request.genOperatorEntityCriteria(request), orders);

		// オペレーター_サブシステムロール割当検索
		List<OperatorSubSystemRoleReferenceDto> operatorSubSystemRoles = operatorSubSystemRoleReferenceService.getOperatorSubSystemRoleList();
		// オペレーター_取引ロール割当リスト
		List<OperatorBizTranRoleReferenceDto> operatorBizTranRoles = operatorBizTranRoleReferenceService.getOperatorBizTranRoleList();
		// 店舗リスト
		List<TempoReferenceDto> tempos = tempoReferenceService.getTempoList(request.getJaId());
		// アカウントロック検索
		orders = Orders.empty()
			.addOrder("OperatorId")
			.addOrder("OccurredDateTime", Order.ASC);
		List<AccountLockEntity> accountLockEntitys = accountLockEntityDao.findAll(orders);
		// パスワード履歴検索
		orders = Orders.empty()
			.addOrder("OperatorId")
			.addOrder("ChangeDateTime", Order.ASC);
		List<PasswordHistoryEntity> passwordHistoryEntitys = passwordHistoryEntityDao.findAll(orders);
		// サインイン証跡検索
		orders = Orders.empty()
			.addOrder("OperatorCode")
			.addOrder("TryDateTime", Order.ASC);
		List<SignInTraceEntity> signInTraceEntitys = signInTraceEntityDao.findAll(orders);
		// サインアウト証跡検索
		orders = Orders.empty()
			.addOrder("OperatorId")
			.addOrder("SignOutDateTime", Order.ASC);
		List<SignOutTraceEntity> signOutTraceEntitys = signOutTraceEntityDao.findAll(orders);

		List<OperatorReferenceDto> operatorList = newArrayList();

		for(OperatorEntity operatorEntity : operatorEntitys) {
			OperatorReferenceDto dto = new OperatorReferenceDto();

			// オペレーター_サブシステムロール割当の項目設定および条件判定
			if (!setOperatorSubSystemRoleInfo(dto, request, operatorEntity, operatorSubSystemRoles)) {
				continue;
			}
			// オペレーター_取引ロール割当の項目設定および条件判定
			if (!setOperatorBizTranRoleInfo(dto, request, operatorEntity, operatorBizTranRoles)) {
				continue;
			}
			// アカウントロックの項目設定および条件判定
			if (!setAccountLockInfo(dto, request, operatorEntity, accountLockEntitys)) {
				continue;
			}
			// パスワード履歴の項目設定および条件判定
			if (!setPasswordHistoryInfo(dto, request, operatorEntity, passwordHistoryEntitys)) {
				continue;
			}
			// サインイン証跡の項目設定および条件判定
			if (!setSignInTraceInfo(dto, request, operatorEntity, signInTraceEntitys)) {
				continue;
			}
			// サインアウト証跡の項目設定および条件判定
			if (!setSignOutTraceInfo(dto, request, operatorEntity, signOutTraceEntitys)) {
				continue;
			}

			// オペレーターID
			dto.setOperatorId(operatorEntity.getOperatorId());
			// オペレーターコード
			dto.setOperatorCode(operatorEntity.getOperatorCode());
			// オペレーター名
			dto.setOperatorName(operatorEntity.getOperatorName());
			// メールアドレス
			dto.setMailAddress(operatorEntity.getMailAddress());
			// 有効期限開始日
			dto.setExpirationStartDate(operatorEntity.getExpirationStartDate());
			// 有効期限終了日
			dto.setExpirationEndDate(operatorEntity.getExpirationEndDate());
			// 機器認証
			dto.setIsDeviceAuth(operatorEntity.getIsDeviceAuth());
			// JAID
			dto.setJaId(operatorEntity.getJaId());
			// JAコード
			dto.setJaCode(operatorEntity.getJaCode());
			// 店舗ID
			dto.setTempoId(operatorEntity.getTempoId());
			// 店舗コード
			dto.setTempoCode(operatorEntity.getTempoCode());
			// 利用可否状態
			dto.setAvailableStatus(operatorEntity.getAvailableStatus());
			// 登録者ID
			dto.setCreatedBy(operatorEntity.getCreatedBy());
			// 登録日時
			dto.setCreatedAt(operatorEntity.getCreatedAt());
			// 登録元IPアドレス
			dto.setCreatedIpAddress(operatorEntity.getCreatedIpAddress());
			// 更新者ID
			dto.setUpdatedBy(operatorEntity.getUpdatedBy());
			// 更新日時
			dto.setUpdatedAt(operatorEntity.getUpdatedAt());
			// 更新元IPアドレス
			dto.setUpdatedIpAddress(operatorEntity.getUpdatedIpAddress());
			// レコードバージョン
			dto.setRecordVersion(operatorEntity.getRecordVersion());
			// 店舗名
			List<TempoReferenceDto> tempoReferenceDto =  tempos.stream().filter(t->t.getTempoCode().equals(operatorEntity.getTempoCode())).collect(Collectors.toList());
			if (tempoReferenceDto.size() > 0) {
				dto.setTempoName(tempoReferenceDto.get(0).getTempoName());
			}

			operatorList.add(dto);
		}
		return operatorList;
	}

	/**
	 * オペレーター_サブシステムロール割当の項目設定および条件判定を行います。
	 * @param dto オペレーターDto
	 * @param request 条件
	 * @param operatorEntity オペレーター
	 * @param operatorSubSystemRoleReferenceDto オペレーター_サブシステムロール割当リスト
	 * @return true：オペレーター_サブシステムロール割当の条件で表示対象、false：オペレーター_サブシステムロール割当の条件で表示対象外
	 */
	private boolean setOperatorSubSystemRoleInfo(OperatorReferenceDto dto,
		OperatorSearchRequest request,
		OperatorEntity operatorEntity,
		List<OperatorSubSystemRoleReferenceDto> operatorSubSystemRoleReferenceDto) {

		// 選択チェックしたサブシステムロールを検索条件にする
		List<Oa11010SearchConverterOperatorSubSystemRole> requestOperatorSubSystemRoles =
			request.getSubSystemRoleList().stream().filter(reqossr->
				Oa11010Vo.CHECKBOX_TRUE.equals(reqossr.getSubSystemRoleSelected())).collect(Collectors.toList());
		if (requestOperatorSubSystemRoles.size() > 0) {
			//サブシステムロールでの検索条件設定あり（無しの場合は全件対象）

			// オペレーターにオペレーター_サブシステムロール割当
			List<OperatorSubSystemRoleReferenceDto> operatorSubSystemRoleReferenceDtos = operatorSubSystemRoleReferenceDto
				.stream().filter(ossrd->ossrd.getOperatorId() == operatorEntity.getOperatorId()).collect(Collectors.toList());
			if (operatorSubSystemRoleReferenceDtos.size() == 0) {
				//対象オペレーターにオペレーター_サブシステムロール割当無し
				return false;
			}

			// サブシステムロール条件選択による振り分け
			if (ConditionsSelect.ＡＮＤ.getCode().equals(request.getSubSystemRoleConditionsSelect())) {
				// AND
				for(Oa11010SearchConverterOperatorSubSystemRole requestOperatorSubSystemRole : requestOperatorSubSystemRoles) {
					if (getFilterOperatorSubSystemRoleDtoList(operatorSubSystemRoleReferenceDtos, requestOperatorSubSystemRole).size() == 0) {
						System.out.println("### OperatorId="+operatorEntity.getOperatorId());
						return false;
					}
				}
			} else if (ConditionsSelect.ＯＲ.getCode().equals(request.getSubSystemRoleConditionsSelect())) {
				// OR
				boolean orReturn = false;
				for(Oa11010SearchConverterOperatorSubSystemRole requestOperatorSubSystemRole : requestOperatorSubSystemRoles) {
					if (getFilterOperatorSubSystemRoleDtoList(operatorSubSystemRoleReferenceDto, requestOperatorSubSystemRole).size() > 0) {
						orReturn = true;
						break;
					}
				}
				if (!orReturn) {return false;}
			}
		}

		// オペレーター_サブシステムロール割当リスト
		dto.setOperatorSubSystemRoleReferenceDtoList(
			operatorSubSystemRoleReferenceDto.stream().filter(
				ossr->ossr.getOperatorId() == operatorEntity.getOperatorId()
			).collect(Collectors.toList()));

		return true;
	}

	/**
	 * サブシステムロール検索条件によるオペレーター_サブシステムロール割当の抽出を行います。
	 * @param operatorSubSystemRoleReferenceDto オペレーター_サブシステムロール割当
	 * @param requestOperatorSubSystemRole サブシステムロール検索条件
	 * @return サブシステムロール検索条件によるオペレーター_サブシステムロール割当リスト
	 */
	private List<OperatorSubSystemRoleReferenceDto> getFilterOperatorSubSystemRoleDtoList(List<OperatorSubSystemRoleReferenceDto> operatorSubSystemRoleReferenceDto,
		Oa11010SearchConverterOperatorSubSystemRole requestOperatorSubSystemRole) {

		List<OperatorSubSystemRoleReferenceDto> filters = newArrayList();
		if (ConditionsExpirationSelect.指定なし.getCode().equals(requestOperatorSubSystemRole.getExpirationSelect())) {
			// 指定無し
			filters = operatorSubSystemRoleReferenceDto.stream().filter(ossrd->
				ossrd.getSubSystemRoleCode().equals(requestOperatorSubSystemRole.getSubSystemRoleCode()))
				.collect(Collectors.toList());
		} else if (ConditionsExpirationSelect.状態指定日.getCode().equals(requestOperatorSubSystemRole.getExpirationSelect())) {
			// 状態指定日
			filters = operatorSubSystemRoleReferenceDto.stream().filter(ossrd->
				ossrd.getSubSystemRoleCode().equals(requestOperatorSubSystemRole.getSubSystemRoleCode()) &&
				ossrd.getExpirationStartDate().compareTo(requestOperatorSubSystemRole.getExpirationStatusDate()) <= 0 &&
				ossrd.getExpirationEndDate().compareTo(requestOperatorSubSystemRole.getExpirationStatusDate()) >= 0)
				.collect(Collectors.toList());
		} else if (ConditionsExpirationSelect.条件指定.getCode().equals(requestOperatorSubSystemRole.getExpirationSelect())) {
			// 条件指定
//			System.out.println("### StartDateFrom="+requestOperatorSubSystemRole.getExpirationStartDateFrom()+
//				",StartDateTo="+requestOperatorSubSystemRole.getExpirationStartDateTo()+
//				",EndDateFrom="+requestOperatorSubSystemRole.getExpirationEndDateFrom()+
//				",EndDateTo="+requestOperatorSubSystemRole.getExpirationEndDateTo());
			filters = operatorSubSystemRoleReferenceDto.stream().filter(ossrd->
				ossrd.getSubSystemRoleCode().equals(requestOperatorSubSystemRole.getSubSystemRoleCode()) &&
					(ossrd.getExpirationStartDate().compareTo(requestOperatorSubSystemRole.getExpirationStartDateFrom()) >= 0) &&
					ossrd.getExpirationStartDate().compareTo(requestOperatorSubSystemRole.getExpirationStartDateTo()) <= 0 &&
					ossrd.getExpirationEndDate().compareTo(requestOperatorSubSystemRole.getExpirationEndDateFrom()) >= 0 &&
					ossrd.getExpirationEndDate().compareTo(requestOperatorSubSystemRole.getExpirationEndDateTo()) <= 0)
				.collect(Collectors.toList());
		}
		return filters;
	}

	/**
	 * オペレーター_取引ロール割当の項目設定および条件判定を行います。
	 * @param dto オペレーターDto
	 * @param request 条件
	 * @param operatorEntity オペレーター
	 * @param operatorBizTranRoleReferenceDto オペレーター_取引ロール割当リスト
	 * @return true：オペレーター_取引ロール割当の条件で表示対象、false：オペレーター_取引ロール割当の条件で表示対象外
	 */
	private boolean setOperatorBizTranRoleInfo(OperatorReferenceDto dto,
		OperatorSearchRequest request,
		OperatorEntity operatorEntity,
		List<OperatorBizTranRoleReferenceDto> operatorBizTranRoleReferenceDto) {

		// 選択チェックした取引ロールを検索条件にする
		List<Oa11010SearchConverterOperatorBizTranRole> requestOperatorBizTranRoles =
			request.getBizTranRoleList().stream().filter(reqobtr->
				Oa11010Vo.CHECKBOX_TRUE.equals(reqobtr.getBizTranRoleSelected())).collect(Collectors.toList());
		if (requestOperatorBizTranRoles.size() > 0) {
			//取引ロールでの検索条件設定あり（無しの場合は全件対象）

			// オペレーターにオペレーター_取引ロール割当
			List<OperatorBizTranRoleReferenceDto> operatorBizTranRoleReferenceDtos = operatorBizTranRoleReferenceDto
				.stream().filter(ossrd->ossrd.getOperatorId() == operatorEntity.getOperatorId()).collect(Collectors.toList());
			if (operatorBizTranRoleReferenceDtos.size() == 0) {
				//対象オペレーターにオペレーター_取引ロール割当無し
				return false;
			}

			// 取引ロール条件選択による振り分け
			if (ConditionsSelect.ＡＮＤ.getCode().equals(request.getBizTranRoleConditionsSelect())) {
				// AND
				for(Oa11010SearchConverterOperatorBizTranRole requestOperatorBizTranRole : requestOperatorBizTranRoles) {
					if (getFilterOperatorBizTranRoleDtoList(operatorBizTranRoleReferenceDtos, requestOperatorBizTranRole).size() == 0) {
						return false;
					}
				}
			} else if (ConditionsSelect.ＯＲ.getCode().equals(request.getBizTranRoleConditionsSelect())) {
				// OR
				boolean orReturn = false;
				for(Oa11010SearchConverterOperatorBizTranRole requestOperatorBizTranRole : requestOperatorBizTranRoles) {
					if (getFilterOperatorBizTranRoleDtoList(operatorBizTranRoleReferenceDto, requestOperatorBizTranRole).size() > 0) {
						orReturn = true;
						break;
					}
				}
				if (!orReturn) {return false;}
			}
		}

		// オペレーター_取引ロール割当リスト
		dto.setOperatorBizTranRoleReferenceDtoList(
			operatorBizTranRoleReferenceDto.stream().filter(
				ossr->ossr.getOperatorId() == operatorEntity.getOperatorId()
			).collect(Collectors.toList()));

		return true;
	}

	/**
	 * 取引ロール検索条件によるオペレーター_取引ロール割当の抽出を行います。
	 * @param operatorBizTranRoleReferenceDto オペレーター_取引ロール割当
	 * @param requestOperatorBizTranRole 取引ロール検索条件
	 * @return サ取引ロール検索条件によるオペレーター_取引ロール割当リスト
	 */
	private List<OperatorBizTranRoleReferenceDto> getFilterOperatorBizTranRoleDtoList(List<OperatorBizTranRoleReferenceDto> operatorBizTranRoleReferenceDto,
		Oa11010SearchConverterOperatorBizTranRole requestOperatorBizTranRole) {

		List<OperatorBizTranRoleReferenceDto> filters = newArrayList();
		if (ConditionsExpirationSelect.指定なし.getCode().equals(requestOperatorBizTranRole.getExpirationSelect())) {
			// 指定無し
			filters = operatorBizTranRoleReferenceDto.stream().filter(obtrd->
				obtrd.getBizTranRoleCode().equals(requestOperatorBizTranRole.getBizTranRoleCode()))
				.collect(Collectors.toList());
		} else if (ConditionsExpirationSelect.状態指定日.getCode().equals(requestOperatorBizTranRole.getExpirationSelect())) {
			// 状態指定日
			System.out.println("### StatusDate="+requestOperatorBizTranRole.getExpirationStatusDate()+
				",BizTranRoleCode="+requestOperatorBizTranRole.getBizTranRoleCode());
			filters = operatorBizTranRoleReferenceDto.stream().filter(obtrd->
				obtrd.getBizTranRoleCode().equals(requestOperatorBizTranRole.getBizTranRoleCode()) &&
					obtrd.getExpirationStartDate().compareTo(requestOperatorBizTranRole.getExpirationStatusDate()) <= 0 &&
					obtrd.getExpirationEndDate().compareTo(requestOperatorBizTranRole.getExpirationStatusDate()) >= 0)
				.collect(Collectors.toList());
		} else if (ConditionsExpirationSelect.条件指定.getCode().equals(requestOperatorBizTranRole.getExpirationSelect())) {
			// 条件指定
			System.out.println("### StartDateFrom="+requestOperatorBizTranRole.getExpirationStartDateFrom()+
				",StartDateTo="+requestOperatorBizTranRole.getExpirationStartDateTo()+
				",EndDateFrom="+requestOperatorBizTranRole.getExpirationEndDateFrom()+
				",EndDateTo="+requestOperatorBizTranRole.getExpirationEndDateTo());
			filters = operatorBizTranRoleReferenceDto.stream().filter(obtrd->
				obtrd.getBizTranRoleCode().equals(requestOperatorBizTranRole.getBizTranRoleCode()) &&
					(obtrd.getExpirationStartDate().compareTo(requestOperatorBizTranRole.getExpirationStartDateFrom()) >= 0) &&
					obtrd.getExpirationStartDate().compareTo(requestOperatorBizTranRole.getExpirationStartDateTo()) <= 0 &&
					obtrd.getExpirationEndDate().compareTo(requestOperatorBizTranRole.getExpirationEndDateFrom()) >= 0 &&
					obtrd.getExpirationEndDate().compareTo(requestOperatorBizTranRole.getExpirationEndDateTo()) <= 0)
				.collect(Collectors.toList());
		}
		return filters;
	}

	/**
	 * アカウントロックの項目設定および条件判定を行います。
	 * @param dto オペレーターDto
	 * @param request 条件
	 * @param operatorEntity オペレーター
	 * @param accountLockEntitys アカウントロックリスト
	 * @return true：アカウントロックの条件で表示対象、false：アカウントロックの条件で表示対象外
	 */
	private boolean setAccountLockInfo(OperatorReferenceDto dto,
		OperatorSearchRequest request,
		OperatorEntity operatorEntity,
		List<AccountLockEntity> accountLockEntitys) {

		// アカウントロックの項目設定
		List<AccountLockEntity> asccountLocks = accountLockEntitys.stream().filter(a->a.getOperatorId().equals(operatorEntity.getOperatorId())).collect(Collectors.toList());
		if (asccountLocks.size() > 0) {
			dto.setAccountLockId(asccountLocks.get(0).getAccountLockId());
			dto.setOccurredDateTime(asccountLocks.get(0).getOccurredDateTime());
			dto.setLockStatus(asccountLocks.get(0).getLockStatus());
		}

		// アカウントロックの検索条件で抽出
		// アカウントロック　最終ロック・アンロック発生日による抽出
		if (request.getAccountLockOccurredDateFrom() != null ||
			request.getAccountLockOccurredDateTo() != null) {

			if (asccountLocks.size() == 0) {
				//continue;
				return false;
			} else {
				if (request.getAccountLockOccurredDateFrom() != null &&
					dto.getOccurredDateTime().compareTo(request.getAccountLockOccurredDateFrom().atStartOfDay()) < 0 ) {
					//continue;
					return false;
				}
				if (request.getAccountLockOccurredDateTo() != null &&
					dto.getOccurredDateTime().compareTo(request.getAccountLockOccurredDateTo().plusDays(1).atStartOfDay()) > 0 ) {
					//continue;
					return false;
				}
			}
		}
		// アカウントロック ロック状態による抽出
		Short accountLockStatusLock = 0;
		Short accountLockStatusUnlock = 0;
		if (request.getAccountLockStatusLock() != null) {accountLockStatusLock = request.getAccountLockStatusLock();}
		if (request.getAccountLockStatusUnlock() != null) {accountLockStatusUnlock = request.getAccountLockStatusUnlock();}
		if (AccountLockLockStatus.UnLock.getCode().equals(dto.getLockStatus())) {
			//アンロック
			if (!accountLockStatusLock.equals(accountLockStatusUnlock)
				&& !Oa11010Vo.CHECKBOX_TRUE.equals(accountLockStatusUnlock)) {
				return false;
			}
		} else if (AccountLockLockStatus.Lock.getCode().equals(dto.getLockStatus())) {
			//ロック
			if (!accountLockStatusLock.equals(accountLockStatusUnlock)
				&& !Oa11010Vo.CHECKBOX_TRUE.equals(accountLockStatusLock)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * パスワード履歴の項目設定および条件判定を行います。
	 * @param dto オペレーターDto
	 * @param request 条件
	 * @param operatorEntity オペレーター
	 * @param passwordHistoryEntitys パスワード履歴リスト
	 * @return true：パスワード履歴の条件で表示対象、false：パスワード履歴の条件で表示対象外
	 */
	private boolean setPasswordHistoryInfo(OperatorReferenceDto dto,
		OperatorSearchRequest request,
		OperatorEntity operatorEntity,
		List<PasswordHistoryEntity> passwordHistoryEntitys) {

		// パスワード履歴の項目設定
		List<PasswordHistoryEntity> passwordHistorys = passwordHistoryEntitys.stream().filter(a->a.getOperatorId().equals(operatorEntity.getOperatorId())).collect(Collectors.toList());
		if (passwordHistorys.size() > 0) {
			dto.setPasswordHistoryId(passwordHistorys.get(0).getPasswordHistoryId());
			dto.setChangeDateTime(passwordHistorys.get(0).getChangeDateTime());
			dto.setChangeType(passwordHistorys.get(0).getChangeType());
		}

		// パスワード履歴検索の検索条件で抽出
		// パスワード履歴　最終パスワード変更日による抽出
		if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getPasswordHistoryCheck()) &&
			request.getPasswordHistoryLastChangeDate() != null) {

			LocalDate passwodrChanheDate = LocalDate.now().minusDays(request.getPasswordHistoryLastChangeDate());
			if ("1".equals(request.getPasswordHistoryLastChangeDateStatus())) {
				// 変更した
				if (dto.getPasswordHistoryId() == null) {
					return false;
				} else {
					if (dto.getChangeDateTime().toLocalDate().compareTo(passwodrChanheDate) < 0) {
						return false;
					}
				}
			} else if ("2".equals(request.getPasswordHistoryLastChangeDateStatus())) {
				// 変更していない
				if (dto.getPasswordHistoryId() != null) {
					if (dto.getChangeDateTime().toLocalDate().compareTo(passwodrChanheDate) >= 0) {
						return false;
					}
				}
			}
		}
		// パスワード履歴　最終パスワード変更種別による抽出
		Short passwordHistoryChangeType0 = 0;
		Short passwordHistoryChangeType1 = 0;
		Short passwordHistoryChangeType2 = 0;
		Short passwordHistoryChangeType3 = 0;
		if (request.getPasswordHistoryChangeType0() != null) {passwordHistoryChangeType0 = request.getPasswordHistoryChangeType0();}
		if (request.getPasswordHistoryChangeType1() != null) {passwordHistoryChangeType1 = request.getPasswordHistoryChangeType1();}
		if (request.getPasswordHistoryChangeType2() != null) {passwordHistoryChangeType2 = request.getPasswordHistoryChangeType2();}
		if (request.getPasswordHistoryChangeType3() != null) {passwordHistoryChangeType3 = request.getPasswordHistoryChangeType2();}
		if (!passwordHistoryChangeType0.equals(passwordHistoryChangeType1) ||
			!passwordHistoryChangeType0.equals(passwordHistoryChangeType2) ||
			!passwordHistoryChangeType0.equals(passwordHistoryChangeType3)) {

			if (dto.getChangeType() == null) {
				return false;
			}
			// 最終パスワード変更種別が全て同じでない
			if (PasswordHistoryChangeType.初期.getCode().equals(dto.getChangeType()) && !Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType0)) {
				// 初期
				return false;
			} else if (PasswordHistoryChangeType.ユーザーによる変更.getCode().equals(dto.getChangeType()) && !Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType1)) {
				// ユーザーによる変更
				return false;
			} else if (PasswordHistoryChangeType.管理者によるリセット.getCode().equals(dto.getChangeType()) && !Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType2)) {
				// 管理者によるリセット
				return false;
			} else if (PasswordHistoryChangeType.機器認証パスワード.getCode().equals(dto.getChangeType()) && !Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType3)) {
				// 機器認証パスワード
				return false;
			}
		}

		return true;
	}

	/**
	 * サインイン証跡の項目設定および条件判定を行います。
	 * @param dto オペレーターDto
	 * @param request 条件
	 * @param operatorEntity オペレーター
	 * @param signInTraceEntitys サインイン証跡リスト
	 * @return true：サインイン証跡の条件で表示対象、false：サインイン証跡の条件で表示対象外
	 */
	private boolean setSignInTraceInfo(OperatorReferenceDto dto,
		OperatorSearchRequest request,
		OperatorEntity operatorEntity,
		List<SignInTraceEntity> signInTraceEntitys) {

		// サインイン証跡の項目設定
		List<SignInTraceEntity> signInTraces = signInTraceEntitys.stream().filter(a->a.getOperatorCode().equals(operatorEntity.getOperatorCode())).collect(Collectors.toList());
		if (signInTraces.size() > 0) {
			dto.setSignInTraceId(signInTraces.get(0).getSignInTraceId());
			dto.setTryDateTime(signInTraces.get(0).getTryDateTime());
			dto.setTryIpAddress(signInTraces.get(0).getTryIpAddress());
			dto.setSignInCause(signInTraces.get(0).getSignInCause());
			dto.setSignInResult(signInTraces.get(0).getSignInResult());
		}

		// サインイン証跡検索の検索条件で抽出
		// サインイン証跡　最終サインイン試行日による抽出
		if (request.getSignintraceTrydateFrom() != null ||
			request.getSignintraceTrydateTo() != null) {

			if (signInTraces.size() == 0) {
				return false;
			}
			if (request.getSignintraceTrydateFrom() != null &&
				dto.getTryDateTime().compareTo(request.getSignintraceTrydateFrom().atStartOfDay()) < 0 ) {
				return false;
			}
			if (request.getSignintraceTrydateTo() != null &&
				dto.getTryDateTime().compareTo(request.getSignintraceTrydateTo().plusDays(1).atStartOfDay()) > 0 ) {
				return false;
			}
		}
		// サインイン証跡　最終サインイン元IPアドレスによる抽出
		if (request.getSignintraceTryIpAddress() != null && request.getSignintraceTryIpAddress().length() > 0) {
			if (signInTraces.size() == 0) {
				return false;
			}
			if (!dto.getTryIpAddress().startsWith(request.getSignintraceTryIpAddress())) {
				return false;
			}
		}
		// サインイン証跡　最終サインオペレーションによる抽出
		if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getSignintraceSignIn())) {
			if (signInTraces.size() == 0) {
				return false;
			}
		}
		if (request.getSignintraceSignInResult() != null && request.getSignintraceSignInResult().length > 0) {
			if (signInTraces.size() == 0) {
				return false;
			}
			// 配列をリストに変換
			List<Short> signInResultList = Arrays.asList(request.getSignintraceSignInResult());
			if (!signInResultList.contains(dto.getSignInResult())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * サインアウト証跡の項目設定および条件判定を行います。
	 * @param dto オペレーターDto
	 * @param request 条件
	 * @param operatorEntity オペレーター
	 * @param signOutTraceEntitys サインアウト証跡リスト
	 * @return true：サインアウト証跡の条件で表示対象、false：サインアウト証跡の条件で表示対象外
	 */
	private boolean setSignOutTraceInfo(OperatorReferenceDto dto,
		OperatorSearchRequest request,
		OperatorEntity operatorEntity,
		List<SignOutTraceEntity> signOutTraceEntitys) {

		// サインアウト証跡の項目設定
		List<SignOutTraceEntity> signOutTraces = signOutTraceEntitys.stream().filter(a->a.getOperatorId().equals(operatorEntity.getOperatorId())).collect(Collectors.toList());
		if (signOutTraces.size() > 0) {
			dto.setSignOutTraceId(signOutTraces.get(0).getSignOutTraceId());
			dto.setSignOutDateTime(signOutTraces.get(0).getSignOutDateTime());
			dto.setSignOutIpAddress(signOutTraces.get(0).getSignOutIpAddress());
		}

		// サインアウト証跡検索の検索条件で抽出
		// サインアウト証跡　最終サインオペレーションによる抽出
		if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getSignintraceSignOut())) {
			if (signOutTraces.size() == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * オペレーター一覧の最終ページを取得します、
	 * @param list オペレーターリスト
	 * @return オペレーター一覧の最終ページ
	 */
	public int getMaxPage(List<OperatorReferenceDto> list) {
		return (int) Math.ceil((double)list.size() / PAGE_SIZE);
	}

	/**
	 * 該当ページのオペレーター一覧を取得します。
	 * @param list オペレーターリスト
	 * @param pageNo 対象ページ
	 * @return 該当ページのオペレーター一覧
	 */
	public List<OperatorReferenceDto> getPageList(List<OperatorReferenceDto> list, int pageNo) {
		int skip = pageNo * PAGE_SIZE - PAGE_SIZE;
		return list.stream().skip(skip).limit(10).collect(Collectors.toList());
	}
}
