package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11050ApplyConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private Long bizTranRoleId0 = 401L;
    private Long bizTranRoleId1 = 402L;
    private Long bizTranRoleId2 = 403L;
    private Long bizTranRoleId3 = 404L;
    private String bizTranRoleCode0 = "KBAG01";
    private String bizTranRoleCode1 = "YSAG10";
    private String bizTranRoleCode2 = "HKAG10";
    private String bizTranRoleCode3 = "ANAG01";
    private String bizTranRoleName0 = "（購買）購買業務基本";
    private String bizTranRoleName1 = "（青果）管理者";
    private String bizTranRoleName2 = "（米）ＪＡ取引全般";
    private String bizTranRoleName3 = "（畜産）取引全般";
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate validThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate validThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate validThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate validThruEndDate3 = LocalDate.of(9999, 1, 24);
    private String changeCause = "業務統括者（販売・花卉）も兼務";

    private List<Oa11050AssignRoleTableVo> createOa11050AssignRoleTableVoList() {
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        createOa11050AssignRole(assignRoleTableVoList, newArrayList());
        return assignRoleTableVoList;
    }
    private List<Oa11050ApplyAssignRoleConverter> createOa11050ApplyAssignRoleConverterList() {
        List<Oa11050ApplyAssignRoleConverter> applyAssignRoleConverterList = newArrayList();
        createOa11050AssignRole(newArrayList(), applyAssignRoleConverterList);
        return applyAssignRoleConverterList;
    }
    private void createOa11050AssignRole(List<Oa11050AssignRoleTableVo> assignRoleTableVoList, List<Oa11050ApplyAssignRoleConverter> applyAssignRoleConverterList) {
        List<Long> bizTranRoleIdList = newArrayList(bizTranRoleId0, bizTranRoleId1, bizTranRoleId2, bizTranRoleId3);
        List<String> bizTranRoleCodeList = newArrayList(bizTranRoleCode0, bizTranRoleCode1, bizTranRoleCode2, bizTranRoleCode3);
        List<String> bizTranRoleNameList = newArrayList(bizTranRoleName0, bizTranRoleName1, bizTranRoleName2, bizTranRoleName3);
        List<LocalDate> validThruStartDateList = newArrayList(validThruStartDate0, validThruStartDate1, validThruStartDate2, validThruStartDate3);
        List<LocalDate> validThruEndDateList = newArrayList(validThruEndDate0, validThruEndDate1, validThruEndDate2, validThruEndDate3);

        for (int i = 0; i < bizTranRoleIdList.size(); i++) {
            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleId(bizTranRoleIdList.get(i));
            assignRoleTableVo.setRoleCode(bizTranRoleCodeList.get(i));
            assignRoleTableVo.setRoleName(bizTranRoleNameList.get(i));
            assignRoleTableVo.setValidThruStartDate(validThruStartDateList.get(i));
            assignRoleTableVo.setValidThruEndDate(validThruEndDateList.get(i));
            assignRoleTableVo.setIsModifiable(((i+2) % 2 == 0)? true : false);
            assignRoleTableVoList.add(assignRoleTableVo);

            Oa11050ApplyAssignRoleConverter applyAssignRoleConverter = Oa11050ApplyAssignRoleConverter.with(assignRoleTableVo);
            applyAssignRoleConverterList.add(applyAssignRoleConverter);
        }
    }

    /**
     * {@link Oa11050ApplyConverter#with(Oa11050Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        vo.setOperatorId(operatorId);
        vo.setAssignRoleTableVoList(createOa11050AssignRoleTableVoList());
        vo.setChangeCause(changeCause);

        // 実行
        Oa11050ApplyConverter converter = Oa11050ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11050ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(createOa11050ApplyAssignRoleConverterList());
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }

    /**
     * {@link Oa11050ApplyConverter#with(Oa11050Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （アサイン取引ロール が ０件）
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        vo.setOperatorId(operatorId);
        vo.setAssignRoleTableVoList(newArrayList());
        vo.setChangeCause(changeCause);

        // 実行
        Oa11050ApplyConverter converter = Oa11050ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11050ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(newArrayList());
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }

    /**
     * {@link Oa11050ApplyConverter#with(Oa11050Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （アサイン取引ロール が null）
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test2() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        vo.setOperatorId(operatorId);
        vo.setAssignRoleTableVoList(null);
        vo.setChangeCause(changeCause);

        // 実行
        Oa11050ApplyConverter converter = Oa11050ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11050ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(newArrayList());
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }
}