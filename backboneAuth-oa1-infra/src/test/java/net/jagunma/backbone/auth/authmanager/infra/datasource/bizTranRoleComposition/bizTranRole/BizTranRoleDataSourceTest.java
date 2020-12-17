package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleDataSourceTest {

    // 実行既定値
    // 取引ロールDaoの作成
    private BizTranRoleEntityDao createBizTranRoleEntityDao() {
        return new BizTranRoleEntityDao() {
            @Override
            public List<BizTranRoleEntity> findBy(BizTranRoleEntityCriteria criteria, Orders orders) {
                return createBizTranRoleEntityList();
            }
            @Override
            public List<BizTranRoleEntity> findAll(Orders orders) {
                return createBizTranRoleEntityList();
            }
            @Override
            public BizTranRoleEntity findOneBy(BizTranRoleEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(BizTranRoleEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranRoleEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranRoleEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranRoleEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranRoleEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranRoleEntity> entities) {
                return new int[0];
            }
        };
    }
    // 取引ロールリストデータ作成
    private List<BizTranRoleEntity> createBizTranRoleEntityList() {
        List<BizTranRoleEntity> list = newArrayList();
        list.add(createBizTranRoleEntity(1L,"ANAG01","（畜産）取引全般","AN",null,null,null,null,null,null,1));
        list.add(createBizTranRoleEntity(2L,"ANAG02","（畜産）維持管理担当者","AN",null,null,null,null,null,null,1));
        list.add(createBizTranRoleEntity(3L,"ANAG99","（畜産）維持管理責任者","AN",null,null,null,null,null,null,1));
        return list;
    }
    // 取引グループデータ作成
    private BizTranRoleEntity createBizTranRoleEntity(
        Long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        BizTranRoleEntity entity = new BizTranRoleEntity();
        entity.setBizTranRoleId(bizTranRoleId);
        entity.setBizTranRoleCode(bizTranRoleCode);
        entity.setBizTranRoleName(bizTranRoleName);
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
     * {@link BizTranRoleDataSource#selectBy(BizTranRoleCriteria,Orders)}のテスト
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
        BizTranRoleCriteria criteria = new BizTranRoleCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        BizTranRoleDataSource bizTranRoleDataSource = new BizTranRoleDataSource(createBizTranRoleEntityDao());

        // 期待値
        List<BizTranRole> expectedBizTranGrpList = newArrayList();
        for(BizTranRoleEntity entity : createBizTranRoleEntityList()) {
            expectedBizTranGrpList.add(BizTranRole.createFrom(
                entity.getBizTranRoleId(),
                entity.getBizTranRoleCode(),
                entity.getBizTranRoleName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranRoles actualBizTranRoles = bizTranRoleDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualBizTranRoles.getValues().size(); i++) {
            assertThat(actualBizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrpList.get(i));
        }
    }

    /**
     * {@link BizTranRoleDataSource#selectAll(Orders)}のテスト
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
        BizTranRoleDataSource bizTranRoleDataSource = new BizTranRoleDataSource(createBizTranRoleEntityDao());

        // 期待値
        List<BizTranRole> expectedBizTranGrpList = newArrayList();
        for(BizTranRoleEntity entity : createBizTranRoleEntityList()) {
            expectedBizTranGrpList.add(BizTranRole.createFrom(
                entity.getBizTranRoleId(),
                entity.getBizTranRoleCode(),
                entity.getBizTranRoleName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranRoles actualBizTranRoles = bizTranRoleDataSource.selectAll(orders);

        // 結果検証
        for(int i = 0; i < actualBizTranRoles.getValues().size(); i++) {
            assertThat(actualBizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrpList.get(i));
        }
    }
}