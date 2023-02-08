from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import random
import string
import unittest
import time
from helper import MySQL


class WebTest(unittest.TestCase):
    def setUp(self) -> None:
        option = webdriver.ChromeOptions()
        option.add_argument('headless')
        self.driver = webdriver.Chrome('./chromedriver.exe', options=option)
        self.url = 'http://localhost:8080'
        self.driver.get(self.url)
        self.driver.maximize_window()
        self.driver.implicitly_wait(5)
        self.db = MySQL()

    def test_register(self):
        # 随机测试用例
        username = ''.join(random.sample(string.ascii_letters, 6))
        password = ''.join(random.sample(string.ascii_letters, 1)) + ''.join(
            random.sample(string.ascii_letters + string.digits + '_', 10))
        # 点击注册按钮
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/div[4]').click()
        self.driver.implicitly_wait(5)
        # 输入数据
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/div[2]/input').send_keys(username)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/div[3]/input').send_keys(password)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/div[4]/input').send_keys(password)
        # 点击注册
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/button').click()
        # 判断是否注册成功
        time.sleep(1)  # 避免网络延时
        data = self.db.Search("select * from _user where _user.name='{}'".format(username))
        try:
            assert len(data) != 0
        except Exception as e:
            raise e
        finally:
            # 数据库中删除数据
            self.db.Operation("delete from _user where name='{}'".format(username))

    def test_login(self):
        self.login()
        # 判断是否登录成功
        token = self.driver.execute_script('return localStorage.getItem("token");')
        assert token != ''

    def test_create_search_abolish(self):
        # 登录账号
        self.login()
        # 创建
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/div[1]/div[1]/div[4]/span').click()
        doc_title = ''.join(random.sample(string.ascii_letters, 6))
        doc_identifier = ''.join(random.sample(string.ascii_letters, 6))
        doc_depart = ''.join(random.sample(string.ascii_letters, 6))
        interpret_depart = ''.join(random.sample(string.ascii_letters, 6))
        interpret_title = ''.join(random.sample(string.ascii_letters, 6))
        interpret_abstract = ''.join(random.sample(string.ascii_letters, 12))
        interpret_content = ''.join(random.sample(string.ascii_letters, 30))
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[1]/div/div/input').send_keys(doc_title)
        self.driver.find_element(By.XPATH, 'html/body/div/div/div[2]/section/main/form/div[2]/div/div/input').send_keys(doc_identifier)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[3]/div/div/input').send_keys(doc_depart)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[4]/div/div/input').send_keys(interpret_depart)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[5]/div/div/input').send_keys(interpret_title)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[6]/div/div/textarea').send_keys(interpret_abstract)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[7]/div/div/textarea').send_keys(interpret_content)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/section/main/form/div[8]/div/button[1]').click()
        # 判断是否创建成功
        time.sleep(1)  # 避免网络延时
        data = self.db.Search("select * from interpretation where doc_title='{}' and doc_identifier='{}' and doc_depart='{}' and "
                              "interpret_depart='{}' and interpret_title='{}'".format(doc_title, doc_identifier, doc_depart, interpret_depart, interpret_title))
        try:
            assert len(data) != 0 and data[0][7] == interpret_abstract and data[0][4] == interpret_content and data[0][8] == 'PUBLIC'
        except Exception as e:
            raise e
        # 废止
        self.driver.find_element(By.XPATH, '/html/body/div[1]/div/div[2]/div/div[1]/div[1]/span').click()
        self.driver.find_element(By.XPATH, '/html/body/div[1]/div/div[2]/section/header/div/div[1]/div[1]/div/input').send_keys(doc_title)
        self.driver.find_element(By.XPATH, '/html/body/div[1]/div/div[2]/section/header/div/div[1]/div[2]/div/input').send_keys(doc_identifier)
        self.driver.find_element(By.XPATH, '/html/body/div[1]/div/div[2]/section/header/div/div[1]/div[3]/div/input').send_keys(doc_depart)
        self.driver.find_element(By.XPATH, '/html/body/div[1]/div/div[2]/section/header/div/div[2]/div[2]/button[1]').click()
        self.driver.implicitly_wait(5)
        self.driver.find_element(By.XPATH, '/html/body/div[1]/div/div[2]/section/main/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[8]/div/button[2]').click()
        self.driver.find_element(By.XPATH, '/html/body/div[4]/div/div[3]/button[2]').click()
        # 判断状态是否更改成功
        time.sleep(1)
        data = self.db.Search("select * from interpretation where doc_title='{}' and doc_identifier='{}' and doc_depart='{}' and "
            "interpret_depart='{}' and interpret_title='{}'".format(doc_title, doc_identifier, doc_depart, interpret_depart, interpret_title))
        try:
            assert len(data) != 0 and data[0][8] == 'ABOLISH'
        except Exception as e:
            raise e
        # 删掉该记录
        try:
            self.db.Operation("delete from interpretation where doc_title='{}' and doc_identifier='{}' and doc_depart='{}' and "
                              "interpret_depart='{}' and interpret_title='{}'".format(doc_title, doc_identifier, doc_depart, interpret_depart, interpret_title))
        except Exception as e:
            raise e

    def test_detail(self):
        self.login()
        self.driver.find_element(By.XPATH, '/html/body/div/div/div[2]/div/div[1]/div[1]/span').click()
        identifier = self.driver.find_element(By.XPATH,
                                              '/html/body/div/div/div[2]/section/main/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[2]/div').text
        self.driver.find_element(By.XPATH,
                                 '/html/body/div[1]/div/div[2]/section/main/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[8]/div/button[1]').click()
        WebDriverWait(self.driver, 5).until(EC.presence_of_element_located(
            (By.XPATH, '/html/body/div/div/div[2]/section/main/div/div/div/div[2]/table/tbody[1]/tr[2]/td/div/span')))
        target = self.driver.find_element(By.XPATH,
                                          '/html/body/div[1]/div/div[2]/section/main/div/div/div/div[2]/table/tbody[2]/tr[2]/td[1]/div').text
        assert target == identifier

    def tearDown(self) -> None:
        self.driver.quit()

    def login(self):
        # 测试数据
        username = 'cccaaa'
        password = '123321'
        # 输入数据
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/div[2]/input').send_keys(username)
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/div[3]/input').send_keys(password)
        # 点击登录
        self.driver.find_element(By.XPATH, '/html/body/div/div/div/button').click()
        WebDriverWait(self.driver, 5).until(
            EC.presence_of_element_located((By.XPATH, '/html/body/div[1]/div/div[1]/div[2]')))


if __name__ == '__main__':
    unittest.main()
