package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessibleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchResponse;

/**
 * OA31020 権限取得 Presenter
 */
public class Oa31020Presenter implements AccessibleSearchResponse {

    private List<SearchAccessibleDto> searchAccessibleDtoList;

    /**
     * 可能取引リストのＳｅｔ
     *
     * @param searchAccessibleDtoList 可能取引リスト
     */
    public void setSearchAccessibleDtoList(
        List<SearchAccessibleDto> searchAccessibleDtoList) {
        this.searchAccessibleDtoList = searchAccessibleDtoList;
    }

    /**
     * 可能取引リストのＧｅｔ
     *
     * @return 可能取引リスト
     */
    public Map<String, List<String>> getResponse() {
        Map<String, List<String>> response = new HashMap<>();
        for (SearchAccessibleDto dto : searchAccessibleDtoList) {
            response.put(dto.getSubSystemCode(), dto.getBizTranCodeList());
        }
        return response;
    }
}
