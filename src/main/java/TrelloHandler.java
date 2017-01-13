import org.trello4j.core.TrelloTemplate;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrelloHandler {
    private static final String USER = "testoctoberwebru";
    private static final String API_KEY = "3ad3df554efd6fca86586146f1311fa6";
    private static final String API_TOKEN = "6c9c6b04d47eb3e2ff393b9c7b1cf3cd7f098e66f87dd1ea688db0dbb2463056";
    private static final String LIST_ID = "backlog";
    private static final String CARD_ID = "5873f9fa7ffeb6515de1e688";
    private static final String ID_LIST = "586ba18216ca1af74fa13ee4";
    private static final String BOARD_ID = "586ba0f770c68e3ba025c662";
    private static final String TEAM_SHORTNAME = "teamforapi";


    public static void main(String[] args) {

        List<Member> teamMembers = getTeamMembers(TEAM_SHORTNAME);
        Map<Member, Board> membersPersonalBoard = new HashMap<Member, Board>();

        for (Member m : teamMembers) {
            //1 check on exist
            //2 check on update
            //3 create board
            Board board = new TrelloTemplate(API_KEY, API_TOKEN).boundMemberOperations(m.getId()).createBoard(m.getUsername(), TEAM_SHORTNAME, "org");
            //4 add member with admin permission
            new TrelloTemplate(API_KEY, API_TOKEN).boundBoardOperations(board.getId()).addMemberOnBoard(m.getId(), "admin");
            //5 list personal board one-to-one
            membersPersonalBoard.put(m,board);
            //6 get all member task in list
            //6.1 get all member card from all non-personal boards
            //6.2 get all cards from all non-personal boards
            //7 copy all cards in personal board
            //8 observe all changes in personal and non-personal boards
            //feature: observe all messages in slack or other
            //9 synchronize all changes in all cards in in personal and non-personal boards

        }


//        createCard();
/*
        for (Card c : getListOfCards(ID_LIST)) {
            System.out.println(c.getName());
        }

        for (Card c : getListOfCards(ID_LIST)) {
//            createTrelloCard(c);
//            updateTrelloCard(c);
        }


        for (Card c : getListOfCards(ID_LIST)) {
            System.out.println(c.getName());
        }
        */
    }

    private static List<Member> getTeamMembers(String teamIdOrShorname) {
        return new TrelloTemplate(API_KEY, API_TOKEN).boundOrganizationOperations(teamIdOrShorname).getMembers();
    }

    private static Card getCardByPosition(int cardPosition) {
        List<Card> cardsInList = getListOfCards(ID_LIST);
        return cardsInList.get(cardPosition);
    }

    private static List<Card> getListOfCards(String idList) {
        return new TrelloTemplate(API_KEY, API_TOKEN).boundListOperations(idList).getCards();
    }

    private static String getBoardName(String boardId) {
        String boardName;
        Board board = new TrelloTemplate(API_KEY, API_TOKEN).boundBoardOperations(boardId).get();
        boardName = board.getName();
        return boardName;
    }

    private static void createTrelloCard(Card card) {
        String fullCardName = getBoardName(BOARD_ID) + " " + card.getName() + " (test)";
        String position = "" + card.getPos();
        new TrelloTemplate(API_KEY, API_TOKEN).boundListOperations(ID_LIST).createCard(fullCardName,
                null, null, position, null, null, null, null);
    }

    private static void updateTrelloCard(Card card) {
        String fullCardName = "*" + /*getBoardName(BOARD_ID) + " " +*/ card.getName();
        new TrelloTemplate(API_KEY, API_TOKEN).boundCardOperations(card.getId()).setNewName(fullCardName);
    }


    private static void createCard() {
//        CardService: Add Card using POST
        String cardName = "0-index card name";
        String description = "this card create from class TrelloHandler " +
                "-> public static void main ";
        String position = "0";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("desc", description);

        String fullCardName = getBoardName(BOARD_ID) + " " + cardName;

        Card card = new TrelloTemplate(API_KEY, API_TOKEN).boundListOperations(ID_LIST).createCard(fullCardName,
                description, null, position, null, null, null, null);

//        assertNotNull(card);
//        assertThat(card.getName(), equalTo(name));
//        assertThat(card.getDesc(), equalTo(description));
    }
}
