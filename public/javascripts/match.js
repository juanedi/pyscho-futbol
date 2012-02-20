$(document).ready(function() {

	//-----			SORT DE LA TABLA		-----//
	
	$("#jPlayers select").bind('change', function(e) {
		$("#jPlayers").trigger("update");
	});
	
    $("#jPlayers").tablesorter({
		textExtraction: function(node) {
			// para hacer que los jugadores libres aparezcan al 
			// final de cada equipo cuando se ordena por equipo.
			if ($(node).hasClass("jPendingAssignment")) {
				var team = $(node).find('select').val();
				return team + team;
			} else {
				return node.innerHTML; 
			}
		}
    });
    
    $("#jPlayers th").wrapInner("<a href='#' />");
    $("#jPlayers th a").click(function(e) {
    	e.preventDefault();
    });
	
    
    
    //-----			CANCELACION DEL PARTIDO		-----//
    
	$('#jCancelMatch').bind('submit', function(e){
		e.preventDefault();
		var ok = confirm("¿Seguro que querés cancelar el partido?");
		if (ok) {
			this.submit();
		}
    });

	
	
	//-----			ASIGNACIÓN A EQUIPOS		-----//
	
	function assignTeam(participationId, team, success) {
		var matchId = $('.matchDetails').attr('id');
		var url = psfutbol.api.setTeam({matchId: matchId, participationId: participationId, team: team});
		console.log(url);
	    $.ajax({
	        type: 'POST',
	        url: url,
	        timeout: 10000,
	        complete: function(xmlHttpRequest, textStatus) {
	        	var status = xmlHttpRequest.status;
		        if(status == 202) {
		            success();
		        } else {
		        	// TODO manejo de error.
		        	alert("error!");
		        }
	        }
	    });
	}
	
	$('.jAssignTeam').live('click', function(e) {
		e.preventDefault();
		var target = $(e.currentTarget);
		var participationId = target.closest('tr').attr('id');
		var selection = target.siblings('select').val();
		assignTeam(participationId, selection, function() {
			var td = target.closest('td');
			td.removeClass('jPendingAssignment');
			td.empty();
			var content = $('.jTeamDisplay.jTemplate').clone().removeClass('jTemplate');
			content.prepend(selection);
			td.append(content);
		});
	});

	$('.jLeaveTeam').live('click', function(e) {
		e.preventDefault();
		var target = $(e.currentTarget);
		var participationId = target.closest('tr').attr('id');
		assignTeam(participationId, null, function() {
			var td = target.closest('td');
			td.addClass('jPendingAssignment');
			td.empty();
			var content = $('.jTeamSelect.jTemplate').clone().removeClass('jTemplate');
			td.append(content);
		});
	});
	
}); 
