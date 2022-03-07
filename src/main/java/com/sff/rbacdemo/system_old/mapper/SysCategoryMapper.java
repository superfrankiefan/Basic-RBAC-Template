package com.sff.rbacdemo.system_old.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system_old.dto.TreeSelectDTO;
import com.sff.rbacdemo.system_old.entity.SysCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: 分类字典
 */
public interface SysCategoryMapper extends BaseMapper<SysCategory> {
	
	/**
	  *  根据父级ID查询树节点数据
	 * @param pid
	 * @return
	 */
	public List<TreeSelectDTO> queryListByPid(@Param("pid")  String pid, @Param("query") Map<String, String> query);

	@Select("SELECT ID FROM sys_category WHERE CODE = #{code,jdbcType=VARCHAR}")
	public String queryIdByCode(@Param("code")  String code);
	

}
