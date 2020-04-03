[文章地址](https://blog.csdn.net/w1054993544/article/details/105287944)
## 前言
接触DDD也有一段时间了， 参考了很多前辈的分享，他们的知识和经验对自己的帮助很大。本文以一个订单服务为示例来落地，同时假设你对DDD的相关概念都已经有所了解。

## 代码结构

主流的都会采用分层架构的方式，如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200403105241846.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3cxMDU0OTkzNTQ0,size_16,color_FFFFFF,t_70)
同样，还有用依赖倒置（DI）的， 都差不多。我们同样采用分层架构，分层结构如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200403105429347.png)
**interfaces用户接口层**：用户请求的入口，通常就是些controller。
**application应用层**：作为领域模型向外提供业务功能的总出入口，应用服务的实现遵循一个很简单的原则，即一个业务用例对应ApplicationService上的一个业务方法，如创建订单。这里借用了简单的CQRS。
**domain领域层**：真正处理业务的地方。
**infrastructure基础设施层**：提供公共基础能力，如数据库、redis、配置等。

## 用户接口层实现
该层很简单，作为与客户端交互的入口， 通常只放controller,然后调用应用服务实现业务，代码如下：

```
public class OrderController {
    private final OrderCmdService orderCmdService;
    private final OrderQueryService orderQueryService;

    public OrderController(OrderCmdService orderCmdService, OrderQueryService orderQueryService) {
        this.orderCmdService = orderCmdService;
        this.orderQueryService = orderQueryService;
    }

    @ApiOperation("创建订单")
    @PostMapping("create")
    public Response createOrder(@RequestBody @Valid CreateOrderCmd command){
        orderCmdService.createOrder(command);
        return Response.buildSuccess();
    }
    @ApiOperation("修改订单详细地址")
    @PostMapping("/{orderId}/changeAddressDetail")
    public Response changeAddressDetail(@PathVariable("orderId") String orderId, @RequestBody @Valid ChangeAddressDetailCmd command){
        orderCmdService.changeAddressDetail(orderId, command);
        return Response.buildSuccess();
    }
    @ApiOperation("获取订单信息")
    @GetMapping("/{orderId}")
    public ResponseWithData<OrderRepresentation> getOrder(@PathVariable("orderId") String orderId){
        return ResponseWithData.of(orderQueryService.getOrder(orderId));
    }
```
## 应用层实现
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200403110606716.png)
作为领域模型向外提供业务功能的总出入口，应用服务的实现遵循一个很简单的原则，即一个业务用例对应ApplicationService上的一个业务方法，如创建订单。这里借用了简单的CQRS。

representation放的是返回给客户端的查询数据的封装， 为啥要有这个呢， 因为查询可能是非常复杂的（如根据距离从远到近、并按销量查询商品等），这样一些查询就有可能不通过聚合根，而是由应用层直接调用repository来完成。

```
public class OrderCmdService {

    private OrderRepository orderRepository;

    private OrderFactory orderFactory;

    private OrderDomainService orderDomainService;

    private DomainEventPublisher domainEventPublisher;

    public OrderCmdService(OrderRepository orderRepository, OrderFactory orderFactory, OrderDomainService orderDomainService) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderDomainService = orderDomainService;
    }
    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrderCmd cmd){
        List<OrderItem> orderItemList = cmd.getItems()
                .stream()
                .map(orderItemCmd -> OrderItem.create(orderItemCmd.getProductId(),
                        orderItemCmd.getCount(),
                        orderItemCmd.getItemPrice()))
                .collect(Collectors.toList());
        Order order = orderFactory.create(cmd.getAddress(),orderItemList);
        orderDomainService.save(order);
        //发布领域事件、领域事件可以在ApplicationService中发、也可以在repository中、也可以用事件表
        domainEventPublisher.publish(new OrderCreatedEvent(order.getId(), order.getAddress(), order.getItems()));
    }
    @Transactional(rollbackFor = Exception.class)
    public void changeAddressDetail(String orderId, ChangeAddressDetailCmd command){
        Order order = orderRepository.byId(orderId);
        String oldAddress = order.getAddress().getDetail();
        String newAddress = command.getDetail();
        order.changeAddressDetail(command.getDetail());
        orderRepository.save(order);
        //发布领域事件、领域事件可以在ApplicationService中发、也可以在repository中、也可以用事件表
        domainEventPublisher.publish(new OrderAddressChangeEvent(orderId, oldAddress, newAddress));
    }

}
```

 
## 领域层实现
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200403111253738.png)
领域层根据包名和好理解。
**converter**：转行器，把DO（data object 数据对象，即数据库对于的表）和DTO进行转换、实体和DO进行转换。
**entity**：实体，如聚合根、实体等。
**event**: 领域事件， 领域事件产生的时机为当聚合根的状态改变时，如订单已支付、订单已取消等状态改变时会产生领域事件。
**factory**：工厂方法， 很多用例都推崇使用工厂方法来创建聚合根， 但是也有缺陷，当聚合根的成员很多时， 构造方法参数很多， 这时候，建议通过建造者模式来创建聚合根。参看下面代码。
**repository**：仓储，即对聚合根进行存储的接口， 同时我把实现类也放在这里的原因是，如果实现类放在基础设施层，那么需要在基础设施层依赖领域层进行对象的装换。
**representation**：跟应用层一样
**service**：领域服务，聚合根是业务逻辑的主要载体，也就是说业务逻辑的实现代码应该尽量地放在聚合根或者聚合根的边界之内。但有时，有些业务逻辑并不适合于放在聚合根上，如我们保存订单（order）的时候需要同时保存订单品相（order_item）,涉及到两个实体的操作，在这种迫不得已的情况下，那么引入领域服务（Domain Service），尽量少用吧。
**valuepbject**： 值对象，如订单（order）里面的收货地址（address），尽量少用值对象，除非你非常明确它就是值对象，否则建议都用实体，如果不合理，在代码的演进过程中进行修改和完善。例如订单（Order）实体包括订单品相（Order_item）和收货地址（Address）, 我设置Order_item为实体、Address为值对象的原因是Order_item是需要单独持久化到数据库中。

```
public class Order extends AggregateRoot {

    private static final long serialVersionUID = 1L;
    private String id;
    private int totalPrice;
    private Address address;
    private List<OrderItem> items = newArrayList();
    private Date createTime;
    private OrderStatus orderStatus;

//    public Order() {
//    }

//    public Order(String id, Address address, List<OrderItem> items, Date createTime) {
//        this.id = id;
//        this.address = address;
//        this.items.addAll(items);
//        this.createTime = createTime;
//        this.orderStatus = OrderStatus.CREATED;
//        this.totalPrice = calculateTotalPrice(this.items);
//    }
//
//    public Order(String id, int totalPrice, Address address, List<OrderItem> items, Date createTime, OrderStatus orderStatus) {
//        this.id = id;
//        this.totalPrice = totalPrice;
//        this.address = address;
//        this.items = items;
//        this.createTime = createTime;
//        this.orderStatus = orderStatus;
//    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.totalPrice = builder.totalPrice;
        this.items.addAll(builder.items);
        this.orderStatus = builder.orderStatus;
        this.createTime = builder.createTime;
    }

//    public static Order create(String id, Address address, List<OrderItem> items){
//        return new Order(id, address, items, new Date());
//    }
//    public static Order create(String id, Address address, int totalPrice, int status, List<OrderItem> items, Date createTime){
//        return new Order(id,totalPrice, address, items, createTime, OrderStatus.of(status));
//    }

    public static class Builder{
        private String id;
        private Address address;
        private Integer totalPrice;
        private List<OrderItem> items = newArrayList();
        private Date createTime;
        private OrderStatus orderStatus;

        public Order build(){
            if (null == createTime){
                createTime = new Date();
            }
            if (null == orderStatus){
                throw new IllegalArgumentException("orderStatus should not be null");
            }
            return new Order(this);
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setItems(List<OrderItem> items) {
            this.items.addAll(items);
            totalPrice = calculateTotalPrice(items);
            return this;
        }

        public Builder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setOrderStatus(int status) {
            this.orderStatus = OrderStatus.of(status);
            return this;

        }

        public Builder setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }
        private int calculateTotalPrice(List<OrderItem> items){
            return items.stream()
                    .map(OrderItem::totalPrice)
                    .reduce(0,Integer::sum);
        }
    }


    public void changeAddressDetail(String detail){
        this.address = address.changeDetailTo(detail);
    }


    public String getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}

```

## 基础设施层
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020040311350090.png)
config：配置信息
event：事件相关的，如DomainEvent、MQ、等
exception： 异常类。
repository：存放于数据库相关的，我这里用mybatis， 那么就会有mapper文件、mapper接口等。


## 结束
整个项目结构大致就这样， 随着对DDD的理解，项目结构可能会随着时间而改变。由于笔者知识有限，不足和错误的地方，请大家随便喷和指正。

[Talk is Cheap , Show me the code](https://github.com/babylikebird/ddd-order)
