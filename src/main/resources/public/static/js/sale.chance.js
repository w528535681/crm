/**
 * 格式化营销数据展示列表的分配状态
 * @param value
 * @returns {string}
 */
function formatterState(value) {
    /**
     *  0-未分配
     *  1-已分配
     */
    if (value == 0){
        return "未分配";
    }else if (value==1){
        return  "已分配";
    }else {
        return "未知";
    }
}

/**
 *  格式化营销数据展示列表的开发状态
 * @param value
 * @returns {string}
 */
function formatterDevResult(value) {

    /**
     * 0-未开发
     * 1-开发中
     * 2-开发成功
     * 3-开发失败
     */
    if (value==0){
        return "未开发";
    }else if (value==1){
        return "开发中";
    }else if (value==2){
        return "开发成功";
    }else if (value==3){
        return "开发失败"
    }else {
        return "未知";
    }
}

/**
 * 查询营销数据
 */
function searchSaleChance() {

    var customerName = $("#s_customerName").val();
    var createMan = $("#s_createMan").val();
    var state = $("#s_state").combobox("getValue");
    $("#dg").datagrid("load",{
        customerName:customerName,
        createMan:createMan,
        state:state
    })
}

/**
 * 打开添加或者更新营销数据的对话框
 */
function openSaleChanceAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","机会数据添加");
}


/**
 * 关闭添加或者更新营销数据的对话框
 */
function closeSaleChanceDialog() {
    $("#dlg").dialog("close");
}

/**
 * 清空表单
 */
function clearFormData() {
    $("#customerName").val("");
    $("#chanceSource").val("");
    $("#linkMan").val("");
    $("#linkPhone").val("");
    $("#cgjl").val("");
    $("#overview").val("");
    $("#description").val("");
    $("#assignMan").combobox("setValue","");
}

/**
 * 添加或者更新营销机会数据
 */
function saveOrUpdateSaleChance() {
    $("#fm").form("submit",{
        url:ctx+"/sale_chance/save",
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data = JSON.parse(data);
            if (data.code==200){
                closeSaleChanceDialog();
                searchSaleChance();
                clearFormData();
            }
        }
    })
}