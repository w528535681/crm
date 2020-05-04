$(function () {
    $("#dg").edatagrid({
        url:ctx+"/customer_rep/list?lossId="+$("#lossId").val(),
        saveUrl:ctx+"/customer_rep/save?lossId="+$("#lossId").val(),
        updateUrl:ctx+"/customer_rep/update",
        destroyUrl:ctx+"/customer_rep/delete"
    })
});


function saveCustomerRep() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("load");
}

function delCustomerRep() {
    $("#dg").edatagrid("destroyRow");
    $("#dg").edatagrid("load");
}

function confirmLoss() {
    $.messager.confirm("来自crm","确定流失该客户?",function (r01) {
        if(r01){
            $.messager.prompt("来自crm","请输入客户流失原因",function (r02) {
                if(r02){
                    alert(r02);
                }
            })
        }
    })
}