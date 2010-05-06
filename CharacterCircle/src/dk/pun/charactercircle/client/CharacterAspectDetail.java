package dk.pun.charactercircle.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

import dk.pun.charactercircle.data.CharacterAspect;

public class CharacterAspectDetail extends ResizeComposite {

	interface Binder extends UiBinder<Widget, CharacterAspectDetail> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Element type;
	@UiField
	Element title;
	@UiField
	Element summary;
	@UiField
	HTML body;
	@UiField
	Button editButton;
	@UiField
	Button deleteButton;

	public CharacterAspectDetail() {
		initWidget(binder.createAndBindUi(this));
		this.editButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Editing...");
			}
		});
		this.deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Deleting...");
			}
		});
	}

	public void setCharacterAspect(CharacterAspect aspect) {
		type.setInnerText(aspect.getType().toString());
		title.setInnerText(aspect.getTitle());
		summary.setInnerHTML(aspect.getSummary());
		body.setHTML(aspect.getDescription());
	}
}
