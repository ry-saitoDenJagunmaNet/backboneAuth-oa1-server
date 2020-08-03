package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;

/**
 * OA11010 オペレーター＜一覧＞ 画面初期表示 Presenter
 */
class Oa11010InitPresenter implements Oa11010InitResponse {

	private long jaId;
	private String jaCode;
	private String jaName;
	private List<TempoReferenceDto> tempoReferenceDtoList;
	private Integer expirationSelect;
	private Integer subSystemRoleConditionsSelect;
	private List<Oa11010SubSystemRoleVo> subSystemRoleList;
	private Integer bizTranRoleConditionsSelect;
	private List<Oa11010BizTranRoleVo> bizTranRoleList;
	private List<SubSystemReferenceDto> bizTranRoleSubSystemList;

	Oa11010InitPresenter() {
	}

	/**
	 * ＪＡIDのＳｅｔ
	 * @param jaId
	 */
	public void setJaId(long jaId) {
		this.jaId = jaId;
	}
	/**
	 * ＪＡコードのＳｅｔ
	 * @param jaCode
	 */
	public void setJaCode(String jaCode) {
		this.jaCode = jaCode;
	}
	/**
	 * 店舗コンボボックスリストのＳｅｔ
	 * @param tempoReferenceDtoList
	 */
	public void setTempoReferenceDtoList(List<TempoReferenceDto> tempoReferenceDtoList) { this.tempoReferenceDtoList = tempoReferenceDtoList; }
	/**
	 * ＪＡ名のＳｅｔ
	 * @param jaName
	 */
	public void setJaName(String jaName) {
		this.jaName = jaName;
	}
	/**
	 * 有効期限選択のＳｅｔ
	 * @param expirationSelect
	 */
	public void setExpirationSelect(Integer expirationSelect) { this.expirationSelect = expirationSelect; }
	/**
	 * サブシステムロール条件選択のＳｅｔ
	 * @param subSystemRoleConditionsSelect
	 */
	public void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect) { this.subSystemRoleConditionsSelect = subSystemRoleConditionsSelect; }
	/**
	 * サブシステムロールリストのＳｅｔ
	 * @param subSystemRoleList
	 */
	public void setSubsystemRoleList(List<Oa11010SubSystemRoleVo> subSystemRoleList) { this.subSystemRoleList = subSystemRoleList; }
	/**
	 * 取引ロール条件選択のＳｅｔ
	 * @param bizTranRoleConditionsSelect
	 */
	public void setBiztranRoleConditionsSelect(Integer bizTranRoleConditionsSelect) { this.bizTranRoleConditionsSelect = bizTranRoleConditionsSelect; }
	/**
	 * 取引ロール一覧のＳｅｔ
	 * @param bizTranRoleList
	 */
	public void setBizTranRoleList(List<Oa11010BizTranRoleVo> bizTranRoleList) { this.bizTranRoleList = bizTranRoleList; }
	/**
	 * サブシステムロールサブシステムコンボボックスリストのＳｅｔ
	 * @param bizTranRoleSubSystemList
	 */
	public void setBizTranRoleSubSystemList(List<SubSystemReferenceDto> bizTranRoleSubSystemList) { this.bizTranRoleSubSystemList = bizTranRoleSubSystemList; }

	/**
	 * responseに変換
	 * @param response
	 */
	public void bindTo(Oa11010Vo response) {
		response.setJa(jaCode + " " + jaName);
		response.setJaId(jaId);

		response.setTempoReferenceDtoList(tempoReferenceDtoList);
		response.setExpirationSelect(expirationSelect);
		response.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
		response.setSubSystemRoleList(subSystemRoleList);
		response.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
		response.setBizTranRoleList(bizTranRoleList);
		response.setBizTranRoleSubSystemList(bizTranRoleSubSystemList);
	}
}
