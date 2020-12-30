import codecs
import sys
from selenium import webdriver
import re
from selenium.webdriver.common.by import  By
from selenium.webdriver.support.ui import WebDriverWait
import time
# from lxml import etree
import pymysql
from time import sleep
from selenium.webdriver import ChromeOptions
import random
#from pyquery import PyQuery as pq
from selenium.webdriver.support import expected_conditions as EC

phone = ['满减华为手机','华为手机','苹果手机','小米手机','三星手机','oppo手机','vivo手机']
computer = ['联想笔记本电脑','戴尔笔记本电脑','惠普笔记本电脑']
earphone = ['华为耳机','漫步者耳机','索尼耳机','苹果耳机']
productSearch = [phone,computer,earphone]
from selenium.webdriver import ActionChains
from pyquery import PyQuery as pq
#options.add_experimental_option('excludeSwitches'['enable-automation'])
#chrome_driver = r"C:\Python38\chromedriver.exe"
#browser = webdriver.Chrome(executable_path=chrome_driver)
#wait=WebDriverWait(browser,3)
import csv
folderPath = sys.argv[1]
# 1. 创建文件对象
f = open('2020 12 7_京东_满减华为手机数据.csv','w',newline='',encoding='utf-8-sig')
#f.write(codecs.BOM_UTF8)
# 2. 基于文件对象构建 csv写入对象
csv_writer = csv.writer(f)






chrome_option = webdriver.ChromeOptions()
chrome_option.add_experimental_option('excludeSwitches', ['enable-automation'])  # 以开发者模式
#chrome_option.add_argument('--proxy-server=http://127.0.0.1:9000') #正确的ip
#chrome_option.add_argument('--proxy-server=http://163.125.251.172:8088')
#chrome_option.add_argument('--proxy-server=http://127.0.0.1:9743')
#chrome_option.add_argument('--proxy-server=http://171.37.135.94:8123')
#chrome_option.add_argument("--proxy-server=http://171.37.135.94:8123")
#chrome_option.add_argument("--proxy-server=https://123.56.161.63 :80")
chrome_option.add_argument('disable-infobars')

browser1 = webdriver.Chrome(options=chrome_option)
browser1.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
  "source": """
    Object.defineProperty(navigator, 'webdriver', {
      get: () => undefined
    })
  """
})
wait = WebDriverWait(browser1, 10)

browser = webdriver.Chrome(options=chrome_option)
browser.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
  "source": """
    Object.defineProperty(navigator, 'webdriver', {
      get: () => undefined
    })
  """
})
wait = WebDriverWait(browser, 10)
global i
globali = 1
global msgtimeout
msgtimeout = 1



# 获取商品
def get_page():
    # 打开京东
    url = 'http://www.jd.com/'
    browser.get(url)
    # 找两个节点
    browser.find_element_by_xpath('//*[@id="key"]').send_keys('满减华为手机')
    browser.find_element_by_xpath('//*[@id="search"]/div/div[2]/button').click()
    # 留出时间给页面加载
    time.sleep(2)


# 解析页面
def parse_page():
    global globali
    favourables = []
    percentages = []
    gifts = []
    giftString = ""
    # 把下拉菜单拉到底部,执行JS脚本
    browser.execute_script(
        'window.scrollTo(0,document.body.scrollHeight)'
    )
    time.sleep(2)
    page4 = browser.page_source
    # 匹配所有商品节点对像列表
    title = browser.find_elements_by_xpath('//*[@class="gl-i-wrap"]/div[4]/a/em')
    productId = 0
    platform = 1#id是1代表京东，是2代表淘宝
    shop = browser.find_elements_by_xpath('//*[@class="gl-i-wrap"]/div[7]/span/a')
    buy_num = browser.find_elements_by_xpath('//*[@class="gl-i-wrap"]/div[5]/strong/a')
    ProductLink = browser.find_elements_by_xpath('//*[@class="gl-i-wrap"]/div[1]/a')
    length = len(ProductLink)
    parsei = 0
    for i in range(20):
        browser1.get(ProductLink[i].get_attribute('href'))
        page1 = browser1.page_source
        comment_button = WebDriverWait(browser1, 10).until(
            EC.presence_of_element_located((By.XPATH, '//li[@data-anchor="#comment"]'))
        )
        comment_button.click()
        time.sleep(2)
        #html = browser1.page_source
        # time.sleep(3)
        page2 = browser1.page_source
        #favourableTitle = browser1.find_elements_by_xpath('//*[@id="summary-quan"]/div[2]/dl/dd/a/span/span')
        favourableIllustration = browser1.find_elements_by_xpath('//*[@id="summary-quan"]/div[2]/dl/dd/a/span')
        #print(favourableIllustration[0].text)
        gift = browser1.find_elements_by_xpath('//*[@class="prom-gift-item"]/a')
        percentage = browser1.find_elements_by_xpath('//*[@class="percent-con"]')
        try:
            percentage1 = percentage[0].text
        except:
            percentage1 = '-1'
        percentage1.strip("%")
        percentages.append(percentage1)
        for i in gift:
            try:
                gift1 = i.get_attribute('title')
            except:
                gift1 = ""
            giftString += gift1 + ","
        gifts.append(giftString)
        favourableString = ""
        for i in favourableIllustration:
            try:
                favourableTitle1 = i.text
                favourableIllustration1 = i.get_attribute('title')
            except:
                favourableTitle1 = ""
                favourableIllustration1 = ""
            favourableString += favourableTitle1 + ":" + favourableIllustration1 + ","
        favourables.append(favourableString)
        sleep(1)
        parsei += 1
        print("第"+str(parsei+1)+"个商品")
    pictureLink = browser.find_elements_by_xpath('//*[@class="gl-i-wrap"]/div[1]/a/img')
    titles = []
    for i in title:
        try:
            title1 = i.text
        except:
            title1 == None
        titles.append(title1)
    print(titles)

    shops = []
    for i in shop:
        try:
            shop1 = i.get_attribute('title')
        except:
            shop1 == None
        shops.append(shop1)
    print(shops)

    buy_nums = []
    for i in buy_num:
        try:
            buy_num1 = i.text
        except:
            buy_num1 == None
        buy_nums.append(buy_num1)
    print(buy_nums)

    links = []
    for i in ProductLink:
        try:
            link1 = i.get_attribute('href')
        except:
            link1 = None
        links.append(link1)
    print(links)

    pictureLinks = []
    for i in pictureLink:
        try:
            link1 = i.get_attribute('src')
        except:
            link1 = None
        pictureLinks.append(link1)
    print(pictureLinks)
    #global globali
    length = len(titles)
    print(length)
    for i in range(20):
        csv_writer.writerow([titles[i], productId, platform, shops[i],favourables[i],gifts[i],percentages[i],buy_nums[i],links[i],pictureLinks[i]])
    globali += 1
    return globali


    sleep(10)


def main():

    get_page()

    # 3. 构建列表头
    csv_writer.writerow(["商品名", "id", "对应平台","店铺名","商品优惠","商品赠品","好评率","销量","商品网址","图片网址"])
    while True:
        global globali
        print("第" + str(globali) + "页")
        if globali == 2:
            break
        parse_page()
        # 判断是否为最后一页
        if browser.page_source.find('pn-next disabled') == -1:
            # 不是最后一页，点击下一页
            browser.find_element_by_class_name('pn-next').click()
            time.sleep(3)
        else:
            break
    f.close()



#if __name__ == '__main__':

main()


