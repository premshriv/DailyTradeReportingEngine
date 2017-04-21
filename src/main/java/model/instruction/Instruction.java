package model.instruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Defines an instruction
 */
public class Instruction {

    private final String entity;

    private final TradeAction action;

    private final LocalDate instructionDate;

    private LocalDate settlementDate;

    private final InstructionDetails details;

    public Instruction(
            String entity,
            TradeAction action,
            Currency currency,
            LocalDate instructionDate,
            LocalDate settlementDate,
            BigDecimal agreedFx,
            int units,
            BigDecimal pricePerUnit)
    {
        this(
            entity, action,
                instructionDate, settlementDate,
            new InstructionDetails(currency, agreedFx, units, pricePerUnit));
    }

    public Instruction(
            String entity,
            TradeAction action,
            LocalDate instructionDate,
            LocalDate settlementDate,
            InstructionDetails details)
    {
        this.entity = entity;
        this.action = action;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.details = details;
    }

    public String getEntity() {
        return entity;
    }

    public TradeAction getAction() {
        return action;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setSettlementDate(LocalDate newDate) {
        settlementDate = newDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public InstructionDetails getDetails() {
        return details;
    }

    public Currency getCurrency() {
        return getDetails().getCurrency();
    }

    public BigDecimal getAgreedFx() {
        return getDetails().getAgreedFx();
    }

    public int getUnits() {
        return getDetails().getUnits();
    }

    public BigDecimal getPricePerUnit() {
        return getDetails().getPricePerUnit();
    }

    public BigDecimal getTradeAmount() {
        return getDetails().getTradeAmount()
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public String toString() {
        return entity;
    }
}
