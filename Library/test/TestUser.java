import java.util.Scanner;

public class TestUser {

    public static void addUserFromInput(UserManagement userList, Scanner scanner) {

        System.out.println("Nhập tên người dùng mới:");
        String name = scanner.nextLine();

        System.out.println("Nhập email người dùng mới:");
        String email = scanner.nextLine();

        System.out.println("Nhập mật khẩu người dùng mới:");
        String password = scanner.nextLine();

        User newUser = new User(name, email, password);
        userList.addUser(newUser);

        System.out.println("Thêm người dùng thành công.");
    }

    public static void testEditDelete() {
    UserManagement userList = new UserManagement();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Nhập số lượng người dùng muốn thêm:");
    int n = 0;
    while (true) {
        try {
            n = Integer.parseInt(scanner.nextLine());
            if (n < 0) {
                System.out.println("Số lượng phải là số nguyên không âm. Nhập lại:");
                continue;
            }
            break;
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số hợp lệ:");
        }
    }

    for (int i = 0; i < n; i++) {
        System.out.printf("Nhập thông tin người dùng thứ %d:\n", i + 1);
        addUserFromInput(userList, scanner);
    }

    if (userList.isEmpty()) {
        System.out.println("Danh sách người dùng trống. Không thể thực hiện sửa hoặc xóa.");
        scanner.close();
        return;
    }

    // Cập nhật người dùng
    System.out.println("Nhập ID người dùng cần sửa:");
    int userID = scanner.nextInt();
    scanner.nextLine(); // Xóa bộ đệm

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
