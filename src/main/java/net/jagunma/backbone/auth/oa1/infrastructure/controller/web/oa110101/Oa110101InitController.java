package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa110101;

import net.jagunma.backbone.auth.oa1.application.service.oa110101.Oa110101InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "oa110101Init")
public class Oa110101InitController {
	private static final Logger LOGGER = LoggerFactory.getLogger(Oa110101InitController.class);
	private Oa110101InitService oa110101InitService;

	public Oa110101InitController(Oa110101InitService oa110101InitService) {
		this.oa110101InitService = oa110101InitService;
	}

	@GetMapping(path = "/get")
	private String get(Model model) {

		return "oa11010";
	}
}
