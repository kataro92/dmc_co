$( document ).ready(function() {
    if($(window).height() > 700) {
        $('.pnl-login').css('top', ($(window).height() - 600));
    }else{
        $('.pnl-login').css('top', 5);
    }
    $('body').height($(window).height());
    $('#wrapper').height($(window).height());
});
$( window ).resize(function() {
    if($(window).height() > 700) {
        $('.pnl-login').css('top', ($(window).height() - 600));
    }else{
        $('.pnl-login').css('top', 5);
    }
    $('body').height($(window).height());
    $('#wrapper').height($(window).height());
});