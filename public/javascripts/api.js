var psfutbol;
if(!psfutbol) throw new Error('psfutbol has not been loaded');
if(!psfutbol.uriFactory) throw new Error('psfutbol uriFactory has not been loaded');

function evalResponse(successCode, successCallback, errorCallback) {
	return function(xmlHttpRequest) {
        var status = xmlHttpRequest.status;
        if (status == successCode) {
        	successCallback();
        } else {
        	errorCallback();
        }
	}
}
function ajaxCall(url, method, successCode, successCallback, errorCallback) {
    $.ajax({
        type: method,
        url: url,
        timeout: 10000,
        complete: evalResponse(successCode, successCallback, errorCallback)
    });        	
}

psfutbol.api = {
		
        setTeam: function(participationId, teamA, success, error) {
        	var url = psfutbol.uriFactory.setTeam({participationId: participationId, teamA: teamA});
        	ajaxCall(url, 'PUT', 202, success, error);
        },
        
        
        clearTeam: function(participationId, success, error) {
            var url = psfutbol.uriFactory.clearTeam({participationId: participationId});
            ajaxCall(url, 'DELETE', 202, success, error);
        }
}