package fr.carbonit.coffeemachine.service;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.hardware.DrinkMaker;

public class CoffeMachine {

    private DrinkMakerProtocol drinkMakerProtocol;
    private DrinkMaker drinkMaker;

    public CoffeMachine(DrinkMakerProtocol drinkMakerProtocol, DrinkMaker drinkMaker) {
        this.drinkMakerProtocol = drinkMakerProtocol;
        this.drinkMaker = drinkMaker;
    }

    public void command(DrinkCommand command) {
        handleStick(command);
        drinkMaker.make(drinkMakerProtocol.convert(command));
    }

    private void handleStick(DrinkCommand command) {
        command.withStick(command.hasSugar());
    }

}
