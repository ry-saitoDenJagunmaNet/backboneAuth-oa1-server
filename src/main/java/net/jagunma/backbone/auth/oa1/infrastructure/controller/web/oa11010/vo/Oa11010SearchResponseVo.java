package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.vo;

import java.io.Serializable;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.springframework.stereotype.Controller;

/**
 * OA11010検索レスポンス
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11010
 * 機能名：オペレーター＜一覧＞
 * サービスID：OA11010
 * サービス名：OA11010サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11010", name = "オペレーター＜一覧＞")
@ServiceInfo(id = "OA11010", name = "OA11010サービス")
@Controller
public class Oa11010SearchResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * オペレータTable
	 */
	private String operatorTable;
	/**
	 * オペレータ一覧pagination
	 */
	private String pagination;

	public String getOperatorTable() { return operatorTable; }
	public void setOperatorTable(String operatorTable) { this.operatorTable = operatorTable; }
	public String getPagination() { return pagination; }
	public void setPagination(String pagination) { this.pagination = pagination; }
}
