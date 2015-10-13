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
import com.jingtum.model.Payments;

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
		System.out.println(wallet2.getAddress());
		System.out.println(wallet2.getSecret());

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
		Payments payment = wallet3.pay("jHb9CJAWyB4jr91VRWn96DkukG4bwdtyTh",1.1, "SWT", "", true, "" );
		System.out.println(payment.getHash());
		System.out.println(payment.getClient_resource_id());
		System.out.println(payment.getSuccess());
		System.out.println(payment.getState());
		System.out.println(payment.getResult());
		System.out.println(payment.getDate());
		System.out.println(payment.getFee());

	}
}
