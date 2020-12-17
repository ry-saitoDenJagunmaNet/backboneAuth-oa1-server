package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdateSuspendBizTranTest {

    // 実行既定値
    private Long suspendBizTranId = 12345678L;
    private LocalDate suspendStartDate = LocalDate.of(2010,6,21);
    private LocalDate suspendEndDate = LocalDate.of(9999,12,31);
    private Integer recordVersion = 9;
    private SuspendBizTran actualUpdateSuspendBizTran;

    // 一時取引抑止登録サービス作成
    private UpdateSuspendBizTran createUpdateSuspendBizTran() {
        SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore = new SuspendBizTranRepositoryForStore() {
            @Override
            public SuspendBizTran update(SuspendBizTran suspendBizTran) {
                actualUpdateSuspendBizTran = suspendBizTran;
                return null;
            }
            @Override
            public SuspendBizTran insert(SuspendBizTran suspendBizTran) {
                return null;
            }
            @Override
            public void delete(SuspendBizTran suspendBizTran) {
            }
        };
        return new UpdateSuspendBizTran(suspendBizTranRepositoryForStore);
    }
    // 一時取引抑止更新サービス Request作成
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
     * {@link UpdateSuspendBizTran#execute(SuspendBizTranUpdateRequest)}テスト
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
        UpdateSuspendBizTran updateSuspendBizTran = createUpdateSuspendBizTran();

        // 実行値
        SuspendBizTranUpdateRequest request = createSuspendBizTranUpdateRequest();

        assertThatCode(() ->
            // 実行
            updateSuspendBizTran.execute(request)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualUpdateSuspendBizTran.getSuspendBizTranId()).isEqualTo(request.getSuspendBizTranId());
        assertThat(actualUpdateSuspendBizTran.getSuspendStartDate()).isEqualTo(request.getSuspendStartDate());
        assertThat(actualUpdateSuspendBizTran.getSuspendEndDate()).isEqualTo(request.getSuspendEndDate());
        assertThat(actualUpdateSuspendBizTran.getRecordVersion()).isEqualTo(request.getRecordVersion());
    }
}