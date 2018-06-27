

/*鑰匙列表*/
function modalKey(uid){
    $('#keyId').val(uid);
    initKeyTable();
    $('#keyTable').html('');
    $('#tpl-lockerkey').modal('open');
}
function modalBind(uid){
    $('#bindId').val(uid);
    $('#tpl-lockerkeyBind').modal('open');
}

function showQrInfo(keyId){
    // 二维码组成字段           1类型 ，2 ID 3 二维码所属人ID 4 数量

    //1003,19,1,5  商品

    //1001,1,1,1  用户
    var qrInfo =  $('#qrInfo').val();
    if(qrInfo == null  || qrInfo == ''){
        alert('请扫二维码！');
        return;
    }
    if(qrInfo.indexOf('-')!= -1){
        var keyId = $('#bindId').val();
        var arr = new Array();
        arr = qrInfo.split("-");
        var type = arr[0];    //1001 用户 1002 订单  1003 商品 1005课程     1007 VIP卡
        var id = arr[1];
        var belong_id = arr[2];
        var num = arr[3];
        modalKeyBind(keyId,type,id,num,belong_id);
    }else {
        alert('警告','二维码格式错误！','确定')
        return;
    }






}
function modalKeyBind(keyId,type,id,num,belong_id) {
    /*$("#userInfo").hide();
    //1001 用户 1002 订单  1003 商品 1005课程     1007 VIP卡
    $("#userInfo").show();*/

    var url = "/lockerkey/bind";
    var completeHandler = function (data) {
        if(data.msg != null && data.msg != ''){
            alert(data.msg)
        }
        $('#tpl-lockerkeyBind').modal('close');
        $('#tpl-lockerkey').modal('close');

    }
    var paramImg={id:keyId,type:type,userId:belong_id,qrInfo:qrInfo};
    doPost(url,paramImg,completeHandler);

}

function addKey() {
    debugger;
    var keyId = $('#keyId').val();
        var startNum = $("#startNum").val();
        var endNum = $("#endNum").val();
        var html = '';
    if(startNum == ""){
        alert("请输入开头柜号！");
        return false;
    }else if(endNum == ""){
        alert("请输入末尾柜号!");
        return false;
    }else if(startNum>endNum){
        alert("开头柜号不能比结尾柜号大！");
        return false;
    }else if(startNum<0){
        alert("柜号不能小于0！");
        return false;
    }else if(endNum<0){
        alert("柜号不能小于0！");

    }




    var url = "/lockerkey/addkeys";
    var param = {case_field_id:keyId,status:1,startNum:startNum,endNum:endNum};
    var completeHandler = function (result) {
        $("#endNum").val("");
        $("#startNum").val("");
        initKeyTable();
        alert(result.msg);
    };
    var html = '<span style="color: red; float: right; font-size: large; font: -apple-system-tall-body" >柜号重复了！</span>';
    doPost(url,param,completeHandler);

}


function initKeyTable(){
    var pageSize = 100;
    var currentPage = 1;
    var orderBy = "lock_num";
    var sort = "asc";
    var param = {orderBy: orderBy, sort: sort, currentPage: currentPage, pageSize: pageSize,conditionList:"case_field_id@string@eq$"+$("#keyId").val()}
    doGet("/lockerkey/list",param,function (res) {
        $('#keyTable').html(template('tpl-lockerkeys', res.data));

    })
}


function deleteKey(id) {

    $(".confirm-modal-title").html("警告");
    $(".confirm-modal-message").html("你，确定要删除这条记录吗？");
    $(".confirm-modal-btn-cancel").html("取消");
    $(".confirm-modal-btn-confirm").html("确定");
    $('#confirm-modal').modal({
        relatedTarget: this,
        onConfirm: function(){
            var url = "/lockerkey/delete/"+id;
            var completeHandler = function (data) {
                initKeyTable();
            }
            var paramImg={};
            doPost(url,paramImg,completeHandler);
        },
        onCancel: function(){}
    });




}
function unbindKey(id) {
    var url = "/lockerkey/unbind/"+id;
    var completeHandler = function (data) {
        $('#tpl-lockerkeyBind').modal('close');
        $('#tpl-lockerkey').modal('close');
        modalKey(id);
    }
    var paramImg={};
    doPost(url,paramImg,completeHandler);
}

/*教练相册结束*/


