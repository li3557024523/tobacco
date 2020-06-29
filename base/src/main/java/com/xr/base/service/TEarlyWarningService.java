package com.xr.base.service;

import com.xr.base.entity.TEarlyWarning;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TEarlyWarningService {
    /**
     * 查询所以岗位部门下的数据
     */
    List<TEarlyWarning> list();
}
