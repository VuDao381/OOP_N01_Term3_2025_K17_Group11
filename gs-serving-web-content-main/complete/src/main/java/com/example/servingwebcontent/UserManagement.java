package com.example.servingwebcontent;
// UserManagement.java
import java.util.ArrayList;

public class UserManagement {

    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        try {
            users.add(user);
            System.out.println("Thêm người dùng thành công.");
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm người dùng: " + e.getMessage());
        } finally {
            System.out.println("Đã xử lý xong thao tác thêm người dùng.");
        }
    }

    public boolean editUser(int userId, String newName, String newEmail, String newPassword) {
        try {
            for (User user : users) {
                if (user.getUID() == userId) {
                    user.setUser(newName, newEmail, newPassword);
                    System.out.println("Cập nhật người dùng thành công.");
                    return true;
                }
            }
            System.out.println("Không tìm thấy người dùng để cập nhật.");
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật người dùng: " + e.getMessage());
        } finally {
            System.out.println("Đã xử lý xong thao tác cập nhật người dùng.");
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        try {
            boolean removed = users.removeIf(user -> user.getUID() == userId);
            if (removed) {
                System.out.println("Xóa người dùng thành công.");
            } else {
                System.out.println("Không tìm thấy người dùng để xóa.");
            }
            return removed;
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa người dùng: " + e.getMessage());
        } finally {
            System.out.println("Đã xử lý xong thao tác xóa người dùng.");
        }
        return false;
    }

    public void printUsers() {
        try {
            if (users.isEmpty()) {
                System.out.println("Danh sách người dùng trống.");
                return;
            }

            for (User user : users) {
                System.out.println("ID: " + user.getUID() +
                        " | Tên: " + user.getName() +
                        " | Email: " + user.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị danh sách người dùng: " + e.getMessage());
        } finally {
            System.out.println("Đã xử lý xong thao tác in danh sách người dùng.");
        }
    }

    public boolean isEmpty() {
        try {
            return users.isEmpty();
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra danh sách rỗng: " + e.getMessage());
            return true; // giả định rỗng nếu có lỗi
        } finally {
            System.out.println("Đã xử lý xong thao tác kiểm tra danh sách rỗng.");
        }
    }
}
