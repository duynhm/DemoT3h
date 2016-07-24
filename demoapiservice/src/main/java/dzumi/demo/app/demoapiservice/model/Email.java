package dzumi.demo.app.demoapiservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Email {

@SerializedName("id")
@Expose
private Long id;
@SerializedName("content")
@Expose
private String content;
@SerializedName("title")
@Expose
private String title;

/**
* 
* @return
* The id
*/
public Long getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Long id) {
this.id = id;
}

/**
* 
* @return
* The content
*/
public String getContent() {
return content;
}

/**
* 
* @param content
* The content
*/
public void setContent(String content) {
this.content = content;
}

/**
* 
* @return
* The title
*/
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
public void setTitle(String title) {
this.title = title;
}

}