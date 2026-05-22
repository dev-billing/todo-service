package com.example.review.dto.request;

import com.example.review.entity.TodoCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoCategorizedCreateRequest {

    /**
     * Todo 제목. 최대 100자.
     * @ex "5월 회고 정리"
     */
    private String title;

    /**
     * Todo 상세 내용 (선택).
     * @ex "회고 미팅 안건 정리 + 액션아이템 도출"
     */
    private String content;

    /**
     * 마감일 (yyyy-MM-dd).
     * @ex "2026-05-30"
     */
    private LocalDate dueDate;

    /**
     * 우선순위. 1~5 권장, 숫자가 높을수록 중요.
     * @ex 4
     */
    private Integer priority;

    /**
     * Todo 카테고리. 값은 TodoCategory enum 을 따른다.
     * @ex "WORK"
     */
    private TodoCategory category;
}
