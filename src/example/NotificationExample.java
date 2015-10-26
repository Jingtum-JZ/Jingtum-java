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
		
		Wallet wallet = new Wallet("js4UaG1pjyCEi9f867QHJbWwD3eo6C5xsa",""); //根据井通地址生成钱包
		Notification noti = wallet.getNotification("9A33D1850F84C51EFCA94B9AE84A15AD6BF691A60E859B190D5728C74A7D545B"); //根据hash值获取notification实例
		System.out.println("---------获取 Notification");
		
		System.out.println(noti.getAccount()); //通知相关账号
		System.out.println(noti.getType()); //通知类型
		System.out.println(noti.getDirection()); //交易的方向，incoming 或者 outgoing
		System.out.println(noti.getState()); //交易的状态
		System.out.println(noti.getResult()); //交易结果
		System.out.println(noti.getHash()); //交易hash值
		System.out.println(noti.getDate()); //交易时间，UNIXTIME
		System.out.println(noti.getPrevious_hash()); //前一个交易的URL
		System.out.println(noti.getNext_hash()); //后一个交易的URL
		
		System.out.println("---------end");
	}
}
