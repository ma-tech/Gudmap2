package org.gudmap.queries.generic;

public class GenericQueries {
	
	   public final static String WHERE_CLAUSE = " WHERE ";
	   public final static String FOCUS_RESET = "reset";
	   public final static String EXPRESSION_JOIN = " JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK ";
	   public final static String FOCUS_METANEPHROS = " AND EXP_COMPONENT_ID IN (SELECT LMN_EMAP FROM LKP_METANEPHROS) ";
	   public final static String FOCUS_URINARY = "AND EXP_COMPONENT_ID IN (SELECT LUR_EMAP FROM LKP_URINARY) ";
	   public final static String FOCUS_EARLY_REPRO = " AND EXP_COMPONENT_ID IN (SELECT LER_EMAP FROM LKP_EARLY_REPRO) ";
	   public final static String FOCUS_MALE_REPRO = " AND EXP_COMPONENT_ID IN (SELECT LMR_EMAP FROM LKP_MALE_REPRO) ";
	   public final static String FOCUS_FEMALE_REPRO = " AND EXP_COMPONENT_ID IN (SELECT LFR_EMAP FROM LKP_FEMALE_REPRO) ";
	   public final static String WHERE_WISH = " SPN_ASSAY_TYPE = 'wholemount' AND ";
	   public final static String WHERE_SISH = " SPN_ASSAY_TYPE = 'section' AND ";
	   public final static String WHERE_OPT = " SPN_ASSAY_TYPE = 'opt-wholemount' AND ";
	
	   public final static String ISH_BROWSE_ALL_TABLES = " FROM ISH_SUBMISSION " +
	             "JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK " +
	             "JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
	             "JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
	             "JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK " +
	             "LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
	             "LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT " +
	             "LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
	             "LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
	             "JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK " +
	             "AND IMG_TYPE NOT LIKE '%wlz%' AND IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) "+
	             "JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK ";
	   
	   public final static String PUBLIC_ENTRIES = " SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 ";
	   
	   public final static String INSITU_TOTAL_JOINS = " FROM ISH_SUBMISSION %s JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
										"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
										"WHERE SUB_ASSAY_TYPE = ? AND ";
	   
	   public final static String TG_TOTAL_JOINS = " FROM ISH_SUBMISSION JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID " +
						"JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
						"%s LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID ";
	   
	   public final static String INSITU_VOLATILE_TOTAL_JOINS = " FROM ISH_SUBMISSION %s JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
				"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
				"WHERE SUB_ASSAY_TYPE = ? AND ";
	   
	   public final static String LOGINS = "SELECT USR_UNAME, USR_PASSWD, USR_USER_TYPE FROM REF_USER WHERE USR_UNAME = ? AND USR_PASSWD = ?";
	   
	   public final static String BROWSE_ISH="SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%e %M %Y') submission_date, " + 
			   	"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, SUB_EMBRYO_STG stage, " +
			   "TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
			   	"SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   	"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   	"(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			   "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image " +
			   	"FROM ISH_SUBMISSION  JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			   "LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
			   	"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%wlz%' AND " +
			   "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK " +
			   	"WHERE SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE='ISH'  GROUP BY SUB_ACCESSION_ID  " +
			   "ORDER BY SUB_OID, NATURAL_SORT(RPR_SYMBOL), SUB_EMBRYO_STG, SPN_SEX";
	   
	   
	   public final static String BROWSE_ISH_PARAM="SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%M %%Y') submission_date, " + 
			   	"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, SUB_EMBRYO_STG stage, " +
			    "TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
			   	"SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   	"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   	"(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			    "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image " +
			   	"FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			    "LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
			   	"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%%wlz%%' AND " +
			    "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK " +
			   	"%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE = ?  %s GROUP BY SUB_OID  " +
			    "ORDER BY %s %s, natural_sort(TRIM(RPR_SYMBOL)), SUB_EMBRYO_STG LIMIT ?, ?";
	   
	   public final static String BROWSE_TG_PARAM = "SELECT DISTINCT SUB_OID oid, ALE_GENE gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%M %%Y') submission_date, " +
			   "IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, '' probe_name, SUB_EMBRYO_STG stage, " +
			   "TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) WHEN 'P' THEN CONCAT('P',SPN_STAGE) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
			   "SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   "(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			   "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_SML_FILENAME) image " +
			   "FROM ISH_SUBMISSION %s JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			   "JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
			   "LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND " +
			   "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID AND I.IMG_TYPE NOT LIKE '%%wlz%%') " +
			   "JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK AND SAL_ORDER = 1  " +
			   "%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE = ? %s  GROUP BY SUB_OID  " +
			   "ORDER BY %s %s, natural_sort(TRIM(ALE_GENE)), SUB_EMBRYO_STG LIMIT ? , ?";
	   
	   public final static String BROWSE_TG_PARAM_orig = "SELECT DISTINCT ALE_GENE AS RPR_SYMBOL, SUB_ACCESSION_ID, SUB_SOURCE, SUB_SUB_DATE, " +
			   "IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) SUB_ASSAY_TYPE, '' RPR_JAX_ACC, SUB_EMBRYO_STG, " +
			   "TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) WHEN 'P' THEN CONCAT('P',SPN_STAGE) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) AGE, " +
			   "SPN_SEX, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS GENOTYPE, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME), " +
			   "(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) EXP_STRENGTH, SPN_ASSAY_TYPE, " +
			   "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_SML_FILENAME) THUMBNAIL, REPLACE(SUB_ACCESSION_ID, ':', 'no') " +
			   "FROM ISH_SUBMISSION JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			   "JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
			   "LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND " +
			   "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID AND I.IMG_TYPE NOT LIKE '%wlz%') " +
			   "JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK AND SAL_ORDER = 1  " +
			   "WHERE SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 AND SUB_ASSAY_TYPE = 'TG'  GROUP BY SUB_OID  ORDER BY natural_sort(TRIM(RPR_SYMBOL)), SUB_EMBRYO_STG LIMIT 0 ,20";
	   
	   public final static String ALL_SOURCES="SELECT DISTINCT SUB_SOURCE FROM ISH_SUBMISSION WHERE SUB_SOURCE <> '' ORDER BY SUB_SOURCE";
	   
	   public final static String ALL_THEILER_STAGES="SELECT DISTINCT SUB_EMBRYO_STG FROM ISH_SUBMISSION ORDER BY SUB_EMBRYO_STG";
	   
	   public final static String ALL_ASSAY_TYPES_INSITU="SELECT DISTINCT SUB_ASSAY_TYPE FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE NOT IN ('Microarray','NextGen','NGS') ORDER BY SUB_ASSAY_TYPE ASC";
	   
	   public final static String ALL_SEXES="SELECT DISTINCT SPN_SEX FROM ISH_SPECIMEN WHERE SPN_SEX <> '' ORDER BY SPN_SEX";
	   
	   public final static String ALL_SPECIMEN_TYPES="SELECT DISTINCT SPN_ASSAY_TYPE FROM ISH_SPECIMEN  WHERE SPN_ASSAY_TYPE NOT IN ('','unspecified') ORDER BY SPN_ASSAY_TYPE";
	   
	  // public final static String ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION="SELECT COUNT(DISTINCT SUB_ACCESSION_ID) AS TOTAL FROM ISH_SUBMISSION %s WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+ "%s %s";
	   public final static String ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION="SELECT COUNT(DISTINCT SUB_ACCESSION_ID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID %s WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+ "%s %s";

}
