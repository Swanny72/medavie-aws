package ca.medavie.aws.lambda.strategy;

import ca.medavie.aws.lambda.AbstractResourceMethodStrategy;
import ca.medavie.aws.lambda.MedavieAWSException;

public class StrategyOne extends AbstractResourceMethodStrategy<InputDataOne, OutputDataOne, ParamDataOne>{

	public StrategyOne(Class<InputDataOne> inputClass, Class<ParamDataOne> parameterClass) {
		super(inputClass, parameterClass);
	}

	@Override
	protected OutputDataOne handleRequest(InputDataOne inputData, ParamDataOne params) throws MedavieAWSException {
		OutputDataOne output = new OutputDataOne();
		if (inputData != null && params != null ) {
			output.setFieldOne(inputData.getFieldOne() + " " + params.getParamOne());
			output.setFieldTwo(inputData.getFieldTwo() + " " + params.getParamTwo());
			output.setFieldThree(inputData.getFieldThree() + "  " + params.getParamThree());
		}
		else if (inputData != null) {
			output.setFieldOne(inputData.getFieldOne());
			output.setFieldTwo("" + inputData.getFieldTwo());
			output.setFieldThree(inputData.getFieldThree());
		}
		else if (params != null) {
			output.setFieldOne("" + params.getParamOne());
			output.setFieldTwo(params.getParamTwo());
			output.setFieldThree(params.getParamThree());	
		}

		return output;
	}


}
