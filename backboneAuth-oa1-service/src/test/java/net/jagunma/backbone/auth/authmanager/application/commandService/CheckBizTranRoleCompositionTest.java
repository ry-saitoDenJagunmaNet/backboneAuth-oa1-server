package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CheckBizTranRoleCompositionTest {

    // 実行既定値
    private final String subSystemCode = "AN";
    // 取引ロール－取引グループ編成データ
    private List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheetList();
    // 取引グループ－取引編成データ
    private final List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheetList();
    // response の MessageDtoリスト
    private List<MessageDto> actualMessageDtoList;


    // 取引ロール－取引グループ編成データ作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheetList() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2, SubSystem.販売_畜産.getDisplayName(),"ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3, SubSystem.販売_畜産.getDisplayName(),"ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(4, SubSystem.販売_畜産.getDisplayName(),"ANAG02","（畜産）維持管理担当者","ANTG02","精算取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(5, SubSystem.販売_畜産.getDisplayName(),"ANAG98","（畜産）センター維持管理担当者","ANTG10","センター維持管理グループ"));
        return list;
    }
    // 取引グループ－取引編成データ作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheetList() {
        List<BizTranGrp_BizTranSheet> list  = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(2,SubSystem.販売_畜産.getDisplayName(),"ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,
            LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,SubSystem.販売_畜産.getDisplayName(),"ANTG01","データ入力取引グループ","AN1510","精算取消",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(4,SubSystem.販売_畜産.getDisplayName(),"ANTG02","精算取引グループ","AN1510","精算取消",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(5,SubSystem.販売_畜産.getDisplayName(),"ANTG10","センター維持管理グループ","AN3500","データ提供メニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }
    // 取引ロールデータ作成
    private List<BizTranRole> createBizTranRoleList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(48L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(49L,"ANAG02","（畜産）維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(51L,"ANAG98","（畜産）センター維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(52L,"ANAG99","削除対象",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // オペレーター_取引ロール割当データ作成
    private List<Operator_BizTranRole> createOperator_BizTranRoleList() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(100001L,18L,48L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),1,
            createOperatorList().stream().filter(o->o.getOperatorId().equals(18L)).findFirst().orElse(null),
            createBizTranRoleList().stream().filter(b->b.getBizTranRoleId().equals(48L)).findFirst().orElse(null)));
        list.add(Operator_BizTranRole.createFrom(100002L,19L,49L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),1,
            createOperatorList().stream().filter(o->o.getOperatorId().equals(19L)).findFirst().orElse(null),
            createBizTranRoleList().stream().filter(b->b.getBizTranRoleId().equals(49L)).findFirst().orElse(null)));
        list.add(Operator_BizTranRole.createFrom(100003L,20L,51L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),1,
            createOperatorList().stream().filter(o->o.getOperatorId().equals(20L)).findFirst().orElse(null),
            createBizTranRoleList().stream().filter(b->b.getBizTranRoleId().equals(51L)).findFirst().orElse(null)));
        list.add(Operator_BizTranRole.createFrom(100004L,20L,52L,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31),1,
            createOperatorList().stream().filter(o->o.getOperatorId().equals(20L)).findFirst().orElse(null),
            createBizTranRoleList().stream().filter(b->b.getBizTranRoleId().equals(52L)).findFirst().orElse(null)));
        return list;
    }
    // オペレーターデータ作成
    private List<Operator> createOperatorList() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        return list;
    }
    // 取引ロール群検索の作成
    private BizTranRolesRepository createBizTranRolesRepository(Boolean isBizTranRoleDeleteTest) {
        return new BizTranRolesRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                List<BizTranRole> list = createBizTranRoleList();
                if (!isBizTranRoleDeleteTest) {
                    // 取引ロール削除以外のテスト（削除対象データをのぞく）
                    list = list.stream().filter(l->!l.getBizTranRoleId().equals(52L)).collect(Collectors.toList());
                }
                return BizTranRoles.createFrom(list);
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
    }
    // オペレーター_取引ロール割当群検索の作成
    private Operator_BizTranRolesRepository createOperator_BizTranRolesRepository() {
        return new Operator_BizTranRolesRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return Operator_BizTranRoles.createFrom(createOperator_BizTranRoleList().stream().filter(o->
                    operator_BizTranRoleCriteria.getBizTranRoleIdCriteria().getIncludes().contains(o.getBizTranRoleId())).collect(Collectors.toList()));
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
                actualMessageDtoList = messageDtoList;
            }
        };
    }

//    /**
//     * {@link  StoreBizTranRoleComposition#execute(BizTranRoleCompositionImportRequest,BizTranRoleCompositionImportResponse)}のテスト
//     *  ●パターン
//     *    正常
//     *
//     *  ●検証事項
//     *  ・正常終了
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void execute_test0() {
//
//        // 期待値
//        List<MessageDto> expectedMessageDtoList = newArrayList();
//
//        // テスト対象クラス生成
//        CheckBizTranRoleComposition checkBizTranRoleComposition = new CheckBizTranRoleComposition(
//            createBizTranRolesRepository(false),
//            createOperator_BizTranRolesRepository());
//
//        // 実行値
//        BizTranRoleCompositionImportRequest request = createBizTranRoleCompositionImportRequest();
//        BizTranRoleCompositionImportResponse response = createBizTranRoleCompositionImportResponse();
//
//        assertThatCode(() ->
//            // 実行
//            checkBizTranRoleComposition.execute(request, response))
//            .doesNotThrowAnyException();
//    }

//    /**
//     * {@link  StoreBizTranRoleComposition#execute(BizTranRoleCompositionImportRequest,BizTranRoleCompositionImportResponse)}のテスト
//     *  ●パターン
//     *    正常
//     *    ・取引ロールの削除対象があり、メッセージが出力される
//     *
//     *  ●検証事項
//     *  ・正常終了
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void execute_test1() {
//
//        // 期待値
//        List<MessageDto> expectedMessageDtoList = newArrayList();
//        expectedMessageDtoList.add(MessageDto.createFrom("WOA13107","",Arrays.asList("yu001011", "ANAG99")));
//
//        // テスト対象クラス生成
//        CheckBizTranRoleComposition checkBizTranRoleComposition = new CheckBizTranRoleComposition(
//            createBizTranRolesRepository(true),
//            createOperator_BizTranRolesRepository());
//
//        // 実行
//        checkBizTranRoleComposition.execute(createBizTranRoleCompositionImportRequest(),
//            createBizTranRoleCompositionImportResponse());
//
//        // 結果検証
//        assertThat(actualMessageDtoList.size()).isEqualTo(expectedMessageDtoList.size());
//        for(int i = 0; i < actualMessageDtoList.size(); i++) {
//            assertThat(actualMessageDtoList.get(i).getMessageCode()).as(i + 1 + "レコード目でエラー")
//                .isEqualTo(expectedMessageDtoList.get(i).getMessageCode());
//            assertThat(actualMessageDtoList.get(i).getMessageArgs()).as(i + 1 + "レコード目でエラー")
//                .usingRecursiveComparison().isEqualTo(expectedMessageDtoList.get(i).getMessageArgs());
//        }
//    }

//    /**
//     * {@link  StoreBizTranRoleComposition#execute(BizTranRoleCompositionImportRequest,BizTranRoleCompositionImportResponse)}のテスト
//     *  ●パターン
//     *    インポートデータ不正
//     *
//     *  ●検証事項
//     *  ・エラー発生
//     */
//    @Test
//    @Tag(TestSize.SMALL)
//    void execute_test2() {
//
//        // 実行値
//        bizTranRole_BizTranGrpSheetList.add(BizTranRole_BizTranGrpSheet.createFrom(4,SubSystem.販売_畜産.getName(),"","エラ－データ","ANTG10","センター維持管理グループ"));
//
//        // 期待値
//        List<MessageDto> expectedMessageDtoList = newArrayList();
//        expectedMessageDtoList.add(MessageDto.createFrom("EOA13103","",Arrays.asList("[取引ロール－取引グループ編成]シート", "4", "取引ロールコード")));
//
//        // テスト対象クラス生成
//        CheckBizTranRoleComposition checkBizTranRoleComposition = new CheckBizTranRoleComposition(
//            createBizTranRolesRepository(false),
//            createOperator_BizTranRolesRepository());
//
//        // 実行 & 結果検証
//        assertThatThrownBy(() ->
//            checkBizTranRoleComposition.execute(createBizTranRoleCompositionImportRequest(),
//                createBizTranRoleCompositionImportResponse()))
//            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
//                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
//            });
//        assertThat(actualMessageDtoList.size()).isEqualTo(expectedMessageDtoList.size());
//        for(int i = 0; i < actualMessageDtoList.size(); i++) {
//            assertThat(actualMessageDtoList.get(i).getMessageCode()).as(i + 1 + "レコード目でエラー")
//                .isEqualTo(expectedMessageDtoList.get(i).getMessageCode());
//            assertThat(actualMessageDtoList.get(i).getMessageArgs()).as(i + 1 + "レコード目でエラー")
//                .usingRecursiveComparison().isEqualTo(expectedMessageDtoList.get(i).getMessageArgs());
//        }
//    }
}