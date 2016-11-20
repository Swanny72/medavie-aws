package ca.medavie.aws.lambda.strategy;

public class ParamDataOne {
	
	private int paramOne;
	private String paramTwo;
	private String paramThree;
	
	public ParamDataOne() {
		
	}

	public ParamDataOne(int paramOne, String paramTwo, String paramThree) {
		super();
		this.paramOne = paramOne;
		this.paramTwo = paramTwo;
		this.paramThree = paramThree;
	}

	public int getParamOne() {
		return paramOne;
	}

	public void setParamOne(int paramOne) {
		this.paramOne = paramOne;
	}

	public String getParamTwo() {
		return paramTwo;
	}

	public void setParamTwo(String paramTwo) {
		this.paramTwo = paramTwo;
	}

	public String getParamThree() {
		return paramThree;
	}

	public void setParamThree(String paramThree) {
		this.paramThree = paramThree;
	}

}
