$( document ).ready(function() {
    if($(window).height() > 700) {
        $('.pnl-login').css('top', ($(window).height() - 600));
    }else{
        $('.pnl-login').css('top', 5);
    }
    $('body').height($(window).height());
    $('#wrapper').height($(window).height());
    $('#content\\:pnlLogin').keypress(function(e) {
        if(e.which == 13) {
            $('#content\\:btnLogin').click();
        }
    });
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