import org.trello4j.core.TrelloTemplate;
import org.trello4j.model.Action;
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
    private static final String TO_LIST_NAME = "To Do";


    public static void main(String[] args) {

        List<Member> teamMembers = getTeamMembers(TEAM_SHORTNAME);
        Map<Member, Board> membersPersonalBoards = new HashMap<Member, Board>();
        Board personalBoardInTeam;
        List<Card> memberCards;
        List<org.trello4j.model.List> boardList;
        String IdList;

        for (Member m : teamMembers) {
            //1 check board on exist
            //??? 2 check board on update
            //3 create personal board
            personalBoardInTeam = createPersonalBoardInTeam(m, TEAM_SHORTNAME);
            //4 add member with admin permission
            addAdminMemberOnBoard(m, personalBoardInTeam);
            //put in local map just added personal board
            membersPersonalBoards.put(m, personalBoardInTeam);
            //get list of all boardLists in just added personal board
            boardList = new TrelloTemplate(API_KEY,API_TOKEN).boundBoardOperations(personalBoardInTeam.getId()).getList();
            IdList = boardList.get(0).getId(); // choose first
            //5 list personal board one-to-one
            //6 get all member task in list
            //6.1 get list of all members card from all non-personal boards
            //6.2 get all cards from all non-personal boards
            // TODO: 14.01.2017 filter personal boards , now there are all cards in list
            memberCards = new TrelloTemplate(API_KEY, API_TOKEN).boundMemberOperations(m.getUsername()).getCards();
            //7 copy all cards in personal board
            // TODO: 14.01.2017 to change null-parameters in method for right coping
            // TODO: 14.01.2017 to add member on board if here was not there
            for (Card memberCard : memberCards) {
                createCard(getBoardName(
                        memberCard.getIdBoard()),
                        /*personalBoardInTeam.getName(),*/
                        IdList,
                        memberCard.getName(),
                        memberCard.getDesc(),
                        "0",
                        memberCard.getId());
            }
            //7.1 // TODO: 14.01.2017 update card with PUT
            //8 observe all changes in personal and non-personal boards
            //feature: observe all messages in slack or other
            //9 synchronize all changes in all cards in in personal and non-personal boards

        }


//        createCard("Name","Desc","0");
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

    private static void addAdminMemberOnBoard(Member m, Board board) {
        new TrelloTemplate(API_KEY, API_TOKEN).boundBoardOperations(board.getId()).addMemberOnBoard(m.getId(), "admin");
    }

    private static Board createPersonalBoardInTeam(Member m, String team) {
        return new TrelloTemplate(API_KEY, API_TOKEN).boundMemberOperations(m.getId()).createBoard(m.getUsername(), team, "org");
    }

    private static List<Member> getTeamMembers(String teamIdOrShortName) {
        return new TrelloTemplate(API_KEY, API_TOKEN).boundOrganizationOperations(teamIdOrShortName).getMembers();
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

    /*private static void createTrelloCard(Card card) {
        String fullCardName = getBoardName(BOARD_ID) + " " + card.getName() + " (test)";
        String position = "" + card.getPos();
        new TrelloTemplate(API_KEY, API_TOKEN).boundListOperations(ID_LIST).createCard(fullCardName,
                null, null, position, null, null, null, null);
    }*/

    private static void updateTrelloCard(Card card) {
        String fullCardName = "*" + /*getBoardName(BOARD_ID) + " " +*/ card.getName();
        new TrelloTemplate(API_KEY, API_TOKEN).boundCardOperations(card.getId()).setNewName(fullCardName);
    }


    private static void createCard(String boardFrom, /*String board, */String boardList, String cardName, String description, String position, String cardSourceId) {
        //CardService: Add Card using POST
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("desc", description);

        String fullCardName = boardFrom + " " + cardName;

        Card card = new TrelloTemplate(API_KEY, API_TOKEN).boundListOperations(boardList).createCard(fullCardName,
                description, null, position, null, null, cardSourceId, null);
    }
}
