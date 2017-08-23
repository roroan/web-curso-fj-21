<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<html>
	<head>
		<link type="text/css" href="resources/css/tarefas.css" rel="stylesheet" />
		<link type='text/css' href='css/ui-lightness/jquery-ui-1.8.2.custom.css' rel='stylesheet'/>
		<script type='text/javascript' src='js/jquery-1.4.2.min.js'></script>
		<script type='text/javascript' src='js/jquery-ui-1.8.2.custom.min.js'></script>

		<meta charset="ISO-8859-1">
	</head>


<body>
	<h3>Alterar tarefa - ${tarefa.id}</h3>
	
	<form action="alteraTarefa" method="post">
	
		<input type="hidden" name="id" value="${tarefa.id}" />
	
		Descrição:<br />
		<textarea name="descricao" rows="5" cols="100">${tarefa.descricao}</textarea> 
		<br />

		Finalizado? <input type="checkbox" name="finalizado" value="true" ${tarefa.finalizado? 'checked' : ''} /> <br/>
		
			Data de finalização:
			<br />
				<mytag:campoData id="dataFinalizacao" value="${tarefa.dataFinalizacao.time}" /> 
			<br />
		
		<input type="submit" value="Alterar"/>
			
	</form>
	
</body>
</html>