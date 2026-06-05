## Description

* 특정 상태의 Todo 건수를 일별로 집계한 리포트를 반환합니다.
* 리포팅·통계 도메인의 API 로, 일반 Todo API 와 다른 도메인을 사용합니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /api/reports/todos/daily |
| Method | `GET` |
| Content-Type | `application/json` |

**Domain**

(리포트 그룹 — `com.example.review.report` 패키지 매칭, `_meta.yml` 의 report 그룹 적용)

| 환경 | URL |
| --- | --- |
| 알파 | https://alpha-report.example.com |
| 리얼 | https://report.example.com |

## Request

### Header

| 항목명 | 필수여부 | 타입 | 의미 |
| --- | ---- | --- | --- |
| clientOrigin | Y | String | 호출처(빌링개발팀에 문의) |
| requestId | N | String | 요청 uuid (로그 추적용) |

### Parameters

| 파라미터 | 필수여부 | 타입 | 기본값 | 설명 |
| --- | ---- | --- | --- | --- |
| status | Y | TodoStatus | - | 집계할 상태값 (`TODO` / `IN_PROGRESS` / `DONE`) |

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
| total | Y | Long | 해당 상태의 Todo 전체 건수 |

### Example

```json
{
  "header": { "code": "success", "message": "success", "requestId": "bill-api-..." },
  "body": { "total": 7 }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835

| 코드 | HTTP Status | 설명 |
| --- | ---- | --- |
| INVALID_PARAM | 400 | status 누락 또는 잘못된 값 |
