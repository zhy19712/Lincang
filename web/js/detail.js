/**
 * Created by zhangchuan on 2017/10/23.
 */
$(function () {
    var url = window.location.search;
    url = decodeURIComponent(url);
    var reg = /[?&][^?&]+=[^?&]+/g;
    var arr = url.match(reg);
    var kind = arr[0].substring(1).split("=")[1];
    var id = arr[1].substring(1).split("=")[1];
    kind = decodeURI(decodeURI(kind));
    var id1 = decodeURI(decodeURI(id));
    $("#fid span").text(id1);
    var id2 = encodeURI(encodeURI(id1));
    console.log(kind,id);
    // $.ajax({
    //     url: "",
    //     type: "post",
    //     data: {id:id2},
    //     dataType: "json",
    //     seccess: function () {
    //
    //     }
    // })
})
