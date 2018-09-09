package DataBase.AttendanceRepository;

import java.util.ArrayList;
import Models.Attendance;
import java.sql.Time;
import java.sql.Timestamp;

abstract public class AttendanceRepository {

    abstract public ArrayList<Attendance> getAllAttendance();

    abstract public Attendance addAttendance(Time startTime,Time endTime,String userName,String shiftName,Timestamp date);

    abstract public Attendance removeAttendance(int id);

    abstract public Attendance updateAttendance(int id,Time startTime, Time endTime, String userName, String shiftName, Timestamp date);

}
