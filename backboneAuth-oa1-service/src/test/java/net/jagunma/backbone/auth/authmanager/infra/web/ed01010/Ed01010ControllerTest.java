package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.application.commandService.UpdatePassword;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Ed01010ControllerTest {

    // 実行 ＆ 期待 既定値
    // オペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";

    // ＪＡAtMoment
    private String jaName = "JA前橋市";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // 店舗AtMoment
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName("本店").build()).build();

    // オペレーター系
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, null, null, null, null, jaId, jaCode, branchId, branchCode, null, null, branchAtMoment);

    // モード
    private String mode = "";

    // パスワード項目系
    private String oldPassword = "OldPaSsWoRd";
    private String newPassword = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";

    // テスト対象クラス生成
    private Ed01010Controller createEd01010Controller() {

        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneById(Long operatorId) {
                return null;
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                return null;
            }
            @Override
            public boolean existsById(Long operatorId) {
                return false;
            }
            @Override
            public boolean existsByCode(String operatorCode) {
                return false;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        SearchOperator searchOperator = new SearchOperator(operatorRepository, null, null, null, null, null, null, null) {
            @Override
            public void execute(OperatorSearchRequest request, OperatorSearchResponse response) {
                // request.getOperatorId().equals(11L) の場合：GunmaRuntimeException を発生させる
                if(request.getOperatorId().equals(11L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "パスワードの確認入力"));
                }
                // request.getOperatorId().equals(12L) の場合：RuntimeException を発生させる
                if(request.getOperatorId().equals(12L)) {
                    throw new RuntimeException();
                }
                response.setOperator(operator);
            }
        };

        PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore = new PasswordHistoryRepositoryForStore() {
            @Override
            public void store(PasswordHistory passwordHistory) {
            }
        };
        PasswordHistoryRepository passwordHistoryRepository = new PasswordHistoryRepository() {
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return null;
            }
            @Override
            public PasswordHistory latestOneByOperatorId(Long operatorId) {
                return null;
            }
        };
        UpdatePassword updatePassword = new UpdatePassword(passwordHistoryRepositoryForStore, passwordHistoryRepository) {
            @Override
            public void execute(PasswordResetRequest request) {
                // request.getOperatorId().equals(21L) の場合：GunmaRuntimeException を発生させる
                if(request.getOperatorId().equals(21L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "パスワード"));
                }
            }
            @Override
            public void execute(PasswordChangeRequest request) {
                // request.getOperatorId().equals(22L) の場合：RuntimeException を発生させる
                if(request.getOperatorId().equals(22L)) {
                    throw new RuntimeException();
                }
            }
        };

        return new Ed01010Controller(updatePassword, searchOperator);
    }

    // Ed01010Vo作成
    private Ed01010Vo createEd01010Vo() {
        Ed01010Vo vo = new Ed01010Vo();

        vo.setMode(mode);
        vo.setOperatorId(operatorId);
        vo.setJa(jaCode + " " + jaName);
        vo.setOperator(operatorCode + " " + operatorName);
        vo.setOldPassword(oldPassword);
        vo.setNewPassword(newPassword);
        vo.setConfirmPassword(confirmPassword);

        return vo;
    }

    /**
     * {@link Ed01010Controller#get(String mode, Long operatorId, Model model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "ed01010";
        mode = "Reset";
        oldPassword = null;
        newPassword = null;
        confirmPassword = null;
        Ed01010Vo expectedVo = createEd01010Vo();

        // 実行
        String actualViewName = ed01010Controller.get(mode, operatorId, model);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Ed01010Controller#get(String mode, Long operatorId, Model model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        mode = "Reset";
        operatorId = 11L;

        // 期待値
        String expectedViewName = "ed01010";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "パスワードの確認入力";

        // 実行
        String actualViewName = ed01010Controller.get(mode, operatorId, model);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Ed01010Controller#get(String mode, Long operatorId, Model model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        mode = "Change";
        operatorId = 12L;

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = ed01010Controller.get(mode, operatorId, model);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Ed01010Controller#update(Model model, Ed01010Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test_reset() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        mode = "Reset";
        Ed01010Vo vo = createEd01010Vo();

        // 期待値
        String expectedViewName = "ed01010"; // ToDo: 遷移制御
        Ed01010Vo expectedVo = createEd01010Vo();

        // 実行
        String actualViewName = ed01010Controller.update(model, vo);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Ed01010Controller#update(Model model, Ed01010Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test_change() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        mode = "Change";
        Ed01010Vo vo = createEd01010Vo();

        // 期待値
        String expectedViewName = "ed01010"; // ToDo: 遷移制御
        Ed01010Vo expectedVo = createEd01010Vo();

        // 実行
        String actualViewName = ed01010Controller.update(model, vo);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Ed01010Controller#update(Model model, Ed01010Vo vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test1() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        mode = "Reset";
        operatorId = 21L;
        Ed01010Vo vo = createEd01010Vo();

        // 期待値
        String expectedViewName = "ed01010";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "パスワード";

        // 実行
        String actualViewName = ed01010Controller.update(model, vo);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Ed01010Controller#update(Model model, Ed01010Vo vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test2() {
        // テスト対象クラス生成
        Ed01010Controller ed01010Controller = createEd01010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        mode = "Change";
        operatorId = 22L;
        Ed01010Vo vo = createEd01010Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = ed01010Controller.update(model, vo);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}