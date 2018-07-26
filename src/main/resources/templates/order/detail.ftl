<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-3 column">
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>订单编号</th>
                        <th>订单总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="success">
                        <td>${orderDTO.getOrderId()}</td>
                        <td>${orderDTO.orderAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="col-md-12 column">
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>总额</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list orderDTO.getOrderDetailList() as detail>
                            <tr class="info">
                                <td>${detail.getOrderId()}</td>
                                <td>${detail.getProductName()}</td>
                                <td>${detail.getProductPrice()}</td>
                                <td>${detail.getProductQuantity()}</td>
                                <td>${detail.getProductQuantity()*detail.getProductPrice()}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>

            <div class="col-md-12 column">
                <#if orderDTO.getOderStatusEnum().code == 0 >
                    <a href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}&returnUrl=${retUrl}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}&returnUrl=${retUrl}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
            </div>
        </div>
    </div>
    </div>
</div>
</body>
</html>