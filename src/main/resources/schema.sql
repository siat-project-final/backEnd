-- #DDL
-- 유저
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
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

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

CREATE TABLE refresh_tokens (
	refresh_token_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	member_id BIGINT NOT NULL,
	token VARCHAR(255) NOT NULL,
	expired_at TIMESTAMP(0) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);

-- 멘토링
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

-- 챌린지
CREATE TABLE problems (
	problem_id	BIGINT	GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	title	VARCHAR(255)    NULL,
	contents	TEXT	NOT NULL,
	type	VARCHAR(255)	NULL,
	difficulty	INTEGER	NULL,
	subject	VARCHAR(255)	NULL,
	is_public	BOOLEAN	DEFAULT TRUE	NULL,
	created_at	TIMESTAMP(0)	DEFAULT CURRENT_TIMESTAMP	NULL,
	updated_at	TIMESTAMP(0)	DEFAULT CURRENT_TIMESTAMP	NULL,
	correct_answer	INTEGER	NOT NULL,
	is_deleted	BOOLEAN	DEFAULT FALSE NOT NULL
);


CREATE TABLE problem_solving (
	problem_solving_id	BIGINT		GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	problem_id	BIGINT		NOT NULL,
	member_id	BIGINT		NOT NULL,
	submit_answer	INTEGER		NOT NULL,
	is_correct	BOOLEAN		NULL,
	points INTEGER		DEFAULT 0 NOT NULL,
	date	TIMESTAMP(0)		NULL,
	is_deleted	BOOLEAN		DEFAULT FALSE NOT NULL,
	CONSTRAINT fk_problem FOREIGN KEY (problem_id) REFERENCES problems (problem_id),
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES members (member_id)

);

CREATE TABLE daily_challenge_rankings (
	daily_ranking_id	BIGINT		GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	member_id	BIGINT		NOT NULL,
	rank_level	INTEGER		NULL,
	updated_at	TIMESTAMP(0)	DEFAULT CURRENT_TIMESTAMP	NULL,
	is_deleted	BOOLEAN		DEFAULT FALSE NOT NULL,
	points	INTEGER		NULL,
	date	TIMESTAMP(0)		NOT NULL
);

-- 학습일지
CREATE TABLE study_diary (
    diary_id    BIGINT      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    member_id   BIGINT      NOT NULL,
    contents    VARCHAR(255),
    title       VARCHAR(255),
    subject     VARCHAR(255),
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    study_date  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    ai_summary  TEXT,
    is_deleted  BOOLEAN DEFAULT FALSE,
    is_public   BOOLEAN,
    likes       INTEGER
);

CREATE TABLE diary_comments (
    comment_id   BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    diary_id     BIGINT       NOT NULL,
    member_id    BIGINT       NOT NULL,
    contents     VARCHAR(255),
    created_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    is_deleted   BOOLEAN DEFAULT FALSE
);

-- 기존

CREATE TABLE todos (
	todo_date_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	date TIMESTAMP(0) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);


CREATE TABLE notification (
	notification_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	title VARCHAR(255) NULL,
	contents VARCHAR(255) NULL,
	is_confirmed BOOLEAN DEFAULT FALSE NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);


CREATE TABLE badges (
	badge_id BIGINT NOT NULL,
	name VARCHAR(255) NULL,
	description TEXT NULL,
	icon_url VARCHAR(255) NULL,
	unlock_condition TEXT NULL,
	required_level INTEGER NULL,
	is_active BOOLEAN DEFAULT TRUE NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);

CREATE TABLE member_badges (
	member_badge_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	badge_id BIGINT NOT NULL,
	is_equipped BOOLEAN DEFAULT FALSE NULL,
	unlocked_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE students (
	student_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	student_name VARCHAR(255) NOT NULL,
	phone_number VARCHAR(255) NOT NULL
);

CREATE TABLE daily_learning (
	learning_id	BIGINT	GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	contents	VARCHAR(255)		NULL,
	title	VARCHAR(255)		NULL,
	subject	VARCHAR(255)		NULL,
	date	DATE	NOT NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);


-- # DML

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

INSERT INTO students (student_name, phone_number)
VALUES
    ('김철수', '01012345678'),
    ('이영희', '01087654321');

INSERT INTO daily_learning (contents, title, subject, date)
VALUES
('Java의 기본 데이터 타입에 대해 학습했습니다. int, char, boolean 등의 특징을 익혔습니다.',
        'Java 기본 데이터 타입',
        'JavaScript',
        '2025-06-15'),
('Python의 기본 데이터 타입에 대해 학습했습니다. int, char, boolean 등의 특징을 익혔습니다.',
        'Python 기본 데이터 타입',
        'Python',
        '2025-06-14'),
('Python의 기본 데이터 타입에 대해 학습했습니다. int, char, boolean 등의 특징을 익혔습니다.',
        'Python 기본 데이터 타입',
        'React.js',
        '2025-06-16');

-- 멘토링
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

-- 챌린지
INSERT INTO problems (
            title,
            contents,
            difficulty,
            subject,
            correct_answer
) VALUES (
    'React.js: 1',
    'React.js는 무엇인가요?',
    1,
    'React.js',
    1
), (
    'React.js: 2',
    'React.js는 무엇인가요?',
    2,
    'React.js',
    2
), (
    'React.js: 3',
    'React.js는 무엇인가요?',
    3,
    'React.js',
    3
), (
    'React.js: 4',
    'React.js는 무엇인가요?',
    4,
    'React.js',
    4
), (
    'React.js: 5',
    'React.js는 무엇인가요?',
    5,
    'React.js',
    5
);

INSERT INTO problem_solving
(
    problem_id,
    member_id,
    submit_answer,
    is_correct,
    points,
    date
)
VALUES
    (
        1,
        1,
        1,
        'Y',
        1,
        NOW()
    )
 ,
    (
        2,
        1,
        2,
        'Y',
        2,
        NOW()
    )
 ,
    (
        3,
        1,
        1,
        'N',
        0,
        NOW()
    )
 ,
    (
        4,
        1,
        4,
        'Y',
        4,
        NOW()
    )
 ,
    (
        5,
        1,
        5,
        'Y',
        5,
        NOW()
    ),(
          1,
          2,
          1,
          'Y',
          1,
          NOW()
      ),
      (
          2,
          2,
          2,
          'Y',
          2,
          NOW()
      ),
      (
          3,
          2,
          1,
          'N',
          0,
          NOW()
      ),
      (
          4,
          2,
          4,
          'Y',
          4,
          NOW()
      ),
      (
          5,
          2,
          5,
          'Y',
          4,
          NOW()
      );

INSERT INTO study_diary
(
    member_id,
    contents,
    title,
    subject,
    created_at,
    updated_at,
    study_date,
    ai_summary,
    is_deleted,
    is_public,
    likes
) VALUES
      (1, 'Java와 Spring Boot 기본 개념 정리', 'Java 스프링 입문', 'Java', NOW(), NOW(), '2025-06-19 10:00:00', '스프링 부트의 핵심 개념 요약', FALSE, TRUE, 5),
      (2, '데이터베이스 트랜잭션과 인덱스 이해', 'DB 트랜잭션 공부', 'Database', NOW(), NOW(), '2025-06-18 14:30:00', '트랜잭션의 중요성과 인덱스 활용법 요약', FALSE, FALSE, 3),
      (3, 'REST API 설계 원칙', 'REST API 설계', 'API', NOW(), NOW(), '2025-06-17 09:00:00', 'REST API의 기본 설계 원칙 설명', FALSE, TRUE, 10),
      (1, 'JPA와 Hibernate 매핑 이해', 'JPA 매핑', 'Java', NOW(), NOW(), '2025-06-16 16:00:00', '엔티티 매핑과 연관관계 설명', FALSE, TRUE, 7),
      (4, 'AWS EC2 인스턴스 생성 및 설정', 'AWS 입문', 'Cloud', NOW(), NOW(), '2025-06-15 11:00:00', 'AWS EC2 사용법과 설정 요약', FALSE, FALSE, 1);


