package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import java.io.ByteArrayInputStream;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionImportReadRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * OA12010 取引ロール編成インポート＆エクスポート Excel Readサービス Request Converter
 */
public class Oa12010CompositionImportReadConverter implements BizTranRoleCompositionImportReadRequest {

    /**
     * OA12010 ViewObject
     */
    private final Oa12010Vo vo;
    /**
     * Excel input Stream
     */
    private ByteArrayInputStream byteArrayInputStream;

    // コンストラクタ
    Oa12010CompositionImportReadConverter(Oa12010Vo vo, ByteArrayInputStream byteArrayInputStream) {
        this.vo = vo;
        this.byteArrayInputStream = byteArrayInputStream;
    }

    // ファクトリーメソッド
    public static Oa12010CompositionImportReadConverter with(Oa12010Vo vo, ByteArrayInputStream byteArrayInputStream) {
        return new Oa12010CompositionImportReadConverter(vo, byteArrayInputStream);
    }

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return vo.getSubSystemCode();
    }
    /**
     * 取引ロール編成 BookのＧｅｔ
     *
     * @return 取引ロール編成 Book
     */
    public ExcelContainer getBizTranRoleCompositionBook() {
        return ExcelContainer.createFrom(byteArrayInputStream);
    }

}
