package example;

import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.util.Utility;

public class UtilityExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {
		UtilityExample utilityExample = new UtilityExample();
		Utility util = new Utility();
		
		System.out.println("---------获取 server连接信息");
		System.out.println(util.isConnected());
		
		System.out.println("---------获取 uuid");
		System.out.println(util.getUuid());
		
		System.out.println("---------end");
	}

}
