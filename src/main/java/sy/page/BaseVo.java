package sy.page;

import java.io.Serializable;

public class BaseVo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long sid;

    private Integer pageNum;

    private Integer pageSize;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    /**
     * 翻页需要使用第几页
     * @return
     */
    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    /**
     * 翻页需要使用第几页
     * @return
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 翻页需要每页笔数
     * @return
     */
    public Integer getPageSize() {
        return pageSize == null ? 20 : pageSize;
    }

    /**
     * 翻页需要每页笔数
     * @return
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
