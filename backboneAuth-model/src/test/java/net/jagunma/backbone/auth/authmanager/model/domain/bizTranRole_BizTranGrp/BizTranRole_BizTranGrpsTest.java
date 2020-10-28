package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpsTest {

    /**
     * {@link BizTranRole_BizTranGrps#createFrom(Collection)}テスト
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
        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(1L,10000001L,1000001L,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(10000001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTranGrp.createFrom(1000001L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(2L,10000002L,1000002L,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(10000002L,"ANAG02","（畜産）維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTranGrp.createFrom(1000002L,"ANTG02","精算取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(3L,10000003L,1000003L,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(10000003L,"ANAG98","（畜産）センター維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTranGrp.createFrom(1000003L,"ANTG10","センター維持管理グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));

        // 実行
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = BizTranRole_BizTranGrps.createFrom(list);

        // 結果検証
        for(int i = 0; i < bizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(bizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}