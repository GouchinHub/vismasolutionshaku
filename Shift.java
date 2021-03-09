
/**
 * Class takes the date of shift as LocalDateTime object and start and end times as Strings.
    Has methods for checking if the given shift is valid (between 00:00 - 23:59, not negative, and less than 16 hours.)
    and for returning the duration of the shift (if valid).
 * 
 * @version 1.0
 * @author Aatu Laitinen
*/

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Shift {

    private String start = "";
    private String end = "";
    private LocalDateTime date = null;
    private LocalDateTime shiftStartDate = null;
    private LocalDateTime shiftEndDate = null;
    private Double shiftDuration = 0.0;

    public Shift(LocalDateTime shiftDate, String startTime, String endTime) {
        start = startTime;
        end = endTime;
        date = shiftDate;
    }

    // Method for checking if the shift is valid.
    public boolean validate() {
        try {
            String[] parts = start.split(":");
            // Checks if start time is between 00:00 - 23:59
            if ((shiftStartDate = this.checkTime(date, parts)) == null) {
                return false;
            }
            // Checks if end time is between 00:00 - 23:59
            parts = end.split(":");
            if ((shiftEndDate = this.checkTime(date, parts)) == null) {
                return false;
            }
            // Checks that end time is after start time
            if (shiftStartDate.isBefore(shiftEndDate) == false) {
                return false;
            }
            // Counts the duration of the shift and checks if exceeds 16 hours
            Duration duration = Duration.between(shiftStartDate, shiftEndDate);
            shiftDuration = (double) duration.toMinutes() / 60;
            if (shiftDuration > 16) {
                return false;
            }
            return true;
        } catch (DateTimeException e) {
            // e.printStackTrace(); // can be used to log
            return false;
        }
    }

    // method for getting the duration of the shift, if shift not valid returns 0.
    public double getShiftDuration() {
        if (validate() == true) {
            return shiftDuration;
        } else {
            return 0;
        }

    }

    // method is used by method validate() to check that the given time is in range
    // 00:00 - 23:59.
    private LocalDateTime checkTime(LocalDateTime date, String[] parts) {
        LocalDateTime ldt = null;
        try {
            if (Integer.parseInt(parts[0]) >= 0 && Integer.parseInt(parts[0]) < 23 && Integer.parseInt(parts[1]) >= 0
                    && Integer.parseInt(parts[1]) < 60) {
                ldt = date.plusHours(Integer.parseInt(parts[0]));
                ldt = ldt.plusMinutes(Integer.parseInt(parts[1]));
            } else {
                return null;
            }
            return ldt;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // e.printStackTrace(); // can be used to log
            return null;
        }

    }

}