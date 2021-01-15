package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.commandService.DeleteSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.commandService.EntrySuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.commandService.UpdateSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranDeleteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa12030ControllerTest {

    // 実行 ＆ 期待 既定値
    private Long suspendBizTranId = 123456789L;
    private Boolean jaCheck = true;
    private String jaCode = "006";
    private Boolean branchCheck = true;
    private String branchCode = "001";
    private Boolean subSystemCheck = true;
    private String subSystemCode = SubSystem.販売_畜産.getCode();
    private Boolean bizTranGrpCheck = true;
    private String bizTranGrpCode = "ANTG01";
    private Boolean bizTranCheck = true;
    private String bizTranCode = "AN0001";
    private LocalDate suspendStartDate = LocalDate.of(2020,12,1);
    private LocalDate suspendEndDate = LocalDate.of(2020,12,2);
    private String suspendReason = "抑止理由";
    private Integer recordVersion = 1;

    private final String GunmaRuntimeExceptionMessageCode = "EOA13008";
    private final String GunmaRuntimeExceptionMessageArg1 = "抑止期間開始";

    private final String expectedViewName = "oa12030";
    private final String expectedRedirectViewMethod = "redirect:/oa12020/backSearch";

    private SuspendBizTran actualSuspendBizTran;

    // テスト対象クラス生成
    private Oa12030Controller createOa12030Controller(Integer throwExceptio) {
        // 一時取引抑止リスト検索サービスの作成
        SearchSuspendBizTran searchSuspendBizTran = new SearchSuspendBizTran(
            new SuspendBizTranRepository() {
                @Override
                public SuspendBizTran findOneById(Long suspendBizTranId) {
                    return null;
                }
                @Override
                public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {
                    return null;
                }
            }
        ) {
            public void execute(SuspendBizTranSearchRequest request, SuspendBizTranSearchResponse response) {
                if (throwExceptio == null) {
                    response.setSuspendBizTranId(suspendBizTranId);
                    response.setJaCode(jaCode);
                    response.setBranchCode(branchCode);
                    response.setSubSystemCode(subSystemCode);
                    response.setBizTranGrpCode(bizTranGrpCode);
                    response.setBizTranCode(bizTranCode);
                    response.setSuspendStartDate(suspendStartDate);
                    response.setSuspendEndDate(suspendEndDate);
                    response.setSuspendReason(suspendReason);
                    response.setRecordVersion(recordVersion);
                    return;
                }
                // createOa12030Controllerの引数 throwExceptio == -1 の場合：RuntimeException を発生させる
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12030Controllerの引数 throwExceptio == -2 の場合：GunmaRuntimeException を発生させる
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1));
                }
            }
        };
        // 一時取引抑止登録サービスの作成
        EntrySuspendBizTran entrySuspendBizTran = new EntrySuspendBizTran(createSuspendBizTranRepositoryForStore()) {
            public void execute(SuspendBizTranEntryRequest request) {
                if (throwExceptio == null) {
                    actualSuspendBizTran = SuspendBizTran.createFrom(
                        null,
                        request.getJaCode(),
                        request.getBranchCode(),
                        request.getSubSystemCode(),
                        request.getBizTranGrpCode(),
                        request.getBizTranCode(),
                        request.getSuspendStartDate(),
                        request.getSuspendEndDate(),
                        request.getSuspendReason(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                    return;
                }
                // createOa12030Controllerの引数 throwExceptio == -1 の場合：RuntimeException を発生させる
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12030Controllerの引数 throwExceptio == -2 の場合：GunmaRuntimeException を発生させる
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1));
                }
            }
        };
        // 一時取引抑止更新サービスの作成
        UpdateSuspendBizTran updateSuspendBizTran = new UpdateSuspendBizTran(createSuspendBizTranRepositoryForStore()) {
            public void execute(SuspendBizTranUpdateRequest request) {
                if (throwExceptio == null) {
                    actualSuspendBizTran = SuspendBizTran.createFrom(
                        request.getSuspendBizTranId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        request.getSuspendStartDate(),
                        request.getSuspendEndDate(),
                        null,
                        request.getRecordVersion(),
                        null,
                        null,
                        null,
                        null,
                        null);
                    return;
                }
                // createOa12030Controllerの引数 throwExceptio == -1 の場合：RuntimeException を発生させる
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12030Controllerの引数 throwExceptio == -2 の場合：GunmaRuntimeException を発生させる
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1));
                }
            }
        };
        // 一時取引抑止削除サービスの作成
        DeleteSuspendBizTran deleteSuspendBizTran = new DeleteSuspendBizTran(createSuspendBizTranRepositoryForStore()) {
            public void execute(SuspendBizTranDeleteRequest request) {
                if (throwExceptio == null) {
                    actualSuspendBizTran = SuspendBizTran.createFrom(
                        request.getSuspendBizTranId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        request.getRecordVersion(),
                        null,
                        null,
                        null,
                        null,
                        null);
                    return;
                }
                // createOa12030Controllerの引数 throwExceptio == -1 の場合：RuntimeException を発生させる
                if (throwExceptio == -1) {
                    throw new RuntimeException();
                }
                // createOa12030Controllerの引数 throwExceptio == -2 の場合：GunmaRuntimeException を発生させる
                if (throwExceptio == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1));
                }
            }
        };
        return new Oa12030Controller(searchSuspendBizTran, entrySuspendBizTran, updateSuspendBizTran, deleteSuspendBizTran);
    }
    // 一時取引抑止格納の作成
    private SuspendBizTranRepositoryForStore createSuspendBizTranRepositoryForStore() {
        return new SuspendBizTranRepositoryForStore() {
            @Override
            public SuspendBizTran insert(SuspendBizTran suspendBizTran) {
                return null;
            }
            @Override
            public SuspendBizTran update(SuspendBizTran suspendBizTran) {
                return null;
            }
            @Override
            public void delete(SuspendBizTran suspendBizTran) {

            }
        };
    }

    /**
     * {@link Oa12030Controller#get(Long, Model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        suspendBizTranId = null;
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();

        // 実行
        String actualViewName = oa12030Controller.get(suspendBizTranId, model);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12030Controller#get(Long, Model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        expectedVo.setSuspendBizTranId(suspendBizTranId);
        expectedVo.setJaCode(jaCode);
        expectedVo.setBranchCode(branchCode);
        expectedVo.setSubSystemCode(subSystemCode);
        expectedVo.setBizTranGrpCode(bizTranGrpCode);
        expectedVo.setBizTranCode(bizTranCode);
        expectedVo.setSuspendStartDate(suspendStartDate);
        expectedVo.setSuspendEndDate(suspendEndDate);
        expectedVo.setSuspendReason(suspendReason);
        expectedVo.setRecordVersion(recordVersion);

        // 実行
        String actualViewName = oa12030Controller.get(suspendBizTranId, model);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12030Controller#get(Long, Model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-1);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa19999";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12030Controller.get(suspendBizTranId, model);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(messageCode);
    }

    /**
     * {@link Oa12030Controller#get(Long, Model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException ）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test3() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-2);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 実行
        String actualViewName = oa12030Controller.get(suspendBizTranId, model);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
    }

    /**
     * {@link Oa12030Controller#entry(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test0() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        jaCode = null;
        branchCode = null;
        subSystemCode = null;
        bizTranGrpCode = null;
        bizTranCode = null;
        suspendStartDate = null;
        suspendEndDate = null;
        suspendReason = null;

        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setJaCheck(jaCheck);
        oa12030Vo.setJaCode(jaCode);
        oa12030Vo.setBranchCheck(branchCheck);
        oa12030Vo.setBranchCode(branchCode);
        oa12030Vo.setSubSystemCheck(subSystemCheck);
        oa12030Vo.setSubSystemCode(subSystemCode);
        oa12030Vo.setBizTranGrpCheck(bizTranGrpCheck);
        oa12030Vo.setBizTranGrpCode(bizTranGrpCode);
        oa12030Vo.setBizTranCheck(bizTranCheck);
        oa12030Vo.setBizTranCode(bizTranCode);
        oa12030Vo.setSuspendStartDate(suspendStartDate);
        oa12030Vo.setSuspendEndDate(suspendEndDate);
        oa12030Vo.setSuspendReason(suspendReason);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            null,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            null,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.entry(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#entry(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test1() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setJaCheck(jaCheck);
        oa12030Vo.setJaCode(jaCode);
        oa12030Vo.setBranchCheck(branchCheck);
        oa12030Vo.setBranchCode(branchCode);
        oa12030Vo.setSubSystemCheck(subSystemCheck);
        oa12030Vo.setSubSystemCode(subSystemCode);
        oa12030Vo.setBizTranGrpCheck(bizTranGrpCheck);
        oa12030Vo.setBizTranGrpCode(bizTranGrpCode);
        oa12030Vo.setBizTranCheck(bizTranCheck);
        oa12030Vo.setBizTranCode(bizTranCode);
        oa12030Vo.setSuspendStartDate(suspendStartDate);
        oa12030Vo.setSuspendEndDate(suspendEndDate);
        oa12030Vo.setSuspendReason(suspendReason);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            null,
            jaCode,
            branchCode,
            subSystemCode,
            bizTranGrpCode,
            bizTranCode,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            null,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.entry(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#entry(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常（Voの選択項目チェックOff(null)）
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test2() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        jaCheck = null;
        branchCheck = null;
        subSystemCheck = null;
        bizTranGrpCheck = null;
        bizTranCheck = null;

        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setJaCheck(jaCheck);
        oa12030Vo.setJaCode(jaCode);
        oa12030Vo.setBranchCheck(branchCheck);
        oa12030Vo.setBranchCode(branchCode);
        oa12030Vo.setSubSystemCheck(subSystemCheck);
        oa12030Vo.setSubSystemCode(subSystemCode);
        oa12030Vo.setBizTranGrpCheck(bizTranGrpCheck);
        oa12030Vo.setBizTranGrpCode(bizTranGrpCode);
        oa12030Vo.setBizTranCheck(bizTranCheck);
        oa12030Vo.setBizTranCode(bizTranCode);
        oa12030Vo.setSuspendStartDate(suspendStartDate);
        oa12030Vo.setSuspendEndDate(suspendEndDate);
        oa12030Vo.setSuspendReason(suspendReason);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            null,
            null,
            null,
            null,
            null,
            null,
            suspendStartDate,
            suspendEndDate,
            suspendReason,
            null,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.entry(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#entry(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test3() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-1);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12030Controller.entry(model, oa12030Vo);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(messageCode);
    }

    /**
     * {@link Oa12030Controller#entry(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException ）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test4() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-2);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();

        // 実行
        String actualViewName = oa12030Controller.entry(model, oa12030Vo);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
    }

    /**
     * {@link Oa12030Controller#update(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test0() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        suspendBizTranId = null;
        suspendStartDate = null;
        suspendEndDate = null;
        recordVersion = null;

        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setSuspendBizTranId(suspendBizTranId);
        oa12030Vo.setSuspendStartDate(suspendStartDate);
        oa12030Vo.setSuspendEndDate(suspendEndDate);
        oa12030Vo.setRecordVersion(recordVersion);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            null,
            null,
            null,
            null,
            null,
            suspendStartDate,
            suspendEndDate,
            null,
            recordVersion,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.update(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#update(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test1() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setSuspendBizTranId(suspendBizTranId);
        oa12030Vo.setSuspendStartDate(suspendStartDate);
        oa12030Vo.setSuspendEndDate(suspendEndDate);
        oa12030Vo.setRecordVersion(recordVersion);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            null,
            null,
            null,
            null,
            null,
            suspendStartDate,
            suspendEndDate,
            null,
            recordVersion,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.update(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#update(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test2() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-1);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12030Controller.update(model, oa12030Vo);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(messageCode);
    }

    /**
     * {@link Oa12030Controller#update(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException ）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test3() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-2);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();

        // 実行
        String actualViewName = oa12030Controller.update(model, oa12030Vo);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
    }

    /**
     * {@link Oa12030Controller#delete(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void delete_test0() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        suspendBizTranId = null;
        recordVersion = null;

        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setSuspendBizTranId(suspendBizTranId);
        oa12030Vo.setRecordVersion(recordVersion);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            recordVersion,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.delete(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#delete(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void delete_test1() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(null);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();
        oa12030Vo.setSuspendBizTranId(suspendBizTranId);
        oa12030Vo.setRecordVersion(recordVersion);

        // 期待値
        Oa12030Vo expectedVo = new Oa12030Vo();
        SuspendBizTran expectedSuspendBizTran = SuspendBizTran.createFrom(
            suspendBizTranId,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            recordVersion,
            null,
            null,
            null,
            null,
            null);

        // 実行
        String actualViewName = oa12030Controller.delete(model, oa12030Vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedRedirectViewMethod);
        assertThat(actualSuspendBizTran).usingRecursiveComparison().isEqualTo(expectedSuspendBizTran);
    }

    /**
     * {@link Oa12030Controller#delete(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void delete_test2() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-1);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String messageCode = "EOA10001";

        // 実行
        String actualViewName = oa12030Controller.delete(model, oa12030Vo);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(messageCode);
    }

    /**
     * {@link Oa12030Controller#delete(Model, Oa12030Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException ）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void delete_test3() {

        // テスト対象クラス生成
        Oa12030Controller oa12030Controller = createOa12030Controller(-2);

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa12030Vo oa12030Vo = new Oa12030Vo();

        // 実行
        String actualViewName = oa12030Controller.delete(model, oa12030Vo);
        Oa12030Vo actualVo = (Oa12030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
    }
}
