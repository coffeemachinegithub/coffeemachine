package fr.carbonit.coffeemachine.service;

import java.math.BigDecimal;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.domain.DrinkType;
import fr.carbonit.coffeemachine.hardware.BeverageQuantityChecker;
import fr.carbonit.coffeemachine.hardware.DrinkMaker;
import fr.carbonit.coffeemachine.hardware.EmailNotifier;

public class CoffeMachine {

    private static final String MISSING_MONEY_MESSAGE = "Not enough money. Missing: %s€";
    private static final String SHORTAGE_MESSAGE = "Shortage of %s";

    private DrinkMakerProtocol drinkMakerProtocol;
    private DrinkMaker drinkMaker;
    private Reporting reporting;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    public CoffeMachine(DrinkMakerProtocol drinkMakerProtocol, DrinkMaker drinkMaker, Reporting reporting, BeverageQuantityChecker beverageQuantityChecker, EmailNotifier emailNotifier) {
        this.drinkMakerProtocol = drinkMakerProtocol;
        this.drinkMaker = drinkMaker;
        this.reporting = reporting;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.emailNotifier = emailNotifier;
    }

    public void command(DrinkCommand command, double money) {
        double missingMoney = computeMissingMoney(command, money);

        if (missingMoney > 0) {
            handleMissingMoney(missingMoney);

        } else if (hasShortage(command.getType())) {
            handleShortage(command.getType());

        } else {
            handleSendCommand(command, money);
        }
    }

    private double computeMissingMoney(DrinkCommand command, double money) {
        return BigDecimal.valueOf(command.getType().getPrice()).subtract(BigDecimal.valueOf(money)).doubleValue();
    }

    private void handleMissingMoney(double missingMoney) {
        drinkMaker.make(drinkMakerProtocol.convertMessage(String.format(MISSING_MONEY_MESSAGE, missingMoney)));
    }

    private boolean hasShortage(DrinkType type) {
        return beverageQuantityChecker.isEmpty(type.name());
    }

    private void handleShortage(DrinkType type) {
        drinkMaker.make(drinkMakerProtocol.convertMessage(String.format(SHORTAGE_MESSAGE, type)));
        emailNotifier.notifyMissingDrink(type.name());
    }

    private void handleSendCommand(DrinkCommand command, double money) {
        handleStick(command);
        drinkMaker.make(drinkMakerProtocol.convert(command));
        reporting.report(command, money);
    }

    private void handleStick(DrinkCommand command) {
        command.withStick(command.hasSugar());
    }

}
