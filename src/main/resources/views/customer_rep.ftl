<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.rep.js"></script>
</head>
<body style="margin: 1px">
<div id="p" class="easyui-panel" title="客户信息" style="width:700px;height: 200px;padding: 10px">
    <input type="hidden" id="lossId" value="${customerLoss.id}"/>
    <table cellspacing="8px">
        <tr>
            <td>客户名称：</td>
            <td><input type="text" readonly="readonly" value="${(customerLoss.cusName)!}"/></td>
            <td></td>
            <td>客户编号</td>
            <td><input type="text" readonly="readonly" value="${(customerLoss.cusNo)!}"/></td>
        </tr>
        <tr>
            <td>客户经理：</td>
            <td><input type="text" readonly="readonly" value="${(customerLoss.cusManager)!}"/></td>
            <#if (customerLoss.confirmLossTime)??>
                <td></td>
                <td>最后下单时间</td>
                <td>
                    <input type="text" readonly="readonly" value="${(customerLoss.confirmLossTime)?string("yyyy-MM-dd HH:mm:ss")}"/>
                </td>
            </#if>
        </tr>
        <tr>
            <td>流失状态：</td>
            <td>
                 <#if customerLoss.state==0>
                      <#assign state="暂缓流失">
                     <#else>
                         <#assign state="确认流失">
                 </#if>
                <input type="text" readonly="readonly" value="${state}"/>
            </td>
            <td></td>
            <td>流失原因</td>
            <td><input type="text" readonly="readonly" value="${(customerLoss.lossReason)!}"/></td>
        </tr>
    </table>
</div>
<br/>


<table id="dg" title="暂缓措施列表" class="easyui-edatagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       toolbar="#toolbar" style="width:750px;height: auto;" singleSelect="true">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="measure" width="100" align="center" editor="{type:'validatebox',options:{required:true}}">暂缓措施</th>
        <th field="createDate" width="80" align="center">创建时间</th>
        <th field="updateDate" width="80" align="center">更新时间</th>
    </tr>
    </thead>
</table>



<div id="toolbar">
    <a href="javascript:$('#dg').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加暂缓</a>
    <a href="javascript:delCustomerRep()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除暂缓</a>
    <a href="javascript:saveCustomerRep()" class="easyui-linkbutton" iconCls="icon-save" plain="true" >保存暂缓</a>
    <a href="javascript:$('#dg').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true" >撤销行</a>
    <a href="javascript:confirmLoss()" class="easyui-linkbutton" iconCls="icon-kfcg" plain="true" >确认流失</a>
    <a href="javascript:updateSaleChanceDevResult(3)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" >终止流失</a>
</div>




</body>