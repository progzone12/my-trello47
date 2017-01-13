package org.trello4j.core;

import org.trello4j.model.*;

import java.util.List;

public interface MemberOperations {
    //GET
    Member get(String... filters);

    List<Board> getBoards(String... filters);

    List<Action> getActions();

    List<Card> getCards(String... filters);

    List<Notification> getNotifications(String... filters);

    List<Organization> getOrganizations(String... filters);

    List<Organization> getInvitedOrganizations(String... filters);

    //POST
    Board createBoard(String name, String idOrganization, String prefs_permissionLevel);

}