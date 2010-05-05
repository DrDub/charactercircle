package dk.pun.charactercircle.data;

public interface CharacterAspect {
	
	Long getId();
	
	CharacterAspectType getType();
	
	void setType(CharacterAspectType type);

	String getTitle();

	void setTitle(String title);

	String getSummary();

	void setSummary(String summary);

	String getDescription();

	void setDescription(String description);

}
