package fr.carbonit.coffeemachine.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.domain.DrinkType;
import fr.carbonit.coffeemachine.hardware.DrinkMaker;

@RunWith(MockitoJUnitRunner.class)
public class CoffeMachineTest {

    @Mock
    private DrinkMakerProtocol drinkMakerProtocol;

    @Mock
    private DrinkMaker drinkMaker;

    @InjectMocks
    private CoffeMachine service;

    @Test
    public void coffeeMachine_should_send_a_drinkMakerProtocol_command_instruction_to_the_drinkMaker_when_enough_money() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE);

        when(drinkMakerProtocol.convert(eq(command))).thenReturn("myConvertedInstruction");

        // Act
        service.command(command, DrinkType.COFFEE.getPrice());

        // Assert
        verify(drinkMaker).make(eq("myConvertedInstruction"));
    }

    @Test
    public void coffeeMachine_should_send_a_drinkMakerProtocol_message_instruction_to_the_drinkMaker_when_not_enough_money() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE);

        when(drinkMakerProtocol.convertMessage(eq("Not enough money. Missing: 0.1€"))).thenReturn("myConvertedMessage");

        // Act
        service.command(command, 0.5);

        // Assert
        verify(drinkMaker).make(eq("myConvertedMessage"));
    }

    @Test
    public void coffeeMachine_should_add_a_stick_when_command_contains_sugar() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE).withSugar(1);

        ArgumentCaptor<DrinkCommand> captor = ArgumentCaptor.forClass(DrinkCommand.class);
        when(drinkMakerProtocol.convert(captor.capture())).thenReturn("myConvertedInstruction");

        // Act
        service.command(command, DrinkType.COFFEE.getPrice());

        // Assert
        DrinkCommand sendedCommand = captor.getValue();
        assertThat(sendedCommand.hasStick()).isTrue();
        verify(drinkMaker).make(eq("myConvertedInstruction"));
    }

}
