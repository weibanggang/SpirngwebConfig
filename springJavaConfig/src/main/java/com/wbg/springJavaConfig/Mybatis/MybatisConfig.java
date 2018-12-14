package com.wbg.springJavaConfig.Mybatis;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wbg.springJavaConfig.dao.AdminDao;
import com.wbg.springJavaConfig.entity.Admin;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//@Configuration声明这是一个xml配置文件
@Configuration
public class MybatisConfig {

    /**
     * C3p0的连接池
     * @return
     * @throws PropertyVetoException
     */
   @Bean
    public DataSource getC3p0DataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass("org.mariadb.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/wbg_logistics");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setMaxPoolSize(30);
        return dataSource;
    }
    /**
     * MyBatis的连接池
     * @return
     */
    //@Bean
    public DataSource getMyBatisDataSource(){
        //数据库连接池
        PooledDataSource dataSource = new PooledDataSource();
        //设驱动
        dataSource.setDriver("org.mariadb.jdbc.Driver");
        //用户名
        dataSource.setUsername("root");
        //密码
        dataSource.setPassword("123456");
        //数据库连接
        dataSource.setUrl("jdbc:mariadb://localhost:3306/wbg_logistics");
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }
    /**
     * Spring自带的SimpleDriverDataSource
     * @return
     */
    //@Bean
    public DataSource getSimpleDriverDataSource(){
        SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
       return dataSource;
    }

    /**
     * 获取SqlSessionFactoryBean
     *
     * @return
     */
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        //注入数据库连接池
        sqlSessionFactoryBean.setDataSource(dataSource);
        //配置MyBaties全局配置文件:mybatis-config.xml
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        //扫描entity包 使用别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.wbg.springJavaConfig.dao");
        //扫描sql配置文件:mapper需要的xml文件
        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("classpath:mapper/*.xml")});
        return sqlSessionFactoryBean;
    }



}
