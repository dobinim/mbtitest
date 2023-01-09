package com.kim.mbtitest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface MbtiService {
	
	public void execute(HttpServletRequest request, Model model);

}
