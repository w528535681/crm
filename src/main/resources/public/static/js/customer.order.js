function formatterState(value) {
    if(value==0){
        return "未支付";
    }else if(value==1){
        return  "已支付";
    }else{
        return "未知"
    }
}


function searchOrders() {
    $("#dg").datagrid("load",{
        orderNo:$("#s_orderNo").val(),
        state:$("#s_state").combobox("getValue")
    })
}