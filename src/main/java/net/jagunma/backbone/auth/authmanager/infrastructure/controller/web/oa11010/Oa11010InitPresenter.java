package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoDto;
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
	private List<TempoDto> tempoList;
	private Integer expirationSelect;
	private Integer subSystemRoleConditionsSelect;
	private List<Oa11010SubSystemRoleVo> subSystemRoleList;
	private Integer bizTranRoleConditionsSelect;
	private List<Oa11010BizTranRoleVo> bizTranRoleList;
	private List<SubSystemDto> bizTranRoleSubSystemList;

	Oa11010InitPresenter() {
	}

	/**
	 * ＪＡIDのセット
	 * @param jaId
	 */
	public void setJaId(long jaId) {
		this.jaId = jaId;
	}
	/**
	 * ＪＡコードのセット
	 * @param jaCode
	 */
	public void setJaCode(String jaCode) {
		this.jaCode = jaCode;
	}
	/**
	 * 店舗コンボボックスリストのセット
	 * @param tempoList
	 */
	public void setTempoList(List<TempoDto> tempoList) { this.tempoList = tempoList; }
	/**
	 * ＪＡ名のセット
	 * @param jaName
	 */
	public void setJaName(String jaName) {
		this.jaName = jaName;
	}
	/**
	 * 有効期限選択のセット
	 * @param expirationSelect
	 */
	public void setExpirationSelect(Integer expirationSelect) { this.expirationSelect = expirationSelect; }
	/**
	 * サブシステムロール条件選択のセット
	 * @param subSystemRoleConditionsSelect
	 */
	public void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect) { this.subSystemRoleConditionsSelect = subSystemRoleConditionsSelect; }
	/**
	 * サブシステムロールリストのセット
	 * @param subSystemRoleList
	 */
	public void setSubsystemRoleList(List<Oa11010SubSystemRoleVo> subSystemRoleList) { this.subSystemRoleList = subSystemRoleList; }
	/**
	 * 取引ロール条件選択のセット
	 * @param bizTranRoleConditionsSelect
	 */
	public void setBiztranRoleConditionsSelect(Integer bizTranRoleConditionsSelect) { this.bizTranRoleConditionsSelect = bizTranRoleConditionsSelect; }
	/**
	 * 取引ロール一覧のセット
	 * @param bizTranRoleList
	 */
	public void setBizTranRoleList(List<Oa11010BizTranRoleVo> bizTranRoleList) { this.bizTranRoleList = bizTranRoleList; }
	/**
	 * サブシステムロールサブシステムコンボボックスリストのセット
	 * @param bizTranRoleSubSystemList
	 */
	public void setBizTranRoleSubSystemList(List<SubSystemDto> bizTranRoleSubSystemList) { this.bizTranRoleSubSystemList = bizTranRoleSubSystemList; }

	/**
	 * responseに変換
	 * @param response
	 */
	public void bindTo(Oa11010Vo response) {
		response.setJa(jaCode + " " + jaName);
		response.setJaId(jaId);

		response.setTempoList(tempoList);
		response.setExpirationSelect(expirationSelect);
		response.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
		response.setSubSystemRoleList(subSystemRoleList);
		response.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
		response.setBizTranRoleList(bizTranRoleList);
		response.setBizTranRoleSubSystemList(bizTranRoleSubSystemList);
	}
}
