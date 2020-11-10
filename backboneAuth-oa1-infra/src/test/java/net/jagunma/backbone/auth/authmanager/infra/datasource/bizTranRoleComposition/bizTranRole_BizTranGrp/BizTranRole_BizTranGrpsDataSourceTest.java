package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpsDataSourceTest {

    // 実行既定値
    // 取引ロール_取引グループ割当Daoの作成
    private BizTranRole_BizTranGrpEntityDao createBizTranRole_BizTranGrpEntityDao() {
        return new BizTranRole_BizTranGrpEntityDao() {
            @Override
            public List<BizTranRole_BizTranGrpEntity> findBy(BizTranRole_BizTranGrpEntityCriteria criteria, Orders orders) {
                return createBizTranRole_BizTranGrpEntityList();
            }
            @Override
            public List<BizTranRole_BizTranGrpEntity> findAll(Orders orders) {
                return createBizTranRole_BizTranGrpEntityList();
            }
            @Override
            public BizTranRole_BizTranGrpEntity findOneBy(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int update(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int delete(BizTranRole_BizTranGrpEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<BizTranRole_BizTranGrpEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<BizTranRole_BizTranGrpEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<BizTranRole_BizTranGrpEntity> entities) {
                return new int[0];
            }
        };
    }
    // 取引ロール_取引グループ割当リストデータ作成
    private List<BizTranRole_BizTranGrpEntity> createBizTranRole_BizTranGrpEntityList() {
        List<BizTranRole_BizTranGrpEntity> list = newArrayList();
        list.add(createBizTranRole_BizTranGrpEntity(1L,10001L,100001L,SubSystem.販売_畜産.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranRole_BizTranGrpEntity(2L,10002L,100003L,SubSystem.販売_畜産.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranRole_BizTranGrpEntity(3L,10003L,100003L,SubSystem.販売_畜産.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // 取引グループ_取引割当データ作成
    private BizTranRole_BizTranGrpEntity createBizTranRole_BizTranGrpEntity(
        Long bizTranRole_BizTranGrpId,
        Long bizTranRoleId,
        Long bizTranGrpId,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {


        BizTranRole_BizTranGrpEntity entity = new BizTranRole_BizTranGrpEntity();
        entity.setBizTranRole_BizTranGrpId(bizTranRole_BizTranGrpId);
        entity.setBizTranRoleId(bizTranRoleId);
        entity.setBizTranGrpId(bizTranGrpId);
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

    // 取引ロ－ルRepositoryの作成
    private BizTranRolesRepository createBizTranRolesRepository() {
        return new BizTranRolesRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return createBizTranRoles();
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return createBizTranRoles();
            }
        };
    }
    // 取引ロ－ル群データ作成
    private BizTranRoles createBizTranRoles() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(10001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(10002L,"ANAG02","（畜産）維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranRole.createFrom(10003L,"ANAG99","（畜産）維持管理責任者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranRoles.createFrom(list);
    }
    // 取引グループRepositoryの作成
    private BizTranGrpsRepository createBizTranGrpsRepository() {
        return new BizTranGrpsRepository() {
            @Override
            public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
                return createBizTranGrps();
            }
            @Override
            public BizTranGrps selectAll(Orders orders) {
                return createBizTranGrps();
            }
        };
    }
    // 取引グループ群データ作成
    private BizTranGrps createBizTranGrps() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(100001L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(100002L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(100003L,"ANTG01","センター維持管理グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranGrps.createFrom(list);
    }

    /**
     * {@link BizTranRole_BizTranGrpsDataSource#selectBy(BizTranRole_BizTranGrpCriteria,Orders)}のテスト
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
        BizTranRole_BizTranGrpCriteria criteria = new BizTranRole_BizTranGrpCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        BizTranRole_BizTranGrpsDataSource bizTranRole_BizTranGrpsDataSource = new BizTranRole_BizTranGrpsDataSource(
            createBizTranRole_BizTranGrpEntityDao(),
            createBizTranRolesRepository(),
            createBizTranGrpsRepository());

        // 期待値
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpList = newArrayList();
        for(BizTranRole_BizTranGrpEntity entity : createBizTranRole_BizTranGrpEntityList()) {
            expectedBizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                entity.getBizTranRole_BizTranGrpId(),
                entity.getBizTranRoleId(),
                entity.getBizTranGrpId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                createBizTranRoles().getValues().stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null),
                createBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps = bizTranRole_BizTranGrpsDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpList.get(i));
        }
    }

    /**
     * {@link BizTranRole_BizTranGrpsDataSource#selectAll(Orders)}のテスト
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
        BizTranRole_BizTranGrpsDataSource bizTranRole_BizTranGrpsDataSource = new BizTranRole_BizTranGrpsDataSource(
            createBizTranRole_BizTranGrpEntityDao(),
            createBizTranRolesRepository(),
            createBizTranGrpsRepository());

        // 期待値
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpList = newArrayList();
        for(BizTranRole_BizTranGrpEntity entity : createBizTranRole_BizTranGrpEntityList()) {
            expectedBizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                entity.getBizTranRole_BizTranGrpId(),
                entity.getBizTranRoleId(),
                entity.getBizTranGrpId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                createBizTranRoles().getValues().stream().filter(b->b.getBizTranRoleId().equals(entity.getBizTranRoleId())).findFirst().orElse(null),
                createBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps = bizTranRole_BizTranGrpsDataSource.selectAll(orders);

        // 結果検証
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpList.get(i));
        }
    }
}