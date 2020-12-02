package net.jagunma.backbone.auth.authmanager.infra.datasource.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityCriteria;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchNotFoundException;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SuspendBizTransDataSourceTest {

    // 実行既定値
    private List<SuspendBizTranEntity> suspendBizTranEntityList = createSuspendBizTranEntityList();

    // 一時取引抑止リストデータ作成
    private List<SuspendBizTranEntity> createSuspendBizTranEntityList() {
        List<SuspendBizTranEntity> list = newArrayList();
        list.add(createSuspendBizTranEntity(1L,6L,33L,SubSystem.販売_畜産.getCode(),10001L,null,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"抑止理由",18L,LocalDateTime.of(2020,10,31,8,30,12),"001.001.001.001",null,null,null,1));
        list.add(createSuspendBizTranEntity(2L,null,null,SubSystem.販売_畜産.getCode(),null,100001L,LocalDate.of(2020,11,1),LocalDate.of(2020,11,2),"抑止理由",18L,LocalDateTime.of(2020,10,31,8,30,12),"001.001.001.001",null,null,null,1));
        return list;
    }
    // 一時取引抑止データ作成
    private SuspendBizTranEntity createSuspendBizTranEntity(
        Long suspendBizTranId,
        Long jaId,
        Long branchId,
        String subSystemCode,
        Long bizTranGrpId,
        Long bizTranId,
        LocalDate suspendStartDate,
        LocalDate suspendEndDate,
        String suspendReason,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        SuspendBizTranEntity entity = new SuspendBizTranEntity();
        entity.setSuspendBizTranId(suspendBizTranId);
        entity.setJaId(jaId);
        entity.setBranchId(branchId);
        entity.setSubSystemCode(subSystemCode);
        entity.setBizTranGrpId(bizTranGrpId);
        entity.setBizTranId(bizTranId);
        entity.setSuspendStartDate(suspendStartDate);
        entity.setSuspendEndDate(suspendEndDate);
        entity.setSuspendReason(suspendReason);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // BranchAtMomentリストデータ作成
    private List<BranchAtMoment> createBranchAtMomentList() {
        List<JaAtMoment> jaAtMomentList = createJaAtMomentList();
        List<BranchAtMoment> list = newArrayList();
        list.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(jaAtMomentList.stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("店舗001").build()).build());
        list.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(jaAtMomentList.stream().filter(j->j.getIdentifier().equals(6L)).findFirst().orElse(null))
            .withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build()).build());
        return list;
    }
    // JaAtMomentリストデータ作成
    private List<JaAtMoment> createJaAtMomentList() {
        List<JaAtMoment> list = newArrayList();
        list.add(JaAtMoment.builder().withIdentifier(6L).withJaAttribute(JaAttribute.builder().withJaCode(JaCode.of("006")).withName("ＪＡ006").withFormalName("").withAbbreviatedName("").build()).build());
        return list;
    }
    // 取引グループリストデータ作成
    private List<BizTranGrp> createBizTranGrp() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10002L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }
    // 取引リストデータ作成
    private List<BizTran> createBizTran() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100002L,"AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return list;
    }

    // 一時取引抑止Repositoryの作成
    private SuspendBizTranEntityDao createSuspendBizTranEntityDao() {
        return new SuspendBizTranEntityDao() {
            @Override
            public List<SuspendBizTranEntity> findBy(SuspendBizTranEntityCriteria criteria, Orders orders) {
                return suspendBizTranEntityList;
            }
            @Override
            public List<SuspendBizTranEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public SuspendBizTranEntity findOneBy(SuspendBizTranEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(SuspendBizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int update(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int delete(SuspendBizTranEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(SuspendBizTranEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<SuspendBizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<SuspendBizTranEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<SuspendBizTranEntity> entities) {
                return new int[0];
            }
        };
    }
    // JaAtMomentRepositoryの作成
    private JaAtMomentRepository createJaAtMomentRepository() {
        return new JaAtMomentRepository() {
            @Override
            public JasAtMoment selectBy(JaAtMomentCriteria criteria, Orders orders) {
                return JasAtMoment.of(createJaAtMomentList());
            }
            @Override
            public JaAtMoment findOneBy(JaAtMomentCriteria criteria) {
                return null;
            }
        };
    }
    // BranchAtMomentRepositoryの作成
    private BranchAtMomentRepository createBranchAtMomentRepository() {
        return new BranchAtMomentRepository() {
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return BranchesAtMoment.of(createBranchAtMomentList());
            }
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) throws BranchNotFoundException {
                return null;
            }
        };
    }
    // 取引グループRepositoryの作成
    private BizTranGrpsRepository createBizTranGrpsRepository() {
        return new BizTranGrpsRepository() {
            @Override
            public BizTranGrps selectBy(BizTranGrpCriteria bizTranGrpCriteria, Orders orders) {
                return BizTranGrps.createFrom(createBizTranGrp());
            }
            @Override
            public BizTranGrps selectAll(Orders orders) {
                return null;
            }
        };
    }
    // 取引Repositoryの作成
    private BizTransRepository createBizTransRepository() {
        return new BizTransRepository() {
            @Override
            public BizTrans selectBy(BizTranCriteria bizTranCriteria, Orders orders) {
                return BizTrans.createFrom(createBizTran());
            }
            @Override
            public BizTrans selectAll(Orders orders) {
                return null;
            }
        };
    }

    /**
     * {@link SuspendBizTransDataSource#selectBy(SuspendBizTranCriteria,Orders)}のテスト
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
        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        Orders orders = Orders.empty()
            .addOrder("suspendStartDate")
            .addOrder("suspendEndDate")
            .addOrder("jaCode")
            .addOrder("branchCode")
            .addOrder("subSystemDisplaySortOrder")
            .addOrder("bizTranGrpCode")
            .addOrder("bizTranCode");

        // テスト対象クラス生成
        SuspendBizTransDataSource suspendBizTransDataSource = new SuspendBizTransDataSource(
            createSuspendBizTranEntityDao(),
            createJaAtMomentRepository(),
            createBranchAtMomentRepository(),
            createBizTranGrpsRepository(),
            createBizTransRepository());

        // 期待値
        List<JaAtMoment> jaAtMomentList = createJaAtMomentList();
        List<BranchAtMoment> branchAtMomentList = createBranchAtMomentList();
        List<BizTranGrp> bizTranGrpList = createBizTranGrp();
        List<BizTran> bizTranList = createBizTran();
        List<SuspendBizTran> expectedSuspendBizTranList = newArrayList();
        for(SuspendBizTranEntity entity : suspendBizTranEntityList) {
            expectedSuspendBizTranList.add(SuspendBizTran.createFrom(
                entity.getSuspendBizTranId(),
                entity.getJaId(),
                entity.getBranchId(),
                entity.getSubSystemCode(),
                entity.getBizTranGrpId(),
                entity.getBizTranId(),
                entity.getSuspendStartDate(),
                entity.getSuspendEndDate(),
                entity.getSuspendReason(),
                entity.getRecordVersion(),
                jaAtMomentList.stream().filter(j->j.getIdentifier().equals(entity.getJaId())).findFirst().orElse(null),
                branchAtMomentList.stream().filter(b->b.getIdentifier().equals(entity.getBranchId())).findFirst().orElse(null),
                SubSystem.codeOf(entity.getSubSystemCode()),
                bizTranGrpList.stream().filter(b->b.getBizTranGrpId().equals(entity.getBizTranGrpId())).findFirst().orElse(null),
                bizTranList.stream().filter(b->b.getBizTranId().equals(entity.getBizTranId())).findFirst().orElse(null)));
        }
        expectedSuspendBizTranList = expectedSuspendBizTranList.stream().sorted(orders.toComparator()).collect(Collectors.toList());

        // 実行
        SuspendBizTrans actualSuspendBizTrans = suspendBizTransDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualSuspendBizTrans.getValues().size()).isEqualTo(expectedSuspendBizTranList.size());
        for(int i = 0; i < actualSuspendBizTrans.getValues().size(); i++) {
            assertThat(actualSuspendBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSuspendBizTranList.get(i));
        }
    }

    /**
     * {@link SuspendBizTransDataSource#selectBy(SuspendBizTranCriteria,Orders)}のテスト
     *  ●パターン
     *    正常（検索結果0件）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test1() {

        // 実行値
        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        Orders orders = Orders.empty()
            .addOrder("jaCode")
            .addOrder("branchCode")
            .addOrder("subSystemDisplaySortOrder")
            .addOrder("bizTranGrpCode")
            .addOrder("bizTranCode")
            .addOrder("suspendStartDate")
            .addOrder("suspendEndDate");
        suspendBizTranEntityList = newArrayList();

        // テスト対象クラス生成
        SuspendBizTransDataSource suspendBizTransDataSource = new SuspendBizTransDataSource(
            createSuspendBizTranEntityDao(),
            createJaAtMomentRepository(),
            createBranchAtMomentRepository(),
            createBizTranGrpsRepository(),
            createBizTransRepository());

        // 期待値
        List<BranchAtMoment> branchAtMomentList = createBranchAtMomentList();
        List<BizTranGrp> bizTranGrpList = createBizTranGrp();
        List<BizTran> bizTranList = createBizTran();
        List<SuspendBizTran> expectedSuspendBizTranList = newArrayList();

        // 実行
        SuspendBizTrans actualSuspendBizTrans = suspendBizTransDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualSuspendBizTrans.getValues().size()).isEqualTo(expectedSuspendBizTranList.size());
        for(int i = 0; i < actualSuspendBizTrans.getValues().size(); i++) {
            assertThat(actualSuspendBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSuspendBizTranList.get(i));
        }
    }

}