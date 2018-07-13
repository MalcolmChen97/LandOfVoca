import getopt
import sys
from bs4 import BeautifulSoup
import requests
import csv

import codecs


#some variables
wordbooks_string = ['【无老师7天TOEFL】List','扇贝精选GRE单词']
wordbooks_url = ['https://www.shanbay.com/wordbook/5440/','https://www.shanbay.com/wordbook/130180/']
index = 0



#check url and get html content
def check_link(url):
    try:

        r = requests.get(url)
        r.raise_for_status()
        r.encoding = r.apparent_encoding

        return r.text
    except:
        print('error 404')



#check whether its English or not, if yes then its a word. Not = useless elements on website
def is_alphabet(uchar):
    if ('\u0041' <= uchar <= '\u005a') or ('\u0061' <= uchar <= '\u007a'):
        return True
    else:
        return False



#find all elements in DOM with tr
def get_contents(urlist):
    result = []

    for url in urlist:

        content = check_link(url)
        soup = BeautifulSoup(content, 'lxml')

        trs = soup.find_all('tr')

        for tr in trs:
            ui = []
            for td in tr:
                ui.append(td.string)
            result.append(ui)

    return result


# get all urls contained in the main url
def get_urls(url_content, root_url="https://www.shanbay.com"):
    ulist = []
    soup = BeautifulSoup(url_content, 'lxml')
    urls = soup.find_all('a')

    for url in urls:
        try:
            global index

            if url.string.startswith(wordbooks_string[index]):

                ulist.append(root_url + url.get('href'))
                for j in range(2, 11):
                    extend_url = root_url + url.get('href') + '?page=' + str(j)
                    ulist.append(extend_url)
        except:
            pass
    return ulist

#save all useful results got from previous steps
def save_contents(result):
    global index
    with codecs.open(wordbooks_string[index], 'w', 'utf_8_sig') as f:
        writer = csv.writer(f)
        for i in range(len(result)):
            try:
                if is_alphabet(result[i][1]):
                    writer.writerow([result[i][1], result[i][3]])
                    print("write in line:", i)
            except:
                print("error in line:{}, contents is:{}".format(i, result[i]))


def main(wordbook):
    global  index
    if wordbook == 'toefl':
        index=0

    elif wordbook == 'gre':
        index=1


    src_url = wordbooks_url[index]
    #1:get the contents in source page
    src_content = check_link(src_url)
    #2:get all the useful urls in source page
    urls = get_urls(src_content)

    #3:scrapy all the useful contents from all the urls
    result = get_contents(urls)
    #4:save all the useful contents into csv
    save_contents(result)



if __name__ == "__main__":
   main(sys.argv[1])