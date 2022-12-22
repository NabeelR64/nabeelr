# this is the impractical way to work with csv files, and gets annoying when you have
# large data sets

# import csv
# with open("weather_data.csv") as weather_data:
#     data = csv.reader(weather_data)
#     temps = []
#     for row in data:
#         if row[1] != "temp":
#             temps.append(int(row[1]))
#     print(temps)

# pandas makes life easier
# import pandas
# data = pandas.read_csv("weather_data.csv")
# here we use ["temp"] to access the temp column
# print(data["temp"])

import pandas
data = pandas.read_csv("weather_data.csv")
print(type(data))
# this return "data frame" which in the documentation is a 2d table, so any table from excel is
# considered a data frame in pandas

print(type(data["temp"]))
# this returns "series" or a 1d element, meaning list. this is the column of a table
# lets make the table into a dictionary. this works on a dataframe
print(data.to_dict())

# how to make series into list:
print(data["temp"].to_list())

# getting data in columns, either works
print(data["condition"])
print(data.condition)

# getting data in row
print(data[data.day == "Monday"])
print(data[data.temp == data.temp.max()])
monday = data[data.day == "Monday"]
print(monday.condition)
mon_temp = (int(monday.temp) * (9/5)) + 32
print(mon_temp)

# creating data frames from scratch using dictionaries
data_dict = {
    "students": ["Nabeel", "Ameen", "Jawad"],
    "scores": [88, 89, 87]
}
datafr = pandas.DataFrame(data_dict)
datafr.to_csv("new_data.csv")
