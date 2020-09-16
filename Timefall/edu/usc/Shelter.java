package Timefall.edu.usc;

import javax.xml.crypto.NodeSetData;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class Shelter extends IntegerComparable {
	
	 @SerializedName("chiralFrequency")
	 @Expose
	private Integer chiralFrequency;
	 @SerializedName("timefall")
	 @Expose
	private Boolean timefall;
	 @SerializedName("guid")
	 @Expose
	private String guid;
	 @SerializedName("name")
	 @Expose
	private String name;
	 @SerializedName("phone")
	 @Expose
	private String phone;
	 @SerializedName("address")
	 @Expose
	private String address;
	
	public Shelter(Integer i, Boolean t, String gui, String n, String p, String a) {
		this.chiralFrequency = i;
		this.timefall = t;
		this.guid = gui;
		this.name = n;
		this.phone = p;
		this.address = a;
	}

	public Integer getChiralFrequency() {
	return chiralFrequency;
	}

	public String toStringPrune() {
		String string = "   {";
		string += "Shelter Information: \n";
		string += "		\"Chiral Frequency\": " + this.getChiralFrequencyString() + ", "+ "\n";
		string += "		\"Timefall\": " + this.getTimefallstring() + "\n";
		string += "		\"GUID\": " + this.getGuid() + "\n";
		string += "		\"Name\": " + this.getName() + "\n";
		string += "		\"Phone\": " + this.getPhone() + "\n";
		string += "		\"Address\": " + this.getAddress() + "\n";
		string += "	}";
		return string;
	}
	public String getChiralFrequencyString() {
		return String.valueOf(chiralFrequency);
	}
	public void setChiralFrequency(Integer chiralFrequency) {
	this.chiralFrequency = chiralFrequency;
	}

	public Boolean getTimefall() {
	return timefall;
	}
	
	public String getTimefallstring() {
		if(timefall) return "Current";
		else return "None";
		}

	public void setTimefall(Boolean timefall) {
	this.timefall = timefall;
	}

	public String getGuid() {
	return guid;
	}

	public void setGuid(String guid) {
	this.guid = guid;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getPhone() {
	return phone;
	}

	public void setPhone(String phone) {
	this.phone = phone;
	}

	public String getAddress() {
	return address;
	}

	public void setAddress(String address) {
	this.address = address;
	}
	
	/**
	 * String representation of a shelter
	 */
	public String toString() {	
		String shelter = "";
		shelter += "Shelter Information: \n";
		shelter += "- Chiral Frequency: " + this.getChiralFrequencyString() + "\n";
		shelter += "- Timefall: " + this.getTimefallstring() + "\n";
		shelter += "- GUID: " + this.getGuid() + "\n";
		shelter += "- Name: " + this.getName() + "\n";
		shelter += "- Phone: " + this.getPhone() + "\n";
		shelter += "- Address: " + this.getAddress() + "\n";
		return shelter;
		
	}

	/**
	 * Returns integer value to compare on
	 */
	@Override
	public int getCompareValue() {
		return chiralFrequency;
	}


}