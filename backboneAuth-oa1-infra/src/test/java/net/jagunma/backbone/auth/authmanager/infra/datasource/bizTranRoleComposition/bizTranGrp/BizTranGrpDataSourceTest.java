package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrpDataSourceTest {

    // 実行既定値
    private Long bizTranGrpId = 12345678L;
    private String bizTranGrpCode = "ANTG01";
    private String bizTranGrpName = "データ入力取引グループ";
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Long createdBy = 18L;
    private LocalDateTime createdAt = LocalDateTime.of(2020,11,1,8,31,12);
    private String createdIpAddress = "001.001.001.001";
    private Long updatedBy = 19L;
    private LocalDateTime updatedAt = LocalDateTime.of(2020,11,2,10,1,2);
    private String updatedIpAddress = "001.001.001.002";
    private Integer recordVersion = 1;

    // 取引グループDaoの作成
    private BizTranGrpEntityDao createBizTranGrpEntityDao() {
        return new BizTranGrpEntityDao() {
            @Override
            public BizTranGrpEntity findOneBy(BizTranGrpEntityCriteria criteria) {
                return createBizTranGrpEntity();
            }
            @Override
            public List<BizTranGrpEntity> findBy(BizTranGrpEntityCriteria criteria, Orders orders) {
                return createBizTranGrpEntityList();
            }
            @Override
            public List<BizTranGrpEntity> findAll(Orders orders) {
                return createBizTranGrpEntityList();
            }
            @Override
            public int countBy(BizTranGrpEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranGrpEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranGrpEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranGrpEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranGrpEntity> entities) {
                return new int[0];
            }
        };
    }
    // 取引グループデータ作成
    private BizTranGrpEntity createBizTranGrpEntity() {
        BizTranGrpEntity entity = new BizTranGrpEntity();
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranGrpCode(bizTranGrpCode);
        entity.setBizTranGrpName(bizTranGrpName);
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
    // 取引グループリストデータ作成
    private List<BizTranGrpEntity> createBizTranGrpEntityList() {
        List<BizTranGrpEntity> list = newArrayList();
        list.add(createBizTranGrpEntity(1L,"ANTG01","データ入力取引グループ","AN",null,null,null,null,null,null,1));
        list.add(createBizTranGrpEntity(2L,"ANTG02","精算取引グループ","AN",null,null,null,null,null,null,1));
        list.add(createBizTranGrpEntity(3L,"ANTG10","センター維持管理グループ","AN",null,null,null,null,null,null,1));
        return list;
    }
    // 取引グループデータ作成
    private BizTranGrpEntity createBizTranGrpEntity(
        Long bizTranGrpId,
        String bizTranGrpCode,
        String bizTranGrpName,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        BizTranGrpEntity entity = new BizTranGrpEntity();
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranGrpCode(bizTranGrpCode);
        entity.setBizTranGrpName(bizTranGrpName);
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
     * {@link BizTranGrpDataSource#findOneBy(BizTranGrpCriteria)}のテスト
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
        bizTranGrpId = null;
        bizTranGrpCode = null;
        bizTranGrpName = null;
        subSystemCode = null;
        createdBy = null;
        createdAt = null;
        createdIpAddress = null;
        updatedBy = null;
        updatedAt = null;
        updatedIpAddress = null;
        recordVersion = null;
        BizTranGrpCriteria criteria = new BizTranGrpCriteria();

        // テスト対象クラス生成
        BizTranGrpDataSource bizTranGrpDataSource = new BizTranGrpDataSource(createBizTranGrpEntityDao());

        // 期待値
        BizTranGrp expectedBizTranGrp = BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode));

        // 実行
        BizTranGrp actualBizTranGrp = bizTranGrpDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualBizTranGrp).usingRecursiveComparison().isEqualTo(expectedBizTranGrp);
    }

    /**
     * {@link BizTranGrpDataSource#findOneBy(BizTranGrpCriteria)}のテスト
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
        BizTranGrpCriteria criteria = new BizTranGrpCriteria();

        // テスト対象クラス生成
        BizTranGrpDataSource bizTranGrpDataSource = new BizTranGrpDataSource(createBizTranGrpEntityDao());

        // 期待値
        BizTranGrp expectedBizTranGrp = BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode)
        );

        // 実行
        BizTranGrp actualBizTranGrp = bizTranGrpDataSource.findOneBy(criteria);

        // 結果検証
        assertThat(actualBizTranGrp).usingRecursiveComparison().isEqualTo(expectedBizTranGrp);
    }

    /**
     * {@link BizTranGrpDataSource#selectBy(BizTranGrpCriteria,Orders)}のテスト
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
        BizTranGrpCriteria criteria = new BizTranGrpCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        BizTranGrpDataSource bizTranGrpDataSource = new BizTranGrpDataSource(createBizTranGrpEntityDao());

        // 期待値
        List<BizTranGrp> expectedBizTranGrpList = newArrayList();
        for(BizTranGrpEntity entity : createBizTranGrpEntityList()) {
            expectedBizTranGrpList.add(BizTranGrp.createFrom(
                entity.getBizTranGrpId(),
                entity.getBizTranGrpCode(),
                entity.getBizTranGrpName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranGrps actualBizTranGrps = bizTranGrpDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualBizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrpList.get(i));
        }
    }

    /**
     * {@link BizTranGrpDataSource#selectAll(Orders)}のテスト
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
        BizTranGrpDataSource bizTranGrpDataSource = new BizTranGrpDataSource(createBizTranGrpEntityDao());

        // 期待値
        List<BizTranGrp> expectedBizTranGrpList = newArrayList();
        for(BizTranGrpEntity entity : createBizTranGrpEntityList()) {
            expectedBizTranGrpList.add(BizTranGrp.createFrom(
                entity.getBizTranGrpId(),
                entity.getBizTranGrpCode(),
                entity.getBizTranGrpName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranGrps actualBizTranGrps = bizTranGrpDataSource.selectAll(orders);

        // 結果検証
        for(int i = 0; i < actualBizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrpList.get(i));
        }
    }
}