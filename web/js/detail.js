/**
 * Created by zhangchuan on 2017/10/23.
 */
$(function () {
    var url = window.location.search;
    url = decodeURIComponent(url);
    var reg = /[?&][^?&]+=[^?&]+/g;
    var arr = url.match(reg);
    var name1 = arr[0].substring(1).split("=")[1];
    name1 = decodeURI(decodeURI(name1));
    console.log(name1);
})
