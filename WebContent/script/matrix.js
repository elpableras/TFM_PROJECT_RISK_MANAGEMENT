	function cambiaValorMatriz(){
		var input = document.getElementById("matrixNumberGreen");
		var valor = input.value;
		var menor = $("table").find("td#amarillo");			
		menor[1].innerHTML = valor;

		var input = document.getElementById("matrixNumberRed");
		var valor = input.value;
		var mayor = $("table").find("td#amarillo");				
		mayor[2].innerHTML = valor;
		fillMatrix();		 
	}  

	function fillMatrix(){		
		trP = $("tbody#tbodyPlan").find("tr");
		for (var i = 0; i <= 4; i++) {
			tdP = $(trP[i+2]).find("td");			
			valueP = tdP[1].innerHTML;												 
			for (var j = 0; j <= 4; j++) {
				trI = $("tbody#tbodyPlan").find("tr");		
				tdI = $(trI[7]).find("td");											
				valueI = tdI[j+2].innerHTML;

				value = (valueP/100) * (valueI/100);				
				tdP[2+j].innerHTML = value.toFixed(2);
				changeColor(value.toFixed(2),tdP[2+j]);																														  				 
			}			  
		}									
	}

	function changeColor(value,position){
		tdv = $("tr.verde").find("td");		
		inputv = document.getElementById("matrixNumberGreen").value;		
		if((value >= tdv[1].innerHTML) && value < inputv ){
			position.setAttribute('id', 'verde');
		}
		tda = $("tr.amarillo").find("td");						
		if((value >= tda[1].innerHTML) && value < tda[2].innerHTML ){
			position.setAttribute('id', 'amarillo');
		}
		tdr = $("tr.rojo").find("td");
		inputr = document.getElementById("matrixNumberRed").value;
		if((value >= inputr) && value < tdr[2].innerHTML ){
			position.setAttribute('id', 'rojo');
		}		
	} 