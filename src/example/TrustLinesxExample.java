package example;

import java.util.Iterator;

import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.model.Wallet;
import com.jingtum.model.PostResult;
import com.jingtum.model.TrustLine;
import com.jingtum.model.TrustLineCollection;

public class TrustLinesxExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {

		System.out.println("---------获取授信");
		Wallet wallet1 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","snqFcHzRe22JTM8j7iZVpQYzxEEbW"); //根据地址和密钥生成钱包，如只为获取全部授信，密钥可为空
		TrustLineCollection tlc = wallet1.getTrustLine();
		TrustLine tl1;
		
		Iterator<TrustLine> it1 = tlc.getData().iterator();
		Integer i = 0;
		while(it1.hasNext()){		
			i++;
			tl1 = (TrustLine)it1.next();
			System.out.println("---------Trust Line #" + i);
			System.out.println(tl1.getAccount()); //当前账号地址
			System.out.println(tl1.getCounterparty()); //授信方
			System.out.println(tl1.getCurrency()); //货币单位
			System.out.println(tl1.getLimit()); //授信额度
	
		}
		
		System.out.println("---------增加授信");
		Wallet wallet2 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","snqFcHzRe22JTM8j7iZVpQYzxEEbW"); //增加授信密钥为必需值，否则提交会失败
		TrustLine trustline = new TrustLine();
		trustline.setCounterparty("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf");
		trustline.setCurrency("USD");
		trustline.setLimit("700");
		PostResult pr = wallet2.addTrustLine(trustline, true);
		System.out.println(pr.getSuccess());
		System.out.println(pr.getHash());
		System.out.println(pr.getState());
		
		System.out.println("---------end");
	}
}
