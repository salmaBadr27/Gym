package DataBase.AttendanceRepository;

import java.util.ArrayList;
import Models.Attendance;
import java.sql.Timestamp;


abstract public class AttendanceRepository {

    abstract public ArrayList<Attendance> getAllAttendance();

    abstract public Attendance getAttendanceById(int id);

    abstract public Attendance getAttendanceByDate(Timestamp date);

    abstract public Attendance addAttendance(Attendance newAttendance);

    abstract public Attendance removeAttendance(int id);

    abstract public Attendance updateAttendance(int id, Attendance newAttendanceInfo);

    abstract public String getUpdatedValue(boolean condition, String newValue , String columnName);

}
