package sy.dao;

import java.math.BigDecimal;
import sy.model.TradPo;

public interface TradPoMapper {
    
    public int deleteByPrimaryKey(BigDecimal id);

    public int insert(TradPo record);

    public int insertSelective(TradPo record);

    public TradPo selectByPrimaryKey(BigDecimal id);

    public int updateByPrimaryKeySelective(TradPo record);

    public int updateByPrimaryKey(TradPo record);
    
}