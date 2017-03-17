/**
 * Created by ss14 on 2017/3/17.
 */

function approve(pid) {

    var data = {
        projectId: pid
    };

    $.ajax({
        type: "POST",
        url:"/manager/examination/creation/approval",
        data: data,
        success: function(data){

            if(data){
                alert("审批成功");
                location.reload();
            }

        },
        traditional:true
    });


}


function reject(pid) {

    alert(pid);
    var data = {
        projectId: pid
    };

    $.ajax({
        type: "POST",
        url:"/manager/examination/creation/rejection",
        data: data,
        success: function(data){
            if(data){
                alert("审批成功");
                location.reload();
            }
        },
        traditional:true
    });


}