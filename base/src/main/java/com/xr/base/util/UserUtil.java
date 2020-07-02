package com.xr.base.util;

import com.xr.base.entity.SysEmp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class UserUtil {

    public static String getUserName(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        SysEmp loginUser = (SysEmp) session.getAttribute("USER_SESSION");
        return loginUser.getUsername();
    }
}
