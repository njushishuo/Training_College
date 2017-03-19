/**
 * Created by ss14 on 2017/3/18.
 */

function reCharge (sid) {

    var status = $('#status').text();
    switch (status){
        case '未激活': status = 'newly'; break;
        case '已激活': status = 'activated'; break;
        case '已暂停': status = 'frozen'; break;
        case '已停止': status = 'disabled'; break;
    }


    var reChargeNum =$('#rechargeNum').val();
    var bankBalance = $('#bankBalance').val();
    reChargeNum = parseInt(reChargeNum);
    bankBalance = parseInt(bankBalance);
    if(reChargeNum>bankBalance){
        reChargeNum = bankBalance;
    }

    if(reChargeNum<0){
        reChargeNum=0;
    }

    var data = {
        sid:sid,
        rechargeNum: reChargeNum,
        status : status
    };

    $.ajax({
        type: "POST",
        url:"/student/"+sid+"/reCharge/",
        data: data,
        success: function(data){

            if(data){
                if(status == "newly"){
                    alert("激活成功");
                }else if(status == "frozen"){
                    alert("恢复成功");
                }
            }else{
                if(status == "newly"){
                    alert("激活失败");
                }else if(status == "frozen"){
                    alert("恢复失败");
                }
            }

            location.reload();
        },

        traditional:true
    });
}