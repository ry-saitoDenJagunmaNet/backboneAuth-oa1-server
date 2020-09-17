package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.Oa12060StoreDetailsConverter;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreCalendarValidatorTest {

    /**
     * {@link StoreCalendarValidator#validate()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test0() {

        // 事前準備
        List<Oa12060StoreDetailsConverter> list = newArrayList();
        list.add(Oa12060StoreDetailsConverter.with(1l, CalendarType.経済システム稼働カレンダー,true,1));
        list.add(Oa12060StoreDetailsConverter.with(2l, CalendarType.信用カレンダー,false,1));
        list.add(Oa12060StoreDetailsConverter.with(3l, CalendarType.広域物流カレンダー,true,1));

        // 実行値
        CalendarStoreRequest request = new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return list;
            }
        };

        assertThatCode(()->
            // 実行
            StoreCalendarValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link StoreCalendarValidator#validate()}のテスト
     *  ●パターン
     *    リクエストのカレンダー詳細が0件のテスト
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test1() {

        // 事前準備
        List<Oa12060StoreDetailsConverter> list = newArrayList();

        // 実行値
        CalendarStoreRequest request = new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return list;
            }
        };

        assertThatCode(()->
            // 実行
            StoreCalendarValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link StoreCalendarValidator#validate()}のテスト
     *  ●パターン
     *    リクエストのカレンダー詳細がnullのテスト
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test2() {

        // 事前準備
        List<Oa12060StoreDetailsConverter> list = null;

        // 実行値
        CalendarStoreRequest request = new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return list;
            }
        };

        assertThatCode(()->
            // 実行
            StoreCalendarValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link StoreCalendarValidator#validate()}のテスト
     *  ●パターン
     *    リクエストがnullのテスト
     *
     *  ●検証事項
     *  ・ エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test3() {

        // 事前準備
        CalendarStoreRequest request = null;

        assertThatThrownBy(()->
            // 実行
            StoreCalendarValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }
}