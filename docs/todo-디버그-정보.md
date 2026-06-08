<!-- scope: private -->
# Todo 디버그 정보

## Description

* 서비스 내부 진단용. 전체 Todo 건수와 서비스 식별자를 반환합니다.
* 외부 사용자에게 노출되지 않는 endpoint 입니다 (URL 은 `/external/` 이지만 운영자만 호출).

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/debug/info |
| Method | `GET` |
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
| totalCount | Y | Long | 전체 Todo 건수 |
| service | Y | String | 서비스 식별자 |

### Example

```json
{
  "header": { "code": "success", "message": "success", "requestId": "bill-api-..." },
  "body": { "totalCount": 15, "service": "todo-service" }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
