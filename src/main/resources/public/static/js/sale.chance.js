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
    //默认访问后台的路径
    var url = ctx+"/sale_chance/save";

   if (!(isEmpty( $("input[name='id']").val()))){
       url = ctx+"/sale_chance/update";
   }
    $("#fm").form("submit",{
        url:url,
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

function openSaleChanceModifyDialog() {

    //获取更新的选中记录数
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length==0){
        $.messager.alert("来自crm","请选择待修改的机会数据!","error");
        return;
    }
    if (rows.length>1){
        $.messager.alert("来自crm","暂不支持批量修改!","error");
        return;
    }
    //修改数据填充表格
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","机会数据更新");
}

function deleteSaleChance() {

    var rows = $("#dg").datagrid("getSelections");
    if (rows.length==0){
        $.messager.alert("来自crm","请选择要修改的机会数据","error");
        return;
    }

    $.messager.confirm("来自crm","确定删除选中的记录?",function (r) {
        if (r){
            var ids = "ids=";
            for (var i = 0;i<rows.length;i++){
                if (i<rows.length-1){
                    ids = ids+rows[i].id+"$ids=";
                }else{
                    ids = ids +rows[i].id;
                }
            }
            $.ajax({
                type:"post",
                url:ctx+"/sale_chance/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if (data.code==200){
                        searchSaleChance();
                    }else {
                     $.messager.alert("来自crm",data.msg,"error");
                    }
                }
            })
        }
    })
    
}

/**
 * EasyUi前台表单校验手机号码的合法性
 */
$.extend($.fn.validatebox.defaults.rules, {
    mobilePhone: {
        validator: function (value, param) {
            return /^1[3-8]+\d{9}$/.test(value);
        },
        message: '请输入正确的手机号码。'
    }
});