
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

function formatterOp(val,rowData) {

    var state = rowData.state;
    var title = rowData.cusName+"_暂缓措施";
    var href ='javascript:openCustomerRepTab("'+title+'","'+rowData.cusNo+'")';
    if (state==0){
        return "<a href='"+href+"'>添加暂缓</a>";
    }
    if(state==1){
        return "<a href='"+href+"'>查看详情</a>";
    }
}

function openCustomerRepTab(title,cusNo) {
    window.parent.openTab(title,ctx+"/customer_rep/index?cusNo="+cusNo);
}