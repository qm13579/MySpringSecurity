# MySpringSecurity
# 创建spring security依赖
# 实现RBAC权限控制
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
    
# 实现JWT（JSON WEB TOKEN）
    jwt增加了用户脚手架的扩展性问题，把数据放在客户端，后端验证jwt。
    token = head(加密信息).载体(用户信息).签名()
    1.修改security配置类session为无状态
    2.定义JWT工具类，定义常量、定义Base64URL算法和解码方法、定义HmacSHA256加密算法和签名、验证token方法
    3.定义JWT内容（3个字段）
    
    