package Models;

import java.sql.Time;
import java.util.Date;

public class Attendance {

    private int attendanceId;
    private String userName;
    private Time startTime;
    private Time endTime;
    private String shiftName;
    private Date attendanceDate;

    public Attendance(int attendanceId, String userName, Time startTime, Time endTime, String shiftName, Date attendanceDate) {
        this.attendanceId = attendanceId;
        this.userName = userName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftName = shiftName;
        this.attendanceDate = attendanceDate;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public String getUserName() {
        return userName;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getShiftName() {
        return shiftName;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    @Override
    public String toString() {
        return "Attendance{" + "attendanceId=" + attendanceId + ", userName=" + userName + ", startTime=" + startTime + ", endTime=" + endTime + ", shiftName=" + shiftName + ", attendanceDate=" + attendanceDate + '}';
    }



   

 

    

   
    

  


}
