package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRolesTest {

    /**
     * {@link BizTranRoles#createFrom(Collection)}テスト
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
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KBAG01","（購買）購買業務基本","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(2L,"KBAG02","（購買）本所業務","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(3L,"KBAG03","（購買）本所管理業務","KB",1,SubSystem.購買));

        // 実行
        BizTranRoles bizTranRoles = BizTranRoles.createFrom(list);

        // 結果検証
        for(int i = 0; i < bizTranRoles.getValues().size(); i++) {
            assertThat(bizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}