/**
 * Created by ss14 on 2017/3/16.
 */
function unReserve(sid) {

    var data =$('input[name="ridCheckBox"]:checked');

    $.ajax({
        type: "POST",
        url:"/student/"+sid+"/orders/",
        data: data,
        success: function(data){
            if(data){
                alert("退订成功");
            }
            window.location.reload();
        },
        traditional:true
    });
}