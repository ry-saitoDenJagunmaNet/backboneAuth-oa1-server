package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import net.jagunma.backbone.auth.authmanager.application.model.types.ConditionsExpirationSelect;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

public class Oa11010SearchValidator {
	private final Oa11010Vo oa11010Vo;

	Oa11010SearchValidator(Oa11010Vo oa11010Vo) {
		this.oa11010Vo = oa11010Vo;
	}

	public static Oa11010SearchValidator with(Oa11010Vo oa11010Vo) {
		return new Oa11010SearchValidator(oa11010Vo);
	}

	public void validate() {
		oa11010Vo.getSubSystemRoleList().forEach(a -> {
			if (Oa11010Vo.CHECKBOX_TRUE.equals(a.getSubSystemRoleSelected()) &&
				ConditionsExpirationSelect.条件指定.equals(a.getExpirationSelect())) {
				Preconditions.checkNotNull(a.getExpirationStatusDate(),
					() -> new GunmaRuntimeException("EOA10001", "状態指定日"));
			}
		});
	}
}
