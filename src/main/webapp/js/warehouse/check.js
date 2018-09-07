function detailShow(id) {
    $('#master-panel-' + id).hide();
    $('#detail-panel-' + id).removeClass('hidden-all');
    $('#detail-panel-' + id).show();
    openNav();
}
function detailHide(id) {
    closeNav();
    $('#detail-panel-' + id).hide();
    $('#master-panel-' + id).show();
}
/* Set the width of the side navigation to 250px */
function openNav() {
    document.getElementById("main-detail-1").style.width = ($(document).width() - 217)+"px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
    // document.getElementById("mySidenav").style.width = "0";
}