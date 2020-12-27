package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntrySuspendBizTranTest {

    // 実行既定値
    private String jaCode = "006";
    private String branchCode = "001";
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private String bizTranGrpCode = "ANTG01";
    private String bizTranCode = "AN0001";
    private final LocalDate suspendStartDate = LocalDate.of(2010,6,21);
    private final LocalDate suspendEndDate = LocalDate.of(9999,12,31);
    private final String suspendReason = "合併前のデータ凍結";
    private SuspendBizTran actualInsertSuspendBizTran;

    // 一時取引抑止登録サービス作成
    private EntrySuspendBizTran createEntrySuspendBizTran() {
        SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore = new SuspendBizTranRepositoryForStore() {
            @Override
            public SuspendBizTran insert(SuspendBizTran suspendBizTran) {
                actualInsertSuspendBizTran = suspendBizTran;
                return null;
            }
            @Override
            public SuspendBizTran update(SuspendBizTran suspendBizTran) {
                return null;
            }
            @Override
            public void delete(SuspendBizTran suspendBizTran) {
            }
        };
        return new EntrySuspendBizTran(suspendBizTranRepositoryForStore);
    }
    // 一時取引抑止登録サービス Request作成
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
     * {@link EntrySuspendBizTran#execute(SuspendBizTranEntryRequest)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        EntrySuspendBizTran entrySuspendBizTran = createEntrySuspendBizTran();

        // 実行値
        SuspendBizTranEntryRequest request = createSuspendBizTranEntryRequest();

        assertThatCode(() ->
            // 実行
            entrySuspendBizTran.execute(request)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualInsertSuspendBizTran.getJaCode()).isEqualTo(request.getJaCode());
        assertThat(actualInsertSuspendBizTran.getBranchCode()).isEqualTo(request.getBranchCode());
        assertThat(actualInsertSuspendBizTran.getSubSystemCode()).isEqualTo(request.getSubSystemCode());
        assertThat(actualInsertSuspendBizTran.getBizTranGrpCode()).isEqualTo(request.getBizTranGrpCode());
        assertThat(actualInsertSuspendBizTran.getBizTranCode()).isEqualTo(request.getBizTranCode());
        assertThat(actualInsertSuspendBizTran.getSuspendStartDate()).isEqualTo(request.getSuspendStartDate());
        assertThat(actualInsertSuspendBizTran.getSuspendEndDate()).isEqualTo(request.getSuspendEndDate());
        assertThat(actualInsertSuspendBizTran.getSuspendReason()).isEqualTo(request.getSuspendReason());
    }

    /**
     * {@link EntrySuspendBizTran#execute(SuspendBizTranEntryRequest)}テスト
     *  ●パターン
     *    正常
     *    ・リクエスト項目未設定（JAコード）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {

        // テスト対象クラス生成
        EntrySuspendBizTran entrySuspendBizTran = createEntrySuspendBizTran();

        // 実行値
        jaCode = null;
        SuspendBizTranEntryRequest request = createSuspendBizTranEntryRequest();

        assertThatCode(() ->
            // 実行
            entrySuspendBizTran.execute(request)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualInsertSuspendBizTran.getJaCode()).isEqualTo(request.getJaCode());
        assertThat(actualInsertSuspendBizTran.getBranchCode()).isEqualTo(request.getBranchCode());
        assertThat(actualInsertSuspendBizTran.getSubSystemCode()).isEqualTo(request.getSubSystemCode());
        assertThat(actualInsertSuspendBizTran.getBizTranGrpCode()).isEqualTo(request.getBizTranGrpCode());
        assertThat(actualInsertSuspendBizTran.getBizTranCode()).isEqualTo(request.getBizTranCode());
        assertThat(actualInsertSuspendBizTran.getSuspendStartDate()).isEqualTo(request.getSuspendStartDate());
        assertThat(actualInsertSuspendBizTran.getSuspendEndDate()).isEqualTo(request.getSuspendEndDate());
        assertThat(actualInsertSuspendBizTran.getSuspendReason()).isEqualTo(request.getSuspendReason());
    }

    /**
     * {@link EntrySuspendBizTran#execute(SuspendBizTranEntryRequest)}テスト
     *  ●パターン
     *    正常
     *    ・リクエスト項目未設定（店舗コード、サブシステムコード、取引グループコード、取引コード）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test3() {

        // テスト対象クラス生成
        EntrySuspendBizTran entrySuspendBizTran = createEntrySuspendBizTran();

        // 実行値
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        SuspendBizTranEntryRequest request = createSuspendBizTranEntryRequest();

        assertThatCode(() ->
            // 実行
            entrySuspendBizTran.execute(request)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualInsertSuspendBizTran.getJaCode()).isEqualTo(request.getJaCode());
        assertThat(actualInsertSuspendBizTran.getBranchCode()).isEqualTo(request.getBranchCode());
        assertThat(actualInsertSuspendBizTran.getSubSystemCode()).isEqualTo(request.getSubSystemCode());
        assertThat(actualInsertSuspendBizTran.getBizTranGrpCode()).isEqualTo(request.getBizTranGrpCode());
        assertThat(actualInsertSuspendBizTran.getBizTranCode()).isEqualTo(request.getBizTranCode());
        assertThat(actualInsertSuspendBizTran.getSuspendStartDate()).isEqualTo(request.getSuspendStartDate());
        assertThat(actualInsertSuspendBizTran.getSuspendEndDate()).isEqualTo(request.getSuspendEndDate());
        assertThat(actualInsertSuspendBizTran.getSuspendReason()).isEqualTo(request.getSuspendReason());
    }

    /**
     * {@link EntrySuspendBizTran#execute(SuspendBizTranEntryRequest)}テスト
     *  ●パターン
     *    正常
     *    ・リクエスト項目未設定（JAコード、店舗コード、サブシステムコード、取引グループコード、取引コード）
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test4() {

        // テスト対象クラス生成
        EntrySuspendBizTran entrySuspendBizTran = createEntrySuspendBizTran();

        // 実行値
        jaCode = null;
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        SuspendBizTranEntryRequest request = createSuspendBizTranEntryRequest();

        // 結果検証
        assertThatThrownBy(() ->
            // 実行
            entrySuspendBizTran.execute(request))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12005");
            });
    }

}