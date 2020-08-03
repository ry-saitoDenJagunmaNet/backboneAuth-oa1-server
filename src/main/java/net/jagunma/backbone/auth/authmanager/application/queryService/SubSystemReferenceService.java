package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemReferenceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubSystemReferenceService {
	public SubSystemReferenceService() {
	}

	/**
	 * サブシステムリストを取得します。
	 * @return サブシステムリスト
	 */
	public List<SubSystemReferenceDto> getSubSystemList() {
		List<SubSystemReferenceDto> list = newArrayList();

		for(SubSystem sybsysrem : SubSystem.values()) {
			//System.out.println(sybsysrem.getSubSystemName() + "," + sybsysrem.name() + "," + sybsysrem.ordinal());
			list.add(new SubSystemReferenceDto(sybsysrem.name(), sybsysrem.getSubSystemName()));
		}
		return list;
	}

	/***
	 * コンボボックス用のリストを取得します。
	 * @return コンボボックス用のリスト
	 */
	public List<SubSystemReferenceDto> getComboBoxList() {
		List<SubSystemReferenceDto> list = getSubSystemList();
		list.add(0, new SubSystemReferenceDto("", ""));

//		List<SubSystemDto> list = newArrayList();
//		list.add(new SubSystemDto("", ""));
//		list.add(new SubSystemDto("KB", "購買・全般"));
//		list.add(new SubSystemDto("YS", "販売・青果"));
//		list.add(new SubSystemDto("YF", "販売・花卉"));
//		list.add(new SubSystemDto("HK", "販売・米"));
//		list.add(new SubSystemDto("HM", "販売・麦"));
//		list.add(new SubSystemDto("AN", "販売・畜産"));

		return list;
	}
}
