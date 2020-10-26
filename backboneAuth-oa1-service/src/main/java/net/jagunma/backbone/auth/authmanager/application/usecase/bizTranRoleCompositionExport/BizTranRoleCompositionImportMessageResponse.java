package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport;

public interface BizTranRoleCompositionImportMessageResponse {

    void setMessageCode(String messageCode);
    void setMessage(String message);
    void setMessageArgs(String[] messageArgs);
}
