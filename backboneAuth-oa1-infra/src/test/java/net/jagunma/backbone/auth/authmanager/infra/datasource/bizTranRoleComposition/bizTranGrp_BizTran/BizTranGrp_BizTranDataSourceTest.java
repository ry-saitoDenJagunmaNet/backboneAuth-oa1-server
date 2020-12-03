package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp_BizTran;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTranDataSourceTest {

    // 実行既定値
    private Long bizTranGrp_BizTranId = 12345678L;
    private Long bizTranGrpId = 10001L;
    private String bizTranGrpCode = "ANTG01";
    private String bizTranGrpName = "データ入力取引グループ";
    private Long bizTranId = 100001L;
    private String bizTranCode = "AN0001";
    private String bizTranName = "畜産メインメニュー";
    private Boolean isCenterBizTran = false;
    private LocalDate validThruStartDate = LocalDate.of(2010,6,21);
    private LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Long createdBy = 18L;
    private LocalDateTime createdAt = LocalDateTime.of(2020,11,1,8,31,12);
    private String createdIpAddress = "001.001.001.001";
    private Long updatedBy = 19L;
    private LocalDateTime updatedAt = LocalDateTime.of(2020,11,2,10,1,2);
    private String updatedIpAddress = "001.001.001.002";
    private Integer recordVersion = 1;

    // 取引グループ_取引割当Daoの作成
    private BizTranGrp_BizTranEntityDao createBizTranGrp_BizTranEntityDao() {
        return new BizTranGrp_BizTranEntityDao() {
            @Override
            public BizTranGrp_BizTranEntity findOneBy(BizTranGrp_BizTranEntityCriteria criteria) {
                return createBizTranGrp_BizTranEntity();
            }
            @Override
            public List<BizTranGrp_BizTranEntity> findBy(BizTranGrp_BizTranEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public List<BizTranGrp_BizTranEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public int countBy(BizTranGrp_BizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranGrp_BizTranEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranGrp_BizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranGrp_BizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranGrp_BizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranGrp_BizTranEntity> entities) {
                return new int[0];
            }
        };
    }
    // 取引グループRepositoryの作成
    private BizTranGrpRepository createBizTranGrpRepository() {
        return new BizTranGrpRepository() {
            @Override
            public BizTranGrp findOneBy(BizTranGrpCriteria bizTranGrpCriteria) {
                return createBizTranGrp();
            }
        };
    }
    // 取引Repositoryの作成
    private BizTranRepository createBizTranRepository() {
        return new BizTranRepository() {
            @Override
            public BizTran findOneBy(BizTranCriteria bizTranCriteria) {
                return createBizTran();
            }
        };
    }
    // 取引グループ_取引割当データ作成
    private BizTranGrp_BizTranEntity createBizTranGrp_BizTranEntity() {
        BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
        entity.setBizTranGrp_BizTranId(bizTranGrp_BizTranId);
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranId(bizTranId);
        entity.setSubSystemCode(subSystemCode);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // 取引グループデータ作成
    private BizTranGrp createBizTranGrp() {
        return BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode));
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
            recordVersion,
            SubSystem.codeOf(subSystemCode));
    }

    /**
     * {@link BizTranGrp_BizTranDataSource#findOneBy(BizTranGrp_BizTranCriteria)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneBy_test0() {

        // 実行値
        bizTranGrp_BizTranId = null;
        bizTranGrpId = null;
        bizTranGrpCode = null;
        bizTranGrpName = null;
        bizTranId = null;
        bizTranCode = null;
        bizTranName = null;
        isCenterBizTran = null;
        validThruStartDate = null;
        validThruEndDate = null;
        subSystemCode = null;
        createdBy = null;
        createdAt = null;
        createdIpAddress = null;
        updatedBy = null;
        updatedAt = null;
        updatedIpAddress = null;
        recordVersion = null;
        BizTranGrp_BizTranCriteria criteria = new BizTranGrp_BizTranCriteria();

        // テスト対象クラス生成
        BizTranGrp_BizTranDataSource bizTranGrp_BizTranDataSource = new BizTranGrp_BizTranDataSource(
            createBizTranGrp_BizTranEntityDao(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // 期待値
        BizTranGrp_BizTran expectedBizTranGrp_BizTran = BizTranGrp_BizTran.createFrom(
            bizTranGrp_BizTranId,
            bizTranGrpId,
            bizTranId,
            subSystemCode,
            recordVersion,
            createBizTranGrp(),
            createBizTran(),
            SubSystem.codeOf(subSystemCode));

        // 実行
        BizTranGrp_BizTran actualBizTranGrp_BizTran = bizTranGrp_BizTranDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualBizTranGrp_BizTran).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTran);
    }

    /**
     * {@link BizTranGrp_BizTranDataSource#findOneBy(BizTranGrp_BizTranCriteria)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneBy_test1() {

        // 実行値
        BizTranGrp_BizTranCriteria criteria = new BizTranGrp_BizTranCriteria();

        // テスト対象クラス生成
        BizTranGrp_BizTranDataSource bizTranGrp_BizTranDataSource = new BizTranGrp_BizTranDataSource(
            createBizTranGrp_BizTranEntityDao(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // 期待値
        BizTranGrp_BizTran expectedBizTranGrp_BizTran = BizTranGrp_BizTran.createFrom(
            bizTranGrp_BizTranId,
            bizTranGrpId,
            bizTranId,
            subSystemCode,
            recordVersion,
            createBizTranGrp(),
            createBizTran(),
            SubSystem.codeOf(subSystemCode));

        // 実行
        BizTranGrp_BizTran actualBizTranGrp_BizTran = bizTranGrp_BizTranDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualBizTranGrp_BizTran).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTran);
    }
}