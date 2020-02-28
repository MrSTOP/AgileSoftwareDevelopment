<%--
  Created by IntelliJ IDEA.
  Author: 闫坤炜
  User: MrST
  Date: 2020/2/25
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加数据模板页面</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>编辑管理员信息</h2>
        <ol class="breadcrumb">
            <li>
                <a href="index.html">管理员模块</a>
            </li>
            <li>
                <strong>编辑管理员信息</strong>
            </li>
        </ol>
    </div>
    <div class="col-sm-8">
        <div class="title-action">
            <a href="add_date_model.html" class="btn btn-primary">活动区域</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form method="post" action="${pageContext.request.contextPath}/BusinessInfoController/updateBusinessInfo" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">登录名</label>

                            <div class="col-sm-10">
                                <input type="text" id="businessUsername" class="form-control" name="businessUsername">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商户名称</label>

                            <div class="col-sm-10">
                                <input type="text" id="businessInfoName" class="form-control" name="businessInfoName">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">法人名称</label>

                            <div class="col-sm-10">
                                <input type="text" id="businessInfoLegalPerson" class="form-control" name="businessInfoLegalPerson">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">法务电话</label>

                            <div class="col-sm-10">
                                <input type="text" id="businessInfoLegalPersonTel" class="form-control" name="businessInfoLegalPersonTel">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码</label>

                            <div class="col-sm-10">
                                <input type="password" id="businessPassword" class="form-control" name="businessPassword">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">提交修改</button>
                                <button class="btn btn-white" type="reset">清空</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>

<script type="text/javascript">
    $(function () {
        var msg = "${msg}"
        if (msg.length !== 0) {
            alert(msg);
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/BusinessInfoController/selectAllBusinessInfoWithPass",
            type: "POST",
            data: "",
            success: function (msg) {
                $("#businessUsername").val(msg.businessUsername);
                $("#businessInfoName").val(msg.businessInfoName);
                $("#businessInfoLegalPerson").val(msg.businessInfoLegalPerson);
                $("#businessInfoLegalPersonTel").val(msg.businessInfoLegalPersonTel);
                $("#businessPassword").val(msg.businessPassword);
            }
        });
    })
</script>


</body>

</html>

