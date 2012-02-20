$(document).ready(function() {
	
        $('#jCancelMatch').bind('submit', function(e){
        	e.preventDefault();
        	var ok = confirm("¿Seguro que querés cancelar el partido?");
        	if (ok) {
        		this.submit();
        	}
        });
        
}); 
