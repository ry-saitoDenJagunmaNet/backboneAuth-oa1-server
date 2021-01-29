package net.jagunma.backbone.auth.authmanager.infra.datasource.jaIpAddressRange;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRange;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRangeCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRanges;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityCriteria;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityDao;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JaIpAddressRangeDataSourceTest {

    // 実行既定値
    private final Long jaIpAddressRangeId = 123456789L;
    private final List<String> jaCodeList = newArrayList("002","006","070");
    private List<JaIpAddressRangeEntity> jaIpAddressRangeEntityList = createJaIpAddressRangeEntityList();
    private final List<JaAtMoment> jaAtMomentList = createJaAtMomentList();

    // 検証値
    JaIpAddressRangeEntityCriteria actualJaIpAddressRangeEntityDao_criteria = new JaIpAddressRangeEntityCriteria();
    JaAtMomentCriteria actualJaAtMomentCriteria = new JaAtMomentCriteria();

    // JA割当IPアドレス範囲Entityリストデータ作成
    private List<JaIpAddressRangeEntity> createJaIpAddressRangeEntityList() {
        List<JaIpAddressRangeEntity> list = newArrayList();
        list.add(createJaIpAddressRangeEntity(1L,"006","145.254.",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31)));
        list.add(createJaIpAddressRangeEntity(2L,"070","145.254.",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31)));
        list.add(createJaIpAddressRangeEntity(3L,"006","145.255.",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31)));
        return list;
    }
    // JA割当IPアドレス範囲Entityデータ作成
    private JaIpAddressRangeEntity createJaIpAddressRangeEntity(
        Long jaIpAddressRangeId,
        String jaCode,
        String ipAddressRange,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate) {

        JaIpAddressRangeEntity entity = new JaIpAddressRangeEntity();
        entity.setJaIpAddressRangeId(jaIpAddressRangeId);
        entity.setJaCode(jaCode);
        entity.setIpAddressRange(ipAddressRange);
        entity.setValidThruStartDate(validThruStartDate);
        entity.setValidThruEndDate(validThruEndDate);
        return entity;
    }
    // JaAtMomentリストデータ作成
    private List<JaAtMoment> createJaAtMomentList() {
        List<JaAtMoment> list = newArrayList();
        list.add(createJaAtMoment(2L,"002","ＪＡ００２"));
        list.add(createJaAtMoment(6L,"006","ＪＡ００６"));
        list.add(createJaAtMoment(23L,"070","ＪＡ０７０"));
        return list;
    }
    // JaAtMomentデータ作成
    private JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {
        if (Strings2.isEmpty(jaCode)) { return null; }
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    // JA割当IPアドレス範囲Daoのスタブ
    private JaIpAddressRangeEntityDao createJaIpAddressRangeEntityDao() {
        return new JaIpAddressRangeEntityDao() {
            @Override
            public List<JaIpAddressRangeEntity> findBy(JaIpAddressRangeEntityCriteria criteria, Orders orders) {
                actualJaIpAddressRangeEntityDao_criteria = criteria;
                return jaIpAddressRangeEntityList;
            }
            @Override
            public List<JaIpAddressRangeEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public JaIpAddressRangeEntity findOneBy(JaIpAddressRangeEntityCriteria criteria) {
                return null;
            }
            @Override
            public int countBy(JaIpAddressRangeEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(JaIpAddressRangeEntity entity) {
                return 0;
            }
            @Override
            public int update(JaIpAddressRangeEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(JaIpAddressRangeEntity entity) {
                return 0;
            }
            @Override
            public int delete(JaIpAddressRangeEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(JaIpAddressRangeEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<JaIpAddressRangeEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<JaIpAddressRangeEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<JaIpAddressRangeEntity> entities) {
                return new int[0];
            }
        };
    }
    // JaAtMomentRepositoryののスタブ
    private JaAtMomentRepository createJaAtMomentRepository() {
        return new JaAtMomentRepository() {
            @Override
            public JasAtMoment selectBy(JaAtMomentCriteria criteria, Orders orders) {
                actualJaAtMomentCriteria = criteria;
                return JasAtMoment.of(jaAtMomentList);
            }
            @Override
            public JaAtMoment findOneBy(JaAtMomentCriteria criteria) {
                return null;
            }
        };
    }

    // 検索条件の作成
    private JaIpAddressRangeCriteria createJaIpAddressRangeCriteria () {
        JaIpAddressRangeCriteria criteria = new JaIpAddressRangeCriteria();
        criteria.getJaIpAddressRangeIdCriteria().setEqualTo(jaIpAddressRangeId);
        criteria.getJaCodeCriteria().getIncludes().addAll(jaCodeList);
        return criteria;
    }

    /**
     * {@link JaIpAddressRangeDataSource#selectBy(JaIpAddressRangeCriteria, Orders)
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     *  ・Dao検索条件、リポジトリ検索条件
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // 実行値
        JaIpAddressRangeCriteria JaIpAddressRangeCriteria = createJaIpAddressRangeCriteria();
        Orders orders = Orders.empty().addOrder("jaCode").addOrder("ipAddressRange");

        // テスト対象クラス生成
        JaIpAddressRangeDataSource JaIpAddressRangeDataSource = new JaIpAddressRangeDataSource(
            createJaIpAddressRangeEntityDao(),
            createJaAtMomentRepository());

        // 期待値
        List<JaIpAddressRange> expectedJaIpAddressRangeList = newArrayList();
        for(JaIpAddressRangeEntity entity : jaIpAddressRangeEntityList) {
            JaIpAddressRange jaIpAddressRange = JaIpAddressRange.createFrom(
                entity.getJaIpAddressRangeId(),
                entity.getJaCode(),
                entity.getIpAddressRange(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                jaAtMomentList.stream().filter(j -> j.getJaAttribute().getJaCode().getValue().equals(entity.getJaCode())).findFirst().orElse(null));
            expectedJaIpAddressRangeList.add(jaIpAddressRange);
        }
        JaIpAddressRangeEntityCriteria expectedJaIpAddressRangeEntityCriteria = new JaIpAddressRangeEntityCriteria();
        expectedJaIpAddressRangeEntityCriteria.getJaIpAddressRangeIdCriteria().assignFrom(JaIpAddressRangeCriteria.getJaIpAddressRangeIdCriteria());
        expectedJaIpAddressRangeEntityCriteria.getJaCodeCriteria().assignFrom(JaIpAddressRangeCriteria.getJaCodeCriteria());
        JaAtMomentCriteria expectedJaAtMomentCriteria = new JaAtMomentCriteria();
        expectedJaAtMomentCriteria.setTargetDate(TargetDate.now());
        expectedJaAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        expectedJaAtMomentCriteria.getJaAttributeCriteria().getJaCodeCriteria().getIncludes().addAll(
            expectedJaIpAddressRangeList.stream().map(j -> JaCode.of(j.getJaCode())).collect(Collectors.toList()));
        expectedJaIpAddressRangeList = expectedJaIpAddressRangeList.stream().sorted(orders.toComparator()).collect(Collectors.toList());

        // 実行
        JaIpAddressRanges actualJaIpAddressRanges = JaIpAddressRangeDataSource.selectBy(JaIpAddressRangeCriteria, orders);

        // 結果検証
        assertThat(actualJaIpAddressRanges.getValues().size()).isEqualTo(expectedJaIpAddressRangeList.size());
        for(int i = 0; i < actualJaIpAddressRanges.getValues().size(); i++) {
            assertThat(actualJaIpAddressRanges.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedJaIpAddressRangeList.get(i));
        }
        assertThat(actualJaIpAddressRangeEntityDao_criteria.toString()).isEqualTo(expectedJaIpAddressRangeEntityCriteria.toString());
        assertThat(actualJaAtMomentCriteria.toString()).isEqualTo(expectedJaAtMomentCriteria.toString());
    }

    /**
     * {@link JaIpAddressRangeDataSource#selectBy(JaIpAddressRangeCriteria, Orders)}テスト
     *  ●パターン
     *    正常（検索結果0件）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・検索結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test1() {

        // 実行値
        JaIpAddressRangeCriteria JaIpAddressRangeCriteria = createJaIpAddressRangeCriteria();
        Orders orders = Orders.empty().addOrder("jaCode").addOrder("ipAddressRange");
        jaIpAddressRangeEntityList = newArrayList();

        // テスト対象クラス生成
        JaIpAddressRangeDataSource JaIpAddressRangeDataSource = new JaIpAddressRangeDataSource(
            createJaIpAddressRangeEntityDao(),
            createJaAtMomentRepository());

        // 期待値
        int expectedJaIpAddressRangeListSize = 0;

        // 実行
        JaIpAddressRanges actualJaIpAddressRanges = JaIpAddressRangeDataSource.selectBy(JaIpAddressRangeCriteria, orders);

        // 結果検証
        assertThat(actualJaIpAddressRanges.getValues().size()).isEqualTo(expectedJaIpAddressRangeListSize);
    }
}