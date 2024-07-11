
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
  member_pw varchar(20) NULL,
  mobile varchar(20) NULL,
  email varchar(30) NULL,
  refresh_token varchar(255) NULL,
  use_yn char(1) NOT NULL default 'Y',
  created_at timestamp NULL,
  updated_at timestamp NULL,
  CONSTRAINT member_pkey PRIMARY KEY (member_seq)
);

-- 권한 테이블

CREATE TABLE "admin"."role" (
  role_seq bigserial NOT NULL,
  role_id varchar(10) NOT NULL,
  role_nm varchar(20) NULL,
  description varchar(255) NULL,
  use_yn char(1) NOT NULL default 'Y',
  created_at timestamp NULL,
  updated_at timestamp NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_seq)
);


-- 회원-권한 매핑 테이블

CREATE TABLE "admin"."member_role" (
  member_role_seq bigserial NOT NULL,
  member_seq bigserial NOT NULL,
  role_seq bigserial NULL,
  created_at timestamp NULL,
  updated_at timestamp NULL,
  CONSTRAINT member_role_pkey PRIMARY KEY (member_role_seq)
);


drop table "admin"."member";

drop table "admin"."role";

drop table "admin"."member_role";




-----
3. 패키지 설정
- 각 기능별의 명칭 패키지를 만들어 controller, dto, entity, service 패키지로 나눠서 관리함.
- common : 각 패키지에 공통적으로 사용하게 될 엔티티 및 객체와 클래스을 담아둔 곳
- config : 인터셉터, 필터, 외부기능(Redis, queryDSL, ElasticSearch, SSO 등)이 포함
- cron : Job 스케줄러, 메일 전송
- repository : JPA, QueryDSL, Elasticsearch 기능을 쓸 수 있는 인터페이스