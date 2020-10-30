package net.jagunma.backbone.auth.authmanager.infra.datasource.passwordHistory;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * パスワード格納
 */
@Component
public class PasswordHistoryForStoreDataSource implements PasswordHistoryRepositoryForStore {

    private final PasswordHistoryEntityDao passwordHistoryEntityDao;

    // コンストラクタ
    public PasswordHistoryForStoreDataSource(PasswordHistoryEntityDao passwordHistoryEntityDao) {

        this.passwordHistoryEntityDao = passwordHistoryEntityDao;
    }

    /**
     * パスワードの格納を行います
     *
     * @param passwordHistory パスワード履歴
     */
    public void store(PasswordHistory passwordHistory) {

        // パスワード履歴のインサートを行います
        insertPasswordHistory(passwordHistory.getOperatorId(), passwordHistory.getPassword(), passwordHistory.getChangeDateTime(), passwordHistory.getPasswordChangeType());
    }

    /**
     * パスワード履歴のインサートを行います
     *
     * @param operatorId オペレーターID
     * @param password パスワード
     * @param changeDateTime 変更日時
     * @param passwordChangeType 変更種別
     * @return パスワード履歴エンティティ
     */
    PasswordHistoryEntity insertPasswordHistory(Long operatorId, String password, LocalDateTime changeDateTime, PasswordChangeType passwordChangeType) {
        PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();

        passwordHistoryEntity.setOperatorId(operatorId);
        passwordHistoryEntity.setChangeDateTime(changeDateTime);
        passwordHistoryEntity.setPassword(password);
        passwordHistoryEntity.setChangeType(passwordChangeType.getCode());

        passwordHistoryEntityDao.insert(passwordHistoryEntity);

        return passwordHistoryEntity;
    }

//    /**
//     * パスワードの格納を行います
//     * （初期パスワードの入力）
//     *
//     * @param operatorId オペレーターID
//     * @param password パスワード
//     * @param changeDateTime 変更日時
//     */
//    public void store(Long operatorId, String password, LocalDateTime changeDateTime) {
//        store(operatorId, password, changeDateTime, PasswordChangeType.初期);
//    }
//    /**
//     * パスワードの格納を行います
//     *
//     * @param operatorId オペレーターID
//     * @param password パスワード
//     * @param changeDateTime 変更日時
//     * @param passwordChangeType 変更種別
//     */
//    public void store(Long operatorId, String password, LocalDateTime changeDateTime, PasswordChangeType passwordChangeType) {
//        // パスワード履歴のインサートを行います
//        insertPasswordHistory(operatorId, password, changeDateTime, passwordChangeType);
//    }

}
