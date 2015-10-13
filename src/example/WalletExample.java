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

public class WalletExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {

		WalletExample walletExample = new WalletExample();
		System.out.println("---------创建 Wallet");
		Wallet wallet = Wallet.create();
		System.out.println(wallet.getAddress());
		System.out.println(wallet.getSecret());
		System.out.println(wallet.getSuccess());
		
		System.out.println("---------获取 Wallet balance");
		BalanceCollection bc = Wallet.balance("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf");
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

	}
}
