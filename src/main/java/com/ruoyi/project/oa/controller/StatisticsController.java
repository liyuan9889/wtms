package com.ruoyi.project.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.oa.domain.Task;
import com.ruoyi.project.oa.domain.Work;
import com.ruoyi.project.oa.domain.WorkStatistics;
import com.ruoyi.project.oa.domain.Worklog;
import com.ruoyi.project.oa.mapper.TaskMapper;
import com.ruoyi.project.oa.mapper.WorkMapper;
import com.ruoyi.project.oa.mapper.WorkStatisticsMapper;
import com.ruoyi.project.oa.mapper.WorklogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/oa/statistics")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticsController extends BaseController {

    private final WorkStatisticsMapper workStatisticsMapper;
    private final TaskMapper taskMapper;
    private final WorkMapper workMapper;
    private final WorklogMapper worklogMapper;

    private String prefix = "oa/statistics";

    @GetMapping()
    public String task()
    {
        return prefix + "/workStatistics";
    }

    @GetMapping("/selectWorkStatisticsList")
    @ResponseBody
    public AjaxResult selectWorkStatisticsList(@RequestParam("deptId") Integer deptId, @RequestParam("searchType") Integer searchType,
                                               @RequestParam("cStartTime") String cStartTime, @RequestParam("cEndTime") String cEndTime) {
        WorkStatistics workStatistics = new WorkStatistics();
        workStatistics.setDeptId(deptId);
        workStatistics.setCStartTime(cStartTime);
        workStatistics.setCEndTime(cEndTime);
        List<WorkStatistics> workStatisticsList = new ArrayList<>();
        switch (searchType) {
            case 1:
                workStatisticsList = workStatisticsMapper.selectTaskStatistics(workStatistics);
                break;
            case 2:
                workStatisticsList = workStatisticsMapper.selectWorkStatisticsList(workStatistics);
                break;
            case 3:
//                workStatisticsList = workStatisticsMapper.selectWorklogStatisticsList(workStatistics);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // 获取当前月第一天
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, 0);
                c.set(Calendar.DAY_OF_MONTH, 1);
                String day1 = format.format(c.getTime());
                workStatistics.setStartDate(day1);
                // 获取当前月最后一天
                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                String day2 = format.format(ca.getTime());
                workStatistics.setEndDate(day2);
                workStatisticsList = workStatisticsMapper.selectWorklogMinuteStatisticsList(workStatistics);
                // 转换小时
                for (WorkStatistics work : workStatisticsList) {
                    double d = makeDivision(work.getMinutes(), 60);
                    work.setHours(d);
                }
                break;
        }

        return AjaxResult.success("查询成功", workStatisticsList);
    }

    public Double makeDivision(int num1, int num2) {
        BigDecimal bigDecimal = new BigDecimal(num1);
        BigDecimal divisor = new BigDecimal(num2);

        // 使用四舍五入模式，保留两位小数，注意模式HALF_UP
        MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
        Double calcResult = bigDecimal.divide(divisor, mc).doubleValue();
        return calcResult;
    }

    @GetMapping("/selectStatisticsForMain")
    @ResponseBody
    public Object selectTaskByUserIdForMain(@RequestParam(value="cStartTime", required = false) String cStartTime,
                                            @RequestParam(value="userName", required = false) String userName,
                                            @RequestParam(value="cEndTime", required = false) String cEndTime,
                                            @RequestParam(value="whoFlag", required = false) String whoFlag) {
        JSONObject jsonObject = new JSONObject();
        Task t = new Task();
        Work w = new Work();
        Worklog log = new Worklog();
        if (StringUtils.isNotEmpty(cStartTime) && StringUtils.isNotEmpty(cEndTime)) {
            t.setCStartTime(cStartTime);
            t.setCEndTime(cEndTime);
            w.setCStartTime(cStartTime);
            w.setCEndTime(cEndTime);
            log.setCStartTime(cStartTime);
            log.setCEndTime(cEndTime);
        }

        if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(userName)) {
            t.setUserName(userName);
            w.setUserName(userName);
            log.setUserName(userName);
        }

        if (StringUtils.isNotEmpty(whoFlag) && whoFlag.equals("0")){
            Long userId = getUserId();
            t.setUserId(userId);
            w.setUserId(userId);
            log.setUserId(userId);
        }

        List<Task> taskList = taskMapper.selectTaskByUserIdForMain(t);
        for (Task task : taskList) {
            int searchType = task.getSearchType();
            int num = task.getNum();
            switch (searchType) {
                case 0: // 总数
                    jsonObject.put("task_totalNum", num);
                    break;
                case 1: // 进行中
                    jsonObject.put("task_proceedNum", num);
                    break;
                case 2: // 待评价
                    jsonObject.put("task_preparedEvaluatingNum", num);
                    break;
                case 3: // 评价未通过
                    jsonObject.put("task_failedNum", num);
                    break;
                case 4: // 已完结
                    jsonObject.put("task_finishNum", num);
                    break;
                case 5: // 已终结
                    jsonObject.put("task_overNum", num);
                    break;
                case 6: // 超期
                    jsonObject.put("task_expiredNum", num);
                    break;
            }
        }

        List<Work> workList = workMapper.selectWorkByUserIdForMain(w);
        for (Work work : workList) {
            int searchType = work.getSearchType();
            int num = work.getNum();
            switch (searchType) {
                case 0: // 总数
                    jsonObject.put("work_totalNum", num);
                    break;
                case 1: // 进行中
                    jsonObject.put("work_proceedNum", num);
                    break;
                case 2: // 已完结
                    jsonObject.put("work_preparedEvaluatingNum", num);
                    break;
                case 3: // 超期
                    jsonObject.put("work_expiredNum", num);
                    break;
            }
        }

        List<Worklog> worklogList = worklogMapper.selectWorklogByUserIdForMain(log);
        for (Worklog worklog : worklogList) {
            int searchType = worklog.getSearchType();
            int num = worklog.getNum();
            switch (searchType) {
                case 0: // 总数
                    jsonObject.put("worklog_totalNum", num);
                    break;
            }
        }
        return jsonObject;
    }


}
