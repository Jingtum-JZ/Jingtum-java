package example;

import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.model.Notification;
import com.jingtum.model.Wallet;

public class NotificationExample {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {
		NotificationExample notificationExample = new NotificationExample();
		Wallet wallet = new Wallet("jMhLAPaNFo288PNo5HMC37kg6ULjJg8vPf","");
		Notification noti = wallet.getNotification("AE85E50D4E9A77EF00A1FA2EE416DB3789F28381D267436E8A82493CC0791B62");
		System.out.println("---------获取 Notification");
		System.out.println(noti.getAccount());
		System.out.println(noti.getType());
		System.out.println(noti.getDirection());
		System.out.println(noti.getState());
		System.out.println(noti.getResult());
		System.out.println(noti.getHash());
		System.out.println(noti.getDate());
		System.out.println(noti.getPrevious_hash());
		System.out.println(noti.getNext_hash());
		
		System.out.println("---------end");
	}
}
