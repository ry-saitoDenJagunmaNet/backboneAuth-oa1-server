package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11050ApplyAssignRoleConverterTest {

    // 実行既定値
    private Long bizTranRoleId = 401L;
    private String bizTranRoleCode = "KBAG01";
    private String bizTranRoleName = "（購買）購買業務基本";
    private String subSystemCode = "KB";
    private SubSystem subSystem = SubSystem.codeOf(subSystemCode) ;
    private LocalDate validThruStartDate = LocalDate.of(2020, 1, 1);
    private LocalDate validThruEndDate = LocalDate.of(9999, 1, 21);
    private BizTranRole bizTranRole = BizTranRole.createFrom(401L, bizTranRoleCode, bizTranRoleName, subSystemCode, null, subSystem);

    /**
     * {@link Oa11050ApplyAssignRoleConverter#with(Oa11050AssignRoleTableVo vo)}テスト
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
        Oa11050AssignRoleTableVo vo = new Oa11050AssignRoleTableVo();
        vo.setRoleId(bizTranRoleId);
        vo.setRoleCode(bizTranRoleCode);
        vo.setRoleName(bizTranRoleName);
        vo.setSubSystemCode(subSystemCode);
        vo.setValidThruStartDate(validThruStartDate);
        vo.setValidThruEndDate(validThruEndDate);

        // 実行
        Oa11050ApplyAssignRoleConverter converter = Oa11050ApplyAssignRoleConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11050ApplyAssignRoleConverter);
        assertThat(converter.getBizTranRole()).usingRecursiveComparison().isEqualTo(bizTranRole);
        assertThat(converter.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(converter.getValidThruEndDate()).isEqualTo(validThruEndDate);
    }
}