package dk.pun.charactercircle.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class CharacterAspectImpl implements CharacterAspect, Serializable {

	private static final long serialVersionUID = -3497588501728319168L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	@Persistent
	private CharacterAspectType type;
	
	@Persistent
	private String title;
	
	@Persistent
	private String summary;
	
	@Persistent
	private String description;
	
	public CharacterAspectImpl() {
		
	}
	
	public CharacterAspectImpl(CharacterAspectType type, String title, String summary) {
		this.setType(type);
		this.setTitle(title);
		this.setSummary(summary);
	}

	public CharacterAspectImpl(CharacterAspectType type, String title, String summary, String description) {
		this.setType(type);
		this.setTitle(title);
		this.setSummary(summary);
		this.setDescription(description);
	}
	
	public long getId() {
		return id;
	}

	public void setType(CharacterAspectType type) {
		this.type = type;
	}

	public CharacterAspectType getType() {
		return this.type;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
