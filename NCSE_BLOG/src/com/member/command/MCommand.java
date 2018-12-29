package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MCommand {
	
	void excute(HttpServletRequest req, HttpServletResponse res);

}
