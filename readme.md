## 系统微服务架构
一：MAVEN 顶级项目介绍

模块讲解
模块名称                                          功能介绍
ylcx-cloud          顶级项目（主要定义项目统一依赖版本）
    - -  ylcx-auth               结合spring security和outh2进行服务认证鉴权
    - -  ylcx-commons            平台公用配置
    - -  ylcx-gteway             结合nacos的网关服务   
    - -  ylcx-modules           平台服务（核心服务）（需要部署的服务）
	- -	 nacos					配置中心和注册中心服务，为三方配置服务 

二.使用到技术路线
java   1.8       
spring boot    2.5.3 --
spring cloud   2020.0.3 -
spring admin   1.5.6
mysql  6.0.3 -
mybatis  3.5.7 -
com.alibaba.druid   1.2.6 -
knife4j-spring-ui 3.0.3 -
maven 3.x -
redis 3.7.0-
security  1.1.1.RELEASE -
outh2 2.3.4.RELEASE -
mail 1.4.7 -

三. MAVEN子工程设计介绍
按照相关依赖进行模块讲解
1.ylcx-commons  （平台公用配置）
模块名称                                                     功能介绍
ylcx-commons                	                  平台公用配置
    - -  ylcx-commons-core                            平台工具核心模块
	
2.ylcx-modules   (平台服务)

服务名称                                                   功能介绍
ylcx-modules                                     平台核心功能服务
    - -  ylcx-system                          	 系统服务包


### 启动环境配置
nacos配置 解压nacos，内含启动策略，将项目中applicat.yml中xxx.xxx.x.xxx:8848替换成nacos启动的ip:8848地址即可 
-- 默认使用统一的nacos服务配置地址192.168.2.224:8848  
-- 下载地址：http://192.168.2.160/nacos.rar
-- 界面管理登录地址：http://192.168.2.224:8848/nacos
-- 数据库连接在nacos配置的yml中
-- 自动生成代码工具地址：http://192.168.2.160/generateJava.zip

### 系统操作条例
1. 数据删除修改
 ```
  程序中所有数据都因逻辑删除，待空间不足，或到特定时间，特定条件通过定时任务物理删除数据
 ```

### 数据库编码
1. 字符集
- MySQL 5.5.3之后增加了utfmb4字符编码
- 支持BMP（Basic Multilingual Plane，基本多文种平面）和补充字符
最多使用四个字节存储字符
- utf8mb4是utf8的超集并完全兼容utf8，能够用四个字节存储更多的字符。
- 标准的UTF-8字符集编码是可以使用1-4个字节去编码21位字符，这几乎包含了世界上所有能看见的语言。
MySQL里面实现的utf8最长使用3个字符，包含了大多数字符但并不是所有。例如emoji和一些不常用的汉字，如“墅”，这些需要四个字节才能编码的就不支持。
2. 排序字符集
utf8mb4_unicode_ci和utf8mb4_general_ci的对比：
- 准确性:
    - utf8mb4_unicode_ci是基于标准的Unicode来排序和比较，能够在各种语言之间精确排序
    - utf8mb4_general_ci没有实现Unicode排序规则，在遇到某些特殊语言或者字符集，排序结果可能不一致。
    但是，在绝大多数情况下，这些特殊字符的顺序并不需要那么精确。
- 性能:
    - utf8mb4_general_ci在比较和排序的时候更快
    - utf8mb4_unicode_ci在特殊情况下，Unicode排序规则为了能够处理特殊字符的情况，实现了略微复杂的排序算法。
    但是在绝大多数情况下发，不会发生此类复杂比较。相比选择哪一种collation，使用者更应该关心字符集与排序规则在db里需要统一。
