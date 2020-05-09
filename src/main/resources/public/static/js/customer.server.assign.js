function searchCustomerServeByParams() {
    $("#dg").datagrid("load",{
        customer:$("#s_customer").val(),
        type:$("#s_serveType").combobox("getValue")
    })
}

function formatterType(value) {
    if (value == 6){
        return "咨询";
    }else if (value==7){
        return  "建议";
    }else if (value==8){
        return "投资";
    }else if (value==9){
        return "维修";
    }else{
        return "未知";
    }
}


function openAssignDialog() {
    var rows=$("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择待分配的数据!","error");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","暂不支持批量分配!","error");
        return;
    }

    $("#fm").form("load",rows[0]);
    openDialog("dlg","服务分配");
}

function addAssign() {
    $.ajax({
        type:"post",
        url:ctx+"/customer_serve/saveOrUpdateCustomerServe",
        data:{
            assigner:$("#s_assigner").combobox("getValue"),
            state:"fw_002",
            id:$("input[name='id']").val()
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
