package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionExcelWriteConverterTest {

    // 実行既定値
    private List<BizTranRole_BizTranGrpSheet> BizTranRole_BizTranGrpSheetList = newArrayList();
    private List<BizTranGrp_BizTranSheet> BizTranGrp_BizTranSheetList = newArrayList();

    // 取引ロール－取引グループ編成リストデータの作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheetLuist() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(1,SubSystem.販売_畜産.getDisplayName(),"ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,SubSystem.販売_畜産.getDisplayName(),"ANAG99","（畜産）維持管理責任者","ANTG10","センター維持管理グループ"));
        return list;
    }
    // 取引グループ－取引編成リストデータの作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheetList() {
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(1,SubSystem.販売_畜産.getDisplayName(),"ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,SubSystem.販売_畜産.getDisplayName(),"ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }

    /**
     * {@link Oa12010CompositionExcelWriteConverter#with(BizTranRole_BizTranGrpsSheet, BizTranGrp_BizTransSheet)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(BizTranRole_BizTranGrpSheetList);
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(BizTranGrp_BizTranSheetList);

        // 期待値
        BizTranRole_BizTranGrpsSheet expectedBizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(BizTranRole_BizTranGrpSheetList);
        BizTranGrp_BizTransSheet expectedBizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(BizTranGrp_BizTranSheetList);

        // 実行
        Oa12010CompositionExcelWriteConverter converter = Oa12010CompositionExcelWriteConverter.with(bizTranRole_BizTranGrpsSheet, bizTranGrp_BizTransSheet);

        // 結果検証
        assertTrue(converter instanceof Oa12010CompositionExcelWriteConverter);
        assertThat(converter.getBizTranRole_BizTranGrpsSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpsSheet);
        assertThat(converter.getBizTranGrp_BizTransSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTransSheet);
    }

    /**
     * {@link Oa12010CompositionExcelWriteConverter#with(BizTranRole_BizTranGrpsSheet, BizTranGrp_BizTransSheet)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        BizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheetLuist();
        BizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheetList();
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(BizTranRole_BizTranGrpSheetList);
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(BizTranGrp_BizTranSheetList);

        // 期待値
        BizTranRole_BizTranGrpsSheet expectedBizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(BizTranRole_BizTranGrpSheetList);
        BizTranGrp_BizTransSheet expectedBizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(BizTranGrp_BizTranSheetList);

        // 実行
        Oa12010CompositionExcelWriteConverter converter = Oa12010CompositionExcelWriteConverter.with(bizTranRole_BizTranGrpsSheet, bizTranGrp_BizTransSheet);

        // 結果検証
        assertTrue(converter instanceof Oa12010CompositionExcelWriteConverter);
        assertThat(converter.getBizTranRole_BizTranGrpsSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpsSheet);
        assertThat(converter.getBizTranGrp_BizTransSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTransSheet);

    }
}