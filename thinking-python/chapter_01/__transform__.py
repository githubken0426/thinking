# !/usr/bin/python
# -*- coding: UTF-8 -*-

# int(x [,base]),long(x [,base]),float(x) 将x转换为一个整数、长整型、浮点数
# x -- 字符串或数字。base -- 进制数，默认十进制。
print(int('10'))

# complex() 函数用于创建一个值为 real + imag * j 的复数或者转化一个字符串或数为复数。
# 如果第一个参数为字符串，则不需要指定第二个参数
# real -- int, long, float或字符串；imag -- int, long, float；
print(complex(1, 2))
print(complex(1))
print(complex("1"))
# 注意：这个地方在"+"号两边不能有空格，也就是不能写成"1 + 2j"，应该是"1+2j"，否则会报错
# ValueError: complex() arg is a malformed string
print(complex("1+2j"))
