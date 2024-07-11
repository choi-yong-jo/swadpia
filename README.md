
# 1. 환경 변수
BUCKET=swadpia-erp;CRON_SETTLEMENT_COMPLETE=0 0 9 * * WED;CRON_SETTLEMENT_WAITE=0 50 8 * * THU;DATABASE_PASSWORD=cyjo1207;DATABASE_URL=jdbc:postgresql://localhost:5432/sungwon;DATABASE_USER=postgres;IMG_SERVER=storage.erp.swadpia.co.kr;IMG_SERVER_KEY=swadpia-erp;IMG_SERVER_SECRET=swadpia-erp;IMG_URL=https://images.erp.swadpia.com/;PROFILE=local;REDIS_HOST=devbox.kr;REDIS_PORT=31379

------

# 2. DB 스키마 테이블
-- cmd 창 실행 후 
psql -d postgres -U postgres -p 5432

-- sungwon 데이터베이스 생성
create database sungwon;

-- sungwon 데이터베이스 접근
c\ sungwon;

-- admion 스키마 생성
create schema if not exists admin authorization postgres;
-----


-- 회원 테이블

CREATE TABLE "admin"."member" (
  member_seq bigserial NOT NULL,
  member_id varchar(20) NOT NULL,
  team_id varchar(20) NULL,
  member_nm varchar(20) NOT NULL,
  member_pw varchar(255) NULL,
  mobile varchar(255) NULL,
  email varchar(255) NULL,
  refresh_token varchar(255) NULL,
  use_yn varchar(1) NOT NULL default 'Y',
  created_at timestamp(6) NULL,
  updated_at timestamp(6) NULL,
  CONSTRAINT member_pkey PRIMARY KEY (member_seq)
);

-- 권한 테이블

CREATE TABLE "admin"."role" (
  role_seq bigserial NOT NULL,
  role_id varchar(20) NOT NULL,
  role_nm varchar(20) NULL,
  description varchar(255) NULL,
  use_yn varchar(1) NOT NULL default 'Y',
  created_at timestamp(6) NULL,
  updated_at timestamp(6) NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_seq)
);


-- 회원-권한 매핑 테이블

CREATE TABLE "admin"."member_role" (
  member_role_seq bigserial NOT NULL,
  member_seq bigserial NOT NULL,
  role_seq bigserial NULL,
  created_at timestamp(6) NULL,
  updated_at timestamp(6) NULL,
  CONSTRAINT member_role_pkey PRIMARY KEY (member_role_seq)
);


drop table "admin"."member";

drop table "admin"."role";

drop table "admin"."member_role";
