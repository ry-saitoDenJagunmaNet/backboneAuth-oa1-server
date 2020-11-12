package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;

/**
 * 取引ロール編成エクスポートExcel 登録サービス Validator
 */
public class StoreBizTranRoleCompositionValidator {

    private final BizTranRoleCompositionImportRequest request;

    // コンストラクタ
    StoreBizTranRoleCompositionValidator(BizTranRoleCompositionImportRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static StoreBizTranRoleCompositionValidator with(
        BizTranRoleCompositionImportRequest request) {
        return new StoreBizTranRoleCompositionValidator(request);
    }

    /**
     * リクエストのチェックを行います
     */
    public void validate() {
        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotEmpty(request.getSubSystemCode(), () -> new GunmaRuntimeException("EOA13002", "サブシステム"));

        // サブシステムチェック
        if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b->
            !b.getSubSystemName().equals(SubSystem.codeOf(request.getSubSystemCode()).getName())).count() > 0) {
            throw new GunmaRuntimeException("EOA13011", "取引ロール－取引グループ編成");
        }
        if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b->
            !b.getSubSystemName().equals(SubSystem.codeOf(request.getSubSystemCode()).getName())).count() > 0) {
            throw new GunmaRuntimeException("EOA13011", "取引グループ－取引編成");
        }
    }

    /**
     * インポートテータのチェックを行います
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
     * 取引ロール－取引グループ編成のチェックを行います
     *
     * @param list メッセージリスト
     */
    private void checkExcelImportBizTranRole_BizTranGrps(List<MessageDto> list) {
        // 取引ロール－取引グループ編成
        for (BizTranRole_BizTranGrpSheet bizTranRole_BizTranGrpsSheet : request.getBizTranRole_BizTranGrpsSheet().getValues()) {
            String message = "［取引ロール－取引グループ編成］（Excel行："+bizTranRole_BizTranGrpsSheet.getRowno()+"）";
            // 未セットチェック
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getSubSystemName())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"サブシステム")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranRoleCode())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引ロールコード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranRoleName())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引ロール名称")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引グループコード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranRole_BizTranGrpsSheet.getBizTranGrpName())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引グループ名称")));
                continue;
            }

            // 重複チェック
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b->
                b.getBizTranRoleCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranRoleCode()) &&
                    b.getBizTranGrpCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode()) &&
                    b.getRowno().compareTo(bizTranRole_BizTranGrpsSheet.getRowno()) > 0).count() > 0) {
                list.add(MessageDto.createFrom("EOA13009", newArrayList(message+"取引ロールコード＋取引グループコード")));
                continue;
            }

            // 同一キーレコード内容一致チェック（取引ロール）
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b ->
                b.getBizTranRoleCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranRoleCode()) &&
                    !b.getBizTranRoleName().equals(bizTranRole_BizTranGrpsSheet.getBizTranRoleName())  &&
                    b.getRowno().compareTo(bizTranRole_BizTranGrpsSheet.getRowno()) > 0).count() > 0) {
                list.add(MessageDto.createFrom("EOA13010", newArrayList(message+"取引ロール")));
                continue;
            }
            // 同一キーレコード内容一致チェック（取引グループ）
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b ->
                b.getBizTranGrpCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode()) &&
                    !b.getBizTranGrpName().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpName()) &&
                    b.getRowno().compareTo(bizTranRole_BizTranGrpsSheet.getRowno()) > 0).count() > 0) {
                list.add(MessageDto.createFrom("EOA13010", newArrayList(message+"取引グループ")));
            }

            // ［取引グループコード］双方向存在チェック
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b->
                b.getSubSystemName().equals(bizTranRole_BizTranGrpsSheet.getSubSystemName()) &&
                    b.getBizTranGrpCode().equals(bizTranRole_BizTranGrpsSheet.getBizTranGrpCode())).count() == 0) {
                list.add(MessageDto.createFrom("EOA13012",
                    newArrayList("［取引ロール－取引グループ編成］", "［取引グループ－取引編成］", bizTranRole_BizTranGrpsSheet.getBizTranGrpCode())));
            }
        }
    }

    /**
     * 取引グループ－取引編成のチェックを行います
     *
     * @param list メッセージリスト
     */
    private void checkExcelImportBizTranGrp_BizTrans(List<MessageDto> list) {
        // 取引グループ－取引編成
        for (BizTranGrp_BizTranSheet bizTranGrp_BizTransSheet : request.getBizTranGrp_BizTransSheet().getValues()) {
            String message = "［取引グループ－取引編成］（Excel行："+bizTranGrp_BizTransSheet.getRowno()+"）";
            // 未セットチェック
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getSubSystemName())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"サブシステム")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranGrpCode())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引グループコード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranGrpName())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引グループ名称")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranCode())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引コード")));
                continue;
            }
            if (Strings2.isEmpty(bizTranGrp_BizTransSheet.getBizTranName())) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"取引名称")));
                continue;
            }
            if (bizTranGrp_BizTransSheet.getIsCenterBizTran() == null) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"センター取引区分")));
                continue;
            }
            if (bizTranGrp_BizTransSheet.getExpirationStartDate() == null) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"有効期限From")));
                continue;
            }
            if (bizTranGrp_BizTransSheet.getExpirationEndDate() == null) {
                list.add(MessageDto.createFrom("EOA13013", newArrayList(message+"有効期限To")));
                continue;
            }

            // 重複チェック
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b->
                b.getBizTranGrpCode().equals(bizTranGrp_BizTransSheet.getBizTranGrpCode()) &&
                    b.getBizTranCode().equals(bizTranGrp_BizTransSheet.getBizTranCode()) &&
                    b.getRowno().compareTo(bizTranGrp_BizTransSheet.getRowno()) > 0).count() > 0) {
                list.add(MessageDto.createFrom("EOA13009", newArrayList(message+"取引グループコード＋取引コード")));
                continue;
            }

            // 同一キーレコード内容一致チェック（取引グループ）
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b ->
                b.getBizTranGrpCode().equals(bizTranGrp_BizTransSheet.getBizTranGrpCode()) &&
                    !b.getBizTranGrpName().equals(bizTranGrp_BizTransSheet.getBizTranGrpName()) &&
                    b.getRowno().compareTo(bizTranGrp_BizTransSheet.getRowno()) > 0).count() > 0) {
                list.add(MessageDto.createFrom("EOA13010", newArrayList(message+"取引グループ")));
                continue;
            }
            // 同一キーレコード内容一致チェック（取引）
            if (request.getBizTranGrp_BizTransSheet().getValues().stream().filter(b ->
                b.getBizTranCode().equals(bizTranGrp_BizTransSheet.getBizTranCode()) &&
                    (!b.getBizTranName().equals(bizTranGrp_BizTransSheet.getBizTranName()) ||
                        !b.getIsCenterBizTran().equals(bizTranGrp_BizTransSheet.getIsCenterBizTran()) ||
                        !b.getExpirationStartDate().equals(bizTranGrp_BizTransSheet.getExpirationStartDate()) ||
                        !b.getExpirationEndDate().equals(bizTranGrp_BizTransSheet.getExpirationEndDate())) &&
                    b.getRowno().compareTo(bizTranGrp_BizTransSheet.getRowno()) > 0).count() > 0) {
                list.add(MessageDto.createFrom("EOA13010", newArrayList(message+"取引")));
            }

            // ［取引グループコード］双方向存在チェック
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(b->
                b.getSubSystemName().equals(bizTranGrp_BizTransSheet.getSubSystemName()) &&
                    b.getBizTranGrpCode().equals(bizTranGrp_BizTransSheet.getBizTranGrpCode())).count() == 0) {
                list.add(MessageDto.createFrom("EOA13012",
                    newArrayList("［取引グループ－取引編成］", "［取引ロール－取引グループ編成］", bizTranGrp_BizTransSheet.getBizTranGrpCode())));
            }
        }
    }
}