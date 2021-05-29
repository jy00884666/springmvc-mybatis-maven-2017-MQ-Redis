package sy.dao;


import sy.page.Page;
import sy.vo.TextVo;

import java.math.BigDecimal;
import sy.model.Text;

public interface TextMapper {
	
    public int deleteByPrimaryKey(BigDecimal id);

    public int insert(Text text);

    public Text selectByPrimaryKey(BigDecimal id);

    public int updateByPrimaryKeySelective(Text text);

    public int updateByPrimaryKey(Text text);

    /**shashijie 2017-01-18 ALM-分页查询
     * @param vo
     * @return Page<Text>
     */
    public Page<Text> getTextByVo(TextVo vo);
    
}