package ifce.polo.sippi.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageableResponse
{
    private Page<?> page;

/* --------------------------------------------------------------------------------------------- */

    public PageableResponse(Page<?> page) {
        this.page = page;
     }

/* --------------------------------------------------------------------------------------------- */

    public List<?> getContent() {
        return page.getContent();
    }

/* --------------------------------------------------------------------------------------------- */

    public int getPage() {
        return page.getNumber();
    }

/* --------------------------------------------------------------------------------------------- */

    public int getTotalPages() {
        return page.getTotalPages();
    }

/* --------------------------------------------------------------------------------------------- */

    public int getPageSize() {
        return page.getSize();
    }

/* --------------------------------------------------------------------------------------------- */

    public long getTotalItems() {
        return page.getTotalElements();
    }

/* --------------------------------------------------------------------------------------------- */

}
