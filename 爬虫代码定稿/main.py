#import spider
#import spiderJD
import os
date = '12_17日'
if not os.path.exists(date + '数据'):
    os.mkdir(date + '数据')
if not os.path.exists(date + '数据\\'+ date + '京东数据'):
    os.mkdir(date + '数据\\' + date + '京东数据')
if not os.path.exists(date + '数据\\'+ date + '淘宝数据'):
    os.mkdir(date + '数据\\' + date + '淘宝数据')
os.system("python spiderJD.py "+"京东"+date+"数据")
os.system("python spider.py "+"淘宝"+date+"数据")
