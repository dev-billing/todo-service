## Description

* 새로운 Todo 항목을 생성합니다.
* 생성된 항목의 초기 상태는 `TODO` 입니다.
* 사내 시스템 간 호출용 API 입니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /internal/api/todo-list |
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
| title | Y | String | Todo 제목 |
| content | N | String | Todo 상세 내용 |
| dueDate | N | String | 마감일 (`yyyy-MM-dd`) |
| priority | N | Integer | 우선순위 (1~5 권장) |
| tags | N | List<String> | 태그 목록 |

### Example

```json
{
  "title": "오늘 할 일 목록 정리",
  "dueDate": "2026-05-10",
  "priority": 3
}
```

## Response

### Header

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| code | Y | String | 결과 코드 | success / 에러코드 |
| message | Y | String | 응답 메시지 |  |
| requestId | Y | String | 로그 확인 및 추적용 |  |

### Body

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| id | Y | Long | 생성된 Todo 고유 식별자 |
| title | Y | String | Todo 제목 |
| status | Y | String | 생성 시 `TODO` |

### Example

```json
{
  "header": { "code": "success", "message": "success", "requestId": "bill-api-..." },
  "body": { "id": 50, "title": "오늘 할 일 목록 정리", "status": "TODO" }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
