-- 1 DDL

CREATE TABLE BOARD_TBL (
    board_idx SERIAL PRIMARY KEY
);

-- TodoDate (날짜별 할 일 그룹) 테이블
CREATE TABLE IF NOT EXISTS todo_dates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_value DATE NOT NULL UNIQUE
);

-- TodoItem (개별 할 일 항목) 테이블
CREATE TABLE IF NOT EXISTS todo_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo_date_id BIGINT,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (todo_date_id) REFERENCES todo_dates(id) ON DELETE CASCADE
);