<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
        $("#excel").click(function () {
            window.location.href="${pageContext.request.contextPath}/banner/excelExport"
        })
       $("#bannerList").jqGrid({
           url:"${pageContext.request.contextPath}/banner/selectAll",
           editurl:"${pageContext.request.contextPath}/banner/edit",
           datatype:"json",
           colNames:["编号","标题","描述","创建时间","状态","图片"],
           colModel:[
               {name:"id"},
               {name:"title",editable:true},
               {name:"description",editable:true},
               {name:"time"},
               {name:"state",editable:true},
               {name:"img",editable:true,edittype:"file",
                   formatter:function (cellvalue, options, rowObject) {
                       return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>"
                   }
               }
           ],
           styleUI:"Bootstrap",
           pager:"#bannerPager",
           multiselect:true,
           viewrecords:true,
           height:"60%"
       }).jqGrid("navGrid","#bannerPager",
           { //处理前台页面按钮组的样式以及展示后者不展示。
               search:true
           },
           {//控制编辑按钮，在编辑之前或者之后要进行的额外操作
               beforeShowForm:function (obj) {
                   obj.find("#imgPath").attr("disabled",true)
               }
           },
           {//控制添加按钮，在添加之前或者之后要进行的额外操作
               closeAfterAdd:true,
               afterSubmit:function (response) {
                   var bannerId=response.responseText;
                   $.ajaxFileUpload({
                       url:"${pageContext.request.contextPath}/banner/upload",
                       fileElementId:"img",
                       data:{bId:bannerId},
                       success:function (data) {

                       }
                   });
                   return "yes";
               }
           },
           {//控制删除按钮，在删除之前或者之后要进行的额外操作
           }
       )
    });
</script>
<button id="excel">导出为Excel</button>
<table id="bannerList"></table>
<div id="bannerPager"></div>