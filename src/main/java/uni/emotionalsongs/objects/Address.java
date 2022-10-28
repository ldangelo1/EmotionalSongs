package uni.emotionalsongs.objects;

public class Address {
	private final String qualifier;
	private final String addrName;
	private final Integer civicNum;
	private final String city;
	private final String province;
	private final Integer cap;
	
	Address(String qualifier, String addrName, Integer civicNum, String city, String province, Integer cap) {
		this.qualifier = qualifier;
		this.addrName = addrName;
		this.civicNum = civicNum;
		this.city = city;
		this.province = province;
		this.cap = cap;
	}
	
	@Override
	public String toString() {
		return qualifier + " " + addrName + " " + civicNum + ", " + city + "(" + province + ")" + cap;
	}
	
	/// Getters
	public String getQualifier() {
		return qualifier;
	}
	
	public String getAddrName() {
		return addrName;
	}
	
	public Integer getCivicNum() {
		return civicNum;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getProvince() {
		return province;
	}
	
	public Integer getCap() {
		return cap;
	}
}