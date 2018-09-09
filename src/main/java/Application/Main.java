package Application;

import DataBase.AttendanceRepository.AttendanceMySqlRepository;
import DataBase.AttendanceRepository.AttendanceRepository;
import DataBase.UserRepository.UserMySqlRepository;
import DataBase.UserRepository.UserRepository;
import Models.Attendance;
import Models.Users;
import Utility.DBConnection;
import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {
        Gson gson = new Gson();
        DBConnection dbConnection = new DBConnection();
        UserRepository userMySQLRepository = new UserMySqlRepository(dbConnection.getConnection(), dbConnection.getStatement());
        AttendanceRepository AttendanceMySqlRepository = new AttendanceMySqlRepository(dbConnection.getConnection(), dbConnection.getStatement());
              // Users apis
        //-------------------//
        //get allUsers api
        get("/users", (req, res) -> {
            //WIP
            try {
                ArrayList<Users> allUsers = userMySQLRepository.getAllUsers();
                if (allUsers == null) {
                    return "no users found";
                } else {
                    return gson.toJson(allUsers);
                }
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });

        //add new user api
        post("/user", (req, res) -> {
            try {
                String newUser = req.body();
                Users addedUser = gson.fromJson(newUser, Users.class);
                Users returnedUser = userMySQLRepository.addUser(addedUser.getUserName(), addedUser.getMobile());
                return gson.toJson(returnedUser);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });
        // delete user by user id 
        delete("/user/:id", (req, res) -> {
            try {
                String userId = req.params("id");
                int id = Integer.parseInt(userId);
                Users deletedUser = userMySQLRepository.removeUser(id);
                if (deletedUser == null) {
                    return " no users found with this id ";
                }
                return gson.toJson(deletedUser);
            } catch (Exception ex) {

                System.out.print("caught in main" + ex);
                return ex;
            }
        });

        //update user by user id 
        put("/user/:id", (req, res) -> {
            try {
                String userId = req.params("id");
                int id = Integer.parseInt(userId);
                String newUserDate = req.body();
                Users updatedUser = gson.fromJson(newUserDate, Users.class);
                Users returnedUser = userMySQLRepository.updatetUser(id, updatedUser.getUserName(), updatedUser.getMobile());
                return gson.toJson(returnedUser);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });
        // end of users apis
        //----------------------------------------------------------------------------------------------------//

        //attendance apis
        //--------------------------
        // get all attendance
        get("/attendances", (req, res) -> {
            try {
                ArrayList<Attendance> allAttendance = AttendanceMySqlRepository.getAllAttendance();
                if (allAttendance == null) {
                    return "no attendance found";
                }
                return gson.toJson(allAttendance);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });

        //add new attendance
        post("/attendance", (req, res) -> {
            try {
                String newAttendance = req.body();
                System.out.print(newAttendance);
                Attendance addedAttendance = gson.fromJson(newAttendance, Attendance.class);
                Attendance returnedAttendance = AttendanceMySqlRepository.addAttendance(addedAttendance.getStartTime(), addedAttendance.getEndTime(), addedAttendance.getUserName(), addedAttendance.getShiftName(), addedAttendance.getAttendanceDate());
                return gson.toJson(returnedAttendance);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });

        // delete attendance by id
        delete("/attendance/:id", (req, res) -> {
            try {
                String attendanceId = req.params("id");
                int id = Integer.parseInt(attendanceId);
                Attendance deletedAttendance = AttendanceMySqlRepository.removeAttendance(id);
                if (deletedAttendance == null) {
                    return "no attendance found with this id";
                }
                return gson.toJson(deletedAttendance);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });

        // update attendance by id
        put("attendance/:id", (req, res) -> {
            try {
                String attendanceId = req.params("id");
                int id = Integer.parseInt(attendanceId);
                String newdAttendanceData = req.body();
                Attendance updatedAttendance = gson.fromJson(newdAttendanceData,Attendance.class);
                Attendance returnedAttendance = AttendanceMySqlRepository.updateAttendance(id, updatedAttendance.getStartTime(), updatedAttendance.getEndTime(), updatedAttendance.getUserName(), updatedAttendance.getShiftName(), updatedAttendance.getAttendanceDate());
                return gson.toJson(returnedAttendance);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });

    }
}
