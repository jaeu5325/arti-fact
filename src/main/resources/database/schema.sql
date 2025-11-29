-- arti_fact 데이터베이스 사용
USE arti_fact;

-- 기존 테이블 삭제 (초기화 순서: 외래키 있는 테이블 → 참조 테이블)
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS liked;
DROP TABLE IF EXISTS art;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS gallery;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS = 1;


-- USERS TABLE
CREATE TABLE users (
    user_id VARCHAR(50) NOT NULL PRIMARY KEY COMMENT 'User ID',
    name VARCHAR(50) NOT NULL COMMENT '사용자 이름',
    pw VARCHAR(255) NOT NULL COMMENT '비밀번호',
    birth_date DATE COMMENT '생년월일',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- GALLERY TABLE
CREATE TABLE gallery (
    gallery_id VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '미술관 ID',
    name VARCHAR(100) NOT NULL COMMENT '미술관 이름',
    address VARCHAR(255) NOT NULL COMMENT '위치/주소',
    open_time VARCHAR(20) COMMENT '운영 시작 시간',
    closed_time VARCHAR(20) COMMENT '운영 종료 시간',
    fee INT DEFAULT 0 COMMENT '입장료'
);


-- ARTIST TABLE
CREATE TABLE artist (
    artist_id VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '화가 ID',
    name VARCHAR(100) NOT NULL COMMENT '화가 이름',
    theme VARCHAR(100) COMMENT '사조/화풍',
    birth_date VARCHAR(20) COMMENT '출생',
    dead_date VARCHAR(20) COMMENT '사망',
    nationality VARCHAR(50) COMMENT '국적',
    info TEXT COMMENT '간단 소개'
);


-- ART TABLE
CREATE TABLE art (
    art_id VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '미술품 ID',
    name VARCHAR(200) NOT NULL COMMENT '작품명',
    age VARCHAR(50) COMMENT '시대',
    genre VARCHAR(50) COMMENT '장르',
    theme VARCHAR(100) COMMENT '사조',
    display BOOLEAN DEFAULT TRUE COMMENT '전시 여부',

    url VARCHAR(500) COMMENT '작품 이미지 URL',

    artist_id VARCHAR(50) COMMENT '화가 ID',
    gallery_id VARCHAR(50) COMMENT '소장처 ID',

    FOREIGN KEY (artist_id) REFERENCES artist(artist_id) ON DELETE SET NULL,
    FOREIGN KEY (gallery_id) REFERENCES gallery(gallery_id) ON DELETE SET NULL
);


-- LIKED TABLE
CREATE TABLE liked (
    liked_id VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '관심목록 ID',
    user_id VARCHAR(50) NOT NULL COMMENT '사용자 ID',
    art_id VARCHAR(50) NOT NULL COMMENT '미술품 ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (art_id) REFERENCES art(art_id) ON DELETE CASCADE
);
