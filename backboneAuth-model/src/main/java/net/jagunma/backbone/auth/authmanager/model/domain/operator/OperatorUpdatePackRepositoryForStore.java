package net.jagunma.backbone.auth.authmanager.model.domain.operator;

/**
 * オペレーターアップデートパック格納
 */
public interface OperatorUpdatePackRepositoryForStore {

    /**
     * オペレーターアップデートパックの更新を行います。
     *
     * @param operatorUpdatePack オペレーターアップデートパック
     */
    void update(OperatorUpdatePack operatorUpdatePack);

}