
/*课程详情*/
function modalCourse(uid){
    $('#courseid').val(uid);
    $("#imgTable").html("");
     initImgDataTable();
    $('#data-bind-modal').modal('open');
}

function addDetail() {
    $("#sortInfo").hide();
    if($("#img_url").val()==""){
        return false;
    }
    var reg =  /^[0-9]+$/;
    if(!reg.test($("#sort").val()) || $("#sort").val()==""){
        $("#sortInfo").show();
        return false;
    }
    $("#confirmEdit").hide();
    $("#imgInfo").html('上传图片');
    $("#upType").val("1");
    var url = "/coursedetail/save";
    var completeHandler = function () {
        $("#img_url").val("");
        $('#iconImg').attr("src","");
        $("#sortImg").hide();
        initImgDataTable();
    }
    var paramImg={courseid:$("#courseid").val(),img_url:$("#img_url").val(),sort:$("#sort").val(),id: $("#imgId").val()};
    doPost(url,paramImg,completeHandler);
}

function deleteImg(target) {
   var url = $(target).data('url');
    var completeHandler = function () {
        initImgDataTable();
    }
    var paramImg={};
    doPost(url,paramImg,completeHandler);
}


function editImg(id) {
    var $ctx = $('title').data('ctx');
    var url = $ctx+"/coursedetail/editDetail?id="+id;
    $.ajax({
        url:url,
        type:'post',
        dataType:'json',
        success:function (data) {
           if(data){
               $("#img_url").val(data.img_url);
               $('#iconImg').attr("src",data.img_url);
               $("#sortImg").show();
               $("#confirmEdit").show();
               $("#imgInfo").html('修改图片');
               $("#upType").val("0");
               $("#sort").val(data.sort);
               $("#imgId").val(data.id);
           }
        }
    })
}

function initImgDataTable(){
    var pageSize = 100;
    var currentPage = 1
    var orderBy = "sort";
    var sort = "asc";
    var param = {orderBy: orderBy, sort: sort, currentPage: currentPage, pageSize: pageSize,conditionList:"courseid@long@eq$"+$("#courseid").val()}
    doGet("/coursedetail/list",param,function (res) {
        $('#imgTable').html(template('tpl-courseDetail', res.data));
    })
}

function addSubCourse() {
    openNewTab();
}




