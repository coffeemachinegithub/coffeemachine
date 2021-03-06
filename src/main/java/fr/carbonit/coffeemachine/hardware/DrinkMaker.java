package fr.carbonit.coffeemachine.hardware;

public interface DrinkMaker {

    /**
     * Receive order as Drink Maker Protocol.<br />
     * <br />
     * <u>Example:</u> <br />
     * <code>"T:1:0" (Drink maker makes 1 tea with 1 sugar and a
     * stick)</code> <br />
     * <code>"H::" (Drink maker makes 1 chocolate with no sugar - and therefore no
     * stick)</code> <br />
     * <code>"C:2:0" (Drink maker makes 1 coffee with 2 sugars and a stick)</code>
     * <br />
     * <code>"M:message-content" (Drink maker forwards any message received onto the
     * coffee machine interface for the customer to see)</code> <br />
     * <code>"O::" (Drink maker will make one orange juice)</code><br />
     * <code>"Ch::" (Drink maker will make an extra hot coffee with no sugar)</code><br
     * />
     * <code>"Hh:1:0" (Drink maker will make an extra hot chocolate with one sugar and
     * a stick)</code><br />
     * <code>"Th:2:0" (The drink maker will make an extra hot tea with two sugar and a
     * stick)</code>
     * 
     * 
     * @param order
     *            a Drink Maker Protocol order.
     */
    void make(String order);

}
