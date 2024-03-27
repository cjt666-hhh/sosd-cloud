# sosd-cloud

> sosd实验室内部使用微服务架构后端开发脚手架。还在迭代中......

## 技术栈选用

数据库层：Mysql+Mybatis-plus

缓存层：Redis（用于缓存和JWT校验等）

消息中间件：选用RabbitMQ 轻量级。可替换为RocketMQ，Kafka

权限框架：Sa-Token

RPC框架：Dubbo

服务发现注册中心：Nacos

运维：可直接打jar包java -jar，或者使用Dockerfile打为容器

## 使用前置条件

1. 确保有一台Linux服务器
2. 部署Nacos
3. 部署Redis
4. 部署Mysql
5. 部署RabbitMQ（可选）
6. 修改cloud-api模块中的yml文件中的连接参数

## 开发规范

1. 最外层cloud父模块禁止引入依赖，只用于依赖版本管理

2. core模块用于编写项目核心代码，禁止涉及业务相关。

3. utils层用来存放工具类代码，以及基础的Result类等

4. service层用于编写业务实现，接口定义。（DubboService/SpringService）

5. api层是项目的入口，用于实现web层Controller接口

> 接口返回规范,查询接口必须分页，传入page和int，使用Mybatis-Plus分页功能进行分页处理。采用PageResult作为返回对象，其他接口均封装到Result返回。

分页接口实例(Service层)：

```java
  public PageResult<List<Collection>> getCollectionByCreator(Integer userId, Integer page, Integer size) {
    Page<Collection> pager = new Page<>(page, size);
    QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("creator", userId).orderByDesc("updated_at");
    Page<Collection> collectionPage = collectionMapper.selectPage(pager, queryWrapper);
    return PageResult.success((int) collectionPage.getTotal(), 200, collectionPage.getRecords(), "数据获取成功");
  }
```

## 配置文件解析

- api层的application.yml为总配置文件，用来控制其他配置文件是否生效以及载入dubbo和nacos相关配置，application-database用于配置mysql，redis信息。application-other用来配置其他信息，包括MQ连接信息等。

- cloud-core层配置文件用来配置sa-token以及线程池相关信息

## 功能介绍

### 利用Dubbo的RPC调用能力调用远端服务

一句话描述，A服务和B服务定义相同的业务实现接口（内部成员交流）

```java
public interface DemoService {
    void hello(String name);
}
```

B服务编写接口的实现类

```java
@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public void hello(String name) {
        System.out.println("hello "+ name);
    }
}
```

这时候对于A服务来说，他是没有实现这个hello接口的，但是假设他们都连接到一个Nacos服务注册中心，A服务是有能力可以通过Dubbo的能力调用到B服务实现的hello方法的。等于是告诉B服务，来帮我处理这个请求。重点在于@DubboService这个注解，把这个Service注册到了nacos上面。

```java
@SpringBootTest
class CloudApplicationTests {
    @DubboReference
    DemoService demoService;
    @Test
    void dubboTest() {
        demoService.hello("花火");
    }
}
```

使用的时候通过    @DubboReference 获取到这个service，便可以使用。