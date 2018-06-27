/*初始化页面金额*/
function initPrice() {
    var cardId = $('#card_id').val();
    if (cardId != null && typeof(cardId) != 'undefined'){
        findPriceByCardId()
    }
    $('body').on('change','#card_id',function () {
        findPriceByCardId()
    })
}
/*根据卡ID查询卡价格*/
function findPriceByCardId() {
    var cid = $('#card_id').val();
    var url = "/swimingfitness/findbyid";
    var param = {id:cid};
    doPost(url,param,function(res){
        $('#price').val(res.data[0].price)
        $('#card_name').val(res.data[0].description)
        $('#card_num').val(res.data[0].num)
        if(res.data[0].time_scope === 'ck'){
            $('#fieldNum').show();
            $('#fieldEndCradTime').show();
            if($('#end_crad_time').val() == '' ||  $('#end_crad_time').val() == null){
                $('#end_crad_time').removeAttr("disabled");
            }
        }else{
            $('#fieldEndCradTime').hide();
            $('#fieldNum').hide();
            $('#end_crad_time').attr("disabled",true);
            $('#card_num').attr("disabled",true);
        }

    })
}

/*初始化tagInput*/
function initTagInput(view,edit,customer_user_id,customer_user_name) {
    /*猎犬 异步数据*/
    var rolenames = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('username'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {//在文本框输入字符时才发起请求
            url:'/customeruser/findcususerbystr?param=%QUERY',
            wildcard: '%QUERY'
            ,filter: function(result) {
                return $.map(result.data, function(item) {
                    return {
                        username:item.username,
                        id:item.id
                    };
                });
            }
        }
    });

    rolenames.initialize();
    /*tags input初始化*/
    $('#customer_user_id').tagsinput(
        {
            typeaheadjs: {
                displayKey: 'username',
                source: rolenames.ttAdapter()
            },
            freeInput:false,
            trimValue: 'true',
            itemValue: 'id',
            maxTags: 1,
            itemText: 'username'
        }
    );

    $('body').on('change','#customer_user_id',function () {
        var customer_user_name = $('.tag,.am-badge,.am-badge-primary').text();
        $('#customer_user_name').val(customer_user_name);
    })

    if(view || edit){
        $('#customer_user_id').tagsinput('add',{id:customer_user_id, username:customer_user_name});
    }
}