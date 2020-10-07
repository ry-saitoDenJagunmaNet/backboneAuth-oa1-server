package net.jagunma.backbone.auth.authmanager.model.domain.operator;

/**
 * オペレーターエントリーパック格納
 */
public interface OperatorEntryPackRepositoryForStore {

    /**
     * オペレーターエントリーパックの登録を行います。
     *
     * @param operatorEntryPack オペレーターエントリーパック
     */
    void entry(OperatorEntryPack operatorEntryPack);

}