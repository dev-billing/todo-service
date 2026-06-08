# Todo 단건 조회

## Description

* ID 로 특정 Todo 항목 단건을 조회합니다.
* `includeDeleted` 파라미터로 삭제된 항목 포함 여부를 제어할 수 있습니다. (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/{id} |
| Method | `GET` |
| Content-Type | `application/json` |

**Domain**

| 환경 | URL |
| --- | --- |
| 알파 | https://alpha-todo-service.com |
| 리얼 | https://todo-service.com |

## Request

### Header

| 항목명 | 필수여부 | 타입 | 의미 |
| --- | ---- | --- | --- |
| clientOrigin | Y | String | 호출처(빌링개발팀에 문의) |
| requestId | N | String | 요청 uuid (로그 추적용) |
| X-Caller-Id | Y | String | 호출자 식별자 |

### PathVariable

| 변수명 | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 조회할 Todo ID |

### Parameters

| 파라미터 | 필수여부 | 타입 | 기본값 | 설명 |
| --- | ---- | --- | --- | --- |
| includeDeleted | N | Boolean | false | 삭제된 항목 포함 여부 |

## Response

### Header

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| code | Y | String | 결과 코드 | success / 에러코드 |
| message | Y | String | 응답 메시지 | |
| requestId | Y | String | 로그 추적용 | 요청 requestId 또는 신규 생성 |

### Body

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| id | Y | Long | Todo ID | |
| title | Y | String | 제목 | |
| content | N | String | 내용 | |
| status | Y | String | 상태 | TODO / IN_PROGRESS / DONE |
| dueDate | N | String (date) | 마감일 | yyyy-MM-dd |
| priority | N | Integer | 우선순위 | |
| createdAt | Y | String (date-time) | 생성 일시 | |
| updatedAt | Y | String (date-time) | 수정 일시 | |

### Example

```json
{
  "header": {
    "code": "success",
    "message": "success",
    "requestId": "bill-api-0000000000000000"
  },
  "body": {
    "id": 1,
    "title": "장보기",
    "content": "우유, 계란 구매",
    "status": "TODO",
    "dueDate": "2026-06-10",
    "priority": 2,
    "createdAt": "2026-06-08T10:00:00",
    "updatedAt": "2026-06-08T10:00:00"
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
