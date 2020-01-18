package pl.dabrowska.michalowski.battleships.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.model.service.GameService;
import java.util.Arrays;
import java.util.List;

class ConfigurationTest {

    private Configuration configuration;

    @Test
    void getInstance() {
        // when
        configuration = Configuration.getInstance();

        // then
        Assert.assertNotNull(configuration);
        Assert.assertEquals(Integer.valueOf(10), configuration.getColumns());
        Assert.assertEquals(Integer.valueOf(10), configuration.getRows());
    }

    @Test
    void getRowsLetters() {
        // given
        configuration = Configuration.getInstance();

        // when
        configuration.setRows(3);
        List<Character> letters = configuration.getRowsLetters();
        List<Character> expected = Arrays.asList('A', 'B', 'C');

        //then
        Assert.assertEquals(expected, letters);
    }
}