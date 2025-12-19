class Weapon:
    def __init__(self, name: str, caliber: float, weight_kg: float):
        self._name = name
        self._caliber = caliber
        self._weight_kg = weight_kg
        self._chamber_status = "Empty" 
        print(f"Базова зброя '{self._name}' ({self._caliber} мм) створена.")

    def load(self, round_type: str = "Standard") -> bool:
        if self._chamber_status == "Empty":
            self._chamber_status = round_type
            print(f"[{self._name}] У патронник дослано патрон: {round_type}.")
            return True
        return False

    def fire(self) -> bool:
        if self._chamber_status != "Empty":
            print(f"[{self._name}] ({self._chamber_status} постріл)")
            self._chamber_status = "Empty" 
            return True
        else:
            print(f"[{self._name}] Клік. Зброя не заряджена.")
            return False
    
    def inspect(self) -> str:
        status = "Заряджено" if self._chamber_status != "Empty" else "Розряджено"
        return (f"\n--- {self._name} Характеристики ---\n"
                f"Калібр: {self._caliber} мм\n"
                f"Вага: {self._weight_kg} кг\n"
                f"Статус: {status}\n"
                f"Патронник: {self._chamber_status}")