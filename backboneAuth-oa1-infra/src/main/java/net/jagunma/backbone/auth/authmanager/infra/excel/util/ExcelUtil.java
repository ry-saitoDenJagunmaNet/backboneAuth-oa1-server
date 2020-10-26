package net.jagunma.backbone.auth.authmanager.infra.excel.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.time.ZoneId;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    /**
     * ストリームからブックを取得する
     *
     * @param in Excelのファイルストリーム
     * @return Workbook
     */
    public static Workbook createWorkbook(InputStream in) {
        try {
            Workbook wb = WorkbookFactory.create(in);

            return wb;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * シートからセルを取得する
     *
     * @param sheet Excelのシート
     * @param rowNo 行番号
     * @param colNo 列番号
     * @return Cell
     */
    public static Cell getCell(Sheet sheet, int rowNo, int colNo) {
        return getCell(sheet.getRow(rowNo), colNo);
    }

    /**
     * 行からセルを取得する
     *
     * @param row Excelの行
     * @param colNo 列番号
     * @return Cell
     */
    public static Cell getCell(Row row, int colNo) {
        if (row == null) {
            return null;
        }
        return row.getCell(colNo);
    }

    /**
     * シートからセルの値をIntegerで取得する
     *
     * @param sheet Excelのシート
     * @param rowNo 行番号
     * @param colNo 列番号
     * @return 値
     */
    public static Integer getInteger(Sheet sheet, int rowNo, int colNo) {
        return getInteger(getCell(sheet, rowNo, colNo));
    }

    /**
     * シートからセルの値をLocalDateで取得する
     *
     * @param sheet Excelのシート
     * @param rowNo 行番号
     * @param colNo 列番号
     * @return 値
     */
    public static LocalDate getLocalDate(Sheet sheet, int rowNo, int colNo) {
        return getLocalDate(getCell(sheet, rowNo, colNo));
    }

    /**
     * シートからセルの値を文字列で取得する
     *
     * @param sheet Excelのシート
     * @param rowNo 行番号
     * @param colNo 列番号
     * @return 値の文字列
     */
    public static String getString(Sheet sheet, int rowNo, int colNo) {
        return getString(getCell(sheet, rowNo, colNo));
    }

    /**
     * セルから値をIntegerで取得する
     *
     * @param cell Excelのセル
     * @return 値
     */
    public static Integer getInteger(Cell cell) {
        if (cell == null) {
            return null;
        }
        return (int) cell.getNumericCellValue();
    }

    /**
     * セルから値をLocalDateで取得する
     *
     * @param cell Excelのセル
     * @return 値
     */
    public static LocalDate getLocalDate(Cell cell) {
        if (cell == null) {
            return null;
        }
        return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * セルから値を文字列で取得する
     *
     * @param cell Excelのセル
     * @return 値の文字列
     */
    public static String getString(Cell cell) {
        try {
            if (cell == null) {
                return null;
            }

            return trim(cell.getRichStringCellValue().getString());
        } catch (IllegalStateException e) {
//            System.out.println(">ERR:" + cell.getSheet().getSheetName() + ":" + cell.getRowIndex() + "行目の値が不正です。");//デバッグで継続したい場合はこれを使用
//            return "";
            throw new IllegalStateException(
                cell.getSheet().getSheetName() + ":" + cell.getRowIndex() + "行目の値が不正です。", e);
        }
    }

    /**
     * 文字列をトリムする
     *
     * @param s 文字列
     * @return トリムされた文字列
     */
    private static String trim(String s) {
        return (s == null) ? null : s.trim();
    }
}
