# ✈️ JORANG 여행에 대한 모든 것


## <br>📃 핵심 기능
### 1. 여행기, 경비, 계획 작성 및 공유

- 날짜별 여행기, 경비 작성 및 공유
- Google cloud storage를 이용하여 사진 업로드 및 가져오기
- 이미지 슬라이더 구현
- 경비를 도표로 구현
- 여행 일지 최신순, 좋아요순 정렬 및 여행 기간에 따른 여행 일지 필터 구현
- 유저 아이디, 이메일 변경 시 이메일로 임시 비밀번호 발송

### 전체 프로젝트 소개
https://github.com/jinho9482/JORANG-TRAVEL

### Langchain server
https://github.com/encore-full-stack-5/JORANG_LANGCHAIN_SERVER

### Front-End
https://github.com/encore-full-stack-5/JORANG-TRAVEL-FE


## <br>⚙️ 기술스택

### Server Framework
![Spring-Boot](https://img.shields.io/badge/spring--boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)

### Database
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

### Infra Framework
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)
   
## API 명세서 (with Swagger API)<br>

<img width="646" alt="image" src="https://github.com/user-attachments/assets/92663c50-4e9d-4999-8d83-f0537bcfcf93">
<img width="646" alt="image" src="https://github.com/user-attachments/assets/565b3874-3ff7-4443-8403-f9eb0ab16aef">
<img width="646" alt="image" src="https://github.com/user-attachments/assets/36b1dcf7-77d2-44cd-9dfb-51c53cd68f3f">
<img width="644" alt="image" src="https://github.com/user-attachments/assets/a6ecbe69-e0de-4173-99c3-f64060e60a5b">
<img width="646" alt="image" src="https://github.com/user-attachments/assets/f6ec63b3-d629-432d-ad87-b43d735bc22e">
<br>


## 🔗 ERD
![image](https://github.com/user-attachments/assets/4266a2da-99d2-4027-a9fe-c7a8451c26a4)

##  🔧 트러블 슈팅

**1. Google cloud storage를 사용할 때 Front에서 file type의 input을 보낼 때 이로부터 파일 경로를 읽어올 수 없음<br><br>**
> * 원인 : Front에서 file type의 input을 보낼 때는 파일 경로를 back으로 보내지 않는다.<br>
> * 해결 : Server에서 MultipartFile[] type으로 받고 front에서 FormData형식으로 server로 보내서 해결<br>

<br>

**2. Post entity를 Page type으로 가져올 때, 요청한 size 보다 적은 데이터를 가져옴<br><br>**
>* 원인 : 아래 그림과 같이 1개의 Post id에 여러 개의 Diary id가 join되어있어, size가 5라고 하면, post_id 가 4번인 것까지 가져온다.<br>
>![image](https://github.com/user-attachments/assets/9408e91b-6ef9-4266-a486-cc0004a66a8f)<br>
>* 해결 : Front 에서 최신순과 같은 정렬을 할 때 전체 Post를 기준으로 정렬하지 못하고, 현재 page내에서만 정렬을 하여 Page<Post> → List<Post>를 return 하는 것으로 변경<br>

<br>

**3. Post service에서 Post와 OneToMany 관계에 있는 Diary entity의 scope(공개 범위) column가 공개인 것만 가져오는 jpa method 작성하였을 때, 비공개 데이터도 가져옴.<br><br>**
>* 원인 : Diary entity가 Post 기준 OneToMany관계에 있기 때문에 기본적으로 lazy loading 상태이다. 이 때, Diary를 아직 읽어오지 않은 상태에서 Diary의 scope column에 where절을 적용하기 때문에, 해당 조건은 적용되지 않는다.<br> 
>* 해결1 : Fetch join으로 Diary entity를 첫 query부터 가져와 조건 적용<br>
>* 해결2 : 결국 table 구조 변경으로 fetch join 불필요로 삭제<br><br>

<br>

**4. Diary table의 content column에서 다음 error 발생 (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'content' at row 1) <br><br>**
>* 원인 : Input 값이 길어 Diary table의 content column이 용량 초과로 받아들이지 못함<br>
>* 해결1 : SQL문을 적용하여 LONGTEXT 로 전환
>```mysql
>ALTER TABLE diaries MODIFY content LONGTEXT;
>```
>* 해결2 : Diary entity의 column 설정 추가 -> column length를  65,535 bytes까지 늘림
>```java
>@Column(name = "CONTENT", columnDefinition="TEXT")
>@Setter
>private String content;
>```

<br>

**5. ddl-auto: update 상태에서 Unique key 해제 불가<br><br>**
>* 원인 : Hibernate가 database schema를 update할 때 index와 같은 제한 조건은 그대로 둠<br> 
>* 해결
>  1. Index 찾기 (unique key 설정은 index로 설정된다. @ MYSQL)
>```mysql
>SHOW INDEX FROM expenses WHERE Column_name = 'date';
>```
>  2. Index 삭제 (Key_name 이용)
>```mysql
>ALTER TABLE expenses DROP INDEX UK_86u9tadcvh7keuk8sj6x50p65;
>```

<br>

**6. Table을 3개 이상 join할 때는 native query 사용<br><br>**
>* 원인 : OneToMany의 many에 해당하는 column은 기본적으로 Lazy loading 때문에 fetch join을 사용해야 데이터를 필터링해서 가져올 수 있다. <br>
하지만 fetch join은 2개의 테이블까지만 join이 가능하기에 3개의 table을 join할 때는 native query를 써야 한다.<br> 
>* 해결 : native query 사용

<br>

**7. JPQL에서는 LIMIT 사용 불가<br><br>**
>* 원인 : LIMIT 를 지원하지 않는다.
>* 해결 : Page class를 사용할 수 있으나, GROUP BY와 같이 jpa named query에 포함 안되는 query를 사용할 때는 native query를 사용
