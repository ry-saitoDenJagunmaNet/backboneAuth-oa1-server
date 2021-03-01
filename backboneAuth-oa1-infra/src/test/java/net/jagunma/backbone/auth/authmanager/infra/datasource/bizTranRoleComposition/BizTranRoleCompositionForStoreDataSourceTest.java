package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
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
import net.jagunma.common.util.beans.Beans;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleCompositionForStoreDataSourceTest {

    // 実行既定値

    // 検証値
    private BizTranGrp_BizTranEntityCriteria actualBizTranGrp_BizTranEntityDaoForceDeleteCriteria;
    private BizTranRole_BizTranGrpEntityCriteria actualBizTranRole_BizTranGrpEntityDaoForceDeleteCriteria;
    private List<BizTranEntity> actualArgBizTranEntityList = newArrayList();
    private List<BizTranGrpEntity> actualArgBizTranGrpEntityList = newArrayList();
    private List<BizTranRoleEntity> actualInsertArgBizTranRoleEntityList = newArrayList();
    private List<BizTranRoleEntity> actualUpdateArgBizTranRoleEntityList = newArrayList();
    private List<BizTranRoleEntity> actualDeleteArgBizTranRoleEntityList = newArrayList();
    private List<BizTranGrp_BizTranEntity> actualArgBizTranGrp_BizTranEntityList = newArrayList();
    private List<BizTranRole_BizTranGrpEntity> actualArgBizTranRole_BizTranGrpEntityList = newArrayList();

    // 取引群データ作成
    private BizTrans createBizTrans() {
        return BizTrans.createFrom(createBizTranList());
    }
    // 取引リストデータ作成
    private List<BizTran> createBizTranList() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(null,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),null,null));
        return list;
    }
    // 取引グループ群データ作成
    private BizTranGrps createBizTranGrps() {
        return BizTranGrps.createFrom(createBizTranGrpList());
    }
    // 取引グループリストデータ作成
    private List<BizTranGrp> createBizTranGrpList() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(null,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),null,null));
        return list;
    }
    // 取引グループ_取引割当群データ作成
    private BizTranGrp_BizTrans createBizTranGrp_BizTrans() {
        return BizTranGrp_BizTrans.createFrom(createBizTranGrp_BizTranList());
    }
    // 取引グループ_取引割当リストデータ作成
    private List<BizTranGrp_BizTran> createBizTranGrp_BizTranList() {
        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(null,null,null,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(null,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,null),
            BizTran.createFrom(null,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),null,null),
            null));
        return list;
    }
    // 取引ロール群データ作成
    private BizTranRoles createBizTranRoles() {
        return BizTranRoles.createFrom(createBizTranRoleList());
    }
    // 取引ロールリストデータ作成
    private List<BizTranRole> createBizTranRoleList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(null,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),null,null));
        list.add(BizTranRole.createFrom(null,"ANAG97","（畜産）センター維持管理担当者【追加テスト】",SubSystem.販売_畜産.getCode(),null,null));
        return list.stream().sorted(Comparator.comparing(BizTranRole::getBizTranRoleCode)).collect(Collectors.toList());
    }
    // 取引ロール登録前リストデータ作成
    private List<BizTranRole> createBizTranRoleStoreBeforeList() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(100001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,null));
        list.add(BizTranRole.createFrom(100002L,"ANAG96","（畜産）センター維持管理担当者【削除テスト】",SubSystem.販売_畜産.getCode(),1,null));
        return list;
    }
    // 取引ロール_取引グループ割当群データ作成
    private BizTranRole_BizTranGrps createBizTranRole_BizTranGrps() {
        return BizTranRole_BizTranGrps.createFrom(createBizTranRole_BizTranGrpList());
    }
    // 取引ロール_取引グループ割当リストデータ作成
    private List<BizTranRole_BizTranGrp> createBizTranRole_BizTranGrpList() {
        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(null,null,null,SubSystem.販売_畜産.getCode(),null,
            BizTranRole.createFrom(null,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),null,null),
            BizTranGrp.createFrom(null,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),null,null),
            SubSystem.販売_畜産));
        return list;
    }

    // 取引ロールDaoのスタブ
    private BizTranRoleEntityDao createBizTranRoleEntityDao() {
        return new BizTranRoleEntityDao() {
            @Override
            public int insert(BizTranRoleEntity entity) {
                actualInsertArgBizTranRoleEntityList.add(Beans.createAndCopy(BizTranRoleEntity.class, entity).execute());
                entity.setBizTranRoleId((long)actualInsertArgBizTranRoleEntityList.size()+100002);
                entity.setRecordVersion(1);
                return 1;
            }
            @Override
            public int updateExcludeNull(BizTranRoleEntity entity) {
                actualUpdateArgBizTranRoleEntityList.add(Beans.createAndCopy(BizTranRoleEntity.class, entity).execute());
                entity.setSubSystemCode(createBizTranRoleStoreBeforeList().stream()
                    .filter(b -> b.getBizTranRoleCode().equals(entity.getBizTranRoleCode()))
                    .map(BizTranRole::getSubSystemCode).findFirst().orElse(null));
                entity.setRecordVersion(entity.getRecordVersion()+1);
                return 1;
            }
            @Override
            public int delete(BizTranRoleEntity entity) {
                actualDeleteArgBizTranRoleEntityList.add(entity);
                return 1;
            }
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
            public int update(BizTranRoleEntity entity) {
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
    // 取引グループDaoのスタブ
    private BizTranGrpEntityDao createBizTranGrpEntityDao() {
        return new BizTranGrpEntityDao() {
            @Override
            public int insert(BizTranGrpEntity entity) {
                actualArgBizTranGrpEntityList.add(Beans.createAndCopy(BizTranGrpEntity.class, entity).execute());
                entity.setBizTranGrpId((long)actualArgBizTranGrpEntityList.size()+1000);
                entity.setRecordVersion(1);
                return 1;
            }
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
    // 取引Daoのスタブ
    private BizTranEntityDao createBizTranEntityDao() {
        return new BizTranEntityDao() {
            @Override
            public int insert(BizTranEntity entity) {
                actualArgBizTranEntityList.add(Beans.createAndCopy(BizTranEntity.class, entity).execute());
                entity.setBizTranId((long) actualArgBizTranEntityList.size()+10000);
                entity.setRecordVersion(1);
                return 1;
            }
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
    // 取引ロール_取引グループ割当Daoのスタブ
    private BizTranRole_BizTranGrpEntityDao createBizTranRole_BizTranGrpEntityDao() {
        return new BizTranRole_BizTranGrpEntityDao() {
            @Override
            public int forceDelete(BizTranRole_BizTranGrpEntityCriteria criteria) {
                actualBizTranRole_BizTranGrpEntityDaoForceDeleteCriteria = criteria;
                return 1;
            }
            @Override
            public int insert(BizTranRole_BizTranGrpEntity entity) {
                actualArgBizTranRole_BizTranGrpEntityList.add(Beans.createAndCopy(BizTranRole_BizTranGrpEntity.class, entity).execute());
                entity.setBizTranRole_BizTranGrpId(100003L);
                entity.setRecordVersion(1);
                return 1;
            }
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
    // 取引グループ_取引割当Daoのスタブ
    private BizTranGrp_BizTranEntityDao createBizTranGrp_BizTranEntityDao() {
        return new BizTranGrp_BizTranEntityDao() {
            @Override
            public int forceDelete(BizTranGrp_BizTranEntityCriteria criteria) {
                actualBizTranGrp_BizTranEntityDaoForceDeleteCriteria = criteria;
                return 1;
            }
            @Override
            public int insert(BizTranGrp_BizTranEntity entity) {
                actualArgBizTranGrp_BizTranEntityList.add(Beans.createAndCopy(BizTranGrp_BizTranEntity.class, entity).execute());
                entity.setBizTranGrp_BizTranId((long)actualArgBizTranGrp_BizTranEntityList.size()+1000000);
                entity.setRecordVersion(1);
                return 1;
            }
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
    // 取引ロールリポジトリのスタブ
    private BizTranRoleRepository createBizTranRoleRepository() {
        return new BizTranRoleRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return BizTranRoles.createFrom(createBizTranRoleStoreBeforeList());
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
    }

    /**
     * {@link BizTranRoleCompositionForStoreDataSource#store(BizTranRoleComposition)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・Dao格納値、リポジトリ検索条件
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
            createBizTranRoleRepository());

        // 期待値
        // 取引リストの期待値
        List<BizTranEntity> expectedArgBizTranEntityList = newArrayList();
        List<BizTran> expectedBizTranList = newArrayList();
        for (BizTran bizTran : createBizTranList()) {
            BizTranEntity argBizTranEntity = new BizTranEntity();
            argBizTranEntity.setBizTranCode(bizTran.getBizTranCode());
            argBizTranEntity.setBizTranName(bizTran.getBizTranName());
            argBizTranEntity.setIsCenterBizTran(bizTran.getIsCenterBizTran());
            argBizTranEntity.setValidThruStartDate(bizTran.getValidThruStartDate());
            argBizTranEntity.setValidThruEndDate(bizTran.getValidThruEndDate());
            argBizTranEntity.setSubSystemCode(bizTran.getSubSystemCode());
            expectedArgBizTranEntityList.add(argBizTranEntity);
            BizTran expectedBizTran = BizTran.createFrom(
                (long) expectedArgBizTranEntityList.size()+10000,
                bizTran.getBizTranCode(),
                bizTran.getBizTranName(),
                bizTran.getIsCenterBizTran(),
                bizTran.getValidThruStartDate(),
                bizTran.getValidThruEndDate(),
                bizTran.getSubSystemCode(),
                1,
                null);
            expectedBizTranList.add(expectedBizTran);
        }

        // 取引グループリストの期待値
        List<BizTranGrpEntity> expectedArgBizTranGrpEntityList = newArrayList();
        List<BizTranGrp> expectedBizTranGrpList = newArrayList();
        for (BizTranGrp bizTranGrp : createBizTranGrpList()) {
            BizTranGrpEntity argBizTranGrpEntity = new BizTranGrpEntity();
            argBizTranGrpEntity.setBizTranGrpCode(bizTranGrp.getBizTranGrpCode());
            argBizTranGrpEntity.setBizTranGrpName(bizTranGrp.getBizTranGrpName());
            argBizTranGrpEntity.setSubSystemCode(bizTranGrp.getSubSystemCode());
            expectedArgBizTranGrpEntityList.add(argBizTranGrpEntity);
            BizTranGrp expectedBizTranGrp = BizTranGrp.createFrom(
                (long)expectedArgBizTranGrpEntityList.size()+1000,
                bizTranGrp.getBizTranGrpCode(),
                bizTranGrp.getBizTranGrpName(),
                bizTranGrp.getSubSystemCode(),
                1,
                null);
            expectedBizTranGrpList.add(expectedBizTranGrp);
        }

        // 取引ロールリスの期待値
        List<BizTranRoleEntity> expectedInsertArgBizTranRoleEntityList = newArrayList();
        List<BizTranRole> expectedInsertBizTranRoleList = newArrayList();
        List<BizTranRoleEntity> expectedUpdateArgBizTranRoleEntityList = newArrayList();
        List<BizTranRole> expectedUpdateBizTranRoleList = newArrayList();
        for (BizTranRole bizTranRole : createBizTranRoleList()) {
            BizTranRole bizTranRoleStoreBefore = createBizTranRoleStoreBeforeList().stream().filter(b -> b.getBizTranRoleCode().equals(bizTranRole.getBizTranRoleCode())).findFirst().orElse(null);
            if (bizTranRoleStoreBefore == null) {
                // insert
                BizTranRoleEntity argBizTranRoleEntity = new BizTranRoleEntity();
                argBizTranRoleEntity.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
                argBizTranRoleEntity.setBizTranRoleName(bizTranRole.getBizTranRoleName());
                argBizTranRoleEntity.setSubSystemCode(bizTranRole.getSubSystemCode());
                expectedInsertArgBizTranRoleEntityList.add(argBizTranRoleEntity);
                BizTranRole expectedBizTranRole = BizTranRole.createFrom(
                    (long)expectedInsertArgBizTranRoleEntityList.size()+100002,
                    bizTranRole.getBizTranRoleCode(),
                    bizTranRole.getBizTranRoleName(),
                    bizTranRole.getSubSystemCode(),
                    1,
                    null);
                expectedInsertBizTranRoleList.add(expectedBizTranRole);
            } else {
                // update
                BizTranRoleEntity argBizTranRoleEntity = new BizTranRoleEntity();
                argBizTranRoleEntity.setBizTranRoleId(bizTranRoleStoreBefore.getBizTranRoleId());
                argBizTranRoleEntity.setBizTranRoleCode(bizTranRoleStoreBefore.getBizTranRoleCode());
                argBizTranRoleEntity.setBizTranRoleName(bizTranRoleStoreBefore.getBizTranRoleName());
                argBizTranRoleEntity.setRecordVersion(bizTranRoleStoreBefore.getRecordVersion());
                expectedUpdateArgBizTranRoleEntityList.add(argBizTranRoleEntity);
                BizTranRole expectedBizTranRole = BizTranRole.createFrom(
                    bizTranRoleStoreBefore.getBizTranRoleId(),
                    bizTranRoleStoreBefore.getBizTranRoleCode(),
                    bizTranRoleStoreBefore.getBizTranRoleName(),
                    bizTranRoleStoreBefore.getSubSystemCode(),
                    bizTranRoleStoreBefore.getRecordVersion()+1,
                    null);
                expectedUpdateBizTranRoleList.add(expectedBizTranRole);
            }
        }
        List<BizTranRoleEntity> expectedDeleteArgBizTranRoleEntityList = newArrayList();
        for (BizTranRole bizTranRole : createBizTranRoleStoreBeforeList()) {
            if (createBizTranRoleList().stream().noneMatch(b -> b.getBizTranRoleCode().equals(bizTranRole.getBizTranRoleCode()))) {
                // delete
                BizTranRoleEntity argBizTranRoleEntity = new BizTranRoleEntity();
                argBizTranRoleEntity.setBizTranRoleId(bizTranRole.getBizTranRoleId());
                argBizTranRoleEntity.setRecordVersion(bizTranRole.getRecordVersion());
                expectedDeleteArgBizTranRoleEntityList.add(argBizTranRoleEntity);
            }
        }

        // 取引グループ＿取引割当リスの期待値
        List<BizTranGrp_BizTranEntity> expectedArgBizTranGrp_BizTranEntityList = newArrayList();
        List<BizTranGrp_BizTran> expectedArgBizTranGrp_BizTranList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : createBizTranGrp_BizTranList()) {
            BizTranGrp_BizTranEntity bizTranGrp_BizTranEntity = new BizTranGrp_BizTranEntity();
            bizTranGrp_BizTranEntity.setBizTranGrpId(expectedBizTranGrpList.stream()
                .filter(b -> b.getBizTranGrpCode().equals(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode()))
                .map(BizTranGrp::getBizTranGrpId).findFirst().orElse(null));
            bizTranGrp_BizTranEntity.setBizTranId(expectedBizTranList.stream()
                .filter(b -> b.getBizTranCode().equals(bizTranGrp_BizTran.getBizTran().getBizTranCode()))
                .map(BizTran::getBizTranId).findFirst().orElse(null));
            bizTranGrp_BizTranEntity.setSubSystemCode(bizTranGrp_BizTran.getSubSystemCode());
            expectedArgBizTranGrp_BizTranEntityList.add(bizTranGrp_BizTranEntity);
            BizTranGrp bizTranGrp = expectedBizTranGrpList.stream()
                .filter(b -> b.getBizTranGrpCode().equals(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode()))
                .findFirst().orElse(null);
            BizTran bizTran = expectedBizTranList.stream()
                .filter(b -> b.getBizTranCode().equals(bizTranGrp_BizTran.getBizTran().getBizTranCode()))
                .findFirst().orElse(null);
            BizTranGrp_BizTran expectedBizTranGrp_BizTran = BizTranGrp_BizTran.createFrom(
                (long)expectedArgBizTranGrp_BizTranEntityList.size()+1000000,
                bizTranGrp.getBizTranGrpId(),
                bizTran.getBizTranId(),
                bizTranGrp_BizTran.getSubSystemCode(),
                1,
                bizTranGrp,
                bizTran,
                SubSystem.codeOf(bizTranGrp_BizTran.getSubSystemCode()));
            expectedArgBizTranGrp_BizTranList.add(expectedBizTranGrp_BizTran);
        }

        // 取引ロール＿取引グループ割当リスの期待値
        List<BizTranRole_BizTranGrpEntity> expectedArgBizTranRole_BizTranGrpEntityList = newArrayList();
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpList = newArrayList();
        List<BizTranRole> expectedBizTranRoleList = newArrayList();
        expectedBizTranRoleList.addAll(expectedInsertBizTranRoleList);
        expectedBizTranRoleList.addAll(expectedUpdateBizTranRoleList);
        expectedBizTranRoleList = expectedBizTranRoleList.stream().sorted(Comparator.comparing(BizTranRole::getBizTranRoleCode)).collect(Collectors.toList());
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : createBizTranRole_BizTranGrpList()) {
            BizTranRole_BizTranGrpEntity bizTranRole_BizTranGrpEntity = new BizTranRole_BizTranGrpEntity();
            bizTranRole_BizTranGrpEntity.setBizTranRoleId(expectedBizTranRoleList.stream()
                .filter(b -> b.getBizTranRoleCode().equals(bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode()))
                .map(BizTranRole::getBizTranRoleId).findFirst().orElse(null));
            bizTranRole_BizTranGrpEntity.setBizTranGrpId(expectedBizTranGrpList.stream()
                .filter(b -> b.getBizTranGrpCode().equals(bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode()))
                .map(BizTranGrp::getBizTranGrpId).findFirst().orElse(null));
            BizTranRole bizTranRole = expectedBizTranRoleList.stream()
                .filter(b -> b.getBizTranRoleCode().equals(bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode()))
                .findFirst().orElse(null);
            BizTranGrp bizTranGrp = expectedBizTranGrpList.stream()
                .filter(b -> b.getBizTranGrpCode().equals(bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode()))
                .findFirst().orElse(null);
            bizTranRole_BizTranGrpEntity.setSubSystemCode(bizTranRole_BizTranGrp.getSubSystemCode());
            expectedArgBizTranRole_BizTranGrpEntityList.add(bizTranRole_BizTranGrpEntity);
            BizTranRole_BizTranGrp expectedBizTranRole_BizTranGrp = BizTranRole_BizTranGrp.createFrom(
                (long)expectedArgBizTranRole_BizTranGrpEntityList.size()+10002,
                bizTranRole.getBizTranRoleId(),
                bizTranGrp.getBizTranGrpId(),
                bizTranRole_BizTranGrp.getSubSystemCode(),
                1,
                bizTranRole,
                bizTranGrp,
                SubSystem.codeOf(bizTranRole_BizTranGrp.getSubSystemCode()));
            expectedBizTranRole_BizTranGrpList.add(expectedBizTranRole_BizTranGrp);
        }
        BizTranGrp_BizTranEntityCriteria expectedBizTranGrp_BizTranEntityDaoForceDeleteCriteria = new BizTranGrp_BizTranEntityCriteria();
        expectedBizTranGrp_BizTranEntityDaoForceDeleteCriteria.getSubSystemCodeCriteria().setEqualTo(createBizTranGrp_BizTrans().getValues().get(0).getSubSystemCode());
        BizTranRole_BizTranGrpEntityCriteria expectedBizTranRole_BizTranGrpEntityDaoForceDeleteCriteria = new BizTranRole_BizTranGrpEntityCriteria();
        expectedBizTranRole_BizTranGrpEntityDaoForceDeleteCriteria.getSubSystemCodeCriteria().setEqualTo(createBizTranRole_BizTranGrps().getValues().get(0).getSubSystemCode());

        // 実行
        BizTranRoleComposition actualBizTranRoleComposition = bizTranRoleCompositionForStoreDataSource.store(bizTranRoleComposition);

        // 結果検証
        assertThat(actualBizTranRoleComposition.getBizTrans().getValues()).usingRecursiveComparison().isEqualTo(expectedBizTranList);
        assertThat(actualBizTranRoleComposition.getBizTranGrps().getValues()).usingRecursiveComparison().isEqualTo(expectedBizTranGrpList);
        assertThat(actualBizTranRoleComposition.getBizTranGrp_BizTrans().getValues()).usingRecursiveComparison().isEqualTo(expectedArgBizTranGrp_BizTranList);
        assertThat(actualBizTranRoleComposition.getBizTranRoles().getValues()).usingRecursiveComparison().isEqualTo(expectedBizTranRoleList);
        assertThat(actualBizTranRoleComposition.getBizTranRole_BizTranGrps().getValues()).isEqualTo(expectedBizTranRole_BizTranGrpList);
        assertThat(actualBizTranGrp_BizTranEntityDaoForceDeleteCriteria.toString()).isEqualTo(expectedBizTranGrp_BizTranEntityDaoForceDeleteCriteria.toString());
        assertThat(actualBizTranRole_BizTranGrpEntityDaoForceDeleteCriteria.toString()).isEqualTo(expectedBizTranRole_BizTranGrpEntityDaoForceDeleteCriteria.toString());
        assertThat(actualArgBizTranEntityList).usingRecursiveComparison().isEqualTo(expectedArgBizTranEntityList);
        assertThat(actualArgBizTranGrpEntityList).usingRecursiveComparison().isEqualTo(expectedArgBizTranGrpEntityList);
        assertThat(actualInsertArgBizTranRoleEntityList).usingRecursiveComparison().isEqualTo(expectedInsertArgBizTranRoleEntityList);
        assertThat(actualUpdateArgBizTranRoleEntityList).usingRecursiveComparison().isEqualTo(expectedUpdateArgBizTranRoleEntityList);
        assertThat(actualDeleteArgBizTranRoleEntityList).usingRecursiveComparison().isEqualTo(expectedDeleteArgBizTranRoleEntityList);
        assertThat(actualArgBizTranGrp_BizTranEntityList).usingRecursiveComparison().isEqualTo(expectedArgBizTranGrp_BizTranEntityList);
        assertThat(actualArgBizTranRole_BizTranGrpEntityList).usingRecursiveComparison().isEqualTo(expectedArgBizTranRole_BizTranGrpEntityList);
    }
}