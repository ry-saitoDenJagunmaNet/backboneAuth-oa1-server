package net.jagunma.backbone.auth.authmanager.model.domain.accountLock;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * アカウントロックの検索条件
 */
public class AccountLockCriteria {

//    private LongCriteria accountLockIdCriteria = new LongCriteria();
    private LongCriteria operatorIdCriteria = new LongCriteria();
//    private LocalDateTimeCriteria occurredDateTimeCriteria = new LocalDateTimeCriteria();
//    private ShortCriteria lockStatusCriteria = new ShortCriteria();

    // Getter
//    public LongCriteria getAccountLockIdCriteria() {
//        return accountLockIdCriteria;
//    }
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
//    public LocalDateTimeCriteria getOccurredDateTimeCriteria() {
//        return occurredDateTimeCriteria;
//    }
//    public ShortCriteria getLockStatusCriteria() {
//        return lockStatusCriteria;
//    }
}
