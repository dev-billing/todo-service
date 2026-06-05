## Description

* 지정한 ID 의 Todo 항목을 수정합니다.
* `fields` 파라미터로 수정할 필드를 한정할 수 있으며, 미입력 시 전체 필드를 덮어씁니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/{id} |
| Method | `PATCH` |
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

### PathVariable

| 변수명 | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 수정할 Todo 의 고유 식별자 |

### Parameters

| 파라미터 | 필수여부 | 타입 | 기본값 | 설명 |
| --- | ---- | --- | --- | --- |
| fields | N | List<String> | - | 수정할 필드명 목록 (예: `title,status`), 미입력 시 전체 수정 |

### Body

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| title | N | String | 수정할 제목 |
| content | N | String | 수정할 상세 내용 |
| status | N | String | 수정할 상태값 (`TODO` / `IN_PROGRESS` / `DONE`) |
| dueDate | N | String | 수정할 마감일 (`yyyy-MM-dd`) |
| priority | N | Integer | 수정할 우선순위 (1~5 권장) |

### Example

```json
{
  "title": "수정된 할 일 제목",
  "status": "IN_PROGRESS",
  "priority": 5
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
| id | Y | Long | Todo 고유 식별자 |  |
| title | Y | String | Todo 제목 |  |
| content | N | String | Todo 상세 내용 |  |
| status | Y | String | 현재 상태 |  |
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
    "id": 42,
    "title": "수정된 할 일 제목",
    "content": "1. 장보기 2. 청소",
    "status": "IN_PROGRESS",
    "dueDate": "2026-05-10",
    "priority": 5,
    "createdAt": "2026-05-01T10:00:00",
    "updatedAt": "2026-05-06T15:30:00"
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835

| 코드 | HTTP Status | 설명 |
| --- | ---- | --- |
| NOT_FOUND | 404 | 해당 ID 의 Todo 가 존재하지 않음 |
| INVALID_PARAM | 400 | 필드 값 형식 오류 |
