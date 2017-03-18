/**
 * Created by ss14 on 2017/3/18.
 */
/**
 * Created by ss14 on 2017/3/17.
 */


function updateRepaySum () {
    //获取预选的机构ID
    var sysId = $( "#orgSysId option:selected" ).val();
    var data ={
        OrgSysId :sysId
    }
    $.ajax({
        type: "GET",
        url:"/manager/accountSettlement/repayment/sum",
        data: data,
        success: function(data){
            $( "#repayment" ).val(data);
            var companyBalance = $("#balance").val();
            var postBalance = parseInt(companyBalance) - parseInt(data) ;
            $("#post_balance").val(postBalance);

        },
        traditional:true
    });

}

$(document).ready(
    updateRepaySum
);


$( "#orgSysId" ).change(
    updateRepaySum
);

function settleRepayment() {
    var repayment = $( "#repayment" ).val();
    var orgSysId = $( "#orgSysId option:selected" ).val();
    var data = {
        repayment:repayment ,
        orgSysId : orgSysId
    };

    $.ajax({
        type: "POST",
        url:"/manager/accountSettlement/repaymentRequest",
        data: data,
        success: function(data){
            if(data){
                alert("结算成功");
                location.href = "/manager/accountSettlement/repayment";
            }

        },
        traditional:true
    });
}



