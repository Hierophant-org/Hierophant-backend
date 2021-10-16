package com.hierophant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This root controller dictating what view will be displayed upon start up
 *You can find this view after running the app > go to http://localhost:5000
 * @author Ethan
 */
@Controller
public class RootController {Logger log = LoggerFactory.getLogger(this.getClass());
	@GetMapping
	@ResponseStatus(HttpStatus.SEE_OTHER)
	public RedirectView redirectViewToApiDocumentation() {
		return new RedirectView("swagger-ui/index.html");
	}
}
