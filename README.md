# ❇️ 나만의 일정 관리 앱 서버 만들기
## ✅ API 명세서
<img width="744" alt="스크린샷 2024-08-16 오전 9 30 32" src="https://github.com/user-attachments/assets/f0e5e1d6-576d-4d79-9eef-fb13d2c0e8ee">

### 1. 일정 생성
새로운 일정을 등록한다.

경로: /api/events

메서드: POST

Request Body

    {
    "title": "팀 회의",
    "manager": "김지훈",
    "startTime": "2024-08-20T10:00:00",
    "endTime": "2024-08-20T12:00:00",
    "password": "password123"
    }

Response Body

    {
    "id": 1,
    "title": "팀 회의",
    "manager": "김지훈",
    "startTime": "2024-08-20T10:00:00",
    "endTime": "2024-08-20T12:00:00",
    "createdAt": "2024-08-20T09:00:00",
    "updatedAt": "2024-08-20T09:00:00"
    }

상태코드

201 Created: 일정이 성공적으로 생성됨

400 Bad Request: 잘못된 요청 형식

### 2. 일정 목록 조회
등록된 일정 목록을 조회합니다. (수정일 및 담당자명으로 필터링 가능)

경로: /api/events

메서드: GET

### Query Parameter

- pdatedAt (선택적, 형식: YYYY-MM-DD): 수정일로 필터링

- manager (선택적): 담당자명으로 필터링

Response Body

    [
    {
        "id": 1,
        "title": "팀 회의",
        "manager": "김지훈",
        "startTime": "2024-08-20T10:00:00",
        "endTime": "2024-08-20T12:00:00",
        "createdAt": "2024-08-20T09:00:00",
        "updatedAt": "2024-08-20T09:00:00"
    },
    {
        "id": 2,
        "title": "프로젝트 발표",
        "manager": "이수진",
        "startTime": "2024-08-21T14:00:00",
        "endTime": "2024-08-21T15:30:00",
        "createdAt": "2024-08-21T13:00:00",
        "updatedAt": "2024-08-21T13:00:00"
    }
    ]

상태코드

200 OK: 일정 목록이 성공적으로 조회

### 3. 특정 일정 조회
특정 ID를 가진 일정을 조회합니다.

경로: /api/events/{id}

메서드: GET

Response Body

    {
    "id": 1,
    "title": "팀 회의",
    "manager": "김지훈",
    "startTime": "2024-08-20T10:00:00",
    "endTime": "2024-08-20T12:00:00",
    "createdAt": "2024-08-20T09:00:00",
    "updatedAt": "2024-08-20T09:00:00"
    }

상태코드

200 OK: 일정이 성공적으로 조회됨

404 Not Found: 지정된 ID의 일정이 없음

### 4. 일정 수정
특정 ID를 가진 일정을 수정합니다. 비밀번호 검증을 포함합니다.

경로: /api/events/{id}

메서드: PUT

Request Body

    {
    "title": "새로운 팀 회의",
    "manager": "김지훈",
    "password": "password123"
    }

Response Body

    {
    "id": 1,
    "title": "새로운 팀 회의",
    "manager": "김지훈",
    "startTime": "2024-08-20T10:00:00",
    "endTime": "2024-08-20T12:00:00",
    "createdAt": "2024-08-20T09:00:00",
    "updatedAt": "2024-08-20T10:00:00"
    }

상태코드

200 OK: 일정이 성공적으로 수정됨

400 Bad Request: 비밀번호 불일치 또는 잘못된 요청 형식

404 Not Found: 지정된 ID의 일정이 없음

### 5. 일정 삭제
특정 ID를 가진 일정을 삭제합니다. 비밀번호 검증을 포함합니다.

경로: /api/events/{id}

메서드: DELETE

### Query Parameter

password: 비밀번호

Response Body

    {
    "message": "일정이 성공적으로 삭제되었습니다."
    }

상태코드

200 OK: 일정이 성공적으로 삭제됨

400 Bad Request: 비밀번호 불일치

404 Not Found: 지정된 ID의 일정이 없음

## ✅ ERD
### ERD 설계
### 엔터티 (Entity)

Event

- id (BIGINT): 고유 식별자

- title (VARCHAR): 일정 제목

- manager (VARCHAR): 담당자명

- start_time (DATETIME): 시작 일시

- end_time (DATETIME): 종료 일시

- password (VARCHAR): 비밀번호

- created_at (DATETIME): 작성일

- updated_at (DATETIME): 수정일

### 관계 (Relationships)

단일 테이블: 현재 Event 테이블만 존재하며, 다른 테이블과의 관계는 없으므로 ERD는 단일 엔터티로 표현됩니다.

### ERD 다이어그램
    +----------------------+
    |        Event         |
    +----------------------+
    | id (BIGINT)          |
    | title (VARCHAR)      |
    | manager (VARCHAR)    |
    | start_time (DATETIME)|
    | end_time (DATETIME)  |
    | password (VARCHAR)   |
    | created_at (DATETIME)|
    | updated_at (DATETIME)|
    +----------------------+
### ERD 설명
Event

- id: 각 일정의 고유 식별자입니다.

- title: 일정을 설명하는 제목입니다.

- manager: 해당 일정을 관리하는 담당자의 이름입니다.

- start_time: 일정의 시작 시간입니다.

- end_time: 일정의 종료 시간입니다.

- password: 일정을 보호하기 위한 비밀번호입니다.

- created_at: 일정이 생성된 시간입니다.

- updated_at: 일정이 마지막으로 수정된 시간입니다.

## 프로젝트 구조
    src/main/java/com/sparta/springfirstys/
    │
    ├── controller/
    │   └── EventController.java
    │
    ├── service/
    │   └── EventService.java
    │
    ├── repository/
    │   └── EventRepository.java
    │
    ├── dto/
    │   ├── EventRequestDto.java
    │   └── EventResponseDto.java
    │
    └── entity/
        └── Event.java

