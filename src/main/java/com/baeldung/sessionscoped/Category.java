package com.baeldung.sessionscoped;

import java.util.Arrays;
import java.util.List;

public enum Category {
    BUSINESS("Business"),
    PERSONAL("Personal"); 

    private String name;

    private Category(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }


    public static List<Category> ALL_CATEGORIES = Arrays.asList(BUSINESS, PERSONAL);

}