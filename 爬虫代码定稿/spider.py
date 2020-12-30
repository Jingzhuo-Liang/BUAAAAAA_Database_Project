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
from selenium.webdriver import ActionChains
from pyquery import PyQuery as pq
#options.add_experimental_option('excludeSwitches'['enable-automation'])
#chrome_driver = r"C:\Python38\chromedriver.exe"
#browser = webdriver.Chrome(executable_path=chrome_driver)
#wait=WebDriverWait(browser,3)
chrome_option = webdriver.ChromeOptions()
chrome_option.add_experimental_option('excludeSwitches', ['enable-automation'])  # 以开发者模式
#chrome_option.add_argument('--proxy-server=http://127.0.0.1:9000')
#chrome_option.add_argument('--proxy-server=http://27.43.191.223:9999')

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


global msgtimeout
msgtimeout = 1

def search():
    browser1.get('https://www.taobao.com')
    # sleep(5)
    # input = wait.until(EC.presence_of_all_elements_located((By.CSS_SELECTOR,'#q')))
    # submit=wait.until(EC.element_to_be_clickable(By.CSS_SELECTOR,"#J_TSearchForm > div.search-button"))
    browser1.find_element_by_name('q').send_keys('华为手机')
    sleep(1)
    browser1.find_element_by_xpath('//*[@id="J_TSearchForm"]/div[1]/button').click()  ##搜索按钮
    # browser.find_element_by_xpath('//*[@class="forget-pwd J_Quick2Static"]').click() #点击密码登录
    sleep(1)
    # browser.find_element_by_name('weibo-login').click()
    # browser.find_element_by_xpath('//*[@id="J_OtherLogin"]/a[1]').click() #点击微博登录
    # sleep(3)
    # browser.find_element_by_name('username').send_keys('18066908677')
    # browser.find_element_by_name('password').send_keys('21916541654Aa')
    page = browser.page_source
    browser1.find_element_by_xpath('//*[@id="fm-login-id"]').send_keys('18066908677')
    time.sleep(1.2)
    browser1.find_element_by_xpath('//*[@id="fm-login-password"]').send_keys('21916541654Aa!')
    time.sleep(1)
    # 点击登陆
    browser1.find_element_by_xpath('//*[@id="login-form"]/div[4]/button').click()
    #options = ChromeOptions()
    # 不加载图片,加快访问速度
    # options.add_experimental_option("prefs", {"profile.managed_default_content_settings.images": 2})
    # 设置为开发者模式，避免被识别
    #options.add_experimental_option('excludeSwitches', ['enable-automation'])
    # 使用代理
    #options.add_argument('--proxy-server=http://127.0.0.1:9000')
    #options.add_argument('disable-infobars')
    #chrome_driver = r"C:\Python38\chromedriver.exe"
    #browser = webdriver.Chrome(executable_path=chrome_driver)
    #wait = WebDriverWait(browser, 3)
    browser.get('https://www.taobao.com')
    #sleep(5)
    #input = wait.until(EC.presence_of_all_elements_located((By.CSS_SELECTOR,'#q')))
    #submit=wait.until(EC.element_to_be_clickable(By.CSS_SELECTOR,"#J_TSearchForm > div.search-button"))
    browser.find_element_by_name('q').send_keys('华为手机')
    sleep(1)
    browser.find_element_by_xpath('//*[@id="J_TSearchForm"]/div[1]/button').click()  ##搜索按钮
    # browser.find_element_by_xpath('//*[@class="forget-pwd J_Quick2Static"]').click() #点击密码登录
    sleep(1)
    #browser.find_element_by_name('weibo-login').click()
    #browser.find_element_by_xpath('//*[@id="J_OtherLogin"]/a[1]').click() #点击微博登录
    #sleep(3)
    #browser.find_element_by_name('username').send_keys('18066908677')
    #browser.find_element_by_name('password').send_keys('21916541654Aa')
    page = browser.page_source
    browser.find_element_by_xpath('//*[@id="fm-login-id"]').send_keys('18066908677')
    time.sleep(1.2)
    browser.find_element_by_xpath('//*[@id="fm-login-password"]').send_keys('21916541654Aa!')
    time.sleep(1)
    # 点击登陆
    browser.find_element_by_xpath('//*[@id="login-form"]/div[4]/button').click()



    # 等待登陆成功
    #WebDriverWait(self.driver, 600).until(EC.presence_of_element_located((By.XPATH, '//*[@id="mtb-nickname"]')))
    # 登陆成功
    #self.driver.get(url=self.index_url)

    #browser.find_element_by_xpath('//*[@id="J_SearchForm"]/button').click()  ##搜索
    #sleep(3)
    #total=browser.find_element_by_xpath('//*[@id="mainsrp-pager"]/div/div/div/div[1]')
    #print(total.text)
    sleep(0.3)
    total = wait.until(EC.presence_of_element_located((
        By.XPATH, '//*[@id="mainsrp-pager"]/div/div/div/div[1]'
    ))).text  # 等到python爬虫页面的总页数加载出来
    page = browser.page_source
    total = re.sub(r',|，', '', total)  # 发现总页数有逗号
    get_products(1)
    return total  # 返回总页数

    #return total.text

def database():
    coon = pymysql.connect(
        host='localhost', user='root', passwd='123456',
        port=3306, db='taobao', charset='utf8'
    )
    cur = coon.cursor()  # 建立游标
    return cur, coon

def get_products(page):
        price = browser.find_elements_by_xpath('//div[@class="price g_price g_price-highlight"]/strong')
        #price = browser.find_elements_by_xpath('//div[@class="shit"]/strong')
        title = browser.find_elements_by_xpath('//*[@id="mainsrp-itemlist"]/div/div/div[1]/div/div[2]/div[2]/a')
        place = browser.find_elements_by_xpath('//div[@class="row row-3 g-clearfix"]/div[2]')
        buy_num = browser.find_elements_by_xpath('//div[@class="row row-1 g-clearfix"]/div[2]')
        shop=browser.find_elements_by_xpath('//div[@class="shop"]/a/span[2]')
        ProductLink = browser.find_elements_by_xpath('//*[@id="mainsrp-itemlist"]/div/div/div[1]/div/div[2]/div[2]/a')
        pictureLinkFirst = browser.find_elements_by_xpath('//div[@class="item J_MouserOnverReq item-ad  "]/div[1]/div[1]/div[1]/a/img')
        pictureLink = browser.find_elements_by_xpath('//div[@class="item J_MouserOnverReq  "]/div[1]/div[1]/div[1]/a/img')
        length = len(ProductLink)
        for i in range(length):
            browser1.get(ProductLink[i].get_attribute('href'))
            page1 = browser1.page_source
            sleep(10)
            print("hi")

        print('第', page, '页,共有---', len(price), '个数据')

        prices = []
        for i in price:
            try:
                price1 = i.text
            except:
                price1 == None
            prices.append(price1)
        print(prices)
        titles=[]
        for i in title:
            try:
                title1 = i.text
            except:
                title1==None
            titles.append(title1)
        print(titles)

        places = []
        for i in place:
            try:
                place1 = i.text
            except:
                price1 == None
            places.append(place1)
        print(places)

        buy_nums = []
        for i in buy_num:
            try:
                buy_num1 = i.text
            except:
                buy_num1 == None
            buy_nums.append(buy_num1)
        print(buy_nums)

        shops = []
        for i in shop:
            try:
                shop1 = i.text
            except:
                shop1 == None
            shops.append(shop1)
        print(shops)
        #cur,coon=database()
        links = []
        for i in ProductLink:
            try:
                link1 = i.get_attribute('href')
            except:
                link1 = None
            links.append(link1)
        print(links)
        pictureLinks = []
        for i in pictureLinkFirst:
            try:
                link1 = i.get_attribute('src')
            except:
                link1 = None
            pictureLinks.append(link1)
        for i in pictureLink:
            try:
                link1 = i.get_attribute('src')
            except:
                link1 = None
            pictureLinks.append(link1)
        print(pictureLinks)
        for i in range(len(price)):
            try:
                shop=shops[i]
                buy_num=buy_nums[i]
                price=prices[i]
                title=titles[i]
                place=places[i]
                link = links[i]
                ss = (str(shop),str(title), str(price), str(place), str(buy_num),str(link),str(link1))
                print(ss)
                #sql = "insert into taobao_food(shop,title,price,place,buy_num) VALUE('%s','%s','%s','%s','%s')" % ss
                #cur.execute(sql)
            except:
                pass
        #coon.commit()
        print('------------------------------页数-------------------------------------')
        global msgtimeout
        msgtimeout = 0


def _lock_exist():
    '''判断是否存在滑动验证'''
    try:
        browser.find_element_by_xpath('//*[@id="nocaptcha-password"][@style="display: block;"]')
        return True
    except Exception as err:
        return False

def _unlock(self):
    bar_element = self.driver.find_element_by_id('nc_1_n1z')
    ActionChains(self.driver).drag_and_drop_by_offset(bar_element, 300, 0).perform()
    time.sleep(1.5)
        # 出现刷新按钮
    try:
        self.driver.find_element_by_xpath('//*[@id="nocaptcha-password"]/div/span/a').click()
        self._unlock()
    except Exception as err:
        print(err)
        raise Exception('滑动验证失败,被监测到了')

def next_page(page_number):
    global msgtimeout
    msgtimeout = 1
    try:
        input=browser.find_element_by_xpath('//*[@id="mainsrp-pager"]/div/div/div/div[2]/input')#填写页数
        submit=browser.find_element_by_xpath('//*[@id="mainsrp-pager"]/div/div/div/div[2]/span[3]')#确定键
        input.clear()
        input.send_keys(page_number)
        submit.click()
        print('第' + str(page_number) + '页正在翻------------')
        #print(browser.find_element_by_css_selector('#mainsrp-pager > div > div > div > ul > li.item.active > span'))
        if _lock_exist():
            _unlock()
        #time.sleep(5)
        wait.until(EC.text_to_be_present_in_element((By.CSS_SELECTOR,'#mainsrp-pager > div > div > div > ul > li.item.active > span'),str(page_number)))
        #global msgtimeout
        msgtimeout = 0
        get_products(page_number)
        #global msgtimeout
        msgtimeout = 0
    except TimeoutError:
        #global msgtimeout
        msgtimeout = 0
        next_page(page_number)
    finally:
        print(msgtimeout)
        if msgtimeout == 1:
            next_page(page_number)
        else:
            print(msgtimeout)
            return




def main():
    total=search()
    time.sleep(1)
    total=int(re.compile('(\d+)').search(total).group(1))
    #print(total)
    for i  in range(2,total+1):
        next_page(i)
        time.sleep(5)

if __name__ == '__main__':
    main()


