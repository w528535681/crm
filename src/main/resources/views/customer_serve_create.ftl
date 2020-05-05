<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.serve.js"></script>
</head>
<body style="margin: 1px">

<div id="p" class="easyui-panel" title="客服信息创建" style="width:700px;height: 300px;padding: 10px">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>服务类型：</td>
                <td>
                    <select id="serveType" class="easyui-combobox" name="serveType" style="width:200px;" panelHeight="auto">
                        <option value="">请选择</option>
                        <option value="6">咨询</option>
                        <option value="7">建议</option>
                        <option value="8">投资</option>
                        <option value="9">维修</option>
                    </select>
                </td>
                <td></td>
                <td>客户</td>
                <td><input type="text" name="customer"  class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>概要：</td>
                <td><input type="text"  name="overview"/></td>
                <td></td>
                <td>服务请求</td>
                <td><input type="text" name="serviceRequest" /></td>
            </tr>
        </table>
        <tr>
            <td> <a href="javascript:saveCustomerServe()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a></td>
            <td></td>
            <td><a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">重置</a></td>
        </tr>
        </table>
    </form>

</div>
</body>