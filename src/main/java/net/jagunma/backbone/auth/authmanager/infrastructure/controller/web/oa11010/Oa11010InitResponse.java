package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoDto;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SubSystemRoleVo;

/**
 * OA11010 オペレーター＜一覧＞ InitPresenter interface
 */
public interface Oa11010InitResponse {
	/**
	 * ＪＡIDのセット
	 * @param jaId
	 */
	void setJaId(long jaId);
	/**
	 * ＪＡコードのセット
	 * @param jaCode
	 */
	void setJaCode(String jaCode);
	/**
	 * ＪＡ名のセット
	 * @param jaName
	 */
	void setJaName(String jaName);
	/**
	 * 店舗コンボボックスリストのセット
	 * @param tempoList
	 */
	void setTempoList(List<TempoDto> tempoList);
	/**
	 * 有効期限選択のセット
	 * @param expirationSelect
	 */
	void setExpirationSelect(Integer expirationSelect);
	/**
	 * サブシステムロール条件選択のセット
	 * @param subSystemRoleConditionsSelect
	 */
	void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect);
	/**
	 * サブシステムロールリストのセット
	 * @param subSystemRoleList
	 */
	void setSubsystemRoleList(List<Oa11010SubSystemRoleVo> subSystemRoleList);
	/**
	 * 取引ロール条件選択のセット
	 * @param biztranRoleConditionsSelect
	 */
	void setBiztranRoleConditionsSelect(Integer biztranRoleConditionsSelect);
	/**
	 * 取引ロール一覧のセット
	 * @param bizTranRoleList
	 */
	void setBizTranRoleList(List<Oa11010BizTranRoleVo> bizTranRoleList);
	/**
	 * サブシステムロールサブシステムコンボボックスリストのセット
	 * @param bizTranRoleSubSystemList
	 */
	void setBizTranRoleSubSystemList(List<SubSystemDto> bizTranRoleSubSystemList);
}
