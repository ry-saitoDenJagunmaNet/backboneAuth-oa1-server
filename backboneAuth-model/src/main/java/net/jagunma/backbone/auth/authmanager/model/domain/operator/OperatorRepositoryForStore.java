package net.jagunma.backbone.auth.authmanager.model.domain.operator;

/**
 * オペレーター格納
 */
public interface OperatorRepositoryForStore {

    /**
     * オペレーターの登録を行います。
     *
     * @param operatorEntryPack オペレーターエントリーパック
     */
    void entry(OperatorEntryPack operatorEntryPack);

    /**
     * オペレーターの更新を行います。
     *
     * @param operatorUpdatePack オペレーターアップデートパック
     */
    void update(OperatorUpdatePack operatorUpdatePack);
}