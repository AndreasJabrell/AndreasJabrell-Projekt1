import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Day extends Week {
    public static DateTimeFormatter day = DateTimeFormatter.ofPattern("EEEE");
    public static DateTimeFormatter date = DateTimeFormatter.ofPattern("d");
    public static DateTimeFormatter month = DateTimeFormatter.ofPattern("MMMM");
    public static LocalDate today = LocalDate.now();

    public static void WhatDay() {

        LocalDate findMonday = (today.with(DayOfWeek.MONDAY));
        for (int i = 0; i <= 6; i++) {
            LocalDate setDays = findMonday.plusDays(i);
            arrayLabel[i].setText(setDays.format(day).toUpperCase() + " den " + setDays.format(date) + " " + setDays.format(month));
        }
        //formaterar om veckodag till String för att kunna jämföras med de min array av dagar, att hitta vilken dag det är*/
        String weekDay = today.format(day);
        //skapar en int som tar värdet av dagens datum och gör om till int, - pga amerikansk vecka(börjar med söndag)
        int wk = today.getDayOfWeek().getValue() - 1;
        //tar fram det som är dagens panel i array och sätter det som rosa och fetstilt
        arrayPanel[wk].setBackground(Color.pink);
        arrayLabel[wk].setFont(f2);
    }

    public static void WhatDayNextWeek() {
        //tar in dagens datum, hittar måndagen och plussar på sju dagar för att komma till måndagen veckan efter
        LocalDate today = LocalDate.now();
        LocalDate findMonday = (today.with(DayOfWeek.MONDAY).plusDays(7));
        for (int i = 0; i <= 6; i++) {
            LocalDate setDays = findMonday.plusDays(i);
            arrayLabel[i].setText(setDays.format(day).toUpperCase() + " den " + setDays.format(date) + " " + setDays.format(month));
        }
    }
}
