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
	
	/*public final static String ENTRIES_AND_GENES = "SELECT (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='IHC' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS ihc, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='TG' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS tg, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='Microarray' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS microarray, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_ASSAY_TYPE='NextGen' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS sequence, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID " +
			"WHERE SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE = 'wholemount' AND SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS wish, " +
			"(SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID WHERE SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE = 'section' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS sish, (SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID " +
			"WHERE SUB_ASSAY_TYPE='ISH' AND SPN_ASSAY_TYPE = 'opt-wholemount' AND SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS opt, " +
			"(SELECT COUNT(SUB_ASSAY_TYPE) FROM ISH_SUBMISSION WHERE SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_entries, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE WHERE SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"WHERE SUB_ASSAY_TYPE = 'IHC' AND SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_ihc_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"WHERE SUB_ASSAY_TYPE = 'TG' AND SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_tg_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID " +
			"JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE WHERE SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'wholemount' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_wish_genes, (SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION " +
			"JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE " +
			"WHERE SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'section' AND SUB_IS_PUBLIC = 1 AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_sish_genes, " +
			"(SELECT COUNT(DISTINCT RPR_LOCUS_TAG) FROM ISH_SUBMISSION JOIN ISH_SPECIMEN ON SPN_SUBMISSION_FK = SUB_OID JOIN ISH_PROBE ON PRB_SUBMISSION_FK = SUB_OID " +
			"JOIN REF_PROBE ON RPR_OID = PRB_MAPROBE WHERE SUB_ASSAY_TYPE = 'ISH' AND SPN_ASSAY_TYPE = 'opt-wholemount' AND SUB_IS_PUBLIC = 1 " +
			"AND SUB_DB_STATUS_FK = 4 AND SUB_IS_DELETED = 0) AS total_opt_genes";*/

}
