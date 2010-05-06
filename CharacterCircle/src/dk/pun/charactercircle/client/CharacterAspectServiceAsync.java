package dk.pun.charactercircle.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.pun.charactercircle.data.CharacterAspect;

public interface CharacterAspectServiceAsync {

	void addCharacterAspect(CharacterAspect aspect, AsyncCallback<Long> callback);
	
	void getCharacterAspect(Long id, AsyncCallback<CharacterAspect> callback);

	void getCharacterAspects(AsyncCallback<List<CharacterAspect>> callback);
	
	void updateCharacterAspect(CharacterAspect aspect, AsyncCallback<Boolean> callback);
	
	void deleteCharacterAspect(Long id, AsyncCallback<Boolean> callback);
	
	void deleteCharacterAspects(List<Long> ids, AsyncCallback<Boolean> callback);
	
}
