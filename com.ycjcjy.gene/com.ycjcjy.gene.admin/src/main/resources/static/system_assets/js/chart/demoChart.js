$(function(){var dataType=$("body").attr("data-type");for(key in pageData){if(key==dataType){pageData[key]()}}});var pageData={"index":function indexData(){$("#example-r").DataTable({bInfo:false,dom:"ti"});var echartsA=echarts.init(document.getElementById("tpl-echarts"));option={tooltip:{trigger:"axis"},grid:{top:"3%",left:"3%",right:"4%",bottom:"3%",containLabel:true},xAxis:[{type:"category",boundaryGap:false,data:["周一","周二","周三","周四","周五","周六","周日"]}],yAxis:[{type:"value"}],textStyle:{color:"#838FA1"},series:[{name:"邮件营销",type:"line",stack:"总量",areaStyle:{normal:{}},data:[120,132,101,134,90],itemStyle:{normal:{color:"#1cabdb",borderColor:"#1cabdb",borderWidth:"2",borderType:"solid",opacity:"1"},emphasis:{}}}]};echartsA.setOption(option)},"chart":function chartData(){var echartsC=echarts.init(document.getElementById("tpl-echarts-C"));optionC={tooltip:{trigger:"axis"},legend:{data:["蒸发量","降水量","平均温度"]},xAxis:[{type:"category",data:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]}],yAxis:[{type:"value",name:"水量",min:0,max:250,interval:50,axisLabel:{formatter:"{value} ml"}},{type:"value",name:"温度",min:0,max:25,interval:5,axisLabel:{formatter:"{value} °C"}}],series:[{name:"蒸发量",type:"bar",data:[2,4.9,7,23.2,25.6,76.7,135.6,162.2,32.6,20,6.4,3.3]},{name:"降水量",type:"bar",data:[2.6,5.9,9,26.4,28.7,70.7,175.6,182.2,48.7,18.8,6,2.3]},{name:"平均温度",type:"line",yAxisIndex:1,data:[2,2.2,3.3,4.5,6.3,10.2,20.3,23.4,23,16.5,12,6.2]}]};echartsC.setOption(optionC);var echartsB=echarts.init(document.getElementById("tpl-echarts-B"));optionB={tooltip:{trigger:"axis"},legend:{x:"center",data:["某软件","某主食手机","某水果手机","降水量","蒸发量"]},radar:[{indicator:[{text:"品牌",max:100},{text:"内容",max:100},{text:"可用性",max:100},{text:"功能",max:100}],center:["25%","40%"],radius:80},{indicator:[{text:"外观",max:100},{text:"拍照",max:100},{text:"系统",max:100},{text:"性能",max:100},{text:"屏幕",max:100}],radius:80,center:["50%","60%"],},{indicator:(function(){var res=[];for(var i=1;i<=12;i++){res.push({text:i+"月",max:100})}return res})(),center:["75%","40%"],radius:80}],series:[{type:"radar",tooltip:{trigger:"item"},itemStyle:{normal:{areaStyle:{type:"default"}}},data:[{value:[60,73,85,40],name:"某软件"}]},{type:"radar",radarIndex:1,data:[{value:[85,90,90,95,95],name:"某主食手机"},{value:[95,80,95,90,93],name:"某水果手机"}]},{type:"radar",radarIndex:2,itemStyle:{normal:{areaStyle:{type:"default"}}},data:[{name:"降水量",value:[2.6,5.9,9,26.4,28.7,70.7,75.6,82.2,48.7,18.8,6,2.3],},{name:"蒸发量",value:[2,4.9,7,23.2,25.6,76.7,35.6,62.2,32.6,20,6.4,3.3]}]}]};echartsB.setOption(optionB);var echartsA=echarts.init(document.getElementById("tpl-echarts-A"));option={tooltip:{trigger:"axis",},legend:{data:["邮件","媒体","资源"]},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:true},xAxis:[{type:"category",boundaryGap:true,data:["周一","周二","周三","周四","周五","周六","周日"]}],yAxis:[{type:"value"}],series:[{name:"邮件",type:"line",stack:"总量",areaStyle:{normal:{}},data:[120,132,101,134,90,230,210],itemStyle:{normal:{color:"#59aea2"},emphasis:{}}},{name:"媒体",type:"line",stack:"总量",areaStyle:{normal:{}},data:[220,182,191,234,290,330,310],itemStyle:{normal:{color:"#e7505a"}}},{name:"资源",type:"line",stack:"总量",areaStyle:{normal:{}},data:[150,232,201,154,190,330,410],itemStyle:{normal:{color:"#32c5d2"}}}]};echartsA.setOption(option)}};