package org.scratch.game.service.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.scratch.game.model.config.Config;
import util.ConfigTestUtils;

class RandomMatrixGeneratorTest {

    Config config = ConfigTestUtils.getConfig();

    MatrixGenerator generator = new RandomMatrixGenerator(config);

    public RandomMatrixGeneratorTest() throws Exception {
    }

    @Test
    void matrixGeneratorTest() {
        var matrix = generator.generate();

        Assertions.assertEquals(config.rows(), matrix.length);
        Assertions.assertEquals(config.columns(), matrix[0].length);
    }
}
