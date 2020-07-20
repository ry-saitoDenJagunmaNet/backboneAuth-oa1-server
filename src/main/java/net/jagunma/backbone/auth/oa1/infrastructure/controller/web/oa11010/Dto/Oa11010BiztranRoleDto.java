package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.Dto;

import java.io.Serializable;
import java.time.LocalDate;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.springframework.stereotype.Controller;

/**
 * OA11010取引ロール一覧行
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
public class Oa11010BiztranRoleDto {
	private static final long serialVersionUID = 1L;

	/**
	 * 取引ロール選択
	 */
	private Integer biztranRoleSelected;
	/**
	 * 取引ロールID
	 */
	private long biztranRoleId;
	/**
	 * 取引ロールコード
	 */
	private String biztranRoleCode;
	/**
	 * 取引ロール名
	 */
	private String biztranRoleName;
	/**
	 * サブシステムコード
	 */
	private String subSystemCode;
	/**
	 * 有効期限選択
	 */
	private Integer expirationSelect;
	/**
	 * 状態指定日
	 */
	private String expirationStatusDate;
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
	private LocalDate expirationEndDateTo;

	public Integer getBiztranRoleSelected() { return biztranRoleSelected; }
	public void setBiztranRoleSelected(Integer biztranRoleSelected) { this.biztranRoleSelected = biztranRoleSelected; }
	public long getBiztranRoleId() { return biztranRoleId; }
	public void setBiztranRoleId(long biztranRoleId) { this.biztranRoleId = biztranRoleId; }
	public String getBiztranRoleCode() { return biztranRoleCode; }
	public void setBiztranRoleCode(String biztranRoleCode) { this.biztranRoleCode = biztranRoleCode; }
	public String getBiztranRoleName() { return biztranRoleName; }
	public void setBiztranRoleName(String biztranRoleName) { this.biztranRoleName = biztranRoleName; }
	public String getSubSystemCode() { return subSystemCode; }
	public void setSubSystemCode(String subSystemCode) { this.subSystemCode = subSystemCode; }
	public Integer getExpirationSelect() { return expirationSelect; }
	public void setExpirationSelect(Integer expirationSelect) { this.expirationSelect = expirationSelect; }
	public String getExpirationStatusDate() { return expirationStatusDate; }
	public void setExpirationStatusDate(String expirationStatusDate) { this.expirationStatusDate = expirationStatusDate; }
	public LocalDate getExpirationStartDateFrom() { return expirationStartDateFrom; }
	public void setExpirationStartDateFrom(LocalDate expirationStartDateFrom) { this.expirationStartDateFrom = expirationStartDateFrom; }
	public LocalDate getExpirationStartDateTo() { return expirationStartDateTo; }
	public void setExpirationStartDateTo(LocalDate expirationStartDateTo) { this.expirationStartDateTo = expirationStartDateTo; }
	public LocalDate getExpirationEndDateFrom() { return expirationEndDateFrom; }
	public void setExpirationEndDateFrom(LocalDate expirationEndDateFrom) { this.expirationEndDateFrom = expirationEndDateFrom; }
	public LocalDate getExpirationEndDateTo() { return expirationEndDateTo; }
	public void setExpirationEndDateTo(LocalDate expirationEndDateTo) { this.expirationEndDateTo = expirationEndDateTo; }
}
