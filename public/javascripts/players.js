$(document).ready(function() 
    { 
        $("#jPlayerStats").tablesorter({sortList: [[0,0]]});
        $("#jPlayerStats th").wrapInner("<a href='#' />");
        $("#jPlayerStats th a").click(function(e) {
        	e.preventDefault();
        });
    } 
); 
