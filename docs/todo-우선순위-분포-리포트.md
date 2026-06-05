## Description

* 전체 Todo 의 우선순위별 분포를 반환합니다.
* 리포팅·통계 도메인의 API 로, 일반 Todo API 와 다른 도메인을 사용합니다.
* 우선순위가 `null` 인 항목은 집계에서 제외됩니다.

## ACL

* ACL 요청 필요 - (확인 필요)

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /api/reports/todos/priority-distribution |
| Method | `GET` |
| Content-Type | `application/json` |

**Domain**

(리포트 그룹 — `com.example.review.report` 패키지 매칭)

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

## Response

### Header

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| code | Y | String | 결과 코드 |
| message | Y | String | 응답 메시지 |
| requestId | Y | String | 로그 추적용 |

### Body

body 는 `Map<Integer, Long>` — key 가 우선순위, value 가 해당 우선순위의 Todo 건수.

| 필드명 | 필수여부 | 타입 | 설명 |
| --- | ---- | --- | --- |
| body.{priority} | Y | Long | priority(Integer) 별 Todo 건수 |

### Example

```json
{
  "header": { "code": "success", "message": "success", "requestId": "bill-api-..." },
  "body": { "1": 2, "3": 5, "5": 1 }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
