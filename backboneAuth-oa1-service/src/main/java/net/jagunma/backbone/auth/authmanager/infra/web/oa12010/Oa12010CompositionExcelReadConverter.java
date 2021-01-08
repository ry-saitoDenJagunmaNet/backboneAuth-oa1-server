package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import java.io.ByteArrayInputStream;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;

/**
 * OA12010 Excel Read Converter
 */
public class Oa12010CompositionExcelReadConverter implements
    BizTranRoleCompositionExcelReadRequest {

    /**
     * OA12010 ViewObject
     */
    private final Oa12010Vo vo;
    /**
     * Excel input Stream
     */
    private ByteArrayInputStream byteArrayInputStream;

    // コンストラクタ
    Oa12010CompositionExcelReadConverter(Oa12010Vo vo, ByteArrayInputStream byteArrayInputStream) {
        this.vo = vo;
        this.byteArrayInputStream = byteArrayInputStream;
    }

    // ファクトリーメソッド
    public static Oa12010CompositionExcelReadConverter with(Oa12010Vo vo, ByteArrayInputStream byteArrayInputStream) {
        return new Oa12010CompositionExcelReadConverter(vo, byteArrayInputStream);
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
