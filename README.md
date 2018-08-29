在线投票网站
--------------------------
author : yosoro
---------------------------






配置设置:
关于网站路径设置还有头像文件夹设置:
1.网站路径设置:如项目war包或者文件夹名为voteOnline
    则需要修改 源代码文件夹/src/main/resource/env中的env.properties文件的project_local的值为voteOnline
2.头像文件夹设置:
    头像文件夹在 源代码文件夹/avatar/中
    另外需要在tomcat根目录/conf/server.xml中的<Host>标签中添加
    <Context path='/项目名/avatar' doBase='图片文件夹所在路径' reloadable="true"></Context>
    如项目war包或者文件夹名为voteOnline,头像文件夹所在路径为C:/somebody/photo
    则内容为<Context path='/voteOnline/avatar' dobase='C:/somebody/photo'>
    如需要新建一个新的头像文件夹，则需要在新的头像文件夹中添加名为tmp的文件，若未添加系统会在修改用户头像功能中出错
-------------------------------------------------------------------------------------------------------
数据库配置修改
需要打开 源代码文件夹/src/main/resource/db/JdbcConfig.properties 的文件
url代表数据库链接地址
username代表数据库账号
password代表密码
drivername代表数据库启动名
默认情况下只需要修改数据库账号和密码即可
------------------------------------------------------------------------------------------------------
