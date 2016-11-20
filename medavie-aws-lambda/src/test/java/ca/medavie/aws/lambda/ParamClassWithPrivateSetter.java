package ca.medavie.aws.lambda;

public class ParamClassWithPrivateSetter {
	private String name;
	private long longish;
	private String time;
	
	public ParamClassWithPrivateSetter() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLongish() {
		return longish;
	}
	public void setLongish(long longish) {
		this.longish = longish;
	}
	public String getTime() {
		return time;
	}
	private void setTime(String time) {
		this.time = time;
	}

}
