package net.jagunma.backbone.auth.authmanager.model.domain.operator;

/**
 * オペレーターエントリーパック登録
 */
public interface OperatorEntryPackRepositoryForStore {

	/**
	 * オペレーターエントリーパックをインサートします
	 *
	 * @param operatorEntryPack オペレーターエントリーパック
	 */
	void insert(OperatorEntryPack operatorEntryPack);

}