from selenium import webdriver
from time import sleep
import random
import string
import codecs

from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.keys import Keys


class OneBot:
    def __init__(self, name):
        letters = string.ascii_uppercase
        # result_str = ''.join((random.choice(string.ascii_uppercase) for i in range(4)))
        # result_str += ''.join((random.choice(string.digits) for i in range(3)))

        options = Options()
        options.headless = True

        # poberi geckodriver-v0.27.0-win64 in spremeni ki je pot
        driver = webdriver.Firefox(options=options, executable_path=r'C:\Users\Erik\Downloads\geckodriver-v0.27.0-win64\geckodriver.exe')

        while True:
            driver.get("https://onepiecewt100.com/en/vote-character/1174")
            # sleep(1.5)
            try:
                driver.find_element_by_xpath("/html/body/div/div[2]/div[2]/div").click()
            except NoSuchElementException:
                pass
            sleep(5)
            driver.find_element_by_xpath("/html/body/div/div[1]/div[2]/div[1]/div[2]").click()

            sleep(5)
            driver.delete_all_cookies()


# tle napisi svoje podatki
# while True:
OneBot("yamato")
