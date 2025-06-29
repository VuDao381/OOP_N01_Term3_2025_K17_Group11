# QUẢN LÝ THƯ VIỆN SÁCH SỐ HÓA

GROUP 11

1.Dao Anh Vu

2.Pham Khac Hung

3.Nguyen Le Thu

<b>Yêu cầu:</b>
 YÊU CẦU & MỤC TIÊU
 Giao diện: Java Spring Boot (Web)

 Chức năng quản lý: Sách (Book) và Người dùng (User)

 Hỗ trợ xử lý mượn/trả sách qua phiếu mượn (BorrowSlip)

<b>I GIỚI THIỆU :</b>
Đây là ứng dụng Web giúp các thư viện nhỏ quản lý việc mượn – trả sách, quản lý người dùng và sách trong kho. Ứng dụng có giao diện web thân thiện, truy cập được qua trình duyệt.

<b>II Chức năng chính:</b>

 Quản lý sách
  +Thêm, sửa, xóa Sách (Book)

  +Liệt kê danh sách sách

  +Tìm kiếm theo tên, tác giả, nhà xuất bản, số lượng

  Quản lý người dùng
   +Thêm, sửa, xóa Người dùng (User)

   +Hiển thị danh sách người dùng theo ID, email...

  Quản lý phiếu mượn
   +Gán sách cho người dùng thông qua phiếu mượn (BorrowSlip)
   
   +Cho phép mượn/trả sách, tự động tính ngày mượn, hạn trả

   +Kiểm tra sách đã mượn, chưa trả, hoặc quá hạn

 <b>THIẾT KẾ CƠ SỞ DỮ LIỆU:</b>
   Hệ thống được thiết kế theo mô hình hướng đối tượng, mỗi thực thể đều được ánh xạ thành một lớp Java và tương ứng với bảng dữ liệu khi lưu trữ lâu dài.
   Dưới đây là bảng mô tả các thực thể chính:
   
   -User (Người dùng)
| Trường     | Kiểu dữ liệu | Mô tả                        |
| ---------- | ------------ | ---------------------------- |
| `id`       | `int`        | Mã người dùng (tự động tăng) |
| `name`     | `String`     | Tên người dùng               |
| `email`    | `String`     | Địa chỉ email                |
| `password` | `String`     | Mật khẩu đăng nhập           |

   -Book (Sách)
| Trường      | Kiểu dữ liệu | Mô tả                  |
| ----------- | ------------ | ---------------------- |
| `title`     | `String`     | Tên sách               |
| `author`    | `String`     | Tác giả                |
| `publisher` | `String`     | Nhà xuất bản           |
| `numPages`  | `int`        | Số trang               |
| `quantity`  | `int`        | Số lượng còn trong kho |

   -BorrowSlip (Phiếu mượn)
| Trường       | Kiểu dữ liệu | Mô tả                               |
| ------------ | ------------ | ----------------------------------- |
| `bookTitle`  | `String`     | Tên sách đã mượn                    |
| `userId`     | `int`        | ID người dùng mượn sách             |
| `borrowDate` | `LocalDate`  | Ngày mượn sách                      |
| `dueDate`    | `LocalDate`  | Hạn trả sách                        |
| `returned`   | `boolean`    | Đã trả hay chưa (`true` nếu đã trả) |

   -UserBorrowed (Lịch sử mượn sách của người dùng)
    
    +Chứa danh sách các BorrowSlip ứng với mỗi User
   
    +Cho phép tìm kiếm theo tiêu đề sách, lọc sách chưa trả, sắp đến hạn hoặc quá hạn.

 <b>III: CÔNG NGHỆ :</b>
 -CÔNG NGHỆ ĐÃ SỬ DỤNG
   +Frontend (Giao diện):

    Sử dụng Spring Boot kết hợp với công cụ template engine Thymeleaf để dựng giao diện động (HTML động).

    Cho phép hiển thị danh sách sách, người dùng và phiếu mượn trực tiếp trên trình duyệt.

   +Backend (Xử lý logic):

    Viết bằng Java 17.

    Kiến trúc mô hình phân lớp MVC (Model - View - Controller).

   +Lưu trữ dữ liệu:

    Hiện tại dữ liệu được lưu trong bộ nhớ (RAM) bằng Collection như List, Map.

    Định hướng tương lai mở rộng với lưu trữ file nhị phân hoặc kết nối MySQL/SQLite.

   +Công cụ phát triển:

    IDE: Visual Studio Code

    Bộ công cụ hỗ trợ: Java Extension Pack, Live Share, Terminal, Debugger

    Quản lý biên dịch: Maven (dự phòng nếu mở rộng)

   <b>IV. CẤU TRÚC DỰ ÁN :</b>
   
   +Mô hình kiến trúc:
   
    Hệ thống được tổ chức theo mô hình 3 lớp chuẩn:

    [Client Layer]: HTML hiển thị thông qua Thymeleaf.

    [Controller Layer]: Xử lý yêu cầu từ người dùng gửi lên (HTTP Request).

    [Service Layer]: Xử lý logic nghiệp vụ: gán sách cho user, kiểm tra quá hạn, validate nhập sai,...

    [Model Layer]: Gồm các lớp Book, User, BorrowSlip, UserBorrowed.

    (Repository Layer): Dự kiến mở rộng dùng để lưu trữ với CSDL.

   +Các thành phần chính trong project:
| Thành phần     | Vai trò                                                                 |
| -------------- | ----------------------------------------------------------------------- |
| `Book`         | Đại diện cho sách: tiêu đề, tác giả, nhà xuất bản, số trang, tồn kho    |
| `User`         | Người dùng hệ thống: tên, email, mật khẩu                               |
| `BorrowSlip`   | Phiếu mượn: gán sách cho người dùng, ngày mượn, hạn trả, trạng thái trả |
| `UserBorrowed` | Danh sách các phiếu mượn tương ứng với từng người dùng                  |

   +Quy trình xử lý:
     Ví dụ tạo phiếu mượn:
     
User nhập thông tin → Controller nhận request →Service xử lý: kiểm tra tồn kho, gán sách →Tạo BorrowSlip → Gán cho User →Hiển thị danh sách mượn

  <b>V. CẤU TRÚC THƯ MỤC:</b>

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
│   │   │   │   │   ├── repository/                # (interface JPA, nếu có)
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
 <b> VI. MÔ HÌNH VÀ CHỨC NĂNG </b>
  1. Mô hình hệ thống – Kiến trúc MVC (Model - View - Controller)

  Ứng dụng Quản lý thư viện sách số hóa được xây dựng theo mô hình MVC, gồm 3 thành phần chính:
 
  +Model (Dữ liệu)
  
   Chứa các lớp đại diện cho các thực thể trong hệ thống:

   User – người dùng (có thể là người mượn sách)

   Book – sách trong thư viện

   BorrowSlip – phiếu mượn sách (gồm ngày mượn, hạn trả, trạng thái)

   BorrowSlipNotice – đối tượng trung gian hiển thị thông báo sắp đến hạn trả

   Các lớp hỗ trợ như UserBorrowed, Check, LibrarySystem, UserManagement...

+View (Giao diện)
   Sử dụng Thymeleaf để hiển thị các trang HTML động, ví dụ:

   Trang hiển thị danh sách sách đã mượn (user-borrowed.html)

   Trang thông báo sách sắp đến hạn trả

   (Có thể mở rộng thêm: trang thêm user, sách, quản lý phiếu mượn...)
 
+Controller (Điều hướng và xử lý)

   Các controller tiếp nhận yêu cầu từ người dùng qua URL, xử lý logic và trả dữ liệu về View:

   UserBorrowedController: Điều phối hiển thị danh sách sách đã mượn và thông báo sách sắp đến hạn trả.

   Các lớp UserBorrowed, LibrarySystem hoạt động như Service hỗ trợ xử lý logic trung gian

 2. Chức năng hệ thống (chia theo từng mô-đun)
  
+Sách (Book)

   Thêm sách mới vào hệ thống (LibrarySystem.addBook())

   Kiểm tra và cập nhật trạng thái sách đang được mượn hay không

   Hiển thị thông tin sách theo phiếu mượn

   Tìm sách theo tiêu đề (Check.findBookByTitle())

 +Phiếu mượn (BorrowSlip)

   Tạo phiếu mượn sách (LibrarySystem.MuonSach())

   Kiểm tra trạng thái quá hạn (Check.checkAndDisplayOverdue())

   Thông báo sách sắp đến hạn trả trong 3 ngày (UserBorrowed.notifyUpcomingDueDates())

   Lưu danh sách phiếu mượn (UserBorrowed.addBorrowSlip())

 +Người dùng (User)
   
   Thêm, sửa, xóa người dùng (UserManagement)

   Tìm người dùng theo ID (Check.findUserById())

   Hiển thị sách đã mượn theo người dùng (UserBorrowed.displayUserBorrowedBooks())
   
 +Thống kê và thông báo

   Thống kê số sách sắp đến hạn trả (giao diện và dòng lệnh)

   Liệt kê phiếu mượn quá hạn (console)

   Hiển thị danh sách sách đã mượn cho từng người dùng

  3. Luồng xử lý chức năng tiêu biểu

     Ví dụ: Mượn sách

Người dùng được thêm hoặc đã tồn tại

Người dùng chọn sách muốn mượn (phải còn tồn kho)

Nhập ngày mượn và hạn trả

Gọi MuonSach(user, bookTitle, borrowDate, dueDate)

Hệ thống kiểm tra tình trạng sách:

Nếu sách không tồn tại → thông báo lỗi

Nếu sách đã bị mượn → từ chối mượn

Nếu hợp lệ → tạo BorrowSlip, thêm vào danh sách

Cập nhật trạng thái sách thành “đang mượn”

Thông báo thành công






