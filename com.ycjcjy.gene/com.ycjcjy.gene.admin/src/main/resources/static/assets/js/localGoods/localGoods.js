/*商品销售*/
function modalGoodsInfo(type,id,num,belong_id) {
    $("#userInfo").hide();
    //1001 用户 1002 订单  1003 商品 1005课程     1007 VIP卡
    $("#castType").val(type);
    $("#castId").val(id);
    $("#castSaleId").val(belong_id);
    $("#castNum").val(num);
    $("#castTicketId").val(id);
    if(type==1001){
        $("#userInfo").show();

        var url = "/customeruser/getById/"+id;
        var completeHandler = function (data) {

            if(data==null){
                alert("无用户信息");
                return false;
            }
            $("#phone").html(data.tel);
            $("#yue").html(data.allBalance);
            $("#userPic").attr("src",data.icon);
        }
        var paramImg={};
        doPost(url,paramImg,completeHandler);



    }

    var showGoodsUrl = "/verification/searchGoods"
    var completeHandler = function (data) {
        if(data.success==1){
            alert("商品列表信息错误");
            return false;
        }
        var list = data.list;
        var html='';
        for(var i=0;i<list.length;i++){
            html+=' <div class="ng_cell" >';
             html+='    <div class=" am-thumbnail-user">';
             html+='    <img src="'+list[i].img_url+'" alt=""  width="114" height="130"/>';
             html+='    <h3 class="am-thumbnail-caption" style="font-size: 14px;height: 65px;line-height: 28px;">'+list[i].goods_name+'</h3>';
             html+='    <input type="checkbox" onclick="countMoney()" class="checkPop_box" style="width:20px; height:20px" name="goods" value="'+list[i].id+'">';
             html+='        <div class="user_conunt">';
             html+='        <span class="jian">-</span>';
             html+='        <em>1</em>';
             html+='        <span class="jia">+</span>';
             html+='        </div>';
             html+='    </div>';
             html+='</div>';
        }
        $("#hAo_counteR").html(html);
        $("#countMoney").html("￥"+"0");
        if(type==1003){
            $(".ng_cell em").html(num);
            $("input[name='goods']").attr("checked","true");

            $(".jian").hide();
            $(".jia").hide();
            countMoney();
        }else{
            $(".jian").show();
            $(".jia").show();
        }
        hAoCounteR();
    }
    var paramImg={type:type,id:id};
    doPost(showGoodsUrl,paramImg,completeHandler);

    //判断是否需要弹出框  userInfo

    $('#sale-bind-modal').modal('open');
}


//计算金额
function countMoney() {
    $("#countMoney").html("——.——");

    var str = getAllCheckGoods();
    var arr = str.split("=");
    var ids = arr[0];
    var nums = arr[1];
    var url = "/verification/calPrice";
    var completeHandler = function (data) {
        $("#countMoney").html("￥"+data.price);
    }
    var paramImg={ids:ids,nums:nums};
    doPost(url,paramImg,completeHandler);
}
//得到选中的
function getAllCheckGoods() {
    var ids = "";
    var nums = "";
    $("input[name='goods']").each(function () {
        if($(this).is(':checked')){
            ids += $(this).val()+",";
            nums += $(this).siblings(".user_conunt").children("em").html()+",";
        }
    });

    return  ids+"="+nums;
}

//付钱
function goCutMoney() {
    var str = getAllCheckGoods();
    var arr = str.split("=");
    var ids = arr[0];
    var nums = arr[1];
    var type = $("#castType").val();
    var id = $("#castId").val();
    var saleId = $("#castSaleId").val();
    var num = $("#castNum").val();
    var url = "/verification/goCutMoney";

    if(ids==''){
        alert("请选择商品购买！");
        return false;
    }

    var completeHandler = function (data) {
        $("#countMoney").html("￥0");
        $("input[name='goods']").removeAttr("checked");
        $("#castType").val("");
        $("#castId").val("");
        $("#castSaleId").val("");
        $("#castNum").val("");

        console.log(data.msg);
        alert(data.msg)



        $('#sale-bind-modal').modal('close');
    }
    var paramImg={ids:ids,nums:nums,type:type,id:id,saleId:saleId,num:num,ticketId:$("#castTicketId").val()};
    doPost(url,paramImg,completeHandler);
}



function  hAoCounteR() {
    $('#hAo_counteR').find('.am-thumbnail-user').each(function () {
        var that = $(this);
        var num=0;
        that.find('.user_conunt span').on('click',function (e) {
            num = that.find('.user_conunt em').text();
            if($(e.target).attr('class') =='jia') {
                num ++;
                that.find('.user_conunt em').text(num)
            }
            if($(e.target).attr('class') =='jian') {
                if(num <=  0) {
                    that.find('.user_conunt em').text(0)
                }else {
                    num --;
                    that.find('.user_conunt em').text(num)
                }
            }
            countMoney();
        })
    })
}


/*商品销售结束*/

/*VIP卡*/
function modalCardInfo(cardNo) {
    $("#cardNoInput").val(cardNo);
    var url = "/vipCard/getByCardNo";
    var completeHandler = function (data) {

        if(data.success==2){
            alert("无此卡信息");
            return false;
        }else if(data.success==1){
            alert("无此卡信息");
            return false;
        }

        $("#saler").html("");
       $("#cardNo").html(data.vipCard.card_no);
       $("#money").html(data.vipCard.price);

       //501 生成 502 已开卡 503 已激活
       if(data.vipCard.state==501){
           $("#state").html("已生成");
           $(".jihuo").hide();
           $(".kaika").show();
           $("#saler").html("");
       }else if(data.vipCard.state==502){
           $("#state").html("已开卡");
           $(".jihuo").show();
           $(".kaika").hide();
           $("#saler").html(data.saler.realname);
       }else if(data.vipCard.state==503){
           $("#state").html("已激活");
           $(".jihuo").hide();
           $(".kaika").hide();
           $("#saler").html(data.saler.realname);
       }
        initSpanAndInput();
    }
    var paramImg={cardNo:cardNo};
    doPost(url,paramImg,completeHandler);

    $('#card-bind-modal').modal('open');

}

function initSpanAndInput() {
    $("#ErrMessage").html("");
    $("#saleName").html("");
    $("#filedName").html("");
    $("#userName").html("");
    $("#bangdingSale").attr("disabled","disabled");
    $("#bangdingUser").attr("disabled","disabled");
    $("#saleId").val("");
    $("#userId").val("");
}
var tel_regex = /^1\d{10}$/;
function checkSaler() {
    initSpanAndInput();
    var tel = $("#saleTel").val();

    if(!(tel_regex.test(tel))){
        $("#ErrMessage").html("请输入正确的手机号");

        return false;
    }

        var url = "/sysuser/findUserByTel";
    var completeHandler = function (data) {
        if(data.isexist==0){
            $("#ErrMessage").html("用户不存在系统中");
            return false;
        }
        $("#saleName").html(data.user.realname);
        $("#filedName").html(data.user.field_name);
        $("#saleId").val(data.user.id);

        $("#bangdingSale").removeAttr("disabled");
    }
    var paramImg={tel:tel};
    doPost(url,paramImg,completeHandler);



}
function checkUser() {
    initSpanAndInput();
    var tel = $("#userTel").val();

    if(!(tel_regex.test(tel))){
        $("#ErrMessage").html("请输入正确的手机号");

        return false;
    }

    var url = "/customeruser/findUserByTel";
    var completeHandler = function (data) {
        if(data.isexist==0){
            $("#ErrMessage").html("用户不存在系统中");
            return false;
        }

        $("#userName").html(data.user.username);
        $("#userId").val(data.user.id);

        $("#bangdingUser").removeAttr("disabled");
    }
    var paramImg={tel:tel};
    doPost(url,paramImg,completeHandler);
}

$("#bangdingSale").click(function () {

    var url = "/vipCard/kaika";
    var completeHandler = function (data) {
        if(data.success==0){
            $('#card-bind-modal').modal('close');
            alert("开卡成功");
        }else{
            alert(data.msg);
        }

    }
    var paramImg={saleId:$("#saleId").val(),cardNo:$("#cardNoInput").val()};
    doPost(url,paramImg,completeHandler);

});

$("#bangdingUser").click(function () {

    var url = "/vipCard/jihuo";
    var completeHandler = function (data) {
        if(data.success==0){
            $('#card-bind-modal').modal('close');
            alert("激活成功");
        }else{
            alert(data.msg);
        }
    }
    var paramImg={userId:$("#userId").val(),cardNo:$("#cardNoInput").val()};
    doPost(url,paramImg,completeHandler);
});

/*VIP卡结束*/


