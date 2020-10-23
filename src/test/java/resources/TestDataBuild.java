package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language , String address ) {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("https://rahulshettyacademy.com");
        addPlace.setName(name);

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        return addPlace;
    }
}
