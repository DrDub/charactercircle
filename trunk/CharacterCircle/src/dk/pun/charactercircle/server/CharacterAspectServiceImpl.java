package dk.pun.charactercircle.server;

import java.util.ArrayList;
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

	public Long addCharacterAspect(CharacterAspect aspect) {
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
	
	public List<CharacterAspect> getCharacterAspects() {
		PersistenceManager pm = getPersistenceManager();
		List<CharacterAspect> characterAspectsDetached = new ArrayList<CharacterAspect>();
		try {
			Query query = pm.newQuery(CharacterAspectImpl.class);
			List<CharacterAspect> characterAspects = (List<CharacterAspect>) query.execute();
			if (characterAspects != null) {
				Collection<CharacterAspect> characterAspectsCollection = pm.detachCopyAll(characterAspects);
				for (CharacterAspect aspect : characterAspectsCollection) {
					characterAspectsDetached.add(aspect);
				}
			}
		} finally {
			pm.close();
		}
		return characterAspectsDetached;
	}

	public Boolean updateCharacterAspect(CharacterAspect aspect) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(aspect);
		} finally {
			pm.close();
		}
		return Boolean.TRUE;
	}

	public Boolean deleteCharacterAspect(Long id) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Query query = pm.newQuery(CharacterAspectImpl.class);
			query.setFilter("id == idParam");
			query.declareParameters("Long idParam");
			List<CharacterAspect> aspects = (List<CharacterAspect>) query.execute(id);
			if (aspects.size() > 0) {
				pm.deletePersistent(aspects.get(0));
			}
		} finally {
			pm.close();
		}
		return Boolean.TRUE;
	}
	
	public Boolean deleteCharacterAspects(List<Long> ids) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.deletePersistentAll(ids);
		} finally {
			pm.close();
		}
		return Boolean.TRUE;
	}
}
