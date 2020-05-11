<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.server.feed.back.js"></script>
</head>
<body style="margin: 1px">


<table id="dg" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/customer_serve/list?state=fw_005" fit="true" toolbar="#tb" singleSelect="true" singleSelect="true">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="customer" width="200" align="center">客户名</th>
        <th field="serveType" width="50" align="center">服务类型</th>
        <th field="overview" width="50" align="center">概要信息</th>
        <th field="serviceRequest" width="50" align="center">请求内容</th>
        <th field="assignTime" width="50" align="center">分配时间</th>
        <th field="createPeople" width="50" align="center">创建人</th>
        <th field="serviceProcePeople" width="50" align="center">处理人</th>
        <th field="serviceProce" width="50" align="center">处理内容</th>
        <th field="serviceProceTime" width="50" align="center">处理时间</th>
        <th field="serviceProceResult" width="50" align="center">反馈结果</th>
        <th field="myd" width="50" align="center">满意度</th>
        <th field="createDate" width="200" align="center">创建时间</th>
        <th field="updateDate" width="200" align="center">更新时间</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        客户：<input type="text" id="s_customer" size="20"
                  onkeydown="if(event.keyCode==13) searchCustomerServeByParams()"/>
        服务类型:
        <select id="s_serveType" class="easyui-combobox" name="serveType" style="width:200px;" panelHeight="auto">
            <option value="">请选择</option>
            <option value="6">咨询</option>
            <option value="7">建议</option>
            <option value="8">投资</option>
            <option value="9">维修</option>
        </select>
        <a href="javascript:searchCustomerServeByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
</body>
</html>