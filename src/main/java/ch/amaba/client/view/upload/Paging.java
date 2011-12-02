package ch.amaba.client.view.upload;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ch.amaba.client.view.upload.state.UploadProgressState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;

public final class Paging extends Composite {

	public interface Images extends ClientBundle {

		@Source("first.png")
		ImageResource first();

		@Source("last.png")
		ImageResource last();

		@Source("previous.png")
		ImageResource previous();

		@Source("next.png")
		ImageResource next();

		@Source("firstDisabled.png")
		ImageResource firstDisabled();

		@Source("lastDisabled.png")
		ImageResource lastDisabled();

		@Source("previousDisabled.png")
		ImageResource previousDisabled();

		@Source("nextDisabled.png")
		ImageResource nextDisabled();
	}

	private static final Images IMAGES = (Images) GWT.create(Images.class);
	private static final Image FIRST_DISABLED = new Image(Paging.IMAGES.firstDisabled());
	private static final Image PREVIOUS_DISABLED = new Image(Paging.IMAGES.previousDisabled());
	private static final Image NEXT_DISABLED = new Image(Paging.IMAGES.nextDisabled());
	private static final Image LAST_DISABLED = new Image(Paging.IMAGES.lastDisabled());
	private final HorizontalPanel panel;

	public Paging() {

		panel = new HorizontalPanel();
		panel.setStyleName("Paging");
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		initWidget(panel);

		UploadProgressState.INSTANCE.addPropertyChangeListener("page", new PageListener());
		UploadProgressState.INSTANCE.addPropertyChangeListener("pages", new PagesListener());
	}

	@Override
	protected void onLoad() {
		redraw();
	}

	private void redraw() {
		final int currentPage = UploadProgressState.INSTANCE.getPage();
		final int pages = UploadProgressState.INSTANCE.getPages();
		final int page = UploadProgressState.INSTANCE.getPage();

		panel.clear();

		if (currentPage > 1) {
			final Image first = new Image(Paging.IMAGES.first());
			first.addClickHandler(new PageClickHandler(1));
			panel.add(first);
			final Image previous = new Image(Paging.IMAGES.previous());
			previous.addClickHandler(new PageClickHandler(currentPage - 1));
			panel.add(previous);
		} else {
			panel.add(Paging.FIRST_DISABLED);
			panel.add(Paging.PREVIOUS_DISABLED);
		}

		final TextBox pageBox = new TextBox();
		pageBox.setText(String.valueOf(page));
		pageBox.addChangeHandler(new PageChangeHandler());

		panel.add(pageBox);

		final HTML ofPages = new HTML("&nbsp;of&nbsp;" + String.valueOf(pages));
		panel.add(ofPages);

		if (currentPage < pages) {
			final Image next = new Image(Paging.IMAGES.next());
			next.addClickHandler(new PageClickHandler(currentPage + 1));
			panel.add(next);
			final Image last = new Image(Paging.IMAGES.last());
			last.addClickHandler(new PageClickHandler(pages));
			panel.add(last);
		} else {
			panel.add(Paging.NEXT_DISABLED);
			panel.add(Paging.LAST_DISABLED);
		}
	}

	private final class PageListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent event) {
			redraw();
		}
	}

	private final class PagesListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent event) {
			redraw();
		}
	}

	private static final class PageClickHandler implements ClickHandler {

		private final int page;

		public PageClickHandler(final int page) {
			this.page = page;
		}

		@Override
		public void onClick(final ClickEvent event) {
			UploadProgressState.INSTANCE.setPage(page);
		}
	}

	private static final class PageChangeHandler implements ChangeHandler {

		@Override
		public void onChange(final ChangeEvent event) {

			final TextBox pageBox = (TextBox) event.getSource();
			final Integer page = Integer.valueOf(pageBox.getText());
			final int pages = UploadProgressState.INSTANCE.getPages();

			if ((page >= 1) && (page <= pages)) {
				UploadProgressState.INSTANCE.setPage(page);
			}
		}
	}
}
