package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.util.CheckboxUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11030UpdateConverterTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;
    private Long branchId = 1L;
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer recordVersion = 1;
    private String changeCause = "認証機器使用開始";

    /**
     * {@link Oa11030UpdateConverter#with(Oa11030Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test() {
        // 実行値
        Oa11030Vo vo = new Oa11030Vo();
        vo.setOperatorId(operatorId);
        vo.setRecordVersion(recordVersion);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setValidThruStartDate(validThruStartDate);
        vo.setValidThruEndDate(validThruEndDate);
        vo.setIsDeviceAuth(CheckboxUtil.setSmoother(isDeviceAuth));
        vo.setBranchId(branchId);
        vo.setAvailableStatus(CheckboxUtil.setSmoother((availableStatus.equals(AvailableStatus.利用可能))? true : false));
        vo.setChangeCause(changeCause);

        // 実行
        Oa11030UpdateConverter converter = Oa11030UpdateConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11030UpdateConverter);
        assertThat(converter.getOperatorId()).isEqualTo(operatorId);
        assertThat(converter.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(converter.getOperatorName()).isEqualTo(operatorName);
        assertThat(converter.getMailAddress()).isEqualTo(mailAddress);
        assertThat(converter.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(converter.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(converter.getIsDeviceAuth()).isEqualTo(isDeviceAuth);
        assertThat(converter.getBranchId()).isEqualTo(branchId);
        assertThat(converter.getAvailableStatus()).isEqualTo(availableStatus);
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
    }
}