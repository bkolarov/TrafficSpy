<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
		<style type="text/css">
			body {
				margin: 0px;
				padding: 0px;
				color: #666;
				font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
				font-size: 14px;
				line-height: 1.5em;
				background: #22b14c;
				background-position: top;
			}
			
			#wrapper{
				margin-left:auto;
				margin-right: auto;
				margin-top: 80px;
				margin-bottom: 20px
				overflow: hidden;
				clear: both;
				width: 80%;
			}
			
			#map{
				padding-top:600px;
				width: 100%;
				background-color: #FFFFFF;
				clear: both;
			}
		</style>
	
		<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="http://raw.githubusercontent.com/Crash-ID/TrafficSpy/master/Server/TrafficSpyService/src/main/webapp/WEB-INF/jsp/js/map.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<h1>TrafficSpy</h1>
			<div id="map">
	
			</div>
		</div>
	</body>

</html>