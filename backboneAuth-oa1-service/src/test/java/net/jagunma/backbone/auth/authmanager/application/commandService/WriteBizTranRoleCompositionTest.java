package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand.BizTranRoleCompositionExcelWriteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand.BizTranRoleCompositionExcelWriteResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForWrite;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WriteBizTranRoleCompositionTest {

    // 実行既定値
    // ByteArrayOutputStreamの作成
    private ByteArrayOutputStream createByteArrayOutputStream() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write( 0x01 );
        out.write( 0x02 );
        out.write( 0x03 );
        return out;
    }
    // 取引ロール編成書き出し作成
    private BizTranRoleCompositionBookRepositoryForWrite createBizTranRoleCompositionBookRepositoryForWrite() {
        return new BizTranRoleCompositionBookRepositoryForWrite() {
            @Override
            public ExcelContainer create(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
                BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {
                return ExcelContainer.createFrom(createByteArrayOutputStream());
            }
        };
    }
    // 取引ロール編成インポート＆エクスポート Excel Writeサービス Request作成
    private BizTranRoleCompositionExcelWriteRequest createBizTranRoleCompositionExcelWriteRequest() {
        return new BizTranRoleCompositionExcelWriteRequest() {
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                return null;
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                return null;
            }
        };
    }

    /**
     * {@link  WriteBizTranRoleComposition#execute(BizTranRoleCompositionExcelWriteRequest,BizTranRoleCompositionExcelWriteResponse)}のテスト
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
        ExcelContainer expectedExcelContainer = ExcelContainer.createFrom(createByteArrayOutputStream());

        // テスト対象クラス生成
        WriteBizTranRoleComposition writeBizTranRoleComposition = new WriteBizTranRoleComposition(createBizTranRoleCompositionBookRepositoryForWrite());

        // 実行
        writeBizTranRoleComposition.execute(createBizTranRoleCompositionExcelWriteRequest(),
            new BizTranRoleCompositionExcelWriteResponse() {
                @Override
                public void setExcelContainer(ExcelContainer excelContainer) {
                    // 結果検証
                    assertThat(excelContainer.getExcelOut()).usingRecursiveComparison().isEqualTo(expectedExcelContainer.getExcelOut());
                }
            });
    }
}