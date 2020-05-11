function searchByParams() {
    $("#dg").datagrid("load",{
        cusName:$("#s_cusName").val(),
        type:$("#s_type").combobox("getValue"),
        time:$("#s_time").combobox("getValue")
    })
}