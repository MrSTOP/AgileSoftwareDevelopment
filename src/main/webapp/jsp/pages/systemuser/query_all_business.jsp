<%--
  Created by IntelliJ IDEA.
  Author: 闫坤炜
  User: MrST
  Date: 2020/2/24
  Time: 17:24
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

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/footable/footable.core.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>商户列表</h5>
                </div>
                <div class="ibox-content">

                    <!--数据表顶部查询 开始
                        该区域可以将来存放 各种控件
                    -->

                    <hr>
                    <!--数据表顶部查询 结束-->
                    <!--数据表开始-->
                    <div class="jqGrid_wrapper">
                        <table class="footable table table-stripped table-hover" data-page-size="8" data-filter=#filter>
                            <!--数据头 开始-->
                            <thead>
                            <tr>
                                <th>商户ID</th>
                                <th>商户名称</th>
                                <th>法人名称</th>
                                <th>法务电话</th>
                            </tr>
                            </thead>
                            <!--数据头 结束-->
                            <!--数据体 开始-->
                            <tbody>
                            <c:forEach var="businessInfo" items="${requestScope.businessInfoList}">
                                <tr class="gradeX" >
                                    <td class="businessId">${businessInfo.businessId}</td>
                                    <td class="Name">${businessInfo.businessInfoName}</td>
                                    <td class="LegalPerson">${businessInfo.businessInfoLegalPerson}</td>
                                    <td class="Tel">${businessInfo.businessInfoLegalPersonTel}</td>

                                    <td>
                                        <button class="btn btn-primary" type="button" name = "goVideo" >查看该用户上传视频</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-white" type="button" name = "goAudio"> 查看该用户上传音频</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-primary" type="button" name = "banBusiness" ${businessInfo.businessIsfreeze?"disabled='disabled'":""}> 冻结账号
                                        <span class="otherbusinessId" style="display: none">${businessInfo.businessId}</span>
                                        </button>
                                    </td>
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

<!-- 全局js -->

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/js/plugins/footable/footable.all.min.js"></script>


<!--
    &lt;!&ndash; Peity &ndash;&gt;
    <script src="../js/plugins/peity/jquery.peity.min.js"></script>
    &lt;!&ndash; jqGrid &ndash;&gt;
    <script src="../js/plugins/jqgrid/i18n/grid.locale-cn.js?0820"></script>
    <script src="../js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>
-->


<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $('[name = "banBusiness"]').click(function () {
            console.log("nihao");
            var btn = $(this);
            var businessId = $(this).find(".otherbusinessId").html();
            var businessIsfreeze = true;
            console.log($(this).html());

            console.log(businessId);
            console.log(businessIsfreeze);

            $.ajax({
                url: "${pageContext.request.contextPath}/BusinessController/freezeBusiness",
                type: "POST",
                data: "businessId="+businessId+"&businessIsfreeze="+businessIsfreeze,
                success: function (msg) {
                    console.log(msg.result);
                    if (msg.result == true) {
                        console.log("ban");

                        btn.attr("disabled","disabled");
                    }
                    alert(msg.msg);
                }
            });
        });

        $('[name = "goVideo"]').click()(function () {
            var businessId = $(this).find(".otherbusinessId").html();
            window.location.href = "${pageContext.request.contextPath}/VideoController/queryAudioById?business="+businessId;
        })

        $('[name = "goAudio"]').click()(function () {
            var businessId = $(this).find(".otherbusinessId").html();
            window.location.href = "${pageContext.request.contextPath}/VideoController/queryVideoById?business="+businessId;
        })
    });


</script>
<script>
    $(document).ready(function () {
        $('.footable').footable();
        $('.footable2').footable();
    });

</script>

</body>

</html>

