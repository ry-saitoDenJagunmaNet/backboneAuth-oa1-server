package net.jagunma.backbone.auth.authmanager.infra.datasource.systemAvailableTimeZone;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZone;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZones;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntity;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityCriteria;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SystemAvailableTimeZoneDataSourceTest {

    // 実行既定値
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Short dayOfWeek = (short) DayOfWeek.MONDAY.getValue();
    private List<SystemAvailableTimeZoneEntity> systemAvailableTimeZoneEntityList = createSystemAvailableTimeZoneEntityList();

    // 検証値
    SystemAvailableTimeZoneEntityCriteria actualSystemAvailableTimeZoneEntityCriteria;

    // システム利用可能時間帯リストデータ作成
    private List<SystemAvailableTimeZoneEntity> createSystemAvailableTimeZoneEntityList() {
        List<SystemAvailableTimeZoneEntity> list = newArrayList();
        list.add(createSystemAvailableTimeZoneEntity(1l,SubSystem.販売_畜産.getCode(),(short) DayOfWeek.SUNDAY.getValue(),null,null,1));
        list.add(createSystemAvailableTimeZoneEntity(2l,SubSystem.販売_畜産.getCode(),(short) DayOfWeek.MONDAY.getValue(),"8:00","20:00",1));
        list.add(createSystemAvailableTimeZoneEntity(3l,SubSystem.販売_畜産.getCode(),(short) DayOfWeek.SATURDAY.getValue(),"0:00","0:00",1));
        return list;
    }
    // システム利用可能時間帯データ作成
    private SystemAvailableTimeZoneEntity createSystemAvailableTimeZoneEntity(
        Long systemAvailableTimeZoneId,
        String subSystemCode,
        Short dayOfWeek,
        String startTime,
        String endTime,
        Integer recordVersion) {

        SystemAvailableTimeZoneEntity entity = new SystemAvailableTimeZoneEntity();
        entity.setSystemAvailableTimeZoneId(systemAvailableTimeZoneId);
        entity.setSubSystemCode(subSystemCode);
        entity.setDayOfWeek(dayOfWeek);
        entity.setStartTime(startTime);
        entity.setEndTime(endTime);
        entity.setRecordVersion(recordVersion);
        return entity;
    }

    // システム利用可能時間帯Daoのスタブ
    private SystemAvailableTimeZoneEntityDao createSystemAvailableTimeZoneEntityDao() {
        return new SystemAvailableTimeZoneEntityDao() {
            @Override
            public List<SystemAvailableTimeZoneEntity> findBy(SystemAvailableTimeZoneEntityCriteria criteria, Orders orders) {
                actualSystemAvailableTimeZoneEntityCriteria = criteria;
                return systemAvailableTimeZoneEntityList;
            }
            @Override
            public List<SystemAvailableTimeZoneEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public SystemAvailableTimeZoneEntity findOneBy(SystemAvailableTimeZoneEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(SystemAvailableTimeZoneEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(SystemAvailableTimeZoneEntity entity) {
                return 0;
            }
            @Override
            public int update(SystemAvailableTimeZoneEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(SystemAvailableTimeZoneEntity entity) {
                return 0;
            }
            @Override
            public int delete(SystemAvailableTimeZoneEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(SystemAvailableTimeZoneEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<SystemAvailableTimeZoneEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<SystemAvailableTimeZoneEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<SystemAvailableTimeZoneEntity> entities) {
                return new int[0];
            }
        };
    }

    /**
     * {@link SystemAvailableTimeZoneDataSource#selectBy(SystemAvailableTimeZoneCriteria, Orders)} のテスト
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
        SystemAvailableTimeZoneCriteria criteria = new SystemAvailableTimeZoneCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        criteria.getDayOfWeekCriteria().setEqualTo(dayOfWeek);
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        SystemAvailableTimeZoneDataSource systemAvailableTimeZoneDataSource = new SystemAvailableTimeZoneDataSource(
            createSystemAvailableTimeZoneEntityDao());

        // 期待値
        List<SystemAvailableTimeZone> expectedSystemAvailableTimeZoneList = newArrayList();
        for(SystemAvailableTimeZoneEntity entity : systemAvailableTimeZoneEntityList) {
            expectedSystemAvailableTimeZoneList.add(SystemAvailableTimeZone.createFrom(
                entity.getSystemAvailableTimeZoneId(),
                entity.getSubSystemCode(),
                entity.getDayOfWeek(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())
            ));
        }
        SystemAvailableTimeZoneEntityCriteria expectedSystemAvailableTimeZoneEntityCriteria = new SystemAvailableTimeZoneEntityCriteria();
        expectedSystemAvailableTimeZoneEntityCriteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        expectedSystemAvailableTimeZoneEntityCriteria.getDayOfWeekCriteria().setEqualTo(dayOfWeek);

        // 実行
        SystemAvailableTimeZones actualSystemAvailableTimeZones = systemAvailableTimeZoneDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualSystemAvailableTimeZones.getValues().size()).isEqualTo(expectedSystemAvailableTimeZoneList.size());
        for(int i = 0; i < actualSystemAvailableTimeZones.getValues().size(); i++) {
            assertThat(actualSystemAvailableTimeZones.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedSystemAvailableTimeZoneList.get(i));
        }
        assertThat(actualSystemAvailableTimeZoneEntityCriteria.toString()).isEqualTo(expectedSystemAvailableTimeZoneEntityCriteria.toString());
    }

    /**
     * {@link SystemAvailableTimeZoneDataSource#selectBy(SystemAvailableTimeZoneCriteria, Orders)} のテスト
     *  ●パターン
     *    正常（結果0件）
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test1() {

        // 実行値
        systemAvailableTimeZoneEntityList = newArrayList();
        SystemAvailableTimeZoneCriteria criteria = new SystemAvailableTimeZoneCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
        criteria.getDayOfWeekCriteria().setEqualTo(dayOfWeek);
        Orders orders = Orders.empty();

        // テスト対象クラス生成
        SystemAvailableTimeZoneDataSource systemAvailableTimeZoneDataSource = new SystemAvailableTimeZoneDataSource(
            createSystemAvailableTimeZoneEntityDao());

        // 期待値
        int expectedSize = 0;

        // 実行
        SystemAvailableTimeZones actualSystemAvailableTimeZones = systemAvailableTimeZoneDataSource.selectBy(criteria, orders);

        // 結果検証
        assertThat(actualSystemAvailableTimeZones.getValues().size()).isEqualTo(expectedSize);
    }
}