package net.jagunma.backbone.auth.authmanager.infra.util;

/**
 * チェックボックスコントロール値をアプリケーションロジックでboolean(true/false)で扱うためのスムーザー
 * （チェックボックスコントロールは、チェックOffの場合nullとなるため）
 */
public class CheckboxUtil {

    /**
     * Voのチェックボックスコントロール項目へ格納する値を変換します
     *
     * @param value 変換対象値
     * @return 変換値
     */
    public static Boolean setSmoother(Boolean value) {
        return (Boolean.TRUE.equals(value))? true : null;
    }

    /**
     * Voのチェックボックスコントロール項目から値を変換して取得します
     *
     * @param value 変換対象値
     * @return 変換値
     */
    public static boolean getSmoother(Boolean value) {
        return (Boolean.TRUE.equals(value))? true : false;
    }
}
