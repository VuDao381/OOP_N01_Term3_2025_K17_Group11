import java.util.Scanner;

public class TestUser {

    public static void addUserFromInput(UserManagement userList, Scanner scanner) {
        System.out.println("Nhập ID người dùng mới:");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.println("Nhập tên người dùng mới:");
        String name = scanner.nextLine();

        System.out.println("Nhập email người dùng mới:");
        String email = scanner.nextLine();

        System.out.println("Nhập mật khẩu người dùng mới:");
        String password = scanner.nextLine();

        User newUser = new User(name, id, email, password);
        userList.addUser(newUser);

        System.out.println("Thêm người dùng thành công.");
    }

    public static void testEditDelete() {
        User u1 = new User("Nguyen Thi Lan Anh", 12345, "lananh@example.com", "password123");
        User u2 = new User("Tran Van Minh", 2, "minh@example.com", "pass456");
        User u3 = new User("Nguyen An", 101010, "an@example.com", "pass789");

        UserManagement userList = new UserManagement();
        userList.addUser(u1);
        userList.addUser(u2);
        userList.addUser(u3);

        Scanner scanner = new Scanner(System.in);

        // Thêm người dùng mới
        System.out.println("Bạn có muốn thêm người dùng mới không? (y/n)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            addUserFromInput(userList, scanner);
        }

        // Cập nhật người dùng
        System.out.println("Nhập ID người dùng cần sửa:");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.println("Nhập tên mới:");
        String newName = scanner.nextLine();

        System.out.println("Nhập email mới:");
        String newEmail = scanner.nextLine();

        System.out.println("Nhập mật khẩu mới:");
        String newPassword = scanner.nextLine();

        boolean edited = userList.editUser(userID, newName, newEmail, newPassword);

        if (edited) {
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy người dùng với ID: " + userID);
        }

        System.out.println("Danh sách người dùng hiện tại:");
        userList.printUsers();

        // Xóa người dùng
        System.out.println("\nNhập ID người dùng cần xóa:");
        int deleteID = scanner.nextInt();

        boolean deleted = userList.deleteUser(deleteID);

        if (deleted) {
            System.out.println("Xóa thành công.");
        } else {
            System.out.println("Không tìm thấy người dùng với ID: " + deleteID);
        }

        System.out.println("Danh sách sau khi xóa:");
        userList.printUsers();

        scanner.close();
    }
}
