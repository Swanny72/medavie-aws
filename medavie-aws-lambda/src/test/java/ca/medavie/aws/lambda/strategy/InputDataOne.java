package ca.medavie.aws.lambda.strategy;

public class InputDataOne {
	private String fieldOne;
	private int fieldTwo;
	private String fieldThree;
	
	public InputDataOne() {
		
	}

	public InputDataOne(String fieldOne, int fieldTwo, String fieldThree) {
		super();
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

	public int getFieldTwo() {
		return fieldTwo;
	}

	public void setFieldTwo(int fieldTwo) {
		this.fieldTwo = fieldTwo;
	}

	public String getFieldThree() {
		return fieldThree;
	}

	public void setFieldThree(String fieldThree) {
		this.fieldThree = fieldThree;
	}
	
	

}
