## 数据库实验报告
若显示不出图片，可点击[这里](https://zhuanlan.zhihu.com/p/107196957)

### 1. 实现环境
#### 1.1 前端技术栈
+ Vue.js 4.5.8
+ ElementUI
+ Axios
+ Echarts
#### 1.2 后端技术栈
+ Spring Boot 2.1.1.RELEASE
+ Spring Data JPA
+ Lombok 1.18.16
#### 1.3 数据库
+ MySQL

### 2. 系统功能结构图
+ 用户功能模块
![用户](https://i.loli.net/2020/12/26/nosP8KIXTqrdUBg.jpg)

+ 管理员功能模块
![管理员](https://i.loli.net/2020/12/26/25NWpT6ZvKy8DEf.jpg)

### 3. 数据库基本表及约束
#### 3.1 管理员信息 (TABLE admin)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|ad_name|管理员名|char|10|无特殊字符|无|否|
|ad_id|管理员编号 | int|4|无|主键|否|
|ad_email|管理员邮箱|char|32|unique|无|否|
|ad_pwd|管理员密码|char|32|无|无|否|
|enabled|查看权限|Int|4|无|无|否|

#### 3.2 用户关系信息 (TABLE users)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|username|用户名|char|32|无特殊字符|无|否|
|email|用户邮箱|char|32|unique|无|否|
|id|用户编号|int|4|无|主键|否|
|password|用户密码|char|32|无|无|否|
|isStudent|是否学生|char|1|0/1|无|是|
|isMember|平台会员|char|10|每位0/1|无|是|
|isVip|是否vip|char|1|0/1|无|否|
|date|入会时间|date|3|无|无|是|

#### 3.3 收藏夹关系信息 (TABLE collection)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|userid|用户编号|int|4|无|外键引用 users(id), 主键 (userid, comid)|否|
|comid|商品编号|int|4|无|外键引用commodity(id), 主键 (userid, comid)|否|
|time|加入时间|date|3|无|无|否|

#### 3.4 商品关系 (TABLE commodity)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|cid|商品类型|int|4|无|无|否|
|comname|商品名|varchar|128|无|无|否|
|id|商品编号|int|4|无|主键|否|
|platform|平台编号|int|4|无|外键引用plat_id|否|
|storename|店铺名|char |32|无|无|是|
|gift|商品赠品|char|128|无|无|是|
|favorrate|好评率|float|4|[0,1]|无|是|
|sales|销量|int|4|>= 0 |无|是|
|url|商品网址|varchar|1024|unique|无|否|
|piclink|图片网址|varchar|512|无|无|是|

#### 3.5 平台关系 (TABLE platform)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|plat_name|平台名称|char|32|无|无|否|
|plat_web|平台网址|char|32|无|无|否|
|plat_id|平台编号|int|4|无|主键|否|

#### 3.6 价格关系 (TABLE price)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|comid|商品编号|int|4|无|外键引用commodity(id), 主键 (comid, date)|否|
|date|时间|date|3|无|主键 (comid, date)|否|
|price|商品原价|int|4|>=0|无|否|
|comDisc|商品优惠|int|4|>=0|无|是|
|platDisc|平台优惠|int|4|>=0|无|是|
|stuDisc|学生优惠|int|4|>=0|无|是|
|memDisc|平台会员优惠|int|4|>=0|无|是|

#### 3.7 平台优惠关系 (TABLE platformdiscount)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|platformId|平台编号|int|4|无|外键引用platform(plat_id), 主键 (platformId, time)|否|
|time|时间|date|3|无|主键 (platformId, time)|否|
|discount|平台优惠|char|32|无|无|是|
|memberDiscount|平台会员优惠|char|32|无|无|是|

#### 3.8 商品优惠关系 (TABLE commoditydiscount)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|comid|商品编号|int|4|无|外键引用commodity(id), 主键 (comid, time)|否|
|time|时间|date|3|无|主键(comid, time)|否|
|discount|商品优惠|char|32|无|无|是|

#### 3.9 留言关系 (TABLE leavemessage)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|comid|商品编号|int|4|无|外键引用commodity(id)|否|
|userid|用户编号|int|4|无|外键引用users(id)|否|
|id|留言编号|int|4|无|主键|否|
|time|留言时间|date|3|无|无|否|
|message|留言内容|char|255|无|无|否|

#### 3.10 留言回复关系 (TABLE replymessage)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|id|回复留言编号|int|4|无|主键|否|
|time|回复留言时间|date|3|无|无|否|
|userid|回复用户编号|int|4|无|外键引用users(id)|否|
|leavemessageid|被回复留言编号|int|4|无|外键引用leavemessage(id)|否|
|message|回复留言内容|char|255|无|无|否|

#### 3.11 浏览历史记录关系 (TABLE history)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|userid|用户编号|int|4|无|外键引用users(id), (userid, comid, time)|否|
|comid|商品编号|int|4|无|外键引用commodity(id), (userid, comid, time)|否|
|time|浏览时间|date|3|无|主键(userid, comid, time)|否|

#### 3.12 人气榜关系 (TABLE popular)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|pop_prod_id|商品编号|int|4|无|外键引用commodity(id), 主键|否|
|pop_pop|人气值|int|4|>=0|无|否|

#### 3.13 用户登录关系 (TABLE visits)
|数据项名|特征|数据类型|数据长度|值约束|键约束|允许空值|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|date|登录日期|date|3|无|无|否|
|userid|登录用户|int|4|无|外键引用users(id), 主键(time,userid)|否|
|time|登录时间|TIMESTAMP|8|无|主键(time,userid)|否|

### 4. 系统安全性设计
为管理员和普通用户设置不同页面和登录入口。同时对管理员权限分为是否有 `INSERT, UPDATE, DELETE` 权限，没有上述权限的为普通管理员，仅有 `SELECT ` 权限。

### 5. SQL 代码说明
#### 5.1 存储过程
* 存储过程 `checkadmin` 在管理员前端界面登录中被调用。前端接收到用户名和密码的信息通过后端发送给数据库，在 `Admin` 表中进行查询，若存在对应用户，则返回用户的 `enabled` 属性（为 `0` 或 `1`），即代表了管理员权限，后面会用来对应前端不同视图。若无匹配用户，则返回 `-1`，以便后端进行相应错误处理操作。
```mysql
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkadmin`(IN adname VARCHAR(10),IN adpwd VARCHAR(32))
Begin
	if exists (select * from admins where ad_name = adname and ad_pwd = adpwd) 
	then select enabled from admins where ad_name = adname and ad_pwd = adpwd;

	ELSE SELECT -1;
	
	end if;
End
```

* 存储过程 `vv` 在管理员界面中 Visit View 统计图表中调用。Visit View 主要用来统计过去一周内每日用户登录总次数。后端获取 7 天前日期作为参数 `begin_date` 传入存储过程，数据库在用户每日登录信息 `visit` 表中查询从 `begin_date` 起到今日每天登录总次数和对应的日期作为输出。

```mysql
CREATE DEFINER=`root`@`localhost` PROCEDURE `vv`(in begin_date date)
BEGIN
	select day,count(day) as cntday from visits where day>begin_date group by day;
END
```

#### 5.2 触发器
* 为了实现人气榜的自动更新功能，使用了触发器。从`collection`表中选出收藏人数最多的前五个商品作为人气榜中的商品。
具体实现方式为，在每一个用户对一个商品点击收藏操作之后，则将`popular`（人气榜）表中的数据清空，并选取`collection`中收藏人数最多的前五个商品插入到`popular`表中，并将人气值设置为收藏人数 * 100。
相关语句为:
```mysql
create trigger updatePopularAuto 
after insert on collection for each row
begin
	delete  from popular;
	insert into popular (select comid, count(comid) * 100 from collection group by comid LIMIT 5);
end 
```
#### 5.3 函数说明
+ 为实现管理员对某个商品删除时需要删除有关该商品的所有相关信息时(即数据库中表的某一个属性为商品的`id`)时，需要相关函数，如下：
```mysql
create function deleteCommodity(comid int) returns int
begin
	delete  from commodity where commodity.id = comid;
	delete  from price where price.comid = comid;
	delete  from collection where collection.comid = comid;
	delete  from commoditydiscount where commoditydiscount.comid = comid;
	delete  from history where history.comid = comid;
	delete  from replymessage where replymessage.leavemessageid in (select id from leavemessage where leavemessage.comid = comid);
	delete from leavemessage where leavemessage.comid = comid;
	delete from popular where popular.id = comid;
	return 0;
end
```
+ 管理员在删除留言信息时，也要删除有关的回复信息，可设置如下函数:
```mysql
create function deleteLeaveMessage(id int) returns int
begin
	delete from leavemessage where leavemessage.id = id;
	delete from replymessage where replymessage.leavemessageid = id;
	return 0;
end
```

### 6. 主要技术论述
#### 6.1 研究背景
如在设计报告中所论述，近年来网购已经逐渐成为人民的日常消费需求，所以如何在网购时可以在众多平台中选择到质量好且价优的心仪商品就成为了很重要的问题。

虽然目前有实现相关功能的网站，如 *比一比价* 和 *慢慢买* 网站，但他们都具有一定问题。如仅有商品实时价格，未考虑平台优惠；未考虑用户信息，即是否有平台会员或者可以享受学生优惠；商品信息杂乱，无法进行有效搜索；无商品收藏和浏览信息记录功能；前端网页不美观等问题。

为实现帮助顾客进行有效比价的功能，且解决现有比价网站的缺陷，我们设计了 *货比三家* 比价网站。

#### 6.2 实现概述
* 项目主要采用 Vue.js + Spring Boot 框架进行前后端分离式 Web 开发。前端分为 *管理员界面*（分为管理员与超级管理员）和 *用户界面* ，通过 API 向后端请求或发送数据进行交互，后端再通过 JPA 操作数据库。
* 本项目中设计了爬虫，每日自动爬取京东、淘宝商品价格、优惠等数据信息导入数据库，实现动态更新商品信息。并且针对淘宝的反扒机制进行了特殊处理。

#### 6.3 前端技术论述
+ 前端主要使用`Vue`框架。`Vue`是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。
+ 用`ElementUI`实现各种组件。`Vue`核心思想是组件和数据驱动,但是每一个组件都需要自己编写模板,样式,添加事件,数据等是非常麻烦的，所以使用基于VUE2.0的组件库`ElementUI`提供的现成PC端组件.
+ 用`Echarts`来实现图表。`ECharts`是一个使用 `JavaScript`实现的开源可视化库，可以流畅的运行在 `PC` 和移动设备上，兼容当前绝大部分浏览器（`IE8/9/10/11，Chrome，Firefox，Safari`等），底层依赖轻量级的矢量图形库 ZRender，提供直观，交互丰富，可高度个性化定制的数据可视化图表。

#### 6.4 后端技术论述
+ 后端主要使用`SpringBoot`框架。`Spring Boot`是所有基于 `Spring` 开发的项目的起点。`Spring Boot`的设计是为了尽可能快的跑起来`Spring` 应用程序并且尽可能减少配置文件。其具有快速创建独立运行的`Spring`项目以及与主流框架集成；使用嵌入式`Servlet`容器，应用不需要打包成`war`；`starts`自动依赖与版本控制；无需大量配置，简化开发等优点。
+ 操作数据库主要使用`JPA`。`JPA（Java Persistence API)`是SUN官方推出的Java持久化规范，它为Java开发人员提供了一种对象/关联映射工具来管理Java应用中的关系数据。它的出现主要是为了简化现有的持久化开发工作和整合ORM技术，结束现在`Hibernate，TopLink，JDO`等ORM框架各自为营的局面。JPA是在充分吸收了现有`Hibernate，TopLink，JDO`等ORM框架的基础上发展而来的，具有易于使用，伸缩性强等优点。
+ 后端还使用了 `Lombok` 。`Lombok` 是一个Java库，能自动插入编辑器并构建工具，简化Java开发。通过添加注解的方式，不需要为类编写 `setter/getter、equals、canEqual、hashCode、toString` 方法，同时可以自动化日志变量，使代码更加简洁。

### 7. 系统演示示例
#### 用户外模式
+ 登录功能

    用户可根据邮箱和密码进行登录。用户名或者密码错误则不会登录成功，同时会有信息提示。

    ![登录]( https://i.loli.net/2020/12/29/qJZWFw78V5rxLoD.png)

+ 注册功能

    用户根据信息进行注册，其中用户`id`系统自动生成，邮箱不能重复。其中用户名、邮箱、密码字段不能为空，其他信息可以为空。

    ![注册](https://i.loli.net/2020/12/29/ifB4JN2X9SWLhCP.png)

+ 人气榜功能

    人气榜根据商品收藏人数进行更新（详细见触发器功能），选出5个人气值最高的商品进行展示。

    ![人气榜展示](https://i.loli.net/2020/12/29/U1raujkT6WHSKq9.png)

+ 商品展示

    展示所有商品，包括商品的图片、商品名称、好评率、最新价格等信息，鼠标悬浮在图片上会显示完整信息。

    ![商品展示](https://i.loli.net/2020/12/29/rmCte7RY5wGqXSl.png)

+ 类别搜索

    用户可根据左侧导航栏选择相应的商品类别。类别有“手机、电脑、耳机、其他”，对应数据库商品表中的`cid`字段。

    ![分类搜索](https://i.loli.net/2020/12/29/SaZBbp4HPxsRW5i.png)

+ 关键词搜索

    用户可根据上方的搜索栏输入关键词进行搜索。关键需包含在商品名称、店铺名称、平台名称等信息内。

    ![关键词搜索](https://i.loli.net/2020/12/29/ScmCByKhwPTfIaQ.png)

+ 商品详细信息展示

    用户点击某一个商品图片后进入商品详细信息界面。在商品介绍部分包括商品名称、赠品信息、店铺名称、最新价格、优惠信息(平台优惠、商品优惠、学生优惠、`vip`优惠)，并根据以上信息自动生成最终价格。用户可选择直达链接和收藏。

    ![商品详细信息](https://i.loli.net/2020/12/29/SgRQdfIZXDa2H5p.png)

+ 商品历史价格趋势

    在商品的介绍下方为商品过去5日的价格变化趋势图。用户根据此图可以选择合适的时机进行购买。

    ![商品历史价格](https://i.loli.net/2020/12/29/NIbkC9d3L4BW5mc.png)

+ 留言功能展示

    在价格变化趋势图的下方为留言和回复功能，用户可点击相应的按钮进行留言和回复。

    ![留言展示](https://i.loli.net/2020/12/29/6G7jFK51COEIQqv.png)

+ 用户信息展示

    用户点击上面的用户名称后进入个人中心，显示用户详细信息。包括用户名、邮箱、会员信息等。

    ![个人信息](https://i.loli.net/2020/12/29/alO5HFbU3vqzkiN.png)

+ 用户信息修改

    用户在用户中心可修改个人信息(除邮箱外)，可修改用户名、会员信息、密码、是否为学生等信息。

    ![修改个人信息](https://i.loli.net/2020/12/29/pIO8zYr6QdCtqMs.png)

+ 收藏夹展示
    用户可根据左侧的选择栏查看收藏夹，并可查看商品详细信息和删除。

    ![收藏夹](https://i.loli.net/2020/12/29/G95LvtDmbrNgQ6O.png)

+ 浏览记录展示

    用户可根据左侧的选择栏产看浏览记录，并可查看商品详细信息和删除。

    ![浏览记录](https://i.loli.net/2020/12/29/PIm3wj2sacpXCgL.png)

+ 登出功能
    用户可点击左侧的选择栏中的登出进行登出。登出后用户所能体验的功能将受到限制，比如不能留言和回复、不能收藏商品、不能进入个人中心等。


#### 管理员外模式
* 登录功能
  * 管理员可以通过用户名和密码登录管理员界面。管理员信息存于 `admins` 表里，其中 `enabled` 属性标识了是管理员或超级管理员。登录界面会自动获取信息来根据权限展现不同前端界面。

  * 同时管理员界面还会根据登录的错误信息进行不同提示，如下图所示会根据信息提醒该用户名并不存在于表中。
  
  ![admin_login.png](https://i.loli.net/2020/12/26/qNrxbWhUVj9avMY.png)


* Home Page 生成统计报表
  VV（Visit View）是统计网站访问量的重要指标，用以统计所有访客1天内访问网站的次数。在管理员界面中首页会展示过去一周内每天的实际登录量和预期登录量，便于对网页访问量进行统计。

  ![admin_vv.png](https://i.loli.net/2020/12/26/kKUp5PmTD4tOx9N.png)

* 通用全局功能
  如上图所示，页面包含很多通用全局功能，如 `Tags View`（用以帮助用户导航）、`Screenfull`（全屏功能）、`Page Search`（全局搜索功能）、`Dynamic breadcrumb & Responsive Sidebar`。虽然这些功能大多可由模版实现，但也极大美化了界面，增强了实用性。

* Guide
  在 `Guide` 中，有对网站的介绍，还有对网页基本使用方法的介绍，便于初次登录的人熟悉界面。
  ![admin_guide1.png](https://i.loli.net/2020/12/26/mtOyADIdBWXN2j7.png)
  ![admin_guide2.png](https://i.loli.net/2020/12/26/9lN52DRwgZa6Of1.png)

* 不同权限控制
  * 管理员登录后，会自动控制页面展现的内容，如下面两图界面的对比可以看到，下图中对普通管理员相较超级管理员，缺少了一些功能，包括对一些用于调试的页面的查看和后面会讲到的，对于基本表增删减改的功能。

	![superadmin.png](https://i.loli.net/2020/12/26/IOywmr27aB4Eoh9.png)

	![superadmin.png](https://i.loli.net/2020/12/26/OaKlryWGz9Jsimq.png)

  * 同时超级管理员可以通过 `Page Permision` 功能切换到普通管理员界面，便于对普通管理员界面进行调试。

	![admin_perminsion.png](https://i.loli.net/2020/12/26/yjioITu6stPwQzK.png)

* 对基本表的增删改查
  1. 修改数据。超级管理员看到的基本表界面如下图所示。可以看到，以 `user table` 为例，超级管理员可以对每条数据通过 `Edit` 进行修改。
	![admin_add.png](https://i.loli.net/2020/12/26/TRwGvdVWguCQYN8.png)
	![admin_edit.png](https://i.loli.net/2020/12/26/Wd4VBFIUz6R81uq.png)

  2. 删除数据。通过 `Delete` 进行操作。
  3. 添加数据。通过点击 `Add` 进行添加。
	![admin_create.png](https://i.loli.net/2020/12/26/6rocOHZjpuYFi3W.png)
  4. 通过关键词搜索数据、选择顺序降序排列、根据属性进行搜索。对于普通管理员，这是对基本表的唯一操作，可以防止管理员误删改数据。
   ![admin_new1.png](https://i.loli.net/2020/12/26/z62MimwuyDRJn39.png)


### 8. 源程序简要说明
#### 8.1 下面以登录过程为例，介绍整个流程
+ 用户在登录界面相应的输入框内输入自己的用户名和密码并点击登录按钮
+ 前端获得用户输入的相应字段，调用`login`方法。在`login`方法中通过`axios`向`8666`端口的指定方法发送`JSON`格式的数据，并等待后端返回结果。
```javascript
login () {
	  this.$axios
	      .post('/login', {
	      email: this.loginForm.email,
	      password: this.loginForm.password
	  }).then(resp => {
	      if (resp && resp.data.code === 200) {
	          this.$store.commit('login',resp.data.data)
	          var path = this.$route.query.redirect
	          this.$router.replace({path: path === '/' || path === undefined ?'/Home':path})
	      }
	      else {
	          this.$alert('用户名或密码错误', '提示', {
	              confirmButtonText: '确定'
	          })
	      }
	  })
  }
```
+ 后端的`LoginController`类中的`login`方法接收到从前端发送的`JSON`并对其进行相应的处理。首先调用`Service`层定义`UserService`类的中的`get`方法。
```java
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        User user = userService.get(email, requestUser.getPassword());
	   if (null == user) {
            return ResultFactory.buildFailFactory("登录失败");
        }
        else {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessFactory(user);
        }
    }
}
```
+ 在`UserService`类中的`get`方法会调用`DAO`层定义的`UserDAO`接口中的`findByEmailAndPassword()`方法。
```java
@Service
public class UserService {
  	@Autowired
    UserDAO userDao;
    
  	public User get(String email, String password) {
  	    return userDao.findByEmailAndPassword(email,password);
  	}
}
```
+ 对于`UserDAO`接口，继承了`JpaRepository`类，通过`findByEmailAndPassword`方法来实现通过`email`和`password`字段找到用户的所有信息并将其层层返回；如果没有找到相应的用户则返回`null`
```java
public interface UserDAO extends JpaRepository<User,Integer> {
	User findByEmailAndPassword(String email, String password);
}
```
+ `Controller`层中的`login`方法根据`get`方法得到的返回值进行判断，若为`null`则在返回数据中写入`FAIL(400)`；若得到一个`User`对象则将其连同`SUCCESS(200)`一起写入返回数据。
+ 前端`login`方法得到返回数据并根据返回数据中的`code`进行判断，若为`200`则登录成功，并保存用户的各种信息；否则登录失败，显示提示信息。

#### 8.2 代码结构说明
##### 8.2.1 前端用户部分
前端用户部分文件为 `dbproject` ，代码结构如下所示
+ `components`包含组成页面的各种组件文件
	+ `commodityInfo`文件夹下主要是商品详细信息的组件文件。包括`CommodityInfo.vue`作为商品详细信息页面的主要部分；`Graph.vue`实现了商品价格走势的折线图；`Message.vue`作为商品啊详细信息页面的留言部分；`LeaveMessage.vue`作为留言部分的子件主要实现留言部分；`ReplyMessage.vue`作为留言部分的子件主要实现回复留言部分。
	+ `home`文件夹下主要是主页中的组件文件。包括`MainPage.vue`作为主页的载体；`Header.vue`作为主页中的页头部分；`SearchBar.vue`作为搜索栏；`Recommand.vue`作为主页中的人气榜部分；`Commodities.vue`在主页中显示所有商品；`SideMeau.vue`作为主页边的类别菜单；`Footer.vue`作为主页的页脚部分；
	+ `usercenter`文件夹下主要包含著称用户中心页面的一些组件。`UserCenter.vue`作为用户中心的主要部分，包含用户信息、收藏夹、浏览记录、登出4个模块；`UserInfoForm.vue`作为用户修改个人信息时的表单。
	+ `Login.vue`对应登录界面
	+ `Register.vue`对应注册界面。
+ `router`文件夹下只有`index.js`一个文件，作为路由文件。
+ `store`文件夹下只有`index.js`一个文件，作为储存全局变量的文件。
+ `main.js`作为全局配置文件。

##### 8.2.2 前端管理员部分	
前端管理员部分文件为 `dbprojectAdmin` ，代码结构如下所示
* `mock` 文件夹主要在前端开发过程中模拟数据。Mock 数据是前端开发过程中必不可少的一环，是分离前后端开发的关键链路。通过预先跟服务器端约定好的接口，模拟请求数据甚至逻辑，能够让前端开发更加独立自主，不会被服务端的开发所阻塞。随着前端功能的逐渐完善，再逐步替换 mock 接口，转为后端真实数据。
* `src/api` 文件夹下主要放置与后台 api 相关的文件。这里面有 axios 库的实例配置文件、使用配置的 axios 实例接入 api 获取数据的函数的集合的文件。
* `src/assets` 文件夹下主要放置静态资源，如 404 网页。
* `src/components` 文件夹下放置通用模块组件。
* `src/directive` 文件夹下为全局定义指令。包括 `el-drag-dialog`, `el-table`, `permission`（用于权限控制）
* `src/icons` 文件夹下是网页中的 svg 格式 icons。
* `src/layout` 文件夹下是页面布局，包含导航、侧边栏、面包屑以及内容。
* `src/router` 文件夹下为路由配置，可以指定路由对应的组件。
* `src/views` 文件夹下文件会在路由中被调用，包含 `dashboard`, `login`, `table`, `guide` 等。
* `main.js` 为全局入口。

##### 8.2.3 后端部分
* `common` 文件夹下为监听文件，这里监听优惠的变化。
* `controller` 文件夹下文件主要用来管理后端业务。
* `service` 管理具体功能。
* `dao` 主要完成和数据库相关的增删改查工作。
* `entity` 中包含了所有实体型。
* `result` 文件指定统一接口封装格式。

### 9. 工作分工

<table>
<tr>
<td></td>
<td>海绵宝宝</td>
<td>派大星</td>
<td>章鱼哥</td>
</tr>
<tr>
<td>系统设计阶段</td>
<td><ol>
<li>前期调研、分析系统需求</li>  
<li>设计数据库关系模式</li>  
<li>设计数据库系统的逻辑模式并将所有关系规范化到 3NF</li>
</ol></td>
<td><ol>
<li>前期调研、分析系统需求</li>
<li>设计数据库系统的概念模式（E-R图）</li>
<li>编写数据流图与数据元素表</li>
</ol></td>
<td><ol>
<li>前期调研、分析系统需求</li>
<li>设计并撰写设计库主要功能</li>
<li>撰写数据库需求分析</li>
</ol></td>
</tr>
<tr>
<td>系统实现阶段</td>
<td><ol>
<li>实现管理员 Web 界面</li>
<li>实现后端管理员部分</li>
<li>基本表、主外码等完整性约束定义</li> 
<li>编写存储过程</li>
</ol></td>
<td><ol>
<li>实现用户 Web 界面</li>
<li>实现后端用户部分</li>
<li>编写函数、触发器</li>
<li>数据库数据插入</li>
</ol></td>
<td><ol>
<li>数据库爬虫部分代码编写</li>
</ol></td>
</tr>
<tr>
<td>系统报告撰写</td>
<td><ol>
<li>数据库基本表、安全性部分</li>
<li>管理员相关前后端技术论述及说明</li>
<li>存储过程代码说明</li> 
</ol></td>
<td><ol>
<li>项目实现环境</li>
<li>用户相关前后端技术论述及说明</li>
<li>函数、触发器代码说明</li>
</ol></td>
<td><ol>
<li>申优 PPT</li>
</ol></td>
</tr>
</table>

### 10. 收获和体会
+ 组员之间要统一环境配置。最开始小组内部讨论的时候决定后端用`java`，但是并没有说明具体使用哪个版本，结果小组两名成员分别使用了`java8`和`java11`，在兼容性方面出现了问题，虽然在进行展示之前成功解决这个问题，但是也给我们留下了教训，在之后的项目开发中，开发人员之间一定商定开发环境。
+ 学习到了前端、后端、数据库之间的交互过程，知道了三者之间协同的重要性。各个部分有不同的功能，开项目设计阶段就要对功能进行划分并商定如何交互，之后三者之间完美协作才能做成一个好的项目。
+ 学习到了数据库设计对于后端和前端功能实现的重要性，数据库是整个项目的基础。数据库的设计将直接决定后端的实现，所以在项目设计阶段就要对数据库进行一个良好的设计，避免之后出现较大的改动。
