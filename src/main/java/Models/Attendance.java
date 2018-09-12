package Models;

import java.sql.Timestamp;


public class Attendance {

    private int attendanceId;
    private int userId;
    private Timestamp attendanceDate;

    public Attendance(int attendanceId, int userId, Timestamp attendanceDate) {
        this.attendanceId = attendanceId;
        this.userId = userId;
        this.attendanceDate = attendanceDate;
    }

 
   
    public int getAttendanceId() {
        return attendanceId;
    }

    public int getUserId() {
        return userId;
    }

   
    public Timestamp getAttendanceDate() {
        return attendanceDate;
    }

    @Override
    public String toString() {
        return "Attendance{" + "attendanceId=" + attendanceId + ", userId=" + userId + ", attendanceDate=" + attendanceDate + '}';
    }

    }



   

 

    

   
    

  



