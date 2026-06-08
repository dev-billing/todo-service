package com.example.review.report.controller;

import com.example.review.dto.response.TodoResponse;
import com.example.review.entity.Todo.TodoStatus;
import com.example.review.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports/todos")
@RequiredArgsConstructor
public class TodoReportController {

    private final TodoService todoService;

    @GetMapping("/daily")
    public Map<String, Long> dailyReport(@RequestParam TodoStatus status) {
        List<TodoResponse> targets = todoService.findAllByStatus(status);
        return Map.of("total", (long) targets.size());
    }

    @GetMapping("/priority-distribution")
    public Map<Integer, Long> priorityDistribution() {
        return todoService.findAll().stream()
                .filter(t -> t.getPriority() != null)
                .collect(java.util.stream.Collectors.groupingBy(
                        TodoResponse::getPriority,
                        java.util.stream.Collectors.counting()
                ));
    }
}
