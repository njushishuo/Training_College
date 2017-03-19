/**
 * Created by ss14 on 2017/3/15.
 */


function reserve(sid, pid) {

    var data = {
        studentId: sid,
        projectId: pid
    };

    $.ajax({
        type: "POST",
        url:"/student/"+sid+"/reservation/"+pid,
        data: data,
        success: function(data){

            if(data){
                alert("预订成功");
                location.reload();
            }

        },
        traditional:true
    });
}



function select(sid, pid) {

    var data = {
        studentId: sid,
        projectId: pid
    };

    $.ajax({
        type: "POST",
        url:"/student/"+sid+"/selection/"+pid,
        data: data,
        success: function(data){

            if(data){
                alert("加入成功");
                location.reload();
            }

        },
        traditional:true
    });
}