package DataBase.AttendanceRepository;

import Models.Attendance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AttendanceMySqlRepository extends AttendanceRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public AttendanceMySqlRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public ArrayList<Attendance> getAllAttendance() {
        ArrayList allAttendance = new ArrayList();
        try {
            String selectQuery = "select * from attendance";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records founds");

            } else {
                do {
                    int attendanceId = result.getInt("attendance_Id");
                    int userId = result.getInt("userID");
                    Timestamp date = result.getTimestamp("date");
                    Attendance attendance = new Attendance(attendanceId, userId, date);
                    allAttendance.add(attendance);
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("some thing went wrong" + ex);
        }
        return allAttendance;
    }

    @Override
    public Attendance addAttendance(Attendance newAttendance) {
        try {
            statement.executeUpdate("insert into attendance (date,userID) VALUES ('" + newAttendance.getAttendanceDate() + "','" + newAttendance.getUserId() + "'))");
            Attendance addedAttendance = getAttendanceByDate(newAttendance.getAttendanceDate());
            return addedAttendance;
        } catch (SQLException ex) {
            System.out.print("something went wrong" + ex);
        }
        return null;
    }

    @Override
    public Attendance removeAttendance(int id) {

        try {
            Attendance deletedAttendance = getAttendanceById(id);
            statement.executeUpdate("Delete from attendance where attendance_id='" + id + "'");
            return deletedAttendance;

        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

    @Override
    public Attendance updateAttendance(int id, Attendance newAttendanceInfo) {
        Attendance oldAttendance = getAttendanceById(id);
        try {
            boolean DateCondition = !(oldAttendance.getAttendanceDate() == newAttendanceInfo.getAttendanceDate());
            String updateQuery = "update attendance set '" + getUpdatedValue(DateCondition, newAttendanceInfo.getAttendanceDate().toString(), "date") + "'";
            statement.executeUpdate(updateQuery);
            return new Attendance(oldAttendance.getAttendanceId(), oldAttendance.getUserId(), newAttendanceInfo.getAttendanceDate());
        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return oldAttendance;
    }

    @Override
    public Attendance getAttendanceById(int id) {
        try {
            String selectQuery = "SELECT * from attendance where attendance_id = '" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {

                do {
                    int attendanceId = result.getInt("attendance_Id");
                    int userId = result.getInt("userID");
                    Timestamp date = result.getTimestamp("date");
                    Attendance returnedAttendance = new Attendance(attendanceId, userId, date);
                    return returnedAttendance;
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

    @Override
    public String getUpdatedValue(boolean condition, String newValue, String columnName) {
        try {
            if (condition) {
                return "'" + columnName + "'='" + newValue + "'";
            }
        } catch (Exception e) {
            System.out.print("error" + e);
        }
        return null;
    }

    @Override
    public Attendance getAttendanceByDate(Timestamp date) {
        try {
            String selectQuery = "SELECT * from attendance where date= '" + date + "'";
            statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("mo records found");

            } else {
                do {
                    int attendanceId = result.getInt("attendance_Id");
                    int userId = result.getInt("userID");
                    Timestamp attendanceDate = result.getTimestamp("date");
                    Attendance returnedAttendance = new Attendance(attendanceId, userId, attendanceDate);
                    return returnedAttendance;
                } while (result.next());
            }
        } catch (Exception e) {
            System.out.print("error" + e);
        }
        return null;

    }

}
