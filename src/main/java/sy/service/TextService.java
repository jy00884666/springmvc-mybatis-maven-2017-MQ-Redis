/**
 * Created 2017-01-15 Copyright shashijie modified by <date> 2017-01-15 <user>shashijie
 * <description>
 */
package sy.service;

import sy.annotations.Sync;

import sy.page.Page;
import sy.vo.TextVo;
import java.math.BigDecimal;
import sy.model.Text;

/**
 * ALM-
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-15
 * @since JDK 1.6
 */
public interface TextService {

    public Text getTextById(BigDecimal id);

    /**
     * shashijie 2017-01-25 删除,顺便测试数据同步
     * @param id
     * @return int
     */
    @Sync
    public int deleteByPrimaryKey(BigDecimal id);

    public int insert(Text text);

    public int updateByPrimaryKeySelective(Text text);

    public int updateByPrimaryKey(Text text);

    /**
     * shashijie 2017-01-18 ALM-分页查询
     * @param vo
     * @return Page<Text>
     */
    // @Sync
    public Page<Text> getTextByVo(TextVo vo);

}
