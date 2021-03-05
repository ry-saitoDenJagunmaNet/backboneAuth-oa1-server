package net.jagunma.backbone.auth.authmanager.infra.web.base.vo;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.infra.web.common.MenuItemVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.MenuVo;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.message.MessageFormatter;
import net.jagunma.common.values.model.operator.SimpleOperator;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * レスポンスの基底クラス
 */
public class BaseOfVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * メッセージコード
     */
    private String messageCode;
    /**
     * メッセージコード
     */
    private List<String> messageArgs;
    /**
     * メッセージ
     */
    private String message;
    /**
     * エラーメッセージ
     */
    private String errorMessage;
    /**
     * エラー詳細メッセージ
     */
    private String errorDetailsMessage;
    /**
     * スタックトレース
     */
    private String stackTrace;

    public String getMessageCode() { return messageCode; }
    public void setMessageCode(String messageCode) { this.messageCode = messageCode; }
    public List<String> getMessageArgs() { return messageArgs; }
    public void setMessageArgs(List<String> messageArgs) { this.messageArgs = messageArgs; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public String getErrorDetailsMessage() { return errorDetailsMessage; }
    public void setErrorDetailsMessage(String errorDetailsMessage) { this.errorDetailsMessage = errorDetailsMessage; }
    public String getStackTrace() { return stackTrace; }
    public void setStackTrace(String stackTrace) { this.stackTrace = stackTrace; }
    public List<MenuVo> getMenuList() {
        return createMenu();
    }

    /**
     * 例外メッセージをセットします
     * @param gre GunmaRuntimeException
     */
    public void setExceptionMessage(GunmaRuntimeException gre) {
        messageCode = gre.getMessageCode();
        messageArgs = Arrays.stream(gre.getArgs()).map(o -> (String) o).collect(Collectors.toList());
        message = gre.getMessage();
    }

    /**
     * 楽観的ロックのメッセージをセットします
     * @param ole OptimisticLockingFailureException
     */
    public void setExceptionMessage(OptimisticLockingFailureException ole) {
        messageCode = "EOA10002";
        errorMessage = MessageFormatter.getSimpleMessage("EOA10002");

        if (ole.getMessage() != null) {errorDetailsMessage = ole.getMessage() + "\r\n";}

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ole.printStackTrace(pw);
        pw.flush();
        stackTrace = sw.toString();
    }

    /**
     * 例外メッセージをセットします
     * @param re IOException
     */
    public void setExceptionMessage(IOException re) {
        messageCode = "EOA10001";
        errorMessage = MessageFormatter.getSimpleMessage("EOA10001");

        if (re.getMessage() != null) {errorDetailsMessage = re.getMessage() + "\r\n";}

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        re.printStackTrace(pw);
        pw.flush();
        stackTrace = sw.toString();
    }

    /**
     * 例外メッセージをセットします
     * @param re RuntimeException
     */
    public void setExceptionMessage(RuntimeException re) {
        messageCode = "EOA10001";
        errorMessage = MessageFormatter.getSimpleMessage("EOA10001");

        if (re.getMessage() != null) {errorDetailsMessage = re.getMessage() + "\r\n";}

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        re.printStackTrace(pw);
        pw.flush();
        stackTrace = sw.toString();
    }

    /**
     * メニューリストを作成します
     *
     * @return メニューリスト
     */
    private List<MenuVo> createMenu() {

        SimpleOperator simpleOperator = AuditInfoHolder.getOperator();
        if (simpleOperator == null) { return newArrayList();}

        List<MenuVo> list = newArrayList();
        MenuVo menuVo = new MenuVo();
        menuVo.setName("オペレーター");
        List<MenuItemVo> menuItemVoList = newArrayList();
        menuItemVoList.add(MenuItemVo.createFrom("oa11020/get", "oa11020.png", "新規登録", "… 新規登録を行います", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11010/get", "oa11010.png", "オペレーター＜一覧＞", "… 下記の各取引を行う際の入口です", newArrayList("・更新（利用可否設定含む）", "・サブシステムロール付与", "・取引ロール付与", "・パスワードリセット")));
        menuItemVoList.add(MenuItemVo.createFrom("oa11100/get", "oa11100.png", "オペレーター履歴確認", "… 変更履歴の確認用に情報をExcelファイルに出力します", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11090/get", "oa11090.png", "アカウントロック照会", "… アカウントのロック状態を確認、またアンロックを行います", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11110/get", "oa11110.png", "パスワード変更履歴照会", "… パスワード変更履歴を照会します", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11120/get", "oa11120.png", "サインイン証跡照会", "… サインイン及び、サインアウトの証跡を照会します", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11130/get", "oa11130.png", "サービス呼出証跡照会", "… サーバーサービスの呼出証跡を照会します", newArrayList()));
        menuVo.setMenuItemList(menuItemVoList);
        list.add(menuVo);

        menuVo = new MenuVo();
        menuVo.setName("ロール");
        menuItemVoList = newArrayList();
        menuItemVoList.add(MenuItemVo.createFrom("oa11140/get", "oa11140.png", "ロール確認", "… 各ロール設定の確認用に情報をExcelファイルに出力します", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11070/get", "oa11070.png", "サブシステムロールオペレーター割当", "…サブシステムロールに対してオペレーターを割り当てます", newArrayList()));
        menuItemVoList.add(MenuItemVo.createFrom("oa11080/get", "oa11080.png", "取引ロールオペレーター割当", "…取引ロールに対してオペレーターを割り当てます", newArrayList()));
        menuVo.setMenuItemList(menuItemVoList);
        list.add(menuVo);

        menuVo = new MenuVo();
        menuVo.setName("パスワード変更");
        menuItemVoList = newArrayList();
        menuItemVoList.add(MenuItemVo.createFrom("ed01010/get?mode=Change&operatorId=" + simpleOperator.getIdentifier(), "ed01010.png", "パスワード変更", "… 現在サインインしている自分のパスワードを変更します", newArrayList()));
        menuVo.setMenuItemList(menuItemVoList);
        list.add(menuVo);

        // ToDo
        //if (simpleOperator.getBranch().getJaAtMoment().isセンターJa()) {
        if (simpleOperator.getBranch().getJaAtMoment().getJaAttribute().getJaCode().getValue().equals("990")) {
            menuVo = new MenuVo();
            menuVo.setName("電算センターオペレーター専用");
            menuItemVoList = newArrayList();
            menuItemVoList.add(MenuItemVo.createFrom("oa12010/get", "oa12010.png", "取引ロール編成インポート＆エクスポート", "… 取引ロールと取引グループの編成、", newArrayList("取引グループと取引の編成 を一括で登録または出力します")));
            menuItemVoList.add(MenuItemVo.createFrom("oa12020/get", "oa12020.png", "一時取引抑止<一覧>", "… 下記の各取引を行う際の入口です", newArrayList("・一時取引抑止登録", "・一時取引抑止更新")));
            menuItemVoList.add(MenuItemVo.createFrom("oa12040/get", "oa12040.png", "システム利用可能時間帯メンテナンス", "… 各サブシステムの曜日毎の利用可能時間帯を設定します", newArrayList()));
            menuItemVoList.add(MenuItemVo.createFrom("oa12060/get", "oa12060.png", "カレンダーメンテナンス", "… 各サブシステムで使用する稼働日カレンダーを設定します", newArrayList()));
            menuItemVoList.add(MenuItemVo.createFrom("oa12070/get", "oa12070.png", "ＪＡ割当IPアドレス範囲メンテナンス", "… 各ＪＡに割り当てているIPアドレスを設定します", newArrayList()));
            menuVo.setMenuItemList(menuItemVoList);
            list.add(menuVo);
        }

        menuVo = new MenuVo();
        menuVo.setName("サインアウト");
        menuItemVoList = newArrayList();
        menuItemVoList.add(MenuItemVo.createFrom("oa10000/signOut", "ed00010.png", "サインアウト", "… サインアウトし基幹系認証管理システムを終了します", newArrayList()));
        menuVo.setMenuItemList(menuItemVoList);
        list.add(menuVo);

        return list;
    }
}
