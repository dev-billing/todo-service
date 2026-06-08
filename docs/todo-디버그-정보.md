<!-- scope: private -->
# Todo 디버그 정보

## Description

* 서비스 내부 디버그용 정보(전체 Todo 건수, 서비스명)를 조회합니다.
* 내부(private) 용도의 엔드포인트입니다.

## API Info

| 항목 | 값 |
| --- | --- |
| Path | /external/api/todo-list/debug/info |
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
| totalCount | Y | Long | 전체 Todo 건수 | |
| service | Y | String | 서비스명 | 고정값 `todo-service` |

### Example

```json
{
  "header": {
    "code": "success",
    "message": "success",
    "requestId": "bill-api-0000000000000000"
  },
  "body": {
    "totalCount": 10,
    "service": "todo-service"
  }
}
```

## Error code

* 공통 에러 코드 : https://nhnent.dooray.com/share/pages/WIcRkRY9RdSwwP9_l5OskA/3657213124062842835
