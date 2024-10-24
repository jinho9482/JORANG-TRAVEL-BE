# ✈️ JORANG - Everything about Travel


## <br>📃 Key Features
### Writing and Sharing Travel Logs, Expenses, and Plans

- Write and share travel logs and expenses by date
- Upload and retrieve photos using Google Cloud Storage
- Implement image slider
- Display expenses in charts
- Sort travel logs by latest and most liked, and filter by travel period
- Send a temporary password via email when a user ID or email is changed

### Full Project Introduction
https://github.com/jinho9482/JORANG-TRAVEL

### Langchain Server
https://github.com/encore-full-stack-5/JORANG_LANGCHAIN_SERVER

### Front-End
https://github.com/encore-full-stack-5/JORANG-TRAVEL-FE


## <br>⚙️ Tech Stack

### Server Framework
![Spring-Boot](https://img.shields.io/badge/spring--boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)

### Database
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

### Infra Framework
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)
   
## API Documentation (with Swagger API)<br>

<img width="640" alt="image" src="https://github.com/user-attachments/assets/ce387d91-2d3f-4adf-988e-ecc3f375c0d4">

<img width="643" alt="image" src="https://github.com/user-attachments/assets/dc7060e0-0f9c-4011-b8b3-6c28ad1fec5f">

<img width="638" alt="image" src="https://github.com/user-attachments/assets/7c91f1d7-2db0-4a38-badc-a4f994caf25a">

<img width="644" alt="image" src="https://github.com/user-attachments/assets/6720c674-fd60-4a76-887b-e133823d9727">

<br>


## 🔗 ERD
![image](https://github.com/user-attachments/assets/4266a2da-99d2-4027-a9fe-c7a8451c26a4

##  <br>🔧 Performance Improvements
<img width="944" alt="image" src="https://github.com/user-attachments/assets/1762e867-8090-461d-b2df-2426c5cabbee">


##  🔧 Troubleshooting

**1. Unable to read the file path when sending file-type input from the front to Google Cloud Storage<br><br>**
> * Cause: When sending file-type input from the front, it doesn't send the file path to the back.<br>
> * Solution: Receive it as MultipartFile[] type on the server and send it to the server in FormData format from the front<br>

<br>

**2. When fetching Post entities as Page type, fewer data is retrieved than the requested size<br><br>**
>* Cause: As shown in the image below, several Diary ids are joined to a single Post id, so when the size is 5, it fetches up to post_id 4.<br>
>![image](https://github.com/user-attachments/assets/9408e91b-6ef9-4266-a486-cc0004a66a8f)<br>
>* Solution: Front wasn't sorting based on all Posts for the latest sort, but only within the current page, so it was changed to return List<Post> instead of Page<Post><br>

<br>

**3. When writing a JPA method to fetch only public Diaries for a Post entity in the Post service, private data was also fetched.<br><br>**
>* Cause: Since the Diary entity is in a OneToMany relationship with Post, it's lazy-loaded by default. As a result, applying a where clause to the scope column of Diary before it is loaded does not work.<br> 
>* Solution 1: Use fetch join to load the Diary entity from the first query and apply the condition<br>
>* Solution 2: Eventually, the table structure was changed, and fetch join was removed as it was no longer necessary<br><br>

<br>

**4. Error in the content column of the Diary table (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'content' at row 1) <br><br>**
>* Cause: The input value was too long for the content column of the Diary table to accept.<br>
>* Solution 1: Apply SQL query to change to LONGTEXT
>```mysql
>ALTER TABLE diaries MODIFY content LONGTEXT;
>```
>* Solution 2: Add column settings in the Diary entity -> Increase column length to up to 65,535 bytes
>```java
>@Column(name = "CONTENT", columnDefinition="TEXT")
>@Setter
>private String content;
>```

<br>

**5. Unable to release Unique key in ddl-auto: update mode<br><br>**
>* Cause: When Hibernate updates the database schema, it keeps constraints like indexes.<br> 
>* Solution
>  1. Find the Index (Unique key settings are configured as indexes @ MYSQL)
>```mysql
>SHOW INDEX FROM expenses WHERE Column_name = 'date';
>```
>  2. Delete the Index (Using Key_name)
>```mysql
>ALTER TABLE expenses DROP INDEX UK_86u9tadcvh7keuk8sj6x50p65;
>```

<br>

**6. Use native query when joining more than 3 tables<br><br>**
>* Cause: Columns corresponding to the "many" side of a OneToMany relationship are lazy-loaded by default, and to filter data, fetch join must be used.<br>
However, fetch join can only join 2 tables, so when joining 3 tables, native query must be used.<br> 
>* Solution: Use native query

<br>

**7. LIMIT is not available in JPQL<br><br>**
>* Cause: JPQL does not support LIMIT.
>* Solution: You can use the Page class, but if the query contains something like GROUP BY, which is not included in JPA named queries, use native query
