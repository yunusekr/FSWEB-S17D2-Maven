package com.workintech.s17d2.model;

import com.workintech.s17d2.model.enums.Experience;

public class MidDeveloper extends Developer{
    public MidDeveloper(int id, String name, double salary) {
        super(id, name, salary,Experience.MID);
    }
}
