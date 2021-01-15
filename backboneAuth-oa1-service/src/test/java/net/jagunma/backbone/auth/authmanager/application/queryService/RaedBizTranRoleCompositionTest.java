package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForRead;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RaedBizTranRoleCompositionTest {

    // 実行既定値
    private String subSystemCode = "AN";
    // 取引ロール－取引グループ編成データ作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheetList() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,SubSystem.販売_畜産.getDisplayName(),"ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        return list;
    }
    // 取引グループ－取引編成データ作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheetList() {
        List<BizTranGrp_BizTranSheet> list  = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(2,SubSystem.販売_畜産.getDisplayName(),"ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }

    // 取引ロール編成取得作成
    private BizTranRoleCompositionBookRepositoryForRead createBizTranRoleCompositionBookRepositoryForRead() {
        return new BizTranRoleCompositionBookRepositoryForRead() {
            @Override
            public void read(ExcelContainer excelContainer,
                List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList,
                List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList) {
                bizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheetList();
                bizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheetList();
            }
        };
    }
    // 取引ロール編成エクスポートExcel Raedサービス Request作成
    private BizTranRoleCompositionExcelReadRequest createBizTranRoleCompositionExcelReadRequest() {
        return new BizTranRoleCompositionExcelReadRequest() {
            @Override
            public String getSubSystemCode() {
                return subSystemCode;
            }
            @Override
            public ExcelContainer getBizTranRoleCompositionBook() {
                return null;
            }
        };
    }

    /**
     * {@link SearchCalendar#execute(CalendarSearchRequest,CalendarSearchResponse)}のテスト
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
        String expectedSubSystemCode = subSystemCode;
        List<BizTranRole_BizTranGrpSheet> expectedBizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheetList();
        List<BizTranGrp_BizTranSheet> expectedBizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheetList();

        // テスト対象クラス生成
        RaedBizTranRoleComposition raedBizTranRoleComposition = new RaedBizTranRoleComposition(createBizTranRoleCompositionBookRepositoryForRead());

        // 実行
        raedBizTranRoleComposition.execute(createBizTranRoleCompositionExcelReadRequest(),
            new BizTranRoleCompositionExcelReadResponse() {
                @Override
                public void setSubSystemCode(String subSystemCode) {
                    // 結果検証
                    assertThat(subSystemCode).isEqualTo(expectedSubSystemCode);
                }
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