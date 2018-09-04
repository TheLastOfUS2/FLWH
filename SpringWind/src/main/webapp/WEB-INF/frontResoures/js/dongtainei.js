$(function() {
    // 图片部分移入移出
    $(".tu-div").each(function(index, el) {
         var i = index;
        $(this).mouseover(function(event) {
            $(".month").each(function(index, el) {
                $(this).css('color', '#919191').css('background-color', '#f5f5f5');
            });
            $(".more").each(function(index, el) {
                $(this).css('color', '#626262');
            });
            $(".month").eq(i).css('color', '#ffffff').css('background-color', '#1c2a4d');
            $(".more").eq(i).css('color', '#1c2a4d');
        });
        $(this).mouseout(function(event) {
            $(".art2-p").each(function(index, el) {
                $(this).hide();
            });
            $(".art2-p").hide();
            $(".art2-p").eq(i).hide();
            $(".art2-a img").eq(i).removeClass('dong');
        });
    })


})
