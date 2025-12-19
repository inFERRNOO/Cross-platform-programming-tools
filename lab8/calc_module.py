import math
import struct

# ----------------------------------------
#   Функція обчислення y = tg(4x) / x
# ----------------------------------------
def calculate_expression(x):
    if x == 0:
        raise ValueError("Ділення на нуль: x не може бути 0.")
    return math.tan(4 * x) / x


# ----------------------------------------
#    Запис у текстовий файл
# ----------------------------------------
def write_text(filename, data_list):
    """
    Записує список чисел у текстовий файл.
    Кожне значення — в окремому рядку.
    """
    with open(filename, "w", encoding="utf-8") as file:
        for x, y in data_list:
            file.write(f"x={x:.5f}, y={y:.5f}\n")


# ----------------------------------------
#    Читання з текстового файлу
# ----------------------------------------
def read_text(filename):
    """
    Зчитує дані з текстового файлу.
    Повертає список кортежів (x, y).
    """
    results = []
    with open(filename, "r", encoding="utf-8") as file:
        for line in file:
            line = line.strip().replace("x=", "").replace("y=", "")
            x_str, y_str = line.split(",")
            x = float(x_str)
            y = float(y_str)
            results.append((x, y))
    return results


# ----------------------------------------
#    Запис у двійковий файл
# ----------------------------------------
def write_binary(filename, data_list):
    
    with open(filename, "wb") as file:
        for x, y in data_list:
            file.write(struct.pack("dd", x, y))  # double-double


# ----------------------------------------
#    Зчитування з двійкового файлу
# ----------------------------------------
def read_binary(filename):
   
    results = []
    with open(filename, "rb") as file:
        data = file.read()
        size = struct.calcsize("dd")
        for i in range(0, len(data), size):
            x, y = struct.unpack("dd", data[i:i + size])
            results.append((x, y))
    return results
