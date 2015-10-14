package example;

import java.util.Iterator;

import com.jingtum.Jingtum;
import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.model.Wallet;
import com.jingtum.model.BalanceCollection;
import com.jingtum.model.Balances;
import com.jingtum.model.JingtumCurrency;
import com.jingtum.model.Orders;
import com.jingtum.model.OrdersCollection;
import com.jingtum.model.Payments;
import com.jingtum.model.PaymentsCollection;

public class WalletExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {

		WalletExample walletExample = new WalletExample();
		System.out.println("---------创建 Wallet");
		Wallet wallet = Wallet.create();
		System.out.println(wallet.getAddress());
		System.out.println(wallet.getSecret());
		System.out.println(wallet.getSuccess());
		
		System.out.println("---------获取 Wallet balance");
		Wallet wallet2 = new Wallet("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf",null);
		BalanceCollection bc = Wallet.balance(wallet2.getAddress());
		Balances bl;
		Iterator it = bc.getData().iterator();
		Integer i = 0;
		while(it.hasNext())
		{			
			i++;
		    bl = (Balances)it.next();
		    System.out.println("---------wallet #" + i);
			System.out.println(bl.getValue());
			System.out.println(bl.getCurrency());
			System.out.println(bl.getCounterparty());
		}
		
		System.out.println("---------Wallet pay");
		Wallet wallet3 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","snqFcHzRe22JTM8j7iZVpQYzxEEbW");
		JingtumCurrency jtc = new JingtumCurrency();
		jtc.setCounterparty("");
		jtc.setCurrency("SWT");
		jtc.setValue(1.1);
		Payments payment = wallet3.pay("jHb9CJAWyB4jr91VRWn96DkukG4bwdtyTh",jtc, true, "" );
		System.out.println(payment.getHash());
		System.out.println(payment.getClient_resource_id());
		System.out.println(payment.getSuccess());
		System.out.println(payment.getState());
		System.out.println(payment.getResult());
		System.out.println(payment.getDate());
		System.out.println(payment.getFee());
		
		System.out.println("---------根据hash值或者资源号获取 Payment 信息");
		Wallet wallet4 = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","");
		Payments payment2 = wallet4.getPaymentByID("FBC53138F3178B365FDAF6F02E24E48485AEB47BA7345518BB8CF04781F599E0");
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
		PaymentsCollection pc = wallet5.getPayments();
		Payments pay;
		Iterator it_2 = pc.getData().iterator();
		Integer j = 0;
		while(it_2.hasNext())
		{			
			j++;
		    pay = (Payments)it_2.next();
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

	}
}
