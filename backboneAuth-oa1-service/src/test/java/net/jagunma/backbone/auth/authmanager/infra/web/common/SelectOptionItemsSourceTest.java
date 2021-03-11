package net.jagunma.backbone.auth.authmanager.infra.web.common;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SelectOptionItemsSourceTest {

    // 実行既定値

    // JaAtMomentリストデータ作成
    private List<JaAtMoment> createJaAtMomentList() {
        List<JaAtMoment> list = newArrayList();
        list.add(createJaAtMoment(2L,"002","ＪＡ００２"));
        list.add(createJaAtMoment(6L,"006","ＪＡ００６"));
        list.add(createJaAtMoment(23L,"070","ＪＡ０７０"));
        return list;
    }
    // JaAtMomentデータ作成
    private JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {
        if (Strings2.isEmpty(jaCode)) { return null; }
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
    // BranchAtMomentリストデータ作成
    private List<BranchAtMoment> createBranchAtMomentList() {
        List<BranchAtMoment> list = newArrayList();
        list.add(createBranchAtMoment(33L,"001", "店舗００１", 2L,"002","ＪＡ００２"));
        list.add(createBranchAtMoment(34L,"002", "店舗００２", 2L,"002","ＪＡ００２"));
        list.add(createBranchAtMoment(35L,"003", "店舗００３", 2L,"002","ＪＡ００２"));
        return list;
    }
    // BranchAtMomentデータ作成
    private BranchAtMoment createBranchAtMoment(Long branchId, String branchCode, String branchName, Long jaId, String jaCode, String jaName) {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment(jaId, jaCode, jaName))
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }
    // 取引グループリストデータ作成
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10002L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10003L,"ANTG03","マスタ取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引リストデータ作成
    private Collection<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100002L,"AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100003L,"AN1210","仕切入力",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(JasAtMoment)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_jasAtMoment_test0() {

        // 実行値
        JasAtMoment jasAtMoment = JasAtMoment.of(createJaAtMomentList());

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        expected.add(SelectOptionItemSource.empty());
        for(JaAtMoment jaAtMoment : createJaAtMomentList()) {
            expected.add(new SelectOptionItemSource(
                jaAtMoment.getIdentifier(),
                jaAtMoment.getJaAttribute().getJaCode().getValue(),
                jaAtMoment.getJaAttribute().getName()
            ));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(jasAtMoment);

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(BranchesAtMoment)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_branchesAtMoment_test0() {

        // 実行値
        BranchesAtMoment branchesAtMoment = BranchesAtMoment.of(createBranchAtMomentList());

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        expected.add(SelectOptionItemSource.empty());
        for(BranchAtMoment branchAtMoment : createBranchAtMomentList()) {
            expected.add(new SelectOptionItemSource(
                branchAtMoment.getIdentifier(),
                branchAtMoment.getBranchAttribute().getBranchCode().getValue(),
                branchAtMoment.getBranchAttribute().getName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(branchesAtMoment);

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(BizTranGrps )}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_bizTranGrps_test0() {

        // 実行値
        BizTranGrps bizTranGrps = BizTranGrps.createFrom(createBizTranGrpList());

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        expected.add(SelectOptionItemSource.empty());
        for(BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expected.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(),
                bizTranGrp.getBizTranGrpCode(),
                bizTranGrp.getBizTranGrpName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(BizTranGrps.createFrom(createBizTranGrpList()));

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(BizTranGrps, Boolean)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_bizTranGrps_test1() {

        // 実行値
        BizTranGrps bizTranGrps = BizTranGrps.createFrom(createBizTranGrpList());

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        for(BizTranGrp bizTranGrp : createBizTranGrpList()) {
            expected.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(),
                bizTranGrp.getBizTranGrpCode(),
                bizTranGrp.getBizTranGrpName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(BizTranGrps.createFrom(createBizTranGrpList()), false);

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(BizTrans)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_bizTrans_test0() {

        // 実行値
        BizTrans bizTrans = BizTrans.createFrom(createBizTranList());

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        expected.add(SelectOptionItemSource.empty());
        for(BizTran bizTran : createBizTranList()) {
            expected.add(new SelectOptionItemSource(
                bizTran.getBizTranId(),
                bizTran.getBizTranCode(),
                bizTran.getBizTranName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(BizTrans.createFrom(createBizTranList()));

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(BizTrans, Boolean)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_bizTrans_test1() {

        // 実行値
        BizTrans bizTrans = BizTrans.createFrom(createBizTranList());

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        for(BizTran bizTran : createBizTranList()) {
            expected.add(new SelectOptionItemSource(
                bizTran.getBizTranId(),
                bizTran.getBizTranCode(),
                bizTran.getBizTranName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(BizTrans.createFrom(createBizTranList()), false);

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(SubSystem[])}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_subSystem_test0() {

        // 実行値
        SubSystem subSystems[] = SubSystem.getValidValues();

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        expected.add(SelectOptionItemSource.empty());
        for(SubSystem subSystem : SubSystem.getValidValues()) {
            if (subSystem.name().equals("UnKnown")) { continue; }
            expected.add(new SelectOptionItemSource(
                null,
                subSystem.getCode(),
                subSystem.getDisplayName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(SubSystem.getValidValues());

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SelectOptionItemsSource#createFrom(SubSystem[])}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_subSystemRole_test0() {

        // 実行値
        SubSystemRole subSystemRoles[] = SubSystemRole.getValidValues();

        // 期待値
        List<SelectOptionItemSource> expected = newArrayList();
        expected.add(SelectOptionItemSource.empty());
        for(SubSystemRole subSystemRole : SubSystemRole.getValidValues()) {
            if (subSystemRole.name().equals("UnKnown")) { continue; }
            expected.add(new SelectOptionItemSource(
                null,
                subSystemRole.getCode(),
                subSystemRole.getDisplayName()));
        }

        // 実行
        SelectOptionItemsSource actual = SelectOptionItemsSource.createFrom(SubSystemRole.getValidValues());

        // 結果検証
        assertThat(actual.getValue()).usingRecursiveComparison().isEqualTo(expected);
    }

}