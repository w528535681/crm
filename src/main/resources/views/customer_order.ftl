<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
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
            <th field="op" width="80" align="center" >操作</th>
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

</body>