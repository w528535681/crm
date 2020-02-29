function searchUsers() {
    $("#dg").datagrid("load",{
        userName:$("#s_userName").val(),
        trueName:$("#s_trueName").val(),
        phone:$("#s_phone").val()
    })
}


function openUserAddDialog() {
    clearFormData();
    openDialog("dlg","用户添加");
}

function closeUserDialog() {
    closeDialog("dlg");
}

function clearFormData() {
    $("#userName").val("");
    $("#email").val("");
    $("#trueName").val("");
    $("#phone").val("");
    $("input[name='id']").val("");
}




function saveOrUpdateUser(){
    saveOrUpdateRecode(ctx+"/user/save",ctx+"/user/update","dlg",searchUsers,clearFormData);
}

function openUserModifyDialog(){
    openModifyDialog("dg","fm","dlg","用户更新");
}

function deleteUser(){
    deleteRecode("dg",ctx+"/user/delete",searchUsers);
}


