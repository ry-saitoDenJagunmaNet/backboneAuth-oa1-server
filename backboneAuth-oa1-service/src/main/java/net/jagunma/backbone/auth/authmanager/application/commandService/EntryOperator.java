package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPackRepositoryForStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * オペレーター登録サービス
 */
@Service
@Transactional
public class EntryOperator {

	private final OperatorEntryPackRepositoryForStore operatorEntryPackRepositoryForStore;

	public EntryOperator(OperatorEntryPackRepositoryForStore operatorEntryPackRepositoryForStore) {

		this.operatorEntryPackRepositoryForStore = operatorEntryPackRepositoryForStore;
	}

	/**
	 * オペレーターの登録を行います。
	 *
	 * @param request オペレーター登録サービス Request
	 */
	public void execute(OperatorEntryRequest request) {

		// パラメーターの検証
		EntryOperatorValidator.with(request).validate();

		// ToDo: JAの取得
		// ToDo: 店舗の取得
		// ToDo: JAに属する店舗かのチェック

		// オペレーターエントリーパック生成
		OperatorEntryPack operatorEntryPack = createOperatorEntryPack(request);

		// オペレーターエントリーパック登録
		operatorEntryPackRepositoryForStore.insert(operatorEntryPack);
	}

	/**
	 * オペレーターエントリーパックの生成を行います。
	 *
	 * @param request オペレーター登録サービス Request
	 * @param jaAtMoment JaAtMoment
	 * @param tempoAtMoment TempoAtMoment
	 * @return オペレーターエントリーパック
	 */
	private OperatorEntryPack createOperatorEntryPack(OperatorEntryRequest request) {

		// ToDo: JA、店舗の受け取り
		return OperatorEntryPack.createFrom(
			request.getOperatorCode(),
			request.getOperatorName(),
			request.getMailAddress(),
			request.getExpirationStartDate(),
			request.getExpirationEndDate(),
			6l,
			"006",
			request.getTempoId(),
			"001",
			request.getChangeCause(),
			request.getPassword(),
			request.getConfirmPassword());
	}
}
