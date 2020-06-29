package com.xr.base.service.impl;

import com.xr.base.entity.SysDept;
import com.xr.base.entity.SysMenu;
import com.xr.base.entity.SysRole;
import com.xr.base.mapper.SysMenuMapper;
import com.xr.base.service.SysMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysRole> roleList( String name,  Integer page,Integer limit) {
        return sysMenuMapper.roleList(name, page, limit);
    }
    @Override
    public List<SysMenu> findMtow(int parentId) {
        return sysMenuMapper.findMtow(parentId);
    }

    @Override
    public void add(Integer roleId, Integer menuId) {
        sysMenuMapper.add(roleId, menuId);
    }

    @Override
    public void delete(Integer id) {
        sysMenuMapper.delete(id);
    }




    @Override
    public List<SysMenu> findMone() {
        List<SysMenu> mone = sysMenuMapper.findMtow(0);
        findMenuList(mone);
        return mone;
    }

    private void findMenuList(List<SysMenu> sysMenuList){
        for (SysMenu sysMenu : sysMenuList){
            //查询子节点
            List<SysMenu> sysMenus = sysMenuMapper.findMtow(sysMenu.getId());
            //回调自己，继续查询字节点
            findMenuList(sysMenus);
            //把查询出的传入集合
            sysMenu.setListM(sysMenus);
        }

    }

}
