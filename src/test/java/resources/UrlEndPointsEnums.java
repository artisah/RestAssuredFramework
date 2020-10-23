package resources;

public enum UrlEndPointsEnums {

    ADDPLACEAPI("/maps/api/place/add/json"),
    GETPLACEAPI("/maps/api/place/get/json"),
    DELETEPLACEAPI("/maps/api/place/delete/json");

    private String urlEndPoint;

    UrlEndPointsEnums(String urlEndPoint) {
        this.urlEndPoint = urlEndPoint;
    }

    public String getUrlEndPoint() {
        return urlEndPoint;
    }
}
