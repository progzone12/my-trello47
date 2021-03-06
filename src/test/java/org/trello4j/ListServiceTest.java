package org.trello4j;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.trello4j.core.TrelloTemplate;
import org.trello4j.model.Card;

/**
 * @author Guilherme Lima Pereira
 */
public class ListServiceTest {

    // Note: this url was used to generate token with read, write permissions:
    // https://trello.com/1/authorize?key=23ec668887f03d4c71c7f74fb0ae30a4&name=My+Application&expiration=never&response_type=token&scope=read,write

    private static final String API_KEY = "b8a19fea9c86c56f92b47ba9841826c7";
    private static final String API_TOKEN = "a8379d5621ca307209024191609a5b39a972a3424cfe0c52952158e91c764117";
    private static final String ID_LIST = "5830d2555ea447150f1f081b";

    @Test
    public void testGetCards() {

        // WHEN
        List<Card> cards = new TrelloTemplate(API_KEY, API_TOKEN).boundListOperations(ID_LIST).getCards();
        // THEN
        assertNotNull(cards);
    }
}
