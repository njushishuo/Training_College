/**
 * Created by ss14 on 2017/3/17.
 */


function updateSum () {
    //获取预选的机构ID
    var sysId = $( "#orgSysId option:selected" ).val();
    var data ={
        OrgSysId :sysId
    }
    $.ajax({
        type: "GET",
        url:"/manager/accountSettlement/payment/sum",
        data: data,
        success: function(data){
            $( "#payment" ).val(data);
            var companyBalance = $("#balance").val();
            var postBalance = parseInt(companyBalance) +parseInt(data) ;
            $("#post_balance").val(postBalance);

        },
        traditional:true
    });

}

$(document).ready(
    updateSum
);


$( "#orgSysId" ).change(
    updateSum
);

function settlePayment() {
    var payment = $( "#payment" ).val();
    var orgSysId = $( "#orgSysId option:selected" ).val();
    var data = {
        payment:payment ,
        orgSysId : orgSysId
    };

    $.ajax({
        type: "POST",
        url:"/manager/accountSettlement/paymentRequest",
        data: data,
        success: function(data){
            if(data){
                alert("结算成功");
                location.href = "/manager/accountSettlement/payment";
            }

        },
        traditional:true
    });
}



