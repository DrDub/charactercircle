package dk.pun.charactercircle.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.pun.charactercircle.client.CharacterAspectService;
import dk.pun.charactercircle.data.CharacterAspect;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CharacterAspectServiceImpl extends RemoteServiceServlet implements
		CharacterAspectService {

	public String createCharacterAspect(CharacterAspect input) {
		return input.getTitle() + ": " + input.getSummary();
	}
}
