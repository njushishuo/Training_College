/**
 * Created by ss14 on 2017/3/17.
 */


function updateProfitStats () {
    //获取预选的机构ID
    var sysId = $( "#orgSysId option:selected" ).val();
    var data ={
        OrgSysId :sysId
    }

    $.ajax({
        type: "GET",
        url:"/manager/accountSettlement/profitStats",
        data: data,
        dataType: 'json',
        success: function(data){
            //alert(data);
            $( "#income" ).val(data[0]);
            $( "#output" ).val(data[1]);
            $( "#profit" ).val(data[2]);

            var companyBalance = $("#balance").val();
            var profit = data[2];
            var postBalance = parseInt(companyBalance) - parseInt(profit) ;
            $("#post_balance").val(postBalance);

        },
        traditional:true
    });

}



$(document).ready(
    updateProfitStats()
);


$( "#orgSysId" ).change(
    updateProfitStats()
);

function settle() {
    var profit = $( "#profit" ).val();
    var orgSysId = $( "#orgSysId option:selected" ).val();
    var data = {
        profit:profit,
        orgSysId : orgSysId
    };

    $.ajax({
        type: "POST",
        url:"/manager/accountSettlement/settleRequest",
        data: data,
        success: function(data){
            if(data){
                alert("结算成功");
                location.href = "/manager/accountSettlement";
            }

        },
        traditional:true
    });
}
