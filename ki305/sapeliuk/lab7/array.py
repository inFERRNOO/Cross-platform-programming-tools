import sys

def generate_array(n, fill_char):

    result = []
    half = n // 2
    count = 1

    for i in range(1, n + 1):
        if i > n / 2 and i < n:
            row = [" "] * half + [fill_char] * count
            count += 1
        elif i > n / 2:
            row = [" "] * half + [fill_char] * (i - half)
        else:
            row = [fill_char] * i
        result.append(row)

    return result

def main():
    try:
        
        n = int(input("Enter the size of the array: "))
        fill_char = input("Enter the character to fill the array: ")
        
        
        if fill_char == "":
            print("Error: Fill character cannot be empty.")
            sys.exit(1)
        elif len(fill_char) > 1:
            print("Error: Fill character must be a single character.")
            sys.exit(1)
        
        
        output = generate_array(n, fill_char)
        
        print(f"\nGenerated array (size: {n}, fill char: '{fill_char}'):")
        for row in output:
            print(' '.join(row))
    
    except ValueError:
        print("Error: Please enter a valid integer for array size.")
        sys.exit(1)
    

if __name__ == "__main__":
    main()
        
