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
                    <table class="ttable table-condensed table-bordered">
                        <thead>
                        <tr>
                            <th>
                                订单号
                            </th>
                            <th>
                                买家名称
                            </th>
                            <th>
                                买家地址
                            </th>
                            <th>
                                买家openid
                            </th>
                            <th>
                                买家手机
                            </th>

                            <th>订单金额</th>

                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>
                                订单创建时间
                            </th>
                            <th>订单更新时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDIOPage.content as orderDTO>
                        <tr>
                            <td>
                                ${orderDTO.orderId}
                            </td>
                            <td>
                                ${orderDTO.buyerName}
                            </td>
                            <td>
                                ${orderDTO.buyerAddress}
                            </td>
                            <td>
                                ${orderDTO.buyerOpenid}
                            </td>
                            <td>
                                ${orderDTO.buyerPhone}
                            </td>

                            <td>
                                ${orderDTO.orderAmount}
                            </td>

                            <td>
                                ${orderDTO.getOderStatusEnum().message}
                            </td>
                            <td>
                                ${orderDTO.getPayStatusEnum().message}
                            </td>
                            <td>
                                ${orderDTO.createTime}
                            </td>
                            <td>
                                ${orderDTO.updateTime}
                            </td>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}&returnUrl=${retUrl}">详情</a>
                            </td>
                            <td>
                                    <#if orderDTO.getOderStatusEnum().code != 2>
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}&returnUrl=${retUrl}">取消</a>
                                    <#else>
                                        <a href="#"></a>
                                    </#if>

                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>
                </div>
            </div>

        <#--分页-->
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disabled"> <a href="#">上一页</a>  </li>
                <#else>
                    <li> <a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>  </li>
                </#if>
                <#list 1..orderDIOPage.getTotalPages() as index>
                    <#if currentPage == index>
                        <li class="disabled"><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a> </li>
                    <#else>
                        <li> <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                <li>
                    <#if currentPage == orderDIOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a>
                    </#if>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://tangah.nat300.top/sell/websocket');
    }else{
        alert('该浏览器不支持websocket');
    }

    websocket.onopen = function (ev) {
        console.log('连接关闭');
    }

    websocket.onclose = function (ev) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (ev) {
        console.log('收到消息' + ev.data);
        //弹窗提示
        $('#myModal').modal('show');
        document.getElementById('notice').play();

    }
</script>

</body>
</html>