package net.jagunma.backbone.auth.oa1.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "oa100000Sample")
public class Oa100000SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa100000SampleController.class);
//	private Oa100000SampleService oa100000SampleService;
//
//	public Oa100000SampleController(Oa100000SampleService oa100000SampleService) {
//		this.oa100000SampleService = oa100000SampleService;
//
//	}

    @GetMapping(path = "/get")
    private String get(Model model) {

        return "hello";
    }

    @PostMapping(path = "/execute/{jaCode}/{tempoCode}/{operatorCode}")
    public String execute(@PathVariable("jaCode") String jaCode,
        @PathVariable("tempoCode") String tempoCode,
        @PathVariable("operatorCode") String operatorCode,
        Model model) {

        model.addAttribute("message", "POSTされたよ");

        return "hello";
    }
}