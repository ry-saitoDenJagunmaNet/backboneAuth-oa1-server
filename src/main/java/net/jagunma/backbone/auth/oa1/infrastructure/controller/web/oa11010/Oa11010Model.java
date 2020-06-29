package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010;

import java.io.Serializable;
import java.time.LocalDate;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.springframework.stereotype.Controller;

/**
 * OA11010リクエスト
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
public class Oa11010Model implements Serializable {
	private static final long serialVersionUID = 1L;

	/* ＪＡ */
	private String ja;
	/* ＪＡID */
	private long jaId;
	/* 店舗 */
	private String tempoCode;
	/* オペレーターコード */
	private String operatorCode;
	/* オペレーター名 */
	private String operatorName;
	/* メールアドレス */
	private String mailAddress;
	/* 利用可否状態 利用可能 */
	private Integer availableStatus0;
	/* 利用可否状態 利用不可 */
	private Integer availableStatus1;

	/* 状態指定日 状態指定日 */
	private LocalDate expirationStatusDate;
	/* 条件指定 有効期限開始（開始日） */
	private LocalDate expirationStartDateFrom;
	/* 条件指定 有効期限開始（終了日） */
	private LocalDate expirationStartDateTo;
	/* 条件指定 有効期限終了（開始日） */
	private LocalDate expirationEndDateFrom;
	/* 条件指定 有効期限終了（終了日） */
	private LocalDate expirationEndDateTo;

	public String getJa() { return ja; }
	public void setJa(String ja) { this.ja = ja; }
	public long getJaId() { return jaId; }
	public void setJaId(long jaId) { this.jaId = jaId; }
	public String getTempoCode() { return tempoCode; }
	public void setTempoCode(String tempoCode) { this.tempoCode = tempoCode; }
	public String getOperatorCode() { return operatorCode; }
	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
	public String getOperatorName() { return operatorName; }
	public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
	public String getMailAddress() { return mailAddress; }
	public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }
	public Integer getAvailableStatus0() { return availableStatus0; }
	public void setAvailableStatus0(Integer availableStatus0) { this.availableStatus0 = availableStatus0; }
	public Integer getAvailableStatus1() { return availableStatus1; }
	public void setAvailableStatus1(Integer availableStatus1) { this.availableStatus1 = availableStatus1; }
	public LocalDate getExpirationStatusDate() { return expirationStatusDate; }
	public void setExpirationStatusDate(LocalDate expirationStatusDate) { this.expirationStatusDate = expirationStatusDate; }
	private LocalDate getExpirationStartDateFrom() { return expirationStartDateFrom; }
	private void setExpirationStartDateFrom(LocalDate expirationStartDateFrom) { this.expirationStartDateFrom = expirationStartDateFrom; }
	private LocalDate getExpirationStartDateTo() { return expirationStartDateTo; }
	private void setExpirationStartDateTo(LocalDate expirationStartDateTo) { this.expirationStartDateTo = expirationStartDateTo; }
	private LocalDate getExpirationEndDateFrom() { return expirationEndDateFrom; }
	private void setExpirationEndDateFrom(LocalDate expirationEndDateFrom) { this.expirationEndDateFrom = expirationEndDateFrom; }
	private LocalDate getExpirationEndDateTo() { return expirationEndDateTo; }
	private void setExpirationEndDateTo(LocalDate expirationEndDateTo) { this.expirationEndDateTo = expirationEndDateTo; }
}
