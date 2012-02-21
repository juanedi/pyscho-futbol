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

	
	function getParticipationId(e) {
		var target = $(e.currentTarget);
		return target.closest('tr').attr('id');
	}
	
	//-----			ELIMINACIÓN DE INVITADOS	-----//
	$('.jRemoveGuest').bind('click', function(e) {
		e.preventDefault(); 
		var participationId = getParticipationId(e);
		psfutbol.api.deleteParticipation(participationId, 
				function() {
					// TODO actualizar sin refrescar?
					document.location = document.location;
				},
				function() {
					alert("error");
				});
	});
	
	
	//-----			ASIGNACIÓN A EQUIPOS		-----//
	
	$('.jAssignTeam').live('click', function(e) {
		e.preventDefault();
		var target = $(e.currentTarget);
		var participationId = getParticipationId(e);
		var teamA = target.siblings('select').val();
		psfutbol.api.setTeam(participationId, teamA,
				function(){
					var td = target.closest('td');
					td.removeClass('jPendingAssignment');
					td.empty();
					var content = $('.jTeamDisplay.jTemplate').clone().removeClass('jTemplate');
					var team = teamA == "true" ? "A" : "B";
					content.prepend(team);
					td.append(content);
					$("#jPlayers").trigger("update");
				},
				function() {
					alert("error");
				});
	});

	$('.jLeaveTeam').live('click', function(e) {
		e.preventDefault();
		var target = $(e.currentTarget);
		var participationId = getParticipationId(e);
		psfutbol.api.clearTeam(participationId, 
				function() {
					var td = target.closest('td');
					td.addClass('jPendingAssignment');
					td.empty();
					var content = $('.jTeamSelect.jTemplate').clone().removeClass('jTemplate');
					td.append(content);
					$("#jPlayers").trigger("update");
				},
				function() {
					alert("error");
				});
	});
	
}); 
