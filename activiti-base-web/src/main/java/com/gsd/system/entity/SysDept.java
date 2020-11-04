package com.gsd.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysDept对象", description="")
public class SysDept implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "部门id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "上级部门id")
    private String pid;

    @ApiModelProperty(value = "上级部门名称")
    private String parentName;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "电话")
    private String deptPhone;

    @ApiModelProperty(value = "地址")
    private String deptAddress;

    @ApiModelProperty(value = "上级部门id及自己的id")
    private String likeId;

    @ApiModelProperty(value = "序号")
    private Integer orderNum;

    @ApiModelProperty(value = "部门经理")
    private String manager;


}
