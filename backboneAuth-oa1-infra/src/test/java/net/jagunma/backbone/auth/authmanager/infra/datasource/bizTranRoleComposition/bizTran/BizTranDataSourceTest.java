package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
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

    // 取引データの作成
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
    // 取引リストデータの作成
    private List<BizTranEntity> createBizTranEntityList() {
        List<BizTranEntity> list = newArrayList();
        list.add(createBizTranEntity(1L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),"AN",null,null,null,null,null,null,1));
        list.add(createBizTranEntity(2L,"AN1110","前日処理照会",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),"AN",null,null,null,null,null,null,1));
        list.add(createBizTranEntity(3L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),"AN",null,null,null,null,null,null,1));
        return list;
    }
    // 取引データの作成
    private BizTranEntity createBizTranEntity(
        Long bizTranId,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

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

    // 取引Daoの作成
    private BizTranEntityDao createBizTranEntityDao() {
        return new BizTranEntityDao() {
            @Override
            public BizTranEntity findOneBy(BizTranEntityCriteria criteria) {
                return createBizTranEntity();
            }
            @Override
            public List<BizTranEntity> findBy(BizTranEntityCriteria criteria, Orders orders) {
                return createBizTranEntityList();
            }
            @Override
            public List<BizTranEntity> findAll(Orders orders) {
                return createBizTranEntityList();
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

    /**
     * {@link BizTranDataSource#findOneByCode(String)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneByCode_test0() {

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
        BizTran actualBizTran = bizTranDataSource.findOneByCode(bizTranCode);

        // 結果検証
        assertThat(actualBizTran).usingRecursiveComparison().isEqualTo(expectedBizTran);
    }

    /**
     * {@link BizTranDataSource#findOneByCode(String)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneByCode_test1() {

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
        BizTran actualBizTran = bizTranDataSource.findOneByCode(bizTranCode);

        // 結果検証
        assertThat(actualBizTran).usingRecursiveComparison().isEqualTo(expectedBizTran);
    }

    /**
     * {@link BizTranDataSource#selectBy(BizTranCriteria,Orders)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // 実行値
        BizTranCriteria criteria = new BizTranCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        BizTranDataSource bizTranDataSource = new BizTranDataSource(createBizTranEntityDao());

        // 期待値
        List<BizTran> expectedBizTranList = newArrayList();
        for(BizTranEntity entity : createBizTranEntityList()) {
            expectedBizTranList.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTrans actualBizTrans = bizTranDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualBizTrans.getValues().size(); i++) {
            assertThat(actualBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranList.get(i));
        }
    }

    /**
     * {@link BizTranDataSource#selectAll(Orders)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectAll_test0() {

        // 実行値
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        BizTranDataSource bizTranDataSource = new BizTranDataSource(createBizTranEntityDao());

        // 期待値
        List<BizTran> expectedBizTranList = newArrayList();
        for(BizTranEntity entity : createBizTranEntityList()) {
            expectedBizTranList.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTrans actualBizTrans = bizTranDataSource.selectAll(orders);

        // 結果検証
        for(int i = 0; i < actualBizTrans.getValues().size(); i++) {
            assertThat(actualBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranList.get(i));
        }
    }
}