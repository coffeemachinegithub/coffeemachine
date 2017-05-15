package fr.carbonit.coffeemachine.domain;

public enum DrinkType {
    TEA("T"), COFFEE("C"), CHOCOLATE("H");

    private String code;

    private DrinkType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
