package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;
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
    private Short availableStatus0 = (short) 1;
    private Short availableStatus1 = (short) 1;
    private Integer expirationSelect = 1;
    private final LocalDate expirationStatusDate = LocalDate.of(2020, 9, 1);
    private final LocalDate expirationStartDateFrom = LocalDate.of(2020, 9, 2);
    private final LocalDate expirationStartDateTo = LocalDate.of(2020, 9, 3);
    private final LocalDate expirationEndDateFrom = LocalDate.of(2020, 9, 4);
    private final LocalDate expirationEndDateTo = LocalDate.of(2020, 9, 5);
    private final Integer subSystemRoleConditionsSelect = 1;
    private List<Oa11010SubSystemRoleVo> getSubSystemRoleVoList() {
        List<Oa11010SubSystemRoleVo> subSystemRoleVoList = newArrayList();
        Oa11010SubSystemRoleVo subSystemRoleVo = new Oa11010SubSystemRoleVo();
        subSystemRoleVo.setSubSystemRoleSelected((short) 1);
        subSystemRoleVo.setSubSystemRoleCode(SubSystemRole.JA管理者.getCode());
        subSystemRoleVo.setSubSystemRoleName(SubSystemRole.JA管理者.getName());
        subSystemRoleVo.setExpirationSelect(1);
        subSystemRoleVo.setExpirationStatusDate(LocalDate.of(2020, 10, 1));
        subSystemRoleVo.setExpirationStartDateFrom(LocalDate.of(2020, 10, 2));
        subSystemRoleVo.setExpirationStartDateTo(LocalDate.of(2020, 10, 3));
        subSystemRoleVo.setExpirationEndDateFrom(LocalDate.of(2020, 10, 4));
        subSystemRoleVo.setExpirationEndDateTo(LocalDate.of(2020, 10, 5));
        subSystemRoleVoList.add(subSystemRoleVo);
        subSystemRoleVo = new Oa11010SubSystemRoleVo();
        subSystemRoleVo.setSubSystemRoleSelected((short) 1);
        subSystemRoleVo.setSubSystemRoleCode(SubSystemRole.業務統括者_販売_青果.getCode());
        subSystemRoleVo.setSubSystemRoleName(SubSystemRole.業務統括者_販売_青果.getName());
        subSystemRoleVo.setExpirationSelect(2);
        subSystemRoleVo.setExpirationStatusDate(LocalDate.of(2020, 10, 6));
        subSystemRoleVo.setExpirationStartDateFrom(LocalDate.of(2020, 10, 7));
        subSystemRoleVo.setExpirationStartDateTo(LocalDate.of(2020, 10, 8));
        subSystemRoleVo.setExpirationEndDateFrom(LocalDate.of(2020, 10, 9));
        subSystemRoleVo.setExpirationEndDateTo(LocalDate.of(2020, 10, 10));
        subSystemRoleVoList.add(subSystemRoleVo);
        subSystemRoleVo = new Oa11010SubSystemRoleVo();
        subSystemRoleVo.setSubSystemRoleSelected((short) 0);
        subSystemRoleVo.setSubSystemRoleCode(SubSystemRole.業務統括者_販売_米.getCode());
        subSystemRoleVo.setSubSystemRoleName(SubSystemRole.業務統括者_販売_米.getName());
        subSystemRoleVo.setExpirationSelect(0);
        subSystemRoleVo.setExpirationStatusDate(null);
        subSystemRoleVo.setExpirationStartDateFrom(null);
        subSystemRoleVo.setExpirationStartDateTo(null);
        subSystemRoleVo.setExpirationEndDateFrom(null);
        subSystemRoleVo.setExpirationEndDateTo(null);
        subSystemRoleVoList.add(subSystemRoleVo);
        return subSystemRoleVoList;
    }
    private final Integer bizTranRoleConditionsSelect = 0;
    private final String bizTranRoleSubSystemCode = "KB";
    private final List<Oa11010SearchSubSystemRoleConverter> bizTranRoleSubSystemList = newArrayList();
    private List<Oa11010BizTranRoleVo> getBizTranRoleVoList() {
        List<Oa11010BizTranRoleVo> bizTranRoleVoList = newArrayList();
        Oa11010BizTranRoleVo bizTranRoleVo = new Oa11010BizTranRoleVo();
        bizTranRoleVo.setBizTranRoleSelected((short) 1);
        bizTranRoleVo.setBizTranRoleId(1L);
        bizTranRoleVo.setBizTranRoleCode("KBAG01");
        bizTranRoleVo.setBizTranRoleName("（購買）購買業務基本");
        bizTranRoleVo.setSubSystemCode(SubSystem.購買.getCode());
        bizTranRoleVo.setExpirationSelect(1);
        bizTranRoleVo.setExpirationStatusDate(LocalDate.of(2020, 10, 11));
        bizTranRoleVo.setExpirationStartDateFrom(LocalDate.of(2020, 10, 12));
        bizTranRoleVo.setExpirationStartDateTo(LocalDate.of(2020, 10, 13));
        bizTranRoleVo.setExpirationEndDateFrom(LocalDate.of(2020, 10, 14));
        bizTranRoleVo.setExpirationEndDateTo(LocalDate.of(2020, 10, 15));
        bizTranRoleVoList.add(bizTranRoleVo);
        bizTranRoleVo = new Oa11010BizTranRoleVo();
        bizTranRoleVo.setBizTranRoleSelected((short) 1);
        bizTranRoleVo.setBizTranRoleId(2L);
        bizTranRoleVo.setBizTranRoleCode("KBAG02");
        bizTranRoleVo.setBizTranRoleName("（購買）本所業務");
        bizTranRoleVo.setSubSystemCode(SubSystem.購買.getCode());
        bizTranRoleVo.setExpirationSelect(1);
        bizTranRoleVo.setExpirationStatusDate(LocalDate.of(2020, 10, 16));
        bizTranRoleVo.setExpirationStartDateFrom(LocalDate.of(2020, 10, 17));
        bizTranRoleVo.setExpirationStartDateTo(LocalDate.of(2020, 10, 18));
        bizTranRoleVo.setExpirationEndDateFrom(LocalDate.of(2020, 10, 19));
        bizTranRoleVo.setExpirationEndDateTo(LocalDate.of(2020, 10, 20));
        bizTranRoleVoList.add(bizTranRoleVo);
        bizTranRoleVo = new Oa11010BizTranRoleVo();
        bizTranRoleVo.setBizTranRoleSelected((short) 0);
        bizTranRoleVo.setBizTranRoleId(3L);
        bizTranRoleVo.setBizTranRoleCode("KBAG03");
        bizTranRoleVo.setBizTranRoleName("（購買）本所管理業務");
        bizTranRoleVo.setSubSystemCode(SubSystem.購買.getCode());
        bizTranRoleVo.setExpirationSelect(0);
        bizTranRoleVo.setExpirationStatusDate(null);
        bizTranRoleVo.setExpirationStartDateFrom(null);
        bizTranRoleVo.setExpirationStartDateTo(null);
        bizTranRoleVo.setExpirationEndDateFrom(null);
        bizTranRoleVo.setExpirationEndDateTo(null);
        bizTranRoleVoList.add(bizTranRoleVo);
        return bizTranRoleVoList;
    }
    private Short deviceAuthUse = (short) 0;
    private Short deviceAuthUnuse = (short) 1;
    private final LocalDate accountLockOccurredDateFrom = LocalDate.of(2020, 9, 6);
    private final LocalDate accountLockOccurredDateTo = LocalDate.of(2020, 9, 7);
    private final Short accountLockStatusLock = (short) 0;
    private final Short accountLockStatusUnlock = (short) 1;
    private final Short passwordHistoryCheck = (short) 1;
    private final Integer passwordHistoryLastChangeDate = 30;
    private final String passwordHistoryLastChangeDateStatus = "2";
    private final Short passwordHistoryChangeType0 = (short) 0;
    private final Short passwordHistoryChangeType1 = (short) 1;
    private final Short passwordHistoryChangeType2 = (short) 0;
    private final Short passwordHistoryChangeType3 = (short) 1;
    private final LocalDate signintraceTrydateFrom = LocalDate.of(2020, 9, 8);
    private final LocalDate signintraceTrydateTo = LocalDate.of(2020, 9, 9);
    private final String signintraceTryIpAddress = "001.001.001.001";
    private final Short signintraceSignIn = (short) 0;
    private final Short signintraceSignOut = (short) 1;
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
        vo.setExpirationSelect(expirationSelect);
        vo.setExpirationStatusDate(expirationStatusDate);
        vo.setExpirationStartDateFrom(expirationStartDateFrom);
        vo.setExpirationStartDateTo(expirationStartDateTo);
        vo.setExpirationEndDateFrom(expirationEndDateFrom);
        vo.setExpirationEndDateTo(expirationEndDateTo);
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
            s->s.getSubSystemRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus0())) {
            availableStatusIncludesList.add((short) 0);
        }
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus1())) {
            availableStatusIncludesList.add((short) 1);
        }

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isEqualTo(operatorCode);
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isEqualTo(operatorName);
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isEqualTo(mailAddress);
        assertThat(converter.getExpirationStartDateCriteria().getLessOrEqual()).isEqualTo(expirationStatusDate);
        assertThat(converter.getExpirationEndDateCriteria().getMoreOrEqual()).isEqualTo(expirationStatusDate);
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isEqualTo(Oa11010Vo.CHECKBOX_TRUE.equals(deviceAuthUse));
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
        expirationSelect = 2;
        availableStatus0 = (short) 0;
        availableStatus1 = (short) 0;
        deviceAuthUse = (short) 1;
        deviceAuthUnuse = (short) 0;
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus0())) {
            availableStatusIncludesList.add((short) 0);
        }
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus1())) {
            availableStatusIncludesList.add((short) 1);
        }

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isEqualTo(operatorCode);
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isEqualTo(operatorName);
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isEqualTo(mailAddress);
        assertThat(converter.getExpirationStartDateCriteria().getMoreOrEqual()).isEqualTo(expirationStartDateFrom);
        assertThat(converter.getExpirationStartDateCriteria().getLessOrEqual()).isEqualTo(expirationStartDateTo);
        assertThat(converter.getExpirationEndDateCriteria().getMoreOrEqual()).isEqualTo(expirationEndDateFrom);
        assertThat(converter.getExpirationEndDateCriteria().getLessOrEqual()).isEqualTo(expirationEndDateTo);
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isEqualTo(Oa11010Vo.CHECKBOX_TRUE.equals(deviceAuthUse));
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
        expirationSelect = 0;
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus0())) {
            availableStatusIncludesList.add((short) 0);
        }
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus1())) {
            availableStatusIncludesList.add((short) 1);
        }

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isNull();
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isNull();
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isNull();
        assertThat(converter.getExpirationStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getExpirationEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getExpirationStartDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getExpirationStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getExpirationEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getExpirationEndDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getIsDeviceAuthCriteria().getEqualTo()).isEqualTo(Oa11010Vo.CHECKBOX_TRUE.equals(deviceAuthUse));
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
        expirationSelect = null;
        deviceAuthUse = null;
        deviceAuthUnuse = null;
        Oa11010Vo vo = createOa11010Vo();

        // 期待値
        List<Oa11010SubSystemRoleVo> expectedSubSystemRoleVoList = getSubSystemRoleVoList().stream().filter(
            s->s.getSubSystemRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Oa11010BizTranRoleVo> expectedBizTranRoleVoList = getBizTranRoleVoList().stream().filter(
            s->s.getBizTranRoleSelected().equals((short)1)).collect(Collectors.toList());
        List<Short> availableStatusIncludesList = newArrayList();
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus0())) {
            availableStatusIncludesList.add((short) 0);
        }
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus1())) {
            availableStatusIncludesList.add((short) 1);
        }

        // 実行
        Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa11010SearchConverter);
        assertThat(converter.getOperatorCodeCriteria().getForwardMatch()).isNull();
        assertThat(converter.getOperatorNameCriteria().getForwardMatch()).isNull();
        assertThat(converter.getMailAddressCriteria().getForwardMatch()).isNull();
        assertThat(converter.getExpirationStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getExpirationEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getExpirationStartDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getExpirationStartDateCriteria().getLessOrEqual()).isNull();
        assertThat(converter.getExpirationEndDateCriteria().getMoreOrEqual()).isNull();
        assertThat(converter.getExpirationEndDateCriteria().getLessOrEqual()).isNull();
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