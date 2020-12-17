package net.jagunma.backbone.auth.authmanager.infra.datasource.passwordHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PasswordHistoryDataSourceTest {

    // 実行既定値
    // パスワード履歴Daoの作成
    private PasswordHistoryEntityDao createPasswordHistoryEntityDao() {
        return new PasswordHistoryEntityDao() {
            @Override
            public List<PasswordHistoryEntity> findBy(PasswordHistoryEntityCriteria criteria, Orders orders) {
                return createPasswordHistoryEntityList();
            }
            @Override
            public List<PasswordHistoryEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public PasswordHistoryEntity findOneBy(PasswordHistoryEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(PasswordHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int update(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int delete(PasswordHistoryEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(PasswordHistoryEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<PasswordHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<PasswordHistoryEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<PasswordHistoryEntity> entities) {
                return new int[0];
            }
        };
    }
    // パスワード履歴リストデータ作成
    private List<PasswordHistoryEntity> createPasswordHistoryEntityList() {
        List<PasswordHistoryEntity> list = newArrayList();
        list.add(createPasswordHistoryEntity(1L,18L,LocalDateTime.of(2020,10,28,8,30,12),"abcdefgh",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        list.add(createPasswordHistoryEntity(2L,19L,LocalDateTime.of(2020,10,28,9,31,3),"12345678",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        list.add(createPasswordHistoryEntity(3L,20L,LocalDateTime.of(2020,10,28,10,1,45),"abcd1234",PasswordChangeType.初期.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // パスワード履歴データ作成
    private PasswordHistoryEntity createPasswordHistoryEntity(
        Long passwordHistoryId,
        Long operatorId,
        LocalDateTime changeDateTime,
        String password,
        Short changeType,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        PasswordHistoryEntity entity = new PasswordHistoryEntity();
        entity.setPasswordHistoryId(passwordHistoryId);
        entity.setOperatorId(operatorId);
        entity.setChangeDateTime(changeDateTime);
        entity.setPassword(password);
        entity.setChangeType(changeType);
        entity.setCreatedBy(createdBy);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress(createdIpAddress);
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedAt(updatedAt);
        entity.setUpdatedIpAddress(updatedIpAddress);
        entity.setRecordVersion(recordVersion);
        return entity;
    }
    // オペレータRepositoryの作成
    private OperatorsRepository createOperatorsRepository() {
        return new OperatorsRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return createOperators();
            }
        };
    }
    // オペレータ群データ作成
    private Operators createOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),true,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,31),false,6L,"006",33L,"001",AvailableStatus.利用不可,1,null));
        return Operators.createFrom(list);
    }

    /**
     * {@link PasswordHistoryDataSource#selectBy(PasswordHistoryCriteria,Orders)}のテスト
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
        PasswordHistoryCriteria criteria = new PasswordHistoryCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        PasswordHistoryDataSource passwordHistoryDataSource = new PasswordHistoryDataSource(
            createPasswordHistoryEntityDao(),
            createOperatorsRepository());

        // 期待値
        Operators operators = createOperators();
        List<PasswordHistory> expectedPasswordHistoryList = newArrayList();
        for(PasswordHistoryEntity entity : createPasswordHistoryEntityList()) {
            expectedPasswordHistoryList.add(PasswordHistory.createFrom(
                entity.getPasswordHistoryId(),
                entity.getOperatorId(),
                entity.getChangeDateTime(),
                entity.getPassword(),
                PasswordChangeType.codeOf(entity.getChangeType()),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)));
        }

        // 実行
        PasswordHistories actualPasswordHistories = passwordHistoryDataSource
            .selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualPasswordHistories.getValues().size(); i++) {
            assertThat(actualPasswordHistories.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedPasswordHistoryList.get(i));
        }
    }
}