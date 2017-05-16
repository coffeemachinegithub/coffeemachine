package fr.carbonit.coffeemachine.domain;

public enum DrinkType {
    TEA("T", 0.4), COFFEE("C", 0.6), CHOCOLATE("H", 0.5), ORANGE_JUICE("O", 0.6);

    private String code;
    private double price;

    private DrinkType(String code, double price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

}
