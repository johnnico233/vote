在线投票网站Demo
----------------
author : yosoro
----------------
后端所用技术：Spring mvc,spring,mybatis

数据库： mysql

前端:html,css,js,jQuery,thymeleaf,bootstrap

主要功能
--------------------------------
1.账户管理功能：登录，注册，账户封停，用户关注
2.投票功能：发起投票，投票修改，投票删除，删除投票
3.留言功能：留言发送，留言修改，留言删除

配置设置:
-----------------------------------------------
关于网站路径设置还有头像文件夹设置:
**1.网站路径设置:如项目war包或者文件夹名为voteOnline
则需要修改 源代码文件夹/src/main/resource/env中的env.properties文件的project_local的值为voteOnline**
**2.头像文件夹设置:
如头像文件夹位置在 C:/somebody/photo 
则需要修改 源代码文件夹/src/main/resource/env中的env.properties文件的avatar_path_root的值为C:/somebody/photo**

**另外需要在tomcat根目录/conf/server.xml中的<Host>标签中添加**
<Context path='/项目名/avatar' doBase='图片文件夹所在路径' reloadable="true"></Context>
**如项目war包或者文件夹名为voteOnline,头像文件夹所在路径C:/somebody/photo
则内容为<context path='/voteonline/avatar' dobase='c:/somebody/photo'></context>**

数据库配置修改
需要打开 源代码文件夹/src/main/resource/db/JdbcConfig.properties 的文件
**url代表数据库链接地址
username代表数据库账号
password代表密码
drivername代表数据库启动名**
默认情况下只需要修改数据库账号和密码即可

