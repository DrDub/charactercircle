package dk.pun.charactercircle.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import dk.pun.charactercircle.data.CharacterAspect;
import dk.pun.charactercircle.data.CharacterAspectImpl;
import dk.pun.charactercircle.data.CharacterAspectType;

public class TopPanel extends Composite {

	interface Binder extends UiBinder<Widget, TopPanel> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	private final CharacterAspectServiceAsync characterAspectService = GWT.create(CharacterAspectService.class);

	@UiField
	Anchor newCharacterAspectLink;
	@UiField
	Anchor signOutLink;
	@UiField
	Anchor aboutLink;

	public TopPanel() {
		initWidget(binder.createAndBindUi(this));
	}

	@UiHandler("newCharacterAspectLink")
	void onNewCharacterAspectClicked(ClickEvent event) {
		CharacterAspect newAspect = new CharacterAspectImpl();
		newAspect.setType(CharacterAspectType.Behaviour);
		newAspect.setTitle("New Character Aspect");
		
		this.characterAspectService.addCharacterAspect(newAspect, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			public void onSuccess(Long id) {
				Window.alert("Created new character aspect with id " + id);
				
			}
		});
	}

	@UiHandler("aboutLink")
	void onAboutClicked(ClickEvent event) {
		AboutDialog dialog = new AboutDialog();
		dialog.show();
		dialog.center();
	}

	@UiHandler("signOutLink")
	void onSignOutClicked(ClickEvent event) {
		Window.alert("If this were implemented, you would be signed out now.");
	}
}
