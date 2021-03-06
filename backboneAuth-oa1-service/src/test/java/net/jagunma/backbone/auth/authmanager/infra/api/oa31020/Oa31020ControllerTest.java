package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.api_queryService.SearchAccessible;
import net.jagunma.backbone.auth.authmanager.application.api_queryService.SearchAccessibleDto;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.accessibleReference.AccessibleSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.accessibleReference.AccessibleSearchResponse;
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

class Oa31020ControllerTest {

    // ???????????????
    private Long operatorId = 123456789L;
    private final List<SearchAccessibleDto> searchAccessibleList = createSearchAccessibleList();

    // ?????????
    private AccessibleSearchRequest actualAccessibleSearchRequest;

    // ??????????????????????????????
    private List<SearchAccessibleDto> createSearchAccessibleList() {
        List<SearchAccessibleDto> list = newArrayList();
        SearchAccessibleDto result = new SearchAccessibleDto();
        result.setSubSystemCode("KB");
        result.setBizTranCodeList(newArrayList("KB0000","KB0001","KB0002"));
        list.add(result);
        result = new SearchAccessibleDto();
        result.setSubSystemCode("AN");
        result.setBizTranCodeList(newArrayList("AN0001","AN1110"));
        list.add(result);
        return list;
    }

    // ??????????????????????????????
    private Oa31020Controller createOa31020Controller() {
        // ??????????????????_?????????????????????????????????????????????????????????
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return null;
            }
        };
        // ???????????????_?????????????????????????????????????????????????????????
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
        // ??????????????????_?????????????????????????????????????????????
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
        // ???????????????????????????????????????????????????
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
        // ????????????????????????????????????????????????
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
        // ??????????????????????????????????????????????????????????????????
        SystemAvailableTimeZoneRepository systemAvailableTimeZoneRepository = new SystemAvailableTimeZoneRepository() {
            @Override
            public SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria, Orders orders) {
                return null;
            }
        };
        // ??????????????????????????????????????????
        SearchAccessible SearchAccessible = new SearchAccessible(operator_BizTranRoleRepository,
            bizTranRole_BizTranGrpRepository,
            bizTranGrp_BizTranRepository,
            suspendBizTranRepository,
            calendarRepository,
            systemAvailableTimeZoneRepository) {

            public void execute(AccessibleSearchRequest request, AccessibleSearchResponse response) {
                actualAccessibleSearchRequest = request;
                // request.getOperatorId() = -1 ????????????RuntimeException ??????????????????
                if (request.getOperatorId().equals(-1L)) {
                    throw new RuntimeException();
                }
                response.setSearchAccessibleDtoList(searchAccessibleList);
            };
        };
        return new Oa31020Controller(SearchAccessible);
    }

    /**
     * {@link Oa31020Controller#Oa31020Controller(SearchAccessible)} ????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void getAccessible_test0() {

        // ??????????????????????????????
        Oa31020Controller controller = createOa31020Controller();

        // ?????????
        List<Oa31020AccessibleResult> list = newArrayList();
        for (SearchAccessibleDto dto : searchAccessibleList) {
            Oa31020AccessibleResult result = new Oa31020AccessibleResult();
            result.setSubSystemCode(dto.getSubSystemCode());
            result.setBizTranCodeList(dto.getBizTranCodeList());
            list.add(result);
        }
        ResponseEntity<List<Oa31020AccessibleResult>> expected = new ResponseEntity<>(list, HttpStatus.OK);
        AccessibleSearchRequest expectedAccessibleSearchRequest =  Oa31020Converter.with(operatorId);

        // ??????
        ResponseEntity<List<Oa31020AccessibleResult>> actual = controller.getAccessible(operatorId);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAccessibleSearchRequest).usingRecursiveComparison().isEqualTo(expectedAccessibleSearchRequest);
    }

    /**
     * {@link Oa31020Controller#Oa31020Controller(SearchAccessible)} ????????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void getAccessible_test2() {

        // ?????????
        operatorId = -1L;

        // ??????????????????????????????
        Oa31020Controller controller = createOa31020Controller();

        // ?????????
        ResponseEntity<List<Oa31020AccessibleResult>> expected = new ResponseEntity<>(newArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);

        // ??????
        ResponseEntity<List<Oa31020AccessibleResult>> result = controller.getAccessible(operatorId);

        // ????????????
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}