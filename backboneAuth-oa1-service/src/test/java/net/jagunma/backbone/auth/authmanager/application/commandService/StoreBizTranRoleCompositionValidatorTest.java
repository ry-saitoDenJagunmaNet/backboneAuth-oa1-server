package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreBizTranRoleCompositionValidatorTest {

    // 実行既定値
    private String subSystemCode = SubSystem.販売_畜産.getCode();

    /**
     * {@link StoreBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test0() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return subSystemCode;
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        assertThatCode(()->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    リクエスト不正
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test1() {

        // 実行値
        BizTranRoleCompositionImportRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    サブシステム 未セット
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test2() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return null;
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        assertThatThrownBy(() ->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サブシステム");
            });
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    サブシステム不一致（取引ロール－取引グループ編成シ－ト）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test3() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"購買","KBAG01","（購買）購買業務基本","KBTG01","購買メニュー"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        assertThatThrownBy(() ->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13011");
                assertThat(e.getArgs()).containsSequence("取引ロール－取引グループ編成");
            });
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    サブシステム不一致（取引グループ－取引編成シ－ト）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test4() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"購買","KBTG01","購買メニュー","KB0000","購買メインメニュー",false,LocalDate.of(2010,3,11),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        assertThatThrownBy(() ->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13011");
                assertThat(e.getArgs()).containsSequence("取引グループ－取引編成");
            });
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了（インポートデータ0件）
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test00() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        assertThatCode(()->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).checkExcelImport()).doesNotThrowAnyException();
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test01() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        assertThatCode(()->
            // 実行
            StoreBizTranRoleCompositionValidator.with(request).checkExcelImport()).doesNotThrowAnyException();
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引ロール－取引グループ編成シ－ト
     *    ・未セットチェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test02() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"","ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(5,"販売・畜産","","（畜産）取引全般","ANTG03","マスタ取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(6,"販売・畜産","ANAG02","","ANTG02","精算取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(7,"販売・畜産","ANAG02","（畜産）維持管理担当者","","マスタ取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(8,"販売・畜産","ANAG98","（畜産）センター維持管理担当者","ANTG10",""));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] rrror_meaasge = {"EOA13013","未セット(［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s)  CL:［取引ロール－取引グループ編成］（Excel行：%1$d）%2$sが入力されていません。","［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],4,"サブシステム"),Arrays.asList(String.format(rrror_meaasge[2],4,"サブシステム"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],5,"取引ロールコード"),Arrays.asList(String.format(rrror_meaasge[2],5,"取引ロールコード"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],6,"取引ロール名称"),Arrays.asList(String.format(rrror_meaasge[2],6,"取引ロール名称"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],7,"取引グループコード"),Arrays.asList(String.format(rrror_meaasge[2],7,"取引グループコード"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],8,"取引グループ名称"),Arrays.asList(String.format(rrror_meaasge[2],8,"取引グループ名称"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引ロール－取引グループ編成シ－ト
     *    ・重複チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test03() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"販売・畜産","ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(5,"販売・畜産","ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(6,"販売・畜産","ANAG01","（畜産）取引全般","ANTG03","マスタ取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG02","精算取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(5,"販売・畜産","ANTG03","マスタ取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13009","重複レコードあり(［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s)    CL:［取引ロール－取引グループ編成］（Excel行：%1$d）%2$sの同一キーで重複するレコードがあります。","［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],4,"取引ロールコード＋取引グループコード"),Arrays.asList(String.format(error_meaasge[2],4,"取引ロールコード＋取引グループコード"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引ロール－取引グループ編成シ－ト
     *    ・同一キーレコード内容一致チェック（取引ロール）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test04() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"販売・畜産","ANAG01","（畜産）取引全般x","ANTG02","精算取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG02","精算取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13010","同一キーで内容不一致レコードあり(［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s)    CL:［取引ロール－取引グループ編成］（Excel行：%1$d）%2$sの同一キーで項目内容が不一致のレコードがあります。","［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],3,"取引ロール"),Arrays.asList(String.format(error_meaasge[2],3,"取引ロール"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引ロール－取引グループ編成シ－ト
     *    ・同一キーレコード内容一致チェック（取引グループ）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test05() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"販売・畜産","ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(5,"販売・畜産","ANAG02","（畜産）取引全般","ANTG02","精算取引グループx"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG02","精算取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13010","同一キーで内容不一致レコードあり(［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s)    CL:［取引ロール－取引グループ編成］（Excel行：%1$d）%2$sの同一キーで項目内容が不一致のレコードがあります。","［取引ロール－取引グループ編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],4,"取引グループ"),Arrays.asList(String.format(error_meaasge[2],4,"取引グループ"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引ロール－取引グループ編成シ－ト
     *    ・［取引グループコード］双方向存在チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test06() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"販売・畜産","ANAG02","（畜産）取引全般","ANTG02","精算取引グループx"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13012","取引グループコード不正    CL:%1$sに入力した取引グループコードが%2$sに存在しません。(取引グループコード:%3$s)","［取引ロール－取引グループ編成］","［取引グループ－取引編成］","ANTG02"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],error_meaasge[2],error_meaasge[3],error_meaasge[4]),Arrays.asList(error_meaasge[2],error_meaasge[3],error_meaasge[4])));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引グループ－取引編成シ－ト
     *    ・未セットチェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test07() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"","ANTG01","データ入力取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(5,"販売・畜産","","データ入力取引グループ","AN1210","仕切入力",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(6,"販売・畜産","ANTG01","データ入力取引グループ","","仕切修正",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(7,"販売・畜産","ANTG01","データ入力取引グループ","AN1410","",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(8,"販売・畜産","ANTG01","データ入力取引グループ","AN1411","仕切エ一覧",null,LocalDate.of(2010,8,23),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(9,"販売・畜産","ANTG01","データ入力取引グループ","AN1710","特別控除入力メニュー",false,null,LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(10,"販売・畜産","ANTG01","データ入力取引グループ","AN1711","特別控除控除コード指定入力",false,LocalDate.of(2010,6,21),null));
                list.add(BizTranGrp_BizTranSheet.createFrom(11,"販売・畜産","ANTG02","","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] rrror_meaasge = {"EOA13013","未セット(［取引グループ－取引編成］（Excel行：%1$d）%2$s)  CL:［取引グループ－取引編成］（Excel行：%1$d）%2$sが入力されていません。","［取引グループ－取引編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],4,"サブシステム"),Arrays.asList(String.format(rrror_meaasge[2],4,"サブシステム"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],5,"取引グループコード"),Arrays.asList(String.format(rrror_meaasge[2],5,"取引グループコード"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],6,"取引コード"),Arrays.asList(String.format(rrror_meaasge[2],6,"取引コード"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],7,"取引名称"),Arrays.asList(String.format(rrror_meaasge[2],7,"取引名称"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],8,"センター取引区分"),Arrays.asList(String.format(rrror_meaasge[2],8,"センター取引区分"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],9,"有効期限From"),Arrays.asList(String.format(rrror_meaasge[2],9,"有効期限From"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],10,"有効期限To"),Arrays.asList(String.format(rrror_meaasge[2],10,"有効期限To"))));
        expectedList.add(MessageDto.createFrom(rrror_meaasge[0],String.format(rrror_meaasge[1],11,"取引グループ名称"),Arrays.asList(String.format(rrror_meaasge[2],11,"取引グループ名称"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引グループ－取引編成シ－ト
     *    ・重複チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test08() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG01","データ入力取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(5,"販売・畜産","ANTG01","データ入力取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(6,"販売・畜産","ANTG01","データ入力取引グループ","AN1210","仕切入力",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13009","重複レコードあり(［取引グループ－取引編成］（Excel行：%1$d）%2$s)    CL:［取引グループ－取引編成］（Excel行：%1$d）%2$sの同一キーで重複するレコードがあります。","［取引グループ－取引編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],4,"取引グループコード＋取引コード"),Arrays.asList(String.format(error_meaasge[2],4,"取引グループコード＋取引コード"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引グループ－取引編成シ－ト
     *    ・同一キーレコード内容一致チェック（取引グループ）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test09() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG01","データ入力取引グループx","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13010","同一キーで内容不一致レコードあり(［取引グループ－取引編成］（Excel行：%1$d）%2$s)    CL:［取引グループ－取引編成］（Excel行：%1$d）%2$sの同一キーで項目内容が不一致のレコードがあります。","［取引グループ－取引編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],3,"取引グループ"),Arrays.asList(String.format(error_meaasge[2],3,"取引グループ"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引グループ－取引編成シ－ト
     *    ・同一キーレコード内容一致チェック（取引）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test10() {

        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"販売・畜産","ANAG02","（畜産）取引全般","ANTG02","精算取引グループx"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG01","データ入力取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(5,"販売・畜産","ANTG01","データ入力取引グループ","AN1610","振込処理",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(6,"販売・畜産","ANTG01","データ入力取引グループ","AN1611","振込要求照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(7,"販売・畜産","ANTG02","精算取引グループ","AN0001","畜産メインメニューx",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(8,"販売・畜産","ANTG02","精算取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,22),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(9,"販売・畜産","ANTG02","精算取引グループ","AN1610","振込処理",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,30)));
                list.add(BizTranGrp_BizTranSheet.createFrom(10,"販売・畜産","ANTG02","精算取引グループ","AN1611","振込要求照会",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13010","同一キーで内容不一致レコードあり(［取引グループ－取引編成］（Excel行：%1$d）%2$s)    CL:［取引グループ－取引編成］（Excel行：%1$d）%2$sの同一キーで項目内容が不一致のレコードがあります。","［取引グループ－取引編成］（Excel行：%1$d）%2$s"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],3,"取引"),Arrays.asList(String.format(error_meaasge[2],3,"取引"))));
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],4,"取引"),Arrays.asList(String.format(error_meaasge[2],4,"取引"))));
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],5,"取引"),Arrays.asList(String.format(error_meaasge[2],5,"取引"))));
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],6,"取引"),Arrays.asList(String.format(error_meaasge[2],6,"取引"))));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }

    /**
     * {@link StoreBizTranRoleCompositionValidator#checkExcelImport()}のテスト
     *  ●パターン
     *    取引グループ－取引編成シ－ト
     *    ・［取引グループコード］双方向存在チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkExcelImport_test11() {

        // 実行値
        BizTranRoleCompositionImportRequest request = new BizTranRoleCompositionImportRequest() {
            @Override
            public String getSubSystemCode() {
                return "AN";
            }
            @Override
            public BizTranRole_BizTranGrpsSheet getBizTranRole_BizTranGrpsSheet() {
                List<BizTranRole_BizTranGrpSheet> list = newArrayList();
                list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
                return BizTranRole_BizTranGrpsSheet.createFrom(list);
            }
            @Override
            public BizTranGrp_BizTransSheet getBizTranGrp_BizTransSheet() {
                List<BizTranGrp_BizTranSheet> list = newArrayList();
                list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","ANTG01","畜産メインメニュー",false, LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG02","精算取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
                return BizTranGrp_BizTransSheet.createFrom(list);
            }
        };

        // 期待値
        String[] error_meaasge = {"EOA13012","取引グループコード不正    CL:%1$sに入力した取引グループコードが%2$sに存在しません。(取引グループコード:%3$s)","［取引グループ－取引編成］","［取引ロール－取引グループ編成］","ANTG02"};
        List<MessageDto> expectedList = newArrayList();
        expectedList.add(MessageDto.createFrom(error_meaasge[0],String.format(error_meaasge[1],error_meaasge[2],error_meaasge[3],error_meaasge[4]),Arrays.asList(error_meaasge[2],error_meaasge[3],error_meaasge[4])));

        // 実行
        List<MessageDto> actualList = StoreBizTranRoleCompositionValidator.with(request).checkExcelImport();

        // 結果検証
        assertThat(actualList.size()).isEqualTo(expectedList.size());
        for(int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedList.get(i));
        }
    }
}
