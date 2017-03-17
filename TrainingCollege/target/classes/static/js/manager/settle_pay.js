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
            $( "#payment" ).text(data);
            $( "#payment" ).val(data);
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



