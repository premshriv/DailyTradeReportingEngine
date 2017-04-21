package model.instruction;

public enum TradeAction {
    BUY("B"),
    SELL("S");

    private String code;

    TradeAction(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static TradeAction fromString(String code) {

        if (code != null) {
            for (TradeAction tmp : TradeAction.values()) {
                if (code.equalsIgnoreCase(tmp.code)) {
                    return tmp;
                }
            }

            throw new IllegalArgumentException("No TradeAction constant with code " + code + " found!");
        } else {
            throw new NullPointerException("Null pointer supplied instead of TradeAction");
        }
    }
}
