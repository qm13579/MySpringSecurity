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
# 实现JWT（JSON WEB TOKEN）
    jwt增加了用户脚手架的扩展性问题，把数据放在客户端，后端验证jwt。
    token = head(加密信息).载体(用户信息).签名()
    
    