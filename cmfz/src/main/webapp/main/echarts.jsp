<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="../echarts/echarts.js"></script>
    <script type="text/javascript" src="../goeasy/goeasy-1.0.3.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '人数分析',
            //link:'http://www.jd.com',
            //show:false
            textStyle:{
                color:'black'
            }
        },
        tooltip: {},
        legend: {
            data:['人数'],
            type:'plain'
        },
        xAxis: {
            data: ["一周内","两周内","三周内"]
        },
        yAxis: {},
        series: [{
            name: '一周内',
            type: 'bar'
        }]
    };

    $.ajax({
        url:'${pageContext.request.contextPath}/user/time',
        type:'get',
        datatype:'json',
        success:function (result) {

            myChart.setOption({
                series: [{
                    data: [result.n1,result.n2,result.n3]
                }]
            });
        }
    })
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io',
        appkey:"BC-c3f6eb39b6784839a30c7bb2a7001a37"
    });
    goEasy.subscribe({
        channel:"MyChannel",
        onMessage: function (data) {
            $.ajax({
                url:'${pageContext.request.contextPath}/user/time',
                type:'post',
                datatype:'json',
                success:function (result) {
                    myChart.setOption({
                        series: [{
                            data: [result.n1,result.n2,result.n3]
                        }],title: {
                            text: data.content,
                        }
                    });
                }
            })
        }
    });
</script>
</body>
</html>