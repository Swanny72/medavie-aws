package ca.medavie.aws.lambda;

public class ParamClass {

	private String name;
	private long longish;
	private String time;
	
	public ParamClass() {
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
	public void setTime(String time) {
		this.time = time;
	}
}
