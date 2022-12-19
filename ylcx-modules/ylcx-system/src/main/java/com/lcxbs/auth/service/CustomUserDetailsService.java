package com.lcxbs.auth.service;

import com.lcxbs.auth2.exception.PasswordBlankException;
import com.lcxbs.auth2.exception.PasswordErrorException;
import com.lcxbs.auth2.exception.UserNameBlankException;
import com.lcxbs.auth2.model.CustomUser;
import com.lcxbs.auth2.service.MyUserDetailsService;
import com.lcxbs.sys.model.SysMenu;
import com.lcxbs.sys.model.SysUser;
import com.lcxbs.sys.service.SysMenuService;
import com.lcxbs.sys.service.SysUserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义用户认证与授权
 */
@Service
public class CustomUserDetailsService extends MyUserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserLogin(userLogin);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if (sysUser == null) {
            throw new UsernameNotFoundException(userLogin);
        }
        // 获取用户授权
        List<SysMenu> sysMenus = sysMenuService.getListByUserId(sysUser.getUserId());
        // 声明用户授权
        sysMenus.forEach(sysMenu -> {
            if (sysMenu != null && sysMenu.getPermCode() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysMenu.getPermCode());
                grantedAuthorities.add(grantedAuthority);
            }
        });
        return new CustomUser(sysUser.getUserName(), sysUser.getUserPassword(), grantedAuthorities);
    }

    /**
     * 用户名和密码校验
     * @param userLogin
     * @param password
     * @return
     * @throws UsernameNotFoundException
     */
    public CustomUser loadUserByUsernameAndPassword(String userLogin,String password){
        if(StringUtils.isBlank(userLogin)){
            throw new UserNameBlankException("用户名输入错误");
        }
        if(StringUtils.isBlank(password)){
            throw new PasswordBlankException("密码输入错误");
        }
        SysUser sysUser = sysUserService.getByUserLogin(userLogin);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if (sysUser == null) {
            throw new UsernameNotFoundException(userLogin);
        }
        if(!passwordEncoder.matches(password,sysUser.getUserPassword())){
            throw new PasswordErrorException(password);
        }
        // 获取用户授权
        List<SysMenu> sysMenus = null;
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeleteFlag(0L);//未删除
        sysMenu.setDisableFlag(1L);//启用
        // 获取用户授权
        if(sysUser.getNid()==0L){
            //超管获取所有资源权限
            sysMenus = sysMenuService.getList(sysMenu);
        }else {
            sysMenus = sysMenuService.getListByUserId(sysUser.getUserId(),sysMenu);
        }
        // 声明用户授权
        sysMenus.forEach(item -> {
            if (item != null && org.apache.commons.lang.StringUtils.isNotBlank(item.getPermCode())) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(item.getPermCode());
                grantedAuthorities.add(grantedAuthority);
            }
        });
        CustomUser customUser=new CustomUser(sysUser.getUserLogin(), sysUser.getUserPassword(), grantedAuthorities);
        customUser.setId(sysUser.getUserId());
        customUser.setFullName(sysUser.getUserName());
        return customUser;
    }
}
