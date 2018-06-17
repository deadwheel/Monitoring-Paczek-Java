
package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
"tracking_number",
"type",
"service",
"status",
"custom_attributes",
"tracking_details",
"expected_flow",
"created_at",
"updated_at"
})
public class InpostPackage {

@JsonProperty("tracking_number")
private String trackingNumber;
@JsonProperty("type")
private String type;
@JsonProperty("service")
private String service;
@JsonProperty("status")
private String status;
@JsonProperty("custom_attributes")
private CustomAttributes customAttributes;
@JsonProperty("tracking_details")
private List<TrackingDetail> trackingDetails = null;
@JsonProperty("expected_flow")
private List<Object> expectedFlow = null;
@JsonProperty("created_at")
private String createdAt;
@JsonProperty("updated_at")
private String updatedAt;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public InpostPackage() {
}

/**
* 
* @param updatedAt
* @param trackingNumber
* @param status
* @param createdAt
* @param trackingDetails
* @param service
* @param type
* @param expectedFlow
* @param customAttributes
*/
public InpostPackage(String trackingNumber, String type, String service, String status, CustomAttributes customAttributes, List<TrackingDetail> trackingDetails, List<Object> expectedFlow, String createdAt, String updatedAt) {
super();
this.trackingNumber = trackingNumber;
this.type = type;
this.service = service;
this.status = status;
this.customAttributes = customAttributes;
this.trackingDetails = trackingDetails;
this.expectedFlow = expectedFlow;
this.createdAt = createdAt;
this.updatedAt = updatedAt;
}

@JsonProperty("tracking_number")
public String getTrackingNumber() {
return trackingNumber;
}

@JsonProperty("tracking_number")
public void setTrackingNumber(String trackingNumber) {
this.trackingNumber = trackingNumber;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
    if(type.equals("inpost_locker_standard"))

        this.type = "Przesy³ka paczkomatowa standardowa";

    else if(type.equals( "inpost_locker_allegro"))

        this.type = "Przesy³ka paczkomatowa Allegro Paczkomaty InPost";

    else if(type.equals( "inpost_locker_pass_thru"))

        this.type = "Przesy³ka paczkomatowa Podaj Dalej";

    else if(type.equals( "inpost_letter_allegro"))

        this.type = "Minipaczka Allegro InPost";

    else if(type.equals( "inpost_courier_allegro"))

        this.type = "Przesy³ka kurierska Allegro Kurier InPost";

    else if(type.equals( "inpost_courier_standard"))

        this.type = "Przesy³ka kurierska standardowa";

    else if(type.equals( "inpost_courier_express_1000"))

        this.type = "Przesy³ka kurierska z dorêczeniem do 10:00";

    else if(type.equals( "inpost_courier_express_1200"))

        this.type = "Przesy³ka kurierska z dorêczeniem do 12:00";

    else if(type.equals( "inpost_courier_express_1700"))

        this.type = "Przesy³ka kurierska z dorêczeniem do 17:00";


    else if(type.equals( "inpost_courier_palette"))

        this.type = "Przesy³ka kurierska Paleta Standard";

    else if(type.equals( "inpost_courier_local_standard"))

        this.type = "Przesy³ka kurierska Lokalna Standardowa";

    else if(type.equals( "inpost_courier_local_express"))

        this.type = "Przesy³ka kurierska Lokalna Expresowa";

    else if(type.equals( "inpost_courier_local_super_express"))

        this.type = "Przesy³ka kurierska Lokalna Super Expresowa";

    else

        this.type = "Nieokreœlony";
}

@JsonProperty("service")
public String getService() {
return service;
}

@JsonProperty("service")
public void setService(String service) {
this.service = service;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("custom_attributes")
public CustomAttributes getCustomAttributes() {
return customAttributes;
}

@JsonProperty("custom_attributes")
public void setCustomAttributes(CustomAttributes customAttributes) {
this.customAttributes = customAttributes;
}

@JsonProperty("tracking_details")
public List<TrackingDetail> getTrackingDetails() {
return trackingDetails;
}

@JsonProperty("tracking_details")
public void setTrackingDetails(List<TrackingDetail> trackingDetails) {
this.trackingDetails = trackingDetails;
}

@JsonProperty("expected_flow")
public List<Object> getExpectedFlow() {
return expectedFlow;
}

@JsonProperty("expected_flow")
public void setExpectedFlow(List<Object> expectedFlow) {
this.expectedFlow = expectedFlow;
}

@JsonProperty("created_at")
public String getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(String createdAt) {
	String string = createdAt;
	LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	String formattedString = date.format(formatter);
	this.createdAt = formattedString;
}

@JsonProperty("updated_at")
public String getUpdatedAt() {
return updatedAt;
}

@JsonProperty("updated_at")
public void setUpdatedAt(String updatedAt) {
	String string = updatedAt;
	LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	String formattedString = date.format(formatter);
	this.updatedAt = formattedString;
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