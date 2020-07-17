package net.jagunma.backbone.auth.application.queryService;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubSystemReferenceService {
	public SubSystemReferenceService() {
	}

	/***
	 * コンボボックス用のリストを取得します。
	 * @return コンボボックス用のリスト
	 */
	public Map<String, String> getComboBoxList() {
		//TODO:　サブシステム情報を読む
		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("", "");
		selectMap.put("KB", "購買");
		selectMap.put("YS", "販売・青果");
		selectMap.put("YF", "販売・花卉");
		selectMap.put("HK", "販売・米");
		selectMap.put("HM", "販売・麦");
		selectMap.put("AN", "販売・畜産");
		return selectMap;
	}
}
