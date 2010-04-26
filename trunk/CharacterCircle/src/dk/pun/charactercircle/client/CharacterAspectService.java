package dk.pun.charactercircle.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.pun.charactercircle.data.CharacterAspect;

@RemoteServiceRelativePath("characterAspect")
public interface CharacterAspectService extends RemoteService {

	String createCharacterAspect(CharacterAspect input);

}
