package example;

import java.util.Iterator;

import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.model.Transaction;
import com.jingtum.model.TransactionCollection;
import com.jingtum.model.Wallet;

public class TransactionExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {
		TransactionExample transactionExample = new TransactionExample();
		Wallet wallet = new Wallet("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf",null);
		Transaction tran;
		
		System.out.println("---------获取所有transaction");
		TransactionCollection tc = wallet.getTransactions(null,true,Transaction.DirectionType.all); //参数为：支付交易的接收方地址，是否移除失败的交易历史，支付交易的方向，incoming或outgoing
		Iterator it1 = tc.getData().iterator();
		Integer i = 0;
		while(it1.hasNext())
		{
			i++;
			tran = (Transaction)it1.next();
			System.out.println("---------Transaction #" + i);
			System.out.println(tran.getType()); //交易类型
			System.out.println(tran.getClient_resource_id()); //交易资源号
			System.out.println(tran.getCounterparty()); //交易对家
			System.out.println(tran.getDate()); //交易时间，UNIXTIME
			System.out.println(tran.getFee()); //交易费用，井通计价
			System.out.println(tran.getHash()); //交易hash
			System.out.println(tran.getResult()); //交易结果
			System.out.println(tran.getAmount().getCurrency()); //货币单位
			System.out.println(tran.getAmount().getIssuer()); //货币发行方
			System.out.println(tran.getAmount().getValue()); //金额
		}
		
		System.out.println("---------根据hash获取transaction");
		tran = wallet.getTransactionByHash("AE85E50D4E9A77EF00A1FA2EE416DB3789F28381D267436E8A82493CC0791B62"); //参数：hash值
		System.out.println(tran.getType());
		System.out.println(tran.getClient_resource_id());
		System.out.println(tran.getCounterparty());
		System.out.println(tran.getDate());
		System.out.println(tran.getFee());
		System.out.println(tran.getHash());
		System.out.println(tran.getResult());
		System.out.println(tran.getAmount().getCurrency());
		System.out.println(tran.getAmount().getIssuer());
		System.out.println(tran.getAmount().getValue());
		
		System.out.println("---------end");
	}

}
