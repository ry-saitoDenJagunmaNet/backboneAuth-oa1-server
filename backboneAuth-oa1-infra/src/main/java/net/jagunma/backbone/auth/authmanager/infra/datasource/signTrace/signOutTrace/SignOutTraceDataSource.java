package net.jagunma.backbone.auth.authmanager.infra.datasource.signTrace.signOutTrace;

import net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signOutTrace.SignOutTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signOutTrace.SignOutTraceRepository;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityDao;
import org.springframework.stereotype.Component;

/**
 * サインアウト証跡検索 DataSource
 */
@Component
public class SignOutTraceDataSource implements SignOutTraceRepository {

    private final SignOutTraceEntityDao signOutTraceEntityDao;

    // コンストラクタ
    public SignOutTraceDataSource(SignOutTraceEntityDao signOutTraceEntityDao) {
        this.signOutTraceEntityDao = signOutTraceEntityDao;
    }

    /**
     * サインアウト証跡の条件検索を行います。
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @return サインアウト証跡
     */
    @Override
    public SignOutTrace findOneBy(SignOutTraceCriteria signOutTraceCriteria) {
        SignOutTraceEntity signOutTraceEntity = signOutTraceEntityDao
            .findOneBy(signOutTraceCriteria);
        return SignOutTrace.of(signOutTraceEntity);
    }
}
