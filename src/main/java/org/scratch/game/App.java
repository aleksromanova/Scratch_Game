package org.scratch.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.scratch.game.model.config.Config;
import org.scratch.game.service.GameService;
import org.scratch.game.service.calculator.ResultCalculatorImpl;
import org.scratch.game.service.generator.RandomMatrixGenerator;

import java.io.File;
import java.io.IOException;

/**
 * Application entrypoint
 */
public class App {
    private static final String CONFIG_ATTRIBUTE = "config";
    private static final String BETTING_AMOUNT_ATTRIBUTE = "betting-amount";

    public static void main(String[] args) {
        var options = new Options();
        options.addOption(null, CONFIG_ATTRIBUTE, true, "Config file path");
        options.addOption(null, BETTING_AMOUNT_ATTRIBUTE, true, "Bet value");
        var parser = new DefaultParser();
        try {
            var cmd = parser.parse(options, args);
            if (cmd.hasOption(CONFIG_ATTRIBUTE)) {
                if (cmd.hasOption(BETTING_AMOUNT_ATTRIBUTE)) {
                    var mapper = new ObjectMapper();
                    var config = mapper.readValue(new File(cmd.getOptionValue(CONFIG_ATTRIBUTE)), Config.class);
                    double betAmount = Double.parseDouble(cmd.getOptionValue(BETTING_AMOUNT_ATTRIBUTE));

                    var generator = new RandomMatrixGenerator(config);
                    var calculator = new ResultCalculatorImpl(config);
                    var gameService = new GameService(generator, calculator);
                    var result = gameService.play(betAmount);
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
                } else {
                    System.out.println("Error: config file path is not found");
                }
            } else {
                System.out.println("Error: bet value is not found");
            }
        } catch (ParseException e) {
            System.out.printf("Wrong input: %s", e.getMessage());
        } catch (IOException e) {
            System.out.printf("Unexpected error: %s", e.getMessage());
        }
    }
}
