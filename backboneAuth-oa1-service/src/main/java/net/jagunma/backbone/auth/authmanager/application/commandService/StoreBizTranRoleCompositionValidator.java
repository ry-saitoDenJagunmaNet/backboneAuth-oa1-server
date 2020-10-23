package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport.BizTranRoleCompositionImportStoreRequest;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.message.MessageFormatter;
import net.jagunma.common.util.strings2.Strings2;

/**
 * 取引ロール編成エクスポートExcel 登録サービス Validator
 */
public class StoreBizTranRoleCompositionValidator {

    private BizTranRoleCompositionImportStoreRequest request;

    // コンストラクタ
    StoreBizTranRoleCompositionValidator(BizTranRoleCompositionImportStoreRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static StoreBizTranRoleCompositionValidator with(BizTranRoleCompositionImportStoreRequest request) {
        return new StoreBizTranRoleCompositionValidator(request);
    }

    /**
     * リクエストのチェックを行います。
     */
    public void validate() {
        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getSubSystemCode(), () -> new GunmaRuntimeException("EOA13002", "サブシステム"));
    }

    /**
     * インポートテータのチェックを行います。
     *
     * @return メッセージリスト
     */
    public List<MessageDto> checkExcelImport() {
        List<MessageDto> list = newArrayList();
        checkExcelImportBizTranRole_BizTranGrps(list);
        checkExcelImportBizTranGrp_BizTrans(list);
        return list;
    }

    /**
     * 取引ロール－取引グループ編成のチェックを行います。
     *
     * @param list メッセージリスト
     */
    private void checkExcelImportBizTranRole_BizTranGrps(List<MessageDto> list) {
        // 取引ロール－取引グループ編成
        for (BizTranRole_BizTranGrpSheet bizTranRole_BizTranGrpsSheet : request.getBizTranRole_BizTranGrpsSheet().getValues()) {
            String message = "［取引ロール－取引グループ編成］（"+bizTranRole_BizTranGrpsSheet.getRowno()+"行目）";
            // 未セットチェック
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranRoleCode())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引ロールコード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranRoleName())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引ロール名称")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引グループコード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranGrpName())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引グループ名称")));
                continue;
            }

            // 重複チェック
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b->
                b.getBizTranRoleCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranRoleCode()) &&
                    b.getBizTranGrpCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode())).count() > 1) {
                list.add(setMessage("EOA13009", newArrayList(message+"取引ロールコード＋取引グループコード")));
                continue;
            }

            // 同一キーレコード内容一致チェック（取引ロール）
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b->
                b.getBizTranRoleCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranRoleCode()) &&
                    !b.getBizTranGrpName().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpName())).count() > 0) {
                list.add(setMessage("EOA13010", newArrayList(message+"取引ロール")));
                continue;
            }
            // 同一キーレコード内容一致チェック（取引グループ）
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b->
                b.getBizTranGrpCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode()) &&
                    !b.getBizTranGrpName().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpName())).count() > 0) {
                list.add(setMessage("EOA13010", newArrayList(message+"取引グループ")));
                continue;
            }
        }
    }

    /**
     * 取引グループ－取引編成のチェックを行います。
     *
     * @param list メッセージリスト
     */
    private void checkExcelImportBizTranGrp_BizTrans(List<MessageDto> list) {
        // 取引グループ－取引編成
        for (BizTranGrp_BizTranSheet bizTranGrp_BizTransSheet : request.getBizTranGrp_BizTransSheet().getValues()) {
            String message = "［取引グループ－取引編成］（"+bizTranGrp_BizTransSheet.getRowno()+"行目）";
            // 未セットチェック
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranGrpCode())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引グループコード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranGrpName())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引グループ名称")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranCode())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引コード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranName())) {
                list.add(setMessage("EOA13002", newArrayList(message+"取引名称")));
                continue;
            }
            if (bizTranGrp_BizTransSheet.getIsCenterBizTran() == null) {
                list.add(setMessage("EOA13002", newArrayList(message+"センター取引区分")));
                continue;
            }
            if (bizTranGrp_BizTransSheet.getExpirationStartDate() == null) {
                list.add(setMessage("EOA13002", newArrayList(message+"有効期限From")));
                continue;
            }
            if (bizTranGrp_BizTransSheet.getExpirationEndDate() == null) {
                list.add(setMessage("EOA13002", newArrayList(message+"有効期限To")));
                continue;
            }

            // 重複チェック
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b->
                b.getBizTranGrpCode().equals(bizTranGrp_BizTransSheet.getBizTranGrpCode()) &&
                    b.getBizTranCode().equals(bizTranGrp_BizTransSheet.getBizTranCode())).count() > 1) {
                list.add(setMessage("EOA13009", newArrayList(message+"取引グループコード＋取引コード")));
                continue;
            }

            // 同一キーレコード内容一致チェック（取引グループ）
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b->
                b.getBizTranGrpCode().equals(bizTranGrp_BizTransSheet.getBizTranGrpCode()) &&
                    !b.getBizTranGrpName().equals(bizTranGrp_BizTransSheet.getBizTranGrpName())).count() > 0) {
                list.add(setMessage("EOA13010", newArrayList(message+"取引グループ")));
                continue;
            }
            // 同一キーレコード内容一致チェック（取引）
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b->
                b.getBizTranGrpCode().equals(bizTranGrp_BizTransSheet.getBizTranGrpCode()) &&
                    (!b.getBizTranGrpName().equals(bizTranGrp_BizTransSheet.getBizTranGrpName()) ||
                    !b.getIsCenterBizTran().equals(bizTranGrp_BizTransSheet.getIsCenterBizTran()) ||
                        !b.getExpirationStartDate().equals(bizTranGrp_BizTransSheet.getExpirationStartDate()) ||
                        !b.getExpirationEndDate().equals(bizTranGrp_BizTransSheet.getExpirationEndDate()))).count() > 0) {
                list.add(setMessage("EOA13010", newArrayList(message+"取引グループ")));
                continue;
            }
        }
    }

    /**
     * メッセージをセットします。
     *
     * @param messageCode メッセージコード
     * @param messageArgList ッセージコードリスト
     * @return
     */
    MessageDto setMessage(String messageCode, List<String> messageArgList) {
        return MessageDto.createFrom(
            messageCode,
            MessageFormatter.getSimpleMessage("EOA13002", messageArgList.toArray(new String[messageArgList.size()])),
            messageArgList
        );
    }
}
