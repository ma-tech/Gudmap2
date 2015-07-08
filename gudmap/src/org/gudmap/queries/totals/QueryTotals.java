package org.gudmap.queries.totals;

import org.gudmap.queries.generic.*;
import java.util.*;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;

@ManagedBean (eager=true)
@ApplicationScoped
public class QueryTotals {
	static  HashMap<String,String> queryList;
	
/***********************************STATIC TOTALS******************************/
	
	private final String ISH_TOTAL_KEY="ISH_TOTAL";
	private final String ISH_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE = 'ISH'  AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String IHC_TOTAL_KEY="IHC_TOTAL";
	private final String IHC_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE ='IHC' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String TG_TOTAL_KEY="TG_TOTAL";
	private final String TG_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE ='TG' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String SEQUENCE_TOTAL_KEY="SEQUENCE_TOTAL";
	private final String SEQUENCE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE ='NextGen' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String MICROARRAY_TOTAL_KEY="MICROARRAY_TOTAL";
	private final String MICROARRAY_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE ='Microarray' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String WISH_TOTAL_KEY="WISH_TOTAL";
	private final String WISH_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION,ISH_SPECIMEN  WHERE SPN_SUBMISSION_FK=SUB_OID AND SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE='wholemount' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String SISH_TOTAL_KEY="SISH_TOTAL";
	private final String SISH_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION,ISH_SPECIMEN  WHERE SPN_SUBMISSION_FK=SUB_OID AND SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE='section' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String OPT_TOTAL_KEY="OPT_TOTAL";
	private final String OPT_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION,ISH_SPECIMEN  WHERE SPN_SUBMISSION_FK=SUB_OID AND SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE='opt-wholemount' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String ISH_TOTAL_GENES_KEY="ISH_TOTAL_GENES";
	private final String ISH_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM REF_PROBE, ISH_PROBE, ISH_SUBMISSION WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String IHC_TOTAL_GENES_KEY="IHC_TOTAL_GENES";
	private final String IHC_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM REF_PROBE, ISH_PROBE, ISH_SUBMISSION WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'IHC' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String TG_TOTAL_GENES_KEY="TG_TOTAL_GENES";
	private final String TG_TOTAL_GENES="SELECT COUNT(DISTINCT ALE_GENE) FROM ISH_ALLELE JOIN LNK_SUB_ALLELE ON SAL_ALE_OID_FK = ALE_OID " +
										"JOIN ISH_SUBMISSION ON SAL_SUBMISSION_FK = SUB_OID WHERE SUB_ASSAY_TYPE = 'TG' AND SAL_ORDER = 1 " +
										"AND "+GenericQueries.PUBLIC_ENTRIES;
	/*private final String TG_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM REF_PROBE, ISH_PROBE, ISH_SUBMISSION WHERE PRB_MAPROBE = RPR_OID AND " +
			 "PRB_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'TG' AND "+GenericQueries.PUBLIC_ENTRIES;*/

	
	private final String WISH_TOTAL_GENES_KEY="WISH_TOTAL_GENES";
	private final String WISH_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM REF_PROBE, ISH_PROBE, ISH_SUBMISSION, ISH_SPECIMEN WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SPN_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE='wholemount' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String SISH_TOTAL_GENES_KEY="SISH_TOTAL_GENES";
	private final String SISH_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM REF_PROBE, ISH_PROBE, ISH_SUBMISSION, ISH_SPECIMEN WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SPN_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE='section' AND "+GenericQueries.PUBLIC_ENTRIES;
	
	private final String OPT_TOTAL_GENES_KEY="OPT_TOTAL_GENES";
	private final String OPT_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM REF_PROBE, ISH_PROBE, ISH_SUBMISSION, ISH_SPECIMEN WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SPN_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE='opt-wholemount' AND "+GenericQueries.PUBLIC_ENTRIES;
	
/***********************************VOLATILE TOTALS******************************/
	
	private final String ISH_VOLATILE_TOTAL_KEY="ISH_VOLATILE_TOTAL";
	private final String ISH_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " +
											"WHERE SUB_ASSAY_TYPE = 'ISH'  AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String IHC_VOLATILE_TOTAL_KEY="IHC_VOLATILE_TOTAL";
	private final String IHC_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " + 
											"WHERE SUB_ASSAY_TYPE ='IHC' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String TG_VOLATILE_TOTAL_KEY="TG_VOLATILE_TOTAL";
	private final String TG_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " +
											"WHERE SUB_ASSAY_TYPE ='TG' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String SEQUENCE_VOLATILE_TOTAL_KEY="SEQUENCE_VOLATILE_TOTAL";
	private final String SEQUENCE_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK=SUB_OID " +
										"WHERE SUB_ASSAY_TYPE ='NextGen' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String MICROARRAY_VOLATILE_TOTAL_KEY="MICROARRAY_VOLATILE_TOTAL";
	private final String MICROARRAY_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " +
												"WHERE SUB_ASSAY_TYPE ='Microarray' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	//private final String MICROARRAY_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE ='Microarray' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String WISH_VOLATILE_TOTAL_KEY="WISH_VOLATILE_TOTAL";
	private final String WISH_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
											"ISH_SPECIMEN  WHERE SPN_SUBMISSION_FK=SUB_OID AND SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE='wholemount' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String SISH_VOLATILE_TOTAL_KEY="SISH_VOLATILE_TOTAL";
	private final String SISH_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
											"ISH_SPECIMEN  WHERE SPN_SUBMISSION_FK=SUB_OID AND SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE='section' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String OPT_VOLATILE_TOTAL_KEY="OPT_VOLATILE_TOTAL";
	private final String OPT_VOLATILE_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
											"ISH_SPECIMEN  WHERE SPN_SUBMISSION_FK=SUB_OID AND SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE='opt-wholemount' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String ISH_VOLATILE_TOTAL_GENES_KEY="ISH_VOLATILE_TOTAL_GENES";
	private final String ISH_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
										 "REF_PROBE, ISH_PROBE WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String IHC_VOLATILE_TOTAL_GENES_KEY="IHC_VOLATILE_TOTAL_GENES";
	private final String IHC_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
										 "REF_PROBE, ISH_PROBE WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'IHC' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String TG_VOLATILE_TOTAL_GENES_KEY="TG_VOLATILE_TOTAL_GENES";
	private final String TG_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT ALE_GENE) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID  " +
										 "JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID JOIN ISH_ALLELE ON SAL_ALE_OID_FK = ALE_OID " +
										 "WHERE SUB_ASSAY_TYPE = 'TG' AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	/*private final String TG_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
			 "REF_PROBE, ISH_PROBE WHERE PRB_MAPROBE = RPR_OID AND " +
			 "PRB_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'TG' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";*/
	
	private final String WISH_VOLATILE_TOTAL_GENES_KEY="WISH_VOLATILE_TOTAL_GENES";
	private final String WISH_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
										  "REF_PROBE, ISH_PROBE, ISH_SPECIMEN WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SPN_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE='wholemount' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String SISH_VOLATILE_TOTAL_GENES_KEY="SISH_VOLATILE_TOTAL_GENES";
	private final String SISH_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
										"REF_PROBE, ISH_PROBE, ISH_SPECIMEN WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SPN_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE='section' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	private final String OPT_VOLATILE_TOTAL_GENES_KEY="OPT_VOLATILE_TOTAL_GENES";
	private final String OPT_VOLATILE_TOTAL_GENES="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID, " +
										 "REF_PROBE, ISH_PROBE, ISH_SPECIMEN WHERE PRB_MAPROBE = RPR_OID AND " +
										 "PRB_SUBMISSION_FK = SUB_OID AND SPN_SUBMISSION_FK = SUB_OID AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE='opt-wholemount' AND "+GenericQueries.PUBLIC_ENTRIES +"%s";
	
	
	/***************************************TABLE COLUMN TOTALS**********************************************/
	
	private final String ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION_KEY="ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION";
	private final String ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION="SELECT COUNT(DISTINCT SUB_ACCESSION_ID) AS TOTAL "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_GENE_KEY="ASSAY_TYPE_TOTAL_GENE";
	private final String ASSAY_TYPE_TOTAL_GENE="SELECT COUNT(DISTINCT RPR_SYMBOL) AS TOTAL "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_SOURCE_KEY="ASSAY_TYPE_TOTAL_SOURCE";
	private final String ASSAY_TYPE_TOTAL_SOURCE="SELECT COUNT(DISTINCT PER_SURNAME) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_SUBMISSION_DATE_KEY="ASSAY_TYPE_TOTAL_SUBMISSION_DATE";
	private final String ASSAY_TYPE_TOTAL_SUBMISSION_DATE="SELECT COUNT(DISTINCT SUB_SUB_DATE) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_ASSAY_TYPE_KEY="ASSAY_TYPE_TOTAL_ASSAY_TYPE";
	private final String ASSAY_TYPE_TOTAL_ASSAY_TYPE="SELECT COUNT(DISTINCT SUB_ASSAY_TYPE) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_PROBE_NAME_KEY="ASSAY_TYPE_TOTAL_PROBE_NAME";
	private final String ASSAY_TYPE_TOTAL_PROBE_NAME="SELECT COUNT(DISTINCT RPR_JAX_ACC) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_EMBRYO_STAGE_KEY="ASSAY_TYPE_TOTAL_EMBRYO_STAGE";
/*	private final String ASSAY_TYPE_TOTAL_EMBRYO_STAGE="SELECT COUNT(DISTINCT SUB_EMBRYO_STG) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
*/	
	private final String ASSAY_TYPE_TOTAL_EMBRYO_STAGE="SELECT COUNT(DISTINCT STG_STAGE_DISPLAY) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_AGE_KEY="ASSAY_TYPE_TOTAL_AGE";
	/*private final String ASSAY_TYPE_TOTAL_AGE="SELECT COUNT(DISTINCT TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) WHEN 'P' THEN CONCAT('P',SPN_STAGE) ELSE " +
										"CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END))  AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";*/
	private final String ASSAY_TYPE_TOTAL_AGE="SELECT COUNT(DISTINCT STG_ALT_STAGE)  AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_SEX_KEY="ASSAY_TYPE_TOTAL_SEX";
	private final String ASSAY_TYPE_TOTAL_SEX="SELECT COUNT(DISTINCT SPN_SEX) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_GENOTYPE_KEY="ASSAY_TYPE_TOTAL_GENOTYPE";
	private final String ASSAY_TYPE_TOTAL_GENOTYPE="SELECT COUNT(DISTINCT SPN_WILDTYPE) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_TISSUE_KEY="ASSAY_TYPE_TOTAL_TISSUE";
	/*private final String ASSAY_TYPE_TOTAL_TISSUE="SELECT COUNT(DISTINCT ANO_COMPONENT_NAME) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
										"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
										"JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK " +
										"LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
										"WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";*/
	private final String ASSAY_TYPE_TOTAL_TISSUE="SELECT COUNT(DISTINCT ANO_COMPONENT_NAME) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK " +
			"LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
			"WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_EXPRESSION_KEY="ASSAY_TYPE_TOTAL_EXPRESSION";
	/*private final String ASSAY_TYPE_TOTAL_EXPRESSION="SELECT COUNT(DISTINCT EXP_STRENGTH) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
										"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
										"JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK " +
										"WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";*/
	private final String ASSAY_TYPE_TOTAL_EXPRESSION="SELECT COUNT(DISTINCT EXP_STRENGTH) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK " +
			"WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_SPECIMEN_TYPE_KEY="ASSAY_TYPE_TOTAL_SPECIMEN_TYPE";
	private final String ASSAY_TYPE_TOTAL_SPECIMEN_TYPE="SELECT COUNT(DISTINCT SPN_ASSAY_TYPE) AS TOTAL  "+GenericQueries.INSITU_TOTAL_JOINS+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String ASSAY_TYPE_TOTAL_IMAGES_KEY="ASSAY_TYPE_TOTAL_IMAGES";
	/*private final String ASSAY_TYPE_TOTAL_IMAGES="SELECT COUNT(DISTINCT IMG_OID) AS TOTAL FROM ISH_SUBMISSION %s JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
										"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
										"JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK " +
										"WHERE IMG_TYPE NOT LIKE '%%wlz%%' AND SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";*/
	private final String ASSAY_TYPE_TOTAL_IMAGES="SELECT COUNT(DISTINCT IMG_OID) AS TOTAL FROM ISH_SUBMISSION %s JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID  JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK " +
			"WHERE IMG_TYPE NOT LIKE '%%wlz%%' AND SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	
	///////////////////////////TRANSGENIC VARIATIONS///////////////////////
	
	private final String TG_TYPE_TOTAL_GUDMAP_ACCESSION_KEY="TG_TYPE_TOTAL_GUDMAP_ACCESSION";
	private final String TG_TYPE_TOTAL_GUDMAP_ACCESSION="SELECT COUNT(DISTINCT SUB_ACCESSION_ID) AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
						 "WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_GENE_KEY="TG_TYPE_TOTAL_GENE";
	private final String TG_TYPE_TOTAL_GENE="SELECT COUNT(DISTINCT ALE_GENE) AS TOTAL  " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_SOURCE_KEY="TG_TYPE_TOTAL_SOURCE";
	private final String TG_TYPE_TOTAL_SOURCE="SELECT COUNT(DISTINCT PER_SURNAME) AS TOTAL   " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_SUBMISSION_DATE_KEY="TG_TYPE_TOTAL_SUBMISSION_DATE";
	private final String TG_TYPE_TOTAL_SUBMISSION_DATE="SELECT COUNT(DISTINCT SUB_SUB_DATE) AS TOTAL  " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_ASSAY_TYPE_KEY="TG_TYPE_TOTAL_ASSAY_TYPE";
	private final String TG_TYPE_TOTAL_ASSAY_TYPE="SELECT COUNT(DISTINCT SUB_ASSAY_TYPE) AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_PROBE_NAME_KEY="TG_TYPE_TOTAL_PROBE_NAME";
	private final String TG_TYPE_TOTAL_PROBE_NAME="SELECT 0 AS TOTAL";
	
	private final String TG_TYPE_TOTAL_EMBRYO_STAGE_KEY="TG_TYPE_TOTAL_EMBRYO_STAGE";
	/*private final String TG_TYPE_TOTAL_EMBRYO_STAGE="SELECT COUNT(DISTINCT SUB_EMBRYO_STG) AS TOTAL  " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	*/
	private final String TG_TYPE_TOTAL_EMBRYO_STAGE="SELECT COUNT(DISTINCT STG_STAGE_DISPLAY) AS TOTAL  " + GenericQueries.TG_TOTAL_JOINS + 
			"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";

	private final String TG_TYPE_TOTAL_AGE_KEY="TG_TYPE_TOTAL_AGE";
	/*private final String TG_TYPE_TOTAL_AGE="SELECT COUNT(DISTINCT TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) WHEN 'P' THEN CONCAT('P',SPN_STAGE) ELSE " +
										"CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END))  AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";*/
	private final String TG_TYPE_TOTAL_AGE="SELECT COUNT(DISTINCT STG_ALT_STAGE)  AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_SEX_KEY="TG_TYPE_TOTAL_SEX";
	private final String TG_TYPE_TOTAL_SEX="SELECT COUNT(DISTINCT SPN_SEX) AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_GENOTYPE_KEY="TG_TYPE_TOTAL_GENOTYPE";
	private final String TG_TYPE_TOTAL_GENOTYPE="SELECT COUNT(DISTINCT CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN " +
						"(SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN " +
						"(SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE " +
						"(SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END ) AS TOTAL " + 
						GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	
	private final String TG_TYPE_TOTAL_TISSUE_KEY="TG_TYPE_TOTAL_TISSUE";
	private final String TG_TYPE_TOTAL_TISSUE="SELECT COUNT(DISTINCT ANO_COMPONENT_NAME) AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
						"LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";

	
	private final String TG_TYPE_TOTAL_EXPRESSION_KEY="TG_TYPE_TOTAL_EXPRESSION";
	/*private final String TG_TYPE_TOTAL_EXPRESSION="SELECT COUNT(DISTINCT EXP_STRENGTH) AS TOTAL FROM ISH_SUBMISSION JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID " +
						"JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
						"JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";*/
	private final String TG_TYPE_TOTAL_EXPRESSION="SELECT COUNT(DISTINCT EXP_STRENGTH) AS TOTAL FROM ISH_SUBMISSION JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID " +
			"JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			"JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
			"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_SPECIMEN_TYPE_KEY="TG_TYPE_TOTAL_SPECIMEN_TYPE";
	private final String TG_TYPE_TOTAL_SPECIMEN_TYPE="SELECT COUNT(DISTINCT SPN_ASSAY_TYPE) AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
						"WHERE SUB_ASSAY_TYPE = ? AND SAL_ORDER = 1 AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	private final String TG_TYPE_TOTAL_IMAGES_KEY="TG_TYPE_TOTAL_IMAGES";
	private final String TG_TYPE_TOTAL_IMAGES="SELECT COUNT(DISTINCT IMG_OID) AS TOTAL " + GenericQueries.TG_TOTAL_JOINS + 
			"JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK " +
			"WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+" %s %s";
	
	/////////////STAGE QUERIES
	
	private final String INSITU_TOTAL_KEY="INSITU_TOTAL";
	private final String INSITU_TOTAL="SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK=SUB_OID " +
									"JOIN REF_PROBE ON RPR_OID=PRB_MAPROBE LEFT JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK=SUB_OID " +
									"WHERE (SUB_ASSAY_TYPE = 'ISH' OR SUB_ASSAY_TYPE = 'IHC')  AND "+GenericQueries.PUBLIC_ENTRIES;
	
	
	
	
	public QueryTotals(){
		queryList=new HashMap<String,String>();
		
		queryList.put(ISH_TOTAL_KEY,ISH_TOTAL);
		queryList.put(IHC_TOTAL_KEY,IHC_TOTAL);
		queryList.put(TG_TOTAL_KEY,TG_TOTAL);
		queryList.put(SEQUENCE_TOTAL_KEY,SEQUENCE_TOTAL);
		queryList.put(MICROARRAY_TOTAL_KEY,MICROARRAY_TOTAL);
		queryList.put(WISH_TOTAL_KEY,WISH_TOTAL);
		queryList.put(SISH_TOTAL_KEY,SISH_TOTAL);
		queryList.put(OPT_TOTAL_KEY,OPT_TOTAL);
		queryList.put(ISH_TOTAL_GENES_KEY,ISH_TOTAL_GENES);
		queryList.put(IHC_TOTAL_GENES_KEY,IHC_TOTAL_GENES);
		queryList.put(TG_TOTAL_GENES_KEY,TG_TOTAL_GENES);
		queryList.put(WISH_TOTAL_GENES_KEY,WISH_TOTAL_GENES);
		queryList.put(SISH_TOTAL_GENES_KEY,SISH_TOTAL_GENES);
		queryList.put(OPT_TOTAL_GENES_KEY,OPT_TOTAL_GENES);
		
		queryList.put(ISH_VOLATILE_TOTAL_KEY,ISH_VOLATILE_TOTAL);
		queryList.put(IHC_VOLATILE_TOTAL_KEY,IHC_VOLATILE_TOTAL);
		queryList.put(TG_VOLATILE_TOTAL_KEY,TG_VOLATILE_TOTAL);
		queryList.put(SEQUENCE_VOLATILE_TOTAL_KEY,SEQUENCE_VOLATILE_TOTAL);
		queryList.put(MICROARRAY_VOLATILE_TOTAL_KEY,MICROARRAY_VOLATILE_TOTAL);
		queryList.put(WISH_VOLATILE_TOTAL_KEY,WISH_VOLATILE_TOTAL);
		queryList.put(SISH_VOLATILE_TOTAL_KEY,SISH_VOLATILE_TOTAL);
		queryList.put(OPT_VOLATILE_TOTAL_KEY,OPT_VOLATILE_TOTAL);
		queryList.put(ISH_VOLATILE_TOTAL_GENES_KEY,ISH_VOLATILE_TOTAL_GENES);
		queryList.put(IHC_VOLATILE_TOTAL_GENES_KEY,IHC_VOLATILE_TOTAL_GENES);
		queryList.put(TG_VOLATILE_TOTAL_GENES_KEY,TG_VOLATILE_TOTAL_GENES);
		queryList.put(WISH_VOLATILE_TOTAL_GENES_KEY,WISH_VOLATILE_TOTAL_GENES);
		queryList.put(SISH_VOLATILE_TOTAL_GENES_KEY,SISH_VOLATILE_TOTAL_GENES);
		queryList.put(OPT_VOLATILE_TOTAL_GENES_KEY,OPT_VOLATILE_TOTAL_GENES);
		
		queryList.put(ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION_KEY, ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION);
		queryList.put(ASSAY_TYPE_TOTAL_GENE_KEY, ASSAY_TYPE_TOTAL_GENE);
		queryList.put(ASSAY_TYPE_TOTAL_SOURCE_KEY, ASSAY_TYPE_TOTAL_SOURCE);
		queryList.put(ASSAY_TYPE_TOTAL_SUBMISSION_DATE_KEY, ASSAY_TYPE_TOTAL_SUBMISSION_DATE);
		queryList.put(ASSAY_TYPE_TOTAL_ASSAY_TYPE_KEY, ASSAY_TYPE_TOTAL_ASSAY_TYPE);
		queryList.put(ASSAY_TYPE_TOTAL_PROBE_NAME_KEY, ASSAY_TYPE_TOTAL_PROBE_NAME);
		queryList.put(ASSAY_TYPE_TOTAL_EMBRYO_STAGE_KEY, ASSAY_TYPE_TOTAL_EMBRYO_STAGE);
		queryList.put(ASSAY_TYPE_TOTAL_AGE_KEY, ASSAY_TYPE_TOTAL_AGE);
		queryList.put(ASSAY_TYPE_TOTAL_SEX_KEY, ASSAY_TYPE_TOTAL_SEX);
		queryList.put(ASSAY_TYPE_TOTAL_GENOTYPE_KEY, ASSAY_TYPE_TOTAL_GENOTYPE);
		queryList.put(ASSAY_TYPE_TOTAL_TISSUE_KEY, ASSAY_TYPE_TOTAL_TISSUE);
		queryList.put(ASSAY_TYPE_TOTAL_EXPRESSION_KEY, ASSAY_TYPE_TOTAL_EXPRESSION);
		queryList.put(ASSAY_TYPE_TOTAL_SPECIMEN_TYPE_KEY, ASSAY_TYPE_TOTAL_SPECIMEN_TYPE);
		queryList.put(ASSAY_TYPE_TOTAL_IMAGES_KEY, ASSAY_TYPE_TOTAL_IMAGES);
		
		queryList.put(TG_TYPE_TOTAL_GUDMAP_ACCESSION_KEY, TG_TYPE_TOTAL_GUDMAP_ACCESSION);
		queryList.put(TG_TYPE_TOTAL_GENE_KEY, TG_TYPE_TOTAL_GENE);
		queryList.put(TG_TYPE_TOTAL_SOURCE_KEY, TG_TYPE_TOTAL_SOURCE);
		queryList.put(TG_TYPE_TOTAL_SUBMISSION_DATE_KEY, TG_TYPE_TOTAL_SUBMISSION_DATE);
		queryList.put(TG_TYPE_TOTAL_ASSAY_TYPE_KEY, TG_TYPE_TOTAL_ASSAY_TYPE);
		queryList.put(TG_TYPE_TOTAL_PROBE_NAME_KEY, TG_TYPE_TOTAL_PROBE_NAME);
		queryList.put(TG_TYPE_TOTAL_EMBRYO_STAGE_KEY, TG_TYPE_TOTAL_EMBRYO_STAGE);
		queryList.put(TG_TYPE_TOTAL_AGE_KEY, TG_TYPE_TOTAL_AGE);
		queryList.put(TG_TYPE_TOTAL_SEX_KEY, TG_TYPE_TOTAL_SEX);
		queryList.put(TG_TYPE_TOTAL_GENOTYPE_KEY, TG_TYPE_TOTAL_GENOTYPE);
		queryList.put(TG_TYPE_TOTAL_TISSUE_KEY, TG_TYPE_TOTAL_TISSUE);
		queryList.put(TG_TYPE_TOTAL_EXPRESSION_KEY, TG_TYPE_TOTAL_EXPRESSION);
		queryList.put(TG_TYPE_TOTAL_SPECIMEN_TYPE_KEY, TG_TYPE_TOTAL_SPECIMEN_TYPE);
		queryList.put(TG_TYPE_TOTAL_IMAGES_KEY, TG_TYPE_TOTAL_IMAGES);
		
		queryList.put(INSITU_TOTAL_KEY,INSITU_TOTAL);
	}
	
	public static String ReturnQuery(String key)
	{
		return (String)queryList.get(key);
	}

}
