# MySpringSecurity
    采用spring boot2+、sprng security、redis实现权限控制
    1.实现rbac1
    2.实现url动态权限鉴别
    3.实现Jwt整合
# 实现权限控制
    #实现RBAC权限控制
    基于角色的权限访问控制，使用用户、角色、权限、用户角色多对多关联、角色权限多对多关联。核心是判断当前用户可以访问的url是否包含当前访问的url。
    1.自定义权限判断接口及实现
    2.在security配置中添加自定义权限表达式,配置密码器
    3.自定义处理类
    4.自定义RBAC处理
         anyRequest().authenticated()
         anyRequest().access  
         二者同时出现时，认证未执行时，注意access使用后不使用authenticated,如有使用导致多次验证。
         通过AntPathMatcher判断url，以用来访问
    5.实现RBAC1角色之间的继承上级角色具有下级角色的所有权限，下级角色不一定具有上级角色的所有权限。
    6.权限颗粒度控制：对访问控制？对按钮控制？角色之间是否有继承的关系？使用动态的url判断是否不需要角色判断？
    #实现数据级权限控制
    1.ACL(access contoller list)访问控制列表，用于控制对象的访问权限。根据不同的角色访问不同的数据
    2.通过动态代理方式实现动态sql
    3.创建代理类，使用前置增强，生成数据权限过滤语句
    4.传递语句，通过xml或者provider获取语句进行拼接
    #实现数据字段级别控制
    1.自定义注解传递数据库表文件alias。
    2.通过动态代理实现动态sql，动态的sql通过动态代理方式添加到实体类中。
    3.传递sql语句通过provider传递语句。
# 实现JWT（JSON WEB TOKEN）
    jwt增加了用户脚手架的扩展性问题，把数据放在客户端，后端验证jwt。
    token = head(加密信息).载体(用户信息).签名()
    1.修改security配置类session为无状态
    2.定义JWT工具类，定义常量、定义Base64URL算法和解码方法、定义HmacSHA256加密算法和签名、验证token方法
    3.定义JWT内容（3个字段）
    4.修改登录成功处理器，在其实现回写token
    5.修改Role实体类实现GrantedAuthority（主要是用于反序列化）
      修改User实体类（注意字段名和父类相一致，主要是用于反序列化）
    6.编写token验证器（本质是一个过滤器）在信息验证通过后放到securityContextHolder中。替换session验证token判断登录。
#Excel文件上传下载
    1.构建excel自定义注解类，定义基本基础信息
    2.创建excel工具类进行实体类的
#redis
    1.使用redisTemplate类，先重写redisTemplate序列化方式，替换JDK序列化
    2.自定义缓存key生成策略，并默认使用该策略
    3.使快速序列化使用fastJson重写序列化器
#MQ

#工作流

#limit 接口限流
    1.自定义注解类
    2.aspect动态增强
    3.使用reids自增
#
#序列化
fastjaon
#字符判断
StringUtils.isNotBlank
            .isEmpty
#sql查询
查询本部门 SELECT * from user_info 
             u WHERE dep = 1

查询本部门及子部门 SELECT * FROM user_info 
                    u WHERE u.dep in
                 (SELECT d.id FROM department d WHERE FIND_IN_SET(1,ancestors))

查询本人 SELECT * FROM user_info 
          u  WHERE u.id = 1263387390332960768
