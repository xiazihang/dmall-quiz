# 京西商城

## 练习要求
现有一京西商城线下零售店王老板，发现随着互联网的普及，越来越多的人喜欢在在线上购买物品，直接配送到家，足不出户就可以获取日常用品。
反观自己的线下零售店，周内店里客人寥寥无几，为了顺应时代的潮流，提高业绩，于是想委托你帮他开发一款名为"京西商城"的在线网站。
方便顾客的购买，为用户提供优质的服务，使得自己商城的业绩能够提升。

### 业务需求

- 作为一个产品运营人员，我想添加商品，以便于我可以上新更多的商品。
- 作为一个产品运营人员，我想修改商品，以便于我可以改变商品的相关信息。
- 作为一个产品运营人员，我想创建商品的库存信息，以便于我可以为已有商品添加库存数量。
- 作为一个产品运营人员，我想修改商品的库存数量，以便于我可以为锁定和解锁库存数量，保证了一个库存数量不会被其他人同时购买。
- 作为一个买家，我想查询商品，以便于我可以搜索我想购买的商品。
- 作为一个买家，我想创建订单，以便于我可以购买我喜欢的货物，以及可购买的库存数量，从而创建订单，得到需要支付的价格。
- 作为一个买家，我想支付订单，以便于我可以对未支付的订单进行支付。
- 作为一个买家，我想撤销订单，以便于我可以对未支付的订单进行撤销。
- 作为一个买家，我想查询订单，以便于我可以一次查询到订单中包含的所有（订单、货物、物流）信息。
- 作为一个买家，我想查询我所拥有的全部订单，以便于我可以知道自己都创建了哪些订单。
- 作为一个买家，我想查询快递单，以便于我可以及时掌握快递单状态。
- 作为一个买家，我想签收货物，以便于我可以完成这次订单。
- 作为一个快递员，我想创建快递单，以便于我可以对支付成功的订单进行物流配送。
- 作为一个快递员，我想对快递单进行发货，以便于我可以完成我的工作。

**Note: 实现功能不区分角色及权限，只是为了业务描述**

### 功能要求

**商品**
- 当添加商品时，输入商品的`名称`，`描述`，`价格`，系统会创建商品，同时创建对应的库存数量为0。
- 当修改商品时，输入商品的`名称`，`描述`，`价格`，系统会对商品信息进行修改。
- 当查询商品时，输入商品的`名称`或者`描述`中的文字，系统会对商品进行模糊查询。

**库存信息**
- 当修改库存数量时，系统会修改商品的库存数量或者锁定数量。
 
**订单**
- 当创建订单时，输入要购买的商品ID，和购买数量，系统会创建订单，同时创建对应商品的货物信息，并锁定对应商品的库存数量，计算总价
- 当支付订单时，系统会修改订单状态，同时记录时间，并为支付成功的订单创建快递单，同时设置快递单状态
- 当撤销订单时，系统会修改订单状态，同时记录时间、解锁库存数量
- 当查询订单时，系统会根据订单ID一次查询出订单信息、订单中所有货物的信息、物流信息
- 当用户查询自己所拥有的全部订单时，系统会根据用户ID查看该用户的所有订单信息

**快递单**  
- 当发货时，系统会修改快递单的状态，同时记录时间
- 当查询快递单时，系统会查看快递单的详细信息
- 当签收时，系统会修改快递单的状态，同时修改订单状态并记录时间，减少库存信息的真实数量

Note：
- 货物信息即商品的快照，该信息不会被修改，为了记录创建订单时该商品的信息
- 不考虑退换货

### 题目要求
- 使用Jenkins持续集成，基于Docker中的Jenkins镜像
- 使用MySQL作为数据存储，基于Docker中的MySQL镜像
- 使用Postman对API进行测试
- 使用Newman对Postman中的API进行集成测试
- 将Jenkins job的运行日志截图，并命名为`result.png`

## 环境要求
- java8
- Intellij-IDEA

## 如何开始
- 使用[Spring Initializr](https://start.spring.io/)搭建基础Spring Boot项目
- 将`collection.json`拷贝到Spring Boot项目的根目录下
- 将项目提交到Github
- 在Jenkins中创建一个Freestyle类型的job，设置trigger使得Github仓库一提交代码就会build，对项目进行持续集成
- 在Jenkins中配置使用Newman对项目中的API进行测试
- 此时构建Jenkins job会失败，可以开始根据题目需求完成API

## 输出规范
- 京西商城功能实现代码
- 项目根目录下应包含`result.png`
