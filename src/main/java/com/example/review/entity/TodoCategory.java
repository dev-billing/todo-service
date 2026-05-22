package com.example.review.entity;

import lombok.Getter;

@Getter
public enum TodoCategory {

    PERSONAL("개인 일정"),
    WORK("업무"),
    STUDY("학습"),
    OTHER("기타");

    private final String description;

    TodoCategory(String description) {
        this.description = description;
    }
}
