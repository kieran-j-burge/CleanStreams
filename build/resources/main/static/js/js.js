$( document ).ready(function() {



});


$(document).ready(function () {
    htmlbodyHeightUpdate()
    $( window ).resize(function() {
        htmlbodyHeightUpdate()
    });
    $( window ).scroll(function() {
        height2 = $('.main').height()
        htmlbodyHeightUpdate()
    });

    changeNavActive();

    $("#searchGame").on('keyup', function() {
        var searchText = $('#searchGame').val();
        var searchLen  = searchText.length;

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Please search with text characters');
                $('#searchGame').val("");
            }
            else {
                url = '/search/game/' + searchText;
                $("#resultsBlock").load(url);
            }
        }
        else{
            url ='/games/home_games';
            $("#resultsBlock").load(url);
        }

    });

    $("#searchAdminGame").on('keyup', function() {
        var searchText = $('#searchAdminGame').val();
        var searchLen  = searchText.length;

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Please search with text characters');
                $('#searchAdminGame').val("");
            }
            else {
                url = '/search/admin/game/' + searchText;
                $("#gameReplaceContainer").load(url);
            }
        }
        else{
            // url ='/games/home_games';
            $("#gameReplaceContainer").html("");
        }

    });

    $("#searchAdminLeague").on('keyup', function() {
        var searchText = $('#searchAdminLeague').val();
        var searchLen  = searchText.length;

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Your search string contains illegal characters.');
                $('#searchAdminLeague').val("");
            }
            else {
                url = '/search/admin/league/' + searchText;
                $("#leagueReplaceContainer").load(url);
            }
        }
        else{
            $("#leagueReplaceContainer").html("");

        }

    });





});

function changeNavActive() {
    var title = $('title').text().toLowerCase();
    $('.nav-'+title+'').addClass('active');
}

function htmlbodyHeightUpdate(){
    var height3 = $( window ).height()
    var height1 = $('.nav').height()+50
    height2 = $('.main').height()
    if(height2 > height3){
        $('html').height(Math.max(height1,height3,height2)+10);
        $('body').height(Math.max(height1,height3,height2)+10);
    }
    else
    {
        $('html').height(Math.max(height1,height3,height2));
        $('body').height(Math.max(height1,height3,height2));
    }

}

$(document).on('click', '.admin-league', function(){
    var id = $(this).find('.league-id').text();
    $('#leagueId').text(id);
    $("#leagueReplaceContainer").html("");

});

$(document).on('click', '.admin-game', function(){
    var id = $(this).find('.game-id').text();
    $('#gameId').text(id);
    $("#gameReplaceContainer").html("");

});

$(document).on('click', '#uploadLeagueStream', function(){
    var id = $('#leagueId').text();
    var g_url = $('#leagueUrl').val();

    g_url = g_url.replace(/\./g,'£');
    g_url = g_url.replace(/\//g,'`');

    var url = "/admin/stream-upload/"+id+"/"+g_url;

    $.post(url, function (data, status) {
        alert('posted');
    });

});


$(document).on('click', '#uploadGameStream', function(){
    var id = $('#gameId').text();
    var g_url = $('#gameUrl').val();

    g_url = g_url.replace(/\./g,'£');
    g_url = g_url.replace(/\//g,'`');

    var url = "/admin/stream-upload/"+id+"/"+g_url;
    $.post(url, function (data, status) {
        alert('posted');
    });

});






