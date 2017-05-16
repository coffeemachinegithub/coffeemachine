package fr.carbonit.coffeemachine.service;

import java.math.BigDecimal;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.hardware.DrinkMaker;

public class CoffeMachine {

    private static final String MISSING_MONEY_MESSAGE = "Not enough money. Missing: %s€";

    private DrinkMakerProtocol drinkMakerProtocol;
    private DrinkMaker drinkMaker;
    private Reporting reporting;

    public CoffeMachine(DrinkMakerProtocol drinkMakerProtocol, DrinkMaker drinkMaker, Reporting reporting) {
        this.drinkMakerProtocol = drinkMakerProtocol;
        this.drinkMaker = drinkMaker;
        this.reporting = reporting;
    }

    public void command(DrinkCommand command, double money) {
        double missingMoney = computeMissingMoney(command, money);

        if (missingMoney > 0) {
            drinkMaker.make(drinkMakerProtocol.convertMessage(String.format(MISSING_MONEY_MESSAGE, missingMoney)));
        } else {
            handleStick(command);
            drinkMaker.make(drinkMakerProtocol.convert(command));
            reporting.report(command, money);
        }
    }

    private double computeMissingMoney(DrinkCommand command, double money) {
        return BigDecimal.valueOf(command.getType().getPrice()).subtract(BigDecimal.valueOf(money)).doubleValue();
    }

    private void handleStick(DrinkCommand command) {
        command.withStick(command.hasSugar());
    }

}
