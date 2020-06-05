function queryStatisticsInit(title, searchFlag) {
    $.ajax({
        type: "get",
        url: prefix + "/selectWorkStatisticsList",
        data: {
            deptId: $("#deptId").val(),
            searchType: $("#searchType").val(),
            cStartTime: $("#cStartTime").val(),
            cEndTime: $("#cEndTime").val()
        },
        success: function (result) {
            var objArr = new Array();
            var userIdArr = new Array();
            var userNameArr = new Array();
            var totalNumArr = new Array();
            var proceedNumArr = new Array();
            var finishNumArr = new Array();
            var expiredNumArr = new Array();
            var preparedEvaluatingNumArr = new Array();
            var failedNumArr = new Array();
            var hoursArr = new Array();
            $.each(result.data, function(i,item){
                var obj = {};
                obj.id = item.userId;
                obj.value = item.totalNum;
                objArr.push(obj);
                userIdArr.push(item.userId);
                userNameArr.push(item.userName);
                totalNumArr.push(item.totalNum);
                proceedNumArr.push(item.proceedNum);
                finishNumArr.push(item.finishNum);
                expiredNumArr.push(item.expiredNum);
                preparedEvaluatingNumArr.push(item.preparedEvaluatingNum);
                failedNumArr.push(item.failedNum);
                hoursArr.push(item.hours);
            });

            var dom = document.getElementById("container1");
            var myChart = echarts.init(dom);
            var option;
            if (searchFlag == 1 || searchFlag ==2){
                option = {
                    title: {
                        text: title,
                        subtext: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['总数', '完成', '未完成', '超期', '待评价', '评价未通过']
                    },
                    toolbox: {
                        show: false,
                        feature: {
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            data: userNameArr
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '总数',
                            type: 'bar',
                            data : objArr
                        },
                        {
                            name: '完成',
                            type: 'bar',
                            data: finishNumArr
                        },
                        {
                            name: '未完成',
                            type: 'bar',
                            data: proceedNumArr
                        },
                        {
                            name: '超期',
                            type: 'bar',
                            data: expiredNumArr
                        },
                        {
                            name: '待评价',
                            type: 'bar',
                            data: preparedEvaluatingNumArr
                        },
                        {
                            name: '评价未通过',
                            type: 'bar',
                            data: failedNumArr
                        }
                    ]
                };
            } else {
                option = {
                    title: {
                        text: title,
                        subtext: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    // legend: {
                    //     data: ['总数', '完成', '未完成', '超期', '待评价', '评价未通过']
                    // },
                    toolbox: {
                        show: false,
                        feature: {
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            data: userNameArr
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        // {
                        //     name: '总数',
                        //     type: 'bar',
                        //     data : objArr
                        // },
                        // {
                        //     name: '完成',
                        //     type: 'bar',
                        //     data: finishNumArr
                        // },
                        // {
                        //     name: '未完成',
                        //     type: 'bar',
                        //     data: proceedNumArr
                        // },
                        // {
                        //     name: '超期',
                        //     type: 'bar',
                        //     data: expiredNumArr
                        // },
                        // {
                        //     name: '待评价',
                        //     type: 'bar',
                        //     data: preparedEvaluatingNumArr
                        // },
                        // {
                        //     name: '评价未通过',
                        //     type: 'bar',
                        //     data: failedNumArr
                        // },
                        {
                            name: '时长',
                            type: 'bar',
                            data: hoursArr
                        }
                    ]
                }
            }
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //点击事件
            myChart.on('click', function(params) {
                console.log(params);
                // alertWin(4);
            });
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    });
}

/* 用户管理-组织 */
function dept() {
    var url = ctx + "system/dept";
    $.modal.openTab("组织管理", url);
}
