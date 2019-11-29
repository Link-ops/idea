<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
       $("#albumList").jqGrid({
           url:"${pageContext.request.contextPath}/album/selectAll",
           editurl:"${pageContext.request.contextPath}/album/edit",
           datatype:"json",
           colNames:[
               "编号","标题","分数","作者","播音员","章节数","专辑简介","状态","发行时间","上传时间","插图"
           ],
           colModel:[
               {name:"id"},
               {name:"title",editable:true},
               {name:"score",editable:true},
               {name:"author",editable:true},
               {name:"announcer",editable:true},
               {name:"chapterNumber",editable:true},
               {name:"synopsis",editable:true},
               {name:"state",editable:true},
               {name:"createTime"},
               {name:"uploadTime"},
               {name:"img",editable:true,edittype:"file",
                   formatter:function (cellvalue, options, rowObject) {
                       return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>"
                   }
               }
           ],
           styleUI:"Bootstrap",
           autowidth:true,
           pager:"#albumPager",
           multiselect:true,
           viewrecords:true,
           height:"60%",
           subGrid:true,
           subGridRowExpanded:function (subGridId, albumId) {
               var tableId = subGridId+"table";
               var pagerId = subGridId+"pager";
               $("#"+subGridId).html(
                   "<table id="+tableId+"></table>\n" +
                   "<div id="+pagerId+"></div>"
               );
               $("#"+tableId).jqGrid({
                   url:"${pageContext.request.contextPath}/chapter/selectAll?albumId="+albumId,
                   editurl:"${pageContext.request.contextPath}/chapter/edit?albumId="+albumId,
                   datatype:"json",
                   colNames:["编号","标题","时长","上传日期","文件大小","音频"],
                   colModel:[
                       {name:"id"},//<input id="id">
                       {name:"title",editable:true},
                       {name:"time"},
                       {name:"uploadTime"},
                       {name:"size"},
                       {name:"music",editable:true,edittype:"file",
                           formatter:function (cellvalue, options, rowObject) {
                               return "<audio  controls src='${pageContext.request.contextPath}/music/"+cellvalue+"'/>"
                           }
                       }
                   ],
                   styleUI:"Bootstrap",
                   autowidth:true,
                   pager:"#"+pagerId,
                   multiselect:true,
                   viewrecords:true,
                   height:"60%"
               }).jqGrid("navGrid","#"+pagerId,
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
                           var id=response.responseText;
                           $.ajaxFileUpload({
                               url:"${pageContext.request.contextPath}/chapter/upload",
                               fileElementId:"music",
                               data:{bId:id},
                               success:function (data) {

                               }
                           });
                           return "yes";
                       }
                   },
                   {//控制删除按钮，在删除之前或者之后要进行的额外操作
                   }
               )
           }
       }).jqGrid("navGrid","#albumPager",
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
                   var albumId=response.responseText;
                   $.ajaxFileUpload({
                       url:"${pageContext.request.contextPath}/album/upload",
                       fileElementId:"img",
                       data:{bId:albumId},
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
<table id="albumList"></table>
<div id="albumPager"></div>
