/**
 * Copyright 2011 ArcBees Inc.
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

package ch.amaba.client.view;

import ch.amaba.client.presenter.HitListPresenter.MyView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * HitList : resultat de la recherche.
 * 
 * @author ROG
 */
public class HitListView extends ViewImpl implements MyView {
	interface HitListViewUiBinder extends UiBinder<Widget, HitListView> {
	}

	@UiField
	FlexTable htmlPanel;

	private static HitListViewUiBinder uiBinder = GWT.create(HitListViewUiBinder.class);
	private final MultiWordSuggestOracle mySuggestions = new MultiWordSuggestOracle();
	private final Widget widget;

	public HitListView() {
		widget = HitListView.uiBinder.createAndBindUi(this);
	}

	public Widget asWidget() {
		return widget;
	}

	public FlexTable getHitListFlowPanel() {
		return htmlPanel;
	}

}