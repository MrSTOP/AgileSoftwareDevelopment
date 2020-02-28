<%--
  Created by IntelliJ IDEA.
  User: 54439
  Date: 2020/2/27
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>数据列表</title>
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
                    <h5>音频列表</h5>
                </div>
                <div class="ibox-content">


                    <hr>
                    <!--数据表顶部查询 结束-->
                    <!--数据表开始-->
                    <div class="jqGrid_wrapper">
                        <table class="footable table table-stripped table-hover" data-page-size="8" data-filter=#filter>
                            <!--数据头 开始-->
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>音频标题</th>
                                <th>上传者</th>
                                <th>上传时间</th>
                                <th>播放</th>
                                <th>删除</th>
                            </tr>
                            </thead>
                            <!--数据头 结束-->
                            <!--数据体 开始-->
                            <tbody>
                            <c:forEach var="audio" items="${requestScope.audioList}">
                                <tr class="gradeX">
                                    <td class="audioId">${audio.audioId}</td>
                                    <td>${audio.audioTitle}</td>
                                    <td>${audio.businessInfoLegalPerson}</td>
                                    <td class="center">${audio.audioDate.toLocaleString()}</td>
                                    <td class="center"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#audioPlay" onclick="changeAudio('${audio.audioSrc}')">播放</button></td>
                                    <td class="center"><button type="button" class="btn btn-danger recoverAudio">删除</button></td>
                                    <td class="center"><button type="button" class="btn btn-danger deleteAudioPermanently">删除</button></td>
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
<div class="modal fade" id="audioPlay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="audioPlayTitle">音频</h4>
            </div>
            <div class="modal-body">
                <audio id="audioPlaySrc" src="" controls="controls"
                       width="300" height="500"></audio>
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
        // $('.footable').footable();
        // $('.footable2').footable();

        $(".recoverAudio").on({"click": function () {
                var trElement = $(this).parent().parent();
                var audioId = $(trElement).children(".audioId").html();
                $.ajax({
                    url: "${pageContext.request.contextPath}/VideoController/recoverAudio",
                    type: "POST",
                    data: "audioId=" + audioId,
                    success: function (msg) {
                        if (msg.result == true) {
                            alert("还原成功");
                            trElement.remove();
                        } else {
                            alert("还原失败");
                        }
                    }
                });
            }});
        $(".deleteAudioPermanently").on({"click": function () {
                var trElement = $(this).parent().parent();
                var audioId = $(trElement).children(".audioId").html();
                $.ajax({
                    url: "${pageContext.request.contextPath}/VideoController/deleteAudioPermanently",
                    type: "POST",
                    data: "audioId=" + audioId,
                    success: function (msg) {
                        if (msg.result == true) {
                            alert("彻底删除成功");
                            trElement.remove();
                        } else {
                            alert("彻底删除失败");
                        }
                    }
                });
            }});
    });
    function changeAudio(src) {
        $("#audioPlaySrc").attr("src", "${pageContext.request.contextPath}" + src);
    }
</script>

</body>

</html>
