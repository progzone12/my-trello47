package org.trello4j.core;

import org.trello4j.TrelloObjectFactoryImpl;
import org.trello4j.TrelloURL;
import org.trello4j.model.Type;

import com.google.gson.reflect.TypeToken;

public class DefaultTypeOperations extends AbstractOperations implements TypeOperations {

	public DefaultTypeOperations(String apiKey, String token, TrelloObjectFactoryImpl trelloObjFactory) {
		super(apiKey, token, trelloObjFactory);
	}

	@Override
	public Type getType(String idOrName) {
		final String url = TrelloURL.create(apiKey, TrelloURL.TYPE_URL, idOrName).token(token).build();

		return trelloObjFactory.createObject(new TypeToken<Type>() {
		}, doGet(url));
	}
}
