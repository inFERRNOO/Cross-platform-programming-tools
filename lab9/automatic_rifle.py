from .base_weapon import Weapon
from typing import Literal, Set

class AutomaticRifle(Weapon):
    FireMode = Literal["SINGLE", "AUTO"]

    def __init__(self, name: str, caliber: float, weight_kg: float,
                 magazine_capacity: int, rate_of_fire: int):
        super().__init__(name, caliber, weight_kg)
        self._magazine_capacity = magazine_capacity
        self._rate_of_fire = rate_of_fire
        self._rounds_in_magazine = 0
        self._current_mode: self.FireMode = "SINGLE"
        self._available_modes: Set[self.FireMode] = {"SINGLE", "AUTO"}
        
        print(f"Автомат {self._name} ініціалізовано. Місткість магазину: {self._magazine_capacity}.")


    def load_magazine(self, rounds: int) -> None:
        """
        Заряджає магазин патронами.
        """
        load_count = min(rounds, self._magazine_capacity - self._rounds_in_magazine)
        self._rounds_in_magazine += load_count
        print(f"[{self._name}] Завантажено {load_count} патронів. У магазині: {self._rounds_in_magazine}/{self._magazine_capacity}.")

    def change_mode(self, new_mode: FireMode) -> None:
        """
        Змінює режим ведення вогню.
        """
        if new_mode in self._available_modes:
            self._current_mode = new_mode
            print(f"[{self._name}] Режим вогню змінено на: {new_mode}.")
        else:
            print(f"[{self._name}] Режим {new_mode} недоступний.")

    def _cycle_chamber(self) -> bool:
        """досилає патрон, якщо він є у магазині."""
        if self._chamber_status == "Empty" and self._rounds_in_magazine > 0:
            self._rounds_in_magazine -= 1
            super().load(round_type="Standard") 
            return True
        return False

    def fire(self, shots: int = 1) -> None:
        """
        Перевизначений метод для стрільби. Використовує поточний режим вогню.
        """
        if self._chamber_status == "Empty" and self._rounds_in_magazine > 0:
            self._cycle_chamber()
            
        # Перевірка неможливості стрільби
        if self._chamber_status == "Empty":
            print(f"[{self._name}] Немає патронів. Стрільба неможлива.")
            return

        rounds_fired = 0
        max_shots = shots if self._current_mode == "AUTO" else 1
        max_shots = min(max_shots, self._rounds_in_magazine + 1) 

        for _ in range(max_shots):
            if super().fire():
                rounds_fired += 1
                if not self._cycle_chamber():
                    break 
            
        print(f"[{self._name}] Випущено {rounds_fired} патронів у режимі: {self._current_mode}.")
    
    def inspect(self) -> str:
        base_info = super().inspect()
        return (f"{base_info}\n"
                f"Магазин: {self._rounds_in_magazine}/{self._magazine_capacity} патронів\n"
                f"Режим вогню: {self._current_mode}")