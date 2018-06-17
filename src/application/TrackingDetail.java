package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"status",
"origin_status",
"agency",
"datetime"
})
public class TrackingDetail {

@JsonProperty("status")
private String status;
@JsonProperty("origin_status")
private String originStatus;
@JsonProperty("agency")
private Object agency;
@JsonProperty("datetime")
private String datetime;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public TrackingDetail() {
}

/**
* 
* @param originStatus
* @param status
* @param agency
* @param datetime
*/
public TrackingDetail(String status, String originStatus, Object agency, String datetime) {
super();
this.status = status;
this.originStatus = originStatus;
this.agency = agency;
this.datetime = datetime;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("origin_status")
public String getOriginStatus() {
return originStatus;
}

@JsonProperty("origin_status")
public void setOriginStatus(String originStatus) {
this.originStatus = originStatus;
}

@JsonProperty("agency")
public Object getAgency() {
return agency;
}

@JsonProperty("agency")
public void setAgency(Object agency) {
this.agency = agency;
}

@JsonProperty("datetime")
public String getDatetime() {
return datetime;
}

@JsonProperty("datetime")
public void setDatetime(String datetime) {
	
	
    try {
    	String string = datetime;
    	LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	String formattedString = date.format(formatter);
    	this.datetime = formattedString;
    } catch (DateTimeParseException e) {
    	this.datetime = datetime;
    }

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