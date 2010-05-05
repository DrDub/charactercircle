package dk.pun.charactercircle.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.pun.charactercircle.data.CharacterAspect;

@RemoteServiceRelativePath("characterAspect")
public interface CharacterAspectService extends RemoteService {

	Long createCharacterAspect(CharacterAspect aspect);
	
	CharacterAspect getCharacterAspect(Long id);

	Collection<CharacterAspect> getCharacterAspects();
	
	void updateCharacterAspect(CharacterAspect aspect);
	
	void deleteCharacterAspect(CharacterAspect aspect);	
}
