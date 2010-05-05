package dk.pun.charactercircle.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.pun.charactercircle.data.CharacterAspect;

public interface CharacterAspectServiceAsync {

	void createCharacterAspect(CharacterAspect aspect, AsyncCallback<Long> callback);
	
	void getCharacterAspect(Long id, AsyncCallback<CharacterAspect> callback);

	void getCharacterAspects(AsyncCallback<Collection<CharacterAspect>> callback);
	
	void updateCharacterAspect(CharacterAspect aspect, AsyncCallback callback);
	
	void deleteCharacterAspect(CharacterAspect aspect, AsyncCallback callback);
	
}
