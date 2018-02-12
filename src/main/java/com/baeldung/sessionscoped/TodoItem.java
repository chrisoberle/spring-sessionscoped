package com.baeldung.sessionscoped;

import java.time.LocalDateTime;

public class TodoItem {

    private String description;
    private LocalDateTime createDate;
    private Category category;

    public TodoItem(String description, LocalDateTime createDate, Category category) {
        this.description = description;
        this.createDate = createDate;
        this.category = category;
    }

    public TodoItem() {
        // TODO Auto-generated constructor stub
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "TodoItem [description=" + description + ", createDate=" + createDate + ", category=" + category + "]";
    }
}
