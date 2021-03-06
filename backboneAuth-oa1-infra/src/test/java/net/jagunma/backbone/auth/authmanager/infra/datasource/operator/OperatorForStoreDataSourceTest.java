package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorForStoreDataSourceTest {

    // ???????????????
    // ?????????????????????
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "?????????????????????";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;   private Boolean isDeviceAuthBefore = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.????????????;
    private Long createdBy = 100L;
    private LocalDateTime createdAt = LocalDateTime.of(2020, 10, 30,1,2,3);
    private String createdIpAddress = "100.100.100.100";
    private Long updatedBy = 200L;
    private LocalDateTime updatedAt = LocalDateTime.of(2020, 10, 31,4,5,6);
    private String updatedIpAddress = "200.200.200.200";
    private Integer recordVersion = 1;

    // ???????????????????????????????????????
    private String changeCause = "?????????????????????????????????";

    // ????????????????????????
    private Long passwordHistoryId = 345678L;
    private String password = "PaSsWoRd";
    private String passwordLastTime = "PasswordLastTime";
    private String password2TimesBefore = "PasswordTwoTimesBefore";
    private PasswordChangeType passwordChangeType = PasswordChangeType.??????;
    private PasswordChangeType passwordChangeTypeLastTime = PasswordChangeType.???????????????????????????;
    private PasswordChangeType passwordChangeType2TimesBefore = PasswordChangeType.??????????????????????????????;
    private PasswordHistories passwordHistories;

    // ?????????
    private OperatorCriteria actualOperatorCriteria;
    private OperatorEntityCriteria actualOperatorEntityCriteria;
    private PasswordHistoryCriteria actualPasswordHistoryCriteria;
    private Orders actualPasswordHistoryOrders;
    private Long actualOperatorId;

    // ????????????????????????????????????????????????
    private OperatorEntryPack createOperatorEntryPack() {
        return OperatorEntryPack.createFrom(
            operatorCode,
            operatorName,
            mailAddress,
            validThruStartDate,
            validThruEndDate,
            jaId,
            jaCode,
            branchId,
            branchCode,
            changeCause,
            password);
    }
    // ???????????????????????????????????????????????????
    private OperatorUpdatePack createOperatorUpdatePack() {
        return OperatorUpdatePack.createFrom(
            operatorId,
            operatorName,
            mailAddress,
            validThruStartDate,
            validThruEndDate,
            isDeviceAuth,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            changeCause);
    }

    // ???????????????????????????
    private PasswordHistories createPasswordHistories() {
        List<PasswordHistory> passwordHistoryList = newArrayList(
            PasswordHistory.createFrom(202L, operatorId, LocalDateTime.of(2020,10,1,8,31,12), passwordLastTime, passwordChangeTypeLastTime, recordVersion, null),
            PasswordHistory.createFrom(201L, operatorId, LocalDateTime.of(2020,10,1,8,30,12), password2TimesBefore, passwordChangeType2TimesBefore, recordVersion, null));

        return PasswordHistories.createFrom(passwordHistoryList);
    }

    // ??????????????????????????????
    private OperatorForStoreDataSource createOperatorForStoreDataSource() {
            OperatorEntityDao operatorEntityDao = new OperatorEntityDao() {
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                // update_test()???, updateOperator_test()???
                actualOperatorEntityCriteria = criteria;
                OperatorEntity entity = new OperatorEntity();
                entity.setOperatorId(criteria.getOperatorIdCriteria().getEqualTo());
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
                entity.setAvailableStatus((availableStatus.equals(AvailableStatus.????????????))? (short) 0 : (short) 1);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setUpdatedBy(updatedBy);
                entity.setUpdatedAt(updatedAt);
                entity.setUpdatedIpAddress(updatedIpAddress);
                entity.setRecordVersion(recordVersion + 1);
                return entity;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                // entry_test()???, insertOperator_test()???
                entity.setOperatorId(operatorId);
                entity.setCreatedBy(createdBy);
                entity.setCreatedAt(createdAt);
                entity.setCreatedIpAddress(createdIpAddress);
                entity.setRecordVersion(recordVersion);
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
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore = new OperatorHistoryPackRepositoryForStore() {
            @Override
            public void store(Long operatorId, LocalDateTime changeDateTime, String changeCause) {
            }
        };
        PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore = new PasswordHistoryRepositoryForStore() {
            @Override
            public void store(PasswordHistory passwordHistory) {
            }
        };
        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneById(Long operatorId) {
                // update_test()???, isChangeDeviceAuth_testX()???
                actualOperatorId = operatorId;
                return Operator.createFrom(
                    operatorId,
                    operatorCode,
                    operatorName,
                    mailAddress,
                    validThruStartDate,
                    validThruEndDate,
                    isDeviceAuthBefore,
                    jaId,
                    jaCode,
                    branchId,
                    branchCode,
                    availableStatus,
                    recordVersion,
                    null);
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                actualOperatorCriteria = operatorCriteria;
                if (operatorCriteria.getOperatorCodeCriteria().getEqualTo().equals(operatorCode)) {
                    return false;   // entry_test()???
                } else {
                    return true;    // checkAlreadyExists_test???
                }
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                return null;
            }
            @Override
            public boolean existsById(Long operatorId) {
                return false;
            }
            @Override
            public boolean existsByCode(String operatorCode) {
                return false;
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        PasswordHistoryRepository passwordHistoryRepository = new PasswordHistoryRepository() {
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                actualPasswordHistoryCriteria = passwordHistoryCriteria;
                actualPasswordHistoryOrders = orders;
                return passwordHistories;
            }
            @Override
            public PasswordHistory latestOneByOperatorId(Long operatorId) {
                return null;
            }
        };

        return new OperatorForStoreDataSource(
            operatorEntityDao,
            operatorHistoryPackRepositoryForStore,
            passwordHistoryRepositoryForStore,
            operatorRepository,
            passwordHistoryRepository);
    }

    /**
     * {@link OperatorForStoreDataSource#entry(OperatorEntryPack operatorEntryPack)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();

        assertThatCode(() ->
            // ??????
            operatorForStoreDataSource.entry(operatorEntryPack))
            .doesNotThrowAnyException();
    }
    /**
     * {@link OperatorForStoreDataSource#update(OperatorUpdatePack operatorUpdatePack)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        changeCause = "????????????????????????";
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();
        passwordHistories = createPasswordHistories();

        assertThatCode(() ->
            // ??????
            operatorForStoreDataSource.update(operatorUpdatePack))
            .doesNotThrowAnyException();
    }

    /**
     * {@link OperatorForStoreDataSource#checkAlreadyExists(String operatorCode)}?????????
     *  ???????????????
     *    ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????(???????????????????????????:yu999999)
     *
     *  ???????????????
     *  ??????????????????
     *  ???Criteria???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkAlreadyExists_test() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        String alreadyExistsOperatorCode = "yu999999";

        // ?????????
        OperatorCriteria expectedOperatorCriteria = new OperatorCriteria();
        expectedOperatorCriteria.getOperatorCodeCriteria().setEqualTo(alreadyExistsOperatorCode);

        assertThatThrownBy(() ->
            // ??????
            operatorForStoreDataSource.checkAlreadyExists(alreadyExistsOperatorCode))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // ????????????
                assertThat(e.getMessageCode()).isEqualTo("EOA11001");
                assertThat(e.getArgs()).containsSequence("???????????????????????????");
                assertThat(e.getArgs()).containsSequence(alreadyExistsOperatorCode);
            });
        // ????????????
        assertThat(actualOperatorCriteria.toString()).isEqualTo(expectedOperatorCriteria.toString());
    }

    /**
     * {@link OperatorForStoreDataSource#isChangeDeviceAuth(OperatorUpdatePack operatorUpdatePack)}?????????
     *  ???????????????
     *    ???????????????????????????????????????????????????????????? ???
     *
     *  ???????????????
     *  ???????????????
     *  ???Criteria???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void isChangeDeviceAuth_test0() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        // ?????????
        Boolean expectedValue = true;
        Long expectedOperatorId = operatorId;

        // ??????
        boolean actualValue = operatorForStoreDataSource.isChangeDeviceAuth(operatorUpdatePack);

        // ????????????
        assertThat(actualValue).isEqualTo(expectedValue);
        assertThat(actualOperatorId).isEqualTo(expectedOperatorId);
    }
    /**
     * {@link OperatorForStoreDataSource#isChangeDeviceAuth(OperatorUpdatePack operatorUpdatePack)}?????????
     *  ???????????????
     *    ???????????????????????????????????????????????????????????? ???
     *
     *  ???????????????
     *  ???????????????
     *  ???Criteria???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void isChangeDeviceAuth_test1() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        isDeviceAuth = false;
        isDeviceAuthBefore = false;
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        // ?????????
        Boolean expectedValue = false;
        Long expectedOperatorId = operatorId;

        // ??????
        boolean actualValue = operatorForStoreDataSource.isChangeDeviceAuth(operatorUpdatePack);

        // ????????????
        assertThat(actualValue).isEqualTo(expectedValue);
        assertThat(actualOperatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link OperatorForStoreDataSource#insertOperator(OperatorEntryPack operatorEntryPack)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Entity???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void insertOperator_test() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack();

        // ?????????
        OperatorEntity expectedEntity = new OperatorEntity();
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setOperatorCode(operatorCode);
        expectedEntity.setOperatorName(operatorName);
        expectedEntity.setMailAddress(mailAddress);
        expectedEntity.setValidThruStartDate(validThruStartDate);
        expectedEntity.setValidThruEndDate(validThruEndDate);
        expectedEntity.setIsDeviceAuth(isDeviceAuthBefore);
        expectedEntity.setJaId(jaId);
        expectedEntity.setJaCode(jaCode);
        expectedEntity.setBranchId(branchId);
        expectedEntity.setBranchCode(branchCode);
        expectedEntity.setAvailableStatus((availableStatus.equals(AvailableStatus.????????????))? (short) 0 : (short) 1);
        expectedEntity.setCreatedBy(createdBy);
        expectedEntity.setCreatedAt(createdAt);
        expectedEntity.setCreatedIpAddress(createdIpAddress);
        expectedEntity.setUpdatedBy(null);
        expectedEntity.setUpdatedAt(null);
        expectedEntity.setUpdatedIpAddress(null);
        expectedEntity.setRecordVersion(recordVersion);

        // ??????
        OperatorEntity operatorEntity = operatorForStoreDataSource.insertOperator(operatorEntryPack);

        // ????????????
        assertThat(operatorEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#updateOperator(OperatorUpdatePack operatorUpdatePack)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Criteria???????????????
     *  ???Entity???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void updateOperator_test() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack();

        // ?????????
        OperatorEntity expectedEntity = new OperatorEntity();
        expectedEntity.setOperatorId(operatorId);
        expectedEntity.setOperatorCode(operatorCode);
        expectedEntity.setOperatorName(operatorName);
        expectedEntity.setMailAddress(mailAddress);
        expectedEntity.setValidThruStartDate(validThruStartDate);
        expectedEntity.setValidThruEndDate(validThruEndDate);
        expectedEntity.setIsDeviceAuth(isDeviceAuth);
        expectedEntity.setJaId(jaId);
        expectedEntity.setJaCode(jaCode);
        expectedEntity.setBranchId(branchId);
        expectedEntity.setBranchCode(branchCode);
        expectedEntity.setAvailableStatus((availableStatus.equals(AvailableStatus.????????????))? (short) 0 : (short) 1);
        expectedEntity.setCreatedBy(createdBy);
        expectedEntity.setCreatedAt(createdAt);
        expectedEntity.setCreatedIpAddress(createdIpAddress);
        expectedEntity.setUpdatedBy(updatedBy);
        expectedEntity.setUpdatedAt(updatedAt);
        expectedEntity.setUpdatedIpAddress(updatedIpAddress);
        expectedEntity.setRecordVersion(recordVersion + 1);
        OperatorEntityCriteria expectedOperatorEntityCriteria = new OperatorEntityCriteria();
        expectedOperatorEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // ??????
        OperatorEntity operatorEntity = operatorForStoreDataSource.updateOperator(operatorUpdatePack);

        // ????????????
        assertThat(actualOperatorEntityCriteria.toString()).isEqualTo(expectedOperatorEntityCriteria.toString());
        assertThat(operatorEntity).usingRecursiveComparison().isEqualTo(expectedEntity);
    }

    /**
     * {@link OperatorForStoreDataSource#storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, String password, PasswordChangeType passwordChangeType)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???model???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void storePasswordHistory_test0() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            createdAt,
            password,
            passwordChangeType,
            null,
            null);

        // ??????
        PasswordHistory passwordHistory = operatorForStoreDataSource.storePasswordHistory(operatorId, createdAt, password, passwordChangeType);

        // ????????????
        assertThat(passwordHistory).usingRecursiveComparison().isEqualTo(expectedModel);
    }

    /**
     * {@link OperatorForStoreDataSource#storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, boolean isDeviceAuth)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Criteria???????????????
     *  ???model???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void storePasswordHistory_test1() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        passwordHistories = createPasswordHistories();

        // ?????????
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            updatedAt,
            passwordLastTime,
            PasswordChangeType.???????????????????????????,
            null,
            null);
        PasswordHistoryCriteria expectedPasswordHistoryCriteria = new PasswordHistoryCriteria();
        expectedPasswordHistoryCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders  expectedPasswordHistoryOrders = Orders.empty().addOrder("changeDateTime", Order.DESC);

        // ??????
        PasswordHistory passwordHistory = operatorForStoreDataSource.storePasswordHistory(operatorId, updatedAt, isDeviceAuth);

        // ????????????
        assertThat(passwordHistory).usingRecursiveComparison().isEqualTo(expectedModel);
        assertThat(actualPasswordHistoryCriteria.toString()).isEqualTo(expectedPasswordHistoryCriteria.toString());
        assertThat(actualPasswordHistoryOrders).usingRecursiveComparison().isEqualTo(expectedPasswordHistoryOrders);
    }

    /**
     * {@link OperatorForStoreDataSource#storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, boolean isDeviceAuth)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Criteria???????????????
     *  ???model???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void storePasswordHistory_test2() {
        // ??????????????????????????????
        OperatorForStoreDataSource operatorForStoreDataSource = createOperatorForStoreDataSource();

        // ?????????
        isDeviceAuth = false;
        passwordLastTime = "pAsSwOrD";
        password2TimesBefore = "pAsSwOrD";
        passwordChangeTypeLastTime = PasswordChangeType.???????????????????????????;
        passwordChangeType2TimesBefore = PasswordChangeType.???????????????????????????;
        passwordHistories = createPasswordHistories();

        // ?????????
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            updatedAt,
            passwordLastTime,
            passwordChangeType2TimesBefore,
            null,
            null);
        PasswordHistoryCriteria expectedPasswordHistoryCriteria = new PasswordHistoryCriteria();
        expectedPasswordHistoryCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders  expectedPasswordHistoryOrders = Orders.empty().addOrder("changeDateTime", Order.DESC);

        // ??????
        PasswordHistory passwordHistory = operatorForStoreDataSource.storePasswordHistory(operatorId, updatedAt, isDeviceAuth);

        // ????????????
        assertThat(passwordHistory).usingRecursiveComparison().isEqualTo(expectedModel);
        assertThat(actualPasswordHistoryCriteria.toString()).isEqualTo(expectedPasswordHistoryCriteria.toString());
        assertThat(actualPasswordHistoryOrders).usingRecursiveComparison().isEqualTo(expectedPasswordHistoryOrders);
    }
}
