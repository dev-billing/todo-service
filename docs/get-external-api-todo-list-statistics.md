# Todo 통계 조회

## Description

* 조건에 맞는 Todo 항목의 전체/완료/미완료 건수 통계를 조회합니다.
* `status`, `minPriority` 로 집계 대상을 필터링할 수 있습니다.

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/statistics |
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

### Parameters

| 파라미터 | 필수여부 | 타입 | 기본값 | 설명 |
| --- | ---- | --- | --- | --- |
| status | N | String | | 상태 필터 (TODO / IN_PROGRESS / DONE) |
| minPriority | N | Integer | | 최소 우선순위 필터 |
| includeDone | N | Boolean | true | 완료 항목 포함 여부 |

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
| total | Y | Long | 전체 건수 | |
| done | Y | Long | 완료 건수 | |
| pending | Y | Long | 미완료 건수 | |

### Example

```json
{
  "header": {
    "code": "success",
    "message": "success",
    "requestId": "bill-api-0000000000000000"
  },
  "body": {
    "total": 10,
    "done": 4,
    "pending": 6
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
