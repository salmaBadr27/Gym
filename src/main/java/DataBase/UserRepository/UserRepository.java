package DataBase.UserRepository;

import Models.Users;
import java.util.ArrayList;

abstract public class UserRepository {

    abstract public ArrayList<Users> getAllUsers();

    abstract public Users getUserById(int id);

    abstract public Users getUserByUserName(String userName);

    abstract public Users addUser(Users newUser);

    abstract public Users updatetUser(int id, Users newUserInfo);

    abstract public Users removeUser(int id);

    abstract public String getUpdatedValue(boolean condition, String newValue, String columnName);

}
