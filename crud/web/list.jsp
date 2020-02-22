<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<script>
    function deleteUser(id) {
        if(confirm("您确定要删除吗？")){
            location.href ="${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
        }
    };

    $(function () {
        var firsttn = $("#first");
        firsttn.click(function () {
            var ids = $("#fom_1 input[name ='ids']");
            // ids.checked = $(this).checked;
            for(var i=0;i<ids.length;i++) {
                ids[i].checked = this.checked;
            }
        })
        // firsttn.click= function () {
        //     var ids = document.getElementsByName("ids");
        //     for(var i=0;i<ids.length;i++){
        //         ids[i].checked = this.checked;
        //     }
        //
        // }

        // document.getElementById("first").onclick = function(){
        //     //2.获取下边列表中所有的cb
        //     var cbs = document.getElementsByName("ids");
        //     //3.遍历
        //     for (var i = 0; i < cbs.length; i++) {
        //         //4.设置这些cbs[i]的checked状态 = firstCb.checked
        //         cbs[i].checked = this.checked;
        //     }
        // };
        
        $("#delbtn").click(function () {
            var cbs = document.getElementsByName("ids");
            var count = 0;
            for (var i =0;i<cbs.length;i++){
                if(cbs[i].checked){
                    count ++;
                }

            }
            if(count <1){
                alert("未选择数据")
            }else{
               if( confirm("确定要删除选中的"+ count +"条数据吗？")){
                   $("#fom_1").submit();
               }
            }
            
        })
        
    })


</script>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/pageListServlet?">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" name="name" value="${condition.name[0]}">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3" name="address" value="${condition.address[0]}">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" class="form-control" id="exampleInputEmail2" name="email" value="${condition.email[0]}" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0)" id="delbtn">删除选中</a>

    </div>
    <form action="${pageContext.request.contextPath}/deleteSelectedServlet" id="fom_1">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="first"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${users.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="ids" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id})">删除</a></td>
                </tr>

            </c:forEach>

        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${users.currentPage ==1}">
                <li class="disabled" style="display: none">
                </c:if>
                <c:if test="${users.currentPage !=1}">
                <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/pageListServlet?currentPage=${users.currentPage-1}&row=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${users.totalPage}" var= "i">
                    <c:if test="${users.currentPage == i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/pageListServlet?currentPage=${i}&row=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${users.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/pageListServlet?currentPage=${i}&row=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${users.currentPage ==users.totalPage}">
                <li class="disabled" style="display: none">
                </c:if>
                <c:if test="${users.currentPage !=users.totalPage}">
                <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/pageListServlet?currentPage=${users.currentPage +1}&row=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${users.totalCount}条记录，共${users.totalPage}页
                </span>

            </ul>
        </nav>


    </div>


</div>


</body>
</html>
