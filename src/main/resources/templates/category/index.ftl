<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.getCategoryName())!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" type="number" class="form-control" value="${(category.getCategoryType())!''}"/>
                        </div>
                        <input hidden type="number" name="categoryId" value="${(category.getCategoryId())!''}">
                        <#--<input hidden type="date" name="createTime" value="${(category.createTime)!''}">-->
                        <#--TODO 表单传递日期格式有问题-->
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>