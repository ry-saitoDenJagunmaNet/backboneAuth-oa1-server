package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TempoReferenceService {
	private final OperatorEntityDao operatorEntityDao;

	public TempoReferenceService(OperatorEntityDao operatorEntityDao) {

		this.operatorEntityDao = operatorEntityDao;
	}

	/***
	 * コンボボックス用のリストを取得します。
	 * @param jaid ＪＡID
	 * @return コンボボックス用のリスト
	 */
	public List<TempoReferenceDto> getComboBoxList(long jaid) {

		// TODO: 店舗情報はCMSよりAPIで取得
//		List<TempoDto> list = newArrayList();
//		list.add(new TempoDto("", ""));
//		list.add(new TempoDto("001", "本所"));
//		list.add(new TempoDto("002", "○○○支所"));
//		list.add(new TempoDto("003", "△△センター"));
//		list.add(new TempoDto("004", "□□□□店"));
//		list.add(new TempoDto("005", "××××館"));
//		return list;
		List<TempoReferenceDto> list = getTempoList(jaid);
		list.add(0, new TempoReferenceDto("", ""));
		return list;
	}

	/**
	 * コンボボックス用のリストを取得します。
	 * @param jaid ＪＡID
	 * @return コンボボックス用のリスト
	 */
	public List<TempoReferenceDto> getTempoList(long jaid) {
		List<TempoReferenceDto> list = newArrayList();

		// TODO: 店舗情報はCMSよりAPIで取得
		// TODO: 暫定でオペレーターテーブルからJA毎に店舗コードを取得
		OperatorEntityCriteria criteria = new OperatorEntityCriteria();
		criteria.getJaIdCriteria().setEqualTo(jaid);

		Orders orders = Orders.empty()
			.addOrder("JaId")
			.addOrder("TempoId");

		List<OperatorEntity> operatorEntitys = operatorEntityDao.findBy(criteria, orders);
		List<String> tempos = newArrayList();
		operatorEntitys.forEach(o -> {
			tempos.add(o.getTempoCode());
		});

		// 重複削除
		tempos.stream().distinct().forEach(t -> {
			TempoReferenceDto tempoReferenceDto = new TempoReferenceDto();
			tempoReferenceDto.setTempoCode(t);
			tempoReferenceDto.setTempoName(t+"店舗");
			list.add(tempoReferenceDto);
		});

		return list;
	}


}
