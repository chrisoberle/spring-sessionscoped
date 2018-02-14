package com.baeldung.sessionscoped;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class TodoControllerWithSessionAttributesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
            .build();
    }

    @Test
    public void whenFirstRequest_thenContainsAllCategoriesAndUnintializedTodo() throws Exception {
        MvcResult result = mockMvc.perform(get("/sessionattributes/form"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("todo", "allCategories"))
            .andReturn();

        TodoItem item = (TodoItem) result.getModelAndView().getModel().get("todo");
        assertTrue(StringUtils.isEmpty(item.getDescription()));
        assertTrue(StringUtils.isEmpty(item.getCategory()));
    }

    @Test
    public void whenTodoExists_thenSubsequentFormRequestContainsesMostRecentTodo() throws Exception {
        FlashMap flashMap = mockMvc.perform(post("/sessionattributes/form")
            .param("description", "newtodo").param("category", "BUSINESS"))
            .andExpect(status().is3xxRedirection())
            .andReturn().getFlashMap();

        MvcResult result = mockMvc.perform(get("/sessionattributes/form")
            .sessionAttrs(flashMap))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("todo", "allCategories"))
            .andReturn();
        TodoItem item = (TodoItem) result.getModelAndView().getModel().get("todo");
        assertEquals("newtodo", item.getDescription());
        assertEquals(Category.BUSINESS, item.getCategory());
    }

}
