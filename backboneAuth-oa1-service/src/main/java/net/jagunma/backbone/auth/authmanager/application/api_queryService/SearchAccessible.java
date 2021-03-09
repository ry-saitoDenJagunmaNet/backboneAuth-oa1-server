package net.jagunma.backbone.auth.authmanager.application.api_queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.accessibleReference.AccessibleSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.accessibleReference.AccessibleSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZone;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZones;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.strings2.Strings2;
import org.springframework.stereotype.Service;

/**
 * 可能取引検索サービス
 */
@Service
public class SearchAccessible {

    // カレンダー判定する対象サブシステムリスト
    static final List<String> browseCalendarSubsystem = newArrayList(SubSystem.販売_青果.getCode(),SubSystem.販売_花卉.getCode()
        ,SubSystem.販売_米.getCode(),SubSystem.販売_麦.getCode(),SubSystem.販売_畜産.getCode());

    private final Operator_BizTranRoleRepository operator_BizTranRoleRepository;
    private final BizTranRole_BizTranGrpRepository bizTranRole_BizTranGrpRepository;
    private final BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository;
    private final SuspendBizTranRepository suspendBizTranRepository;
    private final CalendarRepository calendarRepository;
    private final SystemAvailableTimeZoneRepository systemAvailableTimeZoneRepository;

    // コンストラクタ
    public SearchAccessible(Operator_BizTranRoleRepository operator_BizTranRoleRepository,
        BizTranRole_BizTranGrpRepository bizTranRole_BizTranGrpRepository,
        BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository,
        SuspendBizTranRepository suspendBizTranRepository,
        CalendarRepository calendarRepository,
        SystemAvailableTimeZoneRepository systemAvailableTimeZoneRepository) {

        this.operator_BizTranRoleRepository = operator_BizTranRoleRepository;
        this.bizTranRole_BizTranGrpRepository = bizTranRole_BizTranGrpRepository;
        this.bizTranGrp_BizTranRepository = bizTranGrp_BizTranRepository;
        this.suspendBizTranRepository = suspendBizTranRepository;
        this.calendarRepository = calendarRepository;
        this.systemAvailableTimeZoneRepository = systemAvailableTimeZoneRepository;
    }

    /**
     * 可能取引を検索します
     *
     * @param request  可能取引検索サービス Request
     * @param response 可能取引検索サービス Response
     */
    public void execute(AccessibleSearchRequest request, AccessibleSearchResponse response) {

        LocalDate today = LocalDate.now();

        // オペレーター_取引ロール割当群検索
        Operator_BizTranRoleCriteria operator_BizTranRoleCriteria = new Operator_BizTranRoleCriteria();
        operator_BizTranRoleCriteria.getOperatorIdCriteria().setEqualTo(request.getOperatorId());
        operator_BizTranRoleCriteria.getValidThruStartDateCriteria().setLessOrEqual(today);
        operator_BizTranRoleCriteria.getValidThruEndDateCriteria().setMoreOrEqual(today);
        Operator_BizTranRoles operator_BizTranRoles = operator_BizTranRoleRepository.selectBy(operator_BizTranRoleCriteria, Orders.empty());
        if (operator_BizTranRoles.getValues().isEmpty()) {
            response.setSearchAccessibleDtoList(newArrayList());
            return;
        }

        // 取引ロール_取引グループ割当群検索
        BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria = new BizTranRole_BizTranGrpCriteria();
        bizTranRole_BizTranGrpCriteria.getBizTranRoleIdCriteria().getIncludes().addAll(
            operator_BizTranRoles.getValues().stream().map(Operator_BizTranRole::getBizTranRoleId).distinct().collect(Collectors.toList()));
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = bizTranRole_BizTranGrpRepository.selectBy(bizTranRole_BizTranGrpCriteria, Orders.empty());
        if (bizTranRole_BizTranGrps.getValues().isEmpty()) {
            response.setSearchAccessibleDtoList(newArrayList());
            return;
        }

        // 取引グループ_取引割当群検索
        BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria = new BizTranGrp_BizTranCriteria();
        bizTranGrp_BizTranCriteria.getBizTranGrpIdCriteria().getIncludes().addAll(
            bizTranRole_BizTranGrps.getValues().stream().map(BizTranRole_BizTranGrp::getBizTranGrpId).distinct().collect(Collectors.toList()));
        BizTranGrp_BizTrans bizTranGrp_BizTrans = bizTranGrp_BizTranRepository.selectBy(bizTranGrp_BizTranCriteria, Orders.empty());
        if (bizTranGrp_BizTrans.getValues().isEmpty()) {
            response.setSearchAccessibleDtoList(newArrayList());
            return;
        }

        // 一時取引抑止群検索
        SuspendBizTranCriteria suspendBizTranCriteria = new SuspendBizTranCriteria();
        suspendBizTranCriteria.getSuspendStartDateCriteria().setLessOrEqual(today);
        suspendBizTranCriteria.getSuspendEndDateCriteria().setMoreOrEqual(today);
        SuspendBizTrans suspendBizTrans = suspendBizTranRepository.selectBy(suspendBizTranCriteria, Orders.empty());

        //カレンダー群検索
        CalendarCriteria calendarCriteria = new CalendarCriteria();
        calendarCriteria.getDateCriteria().setEqualTo(today);
        Calendars calendars  = calendarRepository.selectBy(calendarCriteria, Orders.empty());

        //システム利用可能時間帯群検索
        SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria = new SystemAvailableTimeZoneCriteria();
        systemAvailableTimeZoneCriteria.getSubSystemCodeCriteria().getIncludes().addAll(
            bizTranRole_BizTranGrps.getValues().stream().map(BizTranRole_BizTranGrp::getSubSystemCode).distinct().collect(Collectors.toList()));
        systemAvailableTimeZoneCriteria.getDayOfWeekCriteria().setEqualTo((short) (today.getDayOfWeek().getValue()));
        SystemAvailableTimeZones systemAvailableTimeZones = systemAvailableTimeZoneRepository.selectBy(systemAvailableTimeZoneCriteria, Orders.empty());

        // オペレーターによる抑止確認
        if (isSuspendByOperator(suspendBizTrans, operator_BizTranRoles.getValues().get(0).getOperator())) {
            response.setSearchAccessibleDtoList(newArrayList());
            return;
        }

        // サブシステムコード毎の取引コードリストを作成
        List<SearchAccessibleDto> searchAccessibleDtoList = newArrayList();
        for(BizTranGrp_BizTran bizTranGrp_BizTran : bizTranGrp_BizTrans.getValues()) {
            // カレンダーによる抑止確認
            if (isSuspendByCalendar(calendars, bizTranGrp_BizTran.getSubSystemCode())) {
                continue;
            }
            // システム利用可能時間帯による抑止確認
            if (isSuspendBySystemAvailableTimeZone(systemAvailableTimeZones, bizTranGrp_BizTran.getSubSystemCode())) {
                continue;
            }
            // サブシステムによる抑止確認
            if (isSuspendBySubsystem(suspendBizTrans, operator_BizTranRoles.getValues().get(0).getOperator(), bizTranGrp_BizTran.getSubSystemCode())) {
                continue;
            }
            // 取引による抑止確認
            if (isSuspendByBizTran(suspendBizTrans, operator_BizTranRoles.getValues().get(0).getOperator(),
                bizTranGrp_BizTran.getBizTran().getBizTranCode())) {
                continue;
            }
            // 取引グループによる抑止確認
            if (isSuspendByBizTranGrp(suspendBizTrans, operator_BizTranRoles.getValues().get(0).getOperator(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode())) {
                continue;
            }

            SearchAccessibleDto dto = searchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (dto == null) {
                // サブシステム毎のdtoを追加
                dto = new SearchAccessibleDto();
                dto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                dto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                searchAccessibleDtoList.add(dto);
            } else {
                // すでにあるサブシステム毎のdtoに取引コードを追加（重複する取引は除外）
                if (!dto.getBizTranCodeList().contains(bizTranGrp_BizTran.getBizTran().getBizTranCode())) {
                    dto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
                }
            }
        }
        response.setSearchAccessibleDtoList(searchAccessibleDtoList);
    }

    /**
     * オペレーターによる抑止確認を行います
     *
     * @param suspendBizTrans 一時取引抑止群
     * @param operator        オペレーター
     * @return 抑止（true:抑止する）
     */
    private boolean isSuspendByOperator(SuspendBizTrans suspendBizTrans, Operator operator) {

        // ＪＡの抑止
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && Strings2.isEmpty(s.getSubSystemCode())
            && Strings2.isEmpty(s.getBizTranGrpCode())
            && Strings2.isEmpty(s.getBizTranCode()))) {

            return true;
        }

        // 店舗の抑止
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && operator.getBranchCode().equals(s.getBranchCode())
            && Strings2.isEmpty(s.getSubSystemCode())
            && Strings2.isEmpty(s.getBizTranGrpCode())
            && Strings2.isEmpty(s.getBizTranCode()))) {

            return true;
        }

        return false;
    }

    /**
     * サブシステムによる抑止確認を行います
     *
     * @param suspendBizTrans 一時取引抑止群
     * @param operator        オペレーター
     * @param subsystemCode   サブシステムコード
     * @return 抑止（true:抑止する）
     */
    private boolean isSuspendBySubsystem(SuspendBizTrans suspendBizTrans, Operator operator, String subsystemCode) {

        // サブシステムの抑止（ＪＡ、店舗指定の無し）
        if (suspendBizTrans.getValues().stream().anyMatch(s -> Strings2.isEmpty(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && subsystemCode.equals(s.getSubSystemCode()))) {

            return true;
        }

        // ＪＡ＋サブシステムの抑止（店舗指定の無し）
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && subsystemCode.equals(s.getSubSystemCode()))) {

            return true;
        }

        // ＪＡ＋店舗＋サブシステムの抑止
        if (suspendBizTrans.getValues().stream().anyMatch(s ->operator.getJaCode().equals(s.getJaCode())
            && operator.getBranchCode().equals(s.getBranchCode())
            && subsystemCode.equals(s.getSubSystemCode()))) {

            return true;
        }

        return false;
    }

    /**
     * 取引による抑止確認を行います
     *
     * @param suspendBizTrans 一時取引抑止群
     * @param operator        オペレーター
     * @param bizTranCode     取引コード
     * @return 抑止（true:抑止する）
     */
    private boolean isSuspendByBizTran(SuspendBizTrans suspendBizTrans, Operator operator, String bizTranCode) {

        // 取引の抑止（ＪＡ、店舗指定の無し）
        if (suspendBizTrans.getValues().stream().anyMatch(s -> Strings2.isEmpty(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && bizTranCode.equals(s.getBizTranCode()))) {

            return true;
        }

        // ＪＡ＋取引の抑止（店舗指定の無し）
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && bizTranCode.equals(s.getBizTranCode()))) {

            return true;
        }

        // ＪＡ＋店舗＋取引の抑止
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && operator.getBranchCode().equals(s.getBranchCode())
            && bizTranCode.equals(s.getBizTranCode()))) {

            return true;
        }

        return false;
    }

    /**
     * 取引グループによる抑止確認を行います
     *
     * @param suspendBizTrans 一時取引抑止群
     * @param operator        オペレーター
     * @param bizTranGrpCode  取引グループコード
     * @return 抑止（true:抑止する）
     */
    private boolean isSuspendByBizTranGrp(SuspendBizTrans suspendBizTrans, Operator operator, String bizTranGrpCode) {

        // 取引グループの抑止（ＪＡ、店舗指定の無し）
        if (suspendBizTrans.getValues().stream().anyMatch(s -> Strings2.isEmpty(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && bizTranGrpCode.equals(s.getBizTranGrpCode()))) {

            return true;
        }

        // ＪＡ＋取引グループの抑止（店舗指定の無し）
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && Strings2.isEmpty(s.getBranchCode())
            && bizTranGrpCode.equals(s.getBizTranGrpCode()))) {

            return true;
        }

        // ＪＡ＋店舗＋取引グループの抑止
        if (suspendBizTrans.getValues().stream().anyMatch(s -> operator.getJaCode().equals(s.getJaCode())
            && operator.getBranchCode().equals(s.getBranchCode())
            && bizTranGrpCode.equals(s.getBizTranGrpCode()))) {

            return true;
        }

        return false;
    }

    /**
     * カレンダーによる抑止確認を行います
     *
     * @param calendars     カレンダー群（対象日）
     * @param subSystemCode サブシステムコード
     * @return 抑止（true:抑止する）
     */
    private boolean isSuspendByCalendar(Calendars calendars, String subSystemCode) {

        // 対象サブシステム判定
        if (!browseCalendarSubsystem.contains(subSystemCode)) { return false; }

        // 対象日の経済システム稼働カレンダーがない場合trueを返却
        if (calendars.getValues().stream().noneMatch(c -> c.getCalendarType().is経済システム稼働カレンダー())) { return true; }

        // カレンダーが休日の場合trueを返却
        return calendars.getValues().stream().anyMatch(c -> c.getCalendarType().is経済システム稼働カレンダー() && c.getIsHoliday());
    }

    /**
     * システム利用可能時間帯による抑止確認を行います
     *
     * @param systemAvailableTimeZones システム利用可能時間帯群（対象曜日）
     * @param subSystemCode            サブシステム
     * @return 抑止（true:抑止する）
     */
    private boolean isSuspendBySystemAvailableTimeZone(SystemAvailableTimeZones systemAvailableTimeZones, String subSystemCode) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("H:mm");

        SystemAvailableTimeZone systemAvailableTimeZone = systemAvailableTimeZones.getValues().stream().filter(s -> s.getSubSystemCode().equals(subSystemCode)).findFirst().orElse(null);
        if (systemAvailableTimeZone == null) { return true; }
        if (Strings2.isEmpty(systemAvailableTimeZone.getStartTime())) { return true; }
        if (Strings2.isEmpty(systemAvailableTimeZone.getEndTime())) { return true; }

        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.parse(systemAvailableTimeZone.getStartTime(), dateTimeFormatter);
        LocalTime endTime = LocalTime.parse(systemAvailableTimeZone.getEndTime(), dateTimeFormatter);

        // 終了が"0:00"の場合 24時として終了の判定はしない
        LocalTime zeroOClock = LocalTime.parse("0:00", dateTimeFormatter);
        if (endTime.compareTo(zeroOClock) == 0) {
            return currentTime.compareTo(startTime) < 0;
        }

        return (currentTime.compareTo(startTime) < 0 || currentTime.compareTo(endTime) > 0);
    }
}