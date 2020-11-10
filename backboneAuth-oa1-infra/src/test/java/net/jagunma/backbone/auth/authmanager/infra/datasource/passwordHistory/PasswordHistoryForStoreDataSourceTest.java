package net.jagunma.backbone.auth.authmanager.infra.datasource.passwordHistory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PasswordHistoryForStoreDataSourceTest {

    private Long passwordHistoryId = 345678L;
    private Long operatorId = 123456L;
    private LocalDateTime changeDateTime = LocalDateTime.of(2020, 11, 30,11,12,13);
    private String password = "PaSsWoRd";
    private PasswordChangeType passwordChangeType = PasswordChangeType.ユーザーによる変更;
    private Long createdBy = 100L;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 30,1,2,3);
    private String createdIpAddress = "100.100.100.100";
    private Integer recordVersion = 1;

    // テスト対象クラス生成
    private PasswordHistoryForStoreDataSource createPasswordHistoryForStoreDataSource() {
        PasswordHistoryEntityDao passwordHistoryEntityDao = new PasswordHistoryEntityDao() {
            @Override
            public int insert(PasswordHistoryEntity entity) {
                // entryテスト時
                entity.setPasswordHistoryId(passwordHistoryId);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setUpdatedBy(null);
                entity.setUpdatedAt(null);
                entity.setUpdatedIpAddress(null);
                entity.setRecordVersion(recordVersion);
                return 0;
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
            public List<PasswordHistoryEntity> findBy(PasswordHistoryEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(PasswordHistoryEntityCriteria criteria) {
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

        return new PasswordHistoryForStoreDataSource(passwordHistoryEntityDao);
    }

    /**
     * {@link PasswordHistoryForStoreDataSource#store(PasswordHistory passwordHistory)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test() {
        // テスト対象クラス生成
        PasswordHistoryForStoreDataSource passwordHistoryForStoreDataSource = createPasswordHistoryForStoreDataSource();

        // 実行値
        PasswordHistory passwordHistory = PasswordHistory.createFrom(null, operatorId, changeDateTime, password, passwordChangeType, null, null);

        assertThatCode(() ->
            // 実行
            passwordHistoryForStoreDataSource.store(passwordHistory))
            .doesNotThrowAnyException();
    }

    /**
     * {@link PasswordHistoryForStoreDataSource#insertPasswordHistory(PasswordHistory passwordHistory)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Entityへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertPasswordHistory_test() {
        // テスト対象クラス生成
        PasswordHistoryForStoreDataSource passwordHistoryForStoreDataSource = createPasswordHistoryForStoreDataSource();

        // 実行値
        PasswordHistory passwordHistory = PasswordHistory.createFrom(null, operatorId, changeDateTime, password, passwordChangeType, null, null);

        // 期待値
        PasswordHistoryEntity expectedEntity = new PasswordHistoryEntity();
        expectedEntity.setPasswordHistoryId(passwordHistoryId);
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setChangeDateTime(changeDateTime);
        expectedEntity.setPassword(password);
        expectedEntity.setChangeType(passwordChangeType.getCode());
        expectedEntity.setCreatedBy(createdBy);
        expectedEntity.setCreatedAt(createdAt);
        expectedEntity.setCreatedIpAddress(createdIpAddress);
        expectedEntity.setUpdatedBy(null);
        expectedEntity.setUpdatedAt(null);
        expectedEntity.setUpdatedIpAddress(null);
        expectedEntity.setRecordVersion(recordVersion);

        // 実行
        PasswordHistoryEntity passwordHistoryEntity = passwordHistoryForStoreDataSource.insertPasswordHistory(passwordHistory);

        // 結果検証
        assertThat(passwordHistoryEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }
}