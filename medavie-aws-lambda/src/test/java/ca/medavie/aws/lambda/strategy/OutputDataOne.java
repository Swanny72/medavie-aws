package ca.medavie.aws.lambda.strategy;

public class OutputDataOne {
	private String fieldOne;
	private String fieldTwo;
	private String fieldThree;
	
	public OutputDataOne() {
		// TODO Auto-generated constructor stub
	}

	public OutputDataOne(String fieldOne, String fieldTwo, String fieldThree) {
		this.fieldOne = fieldOne;
		this.fieldTwo = fieldTwo;
		this.fieldThree = fieldThree;
	}

	public String getFieldOne() {
		return fieldOne;
	}

	public void setFieldOne(String fieldOne) {
		this.fieldOne = fieldOne;
	}

	public String getFieldTwo() {
		return fieldTwo;
	}

	public void setFieldTwo(String fieldTwo) {
		this.fieldTwo = fieldTwo;
	}

	public String getFieldThree() {
		return fieldThree;
	}

	public void setFieldThree(String fieldThree) {
		this.fieldThree = fieldThree;
	}
	
	
}
