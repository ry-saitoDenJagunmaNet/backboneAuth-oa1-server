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
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleCompositionRepositoryForStore;
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
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ???????????????????????????
 */
@Component
public class BizTranRoleCompositionForStoreDataSource implements BizTranRoleCompositionRepositoryForStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizTranRoleCompositionForStoreDataSource.class);

    private final BizTranRoleEntityDao bizTranRoleEntityDao;
    private final BizTranGrpEntityDao bizTranGrpEntityDao;
    private final BizTranEntityDao bizTranEntityDao;
    private final BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao;
    private final BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao;
    private final BizTranRoleRepository bizTranRoleRepository;

    // ?????????????????????
    BizTranRoleCompositionForStoreDataSource(
        BizTranRoleEntityDao bizTranRoleEntityDao,
        BizTranGrpEntityDao bizTranGrpEntityDao,
        BizTranEntityDao bizTranEntityDao,
        BizTranRole_BizTranGrpEntityDao bizTranRole_BizTranGrpEntityDao,
        BizTranGrp_BizTranEntityDao bizTranGrp_BizTranEntityDao,
        BizTranRoleRepository bizTranRoleRepository) {

        this.bizTranRoleEntityDao = bizTranRoleEntityDao;
        this.bizTranGrpEntityDao = bizTranGrpEntityDao;
        this.bizTranEntityDao = bizTranEntityDao;
        this.bizTranRole_BizTranGrpEntityDao = bizTranRole_BizTranGrpEntityDao;
        this.bizTranGrp_BizTranEntityDao = bizTranGrp_BizTranEntityDao;
        this.bizTranRoleRepository = bizTranRoleRepository;
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param bizTranRoleComposition ?????????????????????
     * @return ?????????????????????????????????
     */
    public BizTranRoleComposition store(BizTranRoleComposition bizTranRoleComposition) {

        // ??????????????????_?????????????????????
        deleteBizTranGrp_BizTrans(bizTranRoleComposition.getBizTranGrp_BizTrans().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store deleteBizTranGrp_BizTrans ??????????????????_????????????????????? end");
        // ???????????????_?????????????????????????????????
        deleteBizTranRole_BizTranGrps(bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store deleteBizTranRole_BizTranGrps ???????????????_????????????????????????????????? end");

        // ???????????????
        BizTrans bizTrans = storeBizTrans(bizTranRoleComposition.getBizTrans());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store storeBizTrans ??????????????? end count="+bizTranRoleComposition.getBizTrans().getValues().size());
        // ???????????????????????????
        BizTranGrps bizTranGrps = storeBizTranGrps(bizTranRoleComposition.getBizTranGrps());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store storeBizTranGrps ??????????????????????????? end count="+bizTranRoleComposition.getBizTranGrps().getValues().size());
        // ??????????????????_?????????????????????
        BizTranGrp_BizTrans bizTranGrp_BizTrans = insertBizTranGrp_BizTrans(bizTranRoleComposition.getBizTranGrp_BizTrans(),
            bizTranGrps,
            bizTrans,
            bizTranRoleComposition.getBizTranGrp_BizTrans().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store insertBizTranGrp_BizTrans( ??????????????????_????????????????????? end count="+bizTranRoleComposition.getBizTranGrp_BizTrans().getValues().size());

        // ????????????????????????
        BizTranRoles bizTranRoles = storeBizTranRoles(bizTranRoleComposition.getBizTranRoles());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store storeBizTranRoles ???????????????????????? end count="+bizTranRoleComposition.getBizTranRoles().getValues().size());
        // ???????????????_?????????????????????????????????
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = insertBizTranRole_BizTranGrps(bizTranRoleComposition.getBizTranRole_BizTranGrps(),
            bizTranRoles,
            bizTranGrps,
            bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues().get(0).getSubSystemCode());
        LOGGER.debug("### BizTranRoleCompositionForStoreDataSource.store insertBizTranRole_BizTranGrps ???????????????_????????????????????????????????? end count="+bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues().size());

        return BizTranRoleComposition.createFrom(
            bizTrans,
            bizTranGrps,
            bizTranGrp_BizTrans,
            bizTranRoles,
            bizTranRole_BizTranGrps);
    }

    /**
     * ??????????????????????????????
     *
     * @param bizTrans ???????????????????????????
     * @return ?????????????????????
     */
    BizTrans storeBizTrans(BizTrans bizTrans) {

        // ????????????
        BizTranEntityCriteria criteria = new BizTranEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(bizTrans.getValues().get(0).getSubSystemCode());
        bizTranEntityDao.forceDelete(criteria);

        // ????????????
        List<BizTran> returnList = newArrayList();
        for (BizTran bizTran : bizTrans.getValues()) {
            BizTranEntity entity = new BizTranEntity();
            entity.setBizTranCode(bizTran.getBizTranCode());
            entity.setBizTranName(bizTran.getBizTranName());
            entity.setIsCenterBizTran(bizTran.getIsCenterBizTran());
            entity.setValidThruStartDate(bizTran.getValidThruStartDate());
            entity.setValidThruEndDate(bizTran.getValidThruEndDate());
            entity.setSubSystemCode(bizTran.getSubSystemCode());
            bizTranEntityDao.insert(entity);

            returnList.add(BizTran.createFrom(
                entity.getBizTranId(),
                entity.getBizTranCode(),
                entity.getBizTranName(),
                entity.getIsCenterBizTran(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                null));
        }
        return BizTrans.createFrom(returnList);
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param bizTranGrps ???????????????????????????????????????
     * @return ???????????????????????????
     */
    BizTranGrps storeBizTranGrps(BizTranGrps bizTranGrps) {

        // ????????????????????????
        BizTranGrpEntityCriteria criteria = new BizTranGrpEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(bizTranGrps.getValues().get(0).getSubSystemCode());
        bizTranGrpEntityDao.forceDelete(criteria);

        // ????????????????????????
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
     * ??????????????????_????????????????????????????????????
     *
     * @param subSystemCode ???????????????????????????
     */
    void deleteBizTranGrp_BizTrans(String subSystemCode) {
        BizTranGrp_BizTranEntityCriteria criteria = new BizTranGrp_BizTranEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranGrp_BizTranEntityDao.forceDelete(criteria);
    }

    /**
     * ??????????????????_????????????????????????????????????
     *
     * @param bizTranGrp_BizTrans ??????????????????_?????????????????????????????????
     * @param bizTranGrps         ?????????????????????
     * @param bizTrans            ?????????
     * @param subSystemCode       ???????????????????????????
     * @return ??????????????????????????????_???????????????
     */
    BizTranGrp_BizTrans insertBizTranGrp_BizTrans(BizTranGrp_BizTrans bizTranGrp_BizTrans,
        BizTranGrps bizTranGrps,
        BizTrans bizTrans,
        String subSystemCode) {

        List<BizTranGrp_BizTran> bizTranGrp_BizTranList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : bizTranGrp_BizTrans.getValues()) {
            BizTranGrp bizTranGrp = bizTranGrps.getValues().stream().filter(
                b -> b.getBizTranGrpCode().equals(bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode())).findFirst().orElse(null);
            BizTran bizTran = bizTrans.getValues().stream().filter(
                b -> b.getBizTranCode().equals(bizTranGrp_BizTran.getBizTran().getBizTranCode())).findFirst().orElse(null);

            BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
            entity.setBizTranGrpId(bizTranGrp.getBizTranGrpId());
            entity.setBizTranId(bizTran.getBizTranId());
            entity.setSubSystemCode(subSystemCode);
            bizTranGrp_BizTranEntityDao.insert(entity);

            bizTranGrp_BizTranList.add(BizTranGrp_BizTran.createFrom(
                entity.getBizTranGrp_BizTranId(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                bizTranGrp,
                bizTran,
                SubSystem.codeOf(subSystemCode)));
        }

        return BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranList);
    }

    /**
     * ???????????????????????????????????????
     *
     * @param bizTranRoles ????????????????????????????????????
     * @return ????????????????????????
     */
    BizTranRoles storeBizTranRoles(BizTranRoles bizTranRoles) {

        // ????????????????????????????????????
        BizTranRoleCriteria criteria = new BizTranRoleCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(bizTranRoles.getValues().get(0).getSubSystemCode());
        BizTranRoles bizTranRolesBefore = bizTranRoleRepository.selectBy(criteria, Orders.empty());

        // ??????????????????????????????
        List<BizTranRole> returnList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues()) {
            BizTranRole bizTranRoleBefore = bizTranRolesBefore.getValues().stream().filter(b->b.getBizTranRoleCode().equals(bizTranRole.getBizTranRoleCode())).findFirst().orElse(null);
            BizTranRoleEntity entity = new BizTranRoleEntity();
            if (bizTranRoleBefore == null) {
                // ??????
                entity.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
                entity.setBizTranRoleName(bizTranRole.getBizTranRoleName());
                entity.setSubSystemCode(bizTranRole.getSubSystemCode());
                bizTranRoleEntityDao.insert(entity);
            } else {
                // ??????
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

        // ?????????????????????
        for (BizTranRole bizTranRoleBefore : bizTranRolesBefore.getValues()) {
            if (bizTranRoles.getValues().stream().filter(b->b.getBizTranRoleCode().equals(bizTranRoleBefore.getBizTranRoleCode())).count() == 0) {
                // ????????????
                BizTranRoleEntity entity = new BizTranRoleEntity();
                entity.setBizTranRoleId(bizTranRoleBefore.getBizTranRoleId());
                entity.setRecordVersion(bizTranRoleBefore.getRecordVersion());
                bizTranRoleEntityDao.delete(entity);
            }
        }

        return BizTranRoles.createFrom(returnList);
    }

    /**
     * ???????????????_????????????????????????????????????????????????
     *
     * @param subSystemCode ???????????????????????????
     */
    void deleteBizTranRole_BizTranGrps(String subSystemCode) {
        BizTranRole_BizTranGrpEntityCriteria criteria = new BizTranRole_BizTranGrpEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranRole_BizTranGrpEntityDao.forceDelete(criteria);
    }

    /**
     * ???????????????_????????????????????????????????????????????????
     *
     * @param bizTranRole_BizTranGrps ???????????????_?????????????????????????????????????????????
     * @param bizTranRoles            ??????????????????
     * @param bizTranGrps             ?????????????????????
     * @param subSystemCode           ???????????????????????????
     * @return ???????????????????????????_???????????????????????????
     */
    BizTranRole_BizTranGrps insertBizTranRole_BizTranGrps(BizTranRole_BizTranGrps bizTranRole_BizTranGrps,
        BizTranRoles bizTranRoles,
        BizTranGrps bizTranGrps,
        String subSystemCode) {

        // ??????????????????????????????_?????????????????????????????????
        List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList = newArrayList();
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : bizTranRole_BizTranGrps.getValues()) {
            BizTranRole bizTranRole = bizTranRoles.getValues().stream().filter(
                b->b.getBizTranRoleCode().equals(bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode())).findFirst().orElse(null);
            BizTranGrp bizTranGrp = bizTranGrps.getValues().stream().filter(
                b->b.getBizTranGrpCode().equals(bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode())).findFirst().orElse(null);

            BizTranRole_BizTranGrpEntity entity = new BizTranRole_BizTranGrpEntity();
            entity.setBizTranRoleId(bizTranRole.getBizTranRoleId());
            entity.setBizTranGrpId(bizTranGrp.getBizTranGrpId());
            entity.setSubSystemCode(subSystemCode);
            bizTranRole_BizTranGrpEntityDao.insert(entity);

            bizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                entity.getBizTranRole_BizTranGrpId(),
                entity.getBizTranRoleId(),
                entity.getBizTranGrpId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                bizTranRole,
                bizTranGrp,
                SubSystem.codeOf(subSystemCode)));
        }

        return BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpList);
    }
}
