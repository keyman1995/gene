function initCaseSelect() {
    var caseList = $("#caseEditIds").val();

    if(caseList!=""){

        var arr=new Array();
        arr=caseList.split(',');
        for(var i=0;i<arr.length;i++){
            $("#caseids option[value='"+arr[i]+"']").attr("selected",true);
        }
    }

    $('#caseids').chosen();
}
function initHzSelect() {
    var badgeList = $("#badgeEditIds").val();

    if(badgeList!=""){

        var arr=new Array();
        arr=badgeList.split(',');
        for(var i=0;i<arr.length;i++){
            $("#badgeIds option[value='"+arr[i]+"']").attr("selected",true);
        }
    }

    $('#badgeIds').chosen();
}


/*教练相册*/
function modalRU(uid){
    $('#coachId').val(uid);
    initImgDataTable();
    $("#imgTable").html("");
    $('#img-bind-modal').modal('open');
}

function addImg() {
    if($("#icon").val()==""){
        return false;
    }
    var url = "/teacherImg/save"
    var completeHandler = function (data) {
        $("#icon").val("");
        $('#iconImg').attr("src","");
        initImgDataTable();
    }
    var paramImg={teacher_id:$("#coachId").val(),img_url:$("#icon").val(),img_type:0};
    doPost(url,paramImg,completeHandler);
}


function initImgDataTable(){
    var pageSize = 100;
    var currentPage = 1
    var orderBy = "create_time";
    var sort = "desc";
    var param = {orderBy: orderBy, sort: sort, currentPage: currentPage, pageSize: pageSize,conditionList:"teacher_id@long@eq$"+$("#coachId").val()}
    doGet("/teacherImg/list",param,function (res) {
        $('#imgTable').html(template('tpl-coachImgList', res.data));
    })
}


function deleteImg(id) {
    var url = "/teacherImg/delete/"+id;
    var completeHandler = function (data) {
        initImgDataTable();
    }
    var paramImg={};
    doPost(url,paramImg,completeHandler);
}

/*教练相册结束*/


/*教练证书*/
function modalCER(uid){
    $('#coachIdCer').val(uid);
    initImgDataTableCER();
    $("#imgTableCer").html("");
    $('#cer-bind-modal').modal('open');
}

function addImgCER() {
    if($("#iconCer").val()==""){
        return false;
    }
    if($("#cerName").val()==""){
        return false;
    }
    var url = "/teacherCertificate/save"
    var completeHandler = function (data) {
        $("#cerName").val("");
        $("#iconCer").val("");
        $('#iconImgCer').attr("src","");
        initImgDataTableCER();
    }
    var paramImg={teacher_id:$("#coachIdCer").val(),img_url:$("#iconCer").val(),name:$("#cerName").val()};
    doPost(url,paramImg,completeHandler);
}


function initImgDataTableCER(){
    var pageSize = 100;
    var currentPage = 1
    var orderBy = "create_time";
    var sort = "desc";
    var param = {orderBy: orderBy, sort: sort, currentPage: currentPage, pageSize: pageSize,conditionList:"teacher_id@long@eq$"+$("#coachIdCer").val()}
    doGet("/teacherCertificate/list",param,function (res) {
        $('#imgTableCer').html(template('tpl-coachCerList', res.data));
    })
}


function deleteImgCER(id) {
    var url = "/teacherCertificate/delete/"+id;
    var completeHandler = function (data) {
        initImgDataTableCER();
    }
    var paramImg={};
    doPost(url,paramImg,completeHandler);
}


/*教练证书结束*/
