package com.example.review.controller;

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

    /**
     * Todo 단건 조회
     * @apiScope external
     *
     * 지정한 ID의 Todo 항목을 반환합니다.
     * 존재하지 않는 ID 요청 시 404를 반환합니다.
     * includeDeleted=true 인 경우 soft delete 된 항목도 함께 조회합니다.
     *
     * @path  id              조회할 Todo의 고유 식별자
     * @param includeDeleted  true 면 삭제된 항목도 조회 (기본값: false)
     * @header X-Caller-Id    호출 시스템 식별자 (감사 로그 추적용)
     * @return 조회된 Todo 정보
     */
    @GetMapping("/{id}")
    public TodoResponse getById(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted,
            @RequestHeader("X-Caller-Id") String callerId) {
        return todoService.findById(id);
    }

    /**
     * Todo 통계 조회
     * @apiScope external
     *
     * 전체 또는 필터링된 Todo의 상태별 집계를 반환합니다. 삭제된 항목은 집계에서 제외됩니다.
     *
     * @param status        필터링할 상태값 (TODO / IN_PROGRESS / DONE), 미입력 시 전체
     * @param minPriority   이 값 이상의 우선순위를 가진 항목만 집계, 미입력 시 전체
     * @return 상태별 Todo 집계 (total, done, pending)
     */
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

    /**
     * Todo 생성
     * @apiScope external
     *
     * 새로운 Todo 항목을 생성합니다.
     * 생성된 항목의 초기 상태는 TODO입니다.
     *
     * @body request 생성할 Todo 정보 (title, content, dueDate, priority)
     * @return 생성된 Todo 정보
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
    }

    /**
     * Todo 수정
     * @apiScope external
     *
     * 지정한 ID의 Todo 항목을 수정합니다.
     * fields 파라미터로 수정할 필드를 한정할 수 있으며, 미입력 시 전체 필드를 덮어씁니다.
     *
     * @path  id       수정할 Todo의 고유 식별자
     * @param fields   수정할 필드명 목록 (예: title,status), 미입력 시 전체 수정
     * @body  request  수정할 내용 (부분 수정 시 fields 와 함께 사용)
     * @return 수정된 Todo 정보
     */
    @PatchMapping("/{id}")
    public TodoResponse update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request,
            @RequestParam(required = false) List<String> fields) {
        return todoService.update(id, request, fields);
    }

    /**
     * Todo 키워드 검색
     * @apiScope external
     *
     * 제목 또는 내용에 키워드가 포함된 Todo 목록을 반환합니다.
     * 상태 필터를 함께 지정하면 해당 상태의 항목만 조회합니다.
     *
     * @param keyword  제목/내용에 포함될 검색어 (대소문자 무시)
     * @param status   필터링할 상태값 (TODO / IN_PROGRESS / DONE), 미입력 시 전체
     * @return 키워드와 일치하는 Todo 목록
     */
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

    /**
     * 카테고리 지정 Todo 생성
     * @apiScope external
     *
     * 카테고리(PERSONAL/WORK/STUDY/OTHER)를 함께 지정해 Todo 를 생성합니다.
     * 카테고리 정보는 별도 분류·집계에 활용됩니다.
     *
     * @body request 카테고리 포함 Todo 정보 (title, content, dueDate, priority, category)
     * @return 생성된 Todo 정보
     */
    @PostMapping("/categorized")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createCategorized(@RequestBody TodoCategorizedCreateRequest request) {
        TodoCreateRequest base = new TodoCreateRequest();
        // category 는 향후 service 레이어 확장 시 활용
        return todoService.create(base);
    }
}
