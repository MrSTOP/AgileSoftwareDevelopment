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
                    <form method="post" action="${pageContext.request.contextPath}/SystemUserAllInfoController/updateSystemUserInfo" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">登录名</label>

                            <div class="col-sm-10">
                                <input type="text" id="userName" class="form-control" name="userName">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">真实姓名</label>

                            <div class="col-sm-10">
                                <input type="text" id="userInfoTrueName" class="form-control" name="userInfoTrueName">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">联系方式</label>

                            <div class="col-sm-10">
                                <input type="text" id="userInfoTel" class="form-control" name="userInfoTel">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码</label>

                            <div class="col-sm-10">
                                <input type="password" id="loginUserPassword" class="form-control" name="loginUserPassword">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>

                            <div class="col-sm-10">
                                <div class="radio">
                                    <label>
                                        <input type="radio" checked="" value="男" id="userSexBoy" name="userInfoSex">男</label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio" value="女" id="userSexGirl" name="userInfoSex">女</label>
                                </div>
                            </div>
                        </div>
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
            url: "${pageContext.request.contextPath}/SystemUserAllInfoController/getSystemUserAllInfoByUid",
            type: "POST",
            data: "",
            success: function (msg) {
                $("#userName").val(msg.userName);
                $("#userInfoTrueName").val(msg.userInfoTrueName);
                $("#userInfoTel").val(msg.userInfoTel);
                $("#loginUserPassword").val(msg.loginUserPassword);
                if (msg.userInfoSex === "男") {
                    $("#userSexBoy").attr("checked", "checked");
                } else if (msg.userInfoSex === "女") {
                    $("#userSexGirl").attr("checked", "checked");
                }
            }
        });
    })
</script>


</body>

</html>

