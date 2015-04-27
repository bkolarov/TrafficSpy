var map;

function initiate_geolocation() {  
    navigator.geolocation.getCurrentPosition(handle_geolocation_query);  
}  

function handle_geolocation_query(position) {
	var lng = position.coords.longitude;
	var lat = position.coords.latitude;

	var mapOptions = {
	  center: new google.maps.LatLng(lat, lng),
	  zoom: 13
	};
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	
	getMarkers();
}

function getMarkers() {
	$.ajax({
		 url:"http://ec2-52-28-51-57.eu-central-1.compute.amazonaws.com:8181/markers",
		 success:function(json) {
			json.forEach(function(obj) { 
			addMarker(obj.latitude, obj.longitude, obj.address); 
		});
		 },
		 error:function(){
			 alert("Error");
		 }      
	});


}

function addMarker(latitude, longitude, address) {
	content = '<p><b>' + address + '</b><p>'
	
	var infowindow = new google.maps.InfoWindow();
	
	myLatlng = new google.maps.LatLng(latitude, longitude);
	
	marker = new google.maps.Marker({
		position: myLatlng,
  	});
	marker.setMap(map);
	
	google.maps.event.addListener(marker,'click', (
		function(marker,content,infowindow){ 
			return function() {
				infowindow.setContent(content);
				infowindow.open(map,marker);
			};
		})(marker,content,infowindow));  	
}

google.maps.event.addDomListener(window, 'load', initiate_geolocation);