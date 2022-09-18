# https://github.com/taizilongxu/interview_python


# python会给0-256这个范围的整数分配固定地址，不管什么变量只要值是这个范围，都是固定的地址
# strings、tuples、numbers都是不可变对象，python里面万物皆对象
def find_id_max():
    for i in range(1000):
        a = i + 1 - 1
        if id(a) != id(i):
            print(a, id(a), id(i))
            break


# metaclass  https://stackoverflow.com/questions/100003/what-are-metaclasses-in-python
class ObjectCreator(object):
    pass


def echo(o):
    print(o)


my_object = ObjectCreator()

print(my_object)
echo(ObjectCreator)
ObjectCreator.new_attribute = 'foo'
print(hasattr(ObjectCreator, 'new_attribute'))
print(ObjectCreator.new_attribute)
print(type(1))
print(type(ObjectCreator))
print(type(ObjectCreator()))

MyShinyClass = type('MyShinyClass', (), {})
print(MyShinyClass)

print("{}".format((1, 2, 3)))
