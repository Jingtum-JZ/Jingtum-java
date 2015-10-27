package example;

import java.util.Iterator;

import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.model.JingtumCurrency;
import com.jingtum.model.PostResult;
import com.jingtum.model.Relation;
import com.jingtum.model.Relation.RelationType;
import com.jingtum.model.RelationCollection;
import com.jingtum.model.Wallet;

public class RelationExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {

		Wallet wallet = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa","snqFcHzRe22JTM8j7iZVpQYzxEEbW");; //根据井通地址生成钱包
		
		System.out.println("---------增加relationship");
		JingtumCurrency amount = new JingtumCurrency();
		amount.setIssuer("janxMdrWE2SUzTqRUtfycH4UGewMMeHa9f"); //Currency issuer
		amount.setCurrency("CNY");
		amount.setLimit(200); //使用limit，而不是value
		PostResult pr = wallet.addRelation(RelationType.authorize, "jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf", amount, true);
		System.out.println(pr.getFee());
		System.out.println(pr.getHash());
		System.out.println(pr.getSuccess());
		System.out.println(pr.getState());
		
/*		System.out.println("---------移除relationship");
		JingtumCurrency currency = new JingtumCurrency();
		currency.setIssuer("janxMdrWE2SUzTqRUtfycH4UGewMMeHa9f"); //Currency issuer
		currency.setCurrency("CNY");
		pr = wallet.deleteRelation(RelationType.authorize, "jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf", currency, true);
		System.out.println(pr.getFee());
		System.out.println(pr.getHash());
		System.out.println(pr.getSuccess());
		System.out.println(pr.getState());*/
		
		System.out.println("---------获取relationship");
		RelationCollection rc = wallet.getRelations(RelationType.all, "jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf", null);//参数均为可选参数
		Iterator<Relation> it = rc.getData().iterator();
		Integer i = 0;
		Relation re;
		while(it.hasNext())
		{
			i++;
			re = (Relation)it.next();
			System.out.println("---------Relation #" + i);
			System.out.println(re.getAccount()); //账号
			System.out.println(re.getType()); //关系类型
			System.out.println(re.getCounterparty()); //关系对家
			System.out.println(re.getAmount().getCurrency()); //货币单位
			System.out.println(re.getAmount().getIssuer()); //货币发行方
			System.out.println(re.getAmount().getLimit()); //金额
		}
		
		System.out.println("---------获取counterparty relationship");
		Wallet wallet2 = new Wallet("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf",null);
		RelationCollection rc2 = wallet2.getCounterpartyRelations(RelationType.all, null, null);//参数均为可选参数
		Iterator<Relation> it2 = rc2.getData().iterator();
		Integer j = 0;
		Relation re2;
		while(it2.hasNext())
		{
			j++;
			re2 = (Relation)it2.next();
			System.out.println("---------Relation #" + j);
			System.out.println(re2.getAccount()); //关系主动方的井通地址
			System.out.println(re2.getType()); //关系类型
			System.out.println(re2.getCounterparty()); //关系被动方的井通地址
			System.out.println(re2.getAmount().getCurrency()); //货币单位
			System.out.println(re2.getAmount().getIssuer()); //货币发行方
			System.out.println(re2.getAmount().getLimit()); //金额
		}
		
		System.out.println("---------end");
	}

}
