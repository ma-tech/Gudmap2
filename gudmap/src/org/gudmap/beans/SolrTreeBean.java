package org.gudmap.beans;


import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.gudmap.utils.SolrUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Named (value="solrTreeBean")
@SessionScoped
public class SolrTreeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String solrInput = null;
	private SolrUtil solrUtil;
	private String filter;
	
    @Inject
   	private SolrInsituFilter solrInsituFilter;
	
	public SolrTreeBean(){
		solrUtil = new SolrUtil();
//		insitufilters = new ArrayList<String>();
	}
	
	public void setSolrInsituFilter(SolrInsituFilter filter){
		this.solrInsituFilter = filter;
	}

	public SolrUtil getSolrUtil(){
		return solrUtil;
	}
		
	public String getSolrInput(){
		return solrInput;
	}

	public void setSolrInput(String value){
		this.solrInput = value;
	}
	
	
	public String goSearch() {
		
		getSolrInput();
		
//		getAnchorGeneCount();
		getGeneCount();
		getInsituCount();
		getMicroarrayCount();
		getGenelistCount();
		getTissueCount();
//		getTutorialCount();
		getMouseStrainsCount();
		getImagesCount();
		
		return null;
	}

	public void goListen(ActionEvent event) {
		
		getSolrInput();
		
	}
	
	public String goInsitu() {
		return "/solr/solrInsituTablePage";
	}
	public String goSeries() {
		return "/solr/solrSeriesTablePage";
	}
	public String goSamples() {
		return "/solr/solrSamplesTablePage";
	}
	public String goTissues() {
		return "/solr/solrTissueSummaryTablePage";
	}
	public String goMicroarray() {
		return "/solr/solrMicroarrayTablePage";
	}

	public String getFilter(){
		return filter;
	}

	public void setFilter(String filter){
		this.filter = filter;
	}

	////////////////// GENE ////////////////////////////	
		
	
	public int getGeneCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getGeneCount(solrInput, filters);
	}
	
	public int getGeneCount(ArrayList<String> filters){
		return solrUtil.getGeneCount(solrInput, filters);
	}
	
	
	////////////////// INSITU ////////////////////////////
	
	public int getInsituCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getInsituFilteredCount(solrInput,filters);
	}
	
	public int getInsituCount(String filter){
		
		if (filter != "" || filter != null)	{	
	        String[]ffields = filter.split(":");
	    	filter = ffields[0] + ":" + '"' + ffields[1] + '"';
		}
		return solrUtil.getInsituCount(solrInput, filter);
	}
	
	public int getInsituFilteredCount(ArrayList<String> filter){
		return solrUtil.getInsituFilteredCount(solrInput, filter);
	}
	
	////////////////// MICROARRAY ////////////////////////////
	
	public int getMicroarrayCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getMicroarrayFilteredCount(solrInput, filters);
	}

	public int getMicroarrayCount(String filter){
		return solrUtil.getMicroarrayCount(solrInput, filter);
	}

	////////////////// GENELIST ////////////////////////////
	
	public int getGenelistCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getGenelistCount(solrInput, filters);
	}

	////////////////// TISSUE ////////////////////////////
	
	public int getTissueCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getTissueCount(solrInput, filters);
	}

	////////////////// TUTORIAL ////////////////////////////
	
	public int getTutorialCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getTutorialCount(solrInput, filters);
	}

	////////////////// MOUSESTRAINS ////////////////////////////
	
	public int getMouseStrainsCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getMouseStrainsCount(solrInput, filters);
	}

	////////////////// IMAGES ////////////////////////////
	
	public int getImagesCount(){
		ArrayList<String> filters = new ArrayList<String>();
		filters = solrInsituFilter.getFilters();
		return solrUtil.getImagesCount(solrInput, filters);
	}

	public ArrayList<String> getTop5Genes(){
		if (solrInput == "" || solrInput == null)
			return null;
		
		ArrayList<String> topFive =  solrUtil.getTop5Genes(solrInput);	
		return topFive;
	}

	public ArrayList<String> getTop5Insitu(){
		if (solrInput == "" || solrInput == null)
			return null;

		ArrayList<String> topFive =  solrUtil.getTop5Insitu(solrInput);
		return topFive;
	}

	public ArrayList<String> getTop5Microarray(){
		if (solrInput == "" || solrInput == null)
			return null;

		ArrayList<String> topFive = solrUtil.getTop5Microarray(solrInput);					
		return topFive;
	}

	public LinkedHashMap<String,String> getTop5Tissues(){
		if (solrInput == "" || solrInput == null)
			return null;
		
		LinkedHashMap<String,String> topFive = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String> map =  solrUtil.getTop5Tissues(solrInput);
		List<String> keys = new ArrayList<String>(map.keySet());
		int count = keys.size();
		for (int i = 0; i < count; i++){
			String key = keys.get(i);
//			topFive.put(key+", ", map.get(key));
			topFive.put(key, map.get(key));
		}
		return topFive;
	}
	
	public ArrayList<String> getTop5MouseStrains(){
		if (solrInput == "" || solrInput == null)
			return null;

		ArrayList<String> topFive =  solrUtil.getTop5MouseStrains(solrInput);
		return topFive;
	}
	
	public LinkedHashMap<String,String> getTop5Images(){
		if (solrInput == "" || solrInput == null)
			return null;

		LinkedHashMap<String,String> topFive = solrUtil.getTop5Images(solrInput);
		
		return topFive;
	}

	public LinkedHashMap<String,String> getTop5Tutorials(){
		if (solrInput == "" || solrInput == null)
			return null;
		
		LinkedHashMap<String,String> map =  new LinkedHashMap<String,String>(); 
		int overviewCount = solrUtil.getTutorialOverviewCount(solrInput);
		int mrsCount = solrUtil.getTutorialDevMRSCount(solrInput);
		int musCount = solrUtil.getTutorialDevMUSCount(solrInput);
//		int totalCount = overviewCount + mrsCount + musCount;
		
		if (overviewCount > 0){
			map.put("Overview","http://www.gudmap.org/About/Tutorial/Overview.html");				
		}
		if (mrsCount > 0){
			map.put("Reproductive Development","http://www.gudmap.org/About/Tutorial/DevMRS.html");
		}
		if (musCount > 0){
			map.put("Urinary Development","http://www.gudmap.org/About/Tutorial/DevMUS.html");
		}
		
		return map;
	}
	
	
	public Map<String,String> getAssayTypeList(){
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		String label = "ISH (" + this.getInsituCount("ASSAY_TYPE:ISH") + ")";
		map.put(label, "ASSAY_TYPE:ISH");
		
		label = "IHC (" + this.getInsituCount("ASSAY_TYPE:IHC") + ")";
		map.put(label, "ASSAY_TYPE:IHC");
		
		label = "TG (" + this.getInsituCount("ASSAY_TYPE:TG") + ")";
		map.put(label, "ASSAY_TYPE:TG");

		
		return map;
	}

	
	public Map<String,String> getFocusGroupList(){
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		String label = "Metanephros (" + this.getInsituCount("FOCUS_GROUPS:metanephros") + ")";
		map.put(label, "FOCUS_GROUPS:metanephros");
		
		label = "Lower Urinary Tract (" + this.getInsituCount("FOCUS_GROUPS:lower urinary tract") + ")";
		map.put(label, "FOCUS_GROUPS:lower urinary tract");
		
		label = "Male Reproductive System (" + this.getInsituCount("FOCUS_GROUPS:male reproductive system") + ")";
		map.put(label, "FOCUS_GROUPS:male reproductive system");
		
		label = "Female Reproductive System (" + this.getInsituCount("FOCUS_GROUPS:female reproductive system") + ")";
		map.put(label, "FOCUS_GROUPS:female reproductive system");
		
		label = "Early Genitourinary System (" + this.getInsituCount("FOCUS_GROUPS:early genitourinary system") + ")";
		map.put(label, "FOCUS_GROUPS:early_genitourinary_system");

		
		return map;
	}

	public Map<String,String> getPlatformList(){
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		String label = "GPL81 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL81") + ")";
		map.put(label, "PLATFORM_GEO_ID:GPL81");
		
		label = "GPL339 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL339") + ")";
		map.put(label, "PLATFORM_GEO_ID:GPL339");
		
		label = "GPL1261 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL1261") + ")";
		map.put(label, "PLATFORM_GEO_ID:GPL1261");
		
		label = "GPL6246 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL6246") + ")";
		map.put(label, "PLATFORM_GEO_ID:GPL6246");

		
		return map;
	}

	
	public Map<String,String> getTheilerStageList(){
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		String label;

		for (int i = 17;  i<29; i++){
			label = "TS" + i+  " (" + this.getInsituCount("THEILER_STAGE:TS" + i) + ")";
			map.put(label, "THEILER_STAGE:TS" + i);
		}

		
		return map;
	}
	
}
