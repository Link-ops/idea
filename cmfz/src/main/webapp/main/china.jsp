<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="../echarts/echarts.js"></script>
<script type="text/javascript" src="../echarts/china.js"></script>
<script type="text/javascript" src="../goeasy/goeasy-1.0.3.js"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="china" style="width: 600px;height: 600px;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('china'));

    function randomData() {
        return Math.round(Math.random() * 10000);
    }

    var option = {
        title: {
            text: '持明法洲App用户分布图',
            subtext: new Date(),
            left: 'center'
        },
        tooltip: {},
        // 说明
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 20,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        // 工具箱
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }

            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }

            }
        ]
    };
    myChart.setOption(option);
    $.ajax({
        url:'${pageContext.request.contextPath}/user/map',
        type:'get',
        datatype:'json',
        success:function (result) {
            myChart.setOption({
                series: [
                    {
                        data: result["n"]
                    },
                    {
                        data: result["y"]
                    }
                ]
            });
        }
    })
    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io',
        appkey:"BC-c3f6eb39b6784839a30c7bb2a7001a37"
    });
    goEasy.subscribe({
        channel:"MyChannel",
        onMessage: function (data) {
            $.ajax({
                url:'${pageContext.request.contextPath}/user/map',
                type:'post',
                datatype:'json',
                success:function (result) {
                    myChart.setOption({
                        series: [
                            {
                                data: result["n"]
                            },
                            {
                                data: result["y"]
                            }
                        ],title: {
                            text: data.content,
                        }
                    });
                }
            })
        }
    });
</script>