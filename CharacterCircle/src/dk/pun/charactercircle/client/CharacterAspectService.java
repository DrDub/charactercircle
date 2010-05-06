package dk.pun.charactercircle.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.pun.charactercircle.data.CharacterAspect;

@RemoteServiceRelativePath("characterAspect")
public interface CharacterAspectService extends RemoteService {

	Long addCharacterAspect(CharacterAspect aspect);
	
	CharacterAspect getCharacterAspect(Long id);
	
	List<CharacterAspect> getCharacterAspects();
	
	Boolean updateCharacterAspect(CharacterAspect aspect);
	
	Boolean deleteCharacterAspect(Long id);	
	
	Boolean deleteCharacterAspects(List<Long> ids);
}
