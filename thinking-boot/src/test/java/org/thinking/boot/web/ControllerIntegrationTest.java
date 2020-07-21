package org.thinking.boot.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thinking.boot.profile.BeanService;
import org.thinking.boot.profile.ProfileConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProfileConfig.class })
@ActiveProfiles(profiles = { "prod" })
@WebAppConfiguration(value = "src/main/webapp") // default: src/main/webapp
public class ControllerIntegrationTest {
	private MockMvc mockMvc;
	@Autowired
	private BeanService beanService;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockHttpSession session;
	@Autowired
	private MockHttpServletRequest request;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testNomalController() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/nomal")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("page"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("page.jsp"))
				.andExpect(MockMvcResultMatchers.model().attribute("message", beanService.getContent()));
	}

	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/restApi")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.content().json(beanService.getContent()));
	}
}
