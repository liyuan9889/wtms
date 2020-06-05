(function($){
    var $box = $('#box');
    var $bg = $('#bg');
    var $bgcolor = $('#bgcolor');
    var $btn = $('#bt');
    var $text = $('#text');
    var statu = false;
    var ox = 0;
    var lx = 0;
    var left = 0;
    var bgleft = 0;
    $btn.mousedown(function(e){
        lx = $btn.offset().left;
        ox = e.pageX - left;
        statu = true;
    });
    $(document).mouseup(function(){
        statu = false;
    });
    $box.mousemove(function(e){
        if(statu){
            left = e.pageX - ox;
            if(left < 0){
                left = 0;
            }
            if(left > 200){
                left = 200;
            }
            $btn.css('left',left);
            $bgcolor.width(left);
            $text.html(parseInt(left/2) + '%');
        }
    });
    $bg.click(function(e){
        if(!statu){
            bgleft = $bg.offset().left;
            left = e.pageX - bgleft;
            if(left < 0){
                left = 0;
            }
            if(left > 200){
                left = 200;
            }
            $btn.css('left',left);
            $bgcolor.stop().animate({width:left},200);
            $text.html(parseInt(left/2) + '%');
        }
    });

})(jQuery);
