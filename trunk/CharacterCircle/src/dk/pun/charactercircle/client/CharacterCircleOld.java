package dk.pun.charactercircle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.pun.charactercircle.data.CharacterAspect;
import dk.pun.charactercircle.data.CharacterAspectImpl;
import dk.pun.charactercircle.data.CharacterAspectType;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CharacterCircleOld implements EntryPoint {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side service.
	 */
	private final CharacterAspectServiceAsync characterAspectService = GWT
			.create(CharacterAspectService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final ListBox characterAspectList = new ListBox();
		characterAspectList.addItem("Behaviour");
		characterAspectList.addItem("Capability");
		characterAspectList.addItem("Environment");
		characterAspectList.addItem("Identity");
		characterAspectList.addItem("Purpose");
		characterAspectList.addItem("Value");
		final TextBox titleField = new TextBox();
		final TextBox summaryField = new TextBox();
		final Button sendButton = new Button("Create");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the Fields and Buttons to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("characterAspectListContainer").add(characterAspectList);
		RootPanel.get("titleFieldContainer").add(titleField);
		RootPanel.get("summaryFieldContainer").add(summaryField);
		RootPanel.get("sendButtonContainer").add(sendButton);

		// Focus the cursor on the widget when the app loads
		characterAspectList.setFocus(true);

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the widgets
		class MyHandler implements ClickHandler, KeyUpHandler {

			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendCharacterAspectToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendCharacterAspectToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendCharacterAspectToServer() {
				sendButton.setEnabled(false);
				String title = titleField.getText();
				String summary = summaryField.getText();
				CharacterAspectType cat;
				if (characterAspectList.getSelectedIndex() > -1) {
					String catName = characterAspectList
							.getItemText(characterAspectList.getSelectedIndex());
					if (catName.equals("Behaviour")) {
						cat = CharacterAspectType.Behaviour;
					} else if (catName.equals("Capability")) {
						cat = CharacterAspectType.Capability;
					} else if (catName.equals("Environment")) {
						cat = CharacterAspectType.Environment;
					} else if (catName.equals("Identity")) {
						cat = CharacterAspectType.Identity;
					} else if (catName.equals("Purpose")) {
						cat = CharacterAspectType.Purpose;
					} else {
						cat = CharacterAspectType.Value;
					}

					CharacterAspect characterAspect = new CharacterAspectImpl(
							cat, title, summary);

					textToServerLabel.setText(characterAspect.toString());
					serverResponseLabel.setText("");
					
					characterAspectService.addCharacterAspect(
							characterAspect, new AsyncCallback<Long>() {
								public void onFailure(Throwable caught) {
									dialogBox
											.setText("Remote Procedure Call - Failure");
									serverResponseLabel
											.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
								}

								public void onSuccess(Long id) {
									dialogBox.setText("Remote Procedure Call");
									serverResponseLabel
											.removeStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(id.toString());
									dialogBox.center();
									closeButton.setFocus(true);
								}
							});
				}
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
	}
}
