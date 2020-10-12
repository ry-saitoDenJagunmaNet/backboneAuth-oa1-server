package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11020EntryValidatorTest {

    // 実行既定値
    private Long branchId = 1L;
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private String changeCause = "新職員の入組による登録";

    // Oa11020Vo作成
    private Oa11020Vo createOa11020Vo() {
        Oa11020Vo vo = new Oa11020Vo();

        vo.setBranchId(branchId);
        vo.setOperatorCode6(operatorCode6);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setExpirationStartDate(expirationStartDate);
        vo.setExpirationEndDate(expirationEndDate);
        vo.setChangeCause(changeCause);

        return vo;
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test() {
        // 実行値
        Oa11020Vo vo = createOa11020Vo();

        assertThatCode(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    ViewObject不正
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test01() {
        // 実行値
        Oa11020Vo vo = null;

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14001");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    未セット  店舗ID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test02() {
        // 実行値
        branchId = null;
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14002");
                assertThat(e.getArgs()).containsSequence("店舗", "選択");
            });

    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    未セット  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test03() {
        // 実行値
        operatorCode6 = null;
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14002");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）", "入力");
            });
    }
    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    未セット  オペレーター名
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test04() {
        // 実行値
        operatorName = null;
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14002");
                assertThat(e.getArgs()).containsSequence("オペレーター名", "入力");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    未セット  有効期限（開始日）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test05() {
        // 実行値
        expirationStartDate = null;
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）", "入力");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    未セット  有効期限（終了日）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test06() {
        // 実行値
        expirationEndDate = null;
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）", "入力");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    未セット  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test07() {
        // 実行値
        changeCause = null;
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14002");
                assertThat(e.getArgs()).containsSequence("変更事由", "入力");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test08() {
        // 実行値
        operatorCode6 = Strings2.repeat("*", 7);
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14003");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）", "6");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  オペレーター名
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test09() {
        // 実行値
        operatorName = Strings2.repeat("*", 256);
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14003");
                assertThat(e.getArgs()).containsSequence("オペレーター名", "255", "以下");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  メールアドレス
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test10() {
        // 実行値
        mailAddress = Strings2.repeat("*", 256);
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14003");
                assertThat(e.getArgs()).containsSequence("メールアドレス", "255", "以下");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test11() {
        // 実行値
        changeCause = Strings2.repeat("*", 256);
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14003");
                assertThat(e.getArgs()).containsSequence("変更事由", "255", "以下");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    全角混入チェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Disabled // ToDo:
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test12() {
        // 実行値
        operatorCode6 = "123全56";
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14005");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    全角混入チェック  メールアドレス
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Disabled // ToDo:
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test13() {
        // 実行値
        mailAddress = "te全st@den.jagunma.net";
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14005");
                assertThat(e.getArgs()).containsSequence("メールアドレス");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    数値チェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test14() {
        // 実行値
        operatorCode6 = "1.3-aB";
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14006");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）");
            });
    }

    /**
     * {@link Oa11020EntryValidator#validate()}テスト
     *  ●パターン
     *    範囲指定不正チェック  有効期限
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test15() {
        // 実行値
        expirationEndDate = LocalDate.of(2020, 8, 31);
        Oa11020Vo vo = createOa11020Vo();

        assertThatThrownBy(() ->
            // 実行
            Oa11020EntryValidator.with(vo).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA14007");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }
}