package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo;

import java.io.Serializable;
import java.time.LocalDate;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.springframework.stereotype.Controller;

/**
 * OA11010サブシステムロール一覧行
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
public class Oa11010SubsystemRoleVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * サブシステムロール選択
	 */
	private Integer subsystemRoleSelected;
	/**
	 * サブシステムロールID
	 */
	private long subsystemRoleId;
	/**
	 * サブシステムロールコード
	 */
	private String subsystemRoleCode;
	/**
	 * サブシステムロール名
	 */
	private String subsystemRoleName;
	/**
	 * 有効期限選択
	 */
	private Integer expirationSelect;
	/**
	 * 状態指定日
	 */
	private LocalDate expirationStatusDate;
	/**
	 * 条件指定日開始（開始日）
	 */
	private LocalDate expirationStartDateFrom;
	/**
	 * 条件指定日開始（終了日）
	 */
	private LocalDate expirationStartDateTo;
	/**
	 * 条件指定日終了（開始日）
	 */
	private LocalDate expirationEndDateFrom;
	/**
	 * 条件指定日終了（終了日）
	 */
	private String expirationEndDateTo;

	public Integer getSubsystemRoleSelected() { return subsystemRoleSelected; }
	public void setSubsystemRoleSelected(Integer subsystemRoleSelected) { this.subsystemRoleSelected = subsystemRoleSelected; }
	public long getSubsystemRoleId() { return subsystemRoleId; }
	public void setSubsystemRoleId(long subsystemRoleId) { this.subsystemRoleId = subsystemRoleId; }
	public String getSubsystemRoleCode() { return subsystemRoleCode; }
	public void setSubsystemRoleCode(String subsystemRoleCode) { this.subsystemRoleCode = subsystemRoleCode; }
	public String getSubsystemRoleName() { return subsystemRoleName; }
	public void setSubsystemRoleName(String subsystemRoleName) { this.subsystemRoleName = subsystemRoleName; }
	public Integer getExpirationSelect() { return expirationSelect; }
	public void setExpirationSelect(Integer expirationSelect) { this.expirationSelect = expirationSelect; }
	public LocalDate getExpirationStatusDate() { return expirationStatusDate; }
	public void setExpirationStatusDate(LocalDate expirationStatusDate) { this.expirationStatusDate = expirationStatusDate; }
	public LocalDate getExpirationStartDateFrom() { return expirationStartDateFrom; }
	public void setExpirationStartDateFrom(LocalDate expirationStartDateFrom) { this.expirationStartDateFrom = expirationStartDateFrom; }
	public LocalDate getExpirationStartDateTo() { return expirationStartDateTo; }
	public void setExpirationStartDateTo(LocalDate expirationStartDateTo) { this.expirationStartDateTo = expirationStartDateTo; }
	public LocalDate getExpirationEndDateFrom() { return expirationEndDateFrom; }
	public void setExpirationEndDateFrom(LocalDate expirationEndDateFrom) { this.expirationEndDateFrom = expirationEndDateFrom; }
	public String getExpirationEndDateTo() { return expirationEndDateTo; }
	public void setExpirationEndDateTo(String expirationEndDateTo) { this.expirationEndDateTo = expirationEndDateTo; }
}
