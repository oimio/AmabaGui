package ch.amaba.client.view.upload.state;

public abstract class PageableState extends AbstractState {

  private static final int DEFAULT_PAGE = 1;
  private static final int DEFAULT_PAGES = 1;
  public static final int DEFAULT_PAGE_SIZE = 5;
  private int page = DEFAULT_PAGE;
  private int pages = DEFAULT_PAGES;
  private int pageSize = DEFAULT_PAGE_SIZE;

  public PageableState() {
  }

  public final int getPage() {
    return page;
  }

  public final int getPages() {
    return pages;
  }

  public final void setPage(final int page) {

    if (page < 1) {
      return;
    }

    int old = this.page;
    this.page = page;
    firePropertyChange("page", old, page);
  }

  public final void setPages(final int pages) {

    if (pages < 1) {
      return;
    }

    int old = this.pages;
    this.pages = pages;
    firePropertyChange("pages", old, pages);
  }

  public final int getPageSize() {
    return pageSize;
  }

  public final void setPageSize(final int pageSize) {

    if (pageSize < 1) {
      return;
    }

    this.pageSize = pageSize;
  }
}
