package net.jagunma.backbone.auth.authmanager.infra.api.oa13020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessible;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessibleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZones;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class Oa13020ControllerTest {

    // 実行既定値
    private Long operatorId = 123456789L;
    private final Map<String, List<String>> searchAccessibleMap = createSearchAccessibleMap();

    // 兼用値
    private AccessibleSearchRequest actualAccessibleSearchRequest;

    // 可能取引データの作成
    private Map<String, List<String>> createSearchAccessibleMap() {
        Map<String, List<String>> searchAccessibleMap = new HashMap<>();
        searchAccessibleMap.put("KB", newArrayList("KB0000","KB0001","KB0002"));
        searchAccessibleMap.put("AN", newArrayList("AN0001","AN1110"));
        return searchAccessibleMap;
    }

    // テスト対象クラス
    private Oa13020Controller createOa13020Controller() {
        // オペレーター_取引ロール割当群検索リポジトリのスタブ
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return null;
            }
        };
        // 取引ロール_取引グループ割当検索リポジトリのスタブ
        BizTranRole_BizTranGrpRepository bizTranRole_BizTranGrpRepository = new BizTranRole_BizTranGrpRepository() {
            @Override
            public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {
                return null;
            }
            @Override
            public BizTranRole_BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
        // 取引グループ_取引割当検索リポジトリのスタブ
        BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository = new BizTranGrp_BizTranRepository() {
            @Override
            public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
                return null;
            }
            @Override
            public BizTranGrp_BizTrans selectAll(Orders orders) {
                return null;
            }
        };
        // 一時取引抑止検索リポジトリのスタブ
        SuspendBizTranRepository suspendBizTranRepository = new SuspendBizTranRepository() {
            @Override
            public SuspendBizTran findOneById(Long suspendBizTranId) {
                return null;
            }
            @Override
            public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria,Orders orders) {
                return null;
            }
        };
        // カレンダー検索リポジトリのスタブ
        CalendarRepository calendarRepository = new CalendarRepository() {
            @Override
            public Calendar findOneById(Long calendarId) {
                return null;
            }
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
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
        // システム利用可能時間帯検索リポジトリのスタブ
        SystemAvailableTimeZoneRepository systemAvailableTimeZoneRepository = new SystemAvailableTimeZoneRepository() {
            @Override
            public SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria, Orders orders) {
                return null;
            }
        };
        // 可能取引検索サービスのスタブ
        SearchAccessible SearchAccessible = new SearchAccessible(operator_BizTranRoleRepository,
            bizTranRole_BizTranGrpRepository,
            bizTranGrp_BizTranRepository,
            suspendBizTranRepository,
            calendarRepository,
            systemAvailableTimeZoneRepository) {

            public void execute(AccessibleSearchRequest request, AccessibleSearchResponse response) {
                actualAccessibleSearchRequest = request;
                // request.getOperatorId() = -1 の場合：RuntimeException を発生させる
                if (request.getOperatorId().equals(-1L)) {
                    throw new RuntimeException();
                }
                List<SearchAccessibleDto> list = newArrayList();
                for (String key : searchAccessibleMap.keySet()) {
                    SearchAccessibleDto dto = new SearchAccessibleDto();
                    dto.setSubSystemCode(key);
                    dto.setBizTranCodeList(searchAccessibleMap.get(key));
                    list.add(dto);
                }
                response.setSearchAccessibleDtoList(list);
            };
        };
        return new Oa13020Controller(SearchAccessible);
    }

    /**
     * {@link Oa13020Controller#Oa13020Controller(SearchAccessible)} のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getAccessible_test0() {

        // テスト対象クラス生成
        Oa13020Controller controller = createOa13020Controller();

        // 期待値
        Map<String, List<String>> map = new HashMap<>();
        for (String key : searchAccessibleMap.keySet()) {
            map.put(key, searchAccessibleMap.get(key));
        }
        ResponseEntity<Map<String, List<String>>> expected = new ResponseEntity<>(map, HttpStatus.OK);
        AccessibleSearchRequest expectedAccessibleSearchRequest =  Oa13020Converter.with(operatorId);

        // 実行
        ResponseEntity<Map<String, List<String>>> result = controller.getAccessible(operatorId);

        // 結果検証
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAccessibleSearchRequest).usingRecursiveComparison().isEqualTo(expectedAccessibleSearchRequest);
    }

    /**
     * {@link Oa13020Controller#Oa13020Controller(SearchAccessible)} のテスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getAccessible_test2() {

        // 実行値
        operatorId = -1L;

        // テスト対象クラス生成
        Oa13020Controller controller = createOa13020Controller();

        // 期待値
        ResponseEntity<Map<String, List<String>>> expected = new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);

        // 実行
        ResponseEntity<Map<String, List<String>>> result = controller.getAccessible(operatorId);

        // 結果検証
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}