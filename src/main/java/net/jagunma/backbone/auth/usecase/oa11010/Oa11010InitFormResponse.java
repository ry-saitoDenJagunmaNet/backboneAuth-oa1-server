package net.jagunma.backbone.auth.usecase.oa11010;

import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.dtox.Oa11010BiztranRoleDto;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.dtox.Oa11010ResponseDto;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.dtox.Oa11010SubsystemRoleDto;

/**
 * OA11010 オペレーター＜一覧＞ InitPresenter interface
 */
public interface Oa11010InitFormResponse {
	void setJaId(long jaId);
	void setJaCode(String jaCode);
	void setJaName(String jaName);
	void setExpirationSelect(Integer expirationSelect);
	void setSubsystemRoleConditionsSelect(Integer subsystemRoleConditionsSelect);
	void setSubsystemRoleList(List<Oa11010SubsystemRoleDto> subsystemRoleList);
	void setBiztranRoleConditionsSelect(Integer biztranRoleConditionsSelect);
	void setBiztranRoleList(List<Oa11010BiztranRoleDto> biztranRoleList);
	void setTempoList(Map<String, String> tempoList);
	void setSubsystemRoleSubsystemList(Map<String, String> subsystemRoleSubsystemList);
	void bindTo(Oa11010ResponseDto response);
}
