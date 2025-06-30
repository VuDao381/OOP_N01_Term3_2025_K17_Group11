# QUẢN LÝ THƯ VIỆN SÁCH SỐ HÓA

## GROUP 11

1.Đào Anh Vũ

2.Phạm Khắc Hùng

## Yêu cầu

 YÊU CẦU & MỤC TIÊU
 
 Giao diện: Java Spring Boot (Web)

 Chức năng quản lý: Sách (Book) và Người dùng (User).

 Hỗ trợ xử lý mượn/trả sách qua phiếu mượn (BorrowSlip).

## I. GIỚI THIỆU

Đây là ứng dụng Web giúp các thư viện nhỏ quản lý việc mượn – trả sách, quản lý người dùng và sách trong kho. Ứng dụng có giao diện web thân thiện, truy cập được qua trình duyệt.

## II Chức năng chính

 Quản lý sách
 
  + Thêm, sửa, xóa Sách (Book)

  + Liệt kê danh sách sách.

  + Tìm kiếm theo tiêu đề sách, tác giả, nhà xuất bản.

  + Lọc danh sách theo chữ cái đầu của sách theo thứ tự từ A-Z và ngược lại.

 Quản lý người dùng
  
   + Thêm, sửa, xóa Người dùng (User).

   + Tìm kiếm người dùng theo tên, email.

   + Hiển thị danh sách người dùng theo ID, email...

 Quản lý phiếu mượn
  
   + Gán sách cho người dùng thông qua phiếu mượn (BorrowSlip).

   + Có các chức năng thêm, sửa, xóa phiếu mượn.

   + Tìm kiếm phiếu mượn theo tên người dùng, tiêu đề của sách.
   
   + Cho phép mượn, nhập thủ công ngày mượn, hạn trả. Đối với những sách đang mượn hoặc quá hạn trả cho phép cập nhật bằng cách đánh dấu đã trả.

   + Kiểm tra sách đã mượn, chưa trả, hoặc quá hạn

 <b>THIẾT KẾ CƠ SỞ DỮ LIỆU:</b>
 
   Hệ thống được thiết kế theo mô hình hướng đối tượng, mỗi thực thể đều được ánh xạ thành một lớp Java và tương ứng với bảng dữ liệu khi lưu trữ lâu dài.
   Dưới đây là bảng mô tả các thực thể chính:
   
   - User (Người dùng)

| Trường         | Kiểu dữ liệu | Mô tả                        |
| ----------     | ------------ | ---------------------------- |
| `id`           | `Long`       | Mã người dùng (tự động tăng) |
| `username`     | `String`     | Tên người dùng               |
| `useremail`    | `String`     | Địa chỉ email                |
| `userpassword` | `String`     | Mật khẩu đăng nhập           |

   - Book (Sách)


| Trường      | Kiểu dữ liệu | Mô tả                  |
| ----------- | ------------ | ---------------------- |
| `id`        | `Long`       | Mã sách (tự động tăng) |
| `title`     | `String`     | Tên sách               |
| `author`    | `String`     | Tác giả                |
| `publisher` | `String`     | Nhà xuất bản           |
| `numPages`  | `int`        | Số trang               |
| `quantity`  | `int`        | Số lượng còn trong kho |

   - BorrowSlip (Phiếu mượn)

| Trường       | Kiểu dữ liệu          | Mô tả                                                        |
| ------------ | --------------------- | ------------------------------------------------------------ |
| `id`         | `Long`                | Mã phiếu mượn                                                |
| `user`       | `User`                | Đối tượng người dùng mượn sách (liên kết ManyToOne đến User) |
| `book`       | `Book`                | Đối tượng sách đã được mượn (liên kết ManyToOne đến Book)    |
| `borrowDate` | `LocalDate`           | Ngày mượn sách                                               |
| `dueDate`    | `LocalDate`           | Ngày phải trả sách (Hạn cuối cùng)                           |
| `isReturned` | `boolean`             | Đã trả hay chưa (`true` nếu đã trả, `false` nếu chưa)        |
| `returnDate` | `LocalDate`(nullable) | Ngày thực tế đã trả sách (có thể null nếu chưa trả)          |



 ## III. CÔNG NGHỆ
 
### CÔNG NGHỆ ĐÃ SỬ DỤNG
 
Frontend (Giao diện):

   - Sử dụng Spring Boot kết hợp với Thymeleaf làm template engine để sinh ra các trang HTML động.

   - Các trang giao diện như danh sách sách, người dùng, phiếu mượn được hiển thị trực tiếp trên trình duyệt qua các file .html nằm trong thư mục resources/templates/.

   - Sử dụng các thư viện CSS như Tailwind CSS và Font Awesome

Backend (Xử lý logic):

   - Ngôn ngữ: Java 17.

   - Framework: Spring Boot 3.5.3.

   - Kiến trúc: MVC gồm Controller, Service, Repository, Model, DTO.

   - Sử dụng Spring Web, Spring Data JPA.

Lưu trữ dữ liệu:

   - Kết nối với MySQL thông qua Hibernate/JPA.
    
   - Các entity ánh xạ với bảng dữ liệu.
    
   - Repository kế thừa JpaRepository để thao tác với CSDL.

Công cụ phát triển:

   - IDE: Visual Studio Code

   - Bộ công cụ hỗ trợ: Java Extension Pack, Live Share, Terminal, Debugger

   - Quản lý biên dịch: Maven (dự phòng nếu mở rộng)

## IV. CẤU TRÚC DỰ ÁN
   
  ### Mô hình kiến trúc:
   
   Hệ thống được tổ chức theo mô hình 3 lớp chuẩn:

   [Client Layer]: 
    
   - Giao diện người dùng được xây dựng bằng Thymeleaf, nhúng dữ liệu vào các file .html.

   [Controller Layer]:

   - Nhận và xử lý HTTP request từ phía client.
     
   - Gọi đến các service để xử lý logic nghiệp vụ.

   - Trả kết quả về client (giao diện HTML hoặc redirect).

   [Service Layer]:

   - Các lớp như BookService, UserService, BorrowSlipService.

   - Tách riêng nghiệp vụ ra khỏi controller để dễ bảo trì và kiểm thử.

   [Model Layer]:
    
   - Các lớp Book, User, BorrowSlip được đánh dấu bằng @Entity, đại diện cho bảng dữ liệu trong MySQL.

   - Ánh xạ quan hệ giữa các bảng bằng JPA (@ManyToOne).

   [Repository Layer]:
    
   - Gồm các interface BookRepository, UserRepository, BorrowSlipRepository, kế thừa JpaRepository.
     
   - Cung cấp sẵn các phương thức như findAll(), save(), deleteById(),... để thao tác với cơ sở dữ liệu mà không cần viết SQL thủ công.

  ### Các thành phần chính trong project:

   | Thành phần     | Vai trò                                                                 |
   | -------------- | ----------------------------------------------------------------------- |
   | `Book`         | Đại diện cho sách: tiêu đề, tác giả, nhà xuất bản, số trang, tồn kho    |
   | `User`         | Người dùng hệ thống: tên, email, mật khẩu                               |
   | `BorrowSlip`   | Phiếu mượn: gán sách cho người dùng, ngày mượn, hạn trả, trạng thái trả |

   ### Quy trình xử lý:
   
   Ví dụ tạo phiếu mượn:
     
   User nhập thông tin → Controller nhận request →Service xử lý: kiểm tra tồn kho, gán sách →Tạo BorrowSlip → Gán cho User →Hiển thị danh sách mượn

  ## V. CẤU TRÚC THƯ MỤC

  ```
OOP_N01_TERM3_2025_K17_GROUPX_QLTV/
├── .vscode/
│   └── settings.json
│
├── gs-serving-web-content-main/                 # PHẦN 1: Ứng dụng web Spring Boot
│   ├── complete/
│   │   ├── build.gradle
│   │   ├── settings.gradle
│   │   ├── pom.xml
│   │   ├── gradlew*
│   │   ├── mvnw*
│   │   ├── .gitignore
│   │   ├── LICENSE.txt
│   │   ├── CONTRIBUTING.adoc
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/com/example/servingwebcontent/
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   ├── BookController.java
│   │   │   │   │   │   ├── BorrowSlipController.java
│   │   │   │   │   │   ├── DashboardController.java
│   │   │   │   │   │   ├── ReportController.java
│   │   │   │   │   │   └── UserController.java
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   ├── BorrowSlipDTO.java
│   │   │   │   │   │   ├── DashboardStats.java
│   │   │   │   │   │   └── ReportDTO.java
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Book.java
│   │   │   │   │   │   ├── BorrowSlip.java
│   │   │   │   │   │   └── User.java
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   ├── BookRepository.java
│   │   │   │   │   │   ├── BorrowSlipRepository.java
│   │   │   │   │   │   └── UserRepository.java
│   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── BookService.java
│   │   │   │   │   │   ├── BorrowSlipService.java
│   │   │   │   │   │   ├── ReportService.java
│   │   │   │   │   │   └── UserService.java
│   │   │   │   │   ├── GreetingController.java
│   │   │   │   │   └── ServingWebContentApplication.java
│   │   │   ├── resources/
│   │   │   │   ├── static/
│   │   │   │   │   └── index.html
│   │   │   │   ├── templates/
│   │   │   │   │   └── greeting.html
│   │   │   │   └── application.properties
│   │   ├── test/java/com/example/servingwebcontent/
│   │   │   ├── testcontroller/
│   │   │   │   ├── BookControllerTest.java
│   │   │   │   ├── BorrowSlipControllerTest.java
│   │   │   │   ├── DashboardControllerTest.java
│   │   │   │   ├── ReportControllerTest.java
│   │   │   │   └── UserControllerTest.java
│   │   │   └── ServingWebContentApplicationTest.java
│   │   ├── target/test-classes/...               # File biên dịch
│
├── Library/                                     # PHẦN 2: Java core (không dùng Spring)
│   ├── src/
│   │   ├── BorrowSlipManagement.java
│   │   ├── BorrowSlipNotice.java
│   │   ├── Check.java
│   │   ├── LibrarySystem.java
│   │   ├── UserBorrowed.java
│   │   ├── UserBorrowedController.java
│   │   └── UserManagement.java
│
│   ├── test/
│   │   ├── BookSearchTestHelper.java
│   │   ├── BookTest.java
│   │   ├── BorrowSlipTest.java
│   │   ├── TestBookManagement.java
│   │   ├── TestBorrowSlipManagement.java
│   │   ├── TestCheck.java
│   │   ├── TestSequence.java
│   │   ├── TestUser.java
│   │   ├── testUserBorrowed.java
│   │   └── UserTest.java
│
│   └── README.md                                # Mô tả phần Library core
```
 ## VI. MÔ HÌNH VÀ CHỨC NĂNG
 
### 1. Mô hình hệ thống – Kiến trúc MVC (Model - View - Controller)
    
Hệ thống Quản lý Thư viện Sách số được xây dựng theo kiến trúc 3 lớp MVC chuẩn của Spring Boot:

#### Model (Dữ liệu)

Chứa các lớp mô hình ánh xạ với bảng dữ liệu qua JPA/Hibernate:

+ User – đại diện người dùng (người mượn sách): id, username, useremail, userpassword.

+ Book – đại diện sách trong thư viện: id, title, author, publisher, numPages, quantity.

+ BorrowSlip – phiếu mượn sách: id, user, book, borrowDate, dueDate, isReturned, returnDate.

+ Các class sử dụng annotation @Entity, @Table, và ánh xạ @ManyToOne để liên kết giữa Book, User, BorrowSlip.

#### View (Giao diện)

Giao diện động được tạo bằng Thymeleaf và các file HTML.

Hiển thị danh sách sách, người dùng, và phiếu mượn theo dữ liệu backend trả về.

Tích hợp TailwindCSS để tăng tính thẩm mỹ.

Có thể mở rộng thêm các giao diện thống kê, báo cáo, cảnh báo trễ hạn.

#### Controller (Điều hướng & API)

BookController: quản lý API sách (/api/books)

UserController: quản lý người dùng (/api/users)

BorrowSlipController: quản lý phiếu mượn, trả sách, thống kê (/api/borrow-slips)

Các controller sử dụng annotation REST như @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, kết hợp xử lý lỗi và logging rõ ràng bằng SLF4J.

#### Service Layer (Xử lý nghiệp vụ)

BookService, UserService, BorrowSlipService xử lý logic nghiệp vụ như:

+ Lưu/xoá/sửa dữ liệu

+ Tìm kiếm, lọc, thống kê sách mượn nhiều nhất

+ Kiểm tra quá hạn, đánh dấu đã trả

+ Phân tích phiếu mượn trong khoảng thời gian

#### Repository Layer (Truy vấn CSDL)

Sử dụng Spring Data JPA: BookRepository, UserRepository, BorrowSlipRepository

Có các phương thức tuỳ chỉnh như:

+ findByTitleContainingIgnoreCase()

+ countOverdueBetween()

+ findTopBorrowedBooks() dùng @Query

### 2. Chức năng hệ thống (chia theo từng mô-đun)
   
#### Sách (Book)

+ Thêm, cập nhật, xoá sách (BookController, BookService)

+ Tìm sách theo tiêu đề, tác giả, nhà xuất bản

+ Thống kê sách được mượn nhiều nhất

+ Tìm kiếm toàn cục theo từ khoá

#### Phiếu mượn (BorrowSlip)

+ Tạo phiếu mượn mới từ User + Book + ngày mượn + hạn trả

+ Cập nhật trạng thái isReturned, returnDate

+ Đánh dấu trả sách

+ Thống kê sách quá hạn, gần đến hạn (dựa vào dueDate)

+ Hiển thị 5 phiếu mượn mới nhất

+ Thống kê sách mượn nhiều nhất

#### Người dùng (User)

+ Thêm, cập nhật, xoá người dùng

+ Tìm người dùng theo ID, username, email

+ Thống kê tổng số người dùng

#### Thống kê & báo cáo

+ Số lượng sách đang được mượn

+ Số sách quá hạn chưa trả

+ Sách mượn phổ biến nhất

+ Lượt mượn sách trong từng khoảng thời gian

### 3. Luồng xử lý chức năng tiêu biểu – Mượn sách

Người dùng gửi yêu cầu mượn sách qua frontend.

Backend nhận thông tin gồm: userId, bookId, borrowDate, dueDate.

BorrowSlipController kiểm tra:

+ User và Book có tồn tại?

+ Sách còn tồn kho?

  - Nếu hợp lệ:

    + Tạo mới BorrowSlip, gán user và book

    + Lưu thông tin vào cơ sở dữ liệu

    + Gửi phản hồi thành công về frontend.

## VII. DIAGRAMS

### Class diagram

![class-diagram](https://github.com/user-attachments/assets/49053191-c2db-4dc0-a189-e9761c291c26)

### Activity Diagram

#### User Management

![User-activity-diagram](https://github.com/user-attachments/assets/e1c4d6eb-7892-498f-9762-31e437c9d96a)

#### Book Management

![book-activity-diagram](https://github.com/user-attachments/assets/a98ce4ac-0fc7-428b-ae30-57df88d13b3a)

#### BorrowSlip Management

![borrowslip-activity-diagram](https://github.com/user-attachments/assets/6bd3d1fc-3183-462e-a726-a6654ebfd4ac)


## VIII. LƯU ĐỒ THUẬT TOÁN CỦA CHỨC NĂNG CHÍNH


![HỆ THỐNG QUẢN LÝ THƯ VIỆN SÁCH SỐ HÓA](https://github.com/user-attachments/assets/2ee0f95e-200e-4c39-aaca-dac1b76aaf87)

## IX.GIAO DIỆN
### Trang chủ

![image](https://github.com/user-attachments/assets/7a8ba723-e58b-47c1-b126-0d6c8167538b)

   - Hiển thị tổng số sách, tổng số người dùng, tổng số sách đã mượn, tổng số sách đã quá hạn trả.
   
   - Hiển thị top 5 sách được mượn gần đây, top 5 sách được mượn nhiều nhất.

### Quản lý Sách

![image](https://github.com/user-attachments/assets/e9301b74-6c18-4890-abb6-6a73a1f84050)

   - Hiển danh sách Book với các cột ID, Title, Author, Publisher, Pages, Quantity. Cột Action để sửa hoặc xóa Book.
    
   - Cung cấp thanh tìm kiếm. Có thể tìm kiếm Book theo Title, Author, Publisher.

 #### Tạo phiếu mượn sách :

![image](https://github.com/user-attachments/assets/06580236-501c-4e04-ad5b-93e909337ace)

   - Nhấn "Add New Book" để mở form tạo mới sách.
   
   - Nhấn "Save" để lưu, "Cancel" để thoát.

  
 #### Phiếu mượn sách tạo thành công :

![image](https://github.com/user-attachments/assets/5a728384-157a-4281-97b7-09b2510644f6)

 ### Quản lý tài khoản người dùng

![image](https://github.com/user-attachments/assets/e9c5864b-c8bc-4556-ad83-e4b61e7f5b21)

   - Hiển danh sách User với các cột ID, Name, Email, Password. Cột Action để sửa hoặc xóa User.

   - Cung cấp thanh tìm kiếm. Có thể tìm kiếm User theo Name, Email.

  #### Tạo tài khoản người dùng :

 ![image](https://github.com/user-attachments/assets/3bc684e0-c0c5-4f7f-a5bf-889d80070329)

   - Nhấn "Add New User" để mở form tạo mới tài khoản người.
   
   - Nhấn "Save" để lưu, "Cancel" để thoát.

  #### Tạo tài khoản thành công :

  ![image](https://github.com/user-attachments/assets/8f9ab02e-89cf-4439-a60b-c0ea7e379189)

  ### Quản lý trả mượn sách
 
  ![Screenshot 2025-06-30 170643](https://github.com/user-attachments/assets/d64a8fbf-ef20-456f-a549-029af58c6f87) 

   - Hiển danh sách  với các cột Slip ID, User, Book, Borrow Date, Due Date. Cột Action để sửa, xóa phiếu mượn hoặc đánh dấu là đã trả sách.

   - Cung cấp thanh tìm kiếm. Có thể tìm kiếm BorrowSlip theo User, Book.

   - Có thể lọc danh sách theo tình trạng (Status), lọc danh sách theo ngày.

   #### Tạo phiếu mượn mới:
  
  ![Screenshot 2025-06-30 173241](https://github.com/user-attachments/assets/019f862a-3fab-4d84-83ce-f5c919790aef)
  
   ### Báo cáo số lượng mượn sách và danh sách đã trả sách ,danh sách mượn đã quá hạn

  ![image](https://github.com/user-attachments/assets/595f24d9-2ae0-437e-91ca-2fe5e6fee1b4)

   - Hiển thị tổng số sách đã mượn, đã trả, đã quá hạn trả theo tuần, tháng, năm.
