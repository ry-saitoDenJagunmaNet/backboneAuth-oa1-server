package net.jagunma.backbone.auth.authmanager.infra.excel.constant;

/**
 * 取引ロール編成定数
 */
public class BizTranRoleCompositionConstants {

    /**
     * 取引ロール編成 Templateファイル
     */
    public static final String TEMPLATE_EXCEL_FILE = "Template取引ロール編成.xlsx";

    /**
     * 取引ロール－取引グループ編成Sheetのindex
     */
    public static final int INDEX_OF_SHEET1 = 0;
    /**
     * 取引ロール－取引グループ編成Sheetの開始行
     */
    public static final int INDEX_OF_SHEET1_ROWSTART = 2;
    /**
     * 取引ロール－取引グループ編成Sheetのサブシステムカラム位置
     */
    public static final int INDEX_OF_SHEET1_SUBSYSTE_NAME = 0;
    /**
     * 取引ロール－取引グループ編成Sheetの取引ロールコードカラム位置
     */
    public static final int INDEX_OF_SHEET1_BIZTRAN_ROLE_CODE = 1;
    /**
     * 取引ロール－取引グループ編成Sheetの取引ロール名カラム位置
     */
    public static final int INDEX_OF_SHEET1_BIZTRAN_ROLE_NAME = 2;
    /**
     * 取引ロール－取引グループ編成Sheetの取引ロールグループコードカラム位置
     */
    public static final int INDEX_OF_SHEET1_BIZTRAN_GRP_CODE = 3;
    /**
     * 取引ロール－取引グループ編成Sheetの取引ロールグループ名カラム位置
     */
    public static final int INDEX_OF_SHEET1_BIZTRAN_GRP_NAME = 4;

    /**
     * 取引グループ－取引編成Sheetのindex
     */
    public static final int INDEX_OF_SHEET2 = 1;
    /**
     * 取引グループ－取引編成Sheetの開始行
     */
    public static final int INDEX_OF_SHEET2_ROWSTART = 2;
    /**
     * 取引グループ－取引編成Sheetのサブシステムカラム位置
     */
    public static final int INDEX_OF_SHEET2_SUBSYSTE_NAME = 0;
    /**
     * 取引グループ－取引編成Sheetの取引ロールグループコードカラム位置
     */
    public static final int INDEX_OF_SHEET2_BIZTRAN_GRP_CODE = 1;
    /**
     * 取引グループ－取引編成Sheetの取引ロールグループ名カラム位置
     */
    public static final int INDEX_OF_SHEET2_BIZTRAN_GRP_NAME = 2;
    /**
     * 取引グループ－取引編成Sheetの取引コードカラム位置
     */
    public static final int INDEX_OF_SHEET2_BIZTRAN_CODE = 3;
    /**
     * 取引グループ－取引編成Sheetの取引名カラム位置
     */
    public static final int INDEX_OF_SHEET2_BIZTRAN_NAME = 4;
    /**
     * 取引グループ－取引編成Sheetのセンター取引区分カラム位置
     */
    public static final int INDEX_OF_SHEET2_CENTER_BIZTRAN = 5;
    /**
     * 取引グループ－取引編成Sheetの有効期限開始カラム位置
     */
    public static final int INDEX_OF_SHEET2_EXPIRATION_STARTDATE = 6;
    /**
     * 取引グループ－取引編成Sheetの有効期限終了カラム位置
     */
    public static final int INDEX_OF_SHEET2_EXPIRATION_ENDDATE = 7;
}
