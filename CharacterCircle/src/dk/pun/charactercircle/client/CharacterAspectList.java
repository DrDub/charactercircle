package dk.pun.charactercircle.client;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import dk.pun.charactercircle.data.CharacterAspect;

public class CharacterAspectList extends ResizeComposite {

	public interface Listener {
		void onCharacterAspectSelected(CharacterAspect aspect);
	}

	interface Binder extends UiBinder<Widget, CharacterAspectList> {
	}

	interface SelectionStyle extends CssResource {
		String selectedRow();
	}

	private static final Binder binder = GWT.create(Binder.class);
	static final int VISIBLE_EMAIL_COUNT = 20;

	@UiField
	FlexTable header;
	@UiField
	FlexTable table;
	@UiField
	SelectionStyle selectionStyle;

	private Listener listener;
	private int selectedRow = -1;

	private final CharacterAspectServiceAsync characterAspectService = GWT.create(CharacterAspectService.class);

	private Collection<CharacterAspect> characterAspects;

	public CharacterAspectList() {
		initWidget(binder.createAndBindUi(this));
		initTable();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	protected void onLoad() {
		this.characterAspectService.getCharacterAspects(new AsyncCallback<List<CharacterAspect>>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			public void onSuccess(List<CharacterAspect> aspects) {
				characterAspects = aspects;
				update();
				if (!aspects.isEmpty() && selectedRow == -1) {
					selectRow(0);
				}
			}
		});
	}

	@UiHandler("table")
	void onTableClicked(ClickEvent event) {
		Cell cell = table.getCellForEvent(event);
		if (cell != null) {
			int row = cell.getRowIndex();
			selectRow(row);
		}
	}

	private void initTable() {
		header.getColumnFormatter().setWidth(0, "128px");
		header.getColumnFormatter().setWidth(1, "192px");
		// header.getColumnFormatter().setWidth(3, "256px");

		header.setText(0, 0, "Type");
		header.setText(0, 1, "Title");
		header.setText(0, 2, "Summary");
		header.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);

		// Initialize the table.
		table.getColumnFormatter().setWidth(0, "128px");
		table.getColumnFormatter().setWidth(1, "192px");
	}

	private void selectRow(int row) {
		styleRow(selectedRow, false);
		styleRow(row, true);
		selectedRow = row;

		if (listener != null && characterAspects != null) {
			CharacterAspect[] aspects = characterAspects.toArray(new CharacterAspect[0]);
			listener.onCharacterAspectSelected(aspects[row]);
		}
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			String style = selectionStyle.selectedRow();

			if (selected) {
				table.getRowFormatter().addStyleName(row, style);
			} else {
				table.getRowFormatter().removeStyleName(row, style);
			}
		}
	}

	private void update() {
		int x = 0;
		Iterator<CharacterAspect> i = characterAspects.iterator();
		while (i.hasNext()) {
			CharacterAspect aspect = i.next();
			table.setText(x, 0, aspect.getType().toString());
			table.setText(x, 1, aspect.getTitle());
			table.setText(x, 2, aspect.getSummary());
			table.setText(x, 3, aspect.getId().toString());
			x++;
		}
	}
}
