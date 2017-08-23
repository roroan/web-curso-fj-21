<%@ attribute name="id" required="true" %>
<%@ attribute name="value" type="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<input type="text" id="${id}" name="${id}" value="<fmt:formatDate value="${value}" pattern="dd/MM/yyyy" />" />

<script>
	$("#${id}").datepicker({changeMonth: true,
							changeYear: true,
							dateFormat: 'dd/mm/yy',
							showOn: "button",
							buttonImage: "imagens/calendar.gif",
							buttonImageOnly: true,
							buttonText: "Selecione uma data"
							});
</script>