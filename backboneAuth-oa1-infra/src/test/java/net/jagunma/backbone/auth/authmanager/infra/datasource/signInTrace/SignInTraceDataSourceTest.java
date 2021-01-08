package net.jagunma.backbone.auth.authmanager.infra.datasource.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTraceDataSourceTest {

    // 実行既定値
    // サインイン証跡Daoの作成
    private SignInTraceEntityDao createSignInTraceEntityDao() {
        return new SignInTraceEntityDao() {
            @Override
            public List<SignInTraceEntity> findBy(SignInTraceEntityCriteria criteria, Orders orders) {
                return createSignInTraceEntityList();
            }
            @Override
            public List<SignInTraceEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public SignInTraceEntity findOneBy(SignInTraceEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(SignInTraceEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int update(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int delete(SignInTraceEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(SignInTraceEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<SignInTraceEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<SignInTraceEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<SignInTraceEntity> entities) {
                return new int[0];
            }
        };
    }
    // サインイン証跡リストデータ作成
    private List<SignInTraceEntity> createSignInTraceEntityList() {
        List<SignInTraceEntity> list = newArrayList();
        list.add(createSignInTraceEntity(1L,LocalDateTime.of(2020,10,28,8,30,12),"001.001.001.001","yu001009",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),null,null,null,null,null,null,1));
        list.add(createSignInTraceEntity(2L,LocalDateTime.of(2020,10,28,9,31,3),"001.001.001.002","yu001010",SignInCause.継続サインイン.getCode(),SignInResult.失敗_パスワード誤り.getCode(),null,null,null,null,null,null,1));
        list.add(createSignInTraceEntity(3L,LocalDateTime.of(2020,10,28,10,1,45),"001.001.001.003","yu001011",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),null,null,null,null,null,null,1));
        return list;
    }
    // サインイン証跡データ作成
    private SignInTraceEntity createSignInTraceEntity(
        Long signInTraceId,
        LocalDateTime tryDateTime,
        String tryIpAddress,
        String operatorCode,
        Short signInCause,
        Short signInResult,
        Long createdBy,
        LocalDateTime createdAt,
        String createdIpAddress,
        Long updatedBy,
        LocalDateTime updatedAt,
        String updatedIpAddress,
        Integer recordVersion) {

        SignInTraceEntity entity = new SignInTraceEntity();
        entity.setSignInTraceId(signInTraceId);
        entity.setTryDateTime(tryDateTime);
        entity.setTryIpAddress(tryIpAddress);
        entity.setOperatorCode(operatorCode);
        entity.setSignInCause(signInCause);
        entity.setSignInResult(signInResult);
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
    private OperatorRepository createOperatorRepository() {
        return new OperatorRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return createOperators();
            }
            @Override
            public Operator findOneById(Long operatorId) {
                return null;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
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
     * {@link SignInTraceDataSource#selectBy(SignInTraceCriteria,Orders)}のテスト
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
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        SignInTraceDataSource signInTraceDataSource = new SignInTraceDataSource(
            createSignInTraceEntityDao(),
            createOperatorRepository());

        // 期待値
        Operators operators = createOperators();
        List<SignInTrace> expectedSignInTraceList = newArrayList();
        for(SignInTraceEntity entity : createSignInTraceEntityList()) {
            expectedSignInTraceList.add(SignInTrace.createFrom(
                entity.getSignInTraceId(),
                entity.getTryDateTime(),
                entity.getTryIpAddress(),
                entity.getOperatorCode(),
                entity.getSignInCause(),
                entity.getSignInResult(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->o.getOperatorCode().equals(entity.getOperatorCode())).findFirst().orElse(null)));
        }

        // 実行
        SignInTraces actualSignInTraces = signInTraceDataSource.selectBy(criteria, orders);

        // 結果検証
        for(int i = 0; i < actualSignInTraces.getValues().size(); i++) {
            assertThat(actualSignInTraces.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSignInTraceList.get(i));
        }
    }
}