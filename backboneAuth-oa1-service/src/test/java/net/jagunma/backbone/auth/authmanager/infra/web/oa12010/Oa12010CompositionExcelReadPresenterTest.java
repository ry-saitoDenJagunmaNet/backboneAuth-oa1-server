package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

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

class Oa12010CompositionExcelReadPresenterTest {

    // 実行既定値
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private BizTranRole_BizTranGrpsSheet createBizTranRole_BizTranGrpsSheet() {
        List<BizTranRole_BizTranGrpSheet> list  = newArrayList(); ;
        list.add(BizTranRole_BizTranGrpSheet.createFrom(1,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,"販売・畜産","ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG98","（畜産）センター維持管理担当者","ANTG10","センター維持管理グループ"));
        return BizTranRole_BizTranGrpsSheet.createFrom(list);
    }
    private BizTranGrp_BizTransSheet createBizTranGrp_BizTransSheet() {
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(1,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(2,"販売・畜産","ANTG02","精算取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return BizTranGrp_BizTransSheet.createFrom(list);
    }

    /**
     * {@link Oa12010CompositionExcelReadPresenter#converterTo()}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void converterTo_test0() {

        // 実行値
        Oa12010CompositionExcelReadPresenter presenter = new Oa12010CompositionExcelReadPresenter();
        presenter.setSubSystemCode(subSystemCode);
        presenter.setBizTranRole_BizTranGrpsSheet(createBizTranRole_BizTranGrpsSheet());
        presenter.setBizTranGrp_BizTransSheet(createBizTranGrp_BizTransSheet());

        // 期待値
        Oa12010CompositionImportConverter expectedConverter = Oa12010CompositionImportConverter.with(
            subSystemCode,
            createBizTranRole_BizTranGrpsSheet(),
            createBizTranGrp_BizTransSheet());

        // 実行
        Oa12010CompositionImportConverter actualConverter = presenter.converterTo();

        // 結果検証
        assertThat(actualConverter).usingRecursiveComparison().isEqualTo(expectedConverter);
    }
}