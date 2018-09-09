package DataBase.UserRepository;

import Models.Users;
import java.util.ArrayList;

abstract public class UserRepository {

    abstract public ArrayList<Users> getAllUsers();

    abstract public Users addUser(String userName, String password);

    abstract public Users updatetUser(int id , String userName , String mobile);

    abstract public Users removeUser(int id);

}
