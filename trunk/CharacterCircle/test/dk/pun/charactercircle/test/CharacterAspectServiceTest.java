package dk.pun.charactercircle.test;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import dk.pun.charactercircle.client.CharacterAspectService;
import dk.pun.charactercircle.data.CharacterAspect;
import dk.pun.charactercircle.data.CharacterAspectImpl;
import dk.pun.charactercircle.data.CharacterAspectType;
import dk.pun.charactercircle.server.CharacterAspectServiceImpl;

public class CharacterAspectServiceTest {

	private LocalServiceTestHelper helper;
	private CharacterAspectService service;

	@Before
	public void setUp() throws Exception {
		LocalDatastoreServiceTestConfig config = new LocalDatastoreServiceTestConfig();
		this.helper = new LocalServiceTestHelper(config);
		this.helper.setUp();
		this.service = new CharacterAspectServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.helper.tearDown();
	}
	
	@Test
	public void createCharacterAspect() throws Exception {
		CharacterAspect aspect = new CharacterAspectImpl(CharacterAspectType.Behaviour, "B1", "Blah...");
		Long id = this.service.addCharacterAspect(aspect);
		Assert.assertNotNull(aspect.getId());
		Assert.assertNotNull(id);
		Assert.assertEquals(id, aspect.getId());
		
		CharacterAspect aspect3 = this.service.getCharacterAspect(id);
		Assert.assertNotNull(aspect3);
		Assert.assertEquals(CharacterAspectType.Behaviour, aspect3.getType());
		Assert.assertEquals("B1", aspect3.getTitle());

		Collection<CharacterAspect> aspects = this.service.getCharacterAspects();
		Assert.assertNotNull(aspects);
		Assert.assertEquals(1, aspects.size());
		
		CharacterAspect aspect2 = aspects.iterator().next();
		Assert.assertEquals(CharacterAspectType.Behaviour, aspect2.getType());
		Assert.assertEquals("B1", aspect2.getTitle());
	}
}
