package com.zjt.crm.workbench.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.workbench.pojo.Clue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* @author ugozjt
* @description 针对表【tbl_clue】的数据库操作Mapper
* @createDate 2022-07-15 23:42:52
* @Entity com.zjt.crm.workbench.pojo.Clue
*/
@Repository
public interface ClueMapper extends BaseMapper<Clue> {
    /**
     * 添加线索
     * @param clue 线索实体类
     * @return 影响条数
     */
    int insertClue(Clue clue);

    /**
     * 根据id查询信息，表连接替换owner.id、create_by、edit_by
     * @param id
     * @return
     */
    Clue selectClueForDetailByClueId(String id);

    /**
     * 查询clue分页信息根据page(pageNo,pageSize)
     */
    IPage<Clue> selectClueForPageByCondition(@Param("page") Page<Clue> page, @Param("clue") Clue clue);

    /**
     * 根据id查询clue详细信息使用原先字段，不用表连接
     * @param clueId clueId
     * @return Clue
     */
    Clue selectClueByIdWithOriginalField(String clueId);

    /**
     * 根据线索id删除线索
     * @param clueId
     * @return
     */
    int deleteClueByClueId(String clueId);

}




