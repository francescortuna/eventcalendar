package eventorganizer;

/**
 * Constants for location of Event
 * @author Frances Cortuna
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
     * Constructor for location constant with the building name and campus name as properties
     * @param buildingName
     * @param campusName
     */
    Location(String buildingName, String campusName) {
        this.buildingName = buildingName;
        this.campusName = campusName;
    }

    /**
     * Gets building name
     * @return Building name
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Gets campus name
     * @return Campus name
     */
    public String getCampusName() {
        return campusName;
    }
}
