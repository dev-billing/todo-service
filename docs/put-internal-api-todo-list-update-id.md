## Description

* 지정한 ID 의 Todo 항목을 전달된 값으로 전체 교체합니다 (PUT semantics).
* 사내 시스템 간 호출용 API 입니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /internal/api/todo-list/update/{id} |
| Method | `PUT` |
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

### Body

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| title | N | String | 수정할 제목 |
| content | N | String | 수정할 상세 내용 |
| status | N | String | 수정할 상태값 |
| dueDate | N | String | 수정할 마감일 |
| priority | N | Integer | 수정할 우선순위 |

### Example

```json
{
  "title": "수정된 제목",
  "status": "IN_PROGRESS",
  "priority": 5
}
```

## Response

### Header

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| code | Y | String | 결과 코드 |
| message | Y | String | 응답 메시지 |
| requestId | Y | String | 로그 추적용 |

### Body

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| id | Y | Long | Todo 고유 식별자 |
| title | Y | String | Todo 제목 |
| status | Y | String | 현재 상태 |

### Example

```json
{
  "header": { "code": "success", "message": "success", "requestId": "bill-api-..." },
  "body": { "id": 42, "title": "수정된 제목", "status": "IN_PROGRESS" }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835

| 코드 | HTTP Status | 설명 |
| --- | ---- | --- |
| NOT_FOUND | 404 | 해당 ID 의 Todo 가 존재하지 않음 |
