package com.gsd.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysPermission对象", description="权限表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "权限 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父权限 ID (0为顶级菜单)")
    private Long parentId;

    @ApiModelProperty(value = "权限名称")
    private String label;

    @ApiModelProperty(value = "授权标识符")
    private String code;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "路由名称")
    private String name;

    @ApiModelProperty(value = "授权路径")
    private String url;

    @ApiModelProperty(value = "序号")
    private Integer orderNum;

    @ApiModelProperty(value = "类型(0 目录 1菜单，2按钮)")
    private Integer type;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "备注")
    private String remark;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "是否是首页(0不是 1是)")
    private String isHome;

    @ApiModelProperty(value = "父级菜单名称")
    private String parentName;

    @TableField(exist = false)
    private List<SysPermission> children = new ArrayList<>();
}
