
function formatterState(val) {
    if(val==0){
        return "暂缓流失";
    }else if(val==1){
        return "确认流失";
    }else{
        return "状态未知"
    }
}

function searchCustomerLoss() {
    $("#dg").datagrid("load",{
        cusNo:$("#s_cusNo").val(),
        cusName:$("#s_cusName").val(),
        state:$("#s_state").combobox("getValue")
    })
}