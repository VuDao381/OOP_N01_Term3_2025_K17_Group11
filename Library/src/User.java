public class User {
    private String username;
    private String useremail;
    private String userpassword;

    public User(String name, String email, String password) {
        this.username = name;
        this.useremail = email;
        this.userpassword = password;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return useremail;
    }

    public String getPassword() {
        return userpassword;
    }

    public void setUser(String name, String email, String password) {
        this.username = name;
        this.useremail = email;
        this.userpassword = password;
    }

    public void displayUserInfo() {
        System.out.println("Name: " + username);
        System.out.println("Email: " + useremail);
    }
}
