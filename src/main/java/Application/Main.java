package Application;

import DataBase.AttendanceRepository.AttendanceMySqlRepository;
import DataBase.AttendanceRepository.AttendanceRepository;
import DataBase.PackageRepository.PackageMySqlRepository;
import DataBase.PackageRepository.PackageRepository;
import DataBase.PaymentRepository.PaymentMySqlRepository;
import DataBase.PaymentRepository.PaymentRepository;
import DataBase.UserRepository.UserMySqlRepository;
import DataBase.UserRepository.UserRepository;
import Models.Attendance;
import Models.Packages;
import Models.Payment;
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
        PackageRepository PacakgeMySqlRepository = new PackageMySqlRepository(dbConnection.getConnection(),dbConnection.getStatement());
        PaymentRepository PaymentMySqlRepository = new PaymentMySqlRepository(dbConnection.getConnection(),dbConnection.getStatement());

        
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
                Users returnedUser = userMySQLRepository.addUser(addedUser);
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
                Users returnedUser = userMySQLRepository.updatetUser(id, updatedUser);
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
                Attendance returnedAttendance = AttendanceMySqlRepository.addAttendance(addedAttendance);
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
                Attendance returnedAttendance = AttendanceMySqlRepository.updateAttendance(id,updatedAttendance);
                return gson.toJson(returnedAttendance);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });
         //end of attendaces api
        //-------------------------------------------------------//
        
        // packages apis
        //---------------------------------------------//
        //get all packages
         get("/packages", (req, res) -> {
            //WIP
            try {
                ArrayList<Packages> allPackages = PacakgeMySqlRepository.getAllPackages();
                if (allPackages == null) {
                    return "no Packages found";
                } else {
                    return gson.toJson(allPackages);
                }
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });
         
         // add new package
           post("/package", (req, res) -> {
            try {
                String newPackage = req.body();
                Packages addedPackage = gson.fromJson(newPackage, Packages.class);
                Packages returnedPackage = PacakgeMySqlRepository.addPackage(addedPackage);
                return gson.toJson(returnedPackage);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });
           
              // update package by id
        put("package/:id", (req, res) -> {
            try {
                String packageId = req.params("id");
                int id = Integer.parseInt(packageId);
                String newpackageData = req.body();
                Packages updatedpackage = gson.fromJson(newpackageData,Packages.class);
                Packages returnedpackage = PacakgeMySqlRepository.updatePackage(id, updatedpackage);
                return gson.toJson(returnedpackage);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });
            // delete package by id
        delete("/package/:id", (req, res) -> {
            try {
                String packageId = req.params("id");
                int id = Integer.parseInt(packageId);
                Packages deletedpackage = PacakgeMySqlRepository.removePackage(id);
                if (deletedpackage == null) {
                    return "no attendance found with this id";
                }
                return gson.toJson(deletedpackage);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });
           // payment apis
        //---------------------------------------------//
        //get all payment
         get("/payment", (req, res) -> {
            //WIP
            try {
                ArrayList<Payment> allPayments = PaymentMySqlRepository.getAllPayments();
                if (allPayments == null) {
                    return "no Payments found";
                } else {
                    return gson.toJson(allPayments);
                }
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });
         
         // add new payment
           post("/payment", (req, res) -> {
            try {
                String newPayment = req.body();
                return null;
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });
           
              // update payment by id
        put("payment/:id", (req, res) -> {
            try {
                String paymentId = req.params("id");
                int id = Integer.parseInt(paymentId);
                String newpaymentData = req.body();
                return null;
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }
        });
            // delete payment by id
        delete("/payment/:id", (req, res) -> {
            try {
                String paymentId = req.params("id");
                int id = Integer.parseInt(paymentId);
                Payment deletedpayment = PaymentMySqlRepository.removePayment(id);
                if (deletedpayment == null) {
                    return "no attendance found with this id";
                }
                return gson.toJson(deletedpayment);
            } catch (Exception ex) {
                System.out.print("caught in main" + ex);
                return ex;
            }

        });


    }
}
