package business.days;

import java.time.DayOfWeek;

/*
 * Class for setting up Working day for Gulf currencies AED and SAR( Emirati Dirham and Saudi Arabian Riyal)
 */
public class GulfWorkingDays extends WorkingDays {

    private static GulfWorkingDays instance = null;

    public static GulfWorkingDays getInstance() {
        if (instance == null) {
            instance = new GulfWorkingDays();
        }
        return instance;
    }

    private GulfWorkingDays() {
        super();
    }

    @Override
    protected void setupWorkingDays() {
        this.isWorkingDayMap.put(DayOfWeek.SUNDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.FRIDAY, false); // in Gulf countries these are not working days
        this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false); // in Gulf countries these are not working days
    }
}
