<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.contri.js"></script>
</head>
<body style="margin: 1px">
<table id="dg" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/customer/queryCustomerContributionByParams" fit="true" toolbar="#tb" singleSelect="true">
    <thead>
    <tr>
        <th field="name" width="200" align="center">客户名称</th>
        <th field="total" width="200" align="center">贡献金额</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">导出</a>
    </div>
    <div>
        客户名称： <input type="text" id="s_cusName" size="20" onkeydown="if(event.keyCode==13) searchByParams()"/>
        金额区间：<select id="s_type" class="easyui-combobox" name="s_type" style="width:200px;" panelHeight="auto" editable="false">
            <option value="">请选择</option>
            <option value="1">0-1000</option>
            <option value="2">1000-3000</option>
            <option value="3">3000-5000</option>
            <option value="4">5000以上</option>
        </select>
        订单时间： <input type="text" class="easyui-datebox" id="s_time"  editable="false"/>
        <a href="javascript:searchByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
</body>
</html>