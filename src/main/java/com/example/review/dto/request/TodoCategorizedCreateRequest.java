package com.example.review.dto.request;

import com.example.review.entity.TodoCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoCategorizedCreateRequest {

    private String title;

    private String content;

    private LocalDate dueDate;

    private Integer priority;

    private TodoCategory category;
}
