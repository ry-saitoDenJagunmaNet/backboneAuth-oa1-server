package net.jagunma.backbone.auth.authmanager.infra.datasource.signInTrace;

import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepositoryForStore;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import org.springframework.stereotype.Component;

/**
 * サインイン証跡格納
 */
@Component
public class SignInTraceForStoreDataSource implements SignInTraceRepositoryForStore {

    private final SignInTraceEntityDao signInTraceEntityDao;
    private final SignInTraceRepository signInTraceRepository;

    // コンストラクタ
    SignInTraceForStoreDataSource(SignInTraceEntityDao signInTraceEntityDao,
        SignInTraceRepository signInTraceRepository) {

        this.signInTraceEntityDao = signInTraceEntityDao;
        this.signInTraceRepository = signInTraceRepository;
    }

    /**
     * サインイン証跡の追加を行います
     *
     * @param signInTrace サインイン証跡
     * @return サインイン証跡
     */
    public SignInTrace insert(SignInTrace signInTrace) {

        SignInTraceEntity entity = new SignInTraceEntity();
        entity.setTryDateTime(signInTrace.getTryDateTime());
        entity.setTryIpAddress(signInTrace.getTryIpAddress());
        entity.setOperatorCode(signInTrace.getOperatorCode());
        entity.setSignInCause(signInTrace.getSignInCause());
        entity.setSignInResult(signInTrace.getSignInResult());
        signInTraceEntityDao.insert(entity);

        return signInTraceRepository.findOneById(entity.getSignInTraceId());
    }
}
