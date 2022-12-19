package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysCommonLogEntity;
import java.sql.Blob;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.lcxbs.validate.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title: SysCommonLog.java</p>
 * <p>Description:登录日志实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 14:32:50
 * @version V1.0
 */
@ApiModel("登录日志")
public class SysCommonLog extends SysCommonLogEntity{


    //region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID;不能空")
    @ApiModelProperty(value = "自增ID;",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "登录用户名长度不能超过 {max}")
    @ApiModelProperty(value = "登录用户名",hidden=false,required=false,example = "")
    public String getUserLogin(){return super.getUserLogin();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "IP地址长度不能超过 {max}")
    @ApiModelProperty(value = "IP地址",hidden=false,required=false,example = "")
    public String getIpAddress(){return super.getIpAddress();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "登录位置长度不能超过 {max}")
    @ApiModelProperty(value = "登录位置",hidden=false,required=false,example = "")
    public String getLoginLocation(){return super.getLoginLocation();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "浏览器长度不能超过 {max}")
    @ApiModelProperty(value = "浏览器",hidden=false,required=false,example = "")
    public String getBrowser(){return super.getBrowser();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "操作系统长度不能超过 {max}")
    @ApiModelProperty(value = "操作系统",hidden=false,required=false,example = "")
    public String getOsType(){return super.getOsType();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "登录状态;字典：1登录成功，0登录失败应该在{min}-{max}之间")
    @ApiModelProperty(value = "登录状态;字典：1登录成功，0登录失败",hidden=false,required=false,example = "")
    public Long getLoginState(){return super.getLoginState();}

    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "操作信息长度不能超过 {max}")
    @ApiModelProperty(value = "操作信息",hidden=false,required=false,example = "")
    public String getLogMsg(){return super.getLogMsg();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "登录平台长度不能超过 {max}")
    @ApiModelProperty(value = "登录平台",hidden=false,required=false,example = "")
    public String getPlatform(){return super.getPlatform();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "登录时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "登录时间",hidden=false,required=false,example = "")
    public Long getLoginTime(){return super.getLoginTime();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "退出时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "退出时间",hidden=false,required=false,example = "")
    public Long getLogoutTime(){return super.getLogoutTime();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "在线时长应该在{min}-{max}之间")
    @ApiModelProperty(value = "在线时长",hidden=false,required=false,example = "")
    public Long getOnlineDuration(){return super.getOnlineDuration();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "会话ID长度不能超过 {max}")
    @ApiModelProperty(value = "会话ID",hidden=false,required=false,example = "")
    public String getSessionId(){return super.getSessionId();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "租户号长度不能超过 {max}")
    @ApiModelProperty(value = "租户号",hidden=false,required=false,example = "")
    public String getTenantId(){return super.getTenantId();}

    //endregion

    //region 构造方法
    /**无参构造函数*/
    public SysCommonLog(){super();}
    /**带主键构造函数*/
    public SysCommonLog(Long id){super(id);}
    //endregion
}
