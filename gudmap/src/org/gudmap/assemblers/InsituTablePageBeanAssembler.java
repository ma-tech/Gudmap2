package org.gudmap.assemblers;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gudmap.globals.Globals;
import org.gudmap.queries.generic.GenericQueries;
import org.gudmap.queries.totals.QueryTotals;
import org.gudmap.utils.Utils;
import org.gudmap.models.InsituTableBeanModel;

public class InsituTablePageBeanAssembler {
	
	
	//private DataSource ds;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet result;
	private InsituTableBeanModel ishmodel;
	private String paramSQL;
	private String assayType;
	private String assayType2;
	private String whereclause;
	private String focusGroupWhereclause;
	private String expressionJoin;
	private String specimenWhereclause;
	private int batch=0;
	private String submitter;
	private int agefrom=-1;
	private int ageto=-1;
	private boolean optResult=false;
	
	public  InsituTablePageBeanAssembler(String paramSQL,String assayType, String assayType2) {
		this.assayType2=assayType2;
		optResult=true;
		setup(paramSQL,assayType);
	}
	
	public  InsituTablePageBeanAssembler(String paramSQL,String assayType) {
		/*if(Globals.getParameterValue("batch")!=null)
			batch=Integer.parseInt(Globals.getParameterValue("batch"));
		
		if(Globals.getParameterValue("submitter")!=null)
			submitter=Globals.getParameterValue("submitter");
		
		if(Globals.getParameterValue("agefrom")!=null)
			agefrom=Integer.parseInt(Globals.getParameterValue("agefrom"));
		
		if(Globals.getParameterValue("ageto")!=null)
			ageto=Integer.parseInt(Globals.getParameterValue("ageto"));
		
		this.paramSQL=paramSQL;
		this.assayType=assayType;*/
		setup(paramSQL,assayType);
	}
	
	public void setup(String paramSQL,String assayType) {
		if(Globals.getParameterValue("batch")!=null)
			batch=Integer.parseInt(Globals.getParameterValue("batch"));
		
		if(Globals.getParameterValue("submitter")!=null)
			submitter=Globals.getParameterValue("submitter");
		
		if(Globals.getParameterValue("agefrom")!=null)
			agefrom=Integer.parseInt(Globals.getParameterValue("agefrom"));
		
		if(Globals.getParameterValue("ageto")!=null)
			ageto=Integer.parseInt(Globals.getParameterValue("ageto"));
		
		this.assayType=assayType;
		this.paramSQL=paramSQL;
	}
	
	public List<InsituTableBeanModel> getData(int firstRow, int rowCount, String sortField, boolean sortAscending, String whereclause, 
											String focusGroupWhereclause, String expressionJoin,String specimenWhereclause){
		this.whereclause=whereclause;
		this.focusGroupWhereclause=focusGroupWhereclause;
		this.expressionJoin=expressionJoin;
		this.specimenWhereclause=specimenWhereclause;
		String sortDirection = sortAscending ? "ASC" : "DESC";
		
		if(batch>0){whereclause+=" SUB_BATCH="+batch+" AND ";}
		if(submitter!=null){whereclause+=" SUB_SOURCE='"+submitter+"' AND ";}
		if(agefrom>=0) {
			whereclause+=" SUB_STAGE_FK BETWEEN "+agefrom+" AND "+ageto+" AND ";
		}
		
		if(assayType.equals("TG"))
			whereclause = whereclause.replace("RPR_SYMBOL", "ALE_GENE");
		if(assayType.equals("INSITU")){
			paramSQL = paramSQL.replace("SUB_ASSAY_TYPE = ?", "SUB_ASSAY_TYPE IN ('ISH','IHC','TG')");
		}
		
		String sql = String.format(paramSQL, expressionJoin, whereclause, focusGroupWhereclause, sortField, sortDirection);
		if(!expressionJoin.equals(""))
			sql=sql.replace("FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID", "");
		sql=sql.replace("SUB_DB_STATUS_FK = 4  AND", "SUB_DB_STATUS_FK = 4  AND"+specimenWhereclause);
		List<InsituTableBeanModel> list = new ArrayList<InsituTableBeanModel>();
		try
		{
			con = Globals.getDatasource().getConnection();
			ps = con.prepareStatement(sql);
			if(assayType.equals("INSITU")){
				ps.setInt(1, firstRow);
				ps.setInt(2, rowCount);
			}
			//OPT - can be ISH or IHC
			else if(optResult) {
				ps.setString(1, assayType);
				ps.setString(2, assayType2);
				ps.setInt(3, firstRow);
				ps.setInt(4, rowCount);
			}
			else
			{
				ps.setString(1, assayType);
				ps.setInt(2, firstRow);
				ps.setInt(3, rowCount);
			}
			result =  ps.executeQuery();
			
			while(result.next()){
				ishmodel=new InsituTableBeanModel();
				ishmodel.setOid(result.getString("oid"));
				ishmodel.setGene(result.getString("gene"));
				ishmodel.setGudmap_accession(result.getString("gudmap_accession"));
				ishmodel.setSource(result.getString("source"));
				ishmodel.setSubmission_date(result.getString("submission_date"));
				ishmodel.setAssay_type(result.getString("assay_type"));
				ishmodel.setProbe_name(result.getString("probe_name"));
				ishmodel.setStage(result.getString("stage"));
				ishmodel.setStage_order(result.getString("stage").substring(2));
				ishmodel.setSpecies(result.getString("species"));
				ishmodel.setAge(result.getString("age"));
				ishmodel.setSex(result.getString("sex"));
				ishmodel.setGenotype(result.getString("genotype"));
				ishmodel.setTissue(result.getString("tissue"));
				ishmodel.setExpression(result.getString("expression"));
				ishmodel.setSpecimen(result.getString("specimen"));
				ishmodel.setImage(result.getString("image"));
				ishmodel.setGene_id(result.getString("gene_id"));
				
				ishmodel.setSelected(false);
				list.add(ishmodel);
			}
		}
		catch(SQLException sqle){sqle.printStackTrace();}
		finally {
		    Globals.closeQuietly(con, ps, result);
		}
		return list;
	}
	
	public int count() {
		int count=0;
		
		String totalwhere=(whereclause.equals(" WHERE "))?"":Utils.removeWhere(whereclause, " WHERE ");
		String queryString = GenericQueries.ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION;
		if(assayType.equals("TG"))
			totalwhere = totalwhere.replace("RPR_SYMBOL", "ALE_GENE");
		if(assayType.equals("INSITU")){
			queryString = queryString.replace("SUB_ASSAY_TYPE = ?", "SUB_ASSAY_TYPE IN ('ISH','IHC','TG')");
		}
		if(optResult){
			queryString = queryString.replace("SUB_ASSAY_TYPE = ?", "SUB_ASSAY_TYPE IN ('ISH','IHC')");
		}
		String sql = String.format(queryString,expressionJoin,totalwhere,focusGroupWhereclause);
		sql=sql.replace(" WHERE ", " WHERE "+specimenWhereclause);
		//PARAMS
		if(batch>0)
			sql=sql.replace(" WHERE ", " WHERE SUB_BATCH="+batch+" AND ");
		if(submitter!=null){sql=sql.replace(" WHERE ", " WHERE SUB_SOURCE='"+submitter+"' AND ");}
		if(agefrom>=0) {
			sql=sql.replace(" WHERE ", " WHERE SUB_STAGE_FK BETWEEN "+agefrom+" AND "+ageto+" AND ");
		}
		
		try
		{
				con = Globals.getDatasource().getConnection();
				ps = con.prepareStatement(sql);
				if(!assayType.equals("INSITU") && !optResult)
					ps.setString(1, assayType);
				result =  ps.executeQuery();
				
				while(result.next()){
					count=result.getInt(1);
				}
		}
		catch(SQLException sqle){sqle.printStackTrace();}
		finally {
			    Globals.closeQuietly(con, ps, result);
		}		
		return count;
	}
	
	public Map<String,String>  getTotals() {
		Map<String,String> totals = new HashMap<String,String>();
		
		String [] queries=(assayType.equals("TG")?Globals.TgColTotals:Globals.IshColTotals);
		
		String totalwhere=(whereclause.equals(" WHERE "))?"":Utils.removeWhere(whereclause, " WHERE ");
		if(assayType.equals("TG"))
			totalwhere = totalwhere.replace("RPR_SYMBOL", "ALE_GENE");
		String sql="";
		for(int i=0;i<queries.length;i++) {
			try
			{
				con = Globals.getDatasource().getConnection();
				if(queries[i].equals("ASSAY_TYPE_TOTAL_TISSUE") || queries[i].equals("ASSAY_TYPE_TOTAL_EXPRESSION") || queries[i].equals("TG_TYPE_TOTAL_EXPRESSION")){
					sql= String.format(QueryTotals.ReturnQuery(queries[i]),totalwhere,focusGroupWhereclause);
					sql=sql.replace(" WHERE ", " WHERE "+specimenWhereclause);
				}
				else if(queries[i].equals("TG_TYPE_TOTAL_PROBE_NAME"))
					sql = QueryTotals.ReturnQuery(queries[i]);
				else {
					sql=String.format(QueryTotals.ReturnQuery(queries[i]),expressionJoin,totalwhere,focusGroupWhereclause);
					sql=sql.replace(" WHERE ", " WHERE "+specimenWhereclause);
					//ps = con.prepareStatement(sql);
				}
				//PARAMS
				//is it a batch query?
				if(batch>0)
					sql=sql.replace(" WHERE ", " WHERE SUB_BATCH="+batch+" AND ");
				
				if(submitter!=null){sql=sql.replace(" WHERE ", " WHERE SUB_SOURCE='"+submitter+"' AND ");}
				
				if(agefrom>=0) {
					sql=sql.replace(" WHERE ", " WHERE SUB_STAGE_FK BETWEEN "+agefrom+" AND "+ageto+" AND ");
				}
				if(assayType.equals("INSITU")){
					sql = sql.replace("SUB_ASSAY_TYPE = ?", "SUB_ASSAY_TYPE IN ('ISH','IHC','TG')");
				}
				if(optResult){
					sql = sql.replace("SUB_ASSAY_TYPE = ?", "SUB_ASSAY_TYPE IN ('ISH','IHC')");
				}
				
				ps = con.prepareStatement(sql);
				if(!assayType.equals("INSITU") && !optResult)
					ps.setString(1, assayType);
				result =  ps.executeQuery();
				
				while(result.next()){
					totals.put(queries[i],result.getString("TOTAL"));
				}
			}
			catch(SQLException sqle){sqle.printStackTrace();}
			finally {
			    Globals.closeQuietly(con, ps, result);
			}
		}
		return totals;
	}
	
	public void setAssayType(String assayType){
		this.assayType=assayType;
	}
}
