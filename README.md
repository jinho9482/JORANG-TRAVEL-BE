# ✈️ JORANG 여행에 대한 모든 것

## 📃 프로젝트 개발 동기
개인적으로 해외 여행을 좋아하는데, 다른 사람들은 내가 가고 싶은 여행지에 대해 어떻게 생각하고 계획과 경비를 어떻게 짰는지 확인하기 위해 서비스를 개발하였습니다.

## 📃 프로젝트 소개
나의 여행 계획, 일지, 실제 사용한 경비를 관리하고 사람들과 공유하여 다른 사람들의 여행기를 읽고 소통하면서 내 여행을 준비하는데 필요한 정보를 얻을 수 있다.

## 📃 핵심 기능
### 1. 여행기, 경비, 계획 작성 및 공유

- 날짜별 여행기, 경비 작성 및 공유
- Google cloud storage를 이용하여 사진 업로드 및 가져오기
- 이미지 슬라이더 구현
- 경비를 도표로 구현
- 여행 일지 최신순, 좋아요순 정렬 및 여행 기간에 따른 여행 일지 필터 구현
- 유저 아이디, 이메일 변경 시 이메일로 임시 비밀번호 발송

### 2. 챗봇 (아래 Langchain server 참고)

- Langchain을 이용하여 명소, 여행지, 여행 계획 추천 기능 및 내 맞춤 여행 계획 추천 구현
  (LLM : Google Gemini, Framework : FastAPI)

### Langchain server
https://github.com/encore-full-stack-5/JORANG-TRAVEL-FE

### Front-End
https://github.com/encore-full-stack-5/JORANG-TRAVEL-FE


## ⚙️ 기술스택

### Server Framework
![Spring-Boot](https://img.shields.io/badge/spring--boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)

### Database
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

### Infra Framework
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)

## 🧑🏻‍💻 멤버구성
 - 조진호
 - 김세현
 - 임서연
   
## 프로젝트 구조

📦src<br>
┣ 📂main<br>
┃ ┣ 📂generated<br>
┃ ┣ 📂java<br>
┃ ┃ ┗ 📂com<br>
┃ ┃ ┃ ┗ 📂example<br>
┃ ┃ ┃ ┃ ┗ 📂travel_diary<br>
┃ ┃ ┃ ┃ ┃ ┣ 📂controller<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exceptionController<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthExceptionController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryExceptionController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoExceptionController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostExceptionController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryInfoController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LikeController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoController.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostController.java<br>
┃ ┃ ┃ ┃ ┃ ┣ 📂global<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomSecurityConfig.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ElasticSearchConfig.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂elasticSearch<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostDocument.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryInfo.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Diary.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Expense.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetail.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindLoginIdEmailSender.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindPasswordEmailSender.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Like.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordGenerator.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Photo.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Post.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryInfoRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LikeRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂type<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Scope.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryNotFoundException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailAlreadyExistsException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginFailedException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginIdAlreadyExistsException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoLimitExceededException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoNotFoundException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostNotFoundException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostNotPublicException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserNotFoundException.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindLoginIdRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindPasswordRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignInRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignUpRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateUserRequestDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailByUserAndCountryResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailChartResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailChartTempResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetUserByIdResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LikeResponse.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginInResponseDto.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostResponse.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂utils<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtUtil.java<br>
┃ ┃ ┃ ┃ ┃ ┣ 📂service<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryInfoService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryInfoServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiaryServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseDetailServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LikeService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LikeServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostSearchService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostService.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostServiceImpl.java<br>
┃ ┃ ┃ ┃ ┃ ┗ 📜TravelDiaryApplication.java<br>
┃ ┗ 📂resources<br>
┃ ┃ ┣ 📜application-prod.yml<br>
┃ ┃ ┗ 📜application.yml<br>
┗ 📂test<br>
┃ ┗ 📂java<br>
┃ ┃ ┗ 📂com<br>
┃ ┃ ┃ ┗ 📂example<br>
┃ ┃ ┃ ┃ ┗ 📂travel_diary<br>
┃ ┃ ┃ ┃ ┃ ┣ 📂service<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthServiceImplTest.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExpenseServiceTest.java<br>
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostServiceImplTest.java<br>
┃ ┃ ┃ ┃ ┃ ┗ 📜TravelDiaryApplicationTests.java<br>

## 🔗 ERD
![image](https://github.com/user-attachments/assets/4266a2da-99d2-4027-a9fe-c7a8451c26a4)

## 트러블 슈팅

**1. Google cloud storage를 사용할 때 Front에서 file type의 input을 보낼 때 이로부터 파일 경로를 읽어올 수 없음<br><br>**
> * 원인 : Front에서 file type의 input을 보낼 때는 파일 경로를 back으로 보내지 않는다.<br>
> * 개선 : Server에서 MultipartFile[] type으로 받고 front에서 FormData형식으로 server로 보내서 해결<br>

<br>

**2. Post entity를 Page type으로 가져올 때, 요청한 size 보다 적은 데이터를 가져옴<br><br>**
>* 원인 : 아래 그림과 같이 1개의 Post id에 여러 개의 Diary id가 join되어있어, size가 5라고 하면, post_id 가 4번인 것까지 가져온다.<br>
>![image](https://github.com/user-attachments/assets/9408e91b-6ef9-4266-a486-cc0004a66a8f)<br>
>* 개선 : Front 에서 최신순과 같은 정렬을 할 때 전체 Post를 기준으로 정렬하지 못하고, 현재 page내에서만 정렬을 하여 Page<Post> → List<Post>를 return 하는 것으로 변경<br>

<br>

**3. Post service에서 Post와 OneToMany 관계에 있는 Diary entity의 scope(공개 범위) column가 공개인 것만 가져오는 jpa method 작성하였을 때, 비공개 데이터도 가져옴.<br><br>**
>* 원인 : Diary entity가 Post 기준 OneToMany관계에 있기 때문에 기본적으로 lazy loading 상태이다. 이 때, Diary를 아직 읽어오지 않은 상태에서 Diary의 scope column에 where절을 적용하기 때문에, 해당 조건은 적용되지 않는다.<br> 
>* 개선1 : Fetch join으로 Diary entity를 첫 query부터 가져와 조건 적용<br>
>* 개선2 : 결국 table 구조 변경으로 fetch join 불필요로 삭제<br><br>

<br>

**4. Diary table의 content column에서 다음 error 발생 (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'content' at row 1) <br><br>**
>* 원인 : Input 값이 길어 Diary table의 content column이 용량 초과로 받아들이지 못함<br>
>* 해결1 : SQL문을 적용하여 LONGTEXT 로 전환
>```mysql
>ALTER TABLE diaries MODIFY content LONGTEXT
>```
>* 해결2 : Diary entity의 column 설정 추가 -> column length를  65,535 bytes까지 늘림
>```java
>@Column(name = "CONTENT", columnDefinition="TEXT")
>@Setter
>private String content;
>```

5. 


