import random

import pandas

numbers = [1, 2, 3]
add1 = []
for n in numbers:
    add1.append(n + 1)
# this is a simple way to create a new list that has all the numbers incremented 1

add1_alt = [n + 1 for n in numbers]
# this is an alternate method, called list comprehension
""" Basic format for this is 
new_list = [new_item for item in list]
"""
name = "Nabeel"
letters = [letter for letter in name]
doubles = [n * 2 for n in range(1, 5)]

# Conditional List Comprehension
"""new_list = [new_item for item in list if condition]"""
names = ["Nabeel", "Ameen", "Jawad", "Ali", "Osamah"]
newNames = [name for name in names if "e" in name]

numbers = [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
squares = [x ** 2 for x in numbers]
print(squares)

"""Now for Dictionary comprehension:
new_dict = {new_key:new_value for item in list}
new_dict = {new_key:new_value for (key,value) in dict.items()}"""
student_scores = {student: random.randint(1, 100) for student in names}
passed = {student: score for (student, score) in student_scores.items() if score > 75}

sentence = "What is the airspeed velocity of an unladen swallow?"
counts = {word: len(word) for word in sentence.split()}
print(counts)

weatherC = {
    "Monday": 12,
    "Tuesday": 14,
    "Wednesday": 15,
    "Thursday": 14,
    "Friday": 21,
    "Saturday": 22,
    "Sunday": 24
}
weatherF = {day: (temp * 9 / 5) + 32 for (day, temp) in weatherC.items()}
print(weatherF)

# Looping through dataframes
student_dict = {
    "student": ["Angela", "James", "lily"],
    "score": [55, 66, 77]
}
for (key, value) in student_dict.items():
    print(value)

student_datafr = pandas.DataFrame(student_dict)
print(student_datafr)
for (key, value) in student_datafr.items():
    print(value)

# loop through rows of a dataframe
for (index, row) in student_datafr.iterrows():  # index is the number at the start of the row
    print(row)
    print(row.student)
    print(row.score > 50)
