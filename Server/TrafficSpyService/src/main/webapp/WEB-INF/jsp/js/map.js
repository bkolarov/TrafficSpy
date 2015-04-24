jQuery(window).ready(function(){  
	        initiate_geolocation();  
	    });  
	    function initiate_geolocation() {  
	        navigator.geolocation.getCurrentPosition(handle_geolocation_query);  
	    }  
	    function handle_geolocation_query(position){
	    var lng = position.coords.longitude;
	    var lat = position.coords.latitude;

	        var mapOptions = {
	          center: new google.maps.LatLng(lat, lng),
	          zoom: 16
	        };
	        var map = new google.maps.Map(document.getElementById("map"),
	            mapOptions);
	      }
	      google.maps.event.addDomListener(window, 'load', initialize);  
