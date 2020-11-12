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

class BizTransDataSourceTest {

    // 実行既定値
    // 取引Daoの作成
    private BizTranEntityDao createBizTranEntityDao() {
        return new BizTranEntityDao() {
            @Override
            public List<BizTranEntity> findBy(BizTranEntityCriteria criteria, Orders orders) {
                return createBizTranEntityList();
            }
            @Override
            public List<BizTranEntity> findAll(Orders orders) {
                return createBizTranEntityList();
            }
            @Override
            public BizTranEntity findOneBy(BizTranEntityCriteria criteria) {
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
    // 取引リストデータ作成
    private List<BizTranEntity> createBizTranEntityList() {
        List<BizTranEntity> list = newArrayList();
        list.add(createBizTranEntity(1L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),"AN",null,null,null,null,null,null,1));
        list.add(createBizTranEntity(2L,"AN1110","前日処理照会",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),"AN",null,null,null,null,null,null,1));
        list.add(createBizTranEntity(3L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),"AN",null,null,null,null,null,null,1));
        return list;
    }
    // 取引データ作成
    private BizTranEntity createBizTranEntity(
        Long bizTranId,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
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
        entity.setExpirationStartDate(expirationStartDate);
        entity.setExpirationEndDate(expirationEndDate);
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
     * {@link BizTransDataSource#selectBy(BizTranCriteria,Orders)}のテスト
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
        BizTransDataSource bizTransDataSource = new BizTransDataSource(createBizTranEntityDao());

        // 期待値
        List<BizTran> expectedBizTranList = newArrayList();
        for(BizTranEntity entity : createBizTranEntityList()) {
            expectedBizTranList.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getExpirationStartDate(),
                entity.getExpirationEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTrans actualBizTrans = bizTransDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualBizTrans.getValues().size(); i++) {
            assertThat(actualBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranList.get(i));
        }
    }

    /**
     * {@link BizTransDataSource#selectAll(Orders)}のテスト
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
        BizTransDataSource bizTransDataSource = new BizTransDataSource(createBizTranEntityDao());

        // 期待値
        List<BizTran> expectedBizTranList = newArrayList();
        for(BizTranEntity entity : createBizTranEntityList()) {
            expectedBizTranList.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getExpirationStartDate(),
                entity.getExpirationEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTrans actualBizTrans = bizTransDataSource.selectAll(orders);

        // 結果検証
        for(int i = 0; i < actualBizTrans.getValues().size(); i++) {
            assertThat(actualBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranList.get(i));
        }
    }
}