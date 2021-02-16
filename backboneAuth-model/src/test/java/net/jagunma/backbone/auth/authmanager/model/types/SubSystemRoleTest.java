package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SubSystemRoleTest {

    /**
     * {@link SubSystemRole#getCode()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・code値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getCode_test0() {

        // 実行 & 結果検証
        assertThat(SubSystemRole.JA管理者.getCode()).isEqualTo("JaAdmin");
        assertThat(SubSystemRole.業務統括者_購買.getCode()).isEqualTo("KbManager");
        assertThat(SubSystemRole.業務統括者_販売_青果.getCode()).isEqualTo("YsManager");
        assertThat(SubSystemRole.業務統括者_販売_花卉.getCode()).isEqualTo("YfManager");
        assertThat(SubSystemRole.業務統括者_販売_米.getCode()).isEqualTo("HkManager");
        assertThat(SubSystemRole.業務統括者_販売_麦.getCode()).isEqualTo("HmManager");
        assertThat(SubSystemRole.業務統括者_販売_畜産.getCode()).isEqualTo("AnManager");
    }

    /**
     * {@link SubSystemRole#getDisplayName()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・displayName
     */
    @Test
    @Tag(TestSize.SMALL)
    void getDisplayName_test0() {

        // 実行 & 結果検証
        assertThat(SubSystemRole.JA管理者.getDisplayName()).isEqualTo("JA管理者");
        assertThat(SubSystemRole.業務統括者_購買.getDisplayName()).isEqualTo("業務統括者（購買）");
        assertThat(SubSystemRole.業務統括者_販売_青果.getDisplayName()).isEqualTo("業務統括者（販売・青果）");
        assertThat(SubSystemRole.業務統括者_販売_花卉.getDisplayName()).isEqualTo("業務統括者（販売・花卉）");
        assertThat(SubSystemRole.業務統括者_販売_米.getDisplayName()).isEqualTo("業務統括者（販売・米）");
        assertThat(SubSystemRole.業務統括者_販売_麦.getDisplayName()).isEqualTo("業務統括者（販売・麦）");
        assertThat(SubSystemRole.業務統括者_販売_畜産.getDisplayName()).isEqualTo("業務統括者（販売・畜産）");
    }

    /**
     * {@link SubSystemRole#getSubSystemList()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・subSystemList
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSubSystemList_test0() {

        // 実行 & 結果検証
        assertThat(SubSystemRole.JA管理者.getSubSystemList()).usingRecursiveComparison().isEqualTo(newArrayList(SubSystem.購買, SubSystem.販売_青果, SubSystem.販売_花卉, SubSystem.販売_米, SubSystem.販売_麦, SubSystem.販売_畜産));
        assertThat(SubSystemRole.業務統括者_購買.getSubSystemList()).isEqualTo(newArrayList(SubSystem.購買));
        assertThat(SubSystemRole.業務統括者_販売_青果.getSubSystemList()).isEqualTo(newArrayList(SubSystem.販売_青果));
        assertThat(SubSystemRole.業務統括者_販売_花卉.getSubSystemList()).isEqualTo(newArrayList(SubSystem.販売_花卉));
        assertThat(SubSystemRole.業務統括者_販売_米.getSubSystemList()).isEqualTo(newArrayList(SubSystem.販売_米));
        assertThat(SubSystemRole.業務統括者_販売_麦.getSubSystemList()).isEqualTo(newArrayList(SubSystem.販売_麦));
        assertThat(SubSystemRole.業務統括者_販売_畜産.getSubSystemList()).isEqualTo(newArrayList(SubSystem.販売_畜産));
    }

    /**
     * {@link SubSystemRole#getValidList()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・list値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidList_test0() {

        // 期待値
        List<SubSystemRole> expected = newArrayList();
        expected.add(SubSystemRole.JA管理者);
        expected.add(SubSystemRole.業務統括者_購買);
        expected.add(SubSystemRole.業務統括者_販売_青果);
        expected.add(SubSystemRole.業務統括者_販売_花卉);
        expected.add(SubSystemRole.業務統括者_販売_米);
        expected.add(SubSystemRole.業務統括者_販売_麦);
        expected.add(SubSystemRole.業務統括者_販売_畜産);

        // 実行
        List<SubSystemRole> actual = SubSystemRole.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SubSystemRole#getValidValues()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・SubSystemRoleの値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidValues_test0() {

        // 期待値
        List<SubSystemRole> list = newArrayList();
        list.add(SubSystemRole.JA管理者);
        list.add(SubSystemRole.業務統括者_購買);
        list.add(SubSystemRole.業務統括者_販売_青果);
        list.add(SubSystemRole.業務統括者_販売_花卉);
        list.add(SubSystemRole.業務統括者_販売_米);
        list.add(SubSystemRole.業務統括者_販売_麦);
        list.add(SubSystemRole.業務統括者_販売_畜産);
        SubSystemRole expected[] = list.toArray(new SubSystemRole[list.size()]);

        // 実行
        SubSystemRole actual[] = SubSystemRole.getValidValues();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SubSystemRole#codeOf(String)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・codeでのenum取得
     */
    @Test
    @Tag(TestSize.SMALL)
    void codeOf_test0() {

        // 実行 & 結果検証
        assertThat(SubSystemRole.codeOf("JaAdmin")).isEqualTo(SubSystemRole.JA管理者);
        assertThat(SubSystemRole.codeOf("KbManager")).isEqualTo(SubSystemRole.業務統括者_購買);
        assertThat(SubSystemRole.codeOf("YsManager")).isEqualTo(SubSystemRole.業務統括者_販売_青果);
        assertThat(SubSystemRole.codeOf("YfManager")).isEqualTo(SubSystemRole.業務統括者_販売_花卉);
        assertThat(SubSystemRole.codeOf("HkManager")).isEqualTo(SubSystemRole.業務統括者_販売_米);
        assertThat(SubSystemRole.codeOf("HmManager")).isEqualTo(SubSystemRole.業務統括者_販売_麦);
        assertThat(SubSystemRole.codeOf("AnManager")).isEqualTo(SubSystemRole.業務統括者_販売_畜産);
        assertThat(SubSystemRole.codeOf("")).isEqualTo(SubSystemRole.UnKnown);
        assertThat(SubSystemRole.codeOf("XX")).isEqualTo(SubSystemRole.UnKnown);
    }
}