package net.jagunma.backbone.auth.authmanager.model.base;

import net.jagunma.common.util.objects2.Objects2;

public class AbstractCriteria {

    /**
     * 受け継ぐCriteriaを文字列に変換します
     *
     * @return 受け継ぐCriteria文字列
     */
    public String toString() {
        return Objects2.toStringHelper(this).defaultConfig().toString();
    }
}
