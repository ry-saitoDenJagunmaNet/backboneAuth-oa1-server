package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_SubSystemRoleTest {

    /**
     * {@link Operator_SubSystemRole#createFrom(Long, Long, String, LocalDate, LocalDate, Integer, Operator, SubSystemRole)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行値 ＆ 期待値
        Long operator_SubSystemRoleId = 1234L;
        Long operatorId = 19L;
        String subSystemRoleCode = "JaAdmin";
        LocalDate validThruStartDate = LocalDate.of(2020,1,1);
        LocalDate validThruEndDate = LocalDate.of(9999,12,31);
        Integer recordVersion = 1;
        SubSystemRole subSystemRole = SubSystemRole.業務統括者_購買;

        String operatorCode = "yu001010";
        String operatorName = "ｙｕ００１０１０";
        String mailAddress = "001.001.001.001";
        LocalDate operatorValidThruStartDate = LocalDate.of(2020, 1, 1);
        LocalDate operatorValidThruEndDate = LocalDate.of(9999, 12, 31);
        Boolean isDeviceAuth = false;
        Long jaId = 6L;
        String jaCode = "006";
        Long branchId = 33L;
        String branchCode = "001";
        AvailableStatus availableStatus = AvailableStatus.利用可能;
        Integer operatorRecordVersion = 1;
        BranchAtMoment branchAtMoment = null;
        Operator operator = Operator.createFrom(
            operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            operatorValidThruStartDate,
            operatorValidThruEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            operatorRecordVersion,
            branchAtMoment);

        // 実行
        Operator_SubSystemRole operator_SubSystemRole = Operator_SubSystemRole.createFrom(
            operator_SubSystemRoleId,
            operatorId,
            subSystemRoleCode,
            validThruStartDate,
            validThruEndDate,
            recordVersion,
            operator,
            subSystemRole);

        // 結果検証
        assertTrue(operator_SubSystemRole instanceof Operator_SubSystemRole);
        assertThat(operator_SubSystemRole.getOperator_SubSystemRoleId()).isEqualTo(operator_SubSystemRoleId);
        assertThat(operator_SubSystemRole.getOperatorId()).isEqualTo(operatorId);
        assertThat(operator_SubSystemRole.getSubSystemRoleCode()).isEqualTo(subSystemRoleCode);
        assertThat(operator_SubSystemRole.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(operator_SubSystemRole.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(operator_SubSystemRole.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(operator_SubSystemRole.getOperator()).usingRecursiveComparison().isEqualTo(operator);
        assertThat(operator_SubSystemRole.getSubSystemRole()).usingRecursiveComparison().isEqualTo(subSystemRole);
    }
}