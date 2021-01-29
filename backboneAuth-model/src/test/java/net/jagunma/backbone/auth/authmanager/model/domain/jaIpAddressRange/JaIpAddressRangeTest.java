package net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JaIpAddressRangeTest {

    // 実行既定値
    private Long jaIpAddressRangeId = 123456789L;
    private String jaCode = "006";
    private String ipAddressRange = "001.001.001.001";
    private LocalDate validThruStartDate = LocalDate.of(2021,1,19);
    private LocalDate validThruEndDate = LocalDate.of(9999,12,21);
    private JaAtMoment jaAtMoment = createJaAtMoment();
    private Long jaId = 6L;
    private String jaName = "ＪＡ００６";

    // JaAtMomentデータの作成
    private JaAtMoment createJaAtMoment() {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    /**
     * {@link JaIpAddressRange#createFrom(Long, String, String, LocalDate, LocalDate, JaAtMoment)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行
        JaIpAddressRange jaIpAddressRange = JaIpAddressRange.createFrom(
            jaIpAddressRangeId,
            jaCode,
            ipAddressRange,
            validThruStartDate,
            validThruEndDate,
            jaAtMoment);

        // 結果検証
        assertTrue(jaIpAddressRange instanceof JaIpAddressRange);
        assertThat(jaIpAddressRange.getJaIpAddressRangeId()).isEqualTo(jaIpAddressRangeId);
        assertThat(jaIpAddressRange.getJaCode()).isEqualTo(jaCode);
        assertThat(jaIpAddressRange.getIpAddressRange()).isEqualTo(ipAddressRange);
        assertThat(jaIpAddressRange.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(jaIpAddressRange.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(jaIpAddressRange.getJaAtMoment()).usingRecursiveComparison().isEqualTo(jaAtMoment);
    }

    /**
     * {@link JaIpAddressRange#createFrom(Long, String, String, LocalDate, LocalDate, JaAtMoment)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test1() {

        // 実行値
        jaIpAddressRangeId = null;
        jaCode = null;
        ipAddressRange = null;
        validThruStartDate = null;
        validThruEndDate = null;
        jaAtMoment = null;

        // 実行
        JaIpAddressRange jaIpAddressRange = JaIpAddressRange.createFrom(
            jaIpAddressRangeId,
            jaCode,
            ipAddressRange,
            validThruStartDate,
            validThruEndDate,
            jaAtMoment);

        // 結果検証
        assertTrue(jaIpAddressRange instanceof JaIpAddressRange);
        assertThat(jaIpAddressRange.getJaIpAddressRangeId()).isEqualTo(jaIpAddressRangeId);
        assertThat(jaIpAddressRange.getJaCode()).isEqualTo(jaCode);
        assertThat(jaIpAddressRange.getIpAddressRange()).isEqualTo(ipAddressRange);
        assertThat(jaIpAddressRange.getValidThruStartDate()).isEqualTo(validThruStartDate);
        assertThat(jaIpAddressRange.getValidThruEndDate()).isEqualTo(validThruEndDate);
        assertThat(jaIpAddressRange.getJaAtMoment()).usingRecursiveComparison().isEqualTo(jaAtMoment);
    }
}