package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRolesRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import org.springframework.stereotype.Component;

/**
 * 取引ロール群登録
 */
@Component
public class BizTranRolesForStoreDataSource implements BizTranRolesRepositoryForStore {

    private final BizTranRoleEntityDao bizTranRoleEntityDao;

    // コンストラクタ
    BizTranRolesForStoreDataSource(BizTranRoleEntityDao bizTranRoleEntityDao) {
        this.bizTranRoleEntityDao = bizTranRoleEntityDao;
    }

    /**
     * 取引ロールの登録を行います
     *
     * @param bizTranRoles 取引ロール群
     * @return 取引ロール群（登録後）
     */
    public BizTranRoles store(BizTranRoles bizTranRoles) {

        // 取引ロール削除
        delete(bizTranRoles.getValues().get(0).getSubSystemCode());

        // 取引ロール追加
        List<BizTranRole> returnList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues()) {
            returnList.add(insert(bizTranRole));
        }
        return BizTranRoles.createFrom(returnList);
    }

    /**
     * 取引ロールの削除を行います
     *
     * @param subSystemCode サブシステムコード
     */
    void delete(String subSystemCode) {
        BizTranRoleEntityCriteria criteria = new BizTranRoleEntityCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        bizTranRoleEntityDao.forceDelete(criteria);
    }

    /**
     * 取引ロールの追加を行います
     *
     * @param bizTranRole 取引ロール
     * @return 取引ロール
     */
    BizTranRole insert(BizTranRole bizTranRole) {
        BizTranRoleEntity entity = new BizTranRoleEntity();
        entity.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
        entity.setBizTranRoleName(bizTranRole.getBizTranRoleName());
        entity.setSubSystemCode(bizTranRole.getSubSystemCode());
        entity.setSubSystemCode(bizTranRole.getSubSystemCode());
        bizTranRoleEntityDao.insert(entity);
        return BizTranRole.createFrom(
            entity.getBizTranRoleId(),
            entity.getBizTranRoleCode(),
            entity.getBizTranRoleName(),
            entity.getSubSystemCode(),
            entity.getRecordVersion(),
            null);
    }
}
