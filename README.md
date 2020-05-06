# MySpringSecurity
# 创建spring security依赖
# 实现RBAC权限控制
    基于角色的权限访问控制，使用用户、角色、权限、用户角色多对多关联、角色权限多对多关联。核心是判断当前用户可以访问的url是否包含当前访问的url。
    1.自定义权限判断接口及实现
    2.在security配置中添加自定义权限表达式