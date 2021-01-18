package net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchAccessibleDto;

/**
 * 可能取引検索サービス Response
 */
public interface AccessibleSearchResponse {

    void setSearchAccessibleDtoList(List<SearchAccessibleDto> searchAccessibleDtoList);
}
