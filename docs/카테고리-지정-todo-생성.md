## Description

* 카테고리(`PERSONAL` / `WORK` / `STUDY` / `OTHER`) 를 함께 지정해 Todo 를 생성합니다.
* 카테고리 정보는 별도 분류·집계에 활용됩니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/categorized |
| Method | `POST` |
| Content-Type | `application/json` |

**Domain**

| 환경 | URL |
| --- | --- |
| 알파 | https://alpha-todo.example.com |
| 리얼 | https://todo.example.com |

## Request

### Header

| 항목명 | 필수여부 | 타입 | 의미 |
| --- | ---- | --- | --- |
| clientOrigin | Y | String | 호출처(빌링개발팀에 문의) |
| requestId | N | String | 요청 uuid (로그 추적용) |

### Body

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| title | Y | String | Todo 제목 (최대 100자) |
| content | N | String | Todo 상세 내용 |
| dueDate | N | String | 마감일 (`yyyy-MM-dd`) |
| priority | N | Integer | 우선순위 (1~5 권장) |
| category | Y | TodoCategory | Todo 카테고리 (`PERSONAL` / `WORK` / `STUDY` / `OTHER`) |

### Example

```json
{
  "title": "5월 회고 정리",
  "content": "회고 미팅 안건 정리 + 액션아이템 도출",
  "dueDate": "2026-05-30",
  "priority": 4,
  "category": "WORK"
}
```

## Response

### Header

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| code | Y | String | 결과 코드 | success / 에러코드 |
| message | Y | String | 응답 메시지 |  |
| requestId | Y | String | 로그 확인 및 추적용 | request 시 요청했던 requestId<br>없다면 신규 requestId 를 생성 |

### Body

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| id | Y | Long | 생성된 Todo 고유 식별자 |  |
| title | Y | String | Todo 제목 |  |
| content | N | String | Todo 상세 내용 |  |
| status | Y | String | 현재 상태 | 생성 시 `TODO` |
| dueDate | N | String | 마감일 |  |
| priority | N | Integer | 우선순위 |  |
| createdAt | Y | String | 생성 일시 |  |
| updatedAt | Y | String | 최종 수정 일시 |  |

### Example

```json
{
  "header": {
    "code": "success",
    "message": "success",
    "requestId": "bill-api-b15f7e69-d68b-4704-95b4-2f45543c384a"
  },
  "body": {
    "id": 99,
    "title": "5월 회고 정리",
    "content": "회고 미팅 안건 정리 + 액션아이템 도출",
    "status": "TODO",
    "dueDate": "2026-05-30",
    "priority": 4,
    "createdAt": "2026-05-15T10:00:00",
    "updatedAt": "2026-05-15T10:00:00"
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835

| 코드 | HTTP Status | 설명 |
| --- | ---- | --- |
| INVALID_PARAM | 400 | 필수 필드 누락 또는 잘못된 category 값 |
