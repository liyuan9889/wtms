/*
 * 根据index获取 str
 */
function byIndexLeve(index){
    var str ="";
    switch (index)
    {
        case 0:
            str="差评";
            break;
        case 1:
            str="较差";
            break;
        case 2:
            str="中等";
            break;
        case 3:
            str="一般";
            break;
        case 4:
            str="好评";
            break;
    }
    return str;
}
//  星星数量
var stars = [
    ['x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png'],
    ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png'],
];
// $(".block li").find("img").hover(function(e) {
$("#starLi").find("img").hover(function(e) {
    var obj = $(this);
    var index = obj.index();
    if(index < (parseInt($("#starLi").attr("data-default-index")) -1)){
        return ;
    }
    var li = obj.closest("li");
    var star_area_index = li.index();
    for (var i = 0; i < 10; i++) {
        li.find("img").eq(i).attr("src", "/img/" + stars[index][i]);//切换每个星星
    }
    // $(".level").html(byIndexLeve(index));
}, function() {
})

// $(".block li").hover(function(e) {
$("#starLi").hover(function(e) {
}, function() {
    var index = $(this).attr("data-default-index");//点击后的索引
    index = parseInt(index);
    // console.log("index",index);
    // $(".level").html(byIndexLeve(index-1));
    // console.log(byIndexLeve(index-1));
    $(".order-evaluation ul li:eq(0)").find("img").attr("src","/img/x1.png");
    for (var i=0;i<index;i++){
        $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","/img/x2.png");
    }
})
// $(".block li").find("img").click(function() {
$("#starLi").find("img").click(function() {
    var obj = $(this);
    var li = obj.closest("li");
    var star_area_index = li.index();
    var index1 = obj.index();
    li.attr("data-default-index", (parseInt(index1)+1));
    // var index = $(".block li").attr("data-default-index");//点击后的索引
    var index = $("#starLi").attr("data-default-index");//点击后的索引
    index = parseInt(index);
    console.log("index",index);
    // $(".level").html(byIndexLeve(index-1));
    // console.log(byIndexLeve(index-1));
    $(".order-evaluation ul li:eq(0)").find("img").attr("src","/img/x1.png");
    for (var i=0;i<index;i++){
        $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","/img/x2.png");
    }

});