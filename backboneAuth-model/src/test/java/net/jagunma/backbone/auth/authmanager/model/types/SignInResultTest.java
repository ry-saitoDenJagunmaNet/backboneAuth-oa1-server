package net.jagunma.backbone.auth.authmanager.model.types;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.log.Logger;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInResultTest {

    static final Logger LOGGER = Logger.getLogger(SignInResultTest.class);

    @Test
    @Tag(TestSize.SMALL)
    void codeOf() {
        LOGGER.debug("デバッグメッセージ");
        SignInResult actual = SignInResult.codeOf((short) 1);

        assertThat(actual).isEqualTo(SignInResult.失敗_存在しないオペレーター);

    }
}