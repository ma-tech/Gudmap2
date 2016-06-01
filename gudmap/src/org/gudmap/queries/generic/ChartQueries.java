package org.gudmap.queries.generic;

public class ChartQueries {
	
	public final static String PUBLIC_CONDITION = "SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0 ";
	
	public final static String ENTRIES_AND_GENES = "SELECT (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='IHC' AND " + PUBLIC_CONDITION + 
			") AS ihc, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='TG' AND " + PUBLIC_CONDITION + 
			") AS tg, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='Microarray' AND " + PUBLIC_CONDITION + 
			") AS microarray, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='NextGen' AND " + PUBLIC_CONDITION + 
			") AS sequence, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID " +
			"WHERE SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE = 'wholemount' AND " + PUBLIC_CONDITION + " ) AS wish, " +
			"(SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID WHERE SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE = 'section' AND " + PUBLIC_CONDITION + 
			") AS sish, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID " +
			"WHERE SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE = 'opt-wholemount' AND " + PUBLIC_CONDITION + " ) AS opt, " +
			"(SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE " + PUBLIC_CONDITION + " ) AS total_entries, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE WHERE " + PUBLIC_CONDITION + 
			") AS total_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"WHERE SUB_ASSAY_TYPE = 'IHC' AND " + PUBLIC_CONDITION + " ) AS total_ihc_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"WHERE SUB_ASSAY_TYPE = 'TG' AND " + PUBLIC_CONDITION + " ) AS total_tg_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID " +
			"JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE WHERE SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'wholemount' AND " + PUBLIC_CONDITION + 
			") AS total_wish_genes, (SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION " +
			"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"WHERE SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'section' AND " + PUBLIC_CONDITION + " ) AS total_sish_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID " +
			"JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE WHERE SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'opt-wholemount' AND " + PUBLIC_CONDITION + 
			") AS total_opt_genes";
	
	public final static String ENTRIES_BY_LAB = "SELECT (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = 'wholemount' AND " + PUBLIC_CONDITION +
			") as wish_lab, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = 'section' AND " + PUBLIC_CONDITION +
			") as sish_lab, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = 'opt-wholemount' AND " + PUBLIC_CONDITION +
			") as opt_lab, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION  WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'IHC' AND " + PUBLIC_CONDITION +
			") as ihc_lab, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION  WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'TG' AND " + PUBLIC_CONDITION +
			") as tg_lab, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION  WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'Microarray' AND " + PUBLIC_CONDITION +
			") as microarray_lab, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION  WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'NextGen' AND " + PUBLIC_CONDITION +
			") as sequence_lab";
	
	public final static String ASSAY_TYPES_BY_AGE = "SELECT (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = 'wholemount' AND " + PUBLIC_CONDITION +
			") as wish_age, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = 'section' AND " + PUBLIC_CONDITION +
			") as sish_age, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = 'opt-wholemount' AND " + PUBLIC_CONDITION +
			") as opt_age, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION  " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'IHC' AND " + PUBLIC_CONDITION +
			") as ihc_age, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'TG' AND " + PUBLIC_CONDITION +
			") as tg_age, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'Microarray' AND " + PUBLIC_CONDITION +
			") as microarray_age, (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION " +
			"JOIN REF_STAGE ON SUB_STAGE_FK = STG_OID WHERE SUB_STAGE_FK BETWEEN ? AND ? " +
			"AND SUB_ASSAY_TYPE = 'NextGen' AND " + PUBLIC_CONDITION +
			") as sequence_age";
	
	public final static String LABS_BY_ISH = "SELECT COUNT(DISTINCT SUB_OID) as TOTAL FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = 'ISH' and SPN_ASSAY_TYPE = ? AND " + PUBLIC_CONDITION ;
	
	public final static String LABS_BY_NON_ISH = "SELECT COUNT(DISTINCT SUB_OID) as TOTAL FROM ISH_SUBMISSION WHERE SUB_SOURCE = ? " +
			"AND SUB_ASSAY_TYPE = ? AND " + PUBLIC_CONDITION ;
	
	/*public final static String ENTRIES_BY_FOCUS_GROUP = "SELECT ( SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " +
			"WHERE  " + PUBLIC_CONDITION + " AND EXP_COMPONENT_ID IN (SELECT LMN_EMAP FROM LKP_METANEPHROS)) AS TOT_MET, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION + 
			" AND EXP_COMPONENT_ID IN (SELECT LUR_EMAP FROM LKP_URINARY)) AS TOT_LUT, (SELECT COUNT(DISTINCT SUB_OID) FROM " +
			"ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
			" AND EXP_COMPONENT_ID IN (SELECT LER_EMAP FROM LKP_EARLY_REPRO)) AS TOT_ERS, (SELECT COUNT(DISTINCT SUB_OID) FROM " +
			"ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
			" AND EXP_COMPONENT_ID IN (SELECT LFR_EMAP FROM LKP_FEMALE_REPRO)) AS TOT_FRS, (SELECT COUNT(DISTINCT SUB_OID) FROM " +
			"ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
			" AND EXP_COMPONENT_ID IN (SELECT LMR_EMAP FROM LKP_MALE_REPRO)) AS TOT_MRS ";*/
	
	public final static String ENTRIES_BY_FOCUS_GROUP = "SELECT SUM(DISTINCT a.TOTAL) TOT_MET,SUM(DISTINCT b.TOTAL) TOT_LUT,SUM(DISTINCT c.TOTAL) TOT_ERS, " +
	"SUM(DISTINCT d.TOTAL) TOT_FRS, SUM(DISTINCT e.TOTAL) TOT_MRS FROM ((SELECT COUNT(DISTINCT SUB_OID) as TOTAL " +
			"FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE EXP_COMPONENT_ID IN (SELECT LMN_EMAP FROM LKP_METANEPHROS) AND " + 
			PUBLIC_CONDITION + " ) " +
			"UNION " +
			"(SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID WHERE IST_COMPONENT IN " +
			"(SELECT LMN_EMAP FROM LKP_METANEPHROS)   AND " + PUBLIC_CONDITION + " AND SUB_ASSAY_TYPE='NextGen' )) as a, " +
			"((SELECT COUNT(DISTINCT SUB_OID) as TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE EXP_COMPONENT_ID IN " +
			"(SELECT LUR_EMAP FROM LKP_URINARY) AND " + PUBLIC_CONDITION + " ) " +
			"UNION " +
			"(SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID WHERE IST_COMPONENT IN " +
			"(SELECT LUR_EMAP FROM LKP_URINARY)   AND " + PUBLIC_CONDITION + " AND SUB_ASSAY_TYPE='NextGen' )) as b, " +
			"((SELECT COUNT(DISTINCT SUB_OID) as TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE EXP_COMPONENT_ID IN " +
			"(SELECT LER_EMAP FROM LKP_EARLY_REPRO) AND " + PUBLIC_CONDITION + " ) " +
			"UNION " +
			"(SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID WHERE IST_COMPONENT IN " +
			"(SELECT LER_EMAP FROM LKP_EARLY_REPRO)   AND " + PUBLIC_CONDITION + " AND SUB_ASSAY_TYPE='NextGen' )) as c, " +
			"((SELECT COUNT(DISTINCT SUB_OID) as TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE EXP_COMPONENT_ID IN " +
			"(SELECT LFR_EMAP FROM LKP_FEMALE_REPRO) AND " + PUBLIC_CONDITION + " ) " +
			"UNION " +
			"(SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID WHERE IST_COMPONENT IN " +
			"(SELECT LFR_EMAP FROM LKP_FEMALE_REPRO)   AND " + PUBLIC_CONDITION + " AND SUB_ASSAY_TYPE='NextGen' )) as d, " +
			"((SELECT COUNT(DISTINCT SUB_OID) as TOTAL FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE EXP_COMPONENT_ID IN " +
			"(SELECT LMR_EMAP FROM LKP_MALE_REPRO) AND " + PUBLIC_CONDITION + " ) " +
			"UNION " +
			"(SELECT COUNT(DISTINCT SUB_OID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID WHERE IST_COMPONENT IN " +
			"(SELECT LMR_EMAP FROM LKP_MALE_REPRO)   AND " + PUBLIC_CONDITION + " AND SUB_ASSAY_TYPE='NextGen' )) as e ";
	
	public final static String GENES_BY_FOCUS_GROUP = "SELECT ( SELECT COUNT(DISTINCT RPR_JAX_ACC) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID " +
	"JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
	" AND EXP_COMPONENT_ID IN (SELECT LMN_EMAP FROM LKP_METANEPHROS)) AS TOT_MET_GENE, (SELECT COUNT(DISTINCT RPR_JAX_ACC) FROM ISH_SUBMISSION  " +
	"JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
	" AND EXP_COMPONENT_ID IN (SELECT LUR_EMAP FROM LKP_URINARY)) AS TOT_LUT_GENE, (SELECT COUNT(DISTINCT RPR_JAX_ACC) FROM ISH_SUBMISSION  " +
	"JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
	" AND EXP_COMPONENT_ID IN (SELECT LER_EMAP FROM LKP_EARLY_REPRO)) AS TOT_ERS_GENE, (SELECT COUNT(DISTINCT RPR_JAX_ACC) FROM ISH_SUBMISSION  " +
	"JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
	" AND EXP_COMPONENT_ID IN (SELECT LFR_EMAP FROM LKP_FEMALE_REPRO)) AS TOT_FRS_GENE, (SELECT COUNT(DISTINCT RPR_JAX_ACC) FROM ISH_SUBMISSION " +
	"JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE  JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE " + PUBLIC_CONDITION +
	" AND EXP_COMPONENT_ID IN (SELECT LMR_EMAP FROM LKP_MALE_REPRO)) AS TOT_MRS_GENE";
	
	public final static String ENTRIES_BY_AGE = "SELECT (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION, REF_STAGE WHERE SUB_STAGE_FK = STG_OID " +
			" AND SUB_STAGE_FK BETWEEN 1 AND 15 AND " + PUBLIC_CONDITION + ") AS TOT_AGE_1, " +
			" (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION, REF_STAGE WHERE SUB_STAGE_FK = STG_OID " +
			" AND SUB_STAGE_FK BETWEEN 16 AND 20 AND " + PUBLIC_CONDITION + ") AS TOT_AGE_2, " +
			" (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION, REF_STAGE WHERE SUB_STAGE_FK = STG_OID " +
			" AND SUB_STAGE_FK BETWEEN 21 AND 22 AND " + PUBLIC_CONDITION + ") AS TOT_AGE_3, " +
			" (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION, REF_STAGE WHERE SUB_STAGE_FK = STG_OID " +
			" AND SUB_STAGE_FK BETWEEN 23 AND 27 AND " + PUBLIC_CONDITION + ") AS TOT_AGE_4, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION, REF_STAGE WHERE SUB_STAGE_FK = STG_OID " +
			" AND SUB_STAGE_FK = 28 AND " + PUBLIC_CONDITION + ") AS TOT_AGE_5";
	
	public final static String ASSAY_TYPES_BY_TISSUE = "SELECT (SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID " +
			"JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE  EXP_COMPONENT_ID IN (SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'ISH' " +
			"AND SPN_ASSAY_TYPE = 'wholemount' AND  " + PUBLIC_CONDITION +  " ) AS wish_tissue, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " +
			"WHERE  EXP_COMPONENT_ID IN (SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'section' AND  " +
			PUBLIC_CONDITION +  " ) AS sish_tissue, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID " +
			"WHERE  EXP_COMPONENT_ID IN (SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'opt-wholemount' AND  " + 
			PUBLIC_CONDITION + " ) AS opt_tissue, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE  EXP_COMPONENT_ID IN " +
			"(SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'IHC' AND  " + 
			PUBLIC_CONDITION + " ) AS ihc_tissue, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE  EXP_COMPONENT_ID IN " +
			"(SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'TG' AND " + 
			PUBLIC_CONDITION + " ) AS tg_tissue, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_EXPRESSION ON EXP_SUBMISSION_FK = SUB_OID WHERE  EXP_COMPONENT_ID IN " +
			"(SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'Microarray' AND  " + 
			PUBLIC_CONDITION + " ) AS microarray_tissue, " +
			"(SELECT COUNT(DISTINCT SUB_OID) FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID WHERE  IST_COMPONENT IN " +
			"(SELECT %s FROM %s) AND SUB_ASSAY_TYPE = 'NextGen' AND " + 
			PUBLIC_CONDITION + " ) AS sequence_tissue";
	

}
