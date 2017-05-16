package fr.carbonit.coffeemachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.carbonit.coffeemachine.domain.DrinkCommand;
import fr.carbonit.coffeemachine.domain.DrinkType;

public class Reporting {

    private static final String REPORT_LINE = "%s: %s€";

    private static class ReportDation {
        private DrinkType type;
        private double money;

        private ReportDation(DrinkType type, double money) {
            this.type = type;
            this.money = money;
        }
    }

    private List<ReportDation> data = new ArrayList<>();

    public void report(DrinkCommand command, double money) {
        data.add(new ReportDation(command.getType(), money));
    }

    public String generateReport() {
        Map<DrinkType, Double> summaryMap = groupDataByDrinkTypeAndSumMoney();
        return summaryMap.entrySet()
                         .stream()
                         .sorted((e1, e2) -> e1.getKey().name().compareTo(e2.getKey().name()))
                         .map(e -> String.format(REPORT_LINE, e.getKey(), e.getValue()))
                         .collect(Collectors.joining("\n"));
    }

    private Map<DrinkType, Double> groupDataByDrinkTypeAndSumMoney() {
        return data.stream().collect(Collectors.groupingBy(dation -> dation.type, Collectors.summingDouble(dation -> dation.money)));
    }

}
