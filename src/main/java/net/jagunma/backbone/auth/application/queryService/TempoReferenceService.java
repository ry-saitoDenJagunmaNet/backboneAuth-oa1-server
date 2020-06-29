package net.jagunma.backbone.auth.application.queryService;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TempoReferenceService {
	public TempoReferenceService() {
	}

	/***
	 * コンボボックス用のリストを取得します。
	 * @return コンボボックス用のリスト
	 */
	public Map<String, String> getComboBoxList() {
		//TODO:　オペレータ情報を読む
		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("", "");
		selectMap.put("001", "本所");
		selectMap.put("002", "○○○支所");
		selectMap.put("003", "△△センター");
		selectMap.put("004", "□□□□店");
		selectMap.put("005", "××××館");
		return selectMap;
	}
}
