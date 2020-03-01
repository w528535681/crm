function searchRoles() {
    $("#dg").datagrid("load",{
        roleName:$("#s_roleName").val()
    })
}

function openRoleAddDialog() {
    clearFormData();
    openDialog("dlg","角色添加");
}

function closeRoleDialog() {
    closeDialog("dlg")
}

function openRoleModifyDialog() {
    openModifyDialog("dg","fm","dlg","角色更新");
}


function clearFormData() {
    $("#roleName").val("");
    $("#roleRemark").val("");
    $("input[name='id']").val("");
}

function saveOrUpdateRole() {
    saveOrUpdateRecode(ctx+"/role/save",ctx+"/role/update","dlg",searchRoles,clearFormData);
}

function deleteRole() {
    deleteRecode("dg",ctx+"/role/delete",searchRoles);
}



function openAddModuleDialog() {
    $.ajax({
        type:"post",
        url:ctx+"/module/queryAllModules",
        dataType:"json",
        success:function (data) {
            var zTreeObj;
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                check: {
                    enable: true
                }
            };
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
            openDialog("module","授权");
        }
    })
}