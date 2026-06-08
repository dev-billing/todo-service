# Todo 생성

## Description

* 새로운 Todo 항목을 생성합니다.
* 생성에 성공하면 HTTP 201 (Created) 와 함께 생성된 Todo 정보를 반환합니다.

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/create |
| Method | `POST` |
| Content-Type | `application/json` |

**Domain**

| 환경 | URL |
| --- | --- |
| Alpha | https://alpha-todo-service.com |
| Real | https://todo-service.com |

## Request

### Header

| 항목명 | 필수여부 | 타입 | 의미 |
| --- | ---- | --- | --- |
| clientOrigin | Y | String | 호출처(빌링개발팀에 문의) |
| requestId | N | String | 요청 uuid (로그 추적용) |

### Body

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| title | Y | String | Todo 제목 (추정) |
| content | N | String | Todo 상세 내용 |
| dueDate | N | String | 마감일 (`yyyy-MM-dd`) |
| priority | N | Integer | 우선순위 (1~5) (추정) |
| tags | N | List<String> | 태그 목록 |

### Example

```json
{
  "title": "오늘 할 일 목록 정리",
  "content": "1. 장보기 2. 청소",
  "dueDate": "2026-05-10",
  "priority": 3,
  "tags": ["집안일", "주말"]
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
| status | Y | String | 현재 상태 | `TODO` / `IN_PROGRESS` / `DONE` |
| dueDate | N | String | 마감일 | `yyyy-MM-dd` |
| priority | N | Integer | 우선순위 | 1~5 |
| createdAt | Y | String | 생성 일시 | ISO-8601 |
| updatedAt | Y | String | 최종 수정 일시 | ISO-8601 |

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
    "title": "오늘 할 일 목록 정리",
    "content": "1. 장보기 2. 청소",
    "status": "TODO",
    "dueDate": "2026-05-10",
    "priority": 3,
    "createdAt": "2026-05-01T10:00:00",
    "updatedAt": "2026-05-01T10:00:00"
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
