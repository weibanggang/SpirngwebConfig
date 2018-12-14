package com.wbg.springJavaConfig.Mybatis;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wbg.springJavaConfig.dao.AdminDao;
import com.wbg.springJavaConfig.entity.Admin;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


public class MySqlSessionFactory  {

    public static SqlSession getopenSession() throws PropertyVetoException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory(getC3p0DataSource());
        sqlSessionFactory.openSession();
        SqlSession sqlSession=null;
        try {
            //打开SqlSession会话
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.commit();//提交事务
        }catch (Exception ex){
            sqlSession.rollback();//回滚
        }
        return sqlSession;
    }

    public static DataSource getC3p0DataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass("org.mariadb.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/wbg_logistics");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setMaxPoolSize(30);
        return dataSource;
    }

    public static SqlSessionFactory getSqlSessionFactory(DataSource dataSource){

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //创建Configuration对象
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        //注册一个MyBatis上下文别名
        configuration.getTypeAliasRegistry().registerAlias("admin", Admin.class);
        //加入一个映射器
        configuration.addMapper(AdminDao.class);
        //使用SqlSessionFactoryBuilder构建SqlSessionFactory
        //构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }

}
