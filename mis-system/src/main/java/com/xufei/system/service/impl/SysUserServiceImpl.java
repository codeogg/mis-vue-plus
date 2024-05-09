package com.xufei.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.utils.ExcelUtil;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysUser;
import com.xufei.system.domain.SysUserMenu;
import com.xufei.system.domain.SysUserRole;
import com.xufei.system.domain.dto.AssignUserRoleDto;
import com.xufei.system.domain.vo.SysUserExportVo;
import com.xufei.system.listener.SysUserImportListener;
import com.xufei.system.mapper.SysUserMapper;
import com.xufei.system.mapper.SysUserMenuMapper;
import com.xufei.system.mapper.SysUserRoleMapper;
import com.xufei.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper baseMapper;
    private final SysUserMenuMapper userMenuMapper;
    private final SysUserRoleMapper userRoleMapper;

    @Override
    public TableData<SysUser> selectPageUserList(SysUser user, PageQuery pageQuery) {
        Page<SysUser> page = baseMapper.selectPage(pageQuery.build(), this.buildQueryWrapper(user));
        return TableData.build(page);
    }

    @Override
    public void save(SysUser user) {
        if (isJobNumberExist(user)) {
            throw new ServiceException("工号已存在");
        }

        if (isUserNameExist(user)) {
            throw new ServiceException("账号已存在");
        }
        user.setPassword(CommonConstants.INITIAL_PASSWORD);
        user.setEmail(user.getUserName() + CommonConstants.EMAIL_SUFFIX);
        user.setEmailPassword(String.valueOf(Math.random()));
        user.setAvatar("https://pic1.zhimg.com/50/v2-39330eb611dce1ff3acff386b9c7d7b9_720w.jpg?source=1940ef5c");

        baseMapper.insert(user);
    }

    @Override
    public void update(SysUser user) {
        if (isJobNumberExist(user)) {
            throw new ServiceException("工号已存在");
        }

        if (isUserNameExist(user)) {
            throw new ServiceException("账号已存在");
        }

        user.setEmail(user.getUserName() + CommonConstants.EMAIL_SUFFIX);
        baseMapper.updateById(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
        userMenuMapper.deleteByUserId(id);
        userRoleMapper.deleteByUserId(id);
    }

    @Override
    public SysUser getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void updateStatus(List<Long> ids) {
        UpdateWrapper<SysUser> uw = new UpdateWrapper<>();
        uw.set("status", 1L).in("id", ids);
        baseMapper.update(uw);
    }

    @Override
    public String importData(MultipartFile file) throws Exception {
        return ExcelUtil.importExcel(file, SysUser.class, new SysUserImportListener());
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, userName);
        return baseMapper.selectOne(lqw);
    }

    @Override
    public void exportData(SysUser searchData, HttpServletResponse response) {

        QueryWrapper<SysUser> queryWrapper = this.buildQueryWrapper(searchData);
        List<SysUser> userList = baseMapper.selectList(queryWrapper);

        try {
            String fileName = URLEncoder.encode("用户导出数据.xlsx", "UTF-8").replaceAll("\\+", "%20");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename*=" + fileName);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
            EasyExcel.write(response.getOutputStream(), SysUserExportVo.class).sheet("用户清单")
                    .doWrite(() -> userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void assignRole(AssignUserRoleDto dto) {
        Long userId = dto.getUserId();
        List<Long> roleIds = dto.getRoleIds();

        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));

        for (Long roleId : roleIds) {
            SysUserRole userRole = new SysUserRole(userId, roleId);
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public List<SysUserMenu> getAssignedUserMenuIds(Long siteId, Long userId) {
        LambdaQueryWrapper<SysUserMenu> lqw = new LambdaQueryWrapper<SysUserMenu>()
                .eq(SysUserMenu::getSiteId, siteId)
                .eq(SysUserMenu::getUserId, userId);
        return userMenuMapper.selectList(lqw);
    }

    /**
     * 判断工号是否已存在
     *
     * @param user
     * @return true 工号已存在 false 工号不存在
     */
    private boolean isJobNumberExist(SysUser user) {
        LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getJobNumber, user.getJobNumber())
                .ne(ObjectUtil.isNotNull(user.getId()), SysUser::getId, user.getId());
        return baseMapper.exists(lqw);
    }

    private boolean isUserNameExist(SysUser user) {
        LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, user.getUserName())
                .ne(ObjectUtil.isNotNull(user.getId()), SysUser::getId, user.getId());
        return baseMapper.exists(lqw);
    }

    private QueryWrapper<SysUser> buildQueryWrapper(SysUser user) {
        Map<String, Object> params = user.getParams();
        QueryWrapper<SysUser> wrapper = Wrappers.query();

        wrapper.like(StringUtil.isNotBlank(user.getUserName()), "user_name", user.getUserName())
                .like(StringUtil.isNotBlank(user.getNickName()), "nick_name", user.getNickName())
                .like(StringUtil.isNotBlank(user.getJobNumber()), "job_number", user.getJobNumber())
                .eq(StringUtil.isNotBlank(user.getSex()), "sex", user.getSex());

        if (params.containsKey("createTime") && params.get("createTime") instanceof List) {
            ArrayList list = (ArrayList) params.get("createTime");
            wrapper.between(CollectionUtil.isNotEmpty(list), "create_time", list.get(0), list.get(1));
        }

        return wrapper;
    }
}
