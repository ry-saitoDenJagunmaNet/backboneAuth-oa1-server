package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11020EntryConverterTest {

    /**
     * {@link Oa11020EntryConverter#with(Oa11020Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {
        // 事前準備
        String operatorCode6 = "123456";
        String operatorName = "オペレーター名";
        String mailAddress = "test@den.jagunma.net";
        LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
        LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
        Long tempoId = 1L;
        String changeCause = "新職員の入組による登録";
        String password = "PaSsWoRd";
        String confirmPassword = "pAsSwOrD";

        // 実行値
        Oa11020Vo vo = new Oa11020Vo();
        vo.setOperatorCode6(operatorCode6);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setExpirationStartDate(expirationStartDate);
        vo.setExpirationEndDate(expirationEndDate);
        vo.setTempoId(tempoId);
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
        assertThat(converter.getExpirationStartDate()).isEqualTo(expirationStartDate);
        assertThat(converter.getExpirationEndDate()).isEqualTo(expirationEndDate);
        assertThat(converter.getTempoId()).isEqualTo(tempoId);
        assertThat(converter.getChangeCause()).isEqualTo(changeCause);
        assertThat(converter.getPassword()).isEqualTo(password);
        assertThat(converter.getConfirmPassword()).isEqualTo(confirmPassword);
    }
}