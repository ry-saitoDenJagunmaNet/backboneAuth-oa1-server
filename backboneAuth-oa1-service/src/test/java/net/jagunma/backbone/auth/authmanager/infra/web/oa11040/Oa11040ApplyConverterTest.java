package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040ApplyConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private SubSystemRole subSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 9, 10);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 11);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 12);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 14);
    private LocalDate validThruStartDate6 = LocalDate.of(2020, 9, 16);
    private LocalDate validThruEndDate0 = LocalDate.of(2020, 9, 20);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate6 = LocalDate.of(2020, 9, 26);
    private String changeCause = "業務統括者（販売・花卉）も兼務";

    private List<Oa11040AssignRoleTableVo> createOa11040AssignRoleTableVoList() {
        List<SubSystemRole> subSystemRoleList = newArrayList(subSystemRole0, subSystemRole1, subSystemRole2, subSystemRole4, subSystemRole6);
        List<LocalDate> validThruStartDateList = newArrayList(validThruStartDate0, validThruStartDate1, validThruStartDate2, validThruStartDate4, validThruStartDate6);
        List<LocalDate> validThruEndDateList = newArrayList(validThruEndDate0, validThruEndDate1, validThruEndDate2, validThruEndDate4, validThruEndDate6);

        List<Oa11040AssignRoleTableVo> oa11040AssignRoleTableVoList = newArrayList();
        for (int i = 0; i < subSystemRoleList.size(); i++) {
            Oa11040AssignRoleTableVo oa11040AssignRoleTableVo = new Oa11040AssignRoleTableVo();
            oa11040AssignRoleTableVo.setRoleCode(subSystemRoleList.get(i).getCode());
            oa11040AssignRoleTableVo.setRoleName(subSystemRoleList.get(i).getDisplayName());
            oa11040AssignRoleTableVo.setValidThruStartDate(validThruStartDateList.get(i));
            oa11040AssignRoleTableVo.setValidThruEndDate(validThruEndDateList.get(i));
            oa11040AssignRoleTableVoList.add(oa11040AssignRoleTableVo);
        }
        return oa11040AssignRoleTableVoList;
    }

    private List<Oa11040ApplyAssignRoleConverter> createOa11040ApplyAssignRoleConverterList() {
        List<SubSystemRole> subSystemRoleList = newArrayList(subSystemRole0, subSystemRole1, subSystemRole2, subSystemRole4, subSystemRole6);
        List<LocalDate> validThruStartDateList = newArrayList(validThruStartDate0, validThruStartDate1, validThruStartDate2, validThruStartDate4, validThruStartDate6);
        List<LocalDate> validThruEndDateList = newArrayList(validThruEndDate0, validThruEndDate1, validThruEndDate2, validThruEndDate4, validThruEndDate6);

        List<Oa11040ApplyAssignRoleConverter> oa11040ApplyAssignRoleConverterList = newArrayList();
        for (int i = 0; i < subSystemRoleList.size(); i++) {
            Oa11040AssignRoleTableVo vo = new Oa11040AssignRoleTableVo();
            vo.setRoleCode(subSystemRoleList.get(i).getCode());
            vo.setRoleName(subSystemRoleList.get(i).getDisplayName());
            vo.setValidThruStartDate(validThruStartDateList.get(i));
            vo.setValidThruEndDate(validThruEndDateList.get(i));
            Oa11040ApplyAssignRoleConverter oa11040ApplyAssignRoleConverter = Oa11040ApplyAssignRoleConverter.with(vo);

            oa11040ApplyAssignRoleConverterList.add(oa11040ApplyAssignRoleConverter);
        }
        return oa11040ApplyAssignRoleConverterList;
    }

    /**
     * {@link Oa11040ApplyConverter#with(Oa11040Vo vo)}テスト
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
        Oa11040Vo vo = new Oa11040Vo();
        vo.setOperatorId(operatorId);
        vo.setOa11040AssignRoleTableVoList(createOa11040AssignRoleTableVoList());
        vo.setChangeCause(changeCause);

        // 実行
        Oa11040ApplyConverter converter = Oa11040ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11040ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(createOa11040ApplyAssignRoleConverterList());
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }

    /**
     * {@link Oa11040ApplyConverter#with(Oa11040Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （割当対象サブシステムロールなし）
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {
        // 実行値
        Oa11040Vo vo = new Oa11040Vo();
        vo.setOperatorId(operatorId);
        vo.setOa11040AssignRoleTableVoList(null);
        vo.setChangeCause(changeCause);

        // 実行
        Oa11040ApplyConverter converter = Oa11040ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11040ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(newArrayList());
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }
}