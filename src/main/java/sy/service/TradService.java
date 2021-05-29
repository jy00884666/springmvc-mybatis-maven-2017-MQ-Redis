/**
 * Created 2017-01-15
 *
 * Copyright shashijie
 *
 * modified by <date> 2017-01-15 <user>shashijie <description> 
 */
package sy.service;

import sy.model.TradPo;

import java.math.BigDecimal;

/**ALM-
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-15
 * @since JDK 1.6
 */
public interface TradService {
    
    public TradPo getTradById(BigDecimal id);
    
    public int deleteByPrimaryKey(BigDecimal id);

    public int insert(TradPo tradPo);

    public int updateByPrimaryKeySelective(TradPo tradPo);

    public int updateByPrimaryKey(TradPo tradPo);

    
}
