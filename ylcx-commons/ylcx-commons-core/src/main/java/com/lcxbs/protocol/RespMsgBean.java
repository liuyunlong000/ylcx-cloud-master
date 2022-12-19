package com.lcxbs.protocol;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.annotation.Order;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: RespMsgBean
* @Description: 统一返回给客户端的数据格式
 */
@ApiModel("统一返回给客户端的数据格式")
@Order(1)
public class RespMsgBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

    public static final String FALLBAKC  = "fallback";

	/**
	 * 数据
	 * */
	@ApiModelProperty(value = "返回数据封装对象",hidden=false,example = "")
	public T data;

	/**
	 * 非0代码请求响应成功!
	 * 0代表请求响应失败!
	 */
	@ApiModelProperty(value = "响应码(200表示操作成功，其余表示操作失败)",hidden=false,example = "")
	private int code;

	/**
	 * 消息提示
	 */
	@ApiModelProperty(value = "操作提示",hidden=false,example = "")
	private String msg;

	/**
	 * 处理时间
	 */
	@ApiModelProperty(value = "处理时间",hidden=false,example = "2021-10-08 21:51:48")

	private String nowTime=DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");

    public RespMsgBean(){}

	public RespMsgBean(T t){
    	success();
    	this.data = t;
	}

	public RespMsgBean(int code){
    	this.code = code;
	}

	public RespMsgBean(int code,String msg){
		this(code);
		this.msg = msg;
	}

	public RespMsgBean(int code,String msg,T data){
    	this(code);
    	this.msg = msg;
    	this.data = data;
	}

	public RespMsgBean success(){
		success(RespCodeEnum.SUCCESS.getCode());
		this.msg = RespCodeEnum.SUCCESS.getName();
		return this;
	}

	public RespMsgBean success(int code){
		this.code = code;
		this.msg = RespCodeEnum.SUCCESS.getName();
		return this;
	}

	public RespMsgBean success(String msg){
		 success();
		 this.msg = msg;
		return this;
	}

	public RespMsgBean success(String msg, T data){
		success();
		success(msg);
		this.data = data;
		return this;
	}

	public RespMsgBean success(int code, String msg){
		success(code);
		success(msg);
		return this;
	}

	public RespMsgBean success(int code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
		return this;
	}

	public RespMsgBean success(T data){
		success();
		this.data = data;
		setData(data);
		return this;
	}

	public RespMsgBean failure(T data){
		failure();
		this.data = data;
		setData(data);
		return this;
	}

	public RespMsgBean failure(){
		failure(RespCodeEnum.FAILURE.getCode());
		return this;
	}

	public RespMsgBean failure(int code){
		this.code= code;
		this.msg = RespCodeEnum.FAILURE.getName();
    	return this;
	}

	public RespMsgBean failure(String msg){
		failure(RespCodeEnum.FAILURE.getCode());
		this.msg = msg != null ? msg:RespCodeEnum.FAILURE.getName();
		return this;
	}

	public RespMsgBean failure(RespCodeEnum  respCodeEnum){
		failure(respCodeEnum.getCode());
		this.msg = respCodeEnum.getName();
		return this;
	}

	public RespMsgBean fallback(RespCodeEnum respCodeEnum, String msg){
		failure(respCodeEnum);
		this.msg = msg;
		return this;
	}

	public RespMsgBean fallback(String msg){
		fallback(RespCodeEnum.FAILURE,null == msg ?FALLBAKC:msg);
		return this;
	}

	public RespMsgBean fallback(){
		success();
		this.msg = FALLBAKC;
		return this;
	}

	public RespMsgBean fallback(T data){
		fallback();
		this.data = data;
		return this;
	}


	public RespMsgBean failure(int code, String msg){
		failure(code);
		this.msg = msg;
		return this;
	}

	public RespMsgBean failure(String msg, T data){
		failure(msg);
		this.data = data;
		return this;
	}

	public RespMsgBean failure(int code, String msg, T data){
		failure(code);
		failure(msg,data);
		return this;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@JsonIgnore
	public boolean isSuccess(){
		return this.getCode() == RespCodeEnum.SUCCESS.getCode();
	}
}
