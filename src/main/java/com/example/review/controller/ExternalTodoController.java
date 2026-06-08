package com.example.review.controller;

import com.example.review.common.apidoc.ApiDocs;
import com.example.review.dto.request.TodoCategorizedCreateRequest;
import com.example.review.dto.request.TodoCreateRequest;
import com.example.review.dto.request.TodoUpdateRequest;
import com.example.review.dto.response.TodoResponse;
import com.example.review.entity.Todo.TodoStatus;
import com.example.review.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/external/api/todo-list")
@RequiredArgsConstructor
public class ExternalTodoController {

    private final TodoService todoService;

    // [시나리오 1] title 있는 @ApiDocs + 매칭 md
    @ApiDocs(title = "Todo 단건 조회")
    @GetMapping("/{id}")
    public TodoResponse getById(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted,
            @RequestHeader("X-Caller-Id") String callerId) {
        return todoService.findById(id);
    }

    // [시나리오 2] title 없는 @ApiDocs (method-url 파일명) + 매칭 md
    @ApiDocs
    @GetMapping("/statistics")
    public Map<String, Long> getStatistics(
            @RequestParam(required = false) TodoStatus status,
            @RequestParam(required = false) Integer minPriority) {
        List<TodoResponse> targets = (status != null)
                ? todoService.findAllByStatus(status)
                : todoService.findAll();
        if (minPriority != null) {
            targets = targets.stream()
                    .filter(t -> t.getPriority() != null && t.getPriority() >= minPriority)
                    .toList();
        }
        long total = targets.size();
        long done = targets.stream().filter(t -> t.getStatus() == TodoStatus.DONE).count();
        return Map.of("total", total, "done", done, "pending", total - done);
    }

    // [시나리오 3] @ApiDocs 있는데 md 없음 — claude-review hint 검증
    @ApiDocs(title = "Todo 생성")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
    }

    @PatchMapping("/{id}")
    public TodoResponse update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request,
            @RequestParam(required = false) List<String> fields) {
        return todoService.update(id, request, fields);
    }

    @GetMapping("/find")
    public List<TodoResponse> findByKeyword(
            @RequestParam String keyword,
            @RequestParam(required = false) TodoStatus status) {
        List<TodoResponse> source = (status != null)
                ? todoService.findAllByStatus(status)
                : todoService.findAll();
        String needle = keyword.toLowerCase();
        return source.stream()
                .filter(t -> (t.getTitle() != null && t.getTitle().toLowerCase().contains(needle))
                        || (t.getContent() != null && t.getContent().toLowerCase().contains(needle)))
                .toList();
    }

    @PostMapping("/categorized")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createCategorized(@RequestBody TodoCategorizedCreateRequest request) {
        TodoCreateRequest base = new TodoCreateRequest();

        return todoService.create(base);
    }

    // [시나리오 4] 신규 endpoint, @ApiDocs 미부착 — claude-review hint 검증
    @GetMapping("/random")
    public TodoResponse random() {
        return todoService.findAll().stream().findAny().orElse(null);
    }
}
