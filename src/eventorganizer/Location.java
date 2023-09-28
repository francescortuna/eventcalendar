package eventorganizer;

/**
 * @author Frances Cortuna
 * Constants for location
 */

public enum Location {
    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");

    private String buildingName;
    private String campusName;

    /**
     * Create a Location object with the building name and campus name
     * @param buildingName
     * @param campusName
     */
    Location(String buildingName, String campusName) {
        this.buildingName = buildingName;
        this.campusName = campusName;
    }
}
