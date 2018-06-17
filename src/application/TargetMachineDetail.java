package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"href",
"name",
"opening_hours",
"location_description",
"location",
"address",
"type"
})
public class TargetMachineDetail {

@JsonProperty("href")
private String href;
@JsonProperty("name")
private String name;
@JsonProperty("opening_hours")
private Object openingHours;
@JsonProperty("location_description")
private String locationDescription;
@JsonProperty("location")
private Location location;
@JsonProperty("address")
private Address address;
@JsonProperty("type")
private List<String> type = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public TargetMachineDetail() {
}

/**
* 
* @param address
* @param location
* @param locationDescription
* @param openingHours
* @param name
* @param type
* @param href
*/
public TargetMachineDetail(String href, String name, Object openingHours, String locationDescription, Location location, Address address, List<String> type) {
super();
this.href = href;
this.name = name;
this.openingHours = openingHours;
this.locationDescription = locationDescription;
this.location = location;
this.address = address;
this.type = type;
}

@JsonProperty("href")
public String getHref() {
return href;
}

@JsonProperty("href")
public void setHref(String href) {
this.href = href;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("opening_hours")
public Object getOpeningHours() {
return openingHours;
}

@JsonProperty("opening_hours")
public void setOpeningHours(Object openingHours) {
this.openingHours = openingHours;
}

@JsonProperty("location_description")
public String getLocationDescription() {
return locationDescription;
}

@JsonProperty("location_description")
public void setLocationDescription(String locationDescription) {
this.locationDescription = locationDescription;
}

@JsonProperty("location")
public Location getLocation() {
return location;
}

@JsonProperty("location")
public void setLocation(Location location) {
this.location = location;
}

@JsonProperty("address")
public Address getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(Address address) {
this.address = address;
}

@JsonProperty("type")
public List<String> getType() {
return type;
}

@JsonProperty("type")
public void setType(List<String> type) {
this.type = type;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}