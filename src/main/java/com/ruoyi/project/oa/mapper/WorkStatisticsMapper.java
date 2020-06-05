package com.ruoyi.project.oa.mapper;

import com.ruoyi.project.oa.domain.WorkStatistics;
import java.util.List;

public interface WorkStatisticsMapper {

    List<WorkStatistics> selectWorklogStatisticsList(WorkStatistics workStatistics);

    List<WorkStatistics> selectWorklogMinuteStatisticsList(WorkStatistics workStatistics);

    List<WorkStatistics> selectTaskStatistics(WorkStatistics workStatistics);

    List<WorkStatistics> selectWorkStatisticsList(WorkStatistics workStatistics);

}
