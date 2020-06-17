package com.xr.base.shiro;


import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysMenu;
import com.xr.base.entity.SysRole;
import com.xr.base.service.SysEmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm{
    @Override
    public String getName() {
        return "myShiroRealm";
    }

    @Autowired
    private SysEmpService sysEmpService;

    @Override
    /**
     * 授权
     * doGetAuthorizationInfo方法是在我们调用
     * SecurityUtils.getSubject().isPermitted（）这个方法时触发，
     * 开启了注解就是进入有 @RequiresPermissions或@RequiresRoles() 两个注解时触发
     * 授权后用户角色及权限会保存在缓存中的
     *
     * @param principal
     * @return
     * @RequiresPermissions这个注解起始就是在执行SecurityUtils.getSubject().isPermitted（）
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 从session中获取 user 对象
        Session session = SecurityUtils.getSubject().getSession();
        SysEmp sysemp = (SysEmp)session.getAttribute("USER_SESSION");
        // 查到权限数据，返回授权信息，要包括上面的权限和角色(可选，一般不这么用了)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 根据身份信息获取角色信息，权限信息
        List<SysRole> roles = sysEmpService.findRoles(sysemp.getName());
        List<SysMenu> menu = sysEmpService.findMenu(sysemp.getName());

        for (SysRole role : roles) {
            simpleAuthorizationInfo.addRole(role.getName());
            System.out.println("roleName:"+role.getName());
            // 根据身份信息获取权限信息
            for (SysMenu m : menu) {
                if(m.getPerms()!=null) {
                    simpleAuthorizationInfo.addStringPermission(m.getPerms());
                    System.out.println("menuName:"+m.getPerms());
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证.登录
     * doGetAuthenticationInfo这个方法是在用户登录的时候调用的
     * 也就是执行SecurityUtils.getSubject().login()的时候调用；(即:登录验证)
     * 验证通过后会用户保存在缓存中的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException  {
        try {
            //获取用户的输入的账号.
            String username = (String) token.getPrincipal();
            //通过username从数据库中查找 User对象，如果找到，没找到.
            //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
            SysEmp sysUser = sysEmpService.findUserByUserName(username);
            if (sysUser == null) {
                return null;
            }
            System.out.println();
//        if (sysUser.getStatus() == 0) { //账户冻结
//            throw new LockedAccountException();
//        }
            System.out.println(new SimpleByteSource(sysUser.getSalt()));
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    username, //用户名
                    sysUser.getPassword(), //密码
                    new SimpleByteSource(sysUser.getSalt()),//salt=username+salt密码加盐
                    getName()  // 当前 realm对象的name.调用父类的getName()方法即可
            );
            // 将用户信息存入到session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("USER_SESSION", sysUser);
            return authenticationInfo;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}