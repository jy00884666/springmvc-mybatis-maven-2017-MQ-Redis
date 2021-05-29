package sy.jms;

import java.io.Serializable;
import java.util.Map;

public class SignalInfo implements Serializable{

	/** 
	* 序列号 
	*/
	private static final long serialVersionUID = 4009312494584223930L;

	/**应用码 */
    private String appCode;

    /**交易码*/
    private String txCode;

    /**交易日期*/
    private String txDt;

    /**交易时间*/
    private String txTm;

    /**客户号*/
    private String custNo;

    /**分销机构代码*/
    private String disCode;

    /**网点代码*/
    private String outletCode;
    
    /**交易渠道*/
    private String tradeChan;

    /**流水号*/
    private String serialNo;
    
    /**扩充信息*/
    private Map<String, Object> extMap;

	/** 
	 * @return 应用码 
	 */
	public String getAppCode() {
		return appCode;
	}

	/** 
	 * @param appCode 要设置的 应用码（001 基金 002 账户 003 储蓄罐 004 代销）
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/** 
	 * @return 交易码 
	 */
	public String getTxCode() {
		return txCode;
	}

	/** 
	 * @param txCode 要设置的 交易码 
	 */
	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	/** 
	 * @return 交易日期 
	 */
	public String getTxDt() {
		return txDt;
	}

	/** 
	 * @param txDt 要设置的交易日期 
	 */
	public void setTxDt(String txDt) {
		this.txDt = txDt;
	}

	/** 
	 * @return 交易时间
	 */
	public String getTxTm() {
		return txTm;
	}

	/** 
	 * @param txTm 要设置的交易时间
	 */
	public void setTxTm(String txTm) {
		this.txTm = txTm;
	}

	/** 
	 * @return 客户号 
	 */
	public String getCustNo() {
		return custNo;
	}

	/** 
	 * @param custNo 要设置的 客户号
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	/** 
	 * @return 分销机构代码 
	 */
	public String getDisCode() {
		return disCode;
	}

	/** 
	 * @param disCode 要设置的 分销机构代码 
	 */
	public void setDisCode(String disCode) {
		this.disCode = disCode;
	}

	/** 
	 * @return 网点代码 
	 */
	public String getOutletCode() {
		return outletCode;
	}

	/** 
	 * @param outletCode 要设置的网点代码
	 */
	public void setOutletCode(String outletCode) {
		this.outletCode = outletCode;
	}

	/** 
	 * @return 流水号 
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/** 
	 * @param serialNo 要设置的流水号 
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/** 
	 * @return 交易渠道 
	 */
	public String getTradeChan() {
		return tradeChan;
	}

	/** 
	 * @param tradeChan 要设置的 交易渠道 
	 */
	public void setTradeChan(String tradeChan) {
		this.tradeChan = tradeChan;
	}

	/** 
	 * @return 扩充信息 
	 */
	public Map<String, Object> getExtMap() {
		return extMap;
	}

	/** 
	 * @param extMap 要设置的 扩充信息
	 */
	public void setExtMap(Map<String, Object> extMap) {
		this.extMap = extMap;
	}
    
}