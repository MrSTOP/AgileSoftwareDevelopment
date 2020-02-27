<%--
  Created by IntelliJ IDEA.
  Author: 闫坤炜
  User: MrST
  Date: 2020/2/27
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>查询列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/footable/footable.core.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>视频查询</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" action="${pageContext.request.contextPath}/VideoController/queryVideoByOther" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名</label>

                            <div class="col-sm-10">
                                <input type="text" id="business_name" class="form-control" name="business_name">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开始时间</label>

                            <div class="col-sm-10">
                                <input type="date" id="startdate" class="form-control" name="startdate">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">结束时间</label>

                            <div class="col-sm-10">
                                <input type="date" id="enddate" class="form-control" name="enddate">
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">查询</button>
                                <button class="btn btn-white" type="reset">取消</button>
                            </div>
                        </div>
                    </form>

                    <hr>
                    <!--数据表顶部查询 结束-->
                    <!--数据表开始-->
                    <div class="jqGrid_wrapper">
                        <table class="footable table table-stripped table-hover" data-page-size="8" data-filter=#filter>
                            <!--数据头 开始-->
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>视频标题</th>
                                <th>上传者</th>
                                <th>上传时间</th>
                                <th>播放</th>
                            </tr>
                            </thead>
                            <!--数据头 结束-->
                            <!--数据体 开始-->
                            <tbody>
                            <c:forEach var="video" items="${requestScope.videoList}">
                                <tr class="gradeX">
                                    <td>${video.businessId}</td>
                                    <td>${video.videoTitle}</td>
                                    <td>${video.businessInfoLegalPerson}</td>
                                    <td class="center">${video.videoDate.toLocaleString()}</td>
                                    <td class="center"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#videoPlay" onclick="changeVideo('${video.videoSrc}')">播放</button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <!--数据体 结束-->
                            <!--数据分页 开始-->
                            <tfoot>
                            <tr>
                                <td colspan="5">
                                    <ul class="pagination pull-right"></ul>
                                </td>
                            </tr>
                            </tfoot>
                            <!--数据分页 结束-->
                        </table>
                    </div>
                    <p>&nbsp;</p>
                    <!--数据表结束-->
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="videoPlay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="videoPlayTitle">视频</h4>
            </div>
            <div class="modal-body">
                <video id="videoPlaySrc" src="" controls="controls"
                       width="300" height="500"></video>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
<%--                <button type="button" class="btn btn-primary">Save changes</button>--%>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/js/plugins/footable/footable.all.min.js"></script>


<!--
    &lt;!&ndash; Peity &ndash;&gt;
    <script src="${pageContext.request.contextPath}/js/plugins/peity/jquery.peity.min.js"></script>
    &lt;!&ndash; jqGrid &ndash;&gt;
    <script src="${pageContext.request.contextPath}/js/plugins/jqgrid/i18n/grid.locale-cn.js?0820"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>
-->


<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>
<script>
    $(document).ready(function () {
        $('.footable').footable();
        $('.footable2').footable();
    });
    function changeVideo(src) {
        $("#videoPlaySrc").attr("src", "${pageContext.request.contextPath}" + src);
    }
</script>

</body>

</html>

