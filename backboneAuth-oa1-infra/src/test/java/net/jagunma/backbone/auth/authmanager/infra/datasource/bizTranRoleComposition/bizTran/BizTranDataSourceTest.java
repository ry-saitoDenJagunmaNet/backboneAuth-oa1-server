package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTran;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranDataSourceTest {

    // 実行既定値
    private Long bizTranId = 12345678L;
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

    // 取引Daoの作成
    private BizTranEntityDao createBizTranEntityDao() {
        return new BizTranEntityDao() {
            @Override
            public BizTranEntity findOneBy(BizTranEntityCriteria criteria) {
                return createBizTranEntity();
            }
            @Override
            public List<BizTranEntity> findBy(BizTranEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public List<BizTranEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public int countBy(BizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranEntity> entities) {
                return new int[0];
            }
        };
    }
    // 取引データ作成
    private BizTranEntity createBizTranEntity() {
        BizTranEntity entity = new BizTranEntity();
        entity.setBizTranId(bizTranId);
        entity.setBizTranCode(bizTranCode);
        entity.setBizTranName(bizTranName);
        entity.setIsCenterBizTran(isCenterBizTran);
        entity.setValidThruStartDate(validThruStartDate);
        entity.setValidThruEndDate(validThruEndDate);
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


    /**
     * {@link BizTranDataSource#findOneBy(BizTranCriteria)}のテスト
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
        bizTranId = 12345678L;
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
        BizTranCriteria criteria = new BizTranCriteria();

        // テスト対象クラス生成
        BizTranDataSource bizTranDataSource = new BizTranDataSource(createBizTranEntityDao());

        // 期待値
        BizTran expectedBizTran = BizTran.createFrom(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode));

        // 実行
        BizTran actualBizTran = bizTranDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualBizTran).usingRecursiveComparison().isEqualTo(expectedBizTran);
    }

    /**
     * {@link BizTranDataSource#findOneBy(BizTranCriteria)}のテスト
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
        BizTranCriteria criteria = new BizTranCriteria();

        // テスト対象クラス生成
        BizTranDataSource bizTranDataSource = new BizTranDataSource(createBizTranEntityDao());

        // 期待値
        BizTran expectedBizTran = BizTran.createFrom(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode));

        // 実行
        BizTran actualBizTran = bizTranDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualBizTran).usingRecursiveComparison().isEqualTo(expectedBizTran);
    }
}