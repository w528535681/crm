function searchCustomersByParams() {
    $("#dg").datagrid("load",{
        cusName:$("#name").val(),
        cusNo:$("#khno").val(),
        myd:$("#myd").combobox("getValue"),
        level:$("#level").combobox("getValue")
    })
}

function openCustomerAddDialog() {
    openDialog("dlg","客户添加")
}

function closeCustomerDialog() {
    closeDialog("dlg")
}


function clearFormData() {

}

function saveOrUpdateCustomer() {
    saveOrUpdateRecode(ctx+"/customer/save",ctx+"/customer/update","dlg",searchCustomersByParams,clearFormData)
}



function deleteCustomer() {
    deleteRecode("dg",ctx+"/customer/delete",searchCustomersByParams);
}


function openCustomerModifyDialog() {
    openModifyDialog("dg","fm","dlg","客户修改")
}
function openShowOrderTab() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0){
        $.messager.alert("来自crm","请选择待查看的客户记录!","error");
        return;
    }
    if (rows>1){
        $.messager.alert("来自crm","暂不支持批量查看!","error");
        return;
    }
    window.parent.openTab(rows[0].name+"_订单展示",ctx+"/customer/order_info?cid="+rows[0].id);
}