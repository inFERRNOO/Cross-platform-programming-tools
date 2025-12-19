
"""
Основний модуль програми (точка входу).
Демонструє створення та використання класів Weapon та AutomaticRifle.
"""

from .automatic_rifle import AutomaticRifle

def main():
    """
    Головна функція для демонстрації роботи автомата.
    """
    print("==================================================")
    print("         СИМУЛЯЦІЯ АВТОМАТИЧНОЇ ЗБРОЇ             ")
    print("==================================================")
    
    ak74 = AutomaticRifle(
        name="AK-74",
        caliber=5.45,
        weight_kg=3.3,
        magazine_capacity=30,
        rate_of_fire=650
    )
    
    print("-" * 70)
    
    # 1. Заряджання та досилання патрона
    print(ak74.inspect())
    ak74.load_magazine(10) # Завантажуємо 10 патронів у магазин
    ak74.load() 
    print(ak74.inspect())
    
    print("-" * 70)

    # 2. Стрільба в одиночному режимі
    print("\n>>> РЕЖИМ: ОДИНОЧНИЙ")
    ak74.change_mode("SINGLE")
    ak74.fire() 
    ak74.fire() 
    print(ak74.inspect())

    print("-" * 70)

    # 3. Стрільба в автоматичному режимі
    print("\n>>> РЕЖИМ: АВТОМАТИЧНИЙ (черга на 5 патронів)")
    ak74.change_mode("AUTO") 
    ak74.fire(shots=5) # Черга
    print(ak74.inspect())
    
    print("-" * 70)
    
    # 4. Спроба стрільби без патронів (автомат закінчує магазин і робить сухий постріл)
    print("\n>>> Спроба стрільби, коли патрони закінчились")
    ak74.fire(shots=10) 

if __name__ == "__main__":
    main()