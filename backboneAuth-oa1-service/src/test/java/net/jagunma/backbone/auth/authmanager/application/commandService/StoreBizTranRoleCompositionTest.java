package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleCompositionRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreBizTranRoleCompositionTest {

    // 実行既定値
    private final String subSystemCode = "AN";
    // 取引ロール－取引グループ編成データ
    private List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheetList();
    // 取引グループ－取引編成データ
    private final List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheetList();


    // 取引ロール－取引グループ編成データ作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheetList() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2, SubSystem.販売_畜産.getName(),"ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3, SubSystem.販売_畜産.getName(),"ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(4, SubSystem.販売_畜産.getName(),"ANAG02","（畜産）維持管理担当者","ANTG02","精算取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(5, SubSystem.販売_畜産.getName(),"ANAG98","（畜産）センター維持管理担当者","ANTG10","センター維持管理グループ"));
        return list;
    }
    // 取引グループ－取引編成データ作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheetList() {
        List<BizTranGrp_BizTranSheet> list  = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(2,SubSystem.販売_畜産.getName(),"ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,SubSystem.販売_畜産.getName(),"ANTG01","データ入力取引グループ","AN1510","精算取消",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(4,SubSystem.販売_畜産.getName(),"ANTG02","精算取引グループ","AN1510","精算取消",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(5,SubSystem.販売_畜産.getName(),"ANTG10","センター維持管理グループ","AN3500","データ提供メニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }
    // 取引ロール編成登録の作成
    private BizTranRoleCompositionRepositoryForStore createBizTranRoleCompositionRepositoryForStore() {
        return new BizTranRoleCompositionRepositoryForStore() {
            @Override
            public void store(BizTranRoleComposition bizTranRoleComposition) {
            }
        };
    }
    // 取引ロール編成インポート＆エクスポート Excel 登録サービス Requestの作成
    private BizTranRoleCompositionImportRequest createBizTranRoleCompositionImportRequest() {
        return new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return subSystemCode;
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                return BizTranRole_BizTranGrpsSheet.createFrom(bizTranRole_BizTranGrpSheetList);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                return BizTranGrp_BizTransSheet.createFrom(bizTranGrp_BizTranSheetList);
            }
        };
    }
    // 取引ロール編成インポート＆エクスポート Excel 登録サービス Responseの作成
    private BizTranRoleCompositionImportResponse createBizTranRoleCompositionImportResponse() {
        return new BizTranRoleCompositionImportResponse() {
            @Override
            public void setMessageDtoList(List<MessageDto> messageDtoList) {
            }
        };
    }

    /**
     * {@link  StoreBizTranRoleComposition#execute(BizTranRoleCompositionImportRequest,BizTranRoleCompositionImportResponse)}のテスト
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
        List<MessageDto> expectedMessageDtoList = newArrayList();

        // テスト対象クラス生成
        StoreBizTranRoleComposition storeBizTranRoleComposition = new StoreBizTranRoleComposition(
            createBizTranRoleCompositionRepositoryForStore());

        // 実行値
        BizTranRoleCompositionImportRequest request = createBizTranRoleCompositionImportRequest();
        BizTranRoleCompositionImportResponse response = createBizTranRoleCompositionImportResponse();

        assertThatCode(() ->
            // 実行
            storeBizTranRoleComposition.execute(request, response))
                .doesNotThrowAnyException();
    }
}