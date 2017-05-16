package fr.carbonit.coffeemachine.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.domain.DrinkType;

@RunWith(MockitoJUnitRunner.class)
public class DrinkMakerProtocolTest {

    @InjectMocks
    private DrinkMakerProtocol service;

    @Test
    public void convert_should_return_a_coffee_instruction_from_coffee_command() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE);

        // Act
        String result = service.convert(command);

        // Assert
        assertThat(result).isEqualTo("C::");
    }

    @Test
    public void convert_should_return_a_tea_instruction_from_tea_command() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.TEA);

        // Act
        String result = service.convert(command);

        // Assert
        assertThat(result).isEqualTo("T::");
    }

    @Test
    public void convert_should_return_a_chocolate_instruction_from_chocolate_command() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.CHOCOLATE);

        // Act
        String result = service.convert(command);

        // Assert
        assertThat(result).isEqualTo("H::");
    }

    @Test
    public void convert_should_return_an_one_sugar_instruction_from_one_sugar_command() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE).withSugar(1);

        // Act
        String result = service.convert(command);

        // Assert
        assertThat(result).isEqualTo("C:1:");
    }

    @Test
    public void convert_should_return_a_two_sugars_instruction_from_two_sugars_command() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE).withSugar(2);

        // Act
        String result = service.convert(command);

        // Assert
        assertThat(result).isEqualTo("C:2:");
    }

    @Test
    public void convert_should_return_a_stick_instruction_from_a_stick_command() throws Exception {
        // Arrange
        DrinkCommand command = new DrinkCommand(DrinkType.COFFEE).withStick(true);

        // Act
        String result = service.convert(command);

        // Assert
        assertThat(result).isEqualTo("C::0");
    }

    @Test
    public void convertMessage_should_return_a_message_instruction() throws Exception {
        // Arrange
        String message = "myMessage";

        // Act
        String result = service.convertMessage(message);

        // Assert
        assertThat(result).isEqualTo("M:myMessage");
    }

}
