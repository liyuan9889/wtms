
function changeSearch(searchType, subSearchType) {
    $("#subSearchType").val(subSearchType);
    if (searchType == 1){
        $("#table1Div").css("display", "block");
        $("#table2Div").css("display", "none");
        $("#table3Div").css("display", "none");
        $.table.search('form1', 'bootstrap-table1');
        // 统计量
        // $.ajax({
        //     url: task_prefix + '/ajaxSelectTaskByUserIdForMain',
        //     type: 'get',
        //     dataType: 'json',
        //     success : function (result) {
        //         if (result.code == 0){
        //             $("#task_totalNum").html(result.data.task_totalNum);
        //             $("#task_proceedNum").html(result.data.task_proceedNum);
        //             $("#task_preparedEvaluatingNum").html(result.data.task_preparedEvaluatingNum);
        //             $("#task_failedNum").html(result.data.task_failedNum);
        //             $("#task_finishNum").html(result.data.task_finishNum);
        //             $("#task_overNum").html(result.data.task_overNum);
        //             $("#task_expiredNum").html(result.data.task_expiredNum);
        //         }
        //     }
        // })

    } else if (searchType == 2){
        $("#table1Div").css("display", "none");
        $("#table2Div").css("display", "block");
        $("#table3Div").css("display", "none");
        $.table.search('form1', 'bootstrap-table2');
        // 统计量
        // $.ajax({
        //     url: work_prefix + '/ajaxSelectWorkByUserIdForMain',
        //     type: 'get',
        //     dataType: 'json',
        //     success : function (result) {
        //         if (result.code == 0){
        //             $("#work_totalNum").html(result.data.work_totalNum);
        //             $("#work_proceedNum").html(result.data.work_proceedNum);
        //             $("#work_preparedEvaluatingNum").html(result.data.work_preparedEvaluatingNum);
        //             $("#work_expiredNum").html(result.data.work_expiredNum);
        //         }
        //     }
        // })

    } else if (searchType == 3){
        $("#table1Div").css("display", "none");
        $("#table2Div").css("display", "none");
        $("#table3Div").css("display", "block");
        $.table.search('form1', 'bootstrap-table3');
        // 统计量
        // $.ajax({
        //     url: worklog_prefix + '/ajaxSelectWorklogByUserIdForMain',
        //     type: 'get',
        //     dataType: 'json',
        //     success : function (result) {
        //         if (result.code == 0){
        //             $("#worklog_totalNum").html(result.data.worklog_totalNum);
        //         }
        //     }
        // })
    }
    searchFlag = searchType;
}

var whoFlag = 0;
function myData() {
    $("#myData").addClass("sel");
    $("#allData").removeClass("sel");
    whoFlag = 0;
    $("#userId").val(userId)
    $.ajax({
        url:'/oa/statistics/selectStatisticsForMain',
        type:'GET',
        data:{
            cStartTime : $("#cStartTime").val(),
            cEndTime : $("#cEndTime").val(),
            whoFlag: 0
        },
        dataType:"json",
        success:function (result) {
            $("#task_totalNum").html(result.task_totalNum);
            $("#task_proceedNum").html(result.task_proceedNum);
            $("#task_preparedEvaluatingNum").html(result.task_preparedEvaluatingNum);
            $("#task_failedNum").html(result.task_failedNum);
            $("#task_finishNum").html(result.task_finishNum);
            $("#task_overNum").html(result.task_overNum);
            $("#task_expiredNum").html(result.task_expiredNum);
            $("#work_totalNum").html(result.work_totalNum);
            $("#work_proceedNum").html(result.work_proceedNum);
            $("#work_preparedEvaluatingNum").html(result.work_preparedEvaluatingNum);
            $("#work_expiredNum").html(result.work_expiredNum);
            $("#worklog_totalNum").html(result.worklog_totalNum);
        }
    })
    if (searchFlag == 1) {
        $.table.search('form1', 'bootstrap-table1');
    } else if (searchFlag == 2) {
        $.table.search('form1', 'bootstrap-table2');
    } else if (searchFlag == 3) {
        $.table.search('form1', 'bootstrap-table3');
    }
}

function allData() {
    $("#myData").removeClass("sel");
    $("#allData").addClass("sel");
    whoFlag = 1;
    $("#userId").val("");
    $.ajax({
        url:'/oa/statistics/selectStatisticsForMain',
        type:'GET',
        data:{
            userName : $("#userName").val(),
            cStartTime : $("#cStartTime").val(),
            cEndTime : $("#cEndTime").val(),
            whoFlag: 1
        },
        dataType:"json",
        success:function (result) {
            $("#task_totalNum").html(result.task_totalNum);
            $("#task_proceedNum").html(result.task_proceedNum);
            $("#task_preparedEvaluatingNum").html(result.task_preparedEvaluatingNum);
            $("#task_failedNum").html(result.task_failedNum);
            $("#task_finishNum").html(result.task_finishNum);
            $("#task_overNum").html(result.task_overNum);
            $("#task_expiredNum").html(result.task_expiredNum);
            // $("#work_totalNum").html(result.work_totalNum);
            // $("#work_proceedNum").html(result.work_proceedNum);
            // $("#work_preparedEvaluatingNum").html(result.work_preparedEvaluatingNum);
            // $("#work_expiredNum").html(result.work_expiredNum);
            $("#task1_totalNum").html(result.task1_totalNum);
            $("#task1_proceedNum").html(result.task1_proceedNum);
            $("#task1_preparedEvaluatingNum").html(result.task1_preparedEvaluatingNum);
            $("#task1_failedNum").html(result.task1_failedNum);
            $("#task1_finishNum").html(result.task1_finishNum);
            $("#task1_overNum").html(result.task1_overNum);
            $("#task1_expiredNum").html(result.task1_expiredNum);
            $("#worklog_totalNum").html(result.worklog_totalNum);
        }
    })
    if (searchFlag == 1) {
        $.table.search('form1', 'bootstrap-table1');
    } else if (searchFlag == 2) {
        $.table.search('form1', 'bootstrap-table2');
    } else if (searchFlag == 3) {
        $.table.search('form1', 'bootstrap-table3');
    }
}

function searchDataByType () {
    if (searchFlag == 1) {
        $.table.search('form1', 'bootstrap-table1');
    } else if (searchFlag == 2) {
        $.table.search('form1', 'bootstrap-table2');
    } else if (searchFlag == 3) {
        $.table.search('form1', 'bootstrap-table3');
    }
    $.ajax({
        url:'/oa/statistics/selectStatisticsForMain',
        type:'GET',
        data:{
            userName : $("#userName").val(),
            cStartTime : $("#cStartTime").val(),
            cEndTime : $("#cEndTime").val(),
            whoFlag: 1
        },
        dataType:"json",
        success:function (result) {
            $("#task_totalNum").html(result.task_totalNum);
            $("#task_proceedNum").html(result.task_proceedNum);
            $("#task_preparedEvaluatingNum").html(result.task_preparedEvaluatingNum);
            $("#task_failedNum").html(result.task_failedNum);
            $("#task_finishNum").html(result.task_finishNum);
            $("#task_overNum").html(result.task_overNum);
            $("#task_expiredNum").html(result.task_expiredNum);
            // $("#work_totalNum").html(result.work_totalNum);
            // $("#work_proceedNum").html(result.work_proceedNum);
            // $("#work_preparedEvaluatingNum").html(result.work_preparedEvaluatingNum);
            // $("#work_expiredNum").html(result.work_expiredNum);
            $("#task1_totalNum").html(result.task1_totalNum);
            $("#task1_proceedNum").html(result.task_proceedNum);
            $("#task1_preparedEvaluatingNum").html(result.task1_preparedEvaluatingNum);
            $("#task1_failedNum").html(result.task1_failedNum);
            $("#task1_finishNum").html(result.task1_finishNum);
            $("#task1_overNum").html(result.task1_overNum);
            $("#task1_expiredNum").html(result.task1_expiredNum);
            $("#worklog_totalNum").html(result.worklog_totalNum);
        }
    })
}

function resetTimeSection() {
    window.location.reload();
    $("#searchWeek").removeClass("active");
    $("#searchMonth").removeClass("active");
    $("#searchYear").removeClass("active");


}

function searchDataByTimeSection (searchType) {
    if (searchType == 1) {
        $("#searchWeek").addClass("active");
        $("#searchMonth").removeClass("active");
        $("#searchYear").removeClass("active");
        getWeek();
    } else if (searchType == 2) {
        $("#searchWeek").removeClass("active");
        $("#searchMonth").addClass("active");
        $("#searchYear").removeClass("active");
        $("#cStartTime").val(getCurrentMonthFirst());
        $("#cEndTime").val(getCurrentMonthLast());
    } else if (searchType == 3) {
        $("#searchWeek").removeClass("active");
        $("#searchMonth").removeClass("active");
        $("#searchYear").addClass("active");
        getYearFirstLastDay();
    }
    $.ajax({
        url:'/oa/statistics/selectStatisticsForMain',
        type:'GET',
        data:{
            userName : $("#userName").val(),
            cStartTime : $("#cStartTime").val(),
            cEndTime : $("#cEndTime").val(),
            whoFlag: whoFlag
        },
        dataType:"json",
        success:function (result) {
            $("#task_totalNum").html(result.task_totalNum);
            $("#task_proceedNum").html(result.task_proceedNum);
            $("#task_preparedEvaluatingNum").html(result.task_preparedEvaluatingNum);
            $("#task_failedNum").html(result.task_failedNum);
            $("#task_finishNum").html(result.task_finishNum);
            $("#task_overNum").html(result.task_overNum);
            $("#task_expiredNum").html(result.task_expiredNum);
            // $("#work_totalNum").html(result.work_totalNum);
            // $("#work_proceedNum").html(result.work_proceedNum);
            // $("#work_preparedEvaluatingNum").html(result.work_preparedEvaluatingNum);
            // $("#work_expiredNum").html(result.work_expiredNum);
            $("#task1_totalNum").html(result.task1_totalNum);
            $("#task1_proceedNum").html(result.task1_proceedNum);
            $("#task1_preparedEvaluatingNum").html(result.task1_preparedEvaluatingNum);
            $("#task1_failedNum").html(result.task1_failedNum);
            $("#task1_finishNum").html(result.task1_finishNum);
            $("#task1_overNum").html(result.task1_overNum);
            $("#task1_expiredNum").html(result.task1_expiredNum);
            $("#worklog_totalNum").html(result.worklog_totalNum);
        }
    })
    if (searchFlag == 1){
        $.table.search('form1', 'bootstrap-table1');
    } else if (searchFlag == 2){
        $.table.search('form1', 'bootstrap-table2');
    } else if (searchFlag == 3){
        $.table.search('form1', 'bootstrap-table3');
    }

}

function getToday() {
    var myDate = new Date();
    var time = myDate.toLocaleDateString().split('/').join('-');
    return time;
}

// 本周
function getWeek() {
    var now = new Date();
    var nowTime = now.getTime() ;
    var day = now.getDay();
    var oneDayTime = 24*60*60*1000 ;
    var MondayTime = nowTime - (day-1)*oneDayTime ; // 显示周一
    var SundayTime =  nowTime + (7-day)*oneDayTime ; // 显示周日
    var MondayDate = new Date(MondayTime).getFullYear() + '-' + this.p((new Date(MondayTime).getMonth() + 1)) + '-' + this.p(new Date(MondayTime).getDate());
    var SundayDate = new Date(SundayTime).getFullYear() + '-' + this.p((new Date(SundayTime).getMonth() + 1)) + '-' + this.p(new Date(SundayTime).getDate());
    $("#cStartTime").val(MondayDate);
    $("#cEndTime").val(SundayDate);
}
function p(s) {
    return s < 10 ? '0' + s : s
}

// 本月
function getCurrentMonthFirst() {
    var date = new Date();
    date.setDate(1);
    var month = parseInt(date.getMonth()+1);
    var day = date.getDate();
    if (month < 10) {
        month = '0' + month
    }
    if (day < 10) {
        day = '0' + day
    }
    return date.getFullYear() + '-' + month + '-' + day;
}
function getCurrentMonthLast() {
    var date=new Date();
    var currentMonth=date.getMonth();
    var nextMonth=++currentMonth;
    var nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,1);
    var oneDay=1000*60*60*24;
    var lastTime = new Date(nextMonthFirstDay-oneDay);
    var month = parseInt(lastTime.getMonth()+1);
    var day = lastTime.getDate();
    if (month < 10) {
        month = '0' + month
    }
    if (day < 10) {
        day = '0' + day
    }
    return date.getFullYear() + '-' + month + '-' + day;
}


Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 获取当前月份的第一天和最后一天
 * @returns {string}  例如 2019-09-01~2019-09-30
 */
function getMonthFirstLastDay() {
    var myDate = new Date();
    var currentMonth = myDate.getMonth();
    var firstDay = new Date(myDate.getFullYear(), currentMonth, 1)
    var lastDay = new Date(firstDay.getFullYear(), currentMonth + 1, 0);
    firstDay = firstDay.Format("yyyy-MM-dd");
    lastDay = lastDay.Format("yyyy-MM-dd");
    return firstDay + "~" + lastDay;
}

/**
 * 获取当前年份的第一天和最后一天
 * @returns {string} 例如 2019-01-01~2019-12-31
 */
function getYearFirstLastDay() {
    var firstDay = new Date();
    firstDay.setDate(1);
    firstDay.setMonth(0);
    var lastDay = new Date();
    lastDay.setFullYear(lastDay.getFullYear()+1);
    lastDay.setDate(0);
    lastDay.setMonth(-1);
    firstDay = firstDay.Format("yyyy-MM-dd");
    lastDay = lastDay.Format("yyyy-MM-dd");
    $("#cStartTime").val(firstDay);
    $("#cEndTime").val(lastDay);
    return firstDay + "~" + lastDay;
}
