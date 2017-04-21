package model.instruction;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Defines details of a given financial instruction
 */
class InstructionDetails {
    private final Currency currency;

    private final BigDecimal agreedFx;

    private final int units;

    private final BigDecimal pricePerUnit;

    private final BigDecimal tradeAmount;

    InstructionDetails(Currency currency, BigDecimal agreedFx, int units, BigDecimal pricePerUnit) {
        this.currency = currency;
        this.agreedFx = agreedFx;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.tradeAmount = calculateAmount(this);
    }

    private static BigDecimal calculateAmount(InstructionDetails ins) {
        return ins.getPricePerUnit()
                .multiply(BigDecimal.valueOf(ins.getUnits()))
                .multiply(ins.getAgreedFx());
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public int getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
