package cn.thinking.lambda.functional;

public class ProduceServiceImpl implements ProduceService {

	@Override
	public String info(String param) {
		System.out.println(ProduceService.class.getName() + " : " + param);
		return ProduceService.class.getName() + " : " + param;
	}

}
