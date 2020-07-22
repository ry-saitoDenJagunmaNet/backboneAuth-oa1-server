package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoDto;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010BiztranRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SubsystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;

/**
 * OA11010 オペレーター＜一覧＞ 画面初期表示 Presenter
 */
class Oa11010InitPresenter implements Oa11010InitResponse {

	private long jaId;
	private String jaCode;
	private String jaName;
	private Integer expirationSelect;
	private Integer subsystemRoleConditionsSelect;
	private List<Oa11010SubsystemRoleVo> subsystemRoleList;
	private Integer biztranRoleConditionsSelect;
	private List<Oa11010BiztranRoleVo> biztranRoleList;
	private List<TempoDto> tempoList;
	private List<SubSystemDto> subsystemRoleSubsystemList;

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
	 * @param subsystemRoleConditionsSelect
	 */
	public void setSubsystemRoleConditionsSelect(Integer subsystemRoleConditionsSelect) { this.subsystemRoleConditionsSelect = subsystemRoleConditionsSelect; }
	/**
	 * サブシステムロールリストのセット
	 * @param subsystemRoleList
	 */
	public void setSubsystemRoleList(List<Oa11010SubsystemRoleVo> subsystemRoleList) { this.subsystemRoleList = subsystemRoleList; }
	/**
	 * 取引ロール条件選択のセット
	 * @param biztranRoleConditionsSelect
	 */
	public void setBiztranRoleConditionsSelect(Integer biztranRoleConditionsSelect) { this.biztranRoleConditionsSelect = biztranRoleConditionsSelect; }
	/**
	 * 取引ロール一覧のセット
	 * @param biztranRoleList
	 */
	public void setBiztranRoleList(List<Oa11010BiztranRoleVo> biztranRoleList) { this.biztranRoleList = biztranRoleList; }
	/**
	 * 店舗コンボボックスリストのセット
	 * @param tempoList
	 */
	public void setTempoList(List<TempoDto> tempoList) { this.tempoList = tempoList; }
	/**
	 * サブシステムロールサブシステムコンボボックスリストのセット
	 * @param subsystemRoleSubsystemList
	 */
	public void setSubsystemRoleSubsystemList(List<SubSystemDto> subsystemRoleSubsystemList) { this.subsystemRoleSubsystemList = subsystemRoleSubsystemList; }

	/**
	 * responseに変換
	 * @param response
	 */
	public void bindTo(Oa11010Vo response) {
		response.setJa(jaCode + " " + jaName);
		response.setJaId(jaId);

		response.setTempoList(tempoList);
		response.setExpirationSelect(expirationSelect);
		response.setSubsystemRoleConditionsSelect(subsystemRoleConditionsSelect);
		response.setSubsystemRoleList(subsystemRoleList);
		response.setBiztranRoleConditionsSelect(biztranRoleConditionsSelect);
		response.setBiztranRoleList(biztranRoleList);
		response.setSubsystemRoleSubsystemList(subsystemRoleSubsystemList);
	}
}
