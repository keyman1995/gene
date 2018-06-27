/**
 * 生成代码
 * @param id
 */
function generatedCode(id) {
    var disIputs = $('input[disabled=disabled]');
    $(disIputs).removeAttr("disabled");
    var parent = $('#DataFrom').serializeJson();
    if(typeof(id) == 'undefined' || id == null){
        var id = $('#entityId').val();
    }
    var url = "/databasetable/generate"
    var completeHandler = function (data) {
        alert( data.msg )
    }
    doPost(url,{id:id},completeHandler)
}