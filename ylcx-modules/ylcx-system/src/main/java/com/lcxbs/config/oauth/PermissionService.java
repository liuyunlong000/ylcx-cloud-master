package com.lcxbs.config.oauth;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.core.DynamicDataSource;
import com.lcxbs.exception.CommonException;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Objects;

@Service("ps")
public class PermissionService {
    private static Logger logger = Logger.getLogger(PermissionService.class);

    @Resource
    private SysUserService sysUserService;

    public PermissionService() {

    }

    public boolean hasAuthority(String permission,boolean recordLog, HttpServletRequest request){
        return true;
    }

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @param recordLog  是否记录日志（true记录日志，false不记录日志）
     * @return 用户是否具备某权限
     */
    public boolean hasAuthority(String permission,boolean recordLog,boolean auth, HttpServletRequest request) {
        //添加默认数据源
        DynamicDataSource.setDataSource(DynamicDataSource.DEFAULT_DATA_SOURCE_NAME);
        /** 目设置超管权限，超管可以访问所有接口;
         **/
        try {
            SysUser currentUser = sysUserService.getCurrentUser();
            if (Objects.nonNull(currentUser)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("检查是否是超管时出错！", e);
        }
        if (StringUtils.isBlank(permission)) {
            return true;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        String userLogin = authentication.getName();
        if(StringUtils.isBlank(userLogin)) {
            throw new CommonException(AuthErrorEnum.UNAUTHORIZED.getCode(),AuthErrorEnum.UNAUTHORIZED.getName());
        }
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for (GrantedAuthority role : roles) {
            if (null != role.getAuthority() && permission.contains(role.getAuthority())) {
                return true;
            }
        }
        throw new CommonException(AuthErrorEnum.INSUFFICIENT_PERMISSIONS.getCode(),AuthErrorEnum.INSUFFICIENT_PERMISSIONS.getName()+"功能编码："+permission);
    }
}
