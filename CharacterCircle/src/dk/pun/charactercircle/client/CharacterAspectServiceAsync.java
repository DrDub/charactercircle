package dk.pun.charactercircle.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.pun.charactercircle.data.CharacterAspect;

public interface CharacterAspectServiceAsync {

	void createCharacterAspect(CharacterAspect input, AsyncCallback<String> callback);

}
