# 京西商城

## 练习要求
我们现在做一个关于"京西商城"的综合练习，只需要按照题目要求实现所有的接口，确保测试通过，没有前端页面题目。需求如下：

#### 功能需求
- 用户可以添加新的商品，可以分别通过商品的`名称`，`描述`进行模糊查询，可以对商品的`名称`，`描述`,`价格`做修改。
- 用户可以创建商品的库存信息，修改商品的库存数量
- 用户可以创建一张订单：
  - 需要创建对应商品的货物信息
  - 在库存信息中锁定库存数量
- 用户可以查看订单信息以及订单中所有商品的信息 
- 用户可以根据用户Id查看该用户拥有的所有订单
- 用户可以对未支付的订单进行撤销
  - 系统需要解锁库存数量，并修改订单状态，记录相应时间
- 用户可以对创建的订单进行支付(支付功能只需要修改订单状态即可)
- 系统需要对支付成功的订单创建物流订单，同时修改订单状态并修改库存数量
- 用户可以查看物流状态(or查看物流订单)
- 当货物到达，用户可以签收货物
  - 系统需要修改订单状态，以及物流订单状态并记录时间



#### 实体及字段要求
- 在京西商城中存在实体：商品(product)，库存信息(inventory)，货物(goods)，订单(order)，物流单(LogisticsRecord)
- 商品(product)实体包含字段有：id, name, description, price
- 库存信息(inventory)实体包含字段有：id, lockedCount, count
- 购买货物(purchaseItem)实体包含字段：id, productName, productDescription, purchasePrice, purchaseCount
- 订单(order)实体包含字段：id, totalPrice, status, createTime, finishTime, paidTime, withdrawnTime
  - 支付状态(status)有：unPaid, paid, withDrawn, finished 
- 物流单(LogisticsRecord)实体包含字段有：id, LogisticsStatus, OutboundTime, signedTime, deliveryMan
  - 物流状态(LogisticsStatus)有：readyToShip, Shipping, Signed
  
#### 题目要求
- 使用docker中的jenkins镜像进行持续集成
- 使用docker中的mysql镜像作为数据存储
- 使用postman对API进行测试
- 使用newman对postman中的API进行命令行集成测试


## 环境要求
- java8
- Intellij-IDEA

## 如何开始
- 认真阅读题目需求
- 基于gradle搭建spring boot项目
- 将`collection.json`导入postman中运行，此时测试全部失败
- 在项目中完成`collection.json`中的API
- 使用postman进行测试，测试成功
- 在jenkins中创建一个freestyle类型的job，设置trigger使得github仓库一提交代码就会build，对项目进行持续集成
- 在jenkins中配置使用newman对`collection.json`中的API进行测试

## 输出规范
- 京西商城功能实现代码
- jenkins job运行日志截图`result.png`(根目录下)
