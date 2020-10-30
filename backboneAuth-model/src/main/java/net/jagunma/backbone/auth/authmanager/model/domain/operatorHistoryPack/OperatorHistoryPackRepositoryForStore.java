package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack;

/**
 * オペレーター履歴格納
 */
public interface OperatorHistoryPackRepositoryForStore {

    /**
     * オペレーター履歴の格納を行います
     *
     * @param operatorId オペレーターID
     * @param changeCause 変更事由
     */
    void store(Long operatorId, String changeCause);

}