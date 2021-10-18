package com.hierophant.Hierophant.controllerTests;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hierophant.controller.UserController;
import com.hierophant.model.AuthRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationTests {

    @Autowired
    private MockMvc mvc;
    private UserController uC = new UserController();

    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isForbidden());
    }
    
    @Test
    public void shouldGenerateAuthToken() throws Exception {
    	AuthRequest a = new AuthRequest("john","doe");
        String token = uC.generateToken(a);
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
    }
}