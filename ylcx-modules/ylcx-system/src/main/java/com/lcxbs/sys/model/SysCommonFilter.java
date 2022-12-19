package com.lcxbs.sys.model;
import com.lcxbs.sys.entity.SysCommonFilterEntity;
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
 * <p>Title: SysCommonFilter.java</p>
 * <p>Description:操作日志实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-8 22:28:27
 * @version V1.0
 */
@ApiModel("操作日志")
public class SysCommonFilter extends SysCommonFilterEntity{


    //region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "主键不能空")
    @ApiModelProperty(value = "主键",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}

    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "系统模块长度不能超过 {max}")
    @ApiModelProperty(value = "系统模块",hidden=false,required=false,example = "")
    public String getModuleName(){return super.getModuleName();}

    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "模块编码长度不能超过 {max}")
    @ApiModelProperty(value = "模块编码",hidden=false,required=false,example = "")
    public String getModuleCode(){return super.getModuleCode();}

    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "请求方法长度不能超过 {max}")
    @ApiModelProperty(value = "请求方法",hidden=false,required=false,example = "")
    public String getRequestMethod(){return super.getRequestMethod();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "操作状态应该在{min}-{max}之间")
    @ApiModelProperty(value = "操作状态",hidden=false,required=false,example = "")
    public Long getRequestStatus(){return super.getRequestStatus();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "消耗时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "消耗时间",hidden=false,required=false,example = "")
    public Long getConsumeTime(){return super.getConsumeTime();}

    @Override
    @ApiModelProperty(value = "错误信息",hidden=false,required=false,example = "")
    public String getErrorMsg(){return super.getErrorMsg();}

    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "被操作信息ID长度不能超过 {max}")
    @ApiModelProperty(value = "被操作信息ID",hidden=false,required=false,example = "")
    public String getInfoId(){return super.getInfoId();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "操作IP长度不能超过 {max}")
    @ApiModelProperty(value = "操作IP",hidden=false,required=false,example = "")
    public String getLogIp(){return super.getLogIp();}

    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "请求地址长度不能超过 {max}")
    @ApiModelProperty(value = "请求地址",hidden=false,required=false,example = "")
    public String getCallUrl(){return super.getCallUrl();}

    @Override
    @ApiModelProperty(value = "请求参数信息",hidden=false,required=false,example = "")
    public String getCallParams(){return super.getCallParams();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "操作人长度不能超过 {max}")
    @ApiModelProperty(value = "操作人",hidden=false,required=false,example = "")
    public String getCreatedBy(){return super.getCreatedBy();}

    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "操作时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "操作时间",hidden=false,required=false,example = "")
    public Long getCreatedTime(){return super.getCreatedTime();}

    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "租户号长度不能超过 {max}")
    @ApiModelProperty(value = "租户号",hidden=false,required=false,example = "")
    public String getTenantId(){return super.getTenantId();}

    //endregion

    //region 构造方法
    /**无参构造函数*/
    public SysCommonFilter(){super();}
    /**带主键构造函数*/
    public SysCommonFilter(Long id){super(id);}
    //endregion
}
