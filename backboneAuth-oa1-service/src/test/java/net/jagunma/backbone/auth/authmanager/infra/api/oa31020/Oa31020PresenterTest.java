package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessibleDto;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31020PresenterTest {

    // 実行既定値

    // 可能取引リストデータの作成
    private List<SearchAccessibleDto> createSearchAccessibleDtoList() {
        List<SearchAccessibleDto> list = newArrayList();
        list.add(createSearchAccessibleDto("KB", newArrayList("KB0000","KB0001")));
        list.add(createSearchAccessibleDto("AN", newArrayList("AN0100","AN0200")));
        return list;
    }
    // 可能取引データの作成
    private SearchAccessibleDto  createSearchAccessibleDto(
        String subSystemCode,
        List<String> bizTranCodeList) {

        SearchAccessibleDto dto = new SearchAccessibleDto();
        dto.setSubSystemCode(subSystemCode);
        dto.setBizTranCodeList(bizTranCodeList);
        return dto;
    }

    /**
     * {@link Oa31020Presenter#bindTo(List<Oa31020AccessibleResult>)} のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・responseへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // テスト対象クラス生成
        Oa31020Presenter presenter = new Oa31020Presenter();

        // 実行値
        List<SearchAccessibleDto> searchAccessibleDtoList = createSearchAccessibleDtoList();
        presenter.setSearchAccessibleDtoList(searchAccessibleDtoList);
        List<Oa31020AccessibleResult> actualList = newArrayList();

        // 期待値
        List<Oa31020AccessibleResult> expectedList = newArrayList();
        for (SearchAccessibleDto dto : createSearchAccessibleDtoList()) {
            Oa31020AccessibleResult result = new Oa31020AccessibleResult();
            result.setSubSystemCode(dto.getSubSystemCode());
            result.setBizTranCodeList(dto.getBizTranCodeList());
            expectedList.add(result);
        }

        // 実行
        presenter.bindTo(actualList);

        // 結果検証
        assertThat(actualList).usingRecursiveComparison().isEqualTo(expectedList);
    }
}