package com.ssm.controller;

import com.ssm.entity.Custom;
import com.ssm.service.ICustomService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.annotation.Resource;

@Controller("customController")
public class CustomController {

	@Resource
	private ICustomService customService;

	public void setCustomService(ICustomService customService) {
		this.customService = customService;
	}

	@RequestMapping("/custom/list")
	public @ResponseBody Map<String, Object> queryCustoms(@RequestParam(required = true, defaultValue = "1") int page,
			@RequestParam(required = true, defaultValue = "10") int rows) {
		return customService.queryCustoms(page, rows);
	}

	@RequestMapping("/custom/{id}")
	public String queryCustom(@PathVariable int id, Model model) {
		model.addAttribute("custom", customService.queryCustom(id));
		return "add";
	}

	@RequestMapping("/custom/insert")
	public @ResponseBody Map<String, Object> insertCustom(@ModelAttribute Custom custom) {
		return customService.addCustom(custom);
	}

	@RequestMapping("/custom/{id}/delete")
	public @ResponseBody Map<String, Object> insertCustom(@PathVariable int id) {
		return customService.delCustom(id);
	}

	@RequestMapping("/custom/update")
	public @ResponseBody Map<String, Object> updateCustom(@ModelAttribute Custom custom) {
		return customService.updateCustom(custom);
	}
}
