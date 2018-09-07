package DataBase;

import Models.Attendance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
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
            String selectQuery = "SELECT \n"
                    + "   attendance.attendance_id,  attendance.date,users.user_name,shift.shift_name,shift.start_date,shift.end_date\n"
                    + "FROM \n"
                    + "    attendance\n"
                    + "INNER JOIN \n"
                    + "    users\n"
                    + "ON\n"
                    + "    attendance.userID=users.userId\n"
                    + "     INNER JOIN \n"
                    + "    shift\n"
                    + "    ON\n"
                    + "    attendance.shiftID=shift.shiftId";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records founds");

            } else {

                do {
                    int attendanceId = result.getInt("attendance_Id");
                    Time attendanceStartTime = result.getTime("start_time");
                    Time attendanceEndTime = result.getTime("end_time");
                    String userName = result.getString("user_name");
                    String shiftName = result.getString("shift_name");
                    Date date = result.getDate("date");
                    Attendance attendance = new Attendance(attendanceId, userName, attendanceStartTime, attendanceEndTime, shiftName, date);
                    allAttendance.add(attendance);
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("some thing went wrong" + ex);

        }
        return allAttendance;

    }

    @Override
    public Attendance addAttendance(Time startTime, Time endTime, String userName, String shiftName, Date date) {
        try {
            String insertQuery = "insert into attendance (`attendance_Id`,`date`,`userID`,`shiftID`) VALUES (NULL,'" + date + "',(select userId from users where user_name = '" + userName + "'),(select shiftId from shift where shift_name = '" + shiftName + "'))";

            result = statement.executeQuery(insertQuery);
            String selectQuery = "SELECT \n"
                    + "   attendance.attendance_id,attendance.date,users.user_name,shift.shift_name,shift.start_time,shift.end_time\n"
                    + "FROM \n"
                    + "    attendance\n"
                    + "INNER JOIN \n"
                    + "    users\n"
                    + "ON\n"
                    + "    attendance.userID=users.userId\n"
                    + "     INNER JOIN \n"
                    + "    shift\n"
                    + "    ON\n"
                    + "    attendance.shiftID=shift.shiftId\n "
                    + "    WHERE attendance.date = '" + date + "'";

            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int attendanceId = result.getInt("attendance_Id");
                    Attendance newAttendance = new Attendance(attendanceId, userName, startTime, endTime, shiftName, date);
                    return newAttendance;

                } while (result.next());

            }

        } catch (SQLException ex) {
            System.out.print("something went wrong" + ex);
        }
        return null;
    }

    @Override
    public Attendance removeAttendanceById(int id) {

        try {
            String selectQuery = "SELECT \n"
                    + "   attendance.attendance_id,attendance.date,users.user_name,shift.shift_name,shift.start_time,shift.end_time\n"
                    + "FROM \n"
                    + "    attendance\n"
                    + "INNER JOIN \n"
                    + "    users\n"
                    + "ON\n"
                    + "    attendance.userID=users.userId\n"
                    + "     INNER JOIN \n"
                    + "    shift\n"
                    + "    ON\n"
                    + "    attendance.shiftID=shift.shiftId\n"
                    + "    WHERE attendance.attendance_id = '" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {

                do {
                    int attendanceId = result.getInt("attendance_Id");
                    String userName = result.getString("user_name");
                    String shiftName = result.getString("shift_name");
                    Time attendanceStartTime = result.getTime("start_time");
                    Time attendanceEndTime = result.getTime("end_time");
                    Date date = result.getDate("date");
                    Attendance deletedAttendance = new Attendance(attendanceId, userName, attendanceStartTime, attendanceEndTime, shiftName, date);
                    statement.executeUpdate("Delete from attendance where attendance_id='" + id + "'");
                    return deletedAttendance;
                } while (result.next());

            }

        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

    @Override
    public Attendance updateAttendanceById(int id, Time startTime, Time endTime, String userName, String shiftName, Date date) {
        try {
            String selectQuery = "SELECT\n"
                    + "attendance.*,users.user_name,shift.shift_name,shift.start_time,shift.end_time\n"
                    + "FROM \n"
                    + "attendance\n"
                    + "INNER JOIN \n"
                    + "users\n"
                    + "ON\n"
                    + "attendance.userID=users.userId\n"
                    + "INNER JOIN \n"
                    + "shift\n"
                    + "ON\n"
                    + "attendance.shiftID=shift.shiftId\n"
                    + "WHERE attendance.attendance_id = '" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int attendanceId = result.getInt("attendance_id");
                    Date attendanceDate = result.getDate("date");
                    int userId = result.getInt("userID");
                    int shiftId = result.getInt("shiftID");
                    Time starttime = result.getTime("start_time");
                    Time endtime = result.getTime("end_time");
                    String username = result.getString("user_name");
                    String shift = result.getString("shift_name");
                    boolean normalCondition = userName.equals(username) && startTime.equals(starttime) && endTime.equals(endtime) && shiftName.equals(shift) && date.equals(attendanceDate);
                    boolean normalDateCondition = userName.equals(username) && startTime.equals(starttime) && endTime.equals(endtime) && shiftName.equals(shift) && !(date.equals(attendanceDate));
                    boolean normalStartDateCondition = userName.equals(username) && !(startTime.equals(starttime)) && endTime.equals(endtime) && shiftName.equals(shift) && date.equals(attendanceDate);
                    boolean normalEndDateCondition = userName.equals(username) && startTime.equals(starttime) && !(endTime.equals(endtime)) && shiftName.equals(shift) && date.equals(attendanceDate);
                    boolean normalUserCondition = !(userName.equals(username)) && startTime.equals(starttime) && endTime.equals(endtime) && shiftName.equals(shift) && date.equals(attendanceDate);
                    boolean normalShiftCondition = userName.equals(username) && startTime.equals(starttime) && endTime.equals(endtime) && !(shiftName.equals(shift)) && date.equals(attendanceDate);

                    if (normalCondition) {
                        statement.executeUpdate("insert into attendance ('attendance_id','date','userID',shiftID)VALUES('" + attendanceId + "','" + attendanceDate + "','" + userId + "','" + shiftId + "')ON DUPLICATE KEY UPDATE attendance_id='" + attendanceId + "', date='" + attendanceDate + "',userID='" + userId + "',shiftID='" + shiftId + "'");
                        Attendance sameAttendance = new Attendance(attendanceId, username, starttime, endtime, shift, attendanceDate);
                        return sameAttendance;
                    } else if (normalDateCondition) {
                        statement.executeUpdate("insert into attendance ('attendance_id','date','userID',shiftID)VALUES('" + attendanceId + "','" + date + "','" + userId + "','" + shiftId + "')ON DUPLICATE KEY UPDATE attendance_id='" + attendanceId + "', date='" + attendanceDate + "',userID='" + userId + "',shiftID='" + shiftId + "'");

                    } else if (normalStartDateCondition) {
                        statement.executeUpdate("insert into attendance ('attendance_id','date','userID',shiftID)VALUES('" + attendanceId + "','" + date + "','" + userId + "','" + shiftId + "')ON DUPLICATE KEY UPDATE attendance_id='" + attendanceId + "', date= '" + attendanceDate + "',userID='" + userId + "',shiftID =select shiftId from shift where start_time = '" + startTime + "'");

                    }
                    else if (normalEndDateCondition) {
                        statement.executeUpdate("insert into attendance ('attendance_id','date','userID',shiftID)VALUES('" + attendanceId + "','" + date + "','" + userId + "','" + shiftId + "')ON DUPLICATE KEY UPDATE attendance_id='" + attendanceId + "', date= '" + attendanceDate + "',userID='" + userId + "',shiftID = select shiftId from shift where end_time = '" + endTime + "'");

                    }
                    else if (normalUserCondition) {
                        statement.executeUpdate("insert into attendance ('attendance_id','date','userID',shiftID)VALUES('" + attendanceId + "','" + date + "','" + userId + "','" + shiftId + "')ON DUPLICATE KEY UPDATE attendance_id='" + attendanceId + "', date= '" + attendanceDate + "',userID= select userId from users where user_name = '" + userName + "',shiftID ='"+shiftId+"'");
                    }
                    else if (normalShiftCondition) {
                        statement.executeUpdate("insert into attendance ('attendance_id','date','userID',shiftID)VALUES('" + attendanceId + "','" + date + "','" + userId + "','" + shiftId + "')ON DUPLICATE KEY UPDATE attendance_id='" + attendanceId + "', date= '" + attendanceDate + "',userID= '" + userId + "',shiftID =select shiftId from shift where shift_name = '" + shiftName + "'");
                    }
                    else{
                    statement.executeUpdate("Update attendance set date = '"+date+"',userID = select userId from users where user_name = '"+userName+"',shiftID=select shiftId from shift where shift_name = '"+shiftName+"'");
                    }
                } while (result.next());

            }
        } catch (SQLException ex) {

            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

}
