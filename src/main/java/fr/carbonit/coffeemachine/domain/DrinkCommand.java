package fr.carbonit.coffeemachine.domain;

public class DrinkCommand {

    private DrinkType type;
    private int sugarCount;
    private boolean stick;

    public DrinkCommand(DrinkType type) {
        this.type = type;
    }

    public DrinkType getType() {
        return type;
    }

    public int getSugarCount() {
        return sugarCount;
    }

    public boolean hasSugar() {
        return sugarCount > 0;
    }

    public DrinkCommand withSugar(int sugarCount) {
        this.sugarCount = sugarCount;
        return this;
    }

    public boolean hasStick() {
        return stick;
    }

    public DrinkCommand withStick(boolean stick) {
        this.stick = stick;
        return this;
    }
}
