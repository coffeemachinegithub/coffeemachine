package fr.carbonit.coffeemachine.service;

import fr.carbonit.coffeemachine.domain.DrinkCommand;

public class DrinkMakerProtocol {

    private static final String PROTOCOL_SEPARATOR = ":";
    private static final String EMPTY_VALUE = "";
    private static final String STICK_VALUE = "0";
    private static final String INSTRUCTION_MESSAGE_CODE = "M";
    private static final String EXTRA_HOT_VALUE = "h";

    public String convert(DrinkCommand command) {
        return convertType(command) + convertExtraHot(command) + PROTOCOL_SEPARATOR + convertSugarCount(command) + PROTOCOL_SEPARATOR + convertStick(command);
    }

    private String convertExtraHot(DrinkCommand command) {
        return command.isExtraHot() ? EXTRA_HOT_VALUE : EMPTY_VALUE;
    }

    private String convertType(DrinkCommand command) {
        return command.getType().getCode();
    }

    private String convertSugarCount(DrinkCommand command) {
        return command.hasSugar() ? String.valueOf(command.getSugarCount()) : EMPTY_VALUE;
    }

    private String convertStick(DrinkCommand command) {
        return command.hasStick() ? STICK_VALUE : EMPTY_VALUE;
    }

    public String convertMessage(String message) {
        return INSTRUCTION_MESSAGE_CODE + PROTOCOL_SEPARATOR + message;
    }

}
