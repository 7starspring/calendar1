<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Calendar</title>
<!-- 
경로를 환경에 맞게 조정합니다.
 -->
<link href='assets/css/calendar/fullcalendar.css' rel='stylesheet' />
<link href='assets/css/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='assets/js/default/jquery.min.js'></script>
<script src='assets/js/default/jquery-ui.custom.min.js'></script>
<script src='assets/js/calendar/fullcalendar.js'></script>
<script>

	$(function() {
		
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var sourceData = [
			{
				title: 'All Day Event',
				start: new Date(y, m, 1)
			},
			{
				title: 'Long Event',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2)
			},
			{
				title: 'Meeting',
				start: new Date(y, m, d, 10, 30),
				allDay: false
			},
			{
				title: 'Click for Google',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://google.com/'
			}
		];
		
		// #1 test
		getData();
		
		// #2 test
		//getData(sourceData);
		
	});
	
	// 아담쇼의 풀캘린더의 시작
	function makeCalendar(eventData){
		//리프레쉬를 위한 기존 정보 삭제
		$('#calendar').fullCalendar('destroy');
		
		var calendar = $('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) { // 클릭 시 이벤트 발생
				var title = prompt('Event Title:');
				/*
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				*/
				calendar.fullCalendar('unselect');
				
				createData();
			},
			editable: true,
			events: eventData // 캘린더의 표시할 이벤트 정보를 넘겨 줍니다.
		});
	}
	
	function createData(){
		// 입력할 정보를 form으로 구성합니다.
		var params = $('#frm').serialize();
		$.post("calendar.do?method=create",
			params,
			function(data){
				console.log("createData() called! returned value >>> \n"+data)
				getData(data);
			}
		);
	}
	
	// 처음 로딩 시 data값은 없음
	function getData(data){
		
		console.log("1. data >>> \n" + data);
		console.log("2. jsonList >>> \n" + '${jsonList}');
		
		var sourceData = data || '${jsonList}';
		console.log("3. sourceData >>> \n" + sourceData);
		
		if($.type(sourceData)=="array") {
			makeCalendar(sourceData);
		} else { // String
			makeCalendar(jsonToObject(sourceData));
		}
		
    }
	
	function jsonToObject(data){
	
		var objData=JSON.parse(data);
		for(var idx=0; idx < objData.length; idx++) {
			objData[idx].title = decodeURI(objData[idx].title); // 한글 처리
			objData[idx].start = stringToDate(objData[idx].start); // 날자 String을 날자 객체로 변경
			
			if(objData[idx].end!=null){
				objData[idx].end = stringToDate(objData[idx].end);
			}
			if(objData[idx].allDay!=null && objData[idx].allDay!="true"){
				objData[idx].allDay=false;
			}
		}
		console.log("jsonToObject() returned >>> \n"+objData);
		return objData;
	}

	function stringToDate(strdate) {
		var year = Number(strdate.substring(0,4));
		var mon = Number(strdate.substring(4,6))-1;
		var day = Number(strdate.substring(6,8));
		var hh = Number(strdate.substring(8,10));
		var mi = Number(strdate.substring(10,12));
		return new Date(year,mon,day,hh,mi);
	}
</script>
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	}

	#calendar {
		width: 900px;
		margin: 0 auto;
	}

</style>
</head>
<body>

<div id='calendar'></div>
<div id='dataBox'>
<form id="frm">
	<input type="text" name="title" value="test"><br>
	<input type="text" name="start" value="201312010700"><br>
</form>
</div>

</body>
</html>