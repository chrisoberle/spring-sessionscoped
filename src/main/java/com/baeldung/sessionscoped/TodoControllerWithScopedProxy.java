package com.baeldung.sessionscoped;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scopedproxy")
public class TodoControllerWithScopedProxy extends AbstractTodoController {

    private TodoList todos;
    private List<Category> allCategories;

    public TodoControllerWithScopedProxy(List<Category> allCategories, TodoList todos) {
        this.todos = todos;
        this.allCategories = allCategories;
        this.setFormUrl("/scopedproxy/form");
        this.setListView("redirect:/scopedproxy/todos.html");
        this.setTitle("Scoped Proxy Example");
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        if (!todos.isEmpty()) {
            model.addAttribute("todo", todos.peekLast());
        } else {
            model.addAttribute("todo", new TodoItem());
        }
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("formUrl", getFormUrl());
        model.addAttribute("title", getTitle());
        return "form";
    }

    @PostMapping("/form")
    public String create(@ModelAttribute TodoItem todo) {
        todo.setCreateDate(LocalDateTime.now());
        todos.add(todo);
        return getListView();
    }

    @GetMapping("/todos.html")
    public String list(Model model) {
        model.addAttribute("todos", todos);
        model.addAttribute("formUrl", getFormUrl());
        model.addAttribute("title", getTitle());
        return "todos";
    }
}
