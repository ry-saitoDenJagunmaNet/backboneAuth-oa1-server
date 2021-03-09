package net.jagunma.backbone.auth.authmanager.application.api_queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchSimpleOperatorTest {

    // 実行既定値
    private Long operatorId = 1018L;
    private String operatorCode = "yu001009";
    private String operatorName = "ｙｕ００１００９";
    private String mailAddress = "yu001009@abcd.com";
    private LocalDate validThruStartDate = LocalDate.of(2010,8,17);
    private LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 33L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer recordVersion = 1;
    private BranchAtMoment branchAtMoment = null;

    // 検証値
    private Operator actualOperator;
    private String actualOperatorCode;

    // オペレーターデータ作成
    private Operator createOperator() {
        return Operator.createFrom(
            operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            validThruStartDate,
            validThruEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            branchAtMoment);
    }

    // オペレーター（簡略版）検索サービス（テスト対象クラス）
    private SearchSimpleOperator createSearchSimpleOperator() {
        // オペレーターリポジトリのスタブ
        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneByCode(String operatorCode) {
                actualOperatorCode = operatorCode;
                return createOperator();
            }
            @Override
            public Operator findOneById(Long operatorId) {
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
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        return new SearchSimpleOperator(operatorRepository);
    }
    // オペレーター（簡略版）検索サービス Requestのスタブ
    private SimpleOperatorSearchRequest createSimpleOperatorSearchRequest() {
        return new SimpleOperatorSearchRequest() {
            @Override
            public String getOperatorCode() {
                return operatorCode;
            }
        };
    }
    // オペレーター（簡略版）検索サービス Responseのスタブ
    private SimpleOperatorSearchResponse createSimpleOperatorSearchResponse() {
        return new SimpleOperatorSearchResponse() {
            @Override
            public void setOperator(Operator operator) {
                actualOperator = operator;
            }
        };
    }

    /**
     * {@link SearchSimpleOperator#execute(SimpleOperatorSearchRequest, SimpleOperatorSearchResponse)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・結果、リポジトリの引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        SearchSimpleOperator SearchSimpleOperator = createSearchSimpleOperator();

        // 実行値
        SimpleOperatorSearchRequest request = createSimpleOperatorSearchRequest();
        SimpleOperatorSearchResponse response = createSimpleOperatorSearchResponse();

        // 期待値
        Operator expectedOperator = createOperator();
        String expectedOperatorCode = operatorCode;

        assertThatCode(() ->
            // 実行
            SearchSimpleOperator.execute(request, response)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualOperator).usingRecursiveComparison().isEqualTo(expectedOperator);
        assertThat(actualOperatorCode).isEqualTo(expectedOperatorCode);
    }
}