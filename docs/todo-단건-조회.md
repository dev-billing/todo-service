## Description

* 지정한 ID 의 Todo 항목을 반환합니다.
* 존재하지 않는 ID 요청 시 404 를 반환합니다.
* `includeDeleted=true` 인 경우 soft delete 된 항목도 함께 조회합니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/{id} |
| Method | `GET` |
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
| X-Caller-Id | Y | String | 호출 시스템 식별자 (감사 로그 추적용) |

### PathVariable

| 변수명 | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 조회할 Todo 의 고유 식별자 |

### Parameters

| 파라미터 | 필수여부 | 타입 | 기본값 | 설명 |
| --- | ---- | --- | --- | --- |
| includeDeleted | N | boolean | `false` | `true` 면 삭제된 항목도 조회 |

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
    "updatedAt": "2026-05-06T15:30:00"
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835

| 코드 | HTTP Status | 설명 |
| --- | ---- | --- |
| NOT_FOUND | 404 | 해당 ID 의 Todo 가 존재하지 않음 |
