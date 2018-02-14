package com.baeldung.sessionscoped;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/sessionattributes")
@SessionAttributes({"todos"})
public class TodoControllerWithSessionAttributes extends AbstractTodoController {

    private List<Category> allCategories;

    public TodoControllerWithSessionAttributes(List<Category> allCategories) {
        this.allCategories = allCategories;
        this.setFormUrl("/sessionattributes/form");
        this.setListView("/sessionattributes/todos.html");
        this.setTitle("Session Attributes Example");
    }

    @GetMapping("/form")
    public String showForm(
            Model model,
            @ModelAttribute("todos") TodoList todos) {
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
    public RedirectView create(
            @ModelAttribute TodoItem todo, 
            @ModelAttribute("todos") TodoList todos, 
            RedirectAttributes attributes) {
        todo.setCreateDate(LocalDateTime.now());
        todos.add(todo);
        attributes.addFlashAttribute("todos", todos);
        return new RedirectView(getListView());
    }

    @GetMapping("/todos.html")
    public String list(
            Model model, 
            @ModelAttribute("todos") TodoList todos) {
        model.addAttribute("todos", todos);
        model.addAttribute("formUrl", getFormUrl());
        model.addAttribute("title", getTitle());
        return "todos";
    }

    @ModelAttribute("todos")
    public TodoList todos() {
        return new TodoList();
    }
}