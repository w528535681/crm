function searchCustomerServeByParams() {
    $("#dg").datagrid("load",{
        customer:$("#s_customer").val(),
        type:$("#s_serveType").combobox("getValue")
    })
}


function openProceDialog() {
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择待处理的数据!","error");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","暂不支持批量处理!","error");
        return;
    }

    $("#fm").form("load",rows[0]);
    openDialog("dlg","服务处理");
}

function doProce() {
    $.ajax({
        type:"post",
        url:ctx+"/customer_serve/saveOrUpdateCustomerServe",
        data:{
            state:"fw_003",
            id:$("input[name='id']").val(),
            serviceProce:$("#serviceProce").val(),
            serviceProcePeople:$.cookie("trueName")
        },
        dataType:"json",
        success:function (data) {
            if(data.code==200){
                closeDialog("dlg");
                searchCustomerServeByParams();
            }
        }
    })
}
