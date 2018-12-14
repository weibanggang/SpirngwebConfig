package com.wbg.springJavaConfig.dao;

import com.wbg.springJavaConfig.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminDao {
   /* @Select("select * from Admin")*/
    List<Admin> listAll();
    Admin getById(int aId);


    /*
     @Select("select * from Admin")
    @Select("select * from Admin where a_id = #{aId}")
    */
}
