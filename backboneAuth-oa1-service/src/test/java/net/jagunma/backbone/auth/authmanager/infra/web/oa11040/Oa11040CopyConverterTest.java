package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.objects2.Objects2.toStringHelper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040CopyConverterTest {

    // 実行既定値
    private Long targetOperatorId = 123456L;
    private Long selectedOperatorId = 234567L;
    private Long signInOperatorId = 345678L;

    /**
     * {@link Oa11040CopyConverter#with(Long targetOperatorId, Long selectedOperatorId, Long signInOperatorId)}テスト
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
        // 実行
//        Oa11040CopyConverter converter = Oa11040CopyConverter.with(targetOperatorId, selectedOperatorId, signInOperatorId);

        // 期待値
        LongCriteria expectedCriteria = new LongCriteria();
        expectedCriteria.getIncludes().addAll(new ArrayList<Long>(Arrays.asList(targetOperatorId, selectedOperatorId, signInOperatorId)));

        // 結果検証
//        assertTrue(converter instanceof Oa11040CopyConverter);
//        assertThat(converter.getTargetOperatorId()).isEqualTo(targetOperatorId);
//        assertThat(converter.getSelectedOperatorId()).isEqualTo(selectedOperatorId);
//        assertThat(converter.getSignInOperatorId()).isEqualTo(signInOperatorId);
//        assertThat(toStringHelper(converter.getOperatorIdCriteria()).defaultConfig().toString()).isEqualTo(toStringHelper(expectedCriteria).defaultConfig().toString());
    }
}