package application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"title",
"description"
})

public class InPostStatus {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;

	/**
	* No args constructor for use in serialization
	*
	*/
	public InPostStatus() {
	}

	/**
	*
	* @param title
	* @param description
	* @param name
	*/
	public InPostStatus(String name, String title, String description) {
	super();
	this.name = name;
	this.title = title;
	this.description = description;
	}

	@JsonProperty("name")
	public String getName() {
	return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
	this.name = name;
	}

	@JsonProperty("title")
	public String getTitle() {
	return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
	this.title = title;
	}

	@JsonProperty("description")
	public String getDescription() {
	return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
	this.description = description;
	}

}
