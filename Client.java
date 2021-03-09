
/**
 * Client for testing Shift class and its methods.
 * 
 * @version 1.0
 * @author Aatu Laitinen
*/

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Client {
    public static void main(String[] args) {
        // creates test date
        LocalDateTime ldt = LocalDateTime.of(2020, Month.MARCH, 9, 00, 00);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DecimalFormat df = new DecimalFormat("0.00");

        // test 1, correct values
        String start = "8:30";
        String end = "16:00";
        System.out.println("\ndate: " + ldt.format(formatter) + " time: " + start + " - " + end);

        Shift shift1 = new Shift(ldt, start, end);
        System.out.println("Shift is " + (shift1.validate() ? "valid" : "not valid"));
        Double result = shift1.getShiftDuration();
        System.out.println("Shift duration: " + df.format(result) + "h");

        // test 2, invalid starting time

        start = "16:11";
        end = "11:00";
        System.out.println("\ndate: " + ldt.format(formatter) + " time: " + start + " - " + end);

        Shift shift2 = new Shift(ldt, start, end);
        System.out.println("Shift is " + (shift2.validate() ? "valid" : "not valid"));
        result = shift2.getShiftDuration();
        System.out.println("Shift duration: " + df.format(result) + "h");

        // test 3, negative start time

        start = "-5:11";
        end = "11:00";
        System.out.println("\ndate: " + ldt.format(formatter) + " time: " + start + " - " + end);

        Shift shift3 = new Shift(ldt, start, end);
        System.out.println("Shift is " + (shift3.validate() ? "valid" : "not valid"));

        // test 4, invalid characters

        start = "16:notnumber";
        end = "11:00";
        System.out.println("\ndate: " + ldt.format(formatter) + " time: " + start + " - " + end);

        Shift shift4 = new Shift(ldt, start, end);
        System.out.println("Shift is " + (shift4.validate() ? "valid" : "not valid"));
        System.out.println("\n");
    }
}
