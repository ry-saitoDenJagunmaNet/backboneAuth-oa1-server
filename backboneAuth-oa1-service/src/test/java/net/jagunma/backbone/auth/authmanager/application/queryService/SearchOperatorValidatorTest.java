package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010SearchBizTranRoleConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010SearchSubSystemRoleConverter;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchOperatorValidatorTest {

    // 実行既定値
    private LocalDate validThruStartDateMoreOrEqual = null;
    private LocalDate validThruStartDateLessOrEqual = null;
    private LocalDate validThruEndDateMoreOrEqual = null;
    private LocalDate validThruEndDateLessOrEqual = null;
    private List<OparatorSearchSubSystemRoleRequest> oparatorSearchSubSystemRoleRequestList = null;
    private List<OparatorSearchBizTranRoleRequest> oparatorSearchBizTranRoleRequestList = null;
    private LocalDate accountLockOccurredDateFrom = null;
    private LocalDate accountLockOccurredDateTo = null;
    private LocalDate signintraceTrydateFrom = null;
    private LocalDate signintraceTrydateTo = null;
    private OperatorSearchRequest createRequest() {
        return new OperatorSearchRequest() {
            @Override
            public LocalDateCriteria getValidThruStartDateCriteria() {
                LocalDateCriteria criteria = new LocalDateCriteria();
                criteria.setMoreOrEqual(validThruStartDateMoreOrEqual);
                criteria.setLessOrEqual(validThruStartDateLessOrEqual);
                return criteria;
            }
            @Override
            public LocalDateCriteria getValidThruEndDateCriteria() {
                LocalDateCriteria criteria = new LocalDateCriteria();
                criteria.setMoreOrEqual(validThruEndDateMoreOrEqual);
                criteria.setLessOrEqual(validThruEndDateLessOrEqual);
                return criteria;
            }
            @Override
            public List<OparatorSearchSubSystemRoleRequest> getSubSystemRoleList() {
                return oparatorSearchSubSystemRoleRequestList;
            }
            @Override
            public List<OparatorSearchBizTranRoleRequest> getBizTranRoleList() {
                return oparatorSearchBizTranRoleRequestList;
            }
            @Override
            public LocalDate getAccountLockOccurredDateFrom() {
                return accountLockOccurredDateFrom;
            }
            @Override
            public LocalDate getAccountLockOccurredDateTo() {
                return accountLockOccurredDateTo;
            }
            @Override
            public LocalDate getSignintraceTrydateFrom() {
                return signintraceTrydateFrom;
            }
            @Override
            public LocalDate getSignintraceTrydateTo() {
                return signintraceTrydateTo;
            }
            @Override
            public LongCriteria getOperatorIdCriteria() {
                return null;
            }
            @Override
            public StringCriteria getOperatorCodeCriteria() {
                return null;
            }
            @Override
            public StringCriteria getOperatorNameCriteria() {
                return null;
            }
            @Override
            public StringCriteria getMailAddressCriteria() {
                return null;
            }
            @Override
            public BooleanCriteria getIsDeviceAuthCriteria() {
                return null;
            }
            @Override
            public LongCriteria getJaIdCriteria() {
                return null;
            }
            @Override
            public LongCriteria getBranchIdCriteria() {
                return null;
            }
            @Override
            public ShortCriteria getAvailableStatusCriteria() {
                return null;
            }
            @Override
            public Integer getSubSystemRoleConditionsSelect() {
                return null;
            }
            @Override
            public Integer getBizTranRoleConditionsSelect() {
                return null;
            }
            @Override
            public String getBizTranRoleSubSystemCode() {
                return null;
            }
            @Override
            public Boolean getAccountLockStatusLock() {
                return null;
            }
            @Override
            public Boolean getAccountLockStatusUnlock() {
                return null;
            }
            @Override
            public Boolean getPasswordHistoryCheck() {
                return null;
            }
            @Override
            public Integer getPasswordHistoryLastChangeDate() {
                return null;
            }
            @Override
            public String getPasswordHistoryLastChangeDateStatus() {
                return null;
            }
            @Override
            public Boolean getPasswordHistoryChangeType0() {
                return null;
            }
            @Override
            public Boolean getPasswordHistoryChangeType1() {
                return null;
            }
            @Override
            public Boolean getPasswordHistoryChangeType2() {
                return null;
            }
            @Override
            public Boolean getPasswordHistoryChangeType3() {
                return null;
            }
            @Override
            public String getSignintraceTryIpAddress() {
                return null;
            }
            @Override
            public Boolean getSignintraceSignIn() {
                return null;
            }
            @Override
            public Boolean getSignintraceSignOut() {
                return null;
            }
            @Override
            public Short[] getSignintraceSignInResult() {
                return new Short[0];
            }
        };
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    正常（リクエストの全ての項目がnull）
     *
     *  ●検証事項
     *  ・正常
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test00() {

        // 実行値
        OperatorSearchRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    正常（リクエストの全ての項目正常）
     *
     *  ●検証事項
     *  ・正常
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test01() {

        // 実行値
        validThruStartDateMoreOrEqual = LocalDate.of(2020, 10, 1);
        validThruStartDateLessOrEqual = LocalDate.of(2020, 10, 1);
        validThruEndDateMoreOrEqual = LocalDate.of(2020, 10, 2);
        validThruEndDateLessOrEqual = LocalDate.of(2020, 10, 2);
        List<OparatorSearchSubSystemRoleRequest> searchSubSystemRoleConverterList = newArrayList();
        searchSubSystemRoleConverterList.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.JA管理者.getCode(),SubSystemRole.JA管理者.getDisplayName(),2,null,LocalDate.of(2020,10,3),LocalDate.of(2020,10,3),null,null));
        searchSubSystemRoleConverterList.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.業務統括者_購買.getCode(),SubSystemRole.業務統括者_購買.getDisplayName(),2,null,null,null,LocalDate.of(2020,10,4),LocalDate.of(2020,10,4)));
        oparatorSearchSubSystemRoleRequestList = searchSubSystemRoleConverterList;
        List<OparatorSearchBizTranRoleRequest> searchBizTranRoleList = newArrayList();
        searchBizTranRoleList.add(Oa11010SearchBizTranRoleConverter.with(true,1L,"KBAG01","（購買）購買業務基本","KB",2,null,LocalDate.of(2020,10,5),LocalDate.of(2020,10,5),null,null));
        searchBizTranRoleList.add(Oa11010SearchBizTranRoleConverter.with(true,2L,"KBAG02","（購買）本所業務","KB",2,null,null,null,LocalDate.of(2020,10,6),LocalDate.of(2020,10,6)));
        accountLockOccurredDateFrom = LocalDate.of(2020, 10, 7);
        accountLockOccurredDateTo = LocalDate.of(2020, 10, 7);
        signintraceTrydateFrom = LocalDate.of(2020, 10, 8);
        signintraceTrydateTo = LocalDate.of(2020, 10, 8);
        oparatorSearchBizTranRoleRequestList = searchBizTranRoleList;

        OperatorSearchRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    リクエストがnullのテスト
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test02() {

        // 実行値
        OperatorSearchRequest request = null;

        assertThatCode(()->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    オペレーター 有効期限開始の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test03() {

        // 実行値
        validThruStartDateMoreOrEqual = LocalDate.of(2020, 10, 3);
        validThruStartDateLessOrEqual = LocalDate.of(2020, 10, 2);
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限開始");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    オペレーター 有効期限終了の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test04() {

        // 実行値
        validThruEndDateMoreOrEqual = LocalDate.of(2020, 11, 30);
        validThruEndDateLessOrEqual = LocalDate.of(2020, 11, 29);
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限終了");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    サブシステムロール 有効期限開始の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test05() {

        // 実行値
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.JA管理者.getCode(),SubSystemRole.JA管理者.getDisplayName(),2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,1),null,null));
        list.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.業務統括者_購買.getCode(),SubSystemRole.業務統括者_購買.getDisplayName(),2,null,LocalDate.of(2020,10,2),LocalDate.of(2020,10,1),null,null));
        list.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.業務統括者_販売_青果.getCode(),SubSystemRole.業務統括者_販売_青果.getDisplayName(),2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),null,null));
        oparatorSearchSubSystemRoleRequestList = list;
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("サブシステムロール 有効期限開始");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    サブシステムロール 有効期限終了の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test06() {

        // 実行値
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.JA管理者.getCode(),SubSystemRole.JA管理者.getDisplayName(),2,null,null,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,1)));
        list.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.業務統括者_購買.getCode(),SubSystemRole.業務統括者_購買.getDisplayName(),2,null,null,null,LocalDate.of(2020,10,2),LocalDate.of(2020,10,31)));
        list.add(Oa11010SearchSubSystemRoleConverter.with(true,SubSystemRole.業務統括者_販売_青果.getCode(),SubSystemRole.業務統括者_販売_青果.getDisplayName(),2,null,null,null,LocalDate.of(2020,10,3),LocalDate.of(2020,10,2)));
        oparatorSearchSubSystemRoleRequestList = list;
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("サブシステムロール 有効期限終了");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    取引ロール 有効期限開始の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test07() {

        // 実行値
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        list.add(Oa11010SearchBizTranRoleConverter.with(true,1L,"KBAG01","（購買）購買業務基本","KB",2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,1),null,null));
        list.add(Oa11010SearchBizTranRoleConverter.with(true,2L,"KBAG02","（購買）本所業務","KB",2,null,LocalDate.of(2020,10,2),LocalDate.of(2020,10,1),null,null));
        list.add(Oa11010SearchBizTranRoleConverter.with(true,3L,"KBAG03","（購買）本所管理業務","KB",2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),null,null));
        oparatorSearchBizTranRoleRequestList = list;
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("取引ロール 有効期限開始");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    取引ロール 有効期限終了の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test08() {

        // 実行値
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        list.add(Oa11010SearchBizTranRoleConverter.with(true,1L,"KBAG01","（購買）購買業務基本","KB",2,null,null,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,1)));
        list.add(Oa11010SearchBizTranRoleConverter.with(true,2L,"KBAG02","（購買）本所業務","KB",2,null,null,null,LocalDate.of(2020,10,2),LocalDate.of(2020,10,31)));
        list.add(Oa11010SearchBizTranRoleConverter.with(true,3L,"KBAG03","（購買）本所管理業務","KB",2,null,null,null,LocalDate.of(2020,10,3),LocalDate.of(2020,10,2)));
        oparatorSearchBizTranRoleRequestList = list;
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("取引ロール 有効期限終了");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    最終ロック・アンロック発生日の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test09() {

        // 実行値
        accountLockOccurredDateFrom = LocalDate.of(2020, 10, 3);
        accountLockOccurredDateTo = LocalDate.of(2020, 10, 2);
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("最終ロック・アンロック発生日");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    最終ロック・アンロック発生日の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test10() {

        // 実行値
        accountLockOccurredDateFrom = LocalDate.of(2020, 10, 3);
        accountLockOccurredDateTo = LocalDate.of(2020, 10, 2);
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("最終ロック・アンロック発生日");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    最終サインオペレーション試行日の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test11() {

        // 実行値
        signintraceTrydateFrom = LocalDate.of(2020, 10, 3);
        signintraceTrydateTo = LocalDate.of(2020, 10, 2);
        OperatorSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("最終サインオペレーション試行日");
            });
    }
}