/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package dk.pun.charactercircle.client;

import java.util.Collection;
import java.util.Iterator;

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

/**
 * A composite that displays a list of emails that can be selected.
 */
public class CharacterAspectList extends ResizeComposite {

	/**
	 * Callback when mail items are selected.
	 */
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

	@UiField FlexTable header;
	@UiField FlexTable table;
	@UiField SelectionStyle selectionStyle;

	private Listener listener;
	private int selectedRow = -1;

	private final CharacterAspectServiceAsync characterAspectService = GWT
			.create(CharacterAspectService.class);

	private Collection<CharacterAspect> characterAspects;

	public CharacterAspectList() {
		initWidget(binder.createAndBindUi(this));

		initTable();
	}

	/**
	 * Sets the listener that will be notified when an item is selected.
	 */
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	protected void onLoad() {
		characterAspectService
				.getCharacterAspects(new AsyncCallback<Collection<CharacterAspect>>() {
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					public void onSuccess(Collection<CharacterAspect> aspects) {
						characterAspects = aspects;
						update();
						// Select the first row if none is selected.
						if (!aspects.isEmpty() && selectedRow == -1) {
							selectRow(0);
						}
					}
				});
	}

	@UiHandler("table")
	void onTableClicked(ClickEvent event) {
		// Select the row that was clicked (-1 to account for header row).
		Cell cell = table.getCellForEvent(event);
		if (cell != null) {
			int row = cell.getRowIndex();
			selectRow(row);
		}
	}

	/**
	 * Initializes the table so that it contains enough rows for a full page of
	 * emails. Also creates the images that will be used as 'read' flags.
	 */
	private void initTable() {
		// Initialize the header.
		header.getColumnFormatter().setWidth(0, "128px");
		header.getColumnFormatter().setWidth(1, "192px");
		// header.getColumnFormatter().setWidth(3, "256px");

		header.setText(0, 0, "Type");
		header.setText(0, 1, "Title");
		header.setText(0, 2, "Summary");
		header.getCellFormatter().setHorizontalAlignment(0, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);

		// Initialize the table.
		table.getColumnFormatter().setWidth(0, "128px");
		table.getColumnFormatter().setWidth(1, "192px");
	}

	/**
	 * Selects the given row (relative to the current page).
	 * 
	 * @param row
	 *            the row to be selected
	 */
	private void selectRow(int row) {
		styleRow(selectedRow, false);
		styleRow(row, true);
		selectedRow = row;

		if (listener != null && characterAspects != null) {
			CharacterAspect[] aspects = characterAspects
			.toArray(new CharacterAspect[0]);
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
