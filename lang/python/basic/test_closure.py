def make_multiplier_of(n):
    def multiplier(x):
        return x * n

    return multiplier


# Multiplier of 3
times3 = make_multiplier_of(3)

# Multiplier of 5
times5 = make_multiplier_of(5)

# 输出: 27
print(times3(9))

# 输出: 15
print(times5(3))

# 输出: 30
print(times5(times3(2)))

print(make_multiplier_of.__closure__)
print(times3.__closure__)
print(times3.__closure__[0].cell_contents)
print(times5.__closure__[0].cell_contents)

assert 1 == 1
