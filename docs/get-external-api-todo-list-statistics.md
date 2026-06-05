## Description

* 전체 또는 필터링된 Todo 의 상태별 집계를 반환합니다.
* 삭제된 항목은 집계에서 제외됩니다.
* 상태(`status`)·우선순위(`minPriority`) 파라미터로 집계 대상을 좁힐 수 있으며, 미입력 시 전체 항목을 대상으로 집계합니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/statistics |
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
| status | N | TodoStatus | - | 필터링할 상태값 (`TODO` / `IN_PROGRESS` / `DONE`), 미입력 시 전체 |
| minPriority | N | Integer | - | 이 값 이상의 우선순위를 가진 항목만 집계 (1~5 권장), 미입력 시 전체 |

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
| total | Y | Long | 조건에 해당하는 전체 Todo 수 |  |
| done | Y | Long | 상태가 `DONE` 인 Todo 수 |  |
| pending | Y | Long | 상태가 `DONE` 이 아닌 Todo 수 | `total - done` |

### Example

```json
{
  "header": {
    "code": "success",
    "message": "success",
    "requestId": "bill-api-b15f7e69-d68b-4704-95b4-2f45543c384a"
  },
  "body": {
    "total": 15,
    "done": 6,
    "pending": 9
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
