package fr.carbonit.coffeemachine.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.domain.DrinkType;

@RunWith(MockitoJUnitRunner.class)
public class ReportingTest {

    @InjectMocks
    private Reporting service;

    @Test
    public void generateReport_should_group_and_sum_by_drinkType() throws Exception {
        // Arrange
        service.report(new DrinkCommand(DrinkType.COFFEE), DrinkType.COFFEE.getPrice());
        service.report(new DrinkCommand(DrinkType.ORANGE_JUICE), DrinkType.COFFEE.getPrice());
        service.report(new DrinkCommand(DrinkType.CHOCOLATE), DrinkType.COFFEE.getPrice());
        service.report(new DrinkCommand(DrinkType.COFFEE), DrinkType.COFFEE.getPrice());
        service.report(new DrinkCommand(DrinkType.COFFEE), DrinkType.COFFEE.getPrice());
        service.report(new DrinkCommand(DrinkType.COFFEE), DrinkType.COFFEE.getPrice());
        service.report(new DrinkCommand(DrinkType.COFFEE), DrinkType.COFFEE.getPrice());

        // Act
        String result = service.generateReport();

        // Assert
        assertThat(result).isEqualTo("CHOCOLATE: 0.6€\nCOFFEE: 3.0€\nORANGE_JUICE: 0.6€");
    }

}
