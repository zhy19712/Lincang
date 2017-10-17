/**
 * Created by zhangchuan on 2017/10/12.
 */
$(document).ready(function () {
    //themes, change CSS with JS
    //default theme(CSS) is cerulean, change it if needed
    var defaultTheme = 'cerulean';
    var currentTheme = $.cookie('currentTheme') == null ? defaultTheme : $.cookie('currentTheme');
    var msie = navigator.userAgent.match(/msie/i);
    $.browser = {};
    $.browser.msie = {};
    switchTheme(currentTheme);

    $('.navbar-toggle').click(function (e) {
        e.preventDefault();
        $('.nav-sm').html($('.navbar-collapse').html());
        $('.sidebar-nav').toggleClass('active');
        $(this).toggleClass('active');
    });

    var $sidebarNav = $('.sidebar-nav');

    // Hide responsive navbar on clicking outside
    $(document).mouseup(function (e) {
        if (!$sidebarNav.is(e.target) // if the target of the click isn't the container...
            && $sidebarNav.has(e.target).length === 0
            && !$('.navbar-toggle').is(e.target)
            && $('.navbar-toggle').has(e.target).length === 0
            && $sidebarNav.hasClass('active')
        )// ... nor a descendant of the container
        {
            e.stopPropagation();
            $('.navbar-toggle').click();
        }
    });


    $('#themes a').click(function (e) {
        e.preventDefault();
        currentTheme = $(this).attr('data-value');
        $.cookie('currentTheme', currentTheme, {expires: 365});
        switchTheme(currentTheme);
    });


    function switchTheme(themeName) {
        if (themeName == 'classic') {
            $('#bs-css').attr('href', 'bower_components/bootstrap/dist/css/bootstrap.min.css');
        } else {
            $('#bs-css').attr('href', 'css/bootstrap-' + themeName + '.min.css');
        }

        $('#themes i').removeClass('glyphicon glyphicon-ok whitespace').addClass('whitespace');
        $('#themes a[data-value=' + themeName + ']').find('i').removeClass('whitespace').addClass('glyphicon glyphicon-ok');
    }

    //disbaling some functions for Internet Explorer
    if (msie) {
        $('#is-ajax').prop('checked', false);
        $('#for-is-ajax').hide();
        $('#toggle-fullscreen').hide();
        $('.login-box').find('.input-large').removeClass('span10');

    }

    //highlight current / active link
    $('ul.main-menu li a').each(function () {
        if ($($(this))[0].href == String(window.location))
            $(this).parent().addClass('active');
    });

    //establish history variables
    var
        History = window.History, // Note: We are using a capital H instead of a lower h
        State = History.getState(),
        $log = $('#log');
    //bind to State Change
    History.Adapter.bind(window, 'statechange', function () { // Note: We are using statechange instead of popstate
        var State = History.getState(); // Note: We are using History.getState() instead of event.state
        $.ajax({
            url: State.url,
            success: function (msg) {
                $('#content').html($(msg).find('#content').html());
                $('#loading').remove();
                $('#content').fadeIn();
                var newTitle = $(msg).filter('title').text();
                $('title').text(newTitle);
                docReady();
            }
        });
    });

    $('.accordion > a').click(function (e) {
        e.preventDefault();
        var $ul = $(this).siblings('ul');
        var $li = $(this).parent();
        if ($ul.is(':visible')) $li.removeClass('active');
        else                    $li.addClass('active');
        $ul.slideToggle();
    });

    $('.accordion li.active:first').parents('ul').slideDown();

    // 清空数据
    function wipeData() {
        $("#input1").val("");
        $("#input2").val("");
        $("#input3").val("");
        $("#input4").val("");
        $("#input5").val("");
        $("#input6").val("");
        $("#input8").val("");
        $("#input9").val("");
        $("#input10").val("");
        $(".add_file").remove();
        $("#oId").text("");
        $("#created_at").text("");
    }

    // 多文件上传
    var fileIndex = 1;
    function add_click_file(index){
        $("#add_file_"+fileIndex).click();
    }

    function add(index) {
        /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
        var len = $("#add_file_" + (fileIndex) + "").val().split("\\").length;
        alert($("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1]);
        var num = $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1];
        $("#filesUpload").append('<span  id="add_file_span_' + (fileIndex) + '"  class="add_file">' + $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1] + '</span>');
        $("#filesUpload").append('<a   id="add_file_a_' + (fileIndex) + '"  class="add_file" href="javascript:del_file(' + fileIndex+ ')">删除</a>');
        $("#filesUpload").append('<input style="display:none;" id="add_file_' + (fileIndex + 1) + '" type="file" name = "files" onChange="add(' + (fileIndex + 1) + ')"/>');
        ++fileIndex;
    }

    function del_file(number) {
        var o=document.getElementById("filesUpload");//获取父节点
        var int=document.getElementById("add_file_" + number+"");//获取需要删除的子节点
        var a=document.getElementById("add_file_a_" + number+"");//获取需要删除的子节点
        var span=document.getElementById("add_file_span_" + number+"");//获取需要删除的子节点
        o.removeChild(int); //从父节点o上面移除子节点a
        o.removeChild(a);
        o.removeChild(span)
    }
    //规划科资金申请
    var money_apply1 = $('#money_apply1').DataTable({
        ajax: {
            url: "/capitalFlowForm.do",
            async:false
        },
        "order": [[1, 'asc']],
        "serverSide": true,
        "columns": [
            {"data": "id"},
            {"data": "create_time"},
            {"data": "report_person"},
            {"data": "report_quarter"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [5],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='detail(this)' value='查看'/>";
                        html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                    return html;
                }
            }
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });

    //申请提交
    $("#money_apply_wdo .btn-primary").click(function () {
        var app_people=  $("#input1").val();
        var app_time=  $("#input2").val();
        var app_content=  $("#input3").val();
        var datas= {
            "report_person":app_people,
            "report_quarter":app_time,
            "report_text":app_content
        };
        if(app_people == ""){
            alert("上报人")
        }else if(app_time == ""){
            alert("上报时间")
        }else if(app_content == ""){
            alert("上报内容")
        }else {
            $.ajax({
                type: 'post',
                url: '/submitDataOfCapital.do',
                data: datas,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {
                    if(result){
                        alert("提交成功");
                        money_apply1.ajax.url("/capitalFlowForm.do").load();
                    }else {
                        alert("提交失败");
                    }
                    $("#money_apply_wdo").modal("hide");
                    wipeData();
                },
                error:function () {
                    alert("系统错误");
                }
            });
            /* $("#fileForm").submit();*/
        }
    });

    //区县资金申请
    // var quxian = $('#quxian').DataTable({
    //     ajax: {
    //         url: "/archivedtable_stuff.do",
    //     },
    //     "order": [[2, 'asc']],
    //     "serverSide": true,
    //     "columns": [
    //         {"data": "ID"},
    //         {"data": "TITLE"},
    //         {"data": "CREATED_AT"},
    //         {"data": null}
    //     ],
    //     "columnDefs": [
    //         {
    //             "searchable": false,
    //             "orderable": false,
    //             "targets": [3],
    //             "render" :  function(data,type,row) {
    //                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='comdetail(this)' value='查看'/>"
    //                 return html;
    //             }
    //         },
    //         {
    //             "searchable": false,
    //             "orderable": false,
    //             "targets": [0]
    //         }
    //     ],
    //     "language": {
    //         "lengthMenu": "每页_MENU_ 条记录",
    //         "zeroRecords": "没有找到记录",
    //         "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
    //         "infoEmpty": "无记录",
    //         "search": "搜索：",
    //         "infoFiltered": "(从 _MAX_ 条记录过滤)",
    //         "paginate": {
    //             "previous": "上一页",
    //             "next": "下一页"
    //         }
    //     }
    // });

    //区县资金申请
    // var caiwu = $('#caiwu').DataTable({
    //     ajax: {
    //         url: "/archivedtable_stuff.do",
    //     },
    //     "order": [[2, 'asc']],
    //     "serverSide": true,
    //     "columns": [
    //         {"data": "ID"},
    //         {"data": "TITLE"},
    //         {"data": "CREATED_AT"},
    //         {"data": null}
    //     ],
    //     "columnDefs": [
    //         {
    //             "searchable": false,
    //             "orderable": false,
    //             "targets": [3],
    //             "render" :  function(data,type,row) {
    //                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='comdetail(this)' value='查看'/>"
    //                 return html;
    //             }
    //         },
    //         {
    //             "searchable": false,
    //             "orderable": false,
    //             "targets": [0]
    //         }
    //     ],
    //     "language": {
    //         "lengthMenu": "每页_MENU_ 条记录",
    //         "zeroRecords": "没有找到记录",
    //         "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
    //         "infoEmpty": "无记录",
    //         "search": "搜索：",
    //         "infoFiltered": "(从 _MAX_ 条记录过滤)",
    //         "paginate": {
    //             "previous": "上一页",
    //             "next": "下一页"
    //         }
    //     }
    // });

    //other things to do on document ready, separated for ajax calls
    docReady();


});


function docReady() {
    //prevent # links from moving to top
    $('a[href="#"][data-top!=true]').click(function (e) {
        e.preventDefault();
    });

    //notifications
    $('.noty').click(function (e) {
        e.preventDefault();
        var options = $.parseJSON($(this).attr('data-noty-options'));
        noty(options);
    });

    //chosen - improves select
    /*$('[data-rel="chosen"],[rel="chosen"]').chosen();*/

    //tabs
    $('#myTab a:first').tab('show');
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    //tooltip
    $('[data-toggle="tooltip"]').tooltip();

    //popover
    $('[data-toggle="popover"]').popover();

    //iOS / iPhone style toggle switch
    $('.iphone-toggle').iphoneStyle();



}