/**
 * Created 2017-01-15 Copyright shashijie modified by <date> 2017-01-15 <user>shashijie
 * <description>
 */
package sy.controller;

import sy.redis.RedisTemplateUtil;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sy.ext.ExtPropertyPlaceholderConfigurer;
import sy.jms.Producer;
import sy.jms.SignalInfo;
import sy.model.Text;
import sy.page.Page;
import sy.service.TextService;
import sy.utils.PageModelUtil;
import sy.vo.TextVo;

/**
 * ALM-
 * @description:
 * @reason: ADD REASON(可选)
 * @author shashijie
 * @date 2017-01-15
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/textController")
public class TextController extends AbstractBaseController {

    private final static Logger logger = LoggerFactory.getLogger(TextController.class);

    @Autowired
    private TextService textService;

    /** shashijie 2017-01-20 发送消息测试 */
    @Autowired
    private Producer producer;
    
    /**shashijie 2017-02-03 缓存测试*/
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    /** 初始化方法 */
    @RequestMapping("/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // 配置文件测试
        logger.debug("datasource.class:{}", ExtPropertyPlaceholderConfigurer.getValue("datasource.class", ""));

        /* Text text = TextService.getTextById(new BigDecimal("0")); if (text == null) {
         * logger.debug("没有此数据!"); } else { logger.debug(text.getKmmc()); } */

        mv.setViewName(getRealView(request, "text"));
        return mv;
    }

    /**
     * shashijie 2017-01-18 ALM-新增初始化方法
     * @param request
     * @param response
     * @param model
     * @return Model
     */
    @RequestMapping("/textAdd")
    public ModelAndView textAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getRealView(request, "textAdd"));
        return mv;
    }

    /** ajax请求新增操作 */
    @ResponseBody
    // 根据前台设置的dataType，去调用相应的HttpMessageConverter
    @RequestMapping("/textAddDo")
    public Map<String, String> textAddDo(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, String> map = new HashMap<String, String>();
        Text po = bindRequestParam(request, Text.class);
        logger.debug("textAdd:{}", JSON.toJSONString(po));
        int resCount = textService.insert(po);
        if (resCount > 0) {
            map.put("showInfo", "新增成功");
        } else {
            map.put("showInfo", "新增失败");
        }
        // mv.setViewName(getRealView(request, "textAdd"));
        return map;
    }

    /** 分页查询 */
    @RequestMapping("/queryList")
    public ModelAndView queryList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        TextVo vo = bindRequestParam(request, TextVo.class);
        Page<Text> list = textService.getTextByVo(vo);

        mv.addObject("list", list.getResult());
        mv.addObject("vo", vo);

        mv.addAllObjects(PageModelUtil.setPageInfo(mv, list));

        mv.setViewName(getRealView(request, "text"));
        return mv;
    }

    /** 修改页面初始化 */
    @RequestMapping("/textUpdate")
    public ModelAndView textUpdate(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        TextVo vo = bindRequestParam(request, TextVo.class);
        Text po = textService.getTextById(vo.getId());

        mv.addObject("po", po);
        mv.addObject("vo", vo);

        mv.setViewName(getRealView(request, "textUpdate"));
        return mv;
    }

    /** ajax请求修改操作 */
    @ResponseBody
    // 根据前台设置的dataType，去调用相应的HttpMessageConverter
    @RequestMapping("/textUpdateDo")
    public Map<String, String> textUpdateDo(HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Map<String, String> map = new HashMap<String, String>();
        Text po = bindRequestParam(request, Text.class);
        logger.debug("textAdd:{}", JSON.toJSONString(po));
        int resCount = textService.updateByPrimaryKeySelective(po);
        if (resCount > 0) {
            map.put("showInfo", "修改成功");
        } else {
            map.put("showInfo", "修改失败");
        }
        // mv.setViewName(getRealView(request, "textAdd"));
        return map;
    }

    /**
     * shashijie 2017-01-25 删除
     * @param request
     * @param response
     * @param model
     * @return Map<String,String>
     */
    // 根据前台设置的dataType，去调用相应的HttpMessageConverter
    @ResponseBody
    @RequestMapping("/textDelete")
    public Map<String, String> textDelete(HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Map<String, String> map = new HashMap<String, String>();
        Text po = bindRequestParam(request, Text.class);
        logger.debug("textAdd:{}", JSON.toJSONString(po));
        // 测试数据同步
        int resCount = textService.deleteByPrimaryKey(po.getId());
        if (resCount > 0) {
            map.put("showInfo", "删除成功");
        } else {
            map.put("showInfo", "删除失败");
        }
        // mv.setViewName(getRealView(request, "textAdd"));
        return map;
    }

    /** 发送消息测试方法 */
    @RequestMapping("/textSend")
    public ModelAndView textSend(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        TextVo vo = bindRequestParam(request, TextVo.class);
        mv.addObject("vo", vo);

        // 发送消息
        SignalInfo signalInfo = new SignalInfo();
        signalInfo.setAppCode("001");
        producer.send(signalInfo);

        mv.setViewName(getRealView(request, "text"));
        return mv;
    }
    
    /** 缓存消息测试方法 */
    @RequestMapping("/textRedis")
    public ModelAndView textRedis(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        TextVo vo = bindRequestParam(request, TextVo.class);
        mv.addObject("vo", vo);

        // 缓存获取消息
        redisTemplateUtil.set("001", "hello word");
        logger.debug("测试缓存...通过key获取value:{}",redisTemplateUtil.get("001"));
        
        mv.setViewName(getRealView(request, "text"));
        return mv;
    }

    /**
     * shashijie 2017-01-18
     * @return the textService
     */
    public TextService getTextService() {
        return textService;
    }

    /**
     * shashijie 2017-01-18
     * @param textService the textService to set
     */
    public void setTextService(TextService textService) {
        this.textService = textService;
    }

    /**
     * shashijie 2017-01-20
     * @return the producer
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * shashijie 2017-01-20
     * @param producer the producer to set
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**shashijie 2017-02-03
     * @return the redisTemplateUtil
     */
    public RedisTemplateUtil getRedisTemplateUtil() {
        return redisTemplateUtil;
    }

    /**shashijie 2017-02-03
     * @param redisTemplateUtil the redisTemplateUtil to set
     */
    public void setRedisTemplateUtil(RedisTemplateUtil redisTemplateUtil) {
        this.redisTemplateUtil = redisTemplateUtil;
    }

}
