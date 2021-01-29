package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

/**
 * サインイン証跡格納
 */
public interface SignInTraceRepositoryForStore {

    /**
     * サインイン証跡の追加を行います
     *
     * @param signInTrace サインイン証跡
     * @return サインイン証跡
     */
    SignInTrace insert(SignInTrace signInTrace);
}
