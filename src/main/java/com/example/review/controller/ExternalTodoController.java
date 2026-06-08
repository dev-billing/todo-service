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

    @ApiDocs(title = "Todo 단건 조회")
    @GetMapping("/{id}")
    public TodoResponse getById(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted,
            @RequestHeader("X-Caller-Id") String callerId) {
        return todoService.findById(id);
    }

    // [시나리오 B] 기존 @ApiDocs 메서드의 시그니처 변경 + 매칭 md 는 의도적으로 미수정
    // → claude-review 가 "외부 인터페이스 변경 vs docs/*.md 미갱신" hint 띄워야
    @ApiDocs
    @GetMapping("/statistics")
    public Map<String, Long> getStatistics(
            @RequestParam(required = false) TodoStatus status,
            @RequestParam(required = false) Integer minPriority,
            @RequestParam(required = false, defaultValue = "true") boolean includeDone) {
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

    @GetMapping("/random")
    public TodoResponse random() {
        return todoService.findAll().stream().findAny().orElse(null);
    }

    // [시나리오 A] @ApiDocs(scope="private") 명시 — URL 은 /external/ 인데 실제론 내부용
    // sync 시 URL prefix 휴리스틱 무시하고 "private" (내부) 부모로 발행되어야
    @ApiDocs(title = "Todo 디버그 정보", scope = "private")
    @GetMapping("/debug/info")
    public Map<String, Object> debugInfo() {
        return Map.of(
                "totalCount", (long) todoService.findAll().size(),
                "service", "todo-service"
        );
    }
}
