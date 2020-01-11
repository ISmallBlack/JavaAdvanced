package com.itheima.mq.rocketmq.base.producer;

import com.itheima.mq.rocketmq.common.ConStants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 发送异步消息
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定Nameserver地址
        producer.setNamesrvAddr(ConStants.CLUSTER_ADD);
        //3.启动producer
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        final CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            //4.创建消息对象，指定主题Topic、Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message msg = new Message("base", "Tag2", ("Hello World异步" + i).getBytes());
            final int index = i;
            //5.发送异步消息
            producer.send(msg, new SendCallback() {
                /**
                 * 发送成功回调函数
                 * @param sendResult
                 */
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.println(index+"发送结果：" + sendResult);
                }

                /**
                 * 发送失败回调函数
                 * @param e
                 */
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.println(index+"发送异常：" + e);
                }
            });

            //线程睡1秒
         //   TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("异步发送结束");
        countDownLatch.await(10, TimeUnit.SECONDS);
        //6.关闭生产者producer
        producer.shutdown();
    }
}
