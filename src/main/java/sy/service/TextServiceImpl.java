/**
 * Created 2017-01-15
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-01-15 <user>shashijie <description> 
 */
package sy.service;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.dao.TextMapper;
import sy.model.Text;
import sy.model.TradPo;
import sy.page.Page;
import sy.page.PageHelper;
import sy.vo.TextVo;

/**ALM-
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-15
 * @since JDK 1.6
 */
@Service("textService")
public class TextServiceImpl implements TextService {

    private final static Logger logger = LoggerFactory.getLogger(TextServiceImpl.class);
    
    @Autowired
    private TextMapper textMapper;
    
    @Autowired
    private TradService tradService;
    
    /**shashijie 2017-01-15 ALM-
     * @param id
     * @return 
     */
    @Override
    public Text getTextById(BigDecimal id) {
        return textMapper.selectByPrimaryKey(id);
    }

    /**shashijie 2017-01-15
     * @return the textMapper
     */
    public TextMapper getTextMapper() {
        return textMapper;
    }

    /**shashijie 2017-01-15
     * @param textMapper the textMapper to set
     */
    public void setTextMapper(TextMapper textMapper) {
        this.textMapper = textMapper;
    }

    /**shashijie 2017-01-16 ALM-
     * @param id
     * @return 
     */
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        logger.debug("删除并发开始执行方法deleteByPrimaryKey()...");
        return textMapper.deleteByPrimaryKey(id);
    }

    /**shashijie 2017-01-16 ALM-
     * @param text
     * @return 
     */
    @Override
    public int insert(Text text) {
        return textMapper.insert(text);
    }

    /**shashijie 2017-01-16 ALM-
     * @param text
     * @return 
     */
    @Override
    public int updateByPrimaryKeySelective(Text text) {
        int resCount = textMapper.updateByPrimaryKeySelective(text);
        //级联新增
        TradPo tradPo = new TradPo();
        tradPo.setTextId("1");
        tradPo.setUserId("12");
        tradPo.setFormYue("123");
        tradService.insert(tradPo);
        return resCount;
    }

    /**shashijie 2017-01-16 ALM-
     * @param text
     * @return 
     */
    @Override
    public int updateByPrimaryKey(Text text) {
        return textMapper.updateByPrimaryKey(text);
    }

    /**shashijie 2017-01-18 ALM-分页查询
     * @param vo
     * @return 
     */
    @Override
    public Page<Text> getTextByVo(TextVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        return textMapper.getTextByVo(vo);
    }

    /**shashijie 2017-01-18
     * @return the tradService
     */
    public TradService getTradService() {
        return tradService;
    }

    /**shashijie 2017-01-18
     * @param tradService the tradService to set
     */
    public void setTradService(TradService tradService) {
        this.tradService = tradService;
    }
    
}
