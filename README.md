# 테이블스키마
```sql 
 // 주문정보 
 create table orders (
       id bigint not null auto_increment,
        order_at DATETIME(0) default CURRENT_TIMESTAMP not null,
        order_no varchar(20) not null,
        product_name varchar(100) not null,
        user_no bigint,
        primary key (id)
    ) engine=InnoDB
  
  // 사용자 권한 테이블 (코드에서는 사용하지 않음)  
  create table user_roles (
       user_user_no bigint not null,
        roles varchar(255)
    ) engine=InnoDB   
    
  // 사용자  
  create table users (
       user_no bigint not null auto_increment,
        email varchar(100) not null,
        gender integer,
        mobile_phone_no varchar(20) not null,
        name varchar(20) not null,
        nickname varchar(30) not null,
        password varchar(100) not null,
        primary key (user_no)
    ) engine=InnoDB   
    
  alter table orders 
       add constraint FKk8po5u2vyuxiwm8tbqr3svqn4 
       foreign key (user_no) 
       references users (user_no)
         
  alter table user_roles 
       add constraint FK4meifofcwly337qikw9nppptd 
       foreign key (user_user_no) 
       references users (user_no)   
```

# 스웨거 URL
http://localhost:8080/swagger-ui/index.html

# 요구사항 

* 회원가입
* 회원 로그인 
* 회원 로그아웃
* 단일 회원 상세 정보 조회
* 여러 회원 목록 조회
* 단일 회원의 주문 목록 조회
* Spring boot 2.3 이상 
* Gradle 빌드 
* Swagger UI
* Mysql InnoDB
* Mysql replication 

# 구현사항
* Java11
* Springboot2.6.4
* docker 
* 회원가입
   * `POST http://localhost:8080/v1/user/signup`    
* 회원 로그인
   * `POST http://localhost:8080/v1/user/login`  
* 회원 로그아웃 
  * JWT 토큰 사용으로 미구현 
* 단일 회원 상세 정보 조회 
  * `GET http://localhost:8080/v1/admin/user/search/{userNo}`
* 여러 회원 목록 조회
  * `GET http://localhost:8080/v1/admin/user/search?searchKey=&searchValue=&page=0&size=20`
* 단일 회원의 주문 목록 조회
  * `GET http://localhost:8080/v1/admin/order/{userNo}`
* mysql replication
> 운영에서는 aurora 디비를 사용한다는 전제로 스마트드라이버를 사용하여 서비스단에서   @Transactional(readOnly = true)로 구분하였고 
> application.yml에서는  jdbc:mysql:aurora:로 연결해야하합니다.``
* Swagger UI
  * `http://localhost:8080/swagger-ui/index.html`
  > 기존에 RestDoc으로 사용하다보니 스웨거는 처음 사용해보았습니다. 컨트롤단에서 지저분한 어노테이션으로 인해 테스트와 연동되는 RestDoc 좋다고 생각됩니다. 


# 테스트 코드 
  * BDD 형식의 테스트 코드를 47개 작성하였습니다.
![Alt text](https://user-images.githubusercontent.com/310264/181422764-20d9f35f-2a72-471c-85a0-0277931ae340.png)