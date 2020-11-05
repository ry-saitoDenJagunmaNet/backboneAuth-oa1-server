package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchBizTranRoleCompositionTest {

    // 実行既定値
    //  取引ロールリストデータ作成
    private List<BizTranRole> createBizTranRoleList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    //  取引グループリストデータ作成
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(101L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    //  取引リストデータ作成
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(1L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引ロール_取引グループ割当リストデータ作成
    private List<BizTranRole_BizTranGrp> createBizTranRole_BizTranGrpList() {
        List<BizTranRole> bizTranRoleList = createBizTranRoleList();
        List<BizTranGrp> bizTranGrpList = createBizTranGrpList();

        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(10001L,1001L,101L,SubSystem.販売_畜産.getCode(),1,
            bizTranRoleList.stream().filter(b->b.getBizTranRoleId().equals(1001L)).findFirst().orElse(null),
            bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(101L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        return list;
    }
    // 取引グループ_取引割当リストデータ作成
    private List<BizTranGrp_BizTran> createBizTranGrp_BizTranList() {
        List<BizTranGrp> bizTranGrpList = createBizTranGrpList();
        List<BizTran> bizTranList = createBizTranList();

        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(10001L,101L,1L,SubSystem.販売_畜産.getCode(),1,
            bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(101L)).findFirst().orElse(null),
            bizTranList.stream().filter(b->b.getBizTranId().equals(1L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        return list;
    }

    // 取引ロール_取引グループ割当群検索作成
    private BizTranRole_BizTranGrpsRepository createBizTranRole_BizTranGrpsRepository() {
        return new BizTranRole_BizTranGrpsRepository() {
            @Override
            public BizTranRole_BizTranGrps selectBy(BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria, Orders orders) {
                return BizTranRole_BizTranGrps.createFrom(createBizTranRole_BizTranGrpList());
            }
            @Override
            public BizTranRole_BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
    }
    // 取引グループ_取引割当群検索作成
    private BizTranGrp_BizTransRepository createBizTranGrp_BizTransRepository() {
        return new BizTranGrp_BizTransRepository() {
            @Override
            public BizTranGrp_BizTrans selectBy(BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria, Orders orders) {
                return BizTranGrp_BizTrans.createFrom(createBizTranGrp_BizTranList());
            }
            @Override
            public BizTranGrp_BizTrans selectAll(Orders orders) {
                return null;
            }
        };
    }
    // 取引ロール編成インポート＆エクスポート エクスポート検索 Requestデータ作成
    private BizTranRoleCompositionExportRequest createBizTranRoleCompositionExportRequest() {
        return new BizTranRoleCompositionExportRequest() {
            @Override
            public String getSubSystemCode() {
                return null;
            }
        };
    }

    /**
     * {@link SearchBizTranRoleComposition#execute(BizTranRoleCompositionExportRequest,BizTranRoleCompositionExportResponse)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // 期待値
        List<BizTranRole_BizTranGrpSheet> expectedBizTranRole_BizTranGrpSheetList = newArrayList();
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : createBizTranRole_BizTranGrpList()) {
            expectedBizTranRole_BizTranGrpSheetList.add(BizTranRole_BizTranGrpSheet.createFrom(
                expectedBizTranRole_BizTranGrpSheetList.size(),
                bizTranRole_BizTranGrp.getSubSystem().getName(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleName(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpName()));
        }
        List<BizTranGrp_BizTranSheet> expectedBizTranGrp_BizTranSheetList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            expectedBizTranGrp_BizTranSheetList.add(BizTranGrp_BizTranSheet.createFrom(
                expectedBizTranGrp_BizTranSheetList.size(),
                bizTranGrp_BizTran.getSubSystem().getName(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpName(),
                bizTranGrp_BizTran.getBizTran().getBizTranCode(),
                bizTranGrp_BizTran.getBizTran().getBizTranName(),
                bizTranGrp_BizTran.getBizTran().getIsCenterBizTran(),
                bizTranGrp_BizTran.getBizTran().getExpirationStartDate(),
                bizTranGrp_BizTran.getBizTran().getExpirationEndDate()));
        }

        // テスト対象クラス生成
        SearchBizTranRoleComposition searchBizTranRoleComposition = new SearchBizTranRoleComposition(
            createBizTranRole_BizTranGrpsRepository(),
            createBizTranGrp_BizTransRepository());

        // 実行
        searchBizTranRoleComposition.execute(createBizTranRoleCompositionExportRequest(),
            new BizTranRoleCompositionExportResponse() {
                @Override
                public void setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {
                    // 結果検証
                    for(int i = 0; i < bizTranRole_BizTranGrpsSheet.getValues().size(); i++) {
                        assertThat(bizTranRole_BizTranGrpsSheet.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpSheetList.get(i));
                    }
                }
                @Override
                public void setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
                    // 結果検証
                    for(int i = 0; i < bizTranGrp_BizTransSheet.getValues().size(); i++) {
                        assertThat(bizTranGrp_BizTransSheet.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranSheetList.get(i));
                    }
                }
            });
    }
}