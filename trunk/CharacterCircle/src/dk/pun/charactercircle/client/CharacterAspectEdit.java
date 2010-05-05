package dk.pun.charactercircle.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import dk.pun.charactercircle.data.CharacterAspect;

public class CharacterAspectEdit extends ResizeComposite {

	interface Binder extends UiBinder<Widget, CharacterAspectEdit> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	private final CharacterAspectServiceAsync characterAspectService = GWT.create(CharacterAspectService.class);

	@UiField
	CharacterAspectTypeListBox type;
	@UiField
	TextBox title;
	@UiField
	TextBox summary;
	@UiField
	RichTextArea body;
	@UiField
	Button saveButton;

	public CharacterAspectEdit() {
		initWidget(binder.createAndBindUi(this));
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Saving...");
			}
		});
	}

	public void setCharacterAspect(CharacterAspect aspect) {
		type.setSelected(aspect.getType());
		title.setText(aspect.getTitle());
		summary.setText(aspect.getSummary());
		body.setHTML(aspect.getDescription());
	}
}
