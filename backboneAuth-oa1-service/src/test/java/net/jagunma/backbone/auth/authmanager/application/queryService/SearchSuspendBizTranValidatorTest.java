package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchRequest;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchSuspendBizTranValidatorTest {

    // 実行既定値
    private final StringCriteria jaCodeCriteria = new StringCriteria();
    private final StringCriteria branchCodeCriteria = new StringCriteria();
    private final StringCriteria subSystemCodeCriteria = new StringCriteria();
    private final StringCriteria bizTranGrpCodeCriteria = new StringCriteria();
    private final StringCriteria bizTranCodeCriteria = new StringCriteria();
    private LocalDateCriteria suspendStartDateCriteria = new LocalDateCriteria();
    private LocalDateCriteria suspendEndDateCriteria = new LocalDateCriteria();
    private final StringCriteria suspendReasonCriteria = new StringCriteria();
    // 一時取引抑止<一覧>検索サービス Request
    private SuspendBizTransSearchRequest createSuspendBizTranSearchRequest() {
        return new SuspendBizTransSearchRequest() {
            @Override
            public StringCriteria getJaCodeCriteria() {
                return jaCodeCriteria;
            }
            @Override
            public StringCriteria getBranchCodeCriteria() {
                return branchCodeCriteria;
            }
            @Override
            public StringCriteria getSubSystemCodeCriteria() {
                return subSystemCodeCriteria;
            }
            @Override
            public StringCriteria getBizTranGrpCodeCriteria() {
                return bizTranGrpCodeCriteria;
            }
            @Override
            public StringCriteria getBizTranCodeCriteria() {
                return bizTranCodeCriteria;
            }
            @Override
            public LocalDateCriteria getSuspendStartDateCriteria() {
                return suspendStartDateCriteria;
            }
            @Override
            public LocalDateCriteria getSuspendEndDateCriteria() {
                return suspendEndDateCriteria;
            }
            @Override
            public StringCriteria getSuspendReasonCriteria() {
                return suspendReasonCriteria;
            }
        };
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
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
        SuspendBizTransSearchRequest request = createSuspendBizTranSearchRequest();

        assertThatCode(()->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test1() {

        // 実行値
        suspendStartDateCriteria.setMoreOrEqual(LocalDate.of(2020,11,1));
        suspendStartDateCriteria.setLessOrEqual(LocalDate.of(2020,11,2));
        suspendEndDateCriteria.setMoreOrEqual(LocalDate.of(2020,11,3));
        suspendEndDateCriteria.setLessOrEqual(LocalDate.of(2020,11,4));
        SuspendBizTransSearchRequest request = createSuspendBizTranSearchRequest();

        assertThatCode(()->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
     *  ●パターン
     *    正常（範囲の開始のみ設定）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test2() {

        // 実行値
        suspendStartDateCriteria.setMoreOrEqual(LocalDate.of(2020,11,1));
        suspendEndDateCriteria.setMoreOrEqual(LocalDate.of(2020,11,3));
        SuspendBizTransSearchRequest request = createSuspendBizTranSearchRequest();

        assertThatCode(()->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
     *  ●パターン
     *    正常（範囲の終了のみ設定）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test3() {

        // 実行値
        suspendStartDateCriteria.setLessOrEqual(LocalDate.of(2020,11,2));
        suspendEndDateCriteria.setLessOrEqual(LocalDate.of(2020,11,4));
        SuspendBizTransSearchRequest request = createSuspendBizTranSearchRequest();

        assertThatCode(()->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
     *  ●パターン
     *    リクエスト不正
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test4() {

        // 実行値
        SuspendBizTransSearchRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate())
                .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
     *  ●パターン
     *    抑止期間開始 範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test5() {

        // 実行値
        suspendStartDateCriteria.setMoreOrEqual(LocalDate.of(2020,11,2));
        suspendStartDateCriteria.setLessOrEqual(LocalDate.of(2020,11,1));
        SuspendBizTransSearchRequest request = createSuspendBizTranSearchRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("抑止期間開始");
            });
    }

    /**
     * {@link SearchSuspendBizTranValidator#validate()}のテスト
     *  ●パターン
     *    抑止期間終了 範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test6() {

        // 実行値
        suspendEndDateCriteria.setMoreOrEqual(LocalDate.of(2020,11,4));
        suspendEndDateCriteria.setLessOrEqual(LocalDate.of(2020,11,3));
        SuspendBizTransSearchRequest request = createSuspendBizTranSearchRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchSuspendBizTranValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("抑止期間終了");
            });
    }
}