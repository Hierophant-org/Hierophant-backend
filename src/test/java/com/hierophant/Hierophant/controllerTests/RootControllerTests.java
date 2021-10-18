package com.hierophant.Hierophant.controllerTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.web.servlet.view.RedirectView;

import com.hierophant.controller.RootController;

public class RootControllerTests {
	RootController rC = new RootController();
	
	@Test
	void testRedierctViewToApiDoc() {
		assertEquals(new RedirectView("swagger-ui/index.html"), rC.redirectViewToApiDocumentation());
	}
}
