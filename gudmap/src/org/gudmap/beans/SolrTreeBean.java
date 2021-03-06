package org.gudmap.beans;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.solr.common.SolrDocumentList;
import org.gudmap.utils.SolrUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


@Named (value="solrTreeBean")
@SessionScoped
public class SolrTreeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String solrInput = null;
	private SolrUtil solrUtil;
	private String filter;
	
    @Inject
    private SolrIndexBean solrIndexBean;
	
    @Inject
   	private SolrFilter solrFilter;
    
    @Inject
   	private BiomedAtlasFilter biomedAtlasFilter;
    
	
	public SolrTreeBean(){
		solrUtil = new SolrUtil();
	}
	
	public void setSolrFilter(SolrFilter filter){
		this.solrFilter = filter;
	}

	public void setBiomedAtlasFilter(BiomedAtlasFilter filter){
		this.biomedAtlasFilter = filter;
	}
	
	public void setSolrIndex(SolrIndexBean index){
		this.solrIndexBean = index;
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
		return null;
	}

	public void goListen(ActionEvent event) {		
		getSolrInput();		
	}
	
    public String getPage(){        
		String page = FacesContext.getCurrentInstance().getViewRoot().getViewId();  
		return page;
    }
	
	public String goInsitu() {
		return "/solr/solrInsitu";
	}
	public String goSeries() {
		return "/solr/solrSeries";
	}
	public String goSamples() {
		return "/solr/solrSamples";
	}
	public String goTissues() {
		return "/solr/solrTissueSummary";
	}
	public String goMicroarray() {
		return "/solr/solrMicroarray";
	}
	public String goSequences() {
		return "/solr/solrSequences";
	}

	public String getFilter(){
		return filter;
	}

	public void setFilter(String filter){
		this.filter = filter;
	}

	public String initpage(){

		if (solrInput == null)
			solrInput = "";
		
		if (!containsGene())
			return "solrInsitu";
		else
			return "solrGeneStrip";
				
	}
	
	////////////////// GENE ////////////////////////////	
		
	
	public int getGeneCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getGeneCount(solrInput, filters);
	}
	
	public int getGeneCount(HashMap<String,String> filters){
		return solrUtil.getGeneCount(solrInput, filters);
	}
	
	////////////////// EUREXPRESS ////////////////////////////
	
	public int getEurExpCount(){

		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getEurExpFilteredCount(solrInput,filters);
	}
	
	public int getEurExpCount(String filter){
		
		if (filter != "" || filter != null)	{	
	        String[]ffields = filter.split(":");
	    	filter = ffields[0] + ":" + '"' + ffields[1] + '"';
		}
		return solrUtil.getEurExpCount(solrInput, filter);
	}
	
	public int getEurExpCount(HashMap<String,String> filters){
		return solrUtil.getEurExpFilteredCount(solrInput, filters);
	}
	
	////////////////// INSITU ////////////////////////////
	
	public int getInsituCount(){

		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getInsituFilteredCount(solrInput,filters);
	}
	
	public int getInsituCount(String filter){
		
		if (filter != "" || filter != null)	{	
	        String[]ffields = filter.split(":");
	    	filter = ffields[0] + ":" + '"' + ffields[1] + '"';
		}
		return solrUtil.getInsituCount(solrInput, filter);
	}
	
	public int getInsituCount(HashMap<String,String> filters){
		return solrUtil.getInsituFilteredCount(solrInput, filters);
	}

	////////////////// SEQUENCES ////////////////////////////
	
	public int getSequencesCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getSequencesCount(solrInput, filters);
	}

	public int getSequencesCount(HashMap<String,String> filters){
		return solrUtil.getSequencesCount(solrInput, filters);
	}
	
	////////////////// MICROARRAY ////////////////////////////
	
	public int getMicroarrayCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getSamplesFilteredCount(solrInput, filters);
	}

	public int getMicroarrayCount(HashMap<String,String> filters){
		return solrUtil.getSamplesFilteredCount(solrInput, filters);
	}
	
	////////////////// GENELIST ////////////////////////////
	
	public int getGenelistCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getGenelistCount(solrInput, filters);
	}

	
	public SolrDocumentList getGenelistData(String query, HashMap<String,String> filters){
		return solrUtil.getGenelistData(solrInput, filters);
	}
	
	////////////////// TISSUE ////////////////////////////
	
	public int getTissueCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getTissueCount(solrInput, filters);
	}

	public int getTissueCount(HashMap<String,String> filters){
		return solrUtil.getTissueCount(solrInput, filters);
	}
	
	////////////////// TUTORIAL ////////////////////////////
	
//	public int getTutorialCount(){
//		HashMap<String,String> filters = new HashMap<String,String>();
//		filters = solrFilter.getFilters();
//		return solrUtil.getTutorialCount(solrInput, filters);
//	}

//	public int getTutorialCount(HashMap<String,String> filters){
//		return solrUtil.getTutorialCount(solrInput, filters);
//	}
	
	////////////////// MOUSESTRAINS ////////////////////////////
	
	public int getMouseStrainsCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getMouseStrainsCount(solrInput, filters);
	}
	
	public int getMouseStrainsCount(HashMap<String,String> filters){
		return solrUtil.getMouseStrainsCount(solrInput, filters);
	}

	////////////////// IMAGES ////////////////////////////
	
	public int getImagesCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getImagesCount(solrInput, filters);
	}

	public int getImagesCount(HashMap<String,String> filters){
		return solrUtil.getImagesCount(solrInput, filters);
	}
	
	////////////////// WEB PAGES ////////////////////////////
	
	public int getWebCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = solrFilter.getFilters();
		return solrUtil.getWebCount(solrInput, filters);
	}

	public int getWebCount(HashMap<String,String> filters){
		return solrUtil.getWebCount(solrInput, filters);
	}
	
		
//	public Map<String,String> getAssayTypeList(){
//		
//		Map<String,String> map = new LinkedHashMap<String,String>();
//		
//		String label = "ISH (" + this.getInsituCount("ASSAY_TYPE:ISH") + ")";
//		map.put(label, "ASSAY_TYPE:ISH");
//		
//		label = "IHC (" + this.getInsituCount("ASSAY_TYPE:IHC") + ")";
//		map.put(label, "ASSAY_TYPE:IHC");
//		
//		label = "TG (" + this.getInsituCount("ASSAY_TYPE:TG") + ")";
//		map.put(label, "ASSAY_TYPE:TG");
//
//		
//		return map;
//	}

	
//	public Map<String,String> getFocusGroupList(){
//		
//		Map<String,String> map = new LinkedHashMap<String,String>();
//		
//		String label = "Metanephros (" + this.getInsituCount("FOCUS_GROUPS:metanephros") + ")";
//		map.put(label, "FOCUS_GROUPS:metanephros");
//		
//		label = "Lower Urinary Tract (" + this.getInsituCount("FOCUS_GROUPS:lower urinary tract") + ")";
//		map.put(label, "FOCUS_GROUPS:lower urinary tract");
//		
//		label = "Male Reproductive System (" + this.getInsituCount("FOCUS_GROUPS:male reproductive system") + ")";
//		map.put(label, "FOCUS_GROUPS:male reproductive system");
//		
//		label = "Female Reproductive System (" + this.getInsituCount("FOCUS_GROUPS:female reproductive system") + ")";
//		map.put(label, "FOCUS_GROUPS:female reproductive system");
//		
//		label = "Early Genitourinary System (" + this.getInsituCount("FOCUS_GROUPS:early genitourinary system") + ")";
//		map.put(label, "FOCUS_GROUPS:early_genitourinary_system");
//
//		
//		return map;
//	}

//	public Map<String,String> getPlatformList(){
//		
//		Map<String,String> map = new LinkedHashMap<String,String>();
//		
//		String label = "GPL81 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL81") + ")";
//		map.put(label, "PLATFORM_GEO_ID:GPL81");
//		
//		label = "GPL339 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL339") + ")";
//		map.put(label, "PLATFORM_GEO_ID:GPL339");
//		
//		label = "GPL1261 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL1261") + ")";
//		map.put(label, "PLATFORM_GEO_ID:GPL1261");
//		
//		label = "GPL6246 (" + this.getMicroarrayCount("PLATFORM_GEO_ID:GPL6246") + ")";
//		map.put(label, "PLATFORM_GEO_ID:GPL6246");
//
//		
//		return map;
//	}

	
//	public Map<String,String> getTheilerStageList(){
//		
//		Map<String,String> map = new LinkedHashMap<String,String>();
//		
//		String label;
//
//		for (int i = 17;  i<29; i++){
//			label = "TS" + i+  " (" + this.getInsituCount("THEILER_STAGE:TS" + i) + ")";
//			map.put(label, "THEILER_STAGE:TS" + i);
//		}
//
//		
//		return map;
//	}
	
	/**
	 * This method returns true or false depending on whether the solrInput string contains a
	 * gene name. If a gene name is found the solrGeneStrip.xhtml page is displayed otherwise 
	 * the solrInsitu.xhtml page is displayed.
	 * 
	 * @return boolean flag
	 */
	private boolean containsGene(){
		
		if (solrInput.isEmpty() || solrInput == "")
			return false;
		
		ArrayList<String> genes = solrIndexBean.getGeneList();
		
		
		String[] items = solrInput.split(" ");
		if (items.length > 0){
			for(String item : items){
				if (genes.contains(item.toLowerCase()))
					return true;
			}
		}
		
		return false;
		
	}

	////////////////// BIOMED ATLAS IMAGES ////////////////////////////
	
	public int getBiomedAtlasImagesCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = biomedAtlasFilter.getFilters();
		return solrUtil.getBiomedAtlasImagesCount(solrInput, filters);
	}

	public int getBiomedAtlasImagesCount(HashMap<String,String> filters){
		return solrUtil.getBiomedAtlasImagesCount(solrInput, filters);
	}

	////////////////// BIOMED ATLAS INSITU ////////////////////////////
	
	public int getBiomedAtlasInsituCount(){

		HashMap<String,String> filters = new HashMap<String,String>();
		filters = biomedAtlasFilter.getFilters();
		return solrUtil.getBiomedAtlasInsituFilteredCount(solrInput,filters);
	}
	
	public int getBiomedAtlasInsituCount(String filter){
		
		if (filter != "" || filter != null)	{	
	        String[]ffields = filter.split(":");
	    	filter = ffields[0] + ":" + '"' + ffields[1] + '"';
		}
		return solrUtil.getBiomedAtlasInsituCount(solrInput, filter);
	}
	
	public int getBiomedAtlasInsituCount(HashMap<String,String> filters){
		return solrUtil.getBiomedAtlasInsituFilteredCount(solrInput, filters);
	}

	////////////////// BIOMED ATLAS GENES ////////////////////////////
	
	public int getBiomedAtlasGeneCount(){
		HashMap<String,String> filters = new HashMap<String,String>();
		filters = biomedAtlasFilter.getFilters();
		return solrUtil.getBiomedAtlasGeneCount(solrInput, filters);
	}
	
	public int getBiomedAtlasGeneCount(HashMap<String,String> filters){
		return solrUtil.getBiomedAtlasGeneCount(solrInput, filters);
	}
	
	
}
