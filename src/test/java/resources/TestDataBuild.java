package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlacePojo AddPlacePayload(String name,String language, String address) {
		
		AddPlacePojo p = new AddPlacePojo();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shop");
		
		p.setTypes(list);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		return p;
		
	}
	
	public String deletePlacePayload(String PlaceId) {
		
		return "{\\r\\n    \\\"place_id\\\":\\\""+PlaceId+"\\\"\\r\\n}";
		
	}

}
