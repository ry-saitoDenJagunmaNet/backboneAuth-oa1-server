package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;

/**
 * Oa13010 認証 Presenter
 */
public class Oa13010AuthenticationPresenter {

    private SignInResult signInResult;
    private Operator operator;

    /**
     * サインイン結果のＳｅｔ
     *
     * @param signInResult サインイン結果
     */
    public void setSignInResult(SignInResult signInResult) {
        this.signInResult = signInResult;
    }
    /**
     * サインイン結果のＧｅｔ
     *
     * @return サインイン結果
     */
    public SignInResult getSignInResult() {
        return signInResult;
    }
    /**
     * オペレーターのＳｅｔ
     *
     * @param operator オペレーター
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    /**
     * オペレーターのＧｅｔ
     *
     * @return オペレーター
     */
    public Operator getOperator() {
        return operator;
    }
}
