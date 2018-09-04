$(function(){
	$(".btn").click(function(event) {
		$(".nav").slideToggle();
	});
	$(".del").click(function(event) {
		$(".nav").slideToggle();
	});
	
    $(".top").click(function(event) {
    	$(document).scrollTop() = 0
    });
    // 轮播部分
    var lunbow = $("#lunbobox").width();
    var lunbow1 = Math.floor(lunbow*717/1920);

    var art0h = $(".art0").height();
    var smallnav = Math.floor(lunbow1+art0h);

    $(".art").css('margin-top', lunbow1);
    var c = Math.floor(lunbow1-20);
    $("#lunbobox ul").css('top', c);
    // nav固定
    var nav=$(".header"); //得到导航对象
    var win=$(window); //得到窗口对象
    var sc=$(document);//得到document文档对象。
    win.scroll(function(){
	    if(sc.scrollTop()>=lunbow1){
		    nav.addClass("fix");
		    $(".logo").css('width', 150);
		    $(".top").css('display', 'block');
	    }else{
		    nav.removeClass("fix");
		    $(".logo").css('width', 200);
		    $(".top").css('display', 'none');
	    }
    });

    // 小nav固定
    var nav1=$(".art1");
    var nav2=$(".sj-art1"); //得到导航对象
    win.scroll(function(){
        if(sc.scrollTop()>=smallnav){
            nav1.addClass("fix1");
            nav2.addClass("fix1");
        }else{
            nav1.removeClass("fix1");
            nav2.removeClass("fix1");
        }
    });






    // 图片部分
    var imgheight = $(".art2-a img").width();
    var imgheight1 = Math.floor(imgheight*500/605);
    $(".art2-p").css('height', imgheight1);    
    
     $(window).resize(function(){
        var lunbow = $("#lunbobox").width();
        var lunbow1 = Math.floor(lunbow*717/1920);

        var art0h = $(".art0").height();
        var smallnav = Math.floor(lunbow1+art0h);

        $(".art").css('margin-top', lunbow1);
        var c = Math.floor(lunbow1-20);
        $("#lunbobox ul").css('top', c);
        // nav固定
        var nav=$(".header"); //得到导航对象
        var win=$(window); //得到窗口对象
        var sc=$(document);//得到document文档对象。
        win.scroll(function(){
	        if(sc.scrollTop()>=lunbow1){
		        nav.addClass("fix");
		        $(".logo").css('width', 150);
		        $(".top").css('display', 'block');
	        }else{
		        nav.removeClass("fix");
		        $(".logo").css('width', 200);
	        }
        });
        // 小nav固定
        var nav1=$(".art1"); //得到导航对象
        var nav2=$(".sj-art1"); //得到导航对象
        win.scroll(function(){
            if(sc.scrollTop()>=smallnav){
                nav1.addClass("fix1");
                nav2.addClass("fix1");
            }else{
                nav1.removeClass("fix1");
                nav2.removeClass("fix1");
            }
        });


        // 图片部分 
        var imgheight = $(".art2-a img").width();
        var imgheight1 = Math.floor(imgheight*500/605);
        $(".art2-p").css('height', imgheight1);

        // 联系我们还有关于垂直居中
        lianXi();
    });
})

function adjustImg(){
    // 文字图片上下居中
    var imgt = $(".img-title").height();
    var imgins = Math.floor(imgt/2-50);
    $(".img-title").css('margin-top', -imgins);
    // 手机版
    var sjimgt = $(".sj-img-title").height();
    var sjimgins = Math.floor(sjimgt/2);
    $(".sj-img-title").css('margin-top', -sjimgins);
}

function lianXi(){
    // 文字图片上下居中
    var art = $(".kuai").height();
    var art1 = $(window).height();
    var art3 = Math.floor((art1-art)/2);
    $(".kuai").css('margin-top', art3);
}