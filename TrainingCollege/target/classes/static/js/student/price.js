/**
 * Created by ss14 on 2017/3/19.
 */
function updatePrice () {
    //获取预选的机构ID
    var classId = $( "#classId option:selected" ).val();

    $.ajax({
        type: "GET",
        url:"/organization/classPrice/"+classId,
        success: function(data){
            $( "#price" ).val(data);
        },
        traditional:true
    });

}

$(document).ready(
    updatePrice
);


$( "#classId" ).change(
    updatePrice
);