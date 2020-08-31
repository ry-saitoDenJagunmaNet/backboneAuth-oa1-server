package net.jagunma.backbone.auth.authmanager.infra.datasource.signTrace.signInTrace;

import net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import org.springframework.stereotype.Component;

/**
 * サインイン証跡検索 DataSource
 */
@Component
public class SignInTraceDataSource implements SignInTraceRepository {

    private final SignInTraceEntityDao signInTraceEntityDao;

    // コンストラクタ
    public SignInTraceDataSource(SignInTraceEntityDao signInTraceEntityDao) {
        this.signInTraceEntityDao = signInTraceEntityDao;
    }

    /**
     * サインイン証跡の条件検索を行います。
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @return サインイン証跡
     */
    @Override
    public SignInTrace findOneBy(SignInTraceCriteria signInTraceCriteria) {
        SignInTraceEntity signInTraceEntity = signInTraceEntityDao.findOneBy(signInTraceCriteria);
        return SignInTrace.of(signInTraceEntity);
    }
}
