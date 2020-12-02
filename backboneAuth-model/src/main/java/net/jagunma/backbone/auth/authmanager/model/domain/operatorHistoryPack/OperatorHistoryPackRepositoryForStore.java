package net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack;

import java.time.LocalDateTime;

/**
 * オペレーター履歴格納
 */
public interface OperatorHistoryPackRepositoryForStore {

    /**
     * オペレーター履歴の格納を行います
     *
     * @param operatorId オペレーターID
     * @param changeDateTime 変更日時
     * @param changeCause 変更事由
     */
    void store(Long operatorId, LocalDateTime changeDateTime, String changeCause);

}