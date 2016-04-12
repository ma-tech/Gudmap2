package org.gudmap.models;

import java.util.List;

public class WebBeanModel {
	
	private String id,alias,title,content=null;
	private List<String> totals=null;;
	private boolean selected;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setAlias(String alias){
		this.alias=alias;
	}
	public String getAlias(){
		return alias;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	
	/////////////////////////////////////////////////////////
	public void setTotals(List<String> totals){
		this.totals=totals;
	}
	public List<String> getTotals(){
		return totals;
	}
	
	public void setSelected(boolean selected){
		this.selected=selected;
	}
	
	public boolean getSelected() {
		return selected;
	}
	

}