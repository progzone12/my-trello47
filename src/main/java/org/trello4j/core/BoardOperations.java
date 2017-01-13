package org.trello4j.core;

import java.util.List;

import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Checklist;
import org.trello4j.model.Member;
import org.trello4j.model.Organization;
import org.trello4j.model.Prefs;

/**
 *
 * @author joel
 */
public interface BoardOperations {

    //GET
    Board get();

    List<Action> getActions(String... filter);

    Organization getOrganization(String... filter);

    List<Member> getInvitedMembers(String... filter);

    List<Member> getMembers(String... filter);

    List<org.trello4j.model.List> getList(String... filter);

    List<Checklist> getChecklist();

    List<Card> getCards(String... filter);

    Prefs getPrefs();


    //PUT

    boolean addMemberOnBoard(String memberId, String type);

}