function unSelect(sid, pid) {

    var data = {
        studentId: sid,
        projectId: pid
    };

    $.ajax({
        type: "POST",
        url:"/student/"+sid+"/unSelection/"+pid,
        data: data,
        success: function(data){
            alert("退课成功");
            location.reload();
        },
        traditional:true
    });
}