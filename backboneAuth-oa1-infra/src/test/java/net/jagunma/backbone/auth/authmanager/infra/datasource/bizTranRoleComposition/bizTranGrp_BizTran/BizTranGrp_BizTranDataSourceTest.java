package net.jagunma.backbone.auth.authmanager.infra.datasource.bizTranRoleComposition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTranDataSourceTest {

    // 実行既定値
    private Long bizTranGrp_BizTranId = 12345678L;
    private Long bizTranGrpId = 10001L;
    private String bizTranGrpCode = "ANTG01";
    private String bizTranGrpName = "データ入力取引グループ";
    private Long bizTranId = 100001L;
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

    // 取引グループ_取引割当Daoの作成
    private BizTranGrp_BizTranEntityDao createBizTranGrp_BizTranEntityDao() {
        return new BizTranGrp_BizTranEntityDao() {
            @Override
            public BizTranGrp_BizTranEntity findOneBy(BizTranGrp_BizTranEntityCriteria criteria) {
                return createBizTranGrp_BizTranEntity();
            }
            @Override
            public List<BizTranGrp_BizTranEntity> findBy(BizTranGrp_BizTranEntityCriteria criteria, Orders orders) {
                return createBizTranGrp_BizTranEntityList();
            }
            @Override
            public List<BizTranGrp_BizTranEntity> findAll(Orders orders) {
                return createBizTranGrp_BizTranEntityList();
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
    // 取引グループRepositoryの作成
    private BizTranGrpRepository createBizTranGrpRepository() {
        return new BizTranGrpRepository() {
            @Override
            public BizTranGrp findOneByCode(String bizTranGrpCode) {
                return createBizTranGrp();
            }
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
    // 取引Repositoryの作成
    private BizTranRepository createBizTranRepository() {
        return new BizTranRepository() {
            @Override
            public BizTran findOneByCode(String bizTranCode) {
                return createBizTran();
            }
            @Override
            public BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders) {
                return createBizTrans();
            }
            @Override
            public BizTrans selectAll(Orders orders) {
                return createBizTrans();
            }
        };
    }
    // 取引グループ_取引割当データ作成
    private BizTranGrp_BizTranEntity createBizTranGrp_BizTranEntity() {
        BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
        entity.setBizTranGrp_BizTranId(bizTranGrp_BizTranId);
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranId(bizTranId);
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
    // 取引グループデータ作成
    private BizTranGrp createBizTranGrp() {
        return BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode));
    }
    // 取引データ作成
    private BizTran createBizTran() {
        return BizTran.createFrom(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate,
            subSystemCode,
            recordVersion,
            SubSystem.codeOf(subSystemCode));
    }
    // 取引グループ_取引割当リストデータ作成
    private List<BizTranGrp_BizTranEntity> createBizTranGrp_BizTranEntityList() {
        List<BizTranGrp_BizTranEntity> list = newArrayList();
        list.add(createBizTranGrp_BizTranEntity(1L,100001L,10000001L,SubSystem.販売_畜産.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranGrp_BizTranEntity(2L,100002L,10000002L,SubSystem.販売_畜産.getCode(),null,null,null,null,null,null,1));
        list.add(createBizTranGrp_BizTranEntity(3L,100003L,10000003L,SubSystem.販売_畜産.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // 取引グループ_取引割当データ作成
    private BizTranGrp_BizTranEntity createBizTranGrp_BizTranEntity(
        Long bizTranGrp_BizTranId,
        Long bizTranGrpId,
        Long bizTranId,
        String subSystemCode,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        BizTranGrp_BizTranEntity entity = new BizTranGrp_BizTranEntity();
        entity.setBizTranGrp_BizTranId(bizTranGrp_BizTranId);
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranId(bizTranId);
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
    // 取引グループ群データ作成
    private BizTranGrps createBizTranGrps() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(100001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(100002L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(100003L,"ANTG01","センター維持管理グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranGrps.createFrom(list);
    }
    // 取引群データ作成
    private BizTrans createBizTrans() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(10000001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2020,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(10000002L,"AN1510","精算取消",false,LocalDate.of(2020,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(10000003L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2020,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTrans.createFrom(list);
    }

    /**
     * {@link BizTranGrp_BizTranDataSource#selectBy(BizTranGrp_BizTranCriteria,Orders)}のテスト
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
        BizTranGrp_BizTranCriteria criteria = new BizTranGrp_BizTranCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        BizTranGrp_BizTranDataSource bizTranGrp_BizTranDataSource = new BizTranGrp_BizTranDataSource(
            createBizTranGrp_BizTranEntityDao(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // 期待値
        List<BizTranGrp_BizTran> expectedBizTranGrp_BizTranList = newArrayList();
        for(BizTranGrp_BizTranEntity entity : createBizTranGrp_BizTranEntityList()) {
            expectedBizTranGrp_BizTranList.add(BizTranGrp_BizTran.createFrom(
                entity.getBizTranGrp_BizTranId(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                createBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                createBizTrans().getValues().stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans = bizTranGrp_BizTranDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranList.get(i));
        }
    }

    /**
     * {@link BizTranGrp_BizTranDataSource#selectAll(Orders)}のテスト
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
        BizTranGrp_BizTranDataSource bizTranGrp_BizTranDataSource = new BizTranGrp_BizTranDataSource(
            createBizTranGrp_BizTranEntityDao(),
            createBizTranGrpRepository(),
            createBizTranRepository());

        // 期待値
        List<BizTranGrp_BizTran> expectedBizTranGrp_BizTranList = newArrayList();
        for(BizTranGrp_BizTranEntity entity : createBizTranGrp_BizTranEntityList()) {
            expectedBizTranGrp_BizTranList.add(BizTranGrp_BizTran.createFrom(
                entity.getBizTranGrp_BizTranId(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSubSystemCode(),
                entity.getRecordVersion(),
                createBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                createBizTrans().getValues().stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        // 実行
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans = bizTranGrp_BizTranDataSource.selectAll(orders);

        // 結果検証
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranList.get(i));
        }
    }
}