package com.wbg.springJavaConfig.service;

import com.wbg.springJavaConfig.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> listAll();

    Admin getById(int aId);
}
