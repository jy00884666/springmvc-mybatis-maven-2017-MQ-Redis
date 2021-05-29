/**
 * Created 2017-01-15
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-01-15 <user>shashijie <description> 
 */
package sy.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.dao.TradPoMapper;
import sy.model.TradPo;

/**ALM-
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-15
 * @since JDK 1.6
 */
@Service("tradService")
public class TradServiceImpl implements TradService {

    @Autowired
    private TradPoMapper tradMapper;
    
    /**shashijie 2017-01-15 ALM-
     * @param id
     * @return 
     */
    @Override
    public TradPo getTradById(BigDecimal id) {
        return tradMapper.selectByPrimaryKey(id);
    }

    /**shashijie 2017-01-16 ALM-
     * @param id
     * @return 
     */
    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return tradMapper.deleteByPrimaryKey(id);
    }

    /**shashijie 2017-01-16 ALM-
     * @param TradPo
     * @return 
     */
    @Override
    public int insert(TradPo TradPo) {
        return tradMapper.insert(TradPo);
    }

    /**shashijie 2017-01-16 ALM-
     * @param TradPo
     * @return 
     */
    @Override
    public int updateByPrimaryKeySelective(TradPo TradPo) {
        return tradMapper.updateByPrimaryKeySelective(TradPo);
    }

    /**shashijie 2017-01-16 ALM-
     * @param TradPo
     * @return 
     */
    @Override
    public int updateByPrimaryKey(TradPo TradPo) {
        return tradMapper.updateByPrimaryKey(TradPo);
    }

    /**shashijie 2017-01-18
     * @return the tradMapper
     */
    public TradPoMapper getTradMapper() {
        return tradMapper;
    }

    /**shashijie 2017-01-18
     * @param tradMapper the tradMapper to set
     */
    public void setTradMapper(TradPoMapper tradMapper) {
        this.tradMapper = tradMapper;
    }

}
