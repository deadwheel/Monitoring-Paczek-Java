package application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"href",
"items"
})
public class InPostStatusy {
	
	@JsonProperty("href")
	private String href;
	@JsonProperty("items")
	private List<InPostStatus> items = null;

	/**
	* No args constructor for use in serialization
	*
	*/
	public InPostStatusy() {
	}

	/**
	*
	* @param items
	* @param href
	*/
	public InPostStatusy(String href, List<InPostStatus> items) {
	super();
	this.href = href;
	this.items = items;
	}

	@JsonProperty("href")
	public String getHref() {
	return href;
	}

	@JsonProperty("href")
	public void setHref(String href) {
	this.href = href;
	}

	@JsonProperty("items")
	public List<InPostStatus> getItems() {
	return items;
	}

	@JsonProperty("items")
	public void setItems(List<InPostStatus> items) {
	this.items = items;
	}
	
}
