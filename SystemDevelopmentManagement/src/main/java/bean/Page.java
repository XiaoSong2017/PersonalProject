package bean;

import java.beans.JavaBean;
import java.util.List;

@JavaBean
public class Page<T> {
    private long currentPage;
    private long pageSize;
    private long totalRecords;
    private long totalPageNo;
    private List<T> data;

    public Page() {
    }

    public long getPreviewPageNo() {
        return this.currentPage <= 1L ? 1L : this.currentPage - 1L;
    }

    public long getNextPageNo() {
        return this.currentPage >= this.totalPageNo ? this.totalPageNo : this.currentPage + 1L;
    }

    public long getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecords() {
        return this.totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTotalPageNo() {
        return this.totalPageNo;
    }

    public void setTotalPageNo(long totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
