package dk.pun.charactercircle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import dk.pun.charactercircle.data.CharacterAspect;

public class CharacterCircle implements EntryPoint {

	interface Binder extends UiBinder<DockLayoutPanel, CharacterCircle> {
	}

	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("global.css")
		CssResource css();
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TopPanel topPanel;
	@UiField
	CharacterAspectList characterAspectList;
	@UiField
	SimplePanel characterAspectContent;
	CharacterAspectDetail characterAspectDetail;
	CharacterAspectEdit characterAspectEdit;

	public void onModuleLoad() {
		GWT.<GlobalResources> create(GlobalResources.class).css().ensureInjected();

		DockLayoutPanel outer = binder.createAndBindUi(this);

		this.characterAspectDetail = new CharacterAspectDetail();
		this.characterAspectEdit = new CharacterAspectEdit();

		Window.enableScrolling(false);
		Window.setMargin("0px");

		Element topElem = outer.getWidgetContainerElement(this.topPanel);
		topElem.getStyle().setZIndex(2);
		topElem.getStyle().setOverflow(Overflow.VISIBLE);

		this.characterAspectList.setListener(new CharacterAspectList.Listener() {
			public void onCharacterAspectSelected(CharacterAspect aspect) {
				characterAspectDetail.setCharacterAspect(aspect);
			}
		});

		this.showCharacterAspectDetail();

		RootLayoutPanel root = RootLayoutPanel.get();
		root.add(outer);
	}

	public void showCharacterAspectDetail() {
		this.characterAspectContent.setWidget(this.characterAspectDetail);
	}

	public void showCharacterAspectEdit() {
		this.characterAspectContent.setWidget(this.characterAspectEdit);
	}

}
