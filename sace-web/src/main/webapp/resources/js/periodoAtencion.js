$(document).ready(function(){
    $("#periodoAtencion").validate({
    	onfocusout : function(el){
    		$(el).valid();
    	},
        rules: {
     		'periodoAtencion:cmbSede': {
         		required : true
        	},        	
        	
     		'periodoAtencion:cmbConvocatoria': {
         		required : true
        	},
     		'periodoAtencion:diaMin': {
         		required : true,
         		digits: true
        	},        	
     		'periodoAtencion:cboMesMin': {
         		required : true
        	},
        	'periodoAtencion:diaMax': {
         		required : true,
         		digits: true
        	},        	
     		'periodoAtencion:cboMesMax': {
         		required : true
        	},   
     		/*'periodoAtencion:hra1': {
         		required : true,
         		digits: true
        	},        	
     		'periodoAtencion:min1': {
         		required : true
        	},
        	'periodoAtencion:hra2': {
         		required : true,
         		digits: true
        	},        	
     		'periodoAtencion:min2': {
         		required : true
        	},*/        	
    	}	
	});
});	