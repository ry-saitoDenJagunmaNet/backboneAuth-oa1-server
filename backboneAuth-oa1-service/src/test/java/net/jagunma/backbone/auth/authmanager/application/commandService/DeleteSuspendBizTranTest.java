package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranDeleteRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.seasar.doma.jdbc.criteria.expression.ArithmeticExpression.Sub;

class DeleteSuspendBizTranTest {

    // 実行既定値
    private Long suspendBizTranId = 12345678L;
    private Integer recordVersion = 9;
    private SuspendBizTran actualUpdateSuspendBizTran;

    // 一時取引抑止登録サービス作成
    private DeleteSuspendBizTran createDeleteSuspendBizTran() {
        SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore = new SuspendBizTranRepositoryForStore() {
            @Override
            public void delete(SuspendBizTran suspendBizTran) {
                actualUpdateSuspendBizTran = suspendBizTran;
            }
            @Override
            public SuspendBizTran update(SuspendBizTran suspendBizTran) {
                return null;
            }
            @Override
            public SuspendBizTran insert(SuspendBizTran suspendBizTran) {
                return null;
            }
        };
        return new DeleteSuspendBizTran(suspendBizTranRepositoryForStore);
    }
    // 一時取引抑止更新サービス Request作成
    private SuspendBizTranDeleteRequest createSuspendBizTranDeleteRequest() {
        return new SuspendBizTranDeleteRequest() {
            @Override
            public Long getSuspendBizTranId() {
                return suspendBizTranId;
            }
            @Override
            public Integer getRecordVersion() {
                return recordVersion;
            }
        };
    }

    /**
     * {@link DeleteSuspendBizTran#execute(SuspendBizTranDeleteRequest)}テスト
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
        DeleteSuspendBizTran deleteSuspendBizTran = createDeleteSuspendBizTran();

        // 実行値
        SuspendBizTranDeleteRequest request = createSuspendBizTranDeleteRequest();

        assertThatCode(() ->
            // 実行
            deleteSuspendBizTran.execute(request)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualUpdateSuspendBizTran.getSuspendBizTranId()).isEqualTo(request.getSuspendBizTranId());
        assertThat(actualUpdateSuspendBizTran.getRecordVersion()).isEqualTo(request.getRecordVersion());
    }
}