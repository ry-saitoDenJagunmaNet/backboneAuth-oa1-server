package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntityDao;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntityDao;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleCompositionForStoreDataSourceTest {

    // 実行既定値
    // 取引ロールDaoの作成
    private BizTranRoleEntityDao createBizTranRoleEntityDao() {
        return new BizTranRoleEntityDao() {
            @Override
            public List<BizTranRoleEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public BizTranRoleEntity findOneBy(BizTranRoleEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranRoleEntity> findBy(BizTranRoleEntityCriteria criteria, Orders orders) {
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
    // 取引グループDaoの作成
    private BizTranGrpEntityDao createBizTranGrpEntityDao() {
        return new BizTranGrpEntityDao() {
            @Override
            public List<BizTranGrpEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public BizTranGrpEntity findOneBy(BizTranGrpEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranGrpEntity> findBy(BizTranGrpEntityCriteria criteria, Orders orders) {
                return null;
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
    // 取引Daoの作成
    private BizTranEntityDao createBizTranEntityDao() {
        return new BizTranEntityDao() {
            @Override
            public List<BizTranEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public BizTranEntity findOneBy(BizTranEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranEntity> findBy(BizTranEntityCriteria criteria, Orders orders) {
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
    // 取引ロール_取引グループ割当Daoの作成
    private BizTranRole_BizTranGrpEntityDao createBizTranRole_BizTranGrpEntityDao() {
        return new BizTranRole_BizTranGrpEntityDao() {
            @Override
            public List<BizTranRole_BizTranGrpEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public BizTranRole_BizTranGrpEntity findOneBy(BizTranRole_BizTranGrpEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranRole_BizTranGrpEntity> findBy(BizTranRole_BizTranGrpEntityCriteria criteria, Orders orders) {
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
    // 取引グループ_取引割当Daoの作成
    private BizTranGrp_BizTranEntityDao createBizTranGrp_BizTranEntityDao() {
        return new BizTranGrp_BizTranEntityDao() {
            @Override
            public List<BizTranGrp_BizTranEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public BizTranGrp_BizTranEntity findOneBy(BizTranGrp_BizTranEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<BizTranGrp_BizTranEntity> findBy(BizTranGrp_BizTranEntityCriteria criteria, Orders orders) {
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
    // 取引ロールRepositoryの作成
    private BizTranRolesRepository createBizTranRolesRepository() {
        return new BizTranRolesRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                // TODO: 2020/11/02 取引ロール更新のパターン用を追加
                List<BizTranRole> list = newArrayList();
                return BizTranRoles.createFrom(list);
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
    }
    // 取引群データ作成
    private BizTrans createBizTrans() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTrans.createFrom(list);
    }
    // 取引グループ群データ作成
    private BizTranGrps createBizTranGrps() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranGrps.createFrom(list);
    }
    // 取引グループ_取引割当群データ作成
    private BizTranGrp_BizTrans createBizTranGrp_BizTrans() {
        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(null,null,null,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(null,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,null),
            BizTran.createFrom(null,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,null),
            SubSystem.販売_畜産));
        return BizTranGrp_BizTrans.createFrom(list);
    }
    // 取引ロール群データ作成
    private BizTranRoles createBizTranRoles() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranRoles.createFrom(list);
    }
    // 取引ロール_取引グループ割当群データ作成
    private BizTranRole_BizTranGrps createBizTranRole_BizTranGrps() {
        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(null,null,null,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(null,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,null),
            BizTranGrp.createFrom(null,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,null),
            SubSystem.販売_畜産));
        return BizTranRole_BizTranGrps.createFrom(list);
    }

    /**
     * {@link BizTranRoleCompositionForStoreDataSource#store(BizTranRoleComposition)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test0() {

        // 実行値
        BizTranRoleComposition bizTranRoleComposition = BizTranRoleComposition.createFrom(
            createBizTrans(),
            createBizTranGrps(),
            createBizTranGrp_BizTrans(),
            createBizTranRoles(),
            createBizTranRole_BizTranGrps());

        // テスト対象クラス生成
        BizTranRoleCompositionForStoreDataSource bizTranRoleCompositionForStoreDataSource = new BizTranRoleCompositionForStoreDataSource(
            createBizTranRoleEntityDao(),
            createBizTranGrpEntityDao(),
            createBizTranEntityDao(),
            createBizTranRole_BizTranGrpEntityDao(),
            createBizTranGrp_BizTranEntityDao(),
            createBizTranRolesRepository());

        // 期待値
        bizTranRoleCompositionForStoreDataSource.store(bizTranRoleComposition);

        // 結果検証

    }
}