package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SystemAvailableTimeZonesTest {

    /**
     * {@link SystemAvailableTimeZones#createFrom(Collection)} のテスト
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
        List<SystemAvailableTimeZone> list = newArrayList();
        list.add(SystemAvailableTimeZone.createFrom(1L,SubSystem.販売_畜産.getCode(),(short) DayOfWeek.SUNDAY.getValue(),null,null,1,SubSystem.販売_畜産));
        list.add(SystemAvailableTimeZone.createFrom(2L,SubSystem.販売_畜産.getCode(),(short) DayOfWeek.MONDAY.getValue(),"8:00","20:00",1,SubSystem.販売_畜産));
        list.add(SystemAvailableTimeZone.createFrom(3L,SubSystem.販売_畜産.getCode(),(short) DayOfWeek.SATURDAY.getValue(),"0:00","0:00",1,SubSystem.販売_畜産));

        // 実行
        SystemAvailableTimeZones systemAvailableTimeZones = SystemAvailableTimeZones.createFrom(list);

        // 結果検証
        assertThat(systemAvailableTimeZones.getValues().size()).isEqualTo(systemAvailableTimeZones.getValues().size());
        for(int i = 0; i < systemAvailableTimeZones.getValues().size(); i++) {
            assertThat(systemAvailableTimeZones.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}