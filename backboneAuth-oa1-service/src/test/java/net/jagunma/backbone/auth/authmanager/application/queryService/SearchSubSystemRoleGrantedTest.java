package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.SubSystemRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchSubSystemRoleGrantedTest {

    // 実行既定値
    private final SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();
    private Long signInOperatorId = 987654L;
    private Long targetOperatorId = 123456L;

    private SubSystemRole signInSubSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole signInSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole signInSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole signInSubSystemRole3 = SubSystemRole.業務統括者_販売_花卉;
    private SubSystemRole signInSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole signInSubSystemRole5 = SubSystemRole.業務統括者_販売_麦;
    private SubSystemRole signInSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate signInValidThruStartDate0 = LocalDate.of(2020, 12, 10);
    private LocalDate signInValidThruStartDate1 = LocalDate.of(2020, 12, 11);
    private LocalDate signInValidThruStartDate2 = LocalDate.of(2020, 12, 12);
    private LocalDate signInValidThruStartDate3 = LocalDate.of(2020, 12, 13);
    private LocalDate signInValidThruStartDate4 = LocalDate.of(2020, 12, 14);
    private LocalDate signInValidThruStartDate5 = LocalDate.of(2020, 12, 15);
    private LocalDate signInValidThruStartDate6 = LocalDate.of(2020, 12, 16);
    private LocalDate signInValidThruEndDate0 = LocalDate.of(9999, 12, 20);
    private LocalDate signInValidThruEndDate1 = LocalDate.of(9999, 12, 21);
    private LocalDate signInValidThruEndDate2 = LocalDate.of(9999, 12, 22);
    private LocalDate signInValidThruEndDate3 = LocalDate.of(9999, 12, 23);
    private LocalDate signInValidThruEndDate4 = LocalDate.of(9999, 12, 24);
    private LocalDate signInValidThruEndDate5 = LocalDate.of(9999, 12, 25);
    private LocalDate signInValidThruEndDate6 = LocalDate.of(9999, 12, 26);
    private Operator_SubSystemRoles signInOperator_SubSystemRoles;

    private SubSystemRole targetSubSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole targetSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole targetSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole targetSubSystemRole3 = SubSystemRole.業務統括者_販売_花卉;
    private SubSystemRole targetSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole targetSubSystemRole5 = SubSystemRole.業務統括者_販売_麦;
    private SubSystemRole targetSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate targetValidThruStartDate0 = LocalDate.of(2020, 10, 10);
    private LocalDate targetValidThruStartDate1 = LocalDate.of(2020, 10, 11);
    private LocalDate targetValidThruStartDate2 = LocalDate.of(2020, 10, 12);
    private LocalDate targetValidThruStartDate3 = LocalDate.of(2020, 10, 13);
    private LocalDate targetValidThruStartDate4 = LocalDate.of(2020, 10, 14);
    private LocalDate targetValidThruStartDate5 = LocalDate.of(2020, 10, 15);
    private LocalDate targetValidThruStartDate6 = LocalDate.of(2020, 10, 16);
    private LocalDate targetValidThruEndDate0 = LocalDate.of(2020, 10, 20);
    private LocalDate targetValidThruEndDate1 = LocalDate.of(2020, 10, 21);
    private LocalDate targetValidThruEndDate2 = LocalDate.of(2020, 10, 22);
    private LocalDate targetValidThruEndDate3 = LocalDate.of(2020, 10, 23);
    private LocalDate targetValidThruEndDate4 = LocalDate.of(2020, 10, 24);
    private LocalDate targetValidThruEndDate5 = LocalDate.of(2020, 10, 25);
    private LocalDate targetValidThruEndDate6 = LocalDate.of(2020, 10, 26);
    private Operator_SubSystemRoles targetOperator_SubSystemRoles;

    // オペレーター履歴ヘッダー系
    private String changeCause = "業務統括者（販売・青果）に昇格";
    private OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, targetOperatorId, LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), changeCause, null, null);

    // 検証値
    private Operator_SubSystemRoleCriteria actualOperator_SubSystemRoleCriteria;
    private Long actualSignInOperatorId;
    private Long actualTargetOperatorId;
    private List<SubSystemRoleGrantedAssignRoleDto> actualAssignRoleDtoList;
    private List<SubSystemRoleGrantedAllRoleDto> actualAllRoleDtoList;
    private OperatorHistoryHeader actualOperatorHistoryHeader;

    // テスト対象クラス生成
    private SearchSubSystemRoleGranted createSearchSubSystemRoleGranted() {
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                actualOperator_SubSystemRoleCriteria = operator_SubSystemRoleCriteria;

                if (operator_SubSystemRoleCriteria.getOperatorIdCriteria().getEqualTo().equals(signInOperatorId)) {
                    return signInOperator_SubSystemRoles;
                }
                if (operator_SubSystemRoleCriteria.getOperatorIdCriteria().getEqualTo().equals(targetOperatorId)) {
                    return targetOperator_SubSystemRoles;
                }
                return null;
            }
        };
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository = new OperatorHistoryHeaderRepository() {
            @Override
            public OperatorHistoryHeader latestOneByOperatorId(Long operatorId) {
                return operatorHistoryHeader;
            }
            @Override
            public OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders) {
                return null;
            }
        };

        return new SearchSubSystemRoleGranted(operator_SubSystemRoleRepository, operatorHistoryHeaderRepository);
    }

    // Request作成
    private SubSystemRoleGrantedSearchRequest createRequest() {
        return new SubSystemRoleGrantedSearchRequest() {
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public Long getTargetOperatorId() {
                return targetOperatorId;
            }
        };
    }
    // Response作成
    private SubSystemRoleGrantedSearchResponse createResponse() {
        return new SubSystemRoleGrantedSearchResponse() {
            @Override
            public void setSignInOperatorId(Long signInOperatorId) {
               actualSignInOperatorId = signInOperatorId;
            }
            @Override
            public void setTargetOperatorId(Long targetOperatorId) {
                actualTargetOperatorId = targetOperatorId;
            }
            @Override
            public void setAssignRoleDtoList(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList) {
                actualAssignRoleDtoList = assignRoleDtoList;
            }
            @Override
            public void setAllRoleDtoList(List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList) {
                actualAllRoleDtoList = allRoleDtoList;
            }
            @Override
            public void setOperatorHistoryHeader(OperatorHistoryHeader operatorHistoryHeader) {
                actualOperatorHistoryHeader = operatorHistoryHeader;
            }
        };
    }

    // サインインオペレーター の オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createSignInOperator_SubSystemRoles(List<Integer> createNoList) {
        List<Operator_SubSystemRole> preOperator_SubSystemRoleList = newArrayList(
            Operator_SubSystemRole.createFrom(9000L, signInOperatorId, signInSubSystemRole0.getCode(), signInValidThruStartDate0, signInValidThruEndDate0, 1, null, signInSubSystemRole0),
            Operator_SubSystemRole.createFrom(9001L, signInOperatorId, signInSubSystemRole1.getCode(), signInValidThruStartDate1, signInValidThruEndDate1, 1, null, signInSubSystemRole1),
            Operator_SubSystemRole.createFrom(9002L, signInOperatorId, signInSubSystemRole2.getCode(), signInValidThruStartDate2, signInValidThruEndDate2, 1, null, signInSubSystemRole2),
            Operator_SubSystemRole.createFrom(9003L, signInOperatorId, signInSubSystemRole3.getCode(), signInValidThruStartDate3, signInValidThruEndDate3, 1, null, signInSubSystemRole3),
            Operator_SubSystemRole.createFrom(9004L, signInOperatorId, signInSubSystemRole4.getCode(), signInValidThruStartDate4, signInValidThruEndDate4, 1, null, signInSubSystemRole4),
            Operator_SubSystemRole.createFrom(9005L, signInOperatorId, signInSubSystemRole5.getCode(), signInValidThruStartDate5, signInValidThruEndDate5, 1, null, signInSubSystemRole5),
            Operator_SubSystemRole.createFrom(9006L, signInOperatorId, signInSubSystemRole6.getCode(), signInValidThruStartDate6, signInValidThruEndDate6, 1, null, signInSubSystemRole6));

        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_SubSystemRoleList.add(preOperator_SubSystemRoleList.get(createNo));
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }
    // ターゲットオペレーター の オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createTargetOperator_SubSystemRoles(List<Integer> createNoList) {
        List<Operator_SubSystemRole> preOperator_SubSystemRoleList = newArrayList(
            Operator_SubSystemRole.createFrom(1000L, targetOperatorId, targetSubSystemRole0.getCode(), targetValidThruStartDate0, targetValidThruEndDate0, 1, null, targetSubSystemRole0),
            Operator_SubSystemRole.createFrom(1001L, targetOperatorId, targetSubSystemRole1.getCode(), targetValidThruStartDate1, targetValidThruEndDate1, 1, null, targetSubSystemRole1),
            Operator_SubSystemRole.createFrom(1002L, targetOperatorId, targetSubSystemRole2.getCode(), targetValidThruStartDate2, targetValidThruEndDate2, 1, null, targetSubSystemRole2),
            Operator_SubSystemRole.createFrom(1003L, targetOperatorId, targetSubSystemRole3.getCode(), targetValidThruStartDate3, targetValidThruEndDate3, 1, null, targetSubSystemRole3),
            Operator_SubSystemRole.createFrom(1004L, targetOperatorId, targetSubSystemRole4.getCode(), targetValidThruStartDate4, targetValidThruEndDate4, 1, null, targetSubSystemRole4),
            Operator_SubSystemRole.createFrom(1005L, targetOperatorId, targetSubSystemRole5.getCode(), targetValidThruStartDate5, targetValidThruEndDate5, 1, null, targetSubSystemRole5),
            Operator_SubSystemRole.createFrom(1006L, targetOperatorId, targetSubSystemRole6.getCode(), targetValidThruStartDate6, targetValidThruEndDate6, 1, null, targetSubSystemRole6));

        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_SubSystemRoleList.add(preOperator_SubSystemRoleList.get(createNo));
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }

    /**
     * {@link SearchSubSystemRoleGranted#execute(SubSystemRoleGrantedSearchRequest request, SubSystemRoleGrantedSearchResponse response)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・responseへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1, 4));
        SubSystemRoleGrantedSearchRequest request = createRequest();
        SubSystemRoleGrantedSearchResponse response = createResponse();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        List<SubSystemRoleGrantedAllRoleDto> expectedAllRoleDtoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.getValidList()) {
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(subSystemRole.getCode(), signInOperator_SubSystemRoles));
            expectedAllRoleDtoList.add(allRoleDto);
        }

        assertThatCode(() ->
            // 実行
            searchSubSystemRoleGranted.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInOperatorId).isEqualTo(signInOperatorId);
        assertThat(actualTargetOperatorId).isEqualTo(targetOperatorId);
        assertThat(actualAssignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
        assertThat(actualAllRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAllRoleDtoList);
        assertThat(actualOperatorHistoryHeader).usingRecursiveComparison().isEqualTo(operatorHistoryHeader);
    }

    /**
     * {@link SearchSubSystemRoleGranted#searchOperator_SubSystemRoles(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Criteriaへのセット
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperator_SubSystemRoles_test() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(5, 4, 3, 2, 1, 0));

        // 期待値
        Operator_SubSystemRoleCriteria expectedCriteria = new Operator_SubSystemRoleCriteria();
        expectedCriteria.getOperatorIdCriteria().setEqualTo(signInOperatorId);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0, 1, 2, 3, 4, 5));

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = searchSubSystemRoleGranted.searchOperator_SubSystemRoles(signInOperatorId);

        // 結果検証
        assertThat(actualOperator_SubSystemRoleCriteria.toString()).isEqualTo(expectedCriteria.toString());
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }

    /**
     * {@link SearchSubSystemRoleGranted#searchOperatorHistoryHeader(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperatorHistoryHeader_test() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 期待値
        OperatorHistoryHeader expectedOperatorHistoryHeader = operatorHistoryHeader;

        // 実行
        OperatorHistoryHeader operatorHistoryHeader = searchSubSystemRoleGranted.searchOperatorHistoryHeader(targetOperatorId);

        // 結果検証
        assertThat(operatorHistoryHeader).usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeader);
    }

    /**
     * {@link SearchSubSystemRoleGranted#createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles targetOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAssignRoleDtoList_test() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1, 4));

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = searchSubSystemRoleGranted.createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link SearchSubSystemRoleGranted#createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles targetOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター の オペレーター_サブシステムロール割当 なし）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAssignRoleDtoList_test1() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList());
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1, 4));

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = searchSubSystemRoleGranted.createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link SearchSubSystemRoleGranted#createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles targetOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （ターゲットオペレーター の オペレーター_サブシステムロール割当 なし）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAssignRoleDtoList_test2() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList());

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = searchSubSystemRoleGranted.createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link SearchSubSystemRoleGranted#createAllRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAllRoleDtoList_test() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));

        // 期待値
        List<SubSystemRoleGrantedAllRoleDto> expectedAllRoleDtoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.getValidList()) {
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(subSystemRole.getCode(), signInOperator_SubSystemRoles));
            expectedAllRoleDtoList.add(allRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList = searchSubSystemRoleGranted.createAllRoleDtoList(signInOperator_SubSystemRoles);

        // 結果検証
        assertThat(allRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAllRoleDtoList);
    }

    /**
     * {@link SearchSubSystemRoleGranted#createAllRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター の オペレーター_サブシステムロール割当 なし）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAllRoleDtoList_test1() {
        // テスト対象クラス生成
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = createSearchSubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList());

        // 期待値
        List<SubSystemRoleGrantedAllRoleDto> expectedAllRoleDtoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.getValidList()) {
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(subSystemRole.getCode(), signInOperator_SubSystemRoles));
            expectedAllRoleDtoList.add(allRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList = searchSubSystemRoleGranted.createAllRoleDtoList(signInOperator_SubSystemRoles);

        // 結果検証
        assertThat(allRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAllRoleDtoList);
    }
}