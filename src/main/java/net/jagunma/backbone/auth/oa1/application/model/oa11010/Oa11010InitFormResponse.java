package net.jagunma.backbone.auth.oa1.application.model.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.application.queryService.dto.TempoDto;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.vo.Oa11010BiztranRoleVo;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.vo.Oa11010SubsystemRoleVo;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.vo.Oa11010Vo;

/**
 * OA11010 オペレーター＜一覧＞ InitPresenter interface
 */
public interface Oa11010InitFormResponse {
	void setJaId(long jaId);
	void setJaCode(String jaCode);
	void setJaName(String jaName);
	void setExpirationSelect(Integer expirationSelect);
	void setSubsystemRoleConditionsSelect(Integer subsystemRoleConditionsSelect);
	void setSubsystemRoleList(List<Oa11010SubsystemRoleVo> subsystemRoleList);
	void setBiztranRoleConditionsSelect(Integer biztranRoleConditionsSelect);
	void setBiztranRoleList(List<Oa11010BiztranRoleVo> biztranRoleList);
	void setTempoList(List<TempoDto> tempoList);
	void setSubsystemRoleSubsystemList(List<SubSystemDto> subsystemRoleSubsystemList);
	void bindTo(Oa11010Vo response);
}
