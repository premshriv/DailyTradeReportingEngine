package business.calculator;

import business.days.GulfWorkingDays;
import business.days.DefaultWorkingDays;
import business.days.IWorkingDays;
import model.instruction.Instruction;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

/**
 * Determines settlement date based on Currency Code
 */
public class InstructionSettlementDateCalculator {


    public static void calculateSettlementDates(Set<Instruction> instructions) {
        instructions.forEach(InstructionSettlementDateCalculator::calculateSettlementDate);
    }

    public static void calculateSettlementDate(Instruction instruction) {
        final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(instruction.getCurrency());

        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(instruction.getSettlementDate());

        if (newSettlementDate != null) {
            instruction.setSettlementDate(newSettlementDate);
        }
    }

    private static IWorkingDays getWorkingDaysStrategy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return GulfWorkingDays.getInstance();
        }
        return DefaultWorkingDays.getInstance();
    }
}
