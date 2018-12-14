package com.wbg.springJavaConfig.controller;

import com.wbg.springJavaConfig.service.AdminService;
import com.wbg.springJavaConfig.service.Impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.PropertyVetoException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    AdminServiceImpl adminServiceImpl = new AdminServiceImpl();

    public AdminController() throws PropertyVetoException {
    }

    @RequestMapping("/get")
    public String index(Model model){
       model.addAttribute("list",adminServiceImpl.listAll());
        return "Admin";
    }
}
