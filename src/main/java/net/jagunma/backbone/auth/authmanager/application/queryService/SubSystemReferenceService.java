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

	/***
	 * コンボボックス用のリストを取得します。
	 *
	 * @return コンボボックス用のリスト
	 */
	public List<SubSystem> getComboBoxList() {
		List<SubSystem> list = subSystemsRepository.selectAll().getValues();
		list.add(0, SubSystem.empty());
		return list;
	}
}
