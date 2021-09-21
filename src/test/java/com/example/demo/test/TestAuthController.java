//package com.example.demo.test;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.http.MediaType;
//import com.example.demo.controller.AuthController;
//import com.example.demo.dto.RegisterRequest;
//import com.example.demo.service.AuthService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class TestAuthController {
//
//	private MockMvc mockMvc;
//	
//	@Mock
//	private AuthService authService;
//	
//	@InjectMocks
//	private AuthController authController;
//	
//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
//	}
//	
//	
//	@Test
//	public void testTokenPost() throws Exception{
//		
//		String json = "{/n" +
//				" \"username\": \"TestUser3\",\n " +
//				 "  \"email\": \"testUserEmail@gmail.com\",\n" +
//				 "  \"password\": \"testPassword\"\n" +
//				 "}";
//		
//		
//		
//		mockMvc.perform(post("/api/auth/signup")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json)
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.username", Matchers.is("TestUser3")))
//				.andExpect(jsonPath("$.email", Matchers.is("testUserEmail@gmail.com")))
//				.andExpect(jsonPath("$.password", Matchers.is("testPassword")))
//				.andExpect(jsonPath("$.*", Matchers.hasSize(3))));
//	}
//}
