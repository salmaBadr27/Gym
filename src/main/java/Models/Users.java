
package Models;

public class Users {
    private int userId ;
    private String userName;
    private String mobile;

    public Users(int userId, String userName, String mobile) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
    }

    public Users(String userName, String mobile) {
        this.userName = userName;
        this.mobile = mobile;
    }

   
    
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "users{" + "userId=" + userId + ", userName=" + userName + ", mobile=" + mobile + '}';
    }
    
    
}
