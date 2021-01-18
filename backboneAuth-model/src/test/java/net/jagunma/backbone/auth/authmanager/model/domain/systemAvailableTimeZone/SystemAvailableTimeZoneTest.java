package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SystemAvailableTimeZoneTest {

    // 実行 ＆ 期待 既定値
    private Long systemAvailableTimeZoneId = 123456789L;
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Short dayOfWeek = (short) LocalDate.now().getDayOfWeek().getValue();
    private String startTime = "8:00";
    private String endTime = "20:00";;
    private Integer recordVersion = 1;
    private SubSystem subSystem = SubSystem.販売_畜産;

    /**
     * {@link  SystemAvailableTimeZone#createFrom(Long, String, Short, String, String, Integer, SubSystem)} テスト
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
        SystemAvailableTimeZone systemAvailableTimeZone = SystemAvailableTimeZone.createFrom(
            systemAvailableTimeZoneId,
            subSystemCode,
            dayOfWeek,
            startTime,
            endTime,
            recordVersion,
            subSystem);

        // 結果検証
        assertTrue(systemAvailableTimeZone instanceof SystemAvailableTimeZone);
        assertThat(systemAvailableTimeZone.getSystemAvailableTimeZoneId()).isEqualTo(systemAvailableTimeZoneId);
        assertThat(systemAvailableTimeZone.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(systemAvailableTimeZone.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(systemAvailableTimeZone.getStartTime()).isEqualTo(startTime);
        assertThat(systemAvailableTimeZone.getEndTime()).isEqualTo(endTime);
        assertThat(systemAvailableTimeZone.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(systemAvailableTimeZone.getSubSystem()).isEqualTo(subSystem);
    }

    /**
     * {@link  SystemAvailableTimeZone#createFrom(Long, String, Short, String, String, Integer, SubSystem)} テスト
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
        systemAvailableTimeZoneId = null;
        subSystemCode = null;
        dayOfWeek = null;
        startTime = null;
        endTime = null;;
        recordVersion = null;
        subSystem = null;

        // 実行
        SystemAvailableTimeZone systemAvailableTimeZone = SystemAvailableTimeZone.createFrom(
            systemAvailableTimeZoneId,
            subSystemCode,
            dayOfWeek,
            startTime,
            endTime,
            recordVersion,
            subSystem);

        // 結果検証
        assertTrue(systemAvailableTimeZone instanceof SystemAvailableTimeZone);
        assertThat(systemAvailableTimeZone.getSystemAvailableTimeZoneId()).isEqualTo(systemAvailableTimeZoneId);
        assertThat(systemAvailableTimeZone.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(systemAvailableTimeZone.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(systemAvailableTimeZone.getStartTime()).isEqualTo(startTime);
        assertThat(systemAvailableTimeZone.getEndTime()).isEqualTo(endTime);
        assertThat(systemAvailableTimeZone.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(systemAvailableTimeZone.getSubSystem()).isEqualTo(subSystem);
    }
}