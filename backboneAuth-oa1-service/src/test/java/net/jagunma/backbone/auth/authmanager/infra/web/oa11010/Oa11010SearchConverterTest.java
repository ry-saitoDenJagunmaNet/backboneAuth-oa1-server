package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010SearchConverterTest {

    // 実行既定値
    private final Long jaId = 6L;
    private final Long branchId = 1L;
    private String operatorCode = "yu001009";
    private String operatorName = "ｙｕ００１００９";
    private String mailAddress = "abcd@efgh.net";
    private Boolean availableStatus0 = true;
    private Boolean availableStatus1 = true;
    private Integer validThruSelect = 1;
    private final LocalDate validThruStatusDate = LocalDate.of(2020, 9, 1);
    private final LocalDate validThruStartDateFrom = LocalDate.of(2020, 9, 2);
    private final LocalDate validThruStartDateTo = LocalDate.of(2020, 9, 3);
    private final LocalDate validThruEndDateFrom = LocalDate.of(2020, 9, 4);
    private final LocalDate validThruEndDateTo = LocalDate.of(2020, 9, 5);
    private final Integer subSystemRoleConditionsSelect = 1;
    private List<Oa11010SubSystemRoleVo> getSubSystemRoleVoList() {
        List<Oa11010SubSystemRoleVo> subSystemRoleVoList = newArrayList();
        Oa11010SubSystemRoleVo subSystemRoleVo = new Oa11010SubSystemRoleVo();
        subSystemRoleVo.setSubSystemRoleSelected(true);
        subSystemRoleVo.setSubSystemRoleCode(SubSystemRole.JA管理者.getCode());
        subSystemRoleVo.setSubSystemRoleName(SubSystemRole.JA管理者.getDisplayName());
        subSystemRoleVo.setValidThruSelect(1);
        subSystemRoleVo.setValidThruStatusDate(LocalDate.of(2020, 10, 1));
        subSystemRoleVo.setValidThruStartDateFrom(LocalDate.of(2020, 10, 2));
        subSystemRoleVo.setValidThruStartDateTo(LocalDate.of(2020, 10, 3));
        subSystemRoleVo.setValidThruEndDateFrom(LocalDate.of(2020, 10, 4));
        subSystemRoleVo.setValidThruEndDateTo(LocalDate.of(2020, 10, 5));
        subSystemRoleVoList.add(subSystemRoleVo);
        subSystemRoleVo = new Oa11010SubSystemRoleVo();
        subSystemRoleVo.setSubSystemRoleSelected(true);
        subSystemRoleVo.setSubSystemRoleCode(SubSystemRole.業務統括者_販売_青果.getCode());
        subSystemRoleVo.setSubSystemRoleName(SubSystemRole.業務統括者_販売_青果.getDisplayName());
        subSystemRoleVo.setValidThruSelect(2);
        subSystemRoleVo.setValidThruStatusDate(LocalDate.of(2020, 10, 6));
        subSystemRoleVo.setValidThruStartDateFrom(LocalDate.of(2020, 10, 7));
        subSystemRoleVo.setValidThruStartDateTo(LocalDate.of(2020, 10, 8));
        subSystemRoleVo.setValidThruEndDateFrom(LocalDate.of(2020, 10, 9));
        subSystemRoleVo.setValidThruEndDateTo(LocalDate.of(2020, 10, 10));
        subSystemRoleVoList.add(subSystemRoleVo);
        subSystemRoleVo = new Oa11010SubSystemRoleVo();
        subSystemRoleVo.setSubSystemRoleSelected(true);
        subSystemRoleVo.setSubSystemRoleCode(SubSystemRole.業務統括者_販売_米.getCode());
        subSystemRoleVo.setSubSystemRoleName(SubSystemRole.業務統括者_販売_米.getDisplayName());
        subSystemRoleVo.setValidThruSelect(0);
        subSystemRoleVo.setValidThruStatusDate(null);
        subSystemRoleVo.setValidThruStartDateFrom(null);
        subSystemRoleVo.setValidThruStartDateTo(null);
        subSystemRoleVo.setValidThruEndDateFrom(null);
        subSystemRoleVo.setValidThruEndDateTo(null);
        subSystemRoleVoList.add(subSystemRoleVo);
        return subSystemRoleVoList;
    }
    private final Integer bizTranRoleConditionsSelect = 0;
    private final String bizTranRoleSubSystemCode = "KB";
    private final List<Oa11010SearchSubSystemRoleConverter> bizTranRoleSubSystemList = newArrayList();
    private List<Oa11010BizTranRoleVo> getBizTranRoleVoList() {
        List<Oa11010BizTranRoleVo> bizTranRoleVoList = newArrayList();
        Oa11010BizTranRoleVo bizTranRoleVo = new Oa11010BizTranRoleVo();
        bizTranRoleVo.setBizTranRoleSelected(true);
        bizTranRoleVo.setBizTranRoleId(1L);
        bizTranRoleVo.setBizTranRoleCode("KBAG01");
        bizTranRoleVo.setBizTranRoleName("（購買）購買業務基本");
        bizTranRoleVo.setSubSystemCode(SubSystem.購買.getCode());
        bizTranRoleVo.setValidThruSelect(1);
        bizTranRoleVo.setValidThruStatusDate(LocalDate.of(2020, 10, 11));
        bizTranRoleVo.setValidThruStartDateFrom(LocalDate.of(2020, 10, 12));
        bizTranRoleVo.setValidThruStartDateTo(LocalDate.of(2020, 10, 13));
        bizTranRoleVo.setValidThruEndDateFrom(LocalDate.of(2020, 10, 14));
        bizTranRoleVo.setValidThruEndDateTo(LocalDate.of(2020, 10, 15));
        bizTranRoleVoList.add(bizTranRoleVo);
        bizTranRoleVo = new Oa11010BizTranRoleVo();
        bizTranRoleVo.setBizTranRoleSelected(true);
        bizTranRoleVo.setBizTranRoleId(2L);
        bizTranRoleVo.setBizTranRoleCode("KBAG02");
        bizTranRoleVo.setBizTranRoleName("（購買）本所業務");
        bizTranRoleVo.setSubSystemCode(SubSystem.購買.getCode());
        bizTranRoleVo.setValidThruSelect(1);
        bizTranRoleVo.setValidThruStatusDate(LocalDate.of(2020, 10, 16));
        bizTranRoleVo.setValidThruStartDateFrom(LocalDate.of(2020, 10, 17));
        bizTranRoleVo.setValidThruStartDateTo(LocalDate.of(2020, 10, 18));
        bizTranRoleVo.setValidThruEndDateFrom(LocalDate.of(2020, 10, 19));
        bizTranRoleVo.setValidThruEndDateTo(LocalDate.of(2020, 10, 20));
        bizTranRoleVoList.add(bizTranRoleVo);
        bizTranRoleVo = new Oa11010BizTranRoleVo();
        bizTranRoleVo.setBizTranRoleSelected(true);
        bizTranRoleVo.setBizTranRoleId(3L);
        bizTranRoleVo.setBizTranRoleCode("KBAG03");
        bizTranRoleVo.setBizTranRoleName("（購買）本所管理業務");
        bizTranRoleVo.setSubSystemCode(SubSystem.購買.getCode());
        bizTranRoleVo.setValidThruSelect(0);
        bizTranRoleVo.setValidThruStatusDate(null);
        bizTranRoleVo.setValidThruStartDateFrom(null);
        bizTranRoleVo.setValidThruStartDateTo(null);
        bizTranRoleVo.setValidThruEndDateFrom(null);
        bizTranRoleVo.setValidThruEndDateTo(null);
        bizTranRoleVoList.add(bizTranRoleVo);
        return bizTranRoleVoList;
    }
    private Boolean deviceAuthUse = false;
    private Boolean deviceAuthUnuse = true;
    private final LocalDate accountLockOccurredDateFrom = LocalDate.of(2020, 9, 6);
    private final LocalDate accountLockOccurredDateTo = LocalDate.of(2020, 9, 7);
    private final Boolean accountLockStatusLock = false;
    private final Boolean accountLockStatusUnlock = true;
    private final Boolean passwordHistoryCheck = true;
    private final Integer passwordHistoryLastChangeDate = 30;
    private final String passwordHistoryLastChangeDateStatus = "2";
    private final Boolean passwordHistoryChangeType0 = false;
    private final Boolean passwordHistoryChangeType1 = true;
    private final Boolean passwordHistoryChangeType2 = false;
    private final Boolean passwordHistoryChangeType3 = true;
    private final LocalDate signintraceTrydateFrom = LocalDate.of(2020, 9, 8);
    private final LocalDate signintraceTrydateTo = LocalDate.of(2020, 9, 9);
    private final String signintraceTryIpAddress = "001.001.001.001";
    private final Boolean signintraceSignIn = false;
    private final Boolean signintraceSignOut = true;
    private final Short[] signintraceSignInResult = {(short)0, (short)1};
    private final Integer pageNo = 1;

    private Oa11010Vo createOa11010Vo() {
        Oa11010Vo vo = new Oa11010Vo();
        vo.setJaId(jaId);
        vo.setBranchId(branchId);
        vo.setOperatorCode(operatorCode);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setAvailableStatus0(availableStatus0);
        vo.setAvailableStatus1(availableStatus1);
        vo.setValidThruSelect(validThruSelect);
        vo.setValidThruStatusDate(validThruStatusDate);
        vo.setValidThruStartDateFrom(validThruStartDateFrom);
        vo.setValidThruStartDateTo(validThruStartDateTo);
        vo.setValidThruEndDateFrom(validThruEndDateFrom);
        vo.setValidThruEndDateTo(validThruEndDateTo);
        vo.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        vo.setSubSystemRoleList(getSubSystemRoleVoList());
        vo.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        vo.setBizTranRoleSubSystemCode(bizTranRoleSubSystemCode);
        vo.setBizTranRoleList(getBizTranRoleVoList());
        vo.setDeviceAuthUse(deviceAuthUse);
        vo.setDeviceAuthUnuse(deviceAuthUnuse);
        vo.setAccountLockOccurredDateFrom(accountLockOccurredDateFrom);
        vo.setAccountLockOccurredDateTo(accountLockOccurredDateTo);
        vo.setAccountLockStatusLock(accountLockStatusLock);
        vo.setAccountLockStatusUnlock(accountLockStatusUnlock);
        vo.setPasswordHistoryCheck(passwordHistoryCheck);
        vo.setPasswordHistoryLastChangeDate(passwordHistoryLastChangeDate);
        vo.setPasswordHistoryLastChangeDateStatus(passwordHistoryLastChangeDateStatus);
        vo.setPasswordHistoryChangeType0(passwordHistoryChangeType0);
        vo.setPasswordHistoryChangeType1(passwordHistoryChangeType1);
        vo.setPasswordHistoryChangeType2(passwordHistoryChangeType2);
        vo.setPasswordHistoryChangeType3(passwordHistoryChangeType3);
        vo.setSignintraceTrydateFrom(signintraceTrydateFrom);
        vo.setSignintraceTrydateTo(signintraceTrydateTo);
        vo.setSignintraceTryIpAddress(signintraceTryIpAddress);
        vo.setSignintraceSignIn(signintraceSignIn);
        vo.setSignintraceSignOut(signintraceSignOut);
        vo.setSignintraceSignInResult(signintraceSignInResult);
        vo.setPageNo(pageNo);
        return vo;
    }

    /**
     * {@link Oa11010SearchConverter}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test0() {

        // 実行値
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals(true)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals(true)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        availableStatusIncludesList.add((short) 0);
        availableStatusIncludesList.add((short) 1);

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isEqualTo(operatorCode);
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isEqualTo(operatorName);
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isEqualTo(mailAddress);
        assertThat(converter.getValidThruStartDateCriteria().getLessOrEqual()).isEqualTo(validThruStatusDate);
        assertThat(converter.getValidThruEndDateCriteria().getMoreOrEqual()).isEqualTo(validThruStatusDate);
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isEqualTo(deviceAuthUse);
        assertThat(converter.getJaIdCriteria().getEqualTo()).isEqualTo(jaId);
        assertThat(converter.getBranchIdCriteria().getEqualTo()).isEqualTo(branchId);
        assertThat(converter.getAvailableStatusCriteria().getIncludes()).usingRecursiveComparison().isEqualTo(availableStatusIncludesList);
        assertThat(converter.getSubSystemRoleConditionsSelect()).isEqualTo(subSystemRoleConditionsSelect);
        assertThat(converter.getSubSystemRoleList()).usingRecursiveComparison().isEqualTo(expectedSubSystemRoleVoList);
        assertThat(converter.getBizTranRoleConditionsSelect()).isEqualTo(bizTranRoleConditionsSelect);
        assertThat(converter.getBizTranRoleSubSystemCode()).isEqualTo(bizTranRoleSubSystemCode);
        assertThat(converter.getBizTranRoleList()).usingRecursiveComparison().isEqualTo(expectedBizTranRoleVoList);
        assertThat(converter.getAccountLockOccurredDateFrom()).isEqualTo(accountLockOccurredDateFrom);
        assertThat(converter.getAccountLockOccurredDateTo()).isEqualTo(accountLockOccurredDateTo);
        assertThat(converter.getAccountLockStatusLock()).isEqualTo(accountLockStatusLock);
        assertThat(converter.getAccountLockStatusUnlock()).isEqualTo(accountLockStatusUnlock);
        assertThat(converter.getPasswordHistoryCheck()).isEqualTo(passwordHistoryCheck);
        assertThat(converter.getPasswordHistoryLastChangeDate()).isEqualTo(passwordHistoryLastChangeDate);
        assertThat(converter.getPasswordHistoryLastChangeDateStatus()).isEqualTo(passwordHistoryLastChangeDateStatus);
        assertThat(converter.getPasswordHistoryChangeType0()).isEqualTo(passwordHistoryChangeType0);
        assertThat(converter.getPasswordHistoryChangeType1()).isEqualTo(passwordHistoryChangeType1);
        assertThat(converter.getPasswordHistoryChangeType2()).isEqualTo(passwordHistoryChangeType2);
        assertThat(converter.getPasswordHistoryChangeType3()).isEqualTo(passwordHistoryChangeType3);
        assertThat(converter.getSignintraceTrydateFrom()).isEqualTo(signintraceTrydateFrom);
        assertThat(converter.getSignintraceTrydateTo()).isEqualTo(signintraceTrydateTo);
        assertThat(converter.getSignintraceTryIpAddress()).isEqualTo(signintraceTryIpAddress);
        assertThat(converter.getSignintraceSignIn()).isEqualTo(signintraceSignIn);
        assertThat(converter.getSignintraceSignOut()).isEqualTo(signintraceSignOut);
        assertThat(converter.getSignintraceSignInResult()).isEqualTo(signintraceSignInResult);
    }
    /**
     * {@link Oa11010SearchConverter}のテスト
     *  ●パターン
     *    通常
     *    （有効期限＝条件指定、
     *      利用可能＝false、利用不可＝false、
     *      機器認証検＝使用）
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        validThruSelect = 2;
        availableStatus0 = false;
        availableStatus1 = false;
        deviceAuthUse = true;
        deviceAuthUnuse = false;
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals(true)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals(true)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isEqualTo(operatorCode);
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isEqualTo(operatorName);
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isEqualTo(mailAddress);
        assertThat(converter.getValidThruStartDateCriteria().getMoreOrEqual()).isEqualTo(validThruStartDateFrom);
        assertThat(converter.getValidThruStartDateCriteria().getLessOrEqual()).isEqualTo(validThruStartDateTo);
        assertThat(converter.getValidThruEndDateCriteria().getMoreOrEqual()).isEqualTo(validThruEndDateFrom);
        assertThat(converter.getValidThruEndDateCriteria().getLessOrEqual()).isEqualTo(validThruEndDateTo);
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isEqualTo(deviceAuthUse);
        assertThat(converter.getJaIdCriteria().getEqualTo()).isEqualTo(jaId);
        assertThat(converter.getBranchIdCriteria().getEqualTo()).isEqualTo(branchId);
        assertThat(converter.getAvailableStatusCriteria().getIncludes()).usingRecursiveComparison().isEqualTo(availableStatusIncludesList);
        assertThat(converter.getSubSystemRoleConditionsSelect()).isEqualTo(subSystemRoleConditionsSelect);
        assertThat(converter.getSubSystemRoleList()).usingRecursiveComparison().isEqualTo(expectedSubSystemRoleVoList);
        assertThat(converter.getBizTranRoleConditionsSelect()).isEqualTo(bizTranRoleConditionsSelect);
        assertThat(converter.getBizTranRoleSubSystemCode()).isEqualTo(bizTranRoleSubSystemCode);
        assertThat(converter.getBizTranRoleList()).usingRecursiveComparison().isEqualTo(expectedBizTranRoleVoList);
        assertThat(converter.getAccountLockOccurredDateFrom()).isEqualTo(accountLockOccurredDateFrom);
        assertThat(converter.getAccountLockOccurredDateTo()).isEqualTo(accountLockOccurredDateTo);
        assertThat(converter.getAccountLockStatusLock()).isEqualTo(accountLockStatusLock);
        assertThat(converter.getAccountLockStatusUnlock()).isEqualTo(accountLockStatusUnlock);
        assertThat(converter.getPasswordHistoryCheck()).isEqualTo(passwordHistoryCheck);
        assertThat(converter.getPasswordHistoryLastChangeDate()).isEqualTo(passwordHistoryLastChangeDate);
        assertThat(converter.getPasswordHistoryLastChangeDateStatus()).isEqualTo(passwordHistoryLastChangeDateStatus);
        assertThat(converter.getPasswordHistoryChangeType0()).isEqualTo(passwordHistoryChangeType0);
        assertThat(converter.getPasswordHistoryChangeType1()).isEqualTo(passwordHistoryChangeType1);
        assertThat(converter.getPasswordHistoryChangeType2()).isEqualTo(passwordHistoryChangeType2);
        assertThat(converter.getPasswordHistoryChangeType3()).isEqualTo(passwordHistoryChangeType3);
        assertThat(converter.getSignintraceTrydateFrom()).isEqualTo(signintraceTrydateFrom);
        assertThat(converter.getSignintraceTrydateTo()).isEqualTo(signintraceTrydateTo);
        assertThat(converter.getSignintraceTryIpAddress()).isEqualTo(signintraceTryIpAddress);
        assertThat(converter.getSignintraceSignIn()).isEqualTo(signintraceSignIn);
        assertThat(converter.getSignintraceSignOut()).isEqualTo(signintraceSignOut);
        assertThat(converter.getSignintraceSignInResult()).isEqualTo(signintraceSignInResult);
    }

    /**
     * {@link Oa11010SearchConverter}のテスト
     *  ●パターン
     *    通常
     *    （オペレーターコード、 オペレーター名、メールアドレス 未指定、
     *      有効期限選択=指定なし、
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test2() {

        // 実行値
        operatorCode = "";
        operatorName = "";
        mailAddress = "";
        validThruSelect = 0;
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals(true)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals(true)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        availableStatusIncludesList.add((short) 0);
        availableStatusIncludesList.add((short) 1);

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isNull();
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isNull();
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isNull();
        assertThat(converter.getValidThruStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getValidThruEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getValidThruStartDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getValidThruStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getValidThruEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getValidThruEndDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isEqualTo(deviceAuthUse);
        assertThat(converter.getJaIdCriteria().getEqualTo()).isEqualTo(jaId);
        assertThat(converter.getBranchIdCriteria().getEqualTo()).isEqualTo(branchId);
        assertThat(converter.getAvailableStatusCriteria().getIncludes()).usingRecursiveComparison().isEqualTo(availableStatusIncludesList);
        assertThat(converter.getSubSystemRoleConditionsSelect()).isEqualTo(subSystemRoleConditionsSelect);
        assertThat(converter.getSubSystemRoleList()).usingRecursiveComparison().isEqualTo(expectedSubSystemRoleVoList);
        assertThat(converter.getBizTranRoleConditionsSelect()).isEqualTo(bizTranRoleConditionsSelect);
        assertThat(converter.getBizTranRoleSubSystemCode()).isEqualTo(bizTranRoleSubSystemCode);
        assertThat(converter.getBizTranRoleList()).usingRecursiveComparison().isEqualTo(expectedBizTranRoleVoList);
        assertThat(converter.getAccountLockOccurredDateFrom()).isEqualTo(accountLockOccurredDateFrom);
        assertThat(converter.getAccountLockOccurredDateTo()).isEqualTo(accountLockOccurredDateTo);
        assertThat(converter.getAccountLockStatusLock()).isEqualTo(accountLockStatusLock);
        assertThat(converter.getAccountLockStatusUnlock()).isEqualTo(accountLockStatusUnlock);
        assertThat(converter.getPasswordHistoryCheck()).isEqualTo(passwordHistoryCheck);
        assertThat(converter.getPasswordHistoryLastChangeDate()).isEqualTo(passwordHistoryLastChangeDate);
        assertThat(converter.getPasswordHistoryLastChangeDateStatus()).isEqualTo(passwordHistoryLastChangeDateStatus);
        assertThat(converter.getPasswordHistoryChangeType0()).isEqualTo(passwordHistoryChangeType0);
        assertThat(converter.getPasswordHistoryChangeType1()).isEqualTo(passwordHistoryChangeType1);
        assertThat(converter.getPasswordHistoryChangeType2()).isEqualTo(passwordHistoryChangeType2);
        assertThat(converter.getPasswordHistoryChangeType3()).isEqualTo(passwordHistoryChangeType3);
        assertThat(converter.getSignintraceTrydateFrom()).isEqualTo(signintraceTrydateFrom);
        assertThat(converter.getSignintraceTrydateTo()).isEqualTo(signintraceTrydateTo);
        assertThat(converter.getSignintraceTryIpAddress()).isEqualTo(signintraceTryIpAddress);
        assertThat(converter.getSignintraceSignIn()).isEqualTo(signintraceSignIn);
        assertThat(converter.getSignintraceSignOut()).isEqualTo(signintraceSignOut);
        assertThat(converter.getSignintraceSignInResult()).isEqualTo(signintraceSignInResult);
    }

    /**
     * {@link Oa11010SearchConverter}のテスト
     *  ●パターン
     *    通常
     *    （オペレーターコード、 オペレーター名、メールアドレス 未指定、
     *      有効期限選択 未指定、
     *      機器認証検索条件 未指定、
     *      機器認証検＝未指定）
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test3() {

        // 実行値
        operatorCode = null;
        operatorName = null;
        mailAddress = null;
        validThruSelect = null;
        deviceAuthUse = null;
        deviceAuthUnuse = null;
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals(true)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals(true)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        availableStatusIncludesList.add((short) 0);
        availableStatusIncludesList.add((short) 1);

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isNull();
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isNull();
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isNull();
        assertThat(converter.getValidThruStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getValidThruEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getValidThruStartDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getValidThruStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getValidThruEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getValidThruEndDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isNull();
        assertThat(converter.getJaIdCriteria().getEqualTo()).isEqualTo(jaId);
        assertThat(converter.getBranchIdCriteria().getEqualTo()).isEqualTo(branchId);
        assertThat(converter.getAvailableStatusCriteria().getIncludes()).usingRecursiveComparison().isEqualTo(availableStatusIncludesList);
        assertThat(converter.getSubSystemRoleConditionsSelect()).isEqualTo(subSystemRoleConditionsSelect);
        assertThat(converter.getSubSystemRoleList()).usingRecursiveComparison().isEqualTo(expectedSubSystemRoleVoList);
        assertThat(converter.getBizTranRoleConditionsSelect()).isEqualTo(bizTranRoleConditionsSelect);
        assertThat(converter.getBizTranRoleSubSystemCode()).isEqualTo(bizTranRoleSubSystemCode);
        assertThat(converter.getBizTranRoleList()).usingRecursiveComparison().isEqualTo(expectedBizTranRoleVoList);
        assertThat(converter.getAccountLockOccurredDateFrom()).isEqualTo(accountLockOccurredDateFrom);
        assertThat(converter.getAccountLockOccurredDateTo()).isEqualTo(accountLockOccurredDateTo);
        assertThat(converter.getAccountLockStatusLock()).isEqualTo(accountLockStatusLock);
        assertThat(converter.getAccountLockStatusUnlock()).isEqualTo(accountLockStatusUnlock);
        assertThat(converter.getPasswordHistoryCheck()).isEqualTo(passwordHistoryCheck);
        assertThat(converter.getPasswordHistoryLastChangeDate()).isEqualTo(passwordHistoryLastChangeDate);
        assertThat(converter.getPasswordHistoryLastChangeDateStatus()).isEqualTo(passwordHistoryLastChangeDateStatus);
        assertThat(converter.getPasswordHistoryChangeType0()).isEqualTo(passwordHistoryChangeType0);
        assertThat(converter.getPasswordHistoryChangeType1()).isEqualTo(passwordHistoryChangeType1);
        assertThat(converter.getPasswordHistoryChangeType2()).isEqualTo(passwordHistoryChangeType2);
        assertThat(converter.getPasswordHistoryChangeType3()).isEqualTo(passwordHistoryChangeType3);
        assertThat(converter.getSignintraceTrydateFrom()).isEqualTo(signintraceTrydateFrom);
        assertThat(converter.getSignintraceTrydateTo()).isEqualTo(signintraceTrydateTo);
        assertThat(converter.getSignintraceTryIpAddress()).isEqualTo(signintraceTryIpAddress);
        assertThat(converter.getSignintraceSignIn()).isEqualTo(signintraceSignIn);
        assertThat(converter.getSignintraceSignOut()).isEqualTo(signintraceSignOut);
        assertThat(converter.getSignintraceSignInResult()).isEqualTo(signintraceSignInResult);
    }
}