/**
 * Created 2017-01-15
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-01-15 <user>shashijie <description> 
 */
package sy.vo;

import java.math.BigDecimal;
import sy.page.BaseVo;

/**ALM-
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-15
 * @since JDK 1.6
 */
public class TextVo extends BaseVo {
    
    /**shashijie 2017-01-18 */
    private static final long serialVersionUID = 1L;

    private BigDecimal id;
    
    private String jjdm;
    
    private String kmmc;
    
    private String yue;

    /**shashijie 2017-01-15
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**shashijie 2017-01-15
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**shashijie 2017-01-15
     * @return the jjdm
     */
    public String getJjdm() {
        return jjdm;
    }

    /**shashijie 2017-01-15
     * @param jjdm the jjdm to set
     */
    public void setJjdm(String jjdm) {
        this.jjdm = jjdm;
    }

    /**shashijie 2017-01-15
     * @return the kmmc
     */
    public String getKmmc() {
        return kmmc;
    }

    /**shashijie 2017-01-15
     * @param kmmc the kmmc to set
     */
    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }

    /**shashijie 2017-01-15
     * @return the yue
     */
    public String getYue() {
        return yue;
    }

    /**shashijie 2017-01-15
     * @param yue the yue to set
     */
    public void setYue(String yue) {
        this.yue = yue;
    }
}
