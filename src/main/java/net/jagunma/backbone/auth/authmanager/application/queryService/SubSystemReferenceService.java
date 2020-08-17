package net.jagunma.backbone.auth.authmanager.application.queryService;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystem;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystemsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * サブシステム参照サービス
 */
@Service
@Transactional
public class SubSystemReferenceService {

	private final SubSystemsRepository subSystemsRepository;
	
	// コンストラクタ
	public SubSystemReferenceService(SubSystemsRepository subSystemsRepository) {
		this.subSystemsRepository = subSystemsRepository;
	}

//	/**
//	 * サブシステムリストを取得します。
//	 * @return サブシステムリスト
//	 */
//	public List<SubSystemReferenceDto> getSubSystemList() {
//		List<SubSystemReferenceDto> list = newArrayList();
//
//		for(SubSystemType sybsysrem : SubSystemType.values()) {
//			//System.out.println(sybsysrem.getSubSystemName() + "," + sybsysrem.name() + "," + sybsysrem.ordinal());
//			list.add(new SubSystemReferenceDto(sybsysrem.name(), sybsysrem.getSubSystemName()));
//		}
//		return list;
//	}

	/***
	 * コンボボックス用のリストを取得します。
	 *
	 * @return コンボボックス用のリスト
	 */
	public List<SubSystem> getComboBoxList() {
		//List<SubSystemReferenceDto> list = getSubSystemList();
		//list.add(0, new SubSystemReferenceDto("", ""));
		List<SubSystem> list = subSystemsRepository.selectAll().getValues();
		list.add(0, SubSystem.empty());

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
