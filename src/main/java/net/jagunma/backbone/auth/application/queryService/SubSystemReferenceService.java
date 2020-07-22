package net.jagunma.backbone.auth.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.application.queryService.dto.SubSystemDto;
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
	public List<SubSystemDto> getComboBoxList() {
		List<SubSystemDto> list = newArrayList();
		list.add(new SubSystemDto("", ""));
		list.add(new SubSystemDto("KB", "購買・全般"));
		list.add(new SubSystemDto("YS", "販売・青果"));
		list.add(new SubSystemDto("YF", "販売・花卉"));
		list.add(new SubSystemDto("HK", "販売・米"));
		list.add(new SubSystemDto("HM", "販売・麦"));
		list.add(new SubSystemDto("AN", "販売・畜産"));

		return list;
	}
}
