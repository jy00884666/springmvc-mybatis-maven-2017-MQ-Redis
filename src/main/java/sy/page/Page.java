package sy.page;

import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis - 分页对象
 * @version 3.2.1
 */
public class Page<E> extends ArrayList<E> {

    private static final long serialVersionUID = 1L;

    /**
     * 不进行count查询
     */
    public static final int NO_SQL_COUNT = -1;
    public static final int SQL_COUNT = 0;
    /** 当前页索引值 */
    private int pageNum;
    /** 页面大小 */
    private int pageSize;
    private int startRow;
    private int endRow;
    /** 记录总数 */
    private long total;
    /** 页面总数 */
    private int pages;

    public Page(int pageNum, int pageSize) {
        this(pageNum, pageSize, SQL_COUNT);
    }

    public Page(int pageNum, int pageSize, int total) {
        super(pageSize);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }

    public Page(RowBounds rowBounds, int total) {
        super(rowBounds.getLimit());
        this.pageSize = rowBounds.getLimit();
        this.startRow = rowBounds.getOffset();
        // RowBounds方式默认不求count总数，如果想求count,可以修改这里为SQL_COUNT
        this.total = total;
        this.endRow = this.startRow + this.pageSize;
    }

    /**
     * 获取结果集
     * @return
     */
    public List<E> getResult() {
        return this;
    }

    /**
     * 获取页面总数
     */
    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * 获取当前页索引值
     */
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获取当前页面大小
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * 获取记录总数
     */
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + ", startRow=" + startRow
                + ", endRow=" + endRow + ", total=" + total + ", pages=" + pages + '}';
    }
}
