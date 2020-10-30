package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
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
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleCompositionRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
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
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 取引ロール編成登録
 */
@Component
public class BizTranRoleCompositionForStoreDataSource implements BizTranRoleCompositionRepositoryForStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizTranRoleCompositionForStoreDataSource.class);

    private final BizTranRoleEntityDao bizTranRoleEntityDao;
    private final BizTranGrpEntityDao bizTranGrpEntityDao;
    private final BizTranEntityDao bizTranEntityDao;
    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;
    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;
    private final BizTranRolesRepository bizTranRolesRepository;

    // コンストラクタ
    BizTranRoleCompositionForStoreDataSource(
        BizTranRoleEntityDao bizTranRoleEntityDao,
        BizTranGrpEntityDao bizTranGrpEntityDao,
        BizTranEntityDao bizTranEntityDao,
        BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao,
        BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao,
        BizTranRolesRepository bizTranRolesRepository) {

        this.bizTranRoleEntityDao = bizTranRoleEntityDao;
        this.bizTranGrpEntityDao = bizTranGrpEntityDao;
        this.bizTranEntityDao = bizTranEntityDao;
        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
        this.bizTranRolesRepository = bizTranRolesRepository;
    }

    /**
     * 取引ロール編成の登録を行います
     *
     * @param bizTranRoleComposition 取引ロール編成
     */
    public void store(BizTranRoleComposition bizTranRoleComposition) {

        // 取引グループ_取引割当の削除
        deleteBizTranGrp_BizTrans(bizTranRoleComposition.getBizTranGrp_BizTrans().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store deleteBizTranGrp_BizTrans 取引グループ_取引割当の削除 end");
        // 取引ロール_取引グループ割当の削除
        deleteBizTranRole_BizTranGrps(bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store deleteBizTranRole_BizTranGrps 取引ロール_取引グループ割当の削除 end");

        // 取引の登録
        BizTrans bizTrans = storeBizTrans(bizTranRoleComposition.getBizTrans());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store storeBizTrans 取引の登録 end count="+bizTranRoleComposition.getBizTrans().getValues().size());
        // 取引グループの登録
        BizTranGrps bizTranGrps = storeBizTranGrps(bizTranRoleComposition.getBizTranGrps());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store storeBizTranGrps 取引グループの登録 end count="+bizTranRoleComposition.getBizTranGrps().getValues().size());
        // 取引グループ_取引割当の追加
        insertBizTranGrp_BizTrans(bizTranRoleComposition.getBizTranGrp_BizTrans(),
            bizTranGrps,
            bizTrans,
            bizTranRoleComposition.getBizTranGrp_BizTrans().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store insertBizTranGrp_BizTrans( 取引グループ_取引割当の追加 end count="+bizTranRoleComposition.getBizTranGrp_BizTrans().getValues().size());

        // 取引ロールの登録
        BizTranRoles bizTranRoles = storeBizTranRoles(bizTranRoleComposition.getBizTranRoles());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store storeBizTranRoles 取引ロールの登録 end count="+bizTranRoleComposition.getBizTranRoles().getValues().size());
        // 取引ロール_取引グループ割当の追加
        insertBizTranRole_BizTranGrps(bizTranRoleComposition.getBizTranRole_BizTranGrps(),
            bizTranRoles,
            bizTranGrps,
            bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store insertBizTranRole_BizTranGrps 取引ロール_取引グループ割当の追加 end count="+bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues().size());
    }

    /**
     * 取引の登録を行います
     *
     * @param bizTrans 取引群（登録対象）
     * @return 取引群（登録後）
     */
    BizTrans storeBizTrans(BizTrans bizTrans) {

        // 取引削除
        BizTranEntityCriteria criteria = new BizTranEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(bizTrans.getValues().get(0).getSubSystemCode());
        bizTranEntityDao.forceDelete(criteria);

        // 取引追加
        List<BizTran> returnList = newArrayList();
        for (BizTran bizTran : bizTrans.getValues()) {
            BizTranEntity entity = new BizTranEntity();
            entity.setBizTranCode(bizTran.getBizTranCode());
            entity.setBizTranName(bizTran.getBizTranName());
            entity.setIsCenterBizTran(bizTran.getIsCenterBizTran());
            entity.setExpirationStartDate(bizTran.getExpirationStartDate());
            entity.setExpirationEndDate(bizTran.getExpirationStartDate());
            entity.setSubSystemCode(bizTran.getSubSystemCode());
            bizTranEntityDao.insert(entity);

            returnList.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getExpirationStartDate(),
                entity.getExpirationEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                null));
        }
        return BizTrans.createFrom(returnList);
    }

    /**
     * 取引グループの登録を行います
     *
     * @param bizTranGrps 取引グループ群（登録対象）
     * @return 取引グループ群（登録後）
     */
    BizTranGrps storeBizTranGrps(BizTranGrps bizTranGrps) {

        // 取引グループ削除
        BizTranGrpEntityCriteria criteria = new BizTranGrpEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(bizTranGrps.getValues().get(0).getSubSystemCode());
        bizTranGrpEntityDao.forceDelete(criteria);


        // 取引グループ追加
        List<BizTranGrp> returnList = newArrayList();
        for (BizTranGrp bizTranGrp : bizTranGrps.getValues()) {
            BizTranGrpEntity entity = new BizTranGrpEntity();
            entity.setBizTranGrpCode(bizTranGrp.getBizTranGrpCode());
            entity.setBizTranGrpName(bizTranGrp.getBizTranGrpName());
            entity.setSubSystemCode(bizTranGrp.getSubSystemCode());
            bizTranGrpEntityDao.insert(entity);

            returnList.add(BizTranGrp.createFrom(
                entity.getBizTranGrpId(),
                entity.getBizTranGrpCode(),
                entity.getBizTranGrpName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                null));
        }
        return BizTranGrps.createFrom(returnList);
    }

    /**
     * 取引グループ_取引割当の削除を行います
     *
     * @param subSystemCode サブシステムコード
     */
    void deleteBizTranGrp_BizTrans(String subSystemCode) {
        BizTranGrp_BizTranEntityCriteria criteria = new BizTranGrp_BizTranEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranGrp_BizTranEntityDao.forceDelete(criteria);
    }

    /**
     * 取引グループ_取引割当の追加を行います
     *
     * @param bizTranGrp_BizTrans 取引グループ_取引割当群（追加対象）
     * @param bizTranGrps         取引グループ群
     * @param bizTrans            取引群
     * @param subSystemCode       サブシステムコード
     */
    void insertBizTranGrp_BizTrans(BizTranGrp_BizTrans bizTranGrp_BizTrans,
        BizTranGrps bizTranGrps,
        BizTrans bizTrans,
        String subSystemCode) {

        List<BizTranGrp_BizTran> bizTranGrp_BizTranList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : bizTranGrp_BizTrans.getValues()) {
            BizTranGrp bizTranGrp = bizTranGrps.getValues().stream().filter(
                b -> b.getBizTranGrpCode().equals(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode())).findFirst().orElse(null);
            BizTran bizTran = bizTrans.getValues().stream().filter(
                b -> b.getBizTranCode().equals(bizTranGrp_BizTran.getBizTran().getBizTranCode())).findFirst().orElse(null);

            if (bizTranGrp != null && bizTran != null) {
                BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
                entity.setBizTranGrpId(bizTranGrp.getBizTranGrpId());
                entity.setBizTranId(bizTran.getBizTranId());
                entity.setSubSystemCode(subSystemCode);
                bizTranGrp_BizTranEntityDao.insert(entity);
            }
        }
    }

    /**
     * 取引ロールの登録を行います
     *
     * @param bizTranRoles 取引ロール群（登録対象）
     * @return 取引ロール群（登録後）
     */
    BizTranRoles storeBizTranRoles(BizTranRoles bizTranRoles) {

        // 取引ロール検索（登録前）
        BizTranRoleCriteria criteria = new BizTranRoleCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(bizTranRoles.getValues().get(0).getSubSystemCode());
        BizTranRoles bizTranRolesBefore = bizTranRolesRepository.selectBy(criteria, Orders.empty());

        // 取引ロール更新／追加
        List<BizTranRole> returnList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues()) {
            BizTranRole bizTranRoleBefore = bizTranRolesBefore.getValues().stream().filter(b->b.getBizTranRoleCode().equals(bizTranRole.getBizTranRoleCode())).findFirst().orElse(null);
            BizTranRoleEntity entity = new BizTranRoleEntity();
            if (bizTranRoleBefore == null) {
                // 追加
                entity.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
                entity.setBizTranRoleName(bizTranRole.getBizTranRoleName());
                entity.setSubSystemCode(bizTranRole.getSubSystemCode());
                bizTranRoleEntityDao.insert(entity);
            } else {
                // 更新
                entity.setBizTranRoleId(bizTranRoleBefore.getBizTranRoleId());
                entity.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
                entity.setBizTranRoleName(bizTranRole.getBizTranRoleName());
                entity.setRecordVersion(bizTranRoleBefore.getRecordVersion());
                bizTranRoleEntityDao.updateExcludeNull(entity);
            }

            returnList.add(BizTranRole.createFrom(
                entity.getBizTranRoleId(),
                entity.getBizTranRoleCode(),
                entity.getBizTranRoleName(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                null));
        }

        // 取引ロール削除
        for (BizTranRole bizTranRoleBefore : bizTranRolesBefore.getValues()) {
            if (bizTranRoles.getValues().stream().filter(b->b.getBizTranRoleCode().equals(bizTranRoleBefore.getBizTranRoleCode())).count() == 0) {
                // 削除対象
                BizTranRoleEntity entity = new BizTranRoleEntity();
                entity.setBizTranRoleId(bizTranRoleBefore.getBizTranRoleId());
                entity.setRecordVersion(bizTranRoleBefore.getRecordVersion());
                bizTranRoleEntityDao.delete(entity);
            }
        }

        return BizTranRoles.createFrom(returnList);
    }

    /**
     * 取引ロール_取引グループ割当の削除を行います
     *
     * @param subSystemCode サブシステムコード
     */
    void deleteBizTranRole_BizTranGrps(String subSystemCode) {
        BizTranRole_BizTranGrpEntityCriteria criteria = new BizTranRole_BizTranGrpEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranRole_BizTranGrpEntityDao.forceDelete(criteria);
    }

    /**
     * 取引ロール_取引グループ割当の追加を行います
     *
     * @param bizTranRole_BizTranGrps 取引ロール_取引グループ割当群
     * @param bizTranRoles            取引ロール群
     * @param bizTranGrps             取引グループ群
     * @param subSystemCode           サブシステムコード
     */
    void insertBizTranRole_BizTranGrps(BizTranRole_BizTranGrps bizTranRole_BizTranGrps,
        BizTranRoles bizTranRoles,
        BizTranGrps bizTranGrps,
        String subSystemCode) {

        // 登録対象の取引ロール_取引グループ割当を抽出
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : bizTranRole_BizTranGrps.getValues()) {
            BizTranRole bizTranRole = bizTranRoles.getValues().stream().filter(
                b->b.getBizTranRoleCode().equals(bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode())).findFirst().orElse(null);
            BizTranGrp bizTranGrp = bizTranGrps.getValues().stream().filter(
                b->b.getBizTranGrpCode().equals(bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode())).findFirst().orElse(null);

            if (bizTranRole != null && bizTranGrp != null) {
                BizTranRole_BizTranGrpEntity entity = new BizTranRole_BizTranGrpEntity();
                entity.setBizTranRoleId(bizTranRole.getBizTranRoleId());
                entity.setBizTranGrpId(bizTranGrp.getBizTranGrpId());
                entity.setSubSystemCode(subSystemCode);
                bizTranRole_BizTranGrpEntityDao.insert(entity);
            }
        }
    }
}
