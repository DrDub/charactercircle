package dk.pun.charactercircle.server;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.pun.charactercircle.client.CharacterAspectService;
import dk.pun.charactercircle.data.CharacterAspect;
import dk.pun.charactercircle.data.CharacterAspectImpl;

@SuppressWarnings("serial")
public class CharacterAspectServiceImpl extends RemoteServiceServlet implements
		CharacterAspectService {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	public static PersistenceManager getPersistenceManager() {
		return pmfInstance.getPersistenceManager();
	}

	public Long createCharacterAspect(CharacterAspect aspect) {
		PersistenceManager pm = getPersistenceManager();
		Long id = null;
		try {
			pm.makePersistent(aspect);
			id = aspect.getId();
		} finally {
			pm.close();
		}
		return id;
	}

	public CharacterAspect getCharacterAspect(Long id) {
		PersistenceManager pm = getPersistenceManager();
		CharacterAspect aspect = null;
		try {
			Query query = pm.newQuery(CharacterAspectImpl.class);
			query.setFilter("id == idParam");
			query.declareParameters("Long idParam");
			List<CharacterAspect> aspects = (List<CharacterAspect>) query.execute(id);
			if (aspects.size() > 0) {
				aspect = aspects.get(0);
				aspect = pm.detachCopy(aspect);
			}
		} finally {
			pm.close();
		}
		return aspect;
	}
	
	public Collection<CharacterAspect> getCharacterAspects() {
		PersistenceManager pm = getPersistenceManager();
		Collection<CharacterAspect> characterAspectsDetached = null;
		try {
			Query query = pm.newQuery(CharacterAspectImpl.class);
			List<CharacterAspect> characterAspects = (List<CharacterAspect>) query.execute();
			if (characterAspects != null) {
				characterAspectsDetached = pm.detachCopyAll(characterAspects);
			}
		} finally {
			pm.close();
		}
		return characterAspectsDetached;
	}

	public void updateCharacterAspect(CharacterAspect aspect) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(aspect);
		} finally {
			pm.close();
		}
	}

	public void deleteCharacterAspect(CharacterAspect aspect) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.deletePersistent(aspect);
		} finally {
			pm.close();
		}
	}
}
