package net.jagunma.backbone.auth.authmanager.infra.datasource.signOutTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignOutTraceDataSourceTest {

    // 実行既定値
    // サインアウト証跡Daoの作成
    private SignOutTraceEntityDao createSignOutTraceEntityDao() {
        return new SignOutTraceEntityDao() {
            @Override
            public List<SignOutTraceEntity> findBy(SignOutTraceEntityCriteria criteria, Orders orders) {
                return createSignOutTraceEntityList();
            }
            @Override
            public List<SignOutTraceEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public SignOutTraceEntity findOneBy(SignOutTraceEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(SignOutTraceEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(SignOutTraceEntity entity) {
                return 0;
            }
            @Override
            public int update(SignOutTraceEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(SignOutTraceEntity entity) {
                return 0;
            }
            @Override
            public int delete(SignOutTraceEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(SignOutTraceEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<SignOutTraceEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<SignOutTraceEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<SignOutTraceEntity> entities) {
                return new int[0];
            }
        };
    }
    // サインアウト証跡リストデータ作成
    private List<SignOutTraceEntity> createSignOutTraceEntityList() {
        List<SignOutTraceEntity> list = newArrayList();
        list.add(createSignOutTraceEntity(1L,LocalDateTime.of(2020,11,2,8,30,12),"001.001.001.001",18L,null,null,null,null,null,null,null));
        list.add(createSignOutTraceEntity(2L,LocalDateTime.of(2020,11,2,9,31,3),"001.001.001.002",19L,null,null,null,null,null,null,null));
        list.add(createSignOutTraceEntity(3L,LocalDateTime.of(2020,11,2,10,1,45),"001.001.001.003",20L,null,null,null,null,null,null,null));
        return list;
    }
    // サインアウト証跡データ作成
    private SignOutTraceEntity createSignOutTraceEntity(
        Long signOutTraceId,
        LocalDateTime signOutDateTime,
        String signOutIpAddress,
        Long operatorId,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        SignOutTraceEntity entity = new SignOutTraceEntity();
        entity.setSignOutTraceId(signOutTraceId);
        entity.setSignOutDateTime(signOutDateTime);
        entity.setSignOutIpAddress(signOutIpAddress);
        entity.setOperatorId(operatorId);
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
     * {@link SignOutTraceDataSource#selectBy(SignOutTraceCriteria,Orders)}のテスト
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
        SignOutTraceCriteria criteria = new SignOutTraceCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        SignOutTraceDataSource signOutTraceDataSource = new SignOutTraceDataSource(
            createSignOutTraceEntityDao(),
            createOperatorsRepository());

        // 期待値
        Operators operators = createOperators();
        List<SignOutTrace> expectedSignOutTraceList = newArrayList();
        for(SignOutTraceEntity entity : createSignOutTraceEntityList()) {
            expectedSignOutTraceList.add(SignOutTrace.createFrom(
                entity.getSignOutTraceId(),
                entity.getSignOutDateTime(),
                entity.getSignOutIpAddress(),
                entity.getOperatorId(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)));
        }

        // 実行
        SignOutTraces actualSignOutTraces = signOutTraceDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualSignOutTraces.getValues().size(); i++) {
            assertThat(actualSignOutTraces.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSignOutTraceList.get(i));
        }
    }
}