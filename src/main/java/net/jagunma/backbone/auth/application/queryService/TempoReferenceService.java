package net.jagunma.backbone.auth.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.application.queryService.dto.TempoDto;
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
	public List<TempoDto> getComboBoxList() {
		List<TempoDto> list = newArrayList();
		list.add(new TempoDto("", ""));
		list.add(new TempoDto("001", "本所"));
		list.add(new TempoDto("002", "○○○支所"));
		list.add(new TempoDto("003", "△△センター"));
		list.add(new TempoDto("004", "□□□□店"));
		list.add(new TempoDto("005", "××××館"));
		return list;
	}
}
