package net.jagunma.backbone.auth.oa1.application.service.oa11010;

import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.Oa11010Model;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Oa11010InitService {
	public Oa11010InitService() {
	}

	public Oa11010Model initForm() {
		Oa11010Model oa11010Model = new Oa11010Model();
		// todo: サインインオペレータのJA
		oa11010Model.setJa("006 JA前橋");
		oa11010Model.setJaId(6);
		return oa11010Model;
	}
}
