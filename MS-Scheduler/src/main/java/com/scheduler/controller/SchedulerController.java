package com.scheduler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

	@GetMapping("/load-balanced")
	public String showLB() {

		return null;
	}
}
