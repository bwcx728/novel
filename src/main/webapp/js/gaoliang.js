/**
 * Created by dell on 2018/2/28.
 */
(function ($) {
    $.fn.GL = function (options) {
        var dataop = {
            ocolor:'red',
            fontSize: '字体大小',
            fontWeight:'字体加粗',
            oshuru:'高亮',
        };
        var chuancan = $.extend(dataop,options);
        $(this).each(function()//先遍历，清除上一次的样式
        {
            var _this = $(this)
            _this.find($(".glnow")).each(function()//找到所有highlight属性的元素；
            {
                $(this).css({color:""});//将他们的属性去掉；
            });
        });
        if(chuancan.oshuru==''){
            return false;
        }else{
            var regExp = new RegExp("(" + chuancan.oshuru.replace(/[(){}.+*?^$|\\\[\]]/g, "\\$&") + ")", "ig");//创建正则表达式，g表示全局的，如果不用g，则查找到第一个就不会继续向下查找了；
            $(this).each(function()//遍历文章；
            {
                var _this1 = $(this)
                var html = _this1.html();
                var newHtml = html.replace(regExp, '<span class="glnow" style="color:'+chuancan.ocolor+';font-size:'+chuancan.fontSize+';font-weight:'+chuancan.fontWeight+'  ">'+chuancan.oshuru+'</span>');//将找到的关键字替换，加上highlight属性；
                _this1.html(newHtml);//更新文章；
            });
        }
    }
})(jQuery);


