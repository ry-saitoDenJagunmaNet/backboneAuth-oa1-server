package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdateSuspendBizTranValidatorTest {

    // 実行既定値
    private final Long suspendBizTranId = 12345678L;
    private LocalDate suspendStartDate = LocalDate.of(2020,11,1);
    private LocalDate suspendEndDate = LocalDate.of(2020,11,2);
    private final Integer recordVersion = 1;

    // 一時取引抑止更新サービスリクエスト作成
    private SuspendBizTranUpdateRequest createSuspendBizTranUpdateRequest() {
        return new SuspendBizTranUpdateRequest() {
            @Override
            public Long getSuspendBizTranId() {
                return suspendBizTranId;
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
            public Integer getRecordVersion() {
                return recordVersion;
            }
        };
    }

    /**
     * {@link UpdateSuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test0() {

        // 実行値
        SuspendBizTranUpdateRequest updateRequest = createSuspendBizTranUpdateRequest();

        assertThatCode(() ->
            // 実行
            UpdateSuspendBizTranValidator.with(updateRequest).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link UpdateSuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test1() {

        // 実行値
        SuspendBizTranUpdateRequest updateRequest = null;

        assertThatThrownBy(() ->
            // 実行
            UpdateSuspendBizTranValidator.with(updateRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link UpdateSuspendBizTranValidator#validate()}テスト
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
        SuspendBizTranUpdateRequest updateRequest = createSuspendBizTranUpdateRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateSuspendBizTranValidator.with(updateRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）");
            });
    }

    /**
     * {@link UpdateSuspendBizTranValidator#validate()}テスト
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
        SuspendBizTranUpdateRequest updateRequest = createSuspendBizTranUpdateRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateSuspendBizTranValidator.with(updateRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）");
            });
    }

    /**
     * {@link UpdateSuspendBizTranValidator#validate()}テスト
     *  ●パターン
     *    範囲指定不正チェック 有効期限
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test4() {

        // 実行値
        suspendStartDate = LocalDate.of(2020,11,3);
        suspendEndDate = LocalDate.of(2020,11,2);
        SuspendBizTranUpdateRequest updateRequest = createSuspendBizTranUpdateRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateSuspendBizTranValidator.with(updateRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }








 }