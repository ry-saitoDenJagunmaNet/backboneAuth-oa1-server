package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11020EntryConverterTest {

    // 実行既定値
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Long branchId = 1L;
    private String changeCause = "新職員の入組による登録";
    private String password = "PaSsWoRd";
    private String confirmPassword = "pAsSwOrD";

    /**
     * {@link Oa11020EntryConverter#with(Oa11020Vo vo)}テスト
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
        Oa11020Vo vo = new Oa11020Vo();
        vo.setOperatorCode6(operatorCode6);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setValidThruStartDate(validThruStartDate);
        vo.setValidThruEndDate(validThruEndDate);
        vo.setBranchId(branchId);
        vo.setChangeCause(changeCause);
        vo.setPassword(password);
        vo.setConfirmPassword(confirmPassword);

        // 実行
        Oa11020EntryConverter converter = Oa11020EntryConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11020EntryConverter);
        assertThat(converter.getOperatorCode6()).isEqualTo(operatorCode6);
        assertThat(converter.getOperatorName()).isEqualTo(operatorName);
        assertThat(converter.getMailAddress()).isEqualTo(mailAddress);
        assertThat(converter.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(converter.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(converter.getBranchId()).isEqualTo(branchId);
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
        assertThat(converter.getPassword()).isEqualTo(password);
        assertThat(converter.getConfirmPassword()).isEqualTo(confirmPassword);
    }
}