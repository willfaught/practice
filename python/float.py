"""Float"""


def binary(f):
    if not (0 < f < 1):
        raise ValueError()
    symbols = []
    while f > 0:
        f2 = f * 2
        if f2 >= 1:
            symbols.append(1)
            f = f2 - 1
        else:
            symbols.append(0)
            f = f2
    return ''.join(str(x) for x in symbols)