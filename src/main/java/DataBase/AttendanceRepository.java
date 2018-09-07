package DataBase;

import java.util.ArrayList;
import Models.Attendance;
import java.sql.Time;
import java.util.Date;

abstract public class AttendanceRepository {

    abstract public ArrayList<Attendance> getAllAttendance();

    abstract public Attendance addAttendance(Time startTime,Time endTime,String userName,String shiftName,Date date);

    abstract public Attendance removeAttendanceById(int id);

    abstract public Attendance updateAttendanceById(int id,Time startTime, Time endTime, String userName, String shiftName, Date date);

}
