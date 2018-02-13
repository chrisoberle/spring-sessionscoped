package com.baeldung.sessionscoped;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("todos")
public class TodoController {

    //private TodoList todos;
    private List<Category> allCategories;

    public TodoController(List<Category> allCategories) {
        //this.todos = todos;
        this.allCategories = allCategories;
    }

    @GetMapping("/form")
    public String showForm(Model model, @ModelAttribute("todos") TodoList todos) {
        if (!todos.isEmpty()) {
            model.addAttribute("todo", todos.peekLast());
        } else {
            model.addAttribute("todo", new TodoItem());
        }
        model.addAttribute("allCategories", allCategories);
        return "form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute TodoItem todo, @ModelAttribute("todos") TodoList todos) {
        todo.setCreateDate(LocalDateTime.now());
        todos.add(todo);
        return "redirect:/todos.html";
    }

    @GetMapping("/todos.html")
    public String list(Model model, @ModelAttribute("todos") TodoList todos) {
        model.addAttribute("todos", todos);
        return "todos";
    }

    @ModelAttribute("todos")
    public TodoList todos() {
        return new TodoList();
    }
}
