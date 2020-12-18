package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SuspendBizTranForStoreDataSourceTest {

    // 実行既定値
    private final Long suspendBizTranId = 12345678L;
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "ＪＡ００６";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "店舗００１";
    private final String subSystemCode = SubSystem.販売_畜産.getCode();
    private final Long bizTranGrpId = 10001L;
    private final String bizTranGrpCode = "ANTG01";
    private final String bizTranGrpName = "データ入力取引グループ";
    private final Integer bizTranGrpRecordVersion = 1;
    private final Long bizTranId = 100001L;
    private final String bizTranCode = "AN0001";
    private final Boolean isCenterBizTran = false;
    private final LocalDate validThruStartDate = LocalDate.of(2010,6,21);
    private final LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private final String bizTranName = "畜産メインメニュー";
    private final Integer bizTranRecordVersion  = 1;
    private final LocalDate suspendStartDate = LocalDate.of(2010,6,21);
    private final LocalDate suspendEndDate = LocalDate.of(9999,12,31);
    private final String suspendReason = "不具合により緊急抑止";

    private Integer actualDeleteRecordVersion;
    private Long actualDeleteSuspendBizTranId;


    // 一時取引抑止データ作成
    private SuspendBizTran createSuspendBizTran(
        Integer recordVersion,
        JaAtMoment jaAtMoment,
        BranchAtMoment branchAtMoment,
        SubSystem subSystem,
        BizTranGrp bizTranGrp,
        BizTran bizTran) {

        return SuspendBizTran.createFrom(
            this.suspendBizTranId,
            this.jaCode,
            this.branchCode,
            this.subSystemCode,
            this.bizTranGrpCode,
            this.bizTranCode,
            this.suspendStartDate,
            this.suspendEndDate,
            this.suspendReason,
            recordVersion,
            jaAtMoment,
            branchAtMoment,
            subSystem,
            bizTranGrp,
            bizTran);
    }
    // JaAtMomentデータ作成
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
    // BranchAtMomentデータ作成
    private BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }
    // 取引グループデータ作成
    private BizTranGrp createBizTranGrp() {
        return BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            bizTranGrpRecordVersion,
            (subSystemCode == null)? null : SubSystem.codeOf(subSystemCode)
        );
    }
    // 取引データ作成
    private BizTran createBizTran() {
        return BizTran.createFrom(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate,
            subSystemCode,
            bizTranRecordVersion,
            (subSystemCode == null)? null : SubSystem.codeOf(subSystemCode)
        );
    }

    // 一時取引抑止Dso作成
    private SuspendBizTranForStoreDataSource createSuspendBizTranForStoreDataSource() {
        return new SuspendBizTranForStoreDataSource(
            // 一時取引抑止Dso作成
            new SuspendBizTranEntityDao() {
                @Override
                public int insert(SuspendBizTranEntity entity) {
                    entity.setRecordVersion(1);
                    return 1;
                }
                @Override
                public int update(SuspendBizTranEntity entity) {
                    return 0;
                }
                @Override
                public int delete(SuspendBizTranEntity entity) {
                    actualDeleteRecordVersion = entity.getRecordVersion();
                    actualDeleteSuspendBizTranId = entity.getSuspendBizTranId();

                    return 0;
                }
                @Override
                public List<SuspendBizTranEntity> findAll(Orders orders) {
                    return null;
                }
                @Override
                public SuspendBizTranEntity findOneBy(SuspendBizTranEntityCriteria criteria) {
                    return null;
                }
                @Override
                public List<SuspendBizTranEntity> findBy(SuspendBizTranEntityCriteria criteria, Orders orders) {
                    return null;
                }
                @Override
                public int countBy(SuspendBizTranEntityCriteria criteria) {
                    return 0;
                }
                @Override
                public int updateExcludeNull(SuspendBizTranEntity entity) {
                    return 0;
                }
                @Override
                public int forceDelete(SuspendBizTranEntityCriteria criteria) {
                    return 0;
                }
                @Override
                public int[] insertBatch(List<SuspendBizTranEntity> entities) {
                    return new int[0];
                }
                @Override
                public int[] updateBatch(List<SuspendBizTranEntity> entities) {
                    return new int[0];
                }
                @Override
                public int[] deleteBatch(List<SuspendBizTranEntity> entities) {
                    return new int[0];
                }
            },
            // 一時取引抑止検索作成
            new SuspendBizTranRepository() {
                @Override
                public SuspendBizTran findOneBy(SuspendBizTranCriteria suspendBizTranCriteria) {
                    return createSuspendBizTran(
                        (suspendBizTranId == null)? null : 1,
                        (jaId == null)? null : createJaAtMoment(),
                        (branchId == null)? null : createBranchAtMoment(),
                        (subSystemCode == null)? null : SubSystem.codeOf(subSystemCode),
                        (bizTranGrpId == null)? null : createBizTranGrp(),
                        (bizTranId == null)? null : createBizTran());
                }

                @Override
                public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {
                    return null;
                }
            });
    }

    /**
     * {@link SuspendBizTranForStoreDataSource#insert(SuspendBizTran)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void insert_test0() {

        // テスト対象クラス生成
        SuspendBizTranForStoreDataSource suspendBizTranForStoreDataSource = createSuspendBizTranForStoreDataSource();

        // 実行値
        SuspendBizTran suspendBizTran = createSuspendBizTran(null,null,null,null,null,null);

        // 期待値
        Integer recordVersion = 1;
        JaAtMoment jaAtMoment = createJaAtMoment();
        BranchAtMoment branchAtMoment = createBranchAtMoment();
        SubSystem subSystem = SubSystem.codeOf(subSystemCode);
        BizTranGrp bizTranGrp = createBizTranGrp();
        BizTran bizTran = createBizTran();
        SuspendBizTran expectedSuspendBizTran = createSuspendBizTran(recordVersion,jaAtMoment,branchAtMoment,subSystem,bizTranGrp,bizTran);

        // 実行
        SuspendBizTran actualSuspendBizTran = suspendBizTranForStoreDataSource.insert(suspendBizTran);

        // 結果検証
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link SuspendBizTranForStoreDataSource#update(SuspendBizTran)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test0() {

        // テスト対象クラス生成
        SuspendBizTranForStoreDataSource suspendBizTranForStoreDataSource = createSuspendBizTranForStoreDataSource();

        // 実行値
        SuspendBizTran suspendBizTran = createSuspendBizTran(1,null,null,null,null,null);

        // 期待値
        Integer recordVersion = 1;
        JaAtMoment jaAtMoment = createJaAtMoment();
        BranchAtMoment branchAtMoment = createBranchAtMoment();
        SubSystem subSystem = SubSystem.codeOf(subSystemCode);
        BizTranGrp bizTranGrp = createBizTranGrp();
        BizTran bizTran = createBizTran();
        SuspendBizTran expectedSuspendBizTran = createSuspendBizTran(recordVersion,jaAtMoment,branchAtMoment,subSystem,bizTranGrp,bizTran);

        // 実行
        SuspendBizTran actualSuspendBizTran = suspendBizTranForStoreDataSource.update(suspendBizTran);

        // 結果検証
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link SuspendBizTranForStoreDataSource#delete(SuspendBizTran)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void delete_test0() {

        // テスト対象クラス生成
        SuspendBizTranForStoreDataSource suspendBizTranForStoreDataSource = createSuspendBizTranForStoreDataSource();

        // 実行値
        SuspendBizTran suspendBizTran = createSuspendBizTran(1,null,null,null,null,null);

        // 期待値
        Integer expectedRecordVersion = 1;
        Long expectedSuspendBizTranId = suspendBizTranId;

        // 実行
        suspendBizTranForStoreDataSource.delete(suspendBizTran);

        // 結果検証
        assertThat(actualDeleteRecordVersion).isEqualTo(expectedRecordVersion);
        assertThat(actualDeleteSuspendBizTranId).isEqualTo(expectedSuspendBizTranId);
    }
}