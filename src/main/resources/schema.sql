-- 1 DDL

CREATE TABLE BOARD_TBL (
    board_idx SERIAL PRIMARY KEY
);



CREATE TABLE mentorings_reservation (
  reservation_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  mentor_id BIGINT NOT NULL,
  mentee_id BIGINT NOT NULL,
  introduction TEXT NULL,
  date TIMESTAMP(0) NOT NULL,
  created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  is_deleted BOOLEAN,

  cancelReason	VARCHAR(255)		NULL,
  cancelMessage	VARCHAR(255)		NULL,
  rejectReason	VARCHAR(255)		NULL,
  rejectMessage	VARCHAR(255)		NULL,
  etcMessage	VARCHAR(255)		NULL
);

CREATE TABLE mentorings (
  mentoring_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  mentor_id BIGINT NOT NULL,
  mentee_id BIGINT NOT NULL,
  reservation_id BIGINT NOT NULL,
  status VARCHAR(255) NOT NULL,
  created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
  is_deleted BOOLEAN
);