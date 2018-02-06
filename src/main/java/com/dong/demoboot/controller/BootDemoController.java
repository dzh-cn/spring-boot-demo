package com.dong.demoboot.controller;

import com.dong.demoboot.dao.UserRepository;
import com.dong.demoboot.entity.User;
import com.dong.demoboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class BootDemoController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(BootDemoController.class);

	@GetMapping("index")
	String index() {
		return "index";
	}

	@GetMapping("index1")
	String index1() {
		return "index1";
	}

	@GetMapping("user/{id}")
	String getOne(@PathVariable Integer id, Model model) {
		User user = userService.getOne(id);
		logger.info(user.toString());
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping("/save")
	String saveOne(User user) {
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("users")
	String pageList(Sort sort, Model model) {
		model.addAttribute("list", userRepository.findAll(sort));
		return "users";
	}
}
