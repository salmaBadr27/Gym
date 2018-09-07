package DataBase;

import Models.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersMySqlRepository extends UsersRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public UsersMySqlRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public ArrayList<Users> getAllUsers() {
        ArrayList allUsers = new ArrayList();
        try {
            String selectQuery = "select * from users";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records founds");
            } else {
                do {
                    int userId = result.getInt("userId");
                    String userName = result.getString("user_name");
                    String mobile = result.getString("mobile");
                    Users user = new Users(userId, userName, mobile);
                    allUsers.add(user);
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("some thing went wrong" + ex);

        }
        return allUsers;
    }

    @Override
    public Users addUser(String userName, String mobile) {
        try {
            statement.executeUpdate("INSERT INTO users (userId,user_name , mobile) VALUES (NULL,'" + userName + "' , '" + mobile + "' ");
            String selectQuery = "select userId from users where users_name='" + userName + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int userId = result.getInt("userId");
                    Users newUser = new Users(userId, userName, mobile);
                    return newUser;
                } while (result.next());

            }
        } catch (SQLException ex) {
            System.out.print("error" + ex);
        }
        return null;
    }

    @Override
    public Users removeUserByUserID(int id) {
        try {
            String selectQuery = "select * from users where userId = '" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int userId = result.getInt("userId");
                    String userName = result.getString("user_name");
                    String mobile = result.getString("mobile");
                    Users deletedUser = new Users(userId, userName, mobile);
                    statement.executeUpdate("Delete from users where userId = '" + id + "'");
                    return deletedUser;
                } while (result.next());

            }

        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

    @Override
    public Users updatetUserByUserID(int id, String userName, String mobile) {
        try {
            String selectQuery = "select*from users where userId= '" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int userId = result.getInt("userId");
                    String username = result.getString("user_name");
                    String mob = result.getString("mobile");
                    if (userName.equals(username) && mobile.equals(mob)) {
                        statement.executeUpdate("insert into users ('userId','user_name','mobile')VALUES('" + userId + "','" + username + "','" + mob + "')ON DUPLICATE KEY UPDATE user_name='" + username + "', mobile='" + mob + "'");
                        Users sameUser = new Users (userId,username,mob);
                        return sameUser;
                    } else if (userName.equals(username) && !(mobile.equals(mob))) {
                        statement.executeUpdate("insert into users ('userId','user_name','mobile')VALUES('" + userId + "','" + username + "','" + mob + "')ON DUPLICATE KEY UPDATE user_name='" + username + "', mobile='" + mobile + "'");
                    } else if (!(userName.equals(username)) && mobile.equals(mob)) {
                        statement.executeUpdate("insert into users ('userId','user_name','mobile')VALUES('" + userId + "','" + username + "','" + mob + "')ON DUPLICATE KEY UPDATE user_name='" + userName + "', mobile='" + mob + "'");
                    } else {
                        statement.executeUpdate("Update users set user_name = '" + userName + "',mobile='" + mobile + "'");
                    }
                } while (result.next());

            }
        } catch (SQLException ex) {

            System.out.print("someThing Went Wrong" + ex);

        }
        return null;
    }

}
