package business.days;

import java.time.LocalDate;

public interface IWorkingDays {
    LocalDate findFirstWorkingDate(LocalDate date);
}
