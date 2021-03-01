package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import java.util.List;
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
     * Resultに変換します
     *
     * @param list 権限リスト
     */
    public void bindTo(List<Oa31020AccessibleResult> list) {
        for (SearchAccessibleDto dto : searchAccessibleDtoList) {
            Oa31020AccessibleResult result = new Oa31020AccessibleResult();
            result.setSubSystemCode(dto.getSubSystemCode());
            result.setBizTranCodeList(dto.getBizTranCodeList());
            list.add(result);
        }
    }
}
