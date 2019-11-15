package com.kevin.config;

import com.kevin.MsgDelegate.MsgDelegate;
import com.kevin.conveter.ImageConverter;
import com.kevin.conveter.WordConverter;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-11-15 9:25
 * @description todo
 **/
@Configuration
public class RabbitmqConfig {

  // 创建连接工厂
  /**
   * * CachingConnectionFactory CachingConnectionFactory为我们提供了两种缓存的模式： •
   * CHANNEL模式：这也是CachingConnectionFactory的默认模式，在这种模式下，所有的createConnection（）方法实际上返回的都是同一个Connection，
   * 同样的Connection.close()方法是没用的，因为就一个，默认情况下，Connection中只缓存了一个Channel，
   * 在并发量不大的时候这种模式是完全够用的，当并发量较高的时候，我们可以setChannelCacheSize（）来增加Connection中缓存的Channel的数量。
   * •
   * CONNECTION模式：在CONNECTION模式下，每一次调用createConnection（）方法都会新建一个或者从缓存中获取，
   * 根据你设置的ConnectionCacheSize的大小，当小于的时候会采用新建的策略，当大于等于的时候会采用从缓存中
   * 获取的策略，与CHANNEL模式不同的是，CONNECTION模式对Connection和Channel都进行了缓存，最新版本的client中
   * 已经将Channel的缓存数量从1增加到了25，但是在并发量不是特别大的情况下，作用并不是特别明显。
   * 使用CachingConnectionFactory需要注意的一点是：所有你获取的Channel对象必须要显式的关闭，
   * 所以finally中一定不要忘记释放资源，如果忘记释放，则可能造成连接池中没有资源可用。
   */
  @Bean
  public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses("192,168.101.15:5672");
        cachingConnectionFactory.setVirtualHost("kevin");
        cachingConnectionFactory.setUsername("kevin");
        cachingConnectionFactory.setPassword("kevin");
        cachingConnectionFactory.setConnectionTimeout(10000);
        cachingConnectionFactory.setCloseTimeout(10000);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
          RabbitAdmin admin=new RabbitAdmin(connectionFactory);
          //spring容器启动加载该类
          admin.setAutoStartup(true);
          return  admin;
    }

    //=====================================申明三个交换机====================================================================

    @Bean
    public TopicExchange topicExchange(){
      TopicExchange topicExchange=new TopicExchange("kevin.topic.exchange",true,false);
      return topicExchange;
    }

    @Bean
    public DirectExchange directExchange(){
      DirectExchange directExchange =new DirectExchange("kevin.direct.exchange",true,false);
      return  directExchange;
    }

    @Bean
    public FanoutExchange fanoutExchange(){
      FanoutExchange fanoutExchange=new FanoutExchange("kevin.fanout.exchange",true,false);
     return  fanoutExchange;
   }

    //===========================================申明队列==================================================


    @Bean
    public Queue testTopicQueue(){
      Queue queue=new Queue("testTopicQueue",true,false,false,null);
      return queue;
    }

    @Bean
    public Queue testTopicQueue2(){
      Queue queue=new Queue("testTopicQueue2",true,false,false,null);
      return queue;
    }

    @Bean
    public Queue testDirectQueue(){
      Queue queue=new Queue("testDirectQueue",true,false,false,null);
      return queue;
    }

    @Bean
    public Queue testFanoutQueue(){
      Queue queue=new Queue("testFanoutQueue",true,false,false,null);
      return queue;
    }

    @Bean
    public Queue orderQueue(){
      Queue queue=new Queue("orderQueue",true,false,false,null);
      return queue;
    }

    @Bean
    public Queue addressQueue(){
      Queue queue=new Queue("addressQueue",true,false,false,null);
      return queue;
    }

    @Bean
    public Queue fileQueue(){
      Queue queue=new Queue("fileQueue",true,false,false,null);
      return  queue;
    }

    //========================================申明绑定================================================

    @Bean
    public Binding topicBinding(){
      return BindingBuilder.bind(testTopicQueue()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(testTopicQueue2()).to(topicExchange()).with("topic.key.#");
    }

    @Bean
    public Binding directbinding(){
      return BindingBuilder.bind(testDirectQueue()).to(directExchange()).with("direct.key");
    }

    @Bean
    public Binding orderQueueBinding(){
      return BindingBuilder.bind(orderQueue()).to(directExchange()).with("rabbit.order");
    }

    @Bean
    public Binding addressQueueBinding(){
      return BindingBuilder.bind(addressQueue()).to(directExchange()).with("rabbit.address");
    }

    @Bean
    public Binding fileQueueBinding(){
      return BindingBuilder.bind(fileQueue()).to(directExchange()).with("rabbitmq.file");
    }

    //发送到扇形交换机的消息会发送给所有与扇形交换机绑定的队列
    @Bean
    public Binding fanoutBinding(){
      return BindingBuilder.bind(testFanoutQueue()).to(fanoutExchange());
    }


    @Bean
    public RabbitTemplate template(){
      //spring boot项目中connectionFactory的配置定义在yml文件中，包含主机地址等信息，可以直接使用
        //如果有个性化配置，则可以配置在此
      RabbitTemplate template=new RabbitTemplate(connectionFactory());
      template.setReceiveTimeout(50000);
      return template;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
      SimpleMessageListenerContainer simpleMessageListenerContainer=new SimpleMessageListenerContainer(connectionFactory());
      //监听需要的队列
      simpleMessageListenerContainer.setQueues(testDirectQueue(),testFanoutQueue(),testTopicQueue(),testTopicQueue2(),orderQueue(),addressQueue(),fileQueue());
      //初始化消费者数量
      simpleMessageListenerContainer.setConcurrentConsumers(5);
      //最大消费者数量
      simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
      //签收模式为手动签收
      simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
      //设置拒绝重回队列
      simpleMessageListenerContainer.setDefaultRequeueRejected(false);

      // 设置使用默认的监听方法
      /***  MessageListenerAdapter adapter=new MessageListenerAdapter(new MsgDelegate());
        simpleMessageListenerContainer.setMessageListener(adapter);*/

      //指定默认消费方法
        /**
      MessageListenerAdapter adapter=new MessageListenerAdapter(new MsgDelegate());
      adapter.setDefaultListenerMethod("consumerMsg");
      simpleMessageListenerContainer.setMessageListener(adapter);


      //给具体队列指定默认的消费方法
      MessageListenerAdapter adapter=new MessageListenerAdapter(new MsgDelegate());
        Map<String,String>queueMaps=new HashMap<>();
        queueMaps.put("testTopicQueue","consumerTopicQueue");
        queueMaps.put("testTopicQueue","consumerTopicQueue2");
        adapter.setQueueOrTagToMethodName(queueMaps);
        simpleMessageListenerContainer.setMessageListener(adapter);


      //处理json
      MessageListenerAdapter adapter=new MessageListenerAdapter(new MsgDelegate());
      adapter.setDefaultListenerMethod("consumerJsonMessage");
        Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
        adapter.setMessageConverter(converter);
        simpleMessageListenerContainer.setMessageListener(adapter);



      //处理java对象
      MessageListenerAdapter adapter=new MessageListenerAdapter(new MsgDelegate());
      adapter.setDefaultListenerMethod("consumerJavaObjMessage");
      Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
      DefaultJackson2JavaTypeMapper javaTypeMapper=new DefaultJackson2JavaTypeMapper();
      javaTypeMapper.setTrustedPackages("com.kevin.entity");
      //设置java转json
      converter.setJavaTypeMapper(javaTypeMapper);
      adapter.setMessageConverter(converter);
      simpleMessageListenerContainer.setMessageListener(adapter);

         */

      //处理文件和图片
      MessageListenerAdapter adapter=new MessageListenerAdapter(new MsgDelegate());
      adapter.setDefaultListenerMethod("consumerFileMessage");

      //设置转换器
        ContentTypeDelegatingMessageConverter converter=new ContentTypeDelegatingMessageConverter();
        converter.addDelegate("img/png",new ImageConverter());
        converter.addDelegate("img/jpg",new ImageConverter());
        converter.addDelegate("application/word",new WordConverter());
        converter.addDelegate("word",new WordConverter());
        adapter.setMessageConverter(converter);
        simpleMessageListenerContainer.setMessageListener(adapter);

        return simpleMessageListenerContainer;
    }


}
