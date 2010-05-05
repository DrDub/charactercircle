package dk.pun.charactercircle.client;

import com.google.gwt.user.client.ui.ListBox;

import dk.pun.charactercircle.data.CharacterAspectType;

public class CharacterAspectTypeListBox extends ListBox {

	public CharacterAspectTypeListBox() {
		super();
		this.addItem(CharacterAspectType.Behaviour.name());
		this.addItem(CharacterAspectType.Capability.name());
		this.addItem(CharacterAspectType.Environment.name());
		this.addItem(CharacterAspectType.Identity.name());
		this.addItem(CharacterAspectType.Purpose.name());
		this.addItem(CharacterAspectType.Value.name());
	}
	
	public void setSelected(CharacterAspectType type) {
		this.setSelectedIndex(type.ordinal());
	}
	
}
