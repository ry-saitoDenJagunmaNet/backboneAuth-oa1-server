package net.jagunma.backbone.auth.authmanager.application.model.domain.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;

/**
 * オペレーター群
 */
public class Operators {

	private final ArrayList<Operator> list = newArrayList();

	// コンストラクタ
	Operators(Collection<Operator> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーターリストから作成します。
	 *
	 * @param operatorList オペレーターリスト
	 * @return オペレーター群
	 */
	public static Operators createFrom(List<OperatorEntity> operatorList) {
		List<Operator> operators = new ArrayList<>();

		operatorList.forEach(d -> {
			Operator operator = Operator.of(d);
			operators.add(operator);
		});
		return new Operators(operators);
	}

	/**
	 * オペレーターリストを取得します。
	 *
	 * @return オペレーターリスト
	 */
	public List<Operator> getValues() {
		return list;
	}

	/**
	 * オブジェクトの比較を行います。
	 *
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			if (list.isEmpty() && ((Operators) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((Operators) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
