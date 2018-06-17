package application;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"target_machine_id",
"dropoff_machine_id",
"target_machine_detail",
"dropoff_machine_detail"
})
public class CustomAttributes {

@JsonProperty("target_machine_id")
private String targetMachineId;
@JsonProperty("dropoff_machine_id")
private String dropoffMachineId;
@JsonProperty("target_machine_detail")
private TargetMachineDetail targetMachineDetail;
@JsonProperty("dropoff_machine_detail")
private DropoffMachineDetail dropoffMachineDetail;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public CustomAttributes() {
}

/**
* 
* @param targetMachineId
* @param dropoffMachineDetail
* @param targetMachineDetail
* @param dropoffMachineId
*/
public CustomAttributes(String targetMachineId, String dropoffMachineId, TargetMachineDetail targetMachineDetail, DropoffMachineDetail dropoffMachineDetail) {
super();
this.targetMachineId = targetMachineId;
this.dropoffMachineId = dropoffMachineId;
this.targetMachineDetail = targetMachineDetail;
this.dropoffMachineDetail = dropoffMachineDetail;
}

@JsonProperty("target_machine_id")
public String getTargetMachineId() {
return targetMachineId;
}

@JsonProperty("target_machine_id")
public void setTargetMachineId(String targetMachineId) {
this.targetMachineId = targetMachineId;
}

@JsonProperty("dropoff_machine_id")
public String getDropoffMachineId() {
return dropoffMachineId;
}

@JsonProperty("dropoff_machine_id")
public void setDropoffMachineId(String dropoffMachineId) {
this.dropoffMachineId = dropoffMachineId;
}

@JsonProperty("target_machine_detail")
public TargetMachineDetail getTargetMachineDetail() {
return targetMachineDetail;
}

@JsonProperty("target_machine_detail")
public void setTargetMachineDetail(TargetMachineDetail targetMachineDetail) {
this.targetMachineDetail = targetMachineDetail;
}

@JsonProperty("dropoff_machine_detail")
public DropoffMachineDetail getDropoffMachineDetail() {
return dropoffMachineDetail;
}

@JsonProperty("dropoff_machine_detail")
public void setDropoffMachineDetail(DropoffMachineDetail dropoffMachineDetail) {
this.dropoffMachineDetail = dropoffMachineDetail;
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