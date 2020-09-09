package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreCalendarValidatorTest {

    /**
     * {@link StoreCalendarValidator}のテスト
     *
     * ・ リクエストが未設定の場合、例外が発生することを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void Requestが未設定の場合の確認() {
        try {
            //　実行
            StoreCalendarValidator.with(null).validate();
        } catch (GunmaRuntimeException e) {
            // 結果確認
            assertThat(e.getMessageCode()).isEqualTo("EOA13004");
            assertThat(e.getSimpleMessage()).isEqualTo("リクエストが不正です。");
            return;
        }
    }
}