package DataBase;

import Models.Users;
import java.util.ArrayList;

abstract public class UsersRepository {

    abstract public ArrayList<Users> getAllUsers();

    abstract public Users addUser(String userName, String password);

    abstract public Users updatetUserByUserID(int id , String userName , String mobile);

    abstract public Users removeUserByUserID(int id);

}
