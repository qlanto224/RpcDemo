# RpcDemo
一个简单的rpc demo

## provider是服务提供方
pom文件中导入了tomcat内置包,commons-io工具包
结构:

pojo:封装的url对象,封装的Invocation对象(由于它需要在io中进行传递,所以对其进行序列化)

registry:注册中心,将url,invocation注册实例化

service:测试的需调用的方法

tomcat:配置内置tomcat

ServiceProvider:进行服务提供的注册及启动

## consumer 是服务消费者
pom文件中导入了commons-io工具包

pojo中是invocation实例化对象,封装了必要的一些信息,接口名,方法名,参数及参数对应的类型

service对应服务提供者的service,这里仅有接口,实质是提供者中的实现

consumer:httpclient中是封装了post请求

consumerMain:入口类,发起调用



