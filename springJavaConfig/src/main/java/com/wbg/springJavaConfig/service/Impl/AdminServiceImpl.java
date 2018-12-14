package com.wbg.springJavaConfig.service.Impl;

import com.wbg.springJavaConfig.Mybatis.MySqlSessionFactory;
import com.wbg.springJavaConfig.dao.AdminDao;
import com.wbg.springJavaConfig.entity.Admin;
import com.wbg.springJavaConfig.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.util.List;


public class AdminServiceImpl implements AdminService {
    SqlSession sqlSession = new MySqlSessionFactory().getopenSession();

    public AdminServiceImpl() throws PropertyVetoException {
    }
    public List<Admin> listAll() {
    /* AdminDao adminDao = sqlSession.getMapper(AdminDao.class);*/
        AdminDao adminDao = sqlSession.selectOne("",1);
        return adminDao.listAll();
    }

    public Admin getById(int aId) {

        return  sqlSession.selectOne("getById",1);
        /* AdminDao adminDao = sqlSession.getMapper(AdminDao.class);

        return adminDao.getById(aId);*/
    }

    public static void main(String[] args) throws PropertyVetoException {
        AdminServiceImpl adminService = new AdminServiceImpl();

        List<Admin> list=adminService.listAll();
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }
}
