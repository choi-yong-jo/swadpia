
# 1. 환경 변수
BUCKET=swadpia-erp;CRON_SETTLEMENT_COMPLETE=0 0 9 * * WED;CRON_SETTLEMENT_WAITE=0 50 8 * * THU;DATABASE_PASSWORD=cyjo1207;DATABASE_URL=jdbc:postgresql://localhost:5432/sungwon;DATABASE_USER=postgres;IMG_SERVER=storage.erp.swadpia.co.kr;IMG_SERVER_KEY=swadpia-erp;IMG_SERVER_SECRET=swadpia-erp;IMG_URL=https://images.erp.swadpia.com/;PROFILE=local;REDIS_HOST=devbox.kr;REDIS_PORT=31379

------

# 2. 코딩 컨벤션


-----

# * **설치(중요)**


**./codeconvention/naver-intellij-formatter.xml** 파일 아래 링크 참조하여 `인텔리제이에 설치`
>https://naver.github.io/hackday-conventions-java/#_intellij


-----


## 목차
1. [공통 요건](#공통-요건)
2. [네이밍 규칙](#네이밍-규칙)
3. [API 엔드포인트 네이밍 규칙](#api-엔드포인트-네이밍-규칙)
4. [주석](#주석)
5. [클래스 및 인터페이스 선언](#클래스-및-인터페이스-선언)
6. [메서드 선언](#메서드-선언)
7. [문장](#문장)
8. [공백](#공백)
9. [데이터 베이스 네이밍 규칙](#데이터베이스-네이밍-규칙)
* [컬럼명 약어 사용 가능 목록](#컬럼명-약어-사용-가능-목록)


-----

#### * 공통 요건.


*  모든 소스, 텍스트 문서 파일의 인코딩은 UTF-8로 통일한다.

-----

-----

#### * 네이밍 규칙

- 식별자에는 영문/숫자/언더스코어만 허용
  > 변수명, 클래스명, 메서드명 등에는 영어와 숫자만을 사용한다.
  
- 한국어 발음대로의 표기 금지
  > ~~jumon (주문) 금지~~


- 대문자로 표기할 약어 명시 
  > HTTP, API, URL 약어 >> ***HttpApiUrl*** 
- 패키지 이름은 소문자로 구성
  >~~codeConvention(X)~~ , codeconvention(o)
- 클래스/인터페이스 이름에 대문자 카멜표기법 적용
  > public class OrderController 

- 클래스 이름에 명사 사용


- 테스트 클래스는 'Test’로 마무리
  > OrderServiceTest

- 메서드 이름에 소문자 카멜표기법 적용
  > public void toString()

- 메서드 이름은 동사/전치사로 시작
  >메서드명은 기본적으로는 동사로 시작한다. 다른 타입으로 전환하는 메서드나 빌더 패턴을 구현한 클래스의 메서드에는 전치사를 쓸 수 있다.
  >> public List<String> searchList()

- 상수는 대문자와 언더스코어로 구성
  > public final int UNLIMITED = -1;
  > 
  > public final String POSTAL_CODE_EXPRESSION = “POST”;

- 변수에 소문자 카멜표기법 적용
  > private String codeName

- 한 줄에 한 문장, 하나의 선언문에는 하나의 변수만

- 배열에서 대괄호는 타입 뒤에 선언
  > String[] names;

- 탭의 크기는 4개의 스페이스


- 하드탭 사용
  > 탭(tab) 문자를 사용하여 들여쓴다. 탭 대신 스페이스를 사용하지 않는다. 이를 잘 준수하기 위해서 스페이스와 탭을 구별해서 보여주도록 에디터를 설정한다.

- 중괄호 참고

      public boolean isValidExpression(String exp) {

        if (exp == null) {
            return false;
        }

        for (char ch : exp.toCharArray()) {
            ....
        }

        return true;
       }

- 메소드 사이에 빈 줄 삽입
 

     public void setId(int id) {
       this.id = id;
      }

      public void setName(String name) {
       this.name = name;
      }

-  중괄호의 시작 전, 종료 후에 공백 삽입

      
       public void printWarnMessage(String line) {
          if (line.startsWith(WARN_PREFIX)) {
            ...
          } else {
            ...
          }
        }

-----

-----
#### * API endpoint 규칙

- 1depth메뉴/ depth메뉴 / 3depth + 기능
- 엔드포인트는 소문자와 하이픈(-)을 사용하여 작성합니다
  > /system/menu/menu-list

- CRUD의 행위는 HTTP 메서드를 사용하여 나타냅니다.
  * GET은 단일 리소스 조회를 나타냅니다.
  * POST는 복수 조회, 리소스 생성을 나타냅니다.
  * PUT은 리소스 업데이트를 나타냅니다.
  * DELETE는 리소스 삭제를 나타냅니다
-----

----

## 주석

#### 블록 주석

- 블록 주석은 코드의 논리적 단위를 설명하는데 사용됩니다.
- `/* ... */` 형태를 사용합니다.

#### Javadoc 주석

- 공개 API에 대한 설명은 Javadoc 주석을 사용합니다.
- `/** ... */` 형태를 사용합니다.


#### 클래스 및 인터페이스 선언

- 클래스와 인터페이스는 각각의 파일에 선언합니다.
- 클래스와 인터페이스 선언 시 명확한 이름을 사용합니다.

#### 메서드 선언

- 메서드는 한 줄에 하나씩 선언합니다.
- 각 매개변수는 한 줄에 하나씩 선언합니다.

## 문장

- 각 문장은 세미콜론(;)으로 끝나야 합니다.
- if, for, while 문 등은 항상 중괄호를 사용합니다.

## 공백

- 키워드(if, for, while, switch, catch 등)와 괄호 사이에는 공백을 삽입합니다.
- 메서드 이름과 여는 괄호 사이에는 공백을 삽입하지 않습니다.
----

----
# 데이터베이스 네이밍 규칙

이 문서는 데이터베이스 스키마의 일관성, 가독성 및 유지 관리성을 보장하기 위한 데이터베이스 객체의 네이밍 규칙을 설명합니다.

## 일반 지침
- **소문자**를 사용합니다.
- 단어를 구분할 때는 **언더스코어**(`_`)를 사용합니다.
- 예약어는 사용하지 않습니다.
- 테이블 이름은 **단수 명사**를 사용합니다.
- **명확하고 간결하게** 작성합니다.

## 테이블 이름
- 단수 명사를 사용합니다.
- 필요할 경우 모듈 또는 기능 이름을 접두사로 사용합니다.
- 예: `user`, `order`, `product`, `customer_address`

## 컬럼 이름
- 명확하고 설명적인 이름을 사용합니다.
- 단어를 구분할 때는 언더스코어를 사용합니다.ㅠ
- 동일한 컬럼 이름이 여러 테이블에서 사용될 수 있는 경우 테이블 이름 또는 약어를 접두사로 사용합니다.

### 컬럼 이름 예시
1. **사용자 테이블 (user)**
  - 사용자 ID: `user_id`
  - 사용자 이름: `user_name`
  - 이메일: `email`
  - 생성 날짜: `created_at`

2. **주문 테이블 (order)**
  - 주문 ID: `order_id`
  - 사용자 ID: `user_id`
  - 주문 날짜: `order_date`
  - 총액: `total_amount`

3. **상품 테이블 (product)**
  - 상품 ID: `product_id`
  - 상품 이름: `product_name`
  - 가격: `price`
  - 재고 수량: `stock_quantity`

4. **고객 주소 테이블 (customer_address)**
  - 주소 ID: `address_id`
  - 고객 ID: `customer_id`
  - 주소: `address`
  - 도시: `city`
  - 우편번호: `postal_code`

## 기타 객체 이름 (뷰, 트리거 등)
- 뷰는 `view_`로 시작합니다.
- 트리거는 `trg_`로 시작합니다.
- 기타 객체도 명확하고 일관된 접두사를 사용합니다.
- 예: `view_user_orders`, `trg_before_insert_order`

----

----
## 컬럼명 약어 사용 가능 목록
###### (다수 테이블에서 사용되는 코드는 개발자들과 상의 후 추가 가능- 어지간해서는 풀네임 사용 권장))
>`id` (아이디), `at` (일시), `dt` (일), `no` (number)
>
>  `res` (response), `req` (request)

----
