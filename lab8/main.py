from calc_module import (
    calculate_expression,
    write_text, read_text,
    write_binary, read_binary
)

# ----------------------------------------
#        Головна функція програми
# ----------------------------------------
def main():
    print("Обчислення виразу y = tg(4x) / x")

    x = float(input(f"Введіть x : "))
    try:
        y = calculate_expression(x)
        print(f"Результат: y = {y:.5f}")
    except ValueError as e:
        print("Помилка:", e)

    # Запис у текстовий файл
    write_text("results.txt", [(x, y)])
    print("Текстовий файл results.txt створено.")

    # Запис у двійковий файл
    write_binary("results.bin", [(x, y)])
    print("Двійковий файл results.bin створено.")

   
    print("\nЗчитано з текстового файлу:")
    for x, y in read_text("results.txt"):
        print(f"x={x}, y={y}")

    print("\nЗчитано з двійкового файлу:")
    for x, y in read_binary("results.bin"):
        print(f"x={x}, y={y}")


if __name__ == "__main__":
    main()
