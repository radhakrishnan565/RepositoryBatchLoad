package com.rk.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="claim_repository")
public class MCR_Bean {
@Id
@Column(name = "`Claim_ID`")
String Claim_ID;
@Column(name = "`Claim_Status`")
String Claim_Status;
@Column(name = "`Source_ID`")
String Source_ID;
@Column(name = "`Title`")
String Title;
@Column(name = "`Content_Provider`")
String Content_Provider;
@Column(name = "`Publication_Year`")
String Publication_Year;
@Column(name = "`Volume`")
String Volume;
@Column(name = "`Volume_Text`")
String Volume_Text;
@Column(name = "`Issue`")
String Issue;
@Column(name = "`Issue_Text`")
String Issue_Text;
@Column(name = "`Claim_Problem_Type`")
String Claim_Problem_Type;
@Column(name = "`Claim_Creation_Date`")
String Claim_Creation_Date;
@Column(name = "`DPR_reference`")
String DPR_reference;
@Column(name = "`Priority`")
String Priority;
@Column(name = "`Manifestation`")
String Manifestation;
@Column(name = "`Problem_Description`")
String Problem_Description;
@Column(name = "`Remarks`")
String Remarks;
@Column(name = "`Filename`")
String Filename;
@Column(name = "`Date`")
String Date;


public String getClaim_ID() {
	return Claim_ID;
}
public void setClaim_ID(String claim_ID) {
	Claim_ID = claim_ID;
}
public String getClaim_Status() {
	return Claim_Status;
}
public void setClaim_Status(String claim_Status) {
	Claim_Status = claim_Status;
}
public String getSource_ID() {
	return Source_ID;
}
public void setSource_ID(String source_ID) {
	Source_ID = source_ID;
}
public String getTitle() {
	return Title;
}
public void setTitle(String title) {
	Title = title;
}
public String getContent_Provider() {
	return Content_Provider;
}
public void setContent_Provider(String content_Provider) {
	Content_Provider = content_Provider;
}
public String getPublication_Year() {
	return Publication_Year;
}
public void setPublication_Year(String publication_Year) {
	Publication_Year = publication_Year;
}
public String getVolume() {
	return Volume;
}
public void setVolume(String volume) {
	Volume = volume;
}
public String getVolume_Text() {
	return Volume_Text;
}
public void setVolume_Text(String volume_Text) {
	Volume_Text = volume_Text;
}
public String getIssue() {
	return Issue;
}
public void setIssue(String issue) {
	Issue = issue;
}
public String getIssue_Text() {
	return Issue_Text;
}
public void setIssue_Text(String issue_Text) {
	Issue_Text = issue_Text;
}
public String getClaim_Problem_Type() {
	return Claim_Problem_Type;
}
public void setClaim_Problem_Type(String claim_Problem_Type) {
	Claim_Problem_Type = claim_Problem_Type;
}
public String getClaim_Creation_Date() {
	return Claim_Creation_Date;
}
public void setClaim_Creation_Date(String claim_Creation_Date) {
	Claim_Creation_Date = claim_Creation_Date;
}
public String getDPR_reference() {
	return DPR_reference;
}
public void setDPR_reference(String dPR_reference) {
	DPR_reference = dPR_reference;
}
public String getPriority() {
	return Priority;
}
public void setPriority(String priority) {
	Priority = priority;
}
public String getManifestation() {
	return Manifestation;
}
public void setManifestation(String manifestation) {
	Manifestation = manifestation;
}
public String getProblem_Description() {
	return Problem_Description;
}
public void setProblem_Description(String problem_Description) {
	Problem_Description = problem_Description;
}
public String getRemarks() {
	return Remarks;
}
public void setRemarks(String remarks) {
	Remarks = remarks;
}
public String getFilename() {
	return Filename;
}
public void setFilename(String filename) {
	Filename = filename;
}
public String getDate() {
	return Date;
}
public void setDate(String date) {
	Date = date;
}
public String getObjectToString() {
	return Claim_ID+" - "+Claim_Status+" - "+Source_ID+" - "+Title+" - "+Content_Provider+" - "+Publication_Year+" - "+Volume+" - "+Volume_Text+" - "+Issue+" - "+Issue_Text+" - "+Claim_Problem_Type+" - "+Claim_Creation_Date+" - "+DPR_reference+" - "+Priority+" - "+Manifestation+" - "+Problem_Description+" - "+Remarks+" - "+Filename+" - "+Date;
}
}
