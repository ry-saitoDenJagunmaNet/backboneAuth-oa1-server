package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorsDataSourceTest {

    // 実行既定値
    // オペレーターDaoの作成
    private OperatorEntityDao createOperatorEntityDao() {
        return new OperatorEntityDao() {
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return createOperatorEntityList();
            }
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int update(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int delete(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
        };
    }
    private BranchAtMomentRepository createBranchAtMomentRepository() {
        return new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                return null;
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };
    }
    private List<OperatorEntity> createOperatorEntityList() {
        List<OperatorEntity> list = newArrayList();
        list.add(createOperatorEntity(18L,"yu001009","ｙｕ００１００９","yu001009@aaaaa.net",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",(short)0));
        list.add(createOperatorEntity(19L,"yu001010","ｙｕ００１０１０","yu001010@aaaaa.net",LocalDate.of(2020,4,1),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",(short)0));
        list.add(createOperatorEntity(20L,"yu001011","ｙｕ００１０１１","yu001011@aaaaa.net",LocalDate.of(2020,1,1),LocalDate.of(2020,12,31),true,6L,"006",33L,"001",(short)0));
        return list;
    }
    private OperatorEntity createOperatorEntity(
        Long operatorId,
        String operatorCode,
        String operatorName,
        String mailAddress,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        Boolean isDeviceAuth,
        Long jaId,
        String jaCode,
        Long branchId,
        String branchCode,
        Short availableStatus) {

        OperatorEntity entity = new OperatorEntity();
        entity.setOperatorId(operatorId);
        entity.setOperatorCode(operatorCode);
        entity.setOperatorName(operatorName);
        entity.setMailAddress(mailAddress);
        entity.setValidThruStartDate(validThruStartDate);
        entity.setValidThruEndDate(validThruEndDate);
        entity.setIsDeviceAuth(isDeviceAuth);
        entity.setJaId(jaId);
        entity.setJaCode(jaCode);
        entity.setBranchId(branchId);
        entity.setBranchCode(branchCode);
        entity.setAvailableStatus(availableStatus);
        return entity;
    }
    private static BranchAtMoment createBranchAtMoment(OperatorEntity entity) {
        JaAtMoment jaAtMoment = JaAtMoment.builder()
            .withIdentifier(entity.getJaId())
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(entity.getJaCode()))
                .withName("ＪＡ"+entity.getJaCode())
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
        return BranchAtMoment.builder()
            .withIdentifier(entity.getBranchId())
            .withJaAtMoment(jaAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(entity.getBranchCode()))
                .withName(entity.getBranchCode()+"店舗")
                .build())
            .build();
    }

    /**
     * {@link OperatorsDataSource#selectBy(OperatorCriteria, Orders)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneBy_test0() {

        // 実行値
        OperatorCriteria criteria = new OperatorCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        OperatorsDataSource operatorsDataSource = new OperatorsDataSource(createOperatorEntityDao(),
            createBranchAtMomentRepository());

        // 期待値
        List<Operator> expectedOperatorlist = newArrayList();
        for(OperatorEntity entity : createOperatorEntityList()) {
            expectedOperatorlist.add(Operator.createFrom(
                entity.getOperatorId(),
                entity.getOperatorCode(),
                entity.getOperatorName(),
                entity.getMailAddress(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getIsDeviceAuth(),
                entity.getJaId(),
                entity.getJaCode(),
                entity.getBranchId(),
                entity.getBranchCode(),
                AvailableStatus.codeOf(entity.getAvailableStatus()),
                entity.getRecordVersion(),
                createBranchAtMoment(entity)
            ));
        }
        Operators expectedOperators = Operators.createFrom(expectedOperatorlist);

        // 実行
        Operators actualOperators = operatorsDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualOperators.getValues().size(); i++) {
            assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
        }
    }
}