/**
 * Create at 2014-7-15 by zhiping.li Copyright 2014, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd.
 * All right reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT MANAGEMENT
 * CO., LTD. THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED TO THIRD PARTIES, COPIED OR DUPLICATED
 * IN ANY FORM, IN WHOLE OR IN PART, WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT
 * MANAGEMENT CO., LTD.
 */
package sy.page;

import java.util.List;

/**
 * MVC-view中使用的分页对象
 * @author zhiping.li
 */
public class PageInfo<E> {

    /** 当前页索引值 */
    private Integer pageNum;
    /** 页面大小 */
    private Integer pageSize;
    /** 记录总数 */
    private long total;
    /** 页面总数 */
    private Integer pages;

    List<E> datas;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<E> getDatas() {
        return datas;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

}
