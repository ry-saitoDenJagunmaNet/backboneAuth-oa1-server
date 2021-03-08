package net.jagunma.backbone.auth.authmanager.infra.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.jagunma.backbone.auth.authmanager.application.util.RestTemplateUtil;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.util.dateProvider.DateProvider;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;
import net.jagunma.common.values.model.operator.SimpleOperator.SimpleOperatorBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controllerの基底クラス
 */
public class BaseOfController {

    // TODO: ProFile取得api URI（本当はao2へ接続）（oa2への接続方法確認）
    private final String GET_SIMPLEOPERATOR_URI_PATH = "/oa31010/getSimpleOperator";

    // TODO: access tokenをsessionで管理する場合（検討中）
    // access token の session key
    protected final String SESSION_KEY_ACCES_TOKEN = "session_key_access_token";

    /**
     * RestTemplateUtil
     */
    @Autowired
    private RestTemplateUtil restTemplateUtil;

    /**
     * HttpServletRequest
     */
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * HttpServletRequestのＳｅｔ
     *
     * @param httpServletRequest HttpServletRequest
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * AuditInfoHolder のＳｅｔ
     *
     * @param simpleOperator オペレーター(簡略版)
     */
    protected void setAuditInfoHolder(SimpleOperator simpleOperator) {

        // ToDo: 各コントローラが動作する前にAuditInfoHolderが設定されるはず・・・・
        AuditInfoHolder.set(AuthInf.createFrom(
            simpleOperator.getBranch().getJaAtMoment().getJaAttribute().getJaCode().getValue(),
            simpleOperator.getBranch().getBranchAttribute().getBranchCode().getValue(),
            simpleOperator.getIdentifier().longValue(),
            simpleOperator.getOperatorName(),
            httpServletRequest.getRemoteAddr()),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            simpleOperator.getBranch().getJaAtMoment(),
            simpleOperator.getBranch(),
            simpleOperator);
    }

    /**
     *  Http Session
     */
    @Autowired
    protected HttpSession httpSession;

    /**
     * HttpSessionのＳｅｔ
     *
     * @param httpSession HttpSession
     */
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    /**
     * Http Sessionにデータを格納します
     *
     * @param id    Session Key
     * @param value Sessionに格納するデータ
     */
    public void setSessionAttribute(String id, Object value) {
        httpSession.setAttribute(id, value);
    }

    /**
     * Http Sessionからデータを取り出します
     *
     * @param id Session Key
     * @return Sessionから取り出したデータ
     */
    public Object getSessionAttribute(String id) {
        return getSessionAttribute(id, true);
    }

    /**
     * Http Sessionからデータを取り出します
     *
     * @param id       Session Key
     * @param isRemove データ取り出し後Session項目の破棄（true：破棄）
     * @return Sessionから取り出したデータ
     */
    public Object getSessionAttribute(String id, boolean isRemove) {
        Object value = httpSession.getAttribute(id);
        if (isRemove) { removeSessionAttribute(id); }
        return value;
    }

    /**
     * Http Session Session項目を破棄します
     *
     * @param id Session Key
     */
    public void removeSessionAttribute(String id) {
        httpSession.removeAttribute(id);
    }


    // ToDo:認証情報（ＰＧ用暫定）
    private Long defaultJaId = 6L;
    private String defaultJaCode = "006";
    private String defaultJaName = "JA前橋市";

    private Long defaultBranchId = 1L;
    private String defaultBranchCode = "001";
    private String defaultBranchName = "本店";

    private Long defaultOperatorId = 18L;
    private String defaultOperatorCode = "yu001009";
    private String defaultOperatorName = "ｙｕ００１００９";
    private String defaultOperatorIp = "001.001.001.001";

    /**
     * 認証情報を設定します（ＰＧ用暫定）
     */
    public void setAuthInf() {
        setAuthInf(null, null, null, null, null);
    }
    public void setAuthInf(
        Long operatorId,
        String operatorCode,
        String operatorName,
        JaAtMoment jaAtMoment,
        BranchAtMoment branchAtMoment) {

        //if (AuditInfoHolder.getAuthInf() != null) { return; }
        // ToDo: access tokenをsessionで管理する場合（検討中）
        Object obj = null;
        if (httpSession != null) {
            // この判定はjunitテストを通すための判定（テストでhttpSessionがないので異常終了する）
            obj = getSessionAttribute(SESSION_KEY_ACCES_TOKEN, false);
        }
        if (obj == null) {
            // ToDo: access tokenが無い場合は、Errorにする
            // ToDo: まだ、認証しないで動作させたいため、疑似的にAuditInfoHolderを設定する
            //throw new GunmaRuntimeException("EOAXXXXX");
        } else {
            String accesstoken = obj.toString();
            // ToDo: 本当はoa2の「Oa2refreshTken取得」を行う
            SimpleOperator simpleOperator = (SimpleOperator)restTemplateUtil.postBackboneAuthOa3ForJsonString(GET_SIMPLEOPERATOR_URI_PATH, accesstoken, SimpleOperator.class);
            setAuditInfoHolder(simpleOperator);
            return;
        }

        JaAtMoment ja = createJaAtMoment();
        BranchAtMoment branch = createBranchAtMoment();

        if (operatorId != null) {
            defaultOperatorId = operatorId;
        }
        if (operatorCode != null) {
            defaultOperatorCode = operatorCode;
        }
        if (operatorName != null) {
            defaultOperatorName = operatorName;
        }
        if (jaAtMoment != null) {
            ja = jaAtMoment;
        }
        if (branchAtMoment != null) {
            branch = branchAtMoment;
        }

        SimpleOperator operator = new SimpleOperatorBuilder()
            .withIdentifier(defaultOperatorId)
            .withOperatorCode(OperatorCode.of(defaultOperatorCode))
            .withOperatorName(defaultOperatorName)
            .withBranchIdentifier(defaultBranchId)
            .withBranch(branch)
            .build();

        AuditInfoHolder.set(AuthInf.createFrom(ja.getJaAttribute().getJaCode().getValue(),
            branch.getBranchAttribute().getBranchCode().getValue(),
            defaultOperatorId, defaultOperatorName, defaultOperatorIp),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            ja,
            branch, operator);
    }

    public JaAtMoment createJaAtMoment() {
        return createJaAtMoment(null, null, null);
    }

    public JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {

        if (jaId != null) {
            defaultJaId = jaId;
        }
        if (jaCode != null) {
            defaultJaCode = jaCode;
        }
        if (jaName != null) {
            defaultJaName = jaName;
        }

        return JaAtMoment.builder()
            .withIdentifier(defaultJaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(defaultJaCode))
                .withName(defaultJaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    public BranchAtMoment createBranchAtMoment() {
        return createBranchAtMoment(null, null, null, null);
    }

    public BranchAtMoment createBranchAtMoment(Long branchId, String branchCode,
        String branchName, JaAtMoment jaAtMoment) {
        JaAtMoment ajAtMoment = createJaAtMoment(null, null, null);
        if (branchId != null) {
            defaultBranchId = branchId;
        }
        if (branchCode != null) {
            defaultBranchCode = branchCode;
        }
        if (branchName != null) {
            defaultBranchName = branchName;
        }
        if (jaAtMoment != null) {
            ajAtMoment = jaAtMoment;
        }

        return BranchAtMoment.builder()
            .withIdentifier(defaultBranchId)
            .withJaAtMoment(ajAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(defaultBranchCode))
                .withName(defaultBranchName)
                .build())
            .build();
    }
}
