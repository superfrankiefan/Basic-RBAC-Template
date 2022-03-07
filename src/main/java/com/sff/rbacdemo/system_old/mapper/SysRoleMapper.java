package com.sff.rbacdemo.system_old.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system_old.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @Description: 删除角色与用户关系
     */
    @Delete("delete from sys_user_role where role_id = #{roleId}")
    void deleteRoleUserRelation(@Param("roleId") String roleId);


    /**
     * @Description: 删除角色与权限关系
     */
    @Delete("delete from sys_role_resource where role_id = #{roleId}")
    void deleteRolePermissionRelation(@Param("roleId") String roleId);

}
