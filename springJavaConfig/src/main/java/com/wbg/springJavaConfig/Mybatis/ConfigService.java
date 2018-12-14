package com.wbg.springJavaConfig.Mybatis;

import com.wbg.springJavaConfig.entity.Admin;
import com.wbg.springJavaConfig.service.Impl.AdminServiceImpl;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {MySqlSessionFactory.class})
public class ConfigService {
}
