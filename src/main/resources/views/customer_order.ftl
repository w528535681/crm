<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.order.js"></script>
</head>
<body style="margin: 1px">

<div id="p" class="easyui-panel" title="客户信息" style="width:700px;height: 200px;padding: 10px">
    <table cellspacing="8px">
        <tr>
            <td>客户名称：</td>
            <td><input type="text" readonly="readonly" value="${(customer.name)!}"/></td>
            <td></td>
            <td>法人</td>
            <td><input type="text" readonly="readonly" value="${(customer.fr)!}"/></td>
        </tr>
        <tr>
            <td>客户地址：</td>
            <td><input type="text" readonly="readonly" value="${(customer.address)!}"/></td>
            <td></td>
            <td>客户联系电话</td>
            <td><input type="text" readonly="readonly" value="${(customer.phone)!}"/></td>
        </tr>
    </table>
</div>
<br/>


<table id="dg" title="客户订单展示" class="easyui-datagrid"   fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/order/list?cid=${customer.id}"  toolbar="#tb" style="width:750px;height: auto;">
    <thead>
        <tr>
            <th field="cb" checkbox="true" align="center"></th>
            <th field="id" width="50" align="center">编号</th>
            <th field="orderNo" width="100" align="center">订单编号</th>
            <th field="address" width="50" align="center">物流地址</th>
            <th field="state" width="50" align="center" formatter="formatterState">支付状态</th>
            <th field="orderDate" width="80" align="center">下单时间</th>
            <th field="createDate" width="80" align="center">创建时间</th>
            <th field="updateDate" width="80" align="center">更新时间</th>
            <th field="op" width="80" align="center" formatter="formatterOp" >操作</th>
        </tr>
    </thead>
</table>

<div id="tb">
    订单编号： <input type="text" id="s_orderNo" size="20" onkeydown="if(event.keyCode==13) searchOrders()"/>
    支付状态： <select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto">
        <option value="">请选择...</option>
        <option value="0">未支付</option>
        <option value="1">已支付</option>
    </select>
    <a href="javascript:searchOrders()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:600px;height:400px"
     closed="true">
    <div id="p" class="easyui-panel" title="订单信息" style="width:550px;height: auto">
        <form id="fm" method="post">
            <table cellspacing="8px">
                <tr>
                    <td>订单编号：</td>
                    <td><input type="text" name="order_no" readonly="readonly"/></td>
                    <td></td>
                    <td>支付状态</td>
                    <td><input type="text" name="status" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>物流地址：</td>
                    <td><input type="text" name="address" readonly="readonly"/></td>
                    <td></td>
                    <td>订单总金额</td>
                    <td><input type="text" name="total" readonly="readonly"/></td>
                </tr>
            </table>
        </form>
    </div>

    <br/>

    <table id="dg02" title="商品列表" class="easyui-datagrid"
           fitColumns="true" pagination="true" rownumbers="true"
           url="${ctx}/order_details/list" toolbar="#tb02" style="width:550px;height: auto;" >
        <thead>
        <tr>
            <th field="cb" checkbox="true" align="center"></th>
            <th field="id" width="50" align="center">编号</th>
            <th field="orderId" width="100" align="center">订单id</th>
            <th field="goodsName" width="50" align="center">商品名称</th>
            <th field="goodsNum" width="50" align="center" >商品数量</th>
            <th field="unit" width="80" align="center">商品单位</th>
            <th field="price" width="80" align="center">商品单价(￥)</th>
            <th field="sum" width="80" align="center">商品总金额(￥)</th>
            <th field="createDate" width="80" align="center">创建时间</th>
            <th field="updateDate" width="80" align="center">更新时间</th>
        </tr>
        </thead>
    </table>

</div>

<div id="tb02">
    商品名： <input type="text" id="s_goodsName" size="20"
                onkeydown="if(event.keyCode==13) searchOrderDetails()"/>
    <br/>
    商品价格(￥):<select id="s_price" class="easyui-combobox" name="s_price" style="width:200px;" panelHeight="auto">
        <option value="">请选择</option>
        <option value="1">0-100</option>
        <option value="2">100-500</option>
        <option value="3">500-1000</option>
        <option value="4">1000-5000</option>
        <option value="5">5000以上</option>
    </select>
    <a href="javascript:searchOrderDetails()" class="easyui-linkbutton" iconCls="icon-search"
       plain="true">搜索</a>
</div>

</body>