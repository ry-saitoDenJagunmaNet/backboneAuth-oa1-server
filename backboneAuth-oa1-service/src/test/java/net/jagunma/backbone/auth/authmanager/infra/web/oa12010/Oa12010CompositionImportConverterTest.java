package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.dto.Oa12010Dto;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionImportConverterTest {

    // 実行既定値
    private String subSystemCode = "";
    private BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(newArrayList());
    private BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(newArrayList());

    // 取引ロール－取引グループ編成リストデータの作成
    private BizTranRole_BizTranGrpsSheet createBizTranRole_BizTranGrpsSheetLuist() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(1,SubSystem.販売_畜産.getName(),"ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,SubSystem.販売_畜産.getName(),"ANAG99","（畜産）維持管理責任者","ANTG10","センター維持管理グループ"));
        return BizTranRole_BizTranGrpsSheet.createFrom(list);
    }
    // 取引グループ－取引編成リストデータの作成
    private BizTranGrp_BizTransSheet createBizTranGrp_BizTransSheet() {
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(1,SubSystem.販売_畜産.getName(),"ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,
            LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,SubSystem.販売_畜産.getName(),"ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return BizTranGrp_BizTransSheet.createFrom(list);
    }

    /**
     * {@link Oa12010CompositionImportConverter#with(Oa12010Dto)}テスト
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
        Oa12010Dto dto = new Oa12010Dto();
        dto.setSubSystemCode(subSystemCode);
        dto.setBizTranRole_BizTranGrpsSheet(bizTranRole_BizTranGrpsSheet);
        dto.setBizTranGrp_BizTransSheet(bizTranGrp_BizTransSheet);

        // 期待値
        String expectedSubSystemCode = subSystemCode;
        BizTranRole_BizTranGrpsSheet expectedBizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
        BizTranGrp_BizTransSheet expectedBizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;

        // 実行
        Oa12010CompositionImportConverter converter = Oa12010CompositionImportConverter.with(dto);

        // 結果検証
        assertTrue(converter instanceof Oa12010CompositionImportConverter);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
        assertThat(converter.getBizTranRole_BizTranGrpsSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpsSheet);
        assertThat(converter.getBizTranGrp_BizTransSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTransSheet);
    }

    /**
     * {@link Oa12010CompositionImportConverter#with(Oa12010Dto)}テスト
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
        Oa12010Dto dto = new Oa12010Dto();
        subSystemCode = SubSystem.販売_畜産.getCode();
        bizTranRole_BizTranGrpsSheet = createBizTranRole_BizTranGrpsSheetLuist();
        bizTranGrp_BizTransSheet = createBizTranGrp_BizTransSheet();

        dto.setSubSystemCode(subSystemCode);
        dto.setBizTranRole_BizTranGrpsSheet(bizTranRole_BizTranGrpsSheet);
        dto.setBizTranGrp_BizTransSheet(bizTranGrp_BizTransSheet);

        // 期待値
        String expectedSubSystemCode = subSystemCode;
        BizTranRole_BizTranGrpsSheet expectedBizTranRole_BizTranGrpsSheet = bizTranRole_BizTranGrpsSheet;
        BizTranGrp_BizTransSheet expectedBizTranGrp_BizTransSheet = bizTranGrp_BizTransSheet;

        // 実行
        Oa12010CompositionImportConverter converter = Oa12010CompositionImportConverter.with(dto);

        // 結果検証
        assertTrue(converter instanceof Oa12010CompositionImportConverter);
        assertThat(converter.getSubSystemCode()).isEqualTo(expectedSubSystemCode);
        assertThat(converter.getBizTranRole_BizTranGrpsSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpsSheet);
        assertThat(converter.getBizTranGrp_BizTransSheet()).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTransSheet);
    }
}