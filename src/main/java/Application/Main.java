package Application;

import DataBase.AttendanceMySqlRepository;
import DataBase.AttendanceRepository;
import DataBase.UsersMySqlRepository;
import DataBase.UsersRepository;
import Utility.DBConnection;
import static spark.Spark.*;
import com.google.gson.Gson;

public class Main {

    public static void main(String args[]) {
        Gson gson = new Gson();
        DBConnection dbConnection = new DBConnection();
        UsersRepository userMySQLRepository = new UsersMySqlRepository(dbConnection.getConnection(), dbConnection.getStatement());
        AttendanceRepository AttendanceMySqlRepository = new AttendanceMySqlRepository(dbConnection.getConnection(), dbConnection.getStatement());
        post("/user", (req, res) -> {
           //WIP
            return null;

        });
    }
}
