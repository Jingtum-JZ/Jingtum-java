package example;

import java.util.Iterator;

import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.model.Wallet;
import com.jingtum.model.BalanceCollection;
import com.jingtum.model.Balance;
import com.jingtum.model.JingtumCurrency;
import com.jingtum.model.Payment;
import com.jingtum.model.PaymentCollection;
import com.jingtum.model.PostResult;

public class WalletExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {

		System.out.println("---------创建 Wallet");
		Wallet wallet = Wallet.create(); //创建新钱包，静态方法
		System.out.println(wallet.getAddress()); //钱包地址
		System.out.println(wallet.getSecret()); //钱包密钥
		
		System.out.println("---------获取 Wallet balance");
		Wallet wallet2 = new Wallet("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf",null);
		BalanceCollection bc = Wallet.getBalance(wallet2.getAddress()); //静态方法，参数为钱包地址
		Balance bl;
		Iterator<Balance> it = bc.getData().iterator();
		Integer i = 0;
		while(it.hasNext())
		{			
			i++;
		    bl = (Balance)it.next();
		    System.out.println("---------wallet #" + i);
			System.out.println(bl.getValue()); //金额
			System.out.println(bl.getCurrency()); //货币单位
			System.out.println(bl.getCounterparty()); //发行方
			System.out.println(bl.getFreezed()); //冻结金额
		}
		
		System.out.println("---------Wallet pay");
		Wallet wallet3 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","snqFcHzRe22JTM8j7iZVpQYzxEEbW"); //如进行支付，密钥为必须参数
		JingtumCurrency jtc = new JingtumCurrency(); //构建支付的货币
		jtc.setCounterparty(""); //货币发行方
		jtc.setCurrency("SWT"); //货币单位
		jtc.setValue(1.1); //金额
		PostResult payment = wallet3.pay("jHb9CJAWyB4jr91VRWn96DkukG4bwdtyTh",jtc, true, "" ); //支付，参数为：获取方地址，货币，是否等待支付结果，和资源号（选填）
		System.out.println(payment.getHash()); //交易hash值
		System.out.println(payment.getClient_resource_id()); //交易资源号
		System.out.println(payment.getSuccess()); //交易是否成功
		System.out.println(payment.getState()); //交易状态
		System.out.println(payment.getResult()); //支付服务器结果
		System.out.println(payment.getDate()); //支付时间，UNIXTIME时间
		System.out.println(payment.getFee()); //支付费用
		
		System.out.println("---------根据hash值或者资源号获取 Payment 信息");
		Wallet wallet4 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","");
		Payment payment2 = wallet4.getPaymentByID("FBC53138F3178B365FDAF6F02E24E48485AEB47BA7345518BB8CF04781F599E0"); 
		System.out.println(payment2.getHash());
		System.out.println(payment2.getClient_resource_id());
		System.out.println(payment2.getSuccess());
		System.out.println(payment2.getState());
		System.out.println(payment2.getResult());
		System.out.println(payment2.getDate());
		System.out.println(payment2.getFee());
		System.out.println(payment2.getType());
		System.out.println(payment2.getCounterparty());
		System.out.println(payment2.getAmount().getIssuer());
		System.out.println(payment2.getAmount().getCurrency());
		System.out.println(payment2.getAmount().getValue());
		
		System.out.println("---------获取全部 Payments 信息");
		Wallet wallet5 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","");
		PaymentCollection pc = wallet5.getPayments();
		Payment pay;
		Iterator<Payment> it_2 = pc.getData().iterator();
		Integer j = 0;
		while(it_2.hasNext())
		{			
			j++;
		    pay = (Payment)it_2.next();
		    System.out.println("---------payment #" + j);
			System.out.println(pay.getDate());
			System.out.println(pay.getHash());
			System.out.println(pay.getType());
			System.out.println(pay.getFee());
			System.out.println(pay.getResult());
			System.out.println(pay.getClient_resource_id());
			System.out.println(pay.getCounterparty());
			System.out.println(pay.getAmount().getCurrency());
			System.out.println(pay.getAmount().getIssuer());
			System.out.println(pay.getAmount().getValue());
		}
		
		System.out.println("---------end");

	}
}
