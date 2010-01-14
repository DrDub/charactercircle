package dk.pun.charactercircle.data;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Identity extends LogicalLevel {

	public Identity(String title, String summary) {
		super(title, summary);
	}

	public Identity(String title, String summary, Text description) {
		super(title, summary, description);
	}
	
}
