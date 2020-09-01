package net.jagunma.backbone.auth.authmanager.infra.web.base;

import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.DefaultAuditTempo;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.util.dateProvider.DateProvider;

/**
 * Controllerの基底クラス
 */
public class BaseOfController {

    /**
     * 認証情報を設定します。（ＰＧ用暫定）
     */
    public void setAuthInf() {
        System.out.println("### BaseOfController");
        //TODO: パラメータでサインインオペレーターの情報を取得する
        AuthInf authInf = new AuthInf().createFrom("006", "001", 18l, "yu001009", "145.254.211.73");
        AuditInfoHolder.set(authInf, DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""), DefaultAuditTempo.empty());
    }
}
