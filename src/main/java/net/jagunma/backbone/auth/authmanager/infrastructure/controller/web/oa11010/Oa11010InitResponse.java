package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SubSystemRoleVo;

/**
 * OA11010 オペレーター＜一覧＞ InitPresenter interface
 */
public interface Oa11010InitResponse {
	/**
	 * ＪＡIDのＳｅｔ
	 * @param jaId
	 */
	void setJaId(long jaId);
	/**
	 * ＪＡコードのＳｅｔ
	 * @param jaCode
	 */
	void setJaCode(String jaCode);
	/**
	 * ＪＡ名のＳｅｔ
	 * @param jaName
	 */
	void setJaName(String jaName);
	/**
	 * 店舗コンボボックスリストのＳｅｔ
	 * @param tempoReferenceDtoList
	 */
	void setTempoReferenceDtoList(List<TempoReferenceDto> tempoReferenceDtoList);
	/**
	 * 有効期限選択のＳｅｔ
	 * @param expirationSelect
	 */
	void setExpirationSelect(Integer expirationSelect);
	/**
	 * サブシステムロール条件選択のＳｅｔ
	 * @param subSystemRoleConditionsSelect
	 */
	void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect);
	/**
	 * サブシステムロールリストのＳｅｔ
	 * @param subSystemRoleList
	 */
	void setSubsystemRoleList(List<Oa11010SubSystemRoleVo> subSystemRoleList);
	/**
	 * 取引ロール条件選択のＳｅｔ
	 * @param biztranRoleConditionsSelect
	 */
	void setBiztranRoleConditionsSelect(Integer biztranRoleConditionsSelect);
	/**
	 * 取引ロール一覧のＳｅｔ
	 * @param bizTranRoleList
	 */
	void setBizTranRoleList(List<Oa11010BizTranRoleVo> bizTranRoleList);
	/**
	 * サブシステムロールサブシステムコンボボックスリストのＳｅｔ
	 * @param bizTranRoleSubSystemList
	 */
	void setBizTranRoleSubSystemList(List<SubSystemReferenceDto> bizTranRoleSubSystemList);
}
