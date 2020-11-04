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
 * 角色权限表
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRolePermission对象", description="角色权限表")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色 ID")
    private Long roleId;

    @ApiModelProperty(value = "权限 ID")
    private Long permissionId;


}
