$(function(){
	
	var i = 5;    	
	var tab = 24;

        $('.agregarFila').live('click', function (){
        	tr = document.createElement("tr");
    		if(i%2==0){            	
    			tr.setAttribute('class','odd-row');
    	    }
    		td1 = document.createElement("td");        	
    		td2 = document.createElement("td");        		
    		td3 = document.createElement("td");
    		td4 = document.createElement("td");
    		td5 = document.createElement("td");
    		td6 = document.createElement("td");
    		td7 = document.createElement("td");        	
    		input1 = document.createElement("input");
    		input2 = document.createElement("input");
    		textarea1 = document.createElement("textarea");
    		textarea2 = document.createElement("textarea");
    		textarea3 = document.createElement("textarea");
    		textarea4 = document.createElement("textarea");
    		textarea5 = document.createElement("textarea");         	      	
    		td2.innerHTML = '<s:text name="plan.9.3"/>: ';
    		td3.innerHTML = '<s:text name="plan.9.4"/>: ';
    		td4.innerHTML = '<s:text name="plan.9.4"/>: ';
    		td5.innerHTML = '<s:text name="plan.9.4"/>: ';
    		td6.innerHTML = '<s:text name="plan.9.4"/>: ';
    		td7.innerHTML = '<s:text name="plan.9.4"/>: ';
    		input1.setAttribute('type', 'button');        	
    		input1.setAttribute('id', 'buttonDelete');
    		input1.setAttribute('value', ' ');
    		input1.setAttribute('class', 'eliminarFila');        	        	        	
    		input1.setAttribute('tabindex', tab);
    		tab++;    		
    		input2.setAttribute('type', 'text');
    		input2.setAttribute('placeholder', ' ');
    		input2.setAttribute('id', 'planObjetive');    		    		
    		input2.setAttribute('name', 'objetivo');
    		input2.setAttribute('required', '');
    		input2.setAttribute('tabindex', tab);    		
    		tab++;    		       	    					
    		textarea1.setAttribute('cols', '20');
    		textarea1.setAttribute('rows', '4');
    		textarea1.setAttribute('cols', '20');
    		textarea1.setAttribute('placeholder', ' ');
    		textarea1.setAttribute('id', 'planDefine');
    		textarea1.setAttribute('name', 'descripcion');
    		textarea1.setAttribute('required', '');
    		textarea1.setAttribute('tabindex', tab);    		    		
    		tab++;
    		textarea2.setAttribute('cols', '20');
    		textarea2.setAttribute('rows', '4');
    		textarea2.setAttribute('cols', '20');
    		textarea2.setAttribute('placeholder', ' ');
    		textarea2.setAttribute('id', 'planDefine');
    		textarea2.setAttribute('name', 'descripcion');
    		textarea2.setAttribute('required', '');
    		textarea2.setAttribute('tabindex', tab);    		    		
    		tab++;
    		textarea3.setAttribute('cols', '20');
    		textarea3.setAttribute('rows', '4');
    		textarea3.setAttribute('cols', '20');
    		textarea3.setAttribute('placeholder', ' ');
    		textarea3.setAttribute('id', 'planDefine');
    		textarea3.setAttribute('name', 'descripcion');
    		textarea3.setAttribute('required', '');
    		textarea3.setAttribute('tabindex', tab);    		    		
    		tab++;
    		textarea4.setAttribute('cols', '20');
    		textarea4.setAttribute('rows', '4');
    		textarea4.setAttribute('cols', '20');
    		textarea4.setAttribute('placeholder', ' ');
    		textarea4.setAttribute('id', 'planDefine');
    		textarea4.setAttribute('name', 'descripcion');
    		textarea4.setAttribute('required', '');
    		textarea4.setAttribute('tabindex', tab);    		    		
    		tab++;
    		textarea5.setAttribute('cols', '20');
    		textarea5.setAttribute('rows', '4');
    		textarea5.setAttribute('cols', '20');
    		textarea5.setAttribute('placeholder', ' ');
    		textarea5.setAttribute('id', 'planDefine');
    		textarea5.setAttribute('name', 'descripcion');
    		textarea5.setAttribute('required', '');
    		textarea5.setAttribute('tabindex', tab);
    		tab++;   		    		
    		i++;
    		td1.appendChild(input1);        	
    		td2.appendChild(input2);        		
    		td3.appendChild(textarea1);
    		td4.appendChild(textarea2);
    		td5.appendChild(textarea3);
    		td6.appendChild(textarea4);
    		td7.appendChild(textarea5);        	
    		tr.appendChild(td1);        	
    		tr.appendChild(td2);        		
    		tr.appendChild(td3);
    		tr.appendChild(td4);
    		tr.appendChild(td5);
    		tr.appendChild(td6);
    		tr.appendChild(td7);        	
    		tbody = $("table").find("tbody");	        	
        	tbody[0].appendChild(tr);                	                	        	        		      		        	        		      		        		                	        		               	
        });                            
      
        $(".eliminarFila").live('click', function (){ 
            var tr = $(this).closest('tr'); 
            tr.remove(); 
        }); 

        $(".eliminarTabla").live('click', function (){ 
            var tabla = $(this).closest('table'); 
            tabla.remove(); 
        });     
    });


function addRow(){
	
	newtr = document.createElement("tr");
	
	tr = $("tbody#tbodyIden").find("tr:last");
	
	td = $(tr[0]).find("td");
	
	td0 = $(td[0]).clone();
	inputDelete = $(td0[0]).find("input");
	inputDelete[0].setAttribute('value', ' ');
	
	td1 = $(td[1]).clone();	
	inputId = $(td1[0]).find("input");
	valor = inputId[0].value;
	inputId[0].setAttribute('value', parseInt(valor)+1);
		
	
	td2 = $(td[2]).clone();
	textarea = $(td2[0]).find("textarea");
	textarea[0].innerHTML = "";
	textarea[0].removeAttribute('readonly');
	
	td3 = $(td[3]).clone();
	textarea = $(td3[0]).find("textarea");
	textarea[0].innerHTML = "";
	textarea[0].removeAttribute('readonly');
	
	td4 = document.createElement("td");
	input = document.createElement("input");
	input.setAttribute('type', 'text');
	input.setAttribute('id', 'idenRisk');    		    		
	input.setAttribute('name', 'responsable');
	input.setAttribute('required', '');
	input.setAttribute('tabindex', parseInt(textarea[0].getAttribute('tabindex'))+1);
	td4.appendChild(input);
	
	td5 = $(td[5]).clone();
	
	td6 = $(tr[0]).find("td#impacto");
	td6 = td6.clone();
	tdimpactolength = $(td6).length;
	
	td7 = $(td[6+tdimpactolength]).clone();
	input2 = $(td7[0]).find("input");
	input2[0].setAttribute('value', ' ');
	
	td8 = $(td[7+tdimpactolength]).clone();
	td8[0].removeAttribute('id');
		
	td9 = $(td[8+tdimpactolength]).clone();
	textarea2 = $(td9[0]).find("textarea");
	textarea2[0].innerHTML = "";
	
	td10 = $(td[9+tdimpactolength]).clone();
	textarea3 = $(td10[0]).find("textarea");
	textarea3[0].innerHTML = "";
		
	newtr.appendChild(td0[0]);
	newtr.appendChild(td1[0]);	
	newtr.appendChild(td2[0]);
	newtr.appendChild(td3[0]);
	newtr.appendChild(td4);
	newtr.appendChild(td5[0]);
	for(var i=0; i<4; i++){		
		newtr.appendChild(td6[i]);	
	}	
	newtr.appendChild(td7[0]);
	newtr.appendChild(td8[0]);
	newtr.appendChild(td9[0]);
	newtr.appendChild(td10[0]);
	        	        		
	tbody = $("table").find("tbody#tbodyIden");
	tbody[0].appendChild(newtr);       	
}

$(function(){ 
	$("#idenRisk").live('change', function (){
		var tr = $(this).closest('tr');        	
			td = $(tr[0]).find("td#probabilidad");       		        	
		select = $(td[0]).find("select");  
		if(select[0]){
			probabilidad = select[0].value;
			tds = $(tr[0]).find("td");
			impactos = $(tr[0]).find("td#impacto");
			tdimpactolength = $(impactos).length;        	 
			var max = 0;       	       	        	
			for(var i=0;i<tdimpactolength;i++){
				selects = $(impactos[i]).find('select');        		        		       		
				if(parseInt(selects[0].value) > parseInt(max)){
		    		max = selects[0].value;            		
		    	}         			           	
		    }         	 
		    var valorImpacto = (probabilidad/100)*(max/100);		    
			input = $(tds[6+tdimpactolength]).find("input");			
			input[0].setAttribute('value', valorImpacto);
		
			corte = document.getElementsByClassName("corte");
			var valorCorte = corte[0].innerHTML;			
			if(valorImpacto > valorCorte){
				tdestudio = $(tr[0]).find("td.estudio");				 							
				tdestudio[0].setAttribute('id', 'rojo');			
			}else{
				tdestudio = $(tr[0]).find("td.estudio");							
				tdestudio[0].setAttribute('id', 'verde');
			}
		}	
	});     
});


$(function(){ 
	$("#analiRisk").live('change', function (){
		var tr = $(this).closest('tr');        	
			td = $(tr[0]).find("td#probabilidad");       		        	
		select = $(td[0]).find("select");  
		if(select[0]){
			probabilidad = select[0].value;
			tds = $(tr[0]).find("td");
			impactos = $(tr[0]).find("td#impacto");
			tdimpactolength = $(impactos).length;        	 
			var max = 0;       	       	        	
			for(var i=0;i<tdimpactolength;i++){
				selects = $(impactos[i]).find('select');        		        		       		
				if(parseInt(selects[0].value) > parseInt(max)){
		    		max = selects[0].value;            		
		    	}         			           	
		    }         	 
		    var valorImpacto = (probabilidad/100)*(max/100);
			input = $(tds[1+tdimpactolength]).find("input");
			input[0].setAttribute('value', valorImpacto);
		}	
	});     
});

function addRowIndicator(){
	newtr = document.createElement("tr");	
	tr = $("tbody#tbodyAnal").find("tr:last");	
	
	td = $(tr[0]).find("td");
	
	td0 = document.createElement("td");
	input = document.createElement("input");
	input.setAttribute('type', 'text');
	input.setAttribute('id', 'analiRisk');    		    		
	input.setAttribute('name', 'indicador');
	input.setAttribute('required', '');
	ta = $(td[1]).find("textarea");
	input.setAttribute('tabindex', parseInt(ta[0].getAttribute('tabindex'))+1);
	td0.appendChild(input);
	
	td1 = $(td[1]).clone();
	textarea = $(td1).find("textarea");
	textarea[0].innerHTML = "";
	
	input2 = $(document.getElementById('buttonAdd')).clone();
	input2[0].setAttribute('value', " ");
	input2[0].setAttribute('class', "eliminarFila");
	input2[0].setAttribute('id', "buttonDeleteAnali");
	input2[0].removeAttribute('onclick');
	input2[0].setAttribute('tabindex', parseInt(ta[0].getAttribute('tabindex'))+2);
	
	td0.appendChild(input2[0]);
	
	newtr.appendChild(td0);
	newtr.appendChild(td1[0]);

	tbody = $("table").find("tbody#tbodyAnal");
	tbody[0].appendChild(newtr);
}