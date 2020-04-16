package wxs.admin.doman.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxs.admin.doman.po.UserRolePo;

import java.util.List;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRolePo> {
    public  void deleteUserRoleMapping(@Param("userId") String userId);
    public  void saveUserRoleMapping(List<UserRolePo> userRolePoList);
}
