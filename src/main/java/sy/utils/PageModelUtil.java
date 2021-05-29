package sy.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import sy.page.Page;

/**
 * Page返回类的工具方法
 */
public class PageModelUtil {

    public static String PAGE_ENDROW = "endrow";
    public static String PAGE_PAGE_NUM = "pagenum";
    public static String PAGE_PAGES = "pages";
    public static String PAGE_PAGE_SIZE = "pagesize";
    public static String PAGE_START_ROW = "startrow";
    public static String PAGE_TOTAL = "total";

    /**
     * page
     * @param model
     * @param page
     * @return
     */
    public static Map<String, Object> setPageInfo(Model model, Page<?> page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(PAGE_ENDROW, page.getEndRow());
        map.put(PAGE_PAGE_NUM, page.getPageNum());
        map.put(PAGE_PAGES, page.getPages());
        map.put(PAGE_PAGE_SIZE, page.getPageSize());
        map.put(PAGE_START_ROW, page.getStartRow());
        map.put(PAGE_TOTAL, page.getTotal());
        return map;
    }

    /**
     * page
     * @param model
     * @param page
     * @return
     */
    public static Map<String, Object> setPageInfo(ModelAndView model, Page<?> page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(PAGE_ENDROW, page.getEndRow());
        map.put(PAGE_PAGE_NUM, page.getPageNum());
        map.put(PAGE_PAGES, page.getPages());
        map.put(PAGE_PAGE_SIZE, page.getPageSize());
        map.put(PAGE_START_ROW, page.getStartRow());
        map.put(PAGE_TOTAL, page.getTotal());
        return map;
    }
}
