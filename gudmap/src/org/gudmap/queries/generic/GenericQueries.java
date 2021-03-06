package org.gudmap.queries.generic;

public class GenericQueries {
	
	   public final static String UNION_CLAUSE = " UNION ";   
	   public final static String WHERE_CLAUSE = " WHERE ";
	   public final static String FOCUS_RESET = "reset";
	   public final static String EXPRESSION_JOIN = " JOIN ISH_EXPRESSION ON SUB_OID = EXP_SUBMISSION_FK ";
	   public final static String FOCUS_METANEPHROS = " AND EXP_COMPONENT_ID IN (SELECT LMN_EMAP FROM LKP_METANEPHROS) ";
	   public final static String FOCUS_MESONEPHROS = " AND EXP_COMPONENT_ID IN (SELECT LMS_EMAP FROM LKP_MESONEPHROS) ";
	   public final static String FOCUS_URINARY = "AND EXP_COMPONENT_ID IN (SELECT LUR_EMAP FROM LKP_URINARY) ";
	   public final static String FOCUS_EARLY_REPRO = " AND EXP_COMPONENT_ID IN (SELECT LER_EMAP FROM LKP_EARLY_REPRO) ";
	   public final static String FOCUS_MALE_REPRO = " AND EXP_COMPONENT_ID IN (SELECT LMR_EMAP FROM LKP_MALE_REPRO) ";
	   public final static String FOCUS_FEMALE_REPRO = " AND EXP_COMPONENT_ID IN (SELECT LFR_EMAP FROM LKP_FEMALE_REPRO) ";
	   public final static String FOCUS_METANEPHROS_SP = " AND IST_COMPONENT IN (SELECT LMN_EMAP FROM LKP_METANEPHROS) ";
	   public final static String FOCUS_MESONEPHROS_SP = " AND IST_COMPONENT IN (SELECT LMS_EMAP FROM LKP_MESONEPHROS) ";
	   public final static String FOCUS_URINARY_SP = "AND IST_COMPONENT IN (SELECT LUR_EMAP FROM LKP_URINARY) ";
	   public final static String FOCUS_EARLY_REPRO_SP = " AND IST_COMPONENT IN (SELECT LER_EMAP FROM LKP_EARLY_REPRO) ";
	   public final static String FOCUS_MALE_REPRO_SP = " AND IST_COMPONENT IN (SELECT LMR_EMAP FROM LKP_MALE_REPRO) ";
	   public final static String FOCUS_FEMALE_REPRO_SP = " AND IST_COMPONENT IN (SELECT LFR_EMAP FROM LKP_FEMALE_REPRO) ";
	   public final static String WHERE_WISH = " SPN_ASSAY_TYPE = 'wholemount' AND ";
	   public final static String WHERE_SISH = " SPN_ASSAY_TYPE = 'section' AND ";
	   public final static String WHERE_OPT = " SPN_ASSAY_TYPE = 'opt-wholemount' AND ";
	   public final static String THEILER_URL_1 = "http://www.emouseatlas.org/emap/ema/theiler_stages/StageDefinition/ts";
	   public final static String THEILER_URL_2 = "definition.html";
	
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
				"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
				"WHERE SUB_ASSAY_TYPE = ? AND ";
	   
	  public final static String TG_TOTAL_JOINS = " FROM ISH_SUBMISSION JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID " +
				"JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			    "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK "+
				"%s LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID ";
	   
	   public final static String INSITU_VOLATILE_TOTAL_JOINS = " FROM ISH_SUBMISSION %s JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
				"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
				"WHERE SUB_ASSAY_TYPE = ? AND ";
	   
	   public final static String LOGINS = "SELECT USR_UNAME, USR_PASSWD, USR_USER_TYPE, USR_PRIV_LEVEL, USR_OID FROM REF_USER WHERE USR_UNAME = ? AND USR_PASSWD = ?";
	   
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
	   
	   
	  /* public final static String BROWSE_ISH_PARAM="SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " + 
			   	"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, STG_STAGE_DISPLAY stage, " +
			    "STG_SPECIES species, IF(INSTR(STG_ALT_STAGE,'(')>0, SUBSTRING(STG_ALT_STAGE,1,INSTR(STG_ALT_STAGE,'(')-1), STG_ALT_STAGE ) age, " +
			   	"SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   	"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   	"(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			    "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image, " +
			   	"RPR_LOCUS_TAG gene_id " +
			   	"FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			    "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
			    "LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
			   	"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%%wlz%%' AND " +
			    "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK " +
			   	"%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE = ?  %s GROUP BY SUB_OID  " +
			    "ORDER BY %s %s, natural_sort(TRIM(RPR_SYMBOL)), SUB_EMBRYO_STG LIMIT ?, ?";*/
	   
	   public final static String BROWSE_ISH_PARAM="SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " + 
			   	"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, STG_STAGE_DISPLAY stage, " +
			    "STG_SPECIES species, TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
			   	"SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   	"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   	"(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			    "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image, " +
			   	"RPR_LOCUS_TAG gene_id " +
			   	"FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			    "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
			    "LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
			   	"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%%wlz%%' AND " +
			    "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK " +
			   	"%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE = ?  %s GROUP BY SUB_OID  " +
			    "ORDER BY %s %s, natural_sort(TRIM(RPR_SYMBOL)), SUB_EMBRYO_STG LIMIT ?, ?";
	   
	   
	   /*public final static String BROWSE_TG_PARAM = "SELECT DISTINCT SUB_OID oid, ALE_GENE gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " +
			   "IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, '' probe_name, STG_STAGE_DISPLAY stage, " +
			   "STG_SPECIES species, IF(INSTR(STG_ALT_STAGE,'(')>0, SUBSTRING(STG_ALT_STAGE,1,INSTR(STG_ALT_STAGE,'(')-1), STG_ALT_STAGE ) age, " +
			   "SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   "(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			   "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_SML_FILENAME) image, " +
			   "ALE_MUTATED_GENE_ID gene_id " +
			   "FROM ISH_SUBMISSION %s JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			   "JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
			   "LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND " +
			   "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID AND I.IMG_TYPE NOT LIKE '%%wlz%%') " +
			   "JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK AND SAL_ORDER = 1  " +
			   "%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE = ? %s  GROUP BY SUB_OID  " +
			   "ORDER BY %s %s, natural_sort(TRIM(ALE_GENE)), SUB_EMBRYO_STG LIMIT ? , ?";*/
	   
	   public final static String BROWSE_TG_PARAM = "SELECT DISTINCT SUB_OID oid, ALE_GENE gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " +
			   "IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, '' probe_name, STG_STAGE_DISPLAY stage, " +
			   "STG_SPECIES species, TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
			   "SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			   "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   "(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			   "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_SML_FILENAME) image, " +
			   "ALE_MUTATED_GENE_ID gene_id " +
			   "FROM ISH_SUBMISSION %s JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID JOIN ISH_ALLELE ON SAL_ALE_OID_FK=ALE_OID JOIN ISH_PERSON ON SUB_PI_FK = PER_OID " +
			   "JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID " +
			   "LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND " +
			   "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID AND I.IMG_TYPE NOT LIKE '%%wlz%%') " +
			   "JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK AND SAL_ORDER = 1  " +
			   "%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE = ? %s  GROUP BY SUB_OID  " +
			   "ORDER BY %s %s, natural_sort(TRIM(ALE_GENE)), SUB_EMBRYO_STG LIMIT ? , ?";
	   
	   public final static String BROWSE_OPT_PARAM="SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " + 
			   	"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, STG_STAGE_DISPLAY stage, " +
			    "STG_SPECIES species, TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
			   	"SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) " +
			   	"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE (SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) " +
			    "FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype, GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, " +
			   	"(SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression, SPN_ASSAY_TYPE specimen, " +
			    "CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image, " +
			   	"RPR_LOCUS_TAG gene_id " +
			   	"FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK " +
			    "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
			    "LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID " +
			   	"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%%wlz%%' AND " +
			    "IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK " +
			   	"%s SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE IN (?,?)  %s GROUP BY SUB_OID  " +
			    "ORDER BY %s %s, natural_sort(TRIM(RPR_SYMBOL)), SUB_EMBRYO_STG LIMIT ?, ?";
	   
	   
	   public final static String ALL_SOURCES="SELECT DISTINCT SUB_SOURCE FROM ISH_SUBMISSION WHERE SUB_SOURCE <> '' ORDER BY SUB_SOURCE";
	   
	   public final static String ALL_PUBLIC_SOURCES= "SELECT DISTINCT(SUBSTRING(SUB_SOURCE,INSTR(SUB_SOURCE,'-')+1)) AS abbrv, SUB_SOURCE AS lab FROM `ISH_SUBMISSION` WHERE SUB_SOURCE <> '' " +
			   "AND SUB_IS_PUBLIC=1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 ORDER BY abbrv";
	   
	   public final static String ALL_SOURCES_PER_ASSAY="SELECT DISTINCT SUB_SOURCE FROM ISH_SUBMISSION WHERE SUB_SOURCE <> '' " +
			   "AND SUB_ASSAY_TYPE = ? ORDER BY SUB_SOURCE";
	   
	   public final static String ALL_THEILER_STAGES="SELECT DISTINCT STG_ORDER, STG_STAGE_DISPLAY FROM REF_STAGE, ISH_SUBMISSION WHERE "+
			   "STG_OID=SUB_STAGE_FK AND STG_SPECIES='Mus musculus' ORDER BY STG_ORDER";
	   
	   public final static String ALL_CARNEGIE_STAGES="SELECT DISTINCT STG_ORDER, STG_STAGE_DISPLAY FROM REF_STAGE, ISH_SUBMISSION WHERE "+
			   "STG_OID=SUB_STAGE_FK AND STG_SPECIES='Homo sapiens' ORDER BY STG_ORDER";
	   
	   public final static String ALL_ASSAY_TYPES_INSITU="SELECT DISTINCT SUB_ASSAY_TYPE FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE NOT IN ('Microarray','NextGen','NGS') ORDER BY SUB_ASSAY_TYPE ASC";

	   public final static String ALL_ASSAY_TYPES="SELECT DISTINCT SUB_ASSAY_TYPE FROM ISH_SUBMISSION ORDER BY SUB_ASSAY_TYPE ASC";
	   
	   public final static String ALL_SEXES="SELECT DISTINCT SPN_SEX FROM ISH_SPECIMEN WHERE SPN_SEX <> '' ORDER BY SPN_SEX";
	   
	   public final static String ALL_SPECIMEN_TYPES="SELECT DISTINCT SPN_ASSAY_TYPE FROM ISH_SPECIMEN  WHERE SPN_ASSAY_TYPE NOT IN ('','unspecified') ORDER BY SPN_ASSAY_TYPE";
	   
	   public final static String ASSAY_TYPE_TOTAL_GUDMAP_ACCESSION="SELECT COUNT(DISTINCT SUB_ACCESSION_ID) AS TOTAL FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK=SUB_OID " +
			   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK %s WHERE SUB_ASSAY_TYPE = ? AND "+GenericQueries.PUBLIC_ENTRIES+ "%s %s";
	  
	  
	   public final static String BROWSE_ACCESSION_PARAM = "SELECT DISTINCT x.oid, x.gene, x.gudmap_accession, x.source, x.submission_date, x.assay_type, x.probe_name, x.stage, x.species, x.age, x.sex, x.genotype, " +
			   "GROUP_CONCAT(DISTINCT x.tissue) tissue, x.expression, x.specimen, x.image, x.gene_id " +
				"FROM ((" +
				"SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " + 
				"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, STG_STAGE_DISPLAY stage, STG_SPECIES species, " +
				"TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, SPN_SEX sex,  " +
				"CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype,  " +
				"GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, (SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression,  " +
				"SPN_ASSAY_TYPE specimen, CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image,  " +
				"RPR_LOCUS_TAG gene_id " +
				"FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK  " +
				"JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
				"LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID  " +
				"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%%wlz%%'  " +
				"AND IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK  " + 
				"%s  ( SUB_ACCESSION_ID IN (%s) " +
				"OR RPR_MTF_JAX IN (%s) " +
				"OR CONCAT(RPR_PREFIX,RPR_OID) IN (%s)  " + 
				"OR RPR_LOCUS_TAG  IN (%s)  " + 
				"OR RPR_ENSEMBL  IN (%s)  ) " +
				"AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE NOT IN ('Microarray','NextGen') %s  GROUP BY oid )  " +
				      
				"union (SELECT DISTINCT SUB_OID oid, '' gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " +
				"'Microarray' assay_type, '' probe_name, STG_STAGE_DISPLAY stage,  STG_SPECIES species, " +
				"TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, " +
				"SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE 'NOVALUE' END AS genotype,  " +
				"GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME SEPARATOR ', ') tissue, '' expression, " +
				"'' specimen, '' image , '' gene_id " +
				"FROM ISH_SUBMISSION, ISH_EXPRESSION, ISH_SPECIMEN, REF_STAGE, ANA_NODE, ANA_TIMED_NODE, ISH_SP_TISSUE  " +
				"%s SUB_ACCESSION_ID   IN (%s)   AND SUB_ASSAY_TYPE = 'Microarray' AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 AND EXP_SUBMISSION_FK=SUB_OID AND " + 
				"SPN_SUBMISSION_FK=SUB_OID AND STG_OID = SUB_STAGE_FK AND ATN_PUBLIC_ID = EXP_COMPONENT_ID AND ATN_NODE_FK = ANO_OID AND IST_SUBMISSION_FK=SUB_OID %s ) " +
				
				"union (SELECT DISTINCT SUB_OID oid, '' gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date,  " +
				"'Sequence' assay_type, '' probe_name, STG_STAGE_DISPLAY stage, STG_SPECIES species, TRIM(CASE NGS_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(NGS_DEV_STAGE,' ',NGS_STAGE_FORMAT) ELSE CONCAT(NGS_STAGE_FORMAT,NGS_DEV_STAGE) END) age, " +
				//"'Sequence' assay_type, '' probe_name, STG_STAGE_DISPLAY stage, STG_SPECIES species, " +
				//"IF(INSTR(STG_ALT_STAGE,'(')>0, SUBSTRING(STG_ALT_STAGE,1,INSTR(STG_ALT_STAGE,'(')-1), STG_ALT_STAGE ) age, NGS_SEX sex,  " +
				"NGS_SEX sex, " +
				"CASE NGS_GENOTYPE WHEN 'true' THEN (CASE NGS_SPECIES WHEN 'Homo sapiens' THEN '' ELSE 'wild type' END) ELSE " +
				"CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME)  " +
				"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME)  " +
				"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END genotype, " +
				"GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME SEPARATOR '; ') tissue, '' expression,  " +
				"'' specimen,   '' image , '' gene_id " +
				"FROM ISH_SUBMISSION JOIN NGD_SAMPLE ON NGS_SUBMISSION_FK = SUB_OID JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK=SUB_OID  " +
				"JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
				"JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID=IST_COMPONENT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID LEFT JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID  " +
				"LEFT JOIN ISH_ALLELE ON SAL_ALE_OID_FK = ALE_OID %s SUB_ASSAY_TYPE = 'NextGen' AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4   " +
				"AND SUB_ACCESSION_ID  IN (%s) %s  ) " +
				
				"ORDER BY gene  ASC , assay_type, FIELD(expression, 'present', 'uncertain', 'not detected', ''), stage, tissue  ) AS x  " +
				"GROUP BY x.gudmap_accession  ORDER BY %s %s, x.assay_type, x.gene, FIELD(x.expression, 'present', 'uncertain', 'not detected', ''), x.stage, x.tissue, x.sex LIMIT ? , ?";
	   
	   public final static String ISH_ACCESSION_TOTAL = "SELECT COUNT(DISTINCT SUB_OID) TOTAL_INSITU FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK " +
			   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
			   "LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			   "%s  ( SUB_ACCESSION_ID   IN (%s)  " +
				"OR RPR_MTF_JAX  IN (%s)  " +
				"OR CONCAT(RPR_PREFIX,RPR_OID)  IN (%s) " + 
				"OR RPR_LOCUS_TAG IN (%s) " + 
				"OR RPR_ENSEMBL  IN (%s)  ) " +
			   "AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 AND SUB_ASSAY_TYPE NOT IN ('Microarray','NextGen') %s";
	   
	   public final static String MICROARRAY_ACCESSION_TOTAL = "SELECT COUNT(DISTINCT SUB_OID) TOTAL_MICROARRAY FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE " +
			   "ON IST_SUBMISSION_FK=SUB_OID " +
			   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
			   "%s  SUB_ACCESSION_ID  IN (%s)   AND SUB_ASSAY_TYPE = 'Microarray' AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 %s";
	   
	   public final static String SEQUENCE_ACCESSION_TOTAL = "SELECT COUNT(DISTINCT SUB_OID) TOTAL_SEQUENCE FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE " +
			   "ON IST_SUBMISSION_FK=SUB_OID " +
			   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
			   "%s  SUB_ACCESSION_ID  IN (%s)   AND SUB_ASSAY_TYPE = 'NextGen' AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 %s"; 
	   
       public final static String EQUIVALENT_DPC_STAGE_FOR_THEILER_STAGE = "SELECT STG_ALT_STAGE FROM REF_STAGE WHERE STG_STAGE_DISPLAY = ?";
       
       public final static String BROWSE_LOCAL_STORAGE_PARAM = "SELECT DISTINCT x.oid, x.gene, x.gudmap_accession, x.source, x.submission_date, x.assay_type, x.probe_name, x.stage, x.species, x.age, x.sex, x.genotype, " +
			   "GROUP_CONCAT(DISTINCT x.tissue) tissue, x.expression, x.specimen, x.image, x.gene_id " +
				"FROM ((" +
				"SELECT DISTINCT SUB_OID oid, RPR_SYMBOL gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " + 
				"IF(SUB_CONTROL=0,SUB_ASSAY_TYPE,CONCAT(SUB_ASSAY_TYPE,' control')) assay_type, RPR_JAX_ACC probe_name, STG_STAGE_DISPLAY stage, STG_SPECIES species, " +
				"TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, SPN_SEX sex,  " +
				"CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE CASE WHEN  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END AS genotype,  " +
				"GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME) tissue, (SELECT GROUP_CONCAT(DISTINCT EXP_STRENGTH) FROM ISH_EXPRESSION WHERE EXP_SUBMISSION_FK=SUB_OID) expression,  " +
				"SPN_ASSAY_TYPE specimen, CONCAT(IMG_URL.URL_URL, IMG_FILEPATH, IMG_URL.URL_SUFFIX, IMG_SML_FILENAME) image,  " +
				"RPR_LOCUS_TAG gene_id " +
				"FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK JOIN ISH_PERSON ON SUB_PI_FK = PER_OID JOIN ISH_SPECIMEN ON SUB_OID = SPN_SUBMISSION_FK  " +
				"JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
				"LEFT JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK = SUB_OID LEFT JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID = IST_COMPONENT LEFT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID  " +
				"LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE JOIN ISH_ORIGINAL_IMAGE ON SUB_OID = IMG_SUBMISSION_FK AND IMG_TYPE NOT LIKE '%%wlz%%'  " +
				"AND IMG_ORDER = (SELECT MIN(I.IMG_ORDER) FROM ISH_ORIGINAL_IMAGE I WHERE I.IMG_SUBMISSION_FK = SUB_OID) JOIN REF_URL IMG_URL ON IMG_URL.URL_OID = IMG_URL_FK  " + 
				"%s   SUB_OID IN (%s) " +
				"AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4  AND SUB_ASSAY_TYPE NOT IN ('Microarray','NextGen') %s  GROUP BY oid )  " +
				      
				"union (SELECT DISTINCT SUB_OID oid, '' gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date, " +
				"'Microarray' assay_type, '' probe_name, STG_STAGE_DISPLAY stage, STG_SPECIES species, " +
				"TRIM(CASE SPN_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(SPN_STAGE,' ',SPN_STAGE_FORMAT) ELSE CONCAT(SPN_STAGE_FORMAT,SPN_STAGE) END) age, SPN_SEX sex, CASE SPN_WILDTYPE WHEN 'Wild Type' THEN 'wild type' ELSE 'NOVALUE' END AS genotype,  " +
				"GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME SEPARATOR ', ') tissue, '' expression, " +
				"'' specimen, '' image, '' gene_id " +
				"FROM ISH_SUBMISSION, ISH_EXPRESSION, ISH_SPECIMEN, REF_STAGE, ANA_NODE, ANA_TIMED_NODE, ISH_SP_TISSUE  " +
				"%s SUB_OID   IN (%s)   AND SUB_ASSAY_TYPE = 'Microarray' AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4 AND EXP_SUBMISSION_FK=SUB_OID AND " + 
				"SPN_SUBMISSION_FK=SUB_OID AND STG_OID = SUB_STAGE_FK AND ATN_PUBLIC_ID = EXP_COMPONENT_ID AND ATN_NODE_FK = ANO_OID AND IST_SUBMISSION_FK=SUB_OID %s ) " +
				
				"union (SELECT DISTINCT SUB_OID oid, '' gene, SUB_ACCESSION_ID gudmap_accession, SUB_SOURCE source, DATE_FORMAT(SUB_SUB_DATE,'%%e %%b %%Y') submission_date,  " +
				"'Sequence' assay_type, '' probe_name, STG_STAGE_DISPLAY stage, STG_SPECIES species, " +
				"TRIM(CASE NGS_STAGE_FORMAT WHEN 'dpc' THEN CONCAT(NGS_DEV_STAGE,' ',NGS_STAGE_FORMAT) ELSE CONCAT(NGS_STAGE_FORMAT,NGS_DEV_STAGE) END) age, NGS_SEX sex,  " +
				"CASE NGS_GENOTYPE WHEN 'true' THEN (CASE NGS_SPECIES WHEN 'Homo sapiens' THEN '' ELSE 'wild type' END) ELSE " +
				"CASE WHEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME)  " +
				"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) IS NOT NULL THEN (SELECT DISTINCT GROUP_CONCAT(ALE_ALLELE_NAME)  " +
				"FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) ELSE  " +
				"(SELECT DISTINCT GROUP_CONCAT(ALE_LAB_NAME_ALLELE) FROM ISH_ALLELE, LNK_SUB_ALLELE  WHERE SAL_ALE_OID_FK=ALE_OID AND SAL_SUBMISSION_FK=SUB_OID) END  END genotype, " +
				"GROUP_CONCAT(DISTINCT ANO_COMPONENT_NAME SEPARATOR '; ') tissue, '' expression,  " +
				"'' specimen,   '' image, '' gene_id  " +
				"FROM ISH_SUBMISSION JOIN NGD_SAMPLE ON NGS_SUBMISSION_FK = SUB_OID JOIN ISH_SP_TISSUE ON IST_SUBMISSION_FK=SUB_OID  " +
				"JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
				"JOIN ANA_TIMED_NODE ON ATN_PUBLIC_ID=IST_COMPONENT JOIN ANA_NODE ON ATN_NODE_FK = ANO_OID LEFT JOIN LNK_SUB_ALLELE ON SAL_SUBMISSION_FK = SUB_OID  " +
				"LEFT JOIN ISH_ALLELE ON SAL_ALE_OID_FK = ALE_OID %s SUB_ASSAY_TYPE = 'NextGen' AND SUB_IS_PUBLIC = 1 AND SUB_IS_DELETED = 0 AND SUB_DB_STATUS_FK = 4   " +
				"AND SUB_OID  IN (%s) %s  ) " +
				
				"ORDER BY gene  ASC , assay_type, FIELD(expression, 'present', 'uncertain', 'not detected', ''), stage, tissue  ) AS x  " +
				"GROUP BY x.gudmap_accession  ORDER BY %s %s, x.assay_type, x.gene, FIELD(x.expression, 'present', 'uncertain', 'not detected', ''), x.stage, x.tissue, x.sex LIMIT ? , ?";
       
		       public final static String ISH_OID_TOTAL = "SELECT COUNT(DISTINCT SUB_OID) TOTAL_INSITU FROM ISH_SUBMISSION %s JOIN ISH_PROBE ON SUB_OID = PRB_SUBMISSION_FK " +
					   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
					   "LEFT JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
					   "%s  SUB_OID  IN (%s) AND SUB_ASSAY_TYPE NOT IN ('Microarray','NextGen') AND " + PUBLIC_ENTRIES +" %s";
			   
			   public final static String MICROARRAY_OID_TOTAL = "SELECT COUNT(DISTINCT SUB_OID) TOTAL_MICROARRAY FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE " +
					   "ON IST_SUBMISSION_FK=SUB_OID " +
					   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
					   "%s  SUB_OID  IN (%s)   AND SUB_ASSAY_TYPE = 'Microarray' AND " + PUBLIC_ENTRIES +" %s"; 
			   
			   public final static String SEQUENCE_OID_TOTAL = "SELECT COUNT(DISTINCT SUB_OID) TOTAL_SEQUENCE FROM ISH_SUBMISSION JOIN ISH_SP_TISSUE " +
					   "ON IST_SUBMISSION_FK=SUB_OID " +
					   "JOIN REF_STAGE ON STG_OID = SUB_STAGE_FK " +
					   "%s  SUB_OID  IN (%s)   AND SUB_ASSAY_TYPE = 'NextGen' AND " + PUBLIC_ENTRIES +" %s"; 
       
	   
	   			//stages in db
       
   		public final static String STAGES_FROM_REF_STAGE = "SELECT DISTINCT(STG_STAGE_DISPLAY) FROM ISH_SUBMISSION, REF_STAGE " +
   				"WHERE STG_OID = SUB_STAGE_FK AND STG_SPECIES = ? ORDER BY STG_ORDER";
   		
   		//update statistics
		public final static String GET_UPDATE_INFO = "SELECT DATE_FORMAT(MIS_SOFT_UPDATE,'%e %b %Y') software_update, DATE_FORMAT(MIS_EDIT_UPDATE,'%e %b %Y') editorial_update, MIS_SOFT_VERSION software_version FROM REF_MISC";
		 
	   	public final static String GET_UPDATE_INFO_DB = "SELECT MIS_SOFT_UPDATE software_update, MIS_EDIT_UPDATE editorial_update FROM REF_MISC";
	   	
		public final static String UPDATE_INFO = "UPDATE REF_MISC SET MIS_SOFT_UPDATE = ?, MIS_EDIT_UPDATE = ?, MIS_SOFT_VERSION = ?";
		
	


	   
}
