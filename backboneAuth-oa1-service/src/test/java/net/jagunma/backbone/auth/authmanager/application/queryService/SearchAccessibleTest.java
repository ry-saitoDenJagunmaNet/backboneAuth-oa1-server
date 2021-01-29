package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZone;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZones;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchAccessibleTest {

    // 実行既定値
    private Long operatorId = 18L;
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(createOperator_BizTranRoleList());
    private BizTranRole_BizTranGrps bizTranRole_BizTranGrps = BizTranRole_BizTranGrps.createFrom(createBizTranRole_BizTranGrpList());
    private BizTranGrp_BizTrans bizTranGrp_BizTrans = BizTranGrp_BizTrans.createFrom(createBizTranGrp_BizTranList());
    private SuspendBizTrans suspendBizTrans = SuspendBizTrans.createFrom(newArrayList());
    private Calendars calendars = Calendars.createFrom(createCalendarList());
    private SystemAvailableTimeZones systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(createSystemAvailableTimeZonesList());

    // 検証値
    private List<SearchAccessibleDto> actualSearchAccessibleDtoList = newArrayList();
    private Operator_BizTranRoleCriteria actualOperator_BizTranRoleCriteria;
    private Orders actualOperator_BizTranRoleRepositoryOrders;
    private BizTranRole_BizTranGrpCriteria actualBizTranRole_BizTranGrpCriteria;
    private Orders actualBizTranRole_BizTranGrpRepositoryOrders;
    private BizTranGrp_BizTranCriteria actualBizTranGrp_BizTranCriteria;
    private Orders actualBizTranGrp_BizTranRepositoryOrders;
    private SuspendBizTranCriteria actualSuspendBizTranCriteria;
    private Orders actualSuspendBizTranRepositoryOrders;
    private CalendarCriteria actualCalendarCriteria;
    private Orders actualCalendarRepositoryOrders;
    private SystemAvailableTimeZoneCriteria actualSystemAvailableTimeZoneCriteria;
    private Orders actualSystemAvailableTimeZoneRepositoryOrders;

    // オペレーター_取引ロール割当データ作成
    private List<Operator_BizTranRole> createOperator_BizTranRoleList() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(101L,18L,1l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(1l)));
        list.add(Operator_BizTranRole.createFrom(201L,18L,2l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(2l)));
        list.add(Operator_BizTranRole.createFrom(202L,18L,3l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(3l)));
        return list;
    }
    // オペレーターデータ作成
    private Operator creatrOperator(Long id) {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009",null,null,null,null,null,6L,"006",33L,"001",null,null,null));
        return list.stream().filter(b -> b.getOperatorId().equals(id)).findFirst().orElse(null);
    }
    // 取引ロール割当データ作成
    private BizTranRole createBizTranRole(Long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KBAG01","（購買）購買業務基本","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(2L,"ANAG01","（畜産）取引全般","AN",1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(3L,"ANAG02","（畜産）維持管理担当者","AN",1,SubSystem.販売_畜産));
        return list.stream().filter(b -> b.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }
    // 取引ロール_取引グループ割当データ作成
    private List<BizTranRole_BizTranGrp> createBizTranRole_BizTranGrpList() {
        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(1001L,1L,11L,SubSystem.購買.getCode(),1,createBizTranRole(1L),createBizTranGrp(11L),SubSystem.購買));
        list.add(BizTranRole_BizTranGrp.createFrom(1002L,2L,12L,SubSystem.販売_畜産.getCode(),1,createBizTranRole(2L),createBizTranGrp(12L),SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(1003L,3L,13L,SubSystem.販売_畜産.getCode(),1,createBizTranRole(3L),createBizTranGrp(13L),SubSystem.販売_畜産));
        return list;
    }
    // 取引グループデータ作成
    private BizTranGrp createBizTranGrp(Long id) {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(11L,"KBTG01","購買メニュー",SubSystem.購買.getCode(),1,SubSystem.購買));
        list.add(BizTranGrp.createFrom(12L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(13L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list.stream().filter(b -> b.getBizTranGrpId().equals(id)).findFirst().orElse(null);
    }
    // 取引グループ_取引割当データ作成
    private List<BizTranGrp_BizTran> createBizTranGrp_BizTranList() {
        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(10001L,11L,100001L,SubSystem.購買.getCode(),1,createBizTranGrp(11L),createBizTran(100001L),SubSystem.購買));
        list.add(BizTranGrp_BizTran.createFrom(10002L,12L,100002L,SubSystem.販売_畜産.getCode(),1,createBizTranGrp(12L),createBizTran(100002L),SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(10003L,13L,100003L,SubSystem.販売_畜産.getCode(),1,createBizTranGrp(13L),createBizTran(100003L),SubSystem.販売_畜産));
        return list;
    }
    // 取引データ作成
    private BizTran createBizTran(Long id) {
        return createBizTranList().stream().filter(b -> b.getBizTranId().equals(id)).findFirst().orElse(null);
    }
    // 取引リストデータ作成
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"KB0000","購買メインメニュー",false,LocalDate.of(2010,3,11),LocalDate.of(9999,12,31),SubSystem.購買.getCode(),1,SubSystem.購買));
        list.add(BizTran.createFrom(100002L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100003L,"AN1210","仕切入力",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 一時取引抑止リストデータ作成
    private List<SuspendBizTran> createSuspendBizTranList(String select) {
        List<SuspendBizTran> list = newArrayList();
        if (select.equals("JA")) {
            list.add(SuspendBizTran.createFrom(1L,"006",null,null,null,null,LocalDate.now(),LocalDate.now(),"ＪＡによる抑止",1,null,null,null,null,null));
        } else if (select.equals("Branch")) {
            list.add(SuspendBizTran.createFrom(2L,"006","001",null,null,null,LocalDate.now(),LocalDate.now(),"店舗による抑止",1,null,null,null,null,null));
        } else if (select.equals("Branch_invalid")) {
            list.add(SuspendBizTran.createFrom(3L,null,"001",null,null,null,LocalDate.now(),LocalDate.now(),"店舗による抑止（無効）",1,null,null,null,null,null));
        } else if (select.equals("SubSystem")) {
            list.add(SuspendBizTran.createFrom(4L,null,null,SubSystem.購買.getCode(),null,null,LocalDate.now(),LocalDate.now(),"サブシステムによる抑止",1,null,null,null,null,null));
        } else if (select.equals("JA+SubSystem")) {
            list.add(SuspendBizTran.createFrom(5L,"006",null,SubSystem.購買.getCode(),null,null,LocalDate.now(),LocalDate.now(),"ＪＡ＋サブシステムによる抑止",1,null,null,null,null,null));
        } else if (select.equals("JA+Branch+SubSystem")) {
            list.add(SuspendBizTran.createFrom(6L,"006","001",SubSystem.購買.getCode(),null,null,LocalDate.now(),LocalDate.now(),"ＪＡ＋店舗＋サブシステムによる抑止",1,null,null,null,null,null));
        } else if (select.equals("BizTranGrp")) {
            list.add(SuspendBizTran.createFrom(7L,null,null,null,"ANTG01",null,LocalDate.now(),LocalDate.now(),"取引グループによる抑止",1,null,null,null,null,null));
        } else if (select.equals("JA+BizTranGrp")) {
            list.add(SuspendBizTran.createFrom(8L,"006",null,null,"ANTG01",null,LocalDate.now(),LocalDate.now(),"ＪＡ＋取引グループによる抑止",1,null,null,null,null,null));
        } else if (select.equals("JA+Branch+BizTranGrp")) {
            list.add(SuspendBizTran.createFrom(9L,"006","001",null,"ANTG01",null,LocalDate.now(),LocalDate.now(),"ＪＡ＋店舗＋取引グループによる抑止",1,null,null,null,null,null));
        } else if (select.equals("BizTran")) {
            list.add(SuspendBizTran.createFrom(10L,null,null,null,null,"AN1210",LocalDate.now(),LocalDate.now(),"取引による抑止",1,null,null,null,null,null));
        } else if (select.equals("JA+BizTran")) {
            list.add(SuspendBizTran.createFrom(11L,"006",null,null,null,"AN1210",LocalDate.now(),LocalDate.now(),"ＪＡ＋取引による抑止",1,null,null,null,null,null));
        } else if (select.equals("JA+Branch+BizTran")) {
            list.add(SuspendBizTran.createFrom(12L,"006","001",null,null,"AN1210",LocalDate.now(),LocalDate.now(),"ＪＡ＋店舗＋取引による抑止",1,null,null,null,null,null));
        }
        return list;
    }
    // カレンダーリストデータ作成
    private List<Calendar> createCalendarList() {
        List<Calendar> list = newArrayList();
        list.add(Calendar.createFrom(1L,CalendarType.天文台カレンダー,LocalDate.now(),true,null,null,1));
        list.add(Calendar.createFrom(2L,CalendarType.経済システム稼働カレンダー,LocalDate.now(),false,null,null,1));
        list.add(Calendar.createFrom(3L,CalendarType.信用カレンダー,LocalDate.now(),true,null,null,1));
        list.add(Calendar.createFrom(4L,CalendarType.広域物流カレンダー,LocalDate.now(),true,null,null,1));
        return list;
    }
    // システム利用可能時間帯リストデータ作成
    private List<SystemAvailableTimeZone> createSystemAvailableTimeZonesList() {
        List<SystemAvailableTimeZone> list = newArrayList();
        String startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm"));
        String endTime = LocalTime.now().plusMinutes(2).format(DateTimeFormatter.ofPattern("H:mm"));
        list.add(SystemAvailableTimeZone.createFrom(1L,SubSystem.購買.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.購買));
        list.add(SystemAvailableTimeZone.createFrom(2L,SubSystem.販売_青果.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_青果));
        list.add(SystemAvailableTimeZone.createFrom(3L,SubSystem.販売_花卉.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_花卉));
        list.add(SystemAvailableTimeZone.createFrom(4L,SubSystem.販売_米.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_米));
        list.add(SystemAvailableTimeZone.createFrom(5L,SubSystem.販売_麦.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_麦));
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_畜産));
        return list;
    }

    // 可能取引検索サービス（テスト対象クラス）
    private SearchAccessible createSearchAccessible() {
        // オペレーター_取引ロール割当リポジトリのスタブ
        Operator_BizTranRoleRepository bperator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                actualOperator_BizTranRoleCriteria = operator_BizTranRoleCriteria;
                actualOperator_BizTranRoleRepositoryOrders = orders;
                return operator_BizTranRoles;
            }
        };
        // 取引ロール_取引グループ割当リポジトリのスタブ
        BizTranRole_BizTranGrpRepository bizTranRole_BizTranGrpRepository = new BizTranRole_BizTranGrpRepository() {
            @Override
            public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {
                actualBizTranRole_BizTranGrpCriteria = bizTranRole_BizTranGrpCriteria;
                actualBizTranRole_BizTranGrpRepositoryOrders = orders;
                return bizTranRole_BizTranGrps;
            }
            @Override
            public BizTranRole_BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
        // 取引グループ_取引割当リポジトリのスタブ
        BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository = new BizTranGrp_BizTranRepository() {
            @Override
            public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
                actualBizTranGrp_BizTranCriteria = bizTranGrp_BizTranCriteria;
                actualBizTranGrp_BizTranRepositoryOrders = orders;
                return bizTranGrp_BizTrans;
            }
            @Override
            public BizTranGrp_BizTrans selectAll(Orders orders) {
                return null;
            }
        };
        // 一時取引抑止リポジトリのスタブ
        SuspendBizTranRepository suspendBizTranRepository = new SuspendBizTranRepository() {
            @Override
            public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {
                actualSuspendBizTranCriteria = suspendBizTranCriteria;
                actualSuspendBizTranRepositoryOrders = orders;
                return suspendBizTrans;
            }
            @Override
            public SuspendBizTran findOneById(Long suspendBizTranId) {
                return null;
            }
        };
        // カレンダーリポジトリのスタブ
        CalendarRepository calendarRepository = new CalendarRepository() {
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
                actualCalendarCriteria = calendarCriteria;
                actualCalendarRepositoryOrders = orders;
                return calendars;
            }
            @Override
            public Calendar findOneById(Long calendarId) {
                return null;
            }
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria) {
                return null;
            }
            @Override
            public Calendars selectAll(Orders orders) {
                return null;
            }
            @Override
            public Calendars selectAll() {
                return null;
            }
        };
        // システム利用可能時間帯リポジトリのスタブ
        SystemAvailableTimeZoneRepository systemAvailableTimeZoneRepository = new SystemAvailableTimeZoneRepository() {
            @Override
            public SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria, Orders orders) {
                actualSystemAvailableTimeZoneCriteria = systemAvailableTimeZoneCriteria;
                actualSystemAvailableTimeZoneRepositoryOrders = orders;
                return systemAvailableTimeZones;
            }
        };
        return new SearchAccessible(bperator_BizTranRoleRepository,
            bizTranRole_BizTranGrpRepository,
            bizTranGrp_BizTranRepository,
            suspendBizTranRepository,
            calendarRepository,
            systemAvailableTimeZoneRepository);
    }
    // 可能取引検索サービス Request
    private AccessibleSearchRequest createAccessibleSearchRequest() {
        return new AccessibleSearchRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
        };
    }
    // 可能取引検索サービス Response
    private AccessibleSearchResponse createAccessibleSearchResponse() {
        return new AccessibleSearchResponse() {
            @Override
            public void setSearchAccessibleDtoList(List<SearchAccessibleDto> searchAccessibleDtoList) {
                actualSearchAccessibleDtoList = searchAccessibleDtoList;
            }
        };
    }


    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }
        LocalDate today = LocalDate.now();
        Operator_BizTranRoleCriteria expectedOperator_BizTranRoleCriteria = new Operator_BizTranRoleCriteria();
        expectedOperator_BizTranRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        expectedOperator_BizTranRoleCriteria.getValidThruStartDateCriteria().setLessOrEqual(today);
        expectedOperator_BizTranRoleCriteria.getValidThruEndDateCriteria().setMoreOrEqual(today);
        BizTranRole_BizTranGrpCriteria expectedBizTranRole_BizTranGrpCriteria  = new BizTranRole_BizTranGrpCriteria();
        expectedBizTranRole_BizTranGrpCriteria.getBizTranRoleIdCriteria().getIncludes().addAll(
            createBizTranRole_BizTranGrpList().stream().map(BizTranRole_BizTranGrp::getBizTranRoleId).collect(Collectors.toList()));
        BizTranGrp_BizTranCriteria expectedBizTranGrp_BizTranCriteria  = new BizTranGrp_BizTranCriteria();
        expectedBizTranGrp_BizTranCriteria.getBizTranGrpIdCriteria().getIncludes().addAll(
            createBizTranGrp_BizTranList().stream().map(BizTranGrp_BizTran::getBizTranGrpId).collect(Collectors.toList()));
        SuspendBizTranCriteria expectedSuspendBizTranCriteria = new SuspendBizTranCriteria();
        expectedSuspendBizTranCriteria.getSuspendStartDateCriteria().setLessOrEqual(today);
        expectedSuspendBizTranCriteria.getSuspendEndDateCriteria().setMoreOrEqual(today);
        CalendarCriteria expectedCalendarCriteria = new CalendarCriteria();
        expectedCalendarCriteria.getDateCriteria().setEqualTo(today);
        SystemAvailableTimeZoneCriteria expectedSystemAvailableTimeZoneCriteria = new SystemAvailableTimeZoneCriteria();
        expectedSystemAvailableTimeZoneCriteria.getSubSystemCodeCriteria().getIncludes().addAll(
            createBizTranRole_BizTranGrpList().stream().map(BizTranRole_BizTranGrp::getSubSystemCode).distinct().collect(Collectors.toList()));
        expectedSystemAvailableTimeZoneCriteria.getDayOfWeekCriteria().setEqualTo((short) (today.getDayOfWeek().getValue()));
        Orders expectedOrders = Orders.empty();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
        assertThat(actualOperator_BizTranRoleCriteria.toString()).isEqualTo(expectedOperator_BizTranRoleCriteria.toString());
        assertThat(actualOperator_BizTranRoleRepositoryOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualBizTranRole_BizTranGrpCriteria.toString()).isEqualTo(expectedBizTranRole_BizTranGrpCriteria.toString());
        assertThat(actualBizTranRole_BizTranGrpRepositoryOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualBizTranGrp_BizTranCriteria.toString()).isEqualTo(expectedBizTranGrp_BizTranCriteria.toString());
        assertThat(actualBizTranGrp_BizTranRepositoryOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualSuspendBizTranCriteria.toString()).isEqualTo(expectedSuspendBizTranCriteria.toString());
        assertThat(actualSuspendBizTranRepositoryOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualCalendarCriteria.toString()).isEqualTo(expectedCalendarCriteria.toString());
        assertThat(actualCalendarRepositoryOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualSystemAvailableTimeZoneCriteria.toString()).isEqualTo(expectedSystemAvailableTimeZoneCriteria.toString());
        assertThat(actualSystemAvailableTimeZoneRepositoryOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・対象のオペレーター_取引ロール割当０件
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test01() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        operator_BizTranRoles = Operator_BizTranRoles.createFrom(newArrayList());
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・対象の取引ロール_取引グループ割当０件
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test02() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        bizTranRole_BizTranGrps = BizTranRole_BizTranGrps.createFrom(newArrayList());
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・対象の取引グループ_取引割当０件
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test03() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        bizTranGrp_BizTrans = BizTranGrp_BizTrans.createFrom(newArrayList());
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡによる抑止確認
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendJa_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・店舗による抑止確認
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBranch_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("Branch"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・店舗による抑止確認（ＪＡなしの店舗指定で無効）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBranch_test01() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("Branch_invalid"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・サブシステムの抑止確認（ＪＡ、店舗指定の無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSubSystem_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("SubSystem"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendSubSystemCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getSubSystemCode).distinct().collect(Collectors.toList());
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象のサブシステムを除外
            if (suspendSubSystemCodeList.contains(bizTran.getSubSystemCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡ＋サブシステムの抑止確認（店舗指定の無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSubSystem_test01() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA+SubSystem"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendSubSystemCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getSubSystemCode).distinct().collect(Collectors.toList());
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象のサブシステムを除外
            if (suspendSubSystemCodeList.contains(bizTran.getSubSystemCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡ＋店舗＋サブシステムの抑止確認
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSubSystem_test02() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA+Branch+SubSystem"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendSubSystemCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getSubSystemCode).distinct().collect(Collectors.toList());
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象のサブシステムを除外
            if (suspendSubSystemCodeList.contains(bizTran.getSubSystemCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・取引グループの抑止確認（ＪＡ、店舗指定の無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBizTranGrp_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("BizTranGrp"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendBizTranGrpCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getBizTranGrpCode).distinct().collect(Collectors.toList());
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            // 抑止対象の取引グループを除外
            if (suspendBizTranGrpCodeList.contains(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡ＋取引グループの抑止確認（店舗指定の無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBizTranGrp_test01() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA+BizTranGrp"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendBizTranGrpCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getBizTranGrpCode).distinct().collect(Collectors.toList());
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            // 抑止対象の取引グループを除外
            if (suspendBizTranGrpCodeList.contains(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡ＋店舗＋取引グループの抑止確認（
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBizTranGrp_test02() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA+Branch+BizTranGrp"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendBizTranGrpCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getBizTranGrpCode).distinct().collect(Collectors.toList());
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            // 抑止対象の取引グループを除外
            if (suspendBizTranGrpCodeList.contains(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・取引の抑止確認（ＪＡ、店舗指定の無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBizTran_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("BizTran"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendBizTranCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getBizTranCode).distinct().collect(Collectors.toList());
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            // 抑止対象の取引を除外
            if (suspendBizTranCodeList.contains(bizTranGrp_BizTran.getBizTran().getBizTranCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡ＋取引の抑止確認（店舗指定の無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBizTran_test01() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA+BizTran"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendBizTranCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getBizTranCode).distinct().collect(Collectors.toList());
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            // 抑止対象の取引を除外
            if (suspendBizTranCodeList.contains(bizTranGrp_BizTran.getBizTran().getBizTranCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・ＪＡ＋店舗＋取引の抑止確認
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendBizTran_test02() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        suspendBizTrans = SuspendBizTrans.createFrom(createSuspendBizTranList("JA+Branch+BizTran"));
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        List<String> suspendBizTranCodeList = suspendBizTrans.getValues().stream().map(SuspendBizTran::getBizTranCode).distinct().collect(Collectors.toList());
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            // 抑止対象の取引を除外
            if (suspendBizTranCodeList.contains(bizTranGrp_BizTran.getBizTran().getBizTranCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTranGrp_BizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTranGrp_BizTran.getBizTran().getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTranGrp_BizTran.getBizTran().getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・カレンダーの抑止確認
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendCalendar_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        List<Calendar> list = newArrayList();
        list.add(Calendar.createFrom(2L,CalendarType.経済システム稼働カレンダー,LocalDate.now(),true,null,null,1));
        calendars = Calendars.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象の取引を除外
            if (SearchAccessible.browseCalendarSubsystem.contains(bizTran.getSubSystemCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（開始前）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test00() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        List<SystemAvailableTimeZone> list = newArrayList();
        String startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm"));
        String endTime = LocalTime.now().plusMinutes(2).format(DateTimeFormatter.ofPattern("H:mm"));
        String outOfRangeStartTime = LocalTime.now().plusMinutes(2).format(DateTimeFormatter.ofPattern("H:mm"));
        list.add(SystemAvailableTimeZone.createFrom(1L,SubSystem.購買.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.購買));
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),outOfRangeStartTime,endTime,1,SubSystem.販売_畜産));
        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象の取引を除外
            if (bizTran.getSubSystemCode().equals(SubSystem.販売_畜産.getCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（終了後）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test01() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        List<SystemAvailableTimeZone> list = newArrayList();
        String startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm"));
        String endTime = LocalTime.now().plusMinutes(2).format(DateTimeFormatter.ofPattern("H:mm"));
        String outOfRangeStartTime = LocalTime.now().minusMinutes(1).format(DateTimeFormatter.ofPattern("H:mm"));
        String outOfRangeEndTime = LocalTime.now().minusMinutes(1).format(DateTimeFormatter.ofPattern("H:mm"));
        list.add(SystemAvailableTimeZone.createFrom(1L,SubSystem.購買.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.購買));
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),outOfRangeStartTime,outOfRangeEndTime,1,SubSystem.販売_畜産));
        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象の取引を除外
            if (bizTran.getSubSystemCode().equals(SubSystem.販売_畜産.getCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（対象システム利用可能時間帯なし）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test02() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        // 対象を畜産だけに
        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(201L,18L,2l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(2l)));
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(202L,18L,3l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(3l)));
        operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(newArrayList());
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（開始時刻未設定）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test03() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        // 対象を畜産だけに
        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(201L,18L,2l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(2l)));
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(202L,18L,3l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(3l)));
        operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        List<SystemAvailableTimeZone> list = newArrayList();
        String endTime = LocalTime.now().plusMinutes(2).format(DateTimeFormatter.ofPattern("H:mm"));
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),"",endTime,1,SubSystem.販売_畜産));
        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（終了時刻未設定）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test04() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        // 対象を畜産だけに
        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(201L,18L,2l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(2l)));
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(202L,18L,3l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(3l)));
        operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        List<SystemAvailableTimeZone> list = newArrayList();
        String startTime = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm"));
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,"",1,SubSystem.販売_畜産));
        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（開始時刻、終了時刻に"0:00"を設定）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test05() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        // 対象を畜産だけに
        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(201L,18L,2l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(2l)));
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(202L,18L,3l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(3l)));
        operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        List<SystemAvailableTimeZone> list = newArrayList();
        String startTime = "0:00";
        String endTime = "0:00";
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_畜産));
        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            // 抑止対象の取引を除外
            if (!bizTran.getSubSystemCode().equals(SubSystem.販売_畜産.getCode())) { continue; }
            SearchAccessibleDto expectedSearchAccessibleDto = expectedSearchAccessibleDtoList.stream().filter(s -> s.getSubSystemCode().equals(bizTran.getSubSystemCode())).findFirst().orElse(null);
            if (expectedSearchAccessibleDto == null) {
                expectedSearchAccessibleDto = new SearchAccessibleDto();
                expectedSearchAccessibleDto.setSubSystemCode(bizTran.getSubSystemCode());
                expectedSearchAccessibleDto.setBizTranCodeList(newArrayList(bizTran.getBizTranCode()));
                expectedSearchAccessibleDtoList.add(expectedSearchAccessibleDto);
            } else {
                expectedSearchAccessibleDto.getBizTranCodeList().add(bizTran.getBizTranCode());
            }
        }

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }

    /**
     * {@link SearchAccessible#execute(AccessibleSearchRequest, AccessibleSearchResponse)}テスト
     *  ●パターン
     *    正常
     *    ・システム利用可能時間帯の抑止確認（開始時刻が時間前、終了時刻に"0:00"を設定）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・可能取引リスト
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isSuspendSystemAvailableTimeZone_test06() {

        // テスト対象クラス生成
        SearchAccessible searchAccessible = createSearchAccessible();

        // 実行値
        // 対象を畜産だけに
        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(201L,18L,2l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(2l)));
        operator_BizTranRoleList.add(Operator_BizTranRole.createFrom(202L,18L,3l,LocalDate.of(2021,1,1),LocalDate.of(9999,12,31),1,creatrOperator(18L),createBizTranRole(3l)));
        operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        List<SystemAvailableTimeZone> list = newArrayList();
        String startTime = LocalTime.now().plusMinutes(1).format(DateTimeFormatter.ofPattern("H:mm"));
        String endTime = "0:00";
        list.add(SystemAvailableTimeZone.createFrom(6L,SubSystem.販売_畜産.getCode(),(short) LocalDate.now().getDayOfWeek().getValue(),startTime,endTime,1,SubSystem.販売_畜産));
        systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);
        AccessibleSearchRequest request = createAccessibleSearchRequest();
        AccessibleSearchResponse response = createAccessibleSearchResponse();

        // 期待値
        List<SearchAccessibleDto> expectedSearchAccessibleDtoList = newArrayList();

        assertThatCode(() ->
            // 実行
            searchAccessible.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSearchAccessibleDtoList).usingRecursiveComparison().isEqualTo(expectedSearchAccessibleDtoList);
    }
}