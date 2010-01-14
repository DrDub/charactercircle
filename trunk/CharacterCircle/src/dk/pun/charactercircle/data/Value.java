package dk.pun.charactercircle.data;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Value extends LogicalLevel {

	public Value(String title, String summary) {
		super(title, summary);
	}

	public Value(String title, String summary, Text description) {
		super(title, summary, description);
	}
	
}
