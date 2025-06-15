CREATE TABLE todos (
	todo_date_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	date TIMESTAMP(0) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE refresh_tokens (
	refresh_token_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	token VARCHAR(255) NOT NULL,
	expired_at TIMESTAMP(0) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);

CREATE TABLE problem_solving (
	problem_solving_id BIGINT NOT NULL,
	problem_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	problem_id2 BIGINT NOT NULL,
	taken_time INTEGER NULL,
	submit_answer INTEGER NOT NULL,
	is_correct BOOLEAN NULL,
	date TIMESTAMP(0) NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE daily_challenge_ranking (
	challenge_point_history_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	rank_level INTEGER NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL,
	rank_date DATE NULL
);

CREATE TABLE notification (
	notification_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	title VARCHAR(255) NULL,
	contents VARCHAR(255) NULL,
	is_confirmed BOOLEAN DEFAULT FALSE NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);

CREATE TABLE todo_item (
	todo_item_id BIGINT NOT NULL,
	todo_date_id BIGINT NOT NULL,
	title VARCHAR(255) NULL,
	importance INTEGER NULL,
	time_priority INTEGER DEFAULT FALSE NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE mentors (
	mentor_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	company VARCHAR(255) NULL,
	position VARCHAR(255) NULL,
	description VARCHAR(255) NULL,
	avail_weekdays VARCHAR(255) NULL,
	completion_date TIMESTAMP(0) NULL,
	is_deleted BOOLEAN NULL
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

CREATE TABLE members (
	member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	id VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	member_name VARCHAR(255) NULL,
	email VARCHAR(255) NULL,
	phone_number VARCHAR(255) NOT NULL,
	nickname VARCHAR(255) NOT NULL,
	role VARCHAR(255) DEFAULT 'TRAINEE' NOT NULL,
	status VARCHAR(255) DEFAULT 'ACTIVE' NOT NULL,
	total_xp INTEGER DEFAULT 0 NOT NULL,
	usable_points INTEGER DEFAULT 0 NOT NULL,
	current_level INTEGER DEFAULT 1 NOT NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
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

CREATE TABLE mentorings_reservation (
	reservation_id BIGINT NOT NULL,
	mentor_id BIGINT NOT NULL,
	mentee_id BIGINT NOT NULL,
	Introduction TEXT NOT NULL,
	subject VARCHAR(255) NOT NULL,
	status INTEGER DEFAULT 'PROGRESS' NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE mentorings (
	mentoring_id BIGINT NOT NULL,
	mentor_id3 BIGINT NOT NULL,
	mentee_id BIGINT NOT NULL,
	reservation_id BIGINT NOT NULL,
	date TIMESTAMP(0) NOT NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE level_configs (
	level INTEGER NOT NULL,
	required_xp INTEGER NULL,
	level_name VARCHAR(255) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
);

CREATE TABLE study_diary (
	diary_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	contents VARCHAR(255) NULL,
	title VARCHAR(255) NULL,
	subject VARCHAR(255) NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	AI_summary TEXT NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE challenge_point_histories (
	challenge_point_history_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	problem_solving_id BIGINT NULL,
	points_earned INTEGER NULL,
	action_type INTEGER NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE member_badges (
	member_badge_id BIGINT NOT NULL,
	member_id BIGINT NOT NULL,
	badge_id BIGINT NOT NULL,
	is_equipped BOOLEAN DEFAULT FALSE NULL,
	unlocked_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	is_deleted BOOLEAN NULL
);

CREATE TABLE difficulty_configs (
	difficulty_config_id BIGINT NOT NULL,
	difficulty INTEGER NOT NULL,
	challenge_points INTEGER NOT NULL,
	display_name VARCHAR(50) NULL,
	is_active BOOLEAN DEFAULT TRUE NULL,
	created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP NULL
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

ALTER TABLE todos ADD CONSTRAINT PK_TODOS PRIMARY KEY (todo_date_id);
ALTER TABLE refresh_tokens ADD CONSTRAINT PK_REFRESH_TOKENS PRIMARY KEY (refresh_token_id);
ALTER TABLE problem_solving ADD CONSTRAINT PK_PROBLEM_SOLVING PRIMARY KEY (problem_solving_id);
ALTER TABLE daily_challenge_ranking ADD CONSTRAINT PK_DAILY_CHALLENGE_RANKING PRIMARY KEY (challenge_point_history_id);
ALTER TABLE notification ADD CONSTRAINT PK_NOTIFICATION PRIMARY KEY (notification_id);
ALTER TABLE todo_item ADD CONSTRAINT PK_TODO_ITEM PRIMARY KEY (todo_item_id, todo_date_id);
ALTER TABLE mentors ADD CONSTRAINT PK_MENTORS PRIMARY KEY (mentor_id);
ALTER TABLE xp_histories ADD CONSTRAINT PK_XP_HISTORIES PRIMARY KEY (xp_history_id);
ALTER TABLE badges ADD CONSTRAINT PK_BADGES PRIMARY KEY (badge_id);
ALTER TABLE mentorings_reservation ADD CONSTRAINT PK_MENTORINGS_RESERVATION PRIMARY KEY (reservation_id);
ALTER TABLE mentorings ADD CONSTRAINT PK_MENTORINGS PRIMARY KEY (mentoring_id);
ALTER TABLE level_configs ADD CONSTRAINT PK_LEVEL_CONFIGS PRIMARY KEY (level);
ALTER TABLE study_diary ADD CONSTRAINT PK_STUDY_DIARY PRIMARY KEY (diary_id);
ALTER TABLE challenge_point_histories ADD CONSTRAINT PK_CHALLENGE_POINT_HISTORIES PRIMARY KEY (challenge_point_history_id);
ALTER TABLE member_badges ADD CONSTRAINT PK_MEMBER_BADGES PRIMARY KEY (member_badge_id);
ALTER TABLE difficulty_configs ADD CONSTRAINT PK_DIFFICULTY_CONFIGS PRIMARY KEY (difficulty_config_id);

ALTER TABLE todo_item ADD CONSTRAINT FK_todos_TO_todo_item_1 FOREIGN KEY (todo_date_id) REFERENCES todos (todo_date_id);

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
        '2025-06-14');


