package DataBase.UserRepository;

import Models.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserMySqlRepository extends UserRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public UserMySqlRepository(Connection connection, Statement statement) {
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
    public Users addUser(Users newUser) {
        try {
            statement.executeUpdate("INSERT INTO users (user_name , mobile) VALUES ('" + newUser.getUserName() + "' , '" + newUser.getMobile() + "' )");
            Users addedUser = getUserByUserName(newUser.getUserName());
            return addedUser;
        } catch (SQLException ex) {
            System.out.print("error" + ex);
        }
        return null;
    }

    @Override
    public Users removeUser(int id) {
        try {
            Users deletedUser = getUserById(id);
            statement.executeUpdate("Delete from users where userId = '" + id + "'");
            return deletedUser;
        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

    @Override
    public Users updatetUser(int id, Users newUserInfo) {
        Users oldUser = getUserById(id);
        try {
            boolean newNameCondition = !(oldUser.getUserName().equals(newUserInfo.getUserName()));
            boolean newmobCondition = !(oldUser.getMobile().equals(newUserInfo.getMobile()));
            String updateQuery = "update users set "+ getUpdatedValue(newNameCondition, newUserInfo.getUserName(), "user_name")+"where userId ='"+id+"'";
//                    +getUpdatedValue(newmobCondition, newUserInfo.getMobile(), "mobile");
            statement.executeUpdate(updateQuery);
            return new Users (id , newUserInfo.getUserName(),newUserInfo.getMobile());
        } catch (SQLException ex) {
            System.out.print("someThing Went Wrong" + ex);
        }

        return oldUser;
    }

    @Override
    public Users getUserById(int id) {
        try {
            String selectQuery = "select * from users where userId = '" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    String userName = result.getString("user_name");
                    String mobile = result.getString("mobile");
                    Users returnedUser = new Users(id, userName, mobile);
                    return returnedUser;
                } while (result.next());
            }
            return null;
        } catch (SQLException ex) {

            System.out.print("someThing Went Wrong" + ex);

        }
        return null;

    }

    @Override
    public Users getUserByUserName(String userName) {
        try {
            String selectQuery = "select * from users where user_name = '" + userName + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int userId = result.getInt("userId");
                    String username = result.getString("user_name");
                    String mobile = result.getString("mobile");
                    Users returnedUser = new Users(userId, username, mobile);
                    return returnedUser;
                } while (result.next());
            }
            return null;
        } catch (SQLException ex) {

            System.out.print("someThing Went Wrong" + ex);

        }
        return null;
    }

    @Override
    public String getUpdatedValue(boolean condition, String newValue, String columnName) {
        try {
            if (condition) {
                return (" set"+" "+ columnName + "='" + newValue + "'");
            }
        } catch (Exception e) {
            System.out.print("error" + e);
        }
        return "";
    }

}
