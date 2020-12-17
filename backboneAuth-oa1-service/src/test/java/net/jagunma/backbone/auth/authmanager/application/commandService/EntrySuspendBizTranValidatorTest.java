package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntrySuspendBizTranValidatorTest {

    // 実行既定値
    private final String jaCode = "006";
    private final String branchCode = "001";
    private final String subSystemCode = SubSystem.販売_畜産.getCode();
    private final String bizTranGrpCode = "ANTG01";
    private final String bizTranCode = "AN0001";
    private LocalDate suspendStartDate = LocalDate.of(2020,11,1);
    private LocalDate suspendEndDate = LocalDate.of(2020,11,2);
    private String suspendReason = "合併前のデータ凍結";


    // 一時取引抑止登録サービスリクエスト作成
    private SuspendBizTranEntryRequest createSuspendBizTranEntryRequest() {
        return new SuspendBizTranEntryRequest() {
            @Override
            public String getJaCode() {
                return jaCode;
            }
            @Override
            public String getBranchCode() {
                return branchCode;
            }
            @Override
            public String getSubSystemCode() {
                return subSystemCode;
            }
            @Override
            public String getBizTranGrpCode() {
                return bizTranGrpCode;
            }
            @Override
            public String getBizTranCode() {
                return bizTranCode;
            }
            @Override
            public LocalDate getSuspendStartDate() {
                return suspendStartDate;
            }
            @Override
            public LocalDate getSuspendEndDate() {
                return suspendEndDate;
            }
            @Override
            public String getSuspendReason() {
                return suspendReason;
            }
        };
    }

    /**
     * {@link EntrySuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void entryValidate_Test0() {

        // 実行値
        SuspendBizTranEntryRequest entryRequest = createSuspendBizTranEntryRequest();

        assertThatCode(() ->
            // 実行
            EntrySuspendBizTranValidator.with(entryRequest).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link EntrySuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void entryValidate_Test1() {

        // 実行値
        SuspendBizTranEntryRequest entryRequest = null;

        assertThatThrownBy(() ->
            // 実行
            EntrySuspendBizTranValidator.with(entryRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link EntrySuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック 有効期限（開始日）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test2() {

        // 実行値
        suspendStartDate = null;
        SuspendBizTranEntryRequest entryRequest = createSuspendBizTranEntryRequest();

        assertThatThrownBy(() ->
            // 実行
            EntrySuspendBizTranValidator.with(entryRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）");
            });
    }

    /**
     * {@link EntrySuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック 有効期限（終了日）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test3() {

        // 実行値
        suspendEndDate = null;
        SuspendBizTranEntryRequest entryRequest = createSuspendBizTranEntryRequest();

        assertThatThrownBy(() ->
            // 実行
            EntrySuspendBizTranValidator.with(entryRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）");
            });
    }

    /**
     * {@link EntrySuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック 抑止理由
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test4() {

        // 実行値
        suspendReason = null;
        SuspendBizTranEntryRequest entryRequest = createSuspendBizTranEntryRequest();

        assertThatThrownBy(() ->
            // 実行
            EntrySuspendBizTranValidator.with(entryRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("抑止理由");
            });
    }

    /**
     * {@link EntrySuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    範囲指定不正チェック 有効期限
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test5() {

        // 実行値
        suspendStartDate = LocalDate.of(2020,11,3);
        suspendEndDate = LocalDate.of(2020,11,2);
        SuspendBizTranEntryRequest entryRequest = createSuspendBizTranEntryRequest();

        assertThatThrownBy(() ->
            // 実行
            EntrySuspendBizTranValidator.with(entryRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }
}