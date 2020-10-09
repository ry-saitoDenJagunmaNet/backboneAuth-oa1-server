package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleTest {

    /**
     * {@link BizTranRole#createFrom(Long, String, String, String, SubSystem)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行値 ＆ 期待値
        Long bizTranRoleId = 123L;
        String bizTranRoleCode = "KBAG01";
        String bizTranRoleName = "（購買）購買業務基本";
        String subSystemCode = "KB";
        SubSystem subSystem = SubSystem.購買;

        // 実行
        BizTranRole bizTranRole = BizTranRole.createFrom(
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
            subSystem);

        // 結果検証
        assertTrue(bizTranRole instanceof BizTranRole);
        assertThat(bizTranRole.getBizTranRoleId()).isEqualTo(bizTranRoleId);
        assertThat(bizTranRole.getBizTranRoleCode()).isEqualTo(bizTranRoleCode);
        assertThat(bizTranRole.getBizTranRoleName()).isEqualTo(bizTranRoleName);
        assertThat(bizTranRole.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(bizTranRole.getSubSystem()).usingRecursiveComparison().isEqualTo(subSystem);
    }
}