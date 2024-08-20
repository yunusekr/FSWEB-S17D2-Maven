package com.workintech.s17d2.model;

import com.workintech.s17d2.model.enums.Experience;

public class SeniorDeveloper extends Developer{
    public SeniorDeveloper(int id, String name, double salary) {
        super(id, name, salary,Experience.SENIOR);
    }
}
