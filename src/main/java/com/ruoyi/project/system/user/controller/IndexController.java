package com.ruoyi.project.system.user.controller;

import java.util.List;
import com.ruoyi.project.oa.domain.Task;
import com.ruoyi.project.oa.domain.Task1;
import com.ruoyi.project.oa.domain.Work;
import com.ruoyi.project.oa.domain.Worklog;
import com.ruoyi.project.oa.mapper.Task1Mapper;
import com.ruoyi.project.oa.mapper.TaskMapper;
import com.ruoyi.project.oa.mapper.WorkMapper;
import com.ruoyi.project.oa.mapper.WorklogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController extends BaseController
{
    private final IMenuService menuService;
    private final IConfigService configService;
    private final RuoYiConfig ruoYiConfig;
    private final TaskMapper taskMapper;
    private final Task1Mapper task1Mapper;
    private final WorkMapper workMapper;
    private final WorklogMapper worklogMapper;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {

        Long userId = getUserId();
        List<Task> taskList = taskMapper.selectTaskByUserIdForMain(new Task());
        for (Task task : taskList) {
            int searchType = task.getSearchType();
            int num = task.getNum();
            switch (searchType) {
                case 0: // 总数
                    mmap.put("task_totalNum", num);
                    break;
                case 1: // 进行中
                    mmap.put("task_proceedNum", num);
                    break;
                case 2: // 待评价
                    mmap.put("task_preparedEvaluatingNum", num);
                    break;
                case 3: // 评价未通过
                    mmap.put("task_failedNum", num);
                    break;
                case 4: // 已完结
                    mmap.put("task_finishNum", num);
                    break;
                case 5: // 已终结
                    mmap.put("task_overNum", num);
                    break;
                case 6: // 超期
                    mmap.put("task_expiredNum", num);
                    break;
            }
        }
        List<Task1> taskList1 = task1Mapper.selectTask1ByUserIdForMain(new Task1());
        for (Task1 task : taskList1) {
            int searchType = task.getSearchType();
            int num = task.getNum();
            switch (searchType) {
                case 0: // 总数
                    mmap.put("task1_totalNum", num);
                    break;
                case 1: // 进行中
                    mmap.put("task1_proceedNum", num);
                    break;
                case 2: // 待评价
                    mmap.put("task1_preparedEvaluatingNum", num);
                    break;
                case 3: // 评价未通过
                    mmap.put("task1_failedNum", num);
                    break;
                case 4: // 已完结
                    mmap.put("task1_finishNum", num);
                    break;
                case 5: // 已终结
                    mmap.put("task1_overNum", num);
                    break;
                case 6: // 超期
                    mmap.put("task1_expiredNum", num);
                    break;
            }
        }
//        List<Work> workList = workMapper.selectWorkByUserIdForMain(new Work());
//        for (Work work : workList) {
//            int searchType = work.getSearchType();
//            int num = work.getNum();
//            switch (searchType) {
//                case 0: // 总数
//                    mmap.put("work_totalNum", num);
//                    break;
//                case 1: // 进行中
//                    mmap.put("work_proceedNum", num);
//                    break;
//                case 2: // 已完结
//                    mmap.put("work_preparedEvaluatingNum", num);
//                    break;
//                case 3: // 超期
//                    mmap.put("work_expiredNum", num);
//                    break;
//            }
//        }
        List<Worklog> worklogList = worklogMapper.selectWorklogByUserIdForMain(new Worklog());
        for (Worklog worklog : worklogList) {
            int searchType = worklog.getSearchType();
            int num = worklog.getNum();
            switch (searchType) {
                case 0: // 总数
                    mmap.put("worklog_totalNum", num);
                    break;
            }
        }


        mmap.put("version", ruoYiConfig.getVersion());
        mmap.put("userId", userId);
        return "main";
    }
}
