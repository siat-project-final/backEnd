-- 1 DDL

CREATE TABLE BOARD_TBL (
    board_idx SERIAL PRIMARY KEY
);
-- 기존 테이블들을 삭제 (개발 환경에서만 사용, 실제 운영에서는 데이터 마이그레이션 필요)
DROP TABLE IF EXISTS todos;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS todo_items; -- 이전 이름 사용 시 삭제
DROP TABLE IF EXISTS todo_dates; -- 이전 이름 사용 시 삭제

-- members 테이블 생성
CREATE TABLE IF NOT EXISTS members (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(255) NOT NULL UNIQUE, -- 로그인 ID (중복 불가)
    password VARCHAR(255) NOT NULL,
    member_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE, -- 이메일 (중복 불가)
    phone VARCHAR(255),
    nickname VARCHAR(255) UNIQUE, -- 닉네임 (중복 불가)
    role VARCHAR(50) DEFAULT 'USER' NOT NULL, -- 사용자 권한 (예: USER, ADMIN)
    status VARCHAR(50) DEFAULT 'ACTIVE' NOT NULL, -- 사용자 상태 (예: ACTIVE, INACTIVE)
    total_xp INTEGER DEFAULT 0,
    usable_points INTEGER DEFAULT 0,
    current_level INTEGER DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL -- 논리적 삭제 여부
);

-- todos 테이블 생성
CREATE TABLE IF NOT EXISTS todos (
    todo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL, -- ★ 외래 키: 어떤 멤버의 할 일인지 명시
    contents VARCHAR(255) NOT NULL,
    date TIMESTAMP(0) NOT NULL, -- 날짜 (시간 정보 포함 가능)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_checked BOOLEAN DEFAULT FALSE NOT NULL, -- 할 일 완료 여부 (completed -> is_checked)
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL, -- 논리적 삭제 여부

    -- ★ 외래 키 제약 조건 추가: members 테이블의 member_id 참조
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE
);

-- 개발/테스트를 위한 샘플 데이터 (선택 사항이지만 테스트에 매우 유용)
INSERT INTO members (id, password, member_name, email, phone, nickname) VALUES
('user1', 'hashed_password_1', '김철수', 'user1@example.com', '010-1111-2222', '철수');

INSERT INTO members (id, password, member_name, email, phone, nickname) VALUES
('user2', 'hashed_password_2', '이영희', 'user2@example.com', '010-3333-4444', '영희');

INSERT INTO todos (member_id, contents, date, is_checked) VALUES
(1, '장보기', '2025-06-15 10:00:00', FALSE),
(1, '보고서 작성', '2025-06-16 14:30:00', FALSE),
(2, '운동하기', '2025-06-15 18:00:00', TRUE),
(2, '책 읽기', '2025-06-17 20:00:00', FALSE);