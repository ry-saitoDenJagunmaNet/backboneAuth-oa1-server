package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040ApplyConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private SubSystemRole subSystemRole1 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole3 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole5 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 1);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 2);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 9, 3);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 4);
    private LocalDate validThruStartDate5 = LocalDate.of(2020, 9, 5);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate3 = LocalDate.of(2020, 9, 23);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate5 = LocalDate.of(2020, 9, 25);
    private String changeCause = "業務統括者（販売・花卉）も兼務";

    private List<Oa11040AllocateRoleTableVo> createOa11040AllocateRoleTableVoList() {
        List<SubSystemRole> subSystemRoleList = newArrayList(subSystemRole1, subSystemRole2, subSystemRole3, subSystemRole4, subSystemRole5);
        List<LocalDate> validThruStartDateList = newArrayList(validThruStartDate1, validThruStartDate2, validThruStartDate3, validThruStartDate4, validThruStartDate5);
        List<LocalDate> validThruEndDateList = newArrayList(validThruEndDate1, validThruEndDate2, validThruEndDate3, validThruEndDate4, validThruEndDate5);

        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();
        for (int i = 0; i < subSystemRoleList.size(); i++) {
            Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo = new Oa11040AllocateRoleTableVo();
            oa11040AllocateRoleTableVo.setRoleCode(subSystemRoleList.get(i).getCode());
            oa11040AllocateRoleTableVo.setRoleName(subSystemRoleList.get(i).getDisplayName());
            oa11040AllocateRoleTableVo.setValidThruStartDate(validThruStartDateList.get(i));
            oa11040AllocateRoleTableVo.setValidThruEndDate(validThruEndDateList.get(i));
            oa11040AllocateRoleTableVoList.add(oa11040AllocateRoleTableVo);
        }
        return oa11040AllocateRoleTableVoList;
    }

    private List<Oa11040ApplyChildConverter> createOa11040ApplyChildConverterList() {
        List<SubSystemRole> subSystemRoleList = newArrayList(subSystemRole1, subSystemRole2, subSystemRole3, subSystemRole4, subSystemRole5);
        List<LocalDate> validThruStartDateList = newArrayList(validThruStartDate1, validThruStartDate2, validThruStartDate3, validThruStartDate4, validThruStartDate5);
        List<LocalDate> validThruEndDateList = newArrayList(validThruEndDate1, validThruEndDate2, validThruEndDate3, validThruEndDate4, validThruEndDate5);

        List<Oa11040ApplyChildConverter> oa11040ApplyChildConverterList = newArrayList();
        for (int i = 0; i < subSystemRoleList.size(); i++) {
            Oa11040AllocateRoleTableVo vo = new Oa11040AllocateRoleTableVo();
            vo.setRoleCode(subSystemRoleList.get(i).getCode());
            vo.setRoleName(subSystemRoleList.get(i).getDisplayName());
            vo.setValidThruStartDate(validThruStartDateList.get(i));
            vo.setValidThruEndDate(validThruEndDateList.get(i));
            Oa11040ApplyChildConverter oa11040ApplyChildConverter = Oa11040ApplyChildConverter.with(vo);

            oa11040ApplyChildConverterList.add(oa11040ApplyChildConverter);
        }
        return oa11040ApplyChildConverterList;
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
        vo.setOa11040AllocateRoleTableVoList(createOa11040AllocateRoleTableVoList());
        vo.setChangeCause(changeCause);

        // 実行
        Oa11040ApplyConverter converter = Oa11040ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11040ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAllocateSubSystemRoleList()).usingRecursiveComparison().isEqualTo(createOa11040ApplyChildConverterList());
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
        vo.setOa11040AllocateRoleTableVoList(null);
        vo.setChangeCause(changeCause);

        // 実行
        Oa11040ApplyConverter converter = Oa11040ApplyConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11040ApplyConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getAllocateSubSystemRoleList()).usingRecursiveComparison().isEqualTo(newArrayList());
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }
}