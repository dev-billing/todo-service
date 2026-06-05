package com.example.review.report.controller;

import com.example.review.apidoc.ApiDocs;
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

    /**
     * Todo 일일 리포트
     * @apiScope external
     *
     * 특정 상태의 Todo 건수를 일별로 집계한 리포트를 반환합니다.
     * 리포팅·통계 도메인의 API 로, 일반 Todo API 와 다른 도메인을 사용합니다.
     *
     * @param status   집계할 상태값 (TODO / IN_PROGRESS / DONE)
     * @return 상태별 집계 결과
     */
    @ApiDocs(title = "Todo 일일 리포트")
    @GetMapping("/daily")
    public Map<String, Long> dailyReport(@RequestParam TodoStatus status) {
        List<TodoResponse> targets = todoService.findAllByStatus(status);
        return Map.of("total", (long) targets.size());
    }

    /**
     * Todo 우선순위 분포 리포트
     * @apiScope external
     *
     * 전체 Todo 의 우선순위별 분포를 반환합니다.
     *
     * @return 우선순위별 건수 맵
     */
    @ApiDocs(title = "Todo 우선순위 분포 리포트")
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
