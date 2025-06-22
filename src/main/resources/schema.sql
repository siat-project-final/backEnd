-- SET client_encoding = 'UTF8';

-- #DDL
-- 유저
CREATE TABLE members (
    member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    member_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    nickname VARCHAR(255) NOT NULL,
    role VARCHAR(255) DEFAULT 'TRAINEE' NOT NULL,
    status VARCHAR(255) DEFAULT 'ACTIVE' NOT NULL,
    total_xp INTEGER,
    usable_points INTEGER NOT NULL,
    current_level INTEGER DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
    expired_at TIMESTAMP(0),
    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_refresh_member FOREIGN KEY (member_id) REFERENCES members(member_id)
);

-- 멘토링
CREATE TABLE mentorings_reservation (
  reservation_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  mentor_id BIGINT NOT NULL,
  member_id BIGINT NOT NULL,
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
  member_id BIGINT NOT NULL,
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
	is_deleted	BOOLEAN	DEFAULT FALSE	NULL
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
	date	DATE		NOT NULL
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

-- CREATE TABLE todos (
-- 	todo_date_id BIGINT NOT NULL,
-- 	member_id BIGINT NOT NULL,
-- 	date TIMESTAMP(0) NULL,
-- 	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
-- 	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
-- 	is_deleted BOOLEAN DEFAULT FALSE NOT NULL
--                        todo_id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--                        member_id   BIGINT NOT NULL,
--                        contents    VARCHAR(255) NOT NULL,
--                        date        DATE,
--                        is_checked  BOOLEAN DEFAULT FALSE,
--                        created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--                        updated_at  TIMESTAMP,
--                        is_deleted  BOOLEAN DEFAULT FALSE
-- );

CREATE TABLE todos (
                       todo_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       todo_date_id BIGINT NOT NULL,
                       member_id BIGINT NOT NULL,
                       contents VARCHAR(255) NOT NULL,
                       date DATE,
                       is_checked BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
                       updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
                       is_deleted BOOLEAN DEFAULT FALSE
);


-- CREATE TABLE todo_item (
--                            todo_item_id BIGINT NOT NULL,
--                            todo_date_id BIGINT NOT NULL,
--                            title VARCHAR(255) NULL,
--                            importance INTEGER NULL,
--                            time_priority INTEGER DEFAULT FALSE NULL,
--                            created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
--                            updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
--                            is_deleted BOOLEAN NULL
-- );

CREATE TABLE notification (
	notification_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	title VARCHAR(255) NULL,
	contents VARCHAR(255) NULL,
	is_confirmed BOOLEAN DEFAULT FALSE NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);



CREATE TABLE xp_histories (
	xp_history_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	activity_type INTEGER NULL,
	xp_amount INTEGER NULL,
	reference_id BIGINT NULL,
	description TEXT NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
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

CREATE TABLE level_configs (
	level INTEGER NOT NULL,
	required_xp INTEGER NULL,
	level_name VARCHAR(255) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);

CREATE TABLE member_badges (
	member_badge_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	badge_id BIGINT NOT NULL,
	is_equipped BOOLEAN DEFAULT FALSE NULL,
	unlocked_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
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
-- CREATE TABLE members (
--     member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--     login_id VARCHAR(255) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     member_name VARCHAR(255),
--     email VARCHAR(255),
--     phone_number VARCHAR(255),
--     nickname VARCHAR(255) NOT NULL,
--     role VARCHAR(255) DEFAULT 'TRAINEE' NOT NULL,
--     status VARCHAR(255) DEFAULT 'ACTIVE' NOT NULL,
--     total_xp INTEGER,
--     usable_points INTEGER NOT NULL,
--     current_level INTEGER DEFAULT 1,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     is_deleted BOOLEAN DEFAULT FALSE NOT NULL
-- );

INSERT INTO members (
    login_id,
    password,
    member_name,
    email,
    phone_number,
    nickname,
    role,
    status,
    is_deleted,
    created_at,
    updated_at,
    usable_points,
    current_level,
    total_xp
) VALUES
      (
          'admin',
          '1234',
          '이수현',
          'hong@example.com',
          '010-1234-5678',
          '길동이',
          'TRAINEE',
          'ACTIVE',
          FALSE,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          100,
          2,
          500
      ),
      (
          'mentor',
          'mentor1234',
          '이수현',
          'chulsoo@example.com',
          '010-2345-6789',
          '철수짱',
          'TRAINEE',
          'ACTIVE',
          FALSE,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          300,
          4,
          1200
      ),
      (
          'mentee',
          'mentee1234',
          '이현',
          'younghee@example.com',
          '010-3456-7890',
          '영희',
          'MENTOR',
          'ACTIVE',
          FALSE,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP,
          500,
          6,
          3000
      );


-- INSERT INTO mentorings_reservation (mentor_id, member_id, introduction, subject, date, status)
-- VALUES
--     (2, 1, '안녕하세요, 프론트엔드 배우고 싶어요!', '프론트엔드', '2025-07-01 14:00:00', 'WAITING'),
--     (3, 1, '자바 백엔드 관련 멘토링 신청합니다.', '백엔드', '2025-07-02 15:30:00', 'WAITING'),
--     (4, 1, 'AI 쪽 진로가 고민입니다.', 'AI/데이터', '2025-07-03 11:00:00', 'WAITING'),
--     (5, 1, '컴퓨터공학 진로 멘토링 요청드립니다.', '진로상담', '2025-07-05 09:00:00', 'WAITING'),
--     (2, 1, 'React 중급 이상 내용 멘토링 부탁드려요.', 'React', '2025-07-06 20:00:00', 'WAITING');


INSERT INTO mentors (
    company, position, description, avail_weekdays,
    completion_date, open_chat_url, is_deleted,
    created_at, updated_at, mentor_image_url, mentor_name
) VALUES
-- -- 1
-- (
--     'Kakao',
--     'Lead Software Engineer',
--     'React와 Spring Boot 연동, MSA 아키텍처 설계 전문가입니다.',
--     'MON,WED,FRI',
--     '2025-11-30 23:59:59',
--     'https://open.kakao.com/o/kakaodev',
--     false,
--     CURRENT_TIMESTAMP,
--     CURRENT_TIMESTAMP,
--     'https://example.com/profile/mentor2.jpg',
--     '이프론트'
-- ),
-- -- 2
-- (
--     'Naver',
--     'Principal Engineer',
--     'QueryDSL과 JPA 성능 최적화, 대용량 데이터 처리 경험을 공유합니다.',
--     'TUE,THU,SAT',
--     '2026-01-31 23:59:59',
--     'https://open.kakao.com/o/naverdev',
--     false,
--     CURRENT_TIMESTAMP,
--     CURRENT_TIMESTAMP,
--     'https://example.com/profile/mentor3.jpg',
--     '박데이터'
-- ),
-- -- 3
-- (
--     'Coupang',
--     'Staff Software Engineer',
--     'Docker와 Kubernetes를 활용한 CI/CD 파이프라인 구축 전문가입니다.',
--     'MON,TUE,THU,FRI',
--     '2025-10-31 23:59:59',
--     'https://open.kakao.com/o/coupangdev',
--     false,
--     CURRENT_TIMESTAMP,
--     CURRENT_TIMESTAMP,
--     'https://example.com/profile/mentor4.jpg',
--     '최데브옵스'
-- ),
-- 4
(
    'SK 쉴더스',
    'Business Analyst',
    'Business Analyst @ SK 쉴더스',
    'MON,TUE,WED,THU,FRI',
    '2025-12-31 23:59:59',
    'https://open.kakao.com/o/walterdev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor1.jpg',
    'Walter White'
),
-- 5
(
    'SK C&C',
    'Software Engineer',
    'Software Engineer @ SK C&C',
    'MON,TUE,WED,THU,FRI',
    '2025-12-31 23:59:59',
    'https://open.kakao.com/o/sarahdev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor2.jpg',
    'Sarah Jhonson'
),
-- 6
(
    'AWS',
    'Cloud Engineer',
    'Cloud Engineer @ AWS',
    'TUE,THU,SAT',
    '2026-01-15 23:59:59',
    'https://open.kakao.com/o/sarahdev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor3.jpg',
    'William Anderson'
),
-- 7
(
    'TVING',
    'Software Developer',
    'Frontend 개발자, UI/UX 설계 중심',
    'WED,FRI,SAT',
    '2025-10-10 23:59:59',
    'https://open.kakao.com/o/sarahdev',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'https://example.com/profile/mentor1.jpg',
    'Amanda Jepson'
),
-- 8
(
    'MEGAZONE CLOUD',
    'Software Developer',
    '클라우드 기반 백엔드 서비스 구축 경험 다수',
    'MON,TUE,THU',
    '2025-09-30 23:59:59',
    'https://open.kakao.com/o/briandoe',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor2.jpg',
    'Brian Doe'
),
-- 9
(
    'AWS',
    'Cloud Engineer',
    'AWS S3, EC2, Lambda 실무 경험자',
    'TUE,THU',
    '2025-12-15 23:59:59',
    'https://open.kakao.com/o/josephaws',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor3.jpg',
    'Josepha Palas'
),
-- 10
(
    'Google',
    'Data Scientist',
    'AI 모델 학습 및 분석, 파이썬과 텐서플로우 활용',
    'WED,THU,FRI',
    '2025-11-01 23:59:59',
    'https://open.kakao.com/o/emilygoogle',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor1.jpg',
    'Emily Chen'
),
-- 11
(
    'Naver',
    'AI Engineer',
    '자연어처리, 추천 시스템 경험 다수',
    'MON,WED,FRI',
    '2025-12-25 23:59:59',
    'https://open.kakao.com/o/michaelnaver',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor2.jpg',
    'Michael Park'
),
-- 12
(
    'Kakao',
    'Frontend Developer',
    '웹 퍼포먼스 최적화, React 고급 기술 강의 경험',
    'TUE,THU,SAT',
    '2026-02-01 23:59:59',
    'https://open.kakao.com/o/sophiekakao',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor3.jpg',
    'Sophie Kim'
),
-- 13
(
    'LINE',
    'Backend Developer',
    'Node.js, Java 기반 서비스 운영 담당',
    'MON,THU',
    '2025-10-20 23:59:59',
    'https://open.kakao.com/o/davidline',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor1.jpg',
    'David Lee'
),
-- 14
(
    'Coupang',
    'DevOps Engineer',
    'CI/CD, 모니터링, 인프라 자동화 관련 경력',
    'WED,FRI',
    '2025-12-12 23:59:59',
    'https://open.kakao.com/o/lisacoupang',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor2.jpg',
    'Lisa Wang'
),
-- 15
(
    'Kakao Security',
    'Security Engineer',
    '시스템 보안, 인증 프로토콜 및 취약점 분석',
    'TUE,THU',
    '2026-01-01 23:59:59',
    'https://open.kakao.com/o/jamessecurity',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    '/assets/img/mentors/mentor3.jpg',
    'James Kim'
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
        'JAVA',
        '2025-06-20'),
('Python의 기본 데이터 타입에 대해 학습했습니다. int, char, boolean 등의 특징을 익혔습니다.',
        'Python 기본 데이터 타입',
        'React.js',
        '2025-06-16');

-- -- 멘토링
-- INSERT INTO mentorings_reservation (
--     mentor_id,
--     member_id,
--     introduction,
--     subject,
--     date
-- )
-- VALUES
-- (
--    1,
--    1,
--    'Spring',
--    'Spring Boot JPA',
--    '2025-06-20T14:30'
-- );

INSERT INTO mentorings (
    mentor_id,
    member_id,
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

INSERT INTO daily_challenge_rankings
(
member_id,
rank_level,
points,
date
  )
VALUES
(1, 1, 15, CAST('2025-06-16' AS DATE)),
(2, 2, 14, CAST('2025-06-16' AS DATE)),
(3, 3, 13, CAST('2025-06-16' AS DATE)),
(1, 1, 15, CAST('2025-06-18' AS DATE)),
(2, 2, 12, CAST('2025-06-18' AS DATE)),
(1, 1, 15, CAST('2025-06-17' AS DATE)),
(2, 2, 14, CAST('2025-06-17' AS DATE));

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

INSERT INTO diary_comments (diary_id, member_id, contents)
VALUES
    (1, 1, '첫 번째 댓글입니다.'),
    (1, 1, '두 번째 댓글, 좋은 글이네요!'),
    (2, 1, '질문이 있습니다. 설명 부탁드려요.'),
    (3, 1, '감사합니다. 많은 도움이 됐어요.'),
    (2, 1, '흥미로운 내용이네요. 잘 읽었습니다.');

