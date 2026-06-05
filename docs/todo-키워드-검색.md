## Description

* 제목 또는 내용에 키워드가 포함된 Todo 목록을 반환합니다.
* `status` 필터를 함께 지정하면 해당 상태의 항목만 조회합니다.
* 키워드 매칭은 대소문자를 구분하지 않습니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/find |
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

### Parameters

| 파라미터 | 필수여부 | 타입 | 기본값 | 설명 |
| --- | ---- | --- | --- | --- |
| keyword | Y | String | - | 제목/내용에 포함될 검색어 (대소문자 무시) |
| status | N | TodoStatus | - | 필터링할 상태값 (`TODO` / `IN_PROGRESS` / `DONE`), 미입력 시 전체 |

## Response

### Header

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| code | Y | String | 결과 코드 | success / 에러코드 |
| message | Y | String | 응답 메시지 |  |
| requestId | Y | String | 로그 확인 및 추적용 | request 시 요청했던 requestId<br>없다면 신규 requestId 를 생성 |

### Body

응답 body 는 `TodoResponse` 의 배열.

| 필드명 | 필수여부 | 타입 | 설명 | 비고 |
| --- | ---- | --- | --- | --- |
| body[].id | Y | Long | Todo 고유 식별자 |  |
| body[].title | Y | String | Todo 제목 |  |
| body[].content | N | String | Todo 상세 내용 |  |
| body[].status | Y | String | 현재 상태 |  |
| body[].dueDate | N | String | 마감일 |  |
| body[].priority | N | Integer | 우선순위 |  |
| body[].createdAt | Y | String | 생성 일시 |  |
| body[].updatedAt | Y | String | 최종 수정 일시 |  |

### Example

```json
{
  "header": {
    "code": "success",
    "message": "success",
    "requestId": "bill-api-b15f7e69-d68b-4704-95b4-2f45543c384a"
  },
  "body": [
    {
      "id": 42,
      "title": "오늘 할 일 목록 정리",
      "status": "TODO",
      "dueDate": "2026-05-10",
      "priority": 3,
      "createdAt": "2026-05-01T10:00:00",
      "updatedAt": "2026-05-06T15:30:00"
    }
  ]
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
