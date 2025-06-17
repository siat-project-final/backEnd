-- 1 DDL

CREATE TABLE mentors (
  mentor_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  company VARCHAR(255),
  position VARCHAR(255),
  description VARCHAR(255),
  avail_weekdays VARCHAR(255),
  completion_date TIMESTAMP(0),
  open_chat_url VARCHAR(255),
  is_deleted BOOLEAN,
  created_at TIMESTAMP(0),
  updated_at TIMESTAMP(0),
  mentor_image_url VARCHAR(255),
  mentor_name VARCHAR(255)
);

CREATE TABLE members (
    member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    member_name VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    phone_number VARCHAR(255) NULL,
    nickname VARCHAR(255) NOT NULL,
    role VARCHAR(255) DEFAULT 'TRAINEE' NOT NULL,
    status VARCHAR(255) DEFAULT 'ACTIVE' NOT NULL,
    total_xp INTEGER NULL,
    usable_points INTEGER NOT NULL,
    current_level INTEGER DEFAULT 1 NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    is_deleted BOOLEAN NULL
);




CREATE TABLE mentorings_reservation (
  reservation_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  mentor_id BIGINT NOT NULL,
  mentee_id BIGINT NOT NULL,
  introduction TEXT NULL,
  date TIMESTAMP(0) NOT NULL,
  subject VARCHAR(255) DEFAULT NULL,
  status VARCHAR(255) DEFAULT 'PENDING' NOT NULL,
  created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  is_deleted BOOLEAN DEFAULT FALSE NOT NULL,

  cancel_reason	VARCHAR(255)		NULL,
  reject_reason	VARCHAR(255)		NULL
);

CREATE TABLE mentorings (
  mentoring_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  mentor_id BIGINT NOT NULL,
  mentee_id BIGINT NOT NULL,
  reservation_id BIGINT NOT NULL,
  status VARCHAR(255) NOT NULL,
  created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

INSERT INTO members (
    id, password, member_name, email, phone_number, nickname, role, status,
    total_xp, usable_points, current_level, created_at, updated_at, is_deleted
) VALUES
(
    'user001', 'password123', '홍길동', 'hong@example.com', '010-1234-5678', '길동이',
    'TRAINEE', 'ACTIVE', 500, 100, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
),
(
    'user002', 'securepass456', '김철수', 'chulsoo@example.com', '010-2345-6789', '철수짱',
    'TRAINEE', 'ACTIVE', 1200, 300, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
),
(
    'user', 'mysecurepwd', '이영희', 'younghee@example.com', '010-3456-7890', '영희',
    'MENTOR', 'ACTIVE', 3000, 500, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
);

INSERT INTO mentors (
    company, position, description, avail_weekdays,
    completion_date, open_chat_url, is_deleted,
    created_at, updated_at, mentor_image_url, mentor_name
) VALUES
(
    'Kakao',
    'Lead Software Engineer',
    'React와 Spring Boot 연동, MSA 아키텍처 설계 전문가입니다.',
    'MON,WED,FRI',
    '2025-11-30 23:59:59',
    'https://open.kakao.com/o/kakaodev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'https://example.com/profile/mentor2.jpg',
    '이프론트'
),
(
    'Naver',
    'Principal Engineer',
    'QueryDSL과 JPA 성능 최적화, 대용량 데이터 처리 경험을 공유합니다.',
    'TUE,THU,SAT',
    '2026-01-31 23:59:59',
    'https://open.kakao.com/o/naverdev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'https://example.com/profile/mentor3.jpg',
    '박데이터'
),
(
    'Coupang',
    'Staff Software Engineer',
    'Docker와 Kubernetes를 활용한 CI/CD 파이프라인 구축 전문가입니다.',
    'MON,TUE,THU,FRI',
    '2025-10-31 23:59:59',
    'https://open.kakao.com/o/coupangdev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'https://example.com/profile/mentor4.jpg',
    '최데브옵스'
);

INSERT INTO mentorings_reservation (
    mentor_id,
    mentee_id,
    introduction,
    subject,
    date
)
VALUES
(
   1,
   1,
   'Spring',
   'Spring Boot JPA',
   '2025-06-20T14:30'
);

INSERT INTO mentorings (
    mentor_id,
    mentee_id,
    reservation_id,
    status,
    created_at
) VALUES (
    '1',
    '1',
    1,
    'COMPLETED',
    NOW()
);

