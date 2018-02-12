package com.baeldung.sessionscoped;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    private TodoList todos;
    private List<Category> allCategories;

    public TodoController(TodoList todos, List<Category> allCategories) {
        this.todos = todos;
        this.allCategories = allCategories;
    }

    @GetMapping("/index.html")
    public String showForm(Model model) {
        model.addAttribute("todo", new TodoItem());
        model.addAttribute("allCategories", allCategories);
        return "index";
    }

    @PostMapping("/createTodo")
    public String create(@ModelAttribute TodoItem todo) {
        todos.add(todo);
        return "redirect:/todos.html";
    }

    @GetMapping("/todos.html")
    public String list(Model model) {
        model.addAttribute("todos", todos);
        return "todos";
    }
}
