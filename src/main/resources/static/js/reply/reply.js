
$(document).ready(function () {
    getRelyList();
});

// reply regist
function insertReply() {
    let boardId = $("#boardId").val();
    let replyContent = $("#replyContent").val();
    let replyWriter = $("#replyWriter").val();

    $.ajax({
    url: '/reply/insert',
    type: 'post',
    data: {'boardId': boardId, 'replyContent': replyContent, 'replyWriter': replyWriter},
    success: function (data) {
    getRelyList();
    }
});
}

// reply list
function getRelyList(){
    let boardId = $("#boardId").val();
    console.log("boardId : " + boardId);
    $.ajax({
    url:'/reply/list',
    type:'get',
    data:{'boardId':boardId},
    success:function(data){
    // 페이징 시 현재 페이지를 서버로 넘겨야 되는데..
    let prev = data.pageMaker.prev;
    let next = data.pageMaker.next;
    let startPage = data.pageMaker.startPage;
    parseInt(startPage);
    let isPrev = startPage - 1;
    let endPage = data.pageMaker.endPage;
    parseInt(endPage);
    let isNext = endPage + 1;

    // console.log('prev : ' + prev + '\n' + 'next : ' + next + '\n' + 'startPage : ' + startPage + '\n' + 'endPage : ' + endPage);
    let replyHtml = "";
    $.each(data.replyList,function(key,value){
    replyHtml += '<div>';
    replyHtml += '<div>' + '댓글번호:'+ value.replyId+ ' / 작성자:' + value.replyWriter + ' / 작성일: ' + value.regDate;
    replyHtml += '<button onclick="replyUpdateForm('+ value.replyId +',\'' + value.replyContent + '\');">수정</button>';
    replyHtml += '<button onclick="replyDelete(' + value.replyId + ');">삭제</button></div>';
    replyHtml += '<div id="replyContentArea"><p>내용:'+value.replyContent+'</p>';
    replyHtml += '</div>';
});
    $("#replyList").html(replyHtml);
    viewPagination(boardId, prev, next, startPage, endPage, isPrev, isNext);
}});
}

function viewPagination(boardId, prev, next, startPage, endPage, isPrev, isNext) {
    // console.log('prev: ' + prev + '\n' + 'next: ' + next + '\n' + 'startPage: ' + startPage + '\n' + 'endPage: ' + endPage + '\n' + 'isPrev: ' + isPrev + '\n' + 'isNext: ' + isNext);
    let replyPagination = "";

    if (prev == true) {
        replyPagination += '<li>';
        replyPagination += '<a href="/reply/list?boardId=' + boardId + '&pageNum=' + isPrev +'">' + "Prev" + '</a>';
        replyPagination += '</li>';
    }
    console.log('#boardId : ' + boardId);
    for (let num = startPage; num <= endPage; num++) {
        replyPagination += '<li>';
        // replyPagination += '<a th:href="@{/board/list(pageNum=${pageMaker.startPage}-1)}">' + num + '</a>';
        // replyPagination += '<a th:href="@{/board/list(pageNum=${pageMaker.startPage}-1)}">' + num + '</a>';
        replyPagination += '<a href="/reply/list?boardId=' + boardId + '&pageNum=' + num +'">' + num + '</a>';
        // replyPagination += '<a onclick="replyLink('+ boardId + ', ' + num +');" href="#">' + num + '</a>';
        replyPagination += '</li>';
    }
    if (next == true) {
        replyPagination += '<li>';
        replyPagination += '<a href="/reply/list?boardId=' + boardId + '&pageNum=' + isNext +'">' + "Next" + '</a>';
        replyPagination += '</li>';
    }
    $("#reply-pagination").html(replyPagination);
}

    // reply delete
    function replyDelete(replyId) {
    $.ajax({
        url : '/reply/{replyId}/del',
        type : 'delete',
        data : {'replyId': replyId},
        success: function (data) {
            getRelyList();
        }
    });
}

    // reply updateForm
    function replyUpdateForm(replyId, replyContent) {
    let replyArea = "";
    replyArea += '<div>';
    replyArea += '<input type="text" id="dyReplyContent" name="replyContent" value="' + replyContent + '"/>';
    replyArea += '<button type="button" onclick="replyUpdateProc(' + replyId + ');">수정</button>';
    replyArea += '</div>';
    $("#replyArea1").hide();
    $("#replyList").html(replyArea);
}

    // reply update process
    function replyUpdateProc(replyId) {
    $.ajax({
        url : '/reply/update',
        type : 'post',
        data : {'replyId': replyId, 'replyContent': $("#dyReplyContent").val()},
        success: function (data) {
            getRelyList();
        }
    });
}