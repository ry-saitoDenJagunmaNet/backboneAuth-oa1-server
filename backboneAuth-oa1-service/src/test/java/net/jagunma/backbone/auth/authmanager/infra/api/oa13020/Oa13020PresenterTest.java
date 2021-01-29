package net.jagunma.backbone.auth.authmanager.infra.api.oa13020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessibleDto;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa13020PresenterTest {

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
     * {@link Oa13020Presenter#getResponse()} のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・responseへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void getResponse_test0() {

        // テスト対象クラス生成
        Oa13020Presenter presenter = new Oa13020Presenter();

        // 実行値
        List<SearchAccessibleDto> searchAccessibleDtoList = createSearchAccessibleDtoList();
        presenter.setSearchAccessibleDtoList(searchAccessibleDtoList);

        // 期待値
        Map<String, List<String>> expectedSearchAccessible = new HashMap<>();
        for (SearchAccessibleDto dto : createSearchAccessibleDtoList()) {
            expectedSearchAccessible.put(dto.getSubSystemCode(), dto.getBizTranCodeList());
        }

        // 実行
        Map<String, List<String>> actualSearchAccessible = presenter.getResponse();

        // 結果検証
        assertThat(actualSearchAccessible).usingRecursiveComparison().isEqualTo(expectedSearchAccessible);
    }
}