
package Models;

import java.util.Date;


public class Shift {
    private int shiftId;
    private String shiftName;
    private Date startDate;
    private Date endDate;

    public Shift(int shiftId, String shiftName, Date startDate, Date endDate) {
        this.shiftId = shiftId;
        this.shiftName = shiftName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

  
    

    public int getShiftId() {
        return shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "shift{" + "shiftId=" + shiftId + ", shiftName=" + shiftName + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
    
    
    
}
