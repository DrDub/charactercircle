package dk.pun.charactercircle.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class LogicalLevel {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String title;
	
	@Persistent
	private String summary;
	
	@Persistent
	private Text description;
	
	public LogicalLevel(String title, String summary) {
		this.setTitle(title);
		this.setSummary(summary);
	}

	public LogicalLevel(String title, String summary, Text description) {
		this.setTitle(title);
		this.setSummary(summary);
		this.setDescription(description);
	}
	
	public Key getKey() {
		return key;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}

	public void setDescription(Text description) {
		this.description = description;
	}

	public Text getDescription() {
		return description;
	}

}
