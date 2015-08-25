$(function() {
	$("#sub_btn").click(function() {
		load("/report/sumtotal.action", {
			startDate : $("#start_date").val(),
			endDate : $("#end_date").val()
		}, $("#main"), null);
	});
});