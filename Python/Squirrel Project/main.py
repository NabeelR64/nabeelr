"""Build data frame that looks like this
Fur Color, Count
0, grey, 2473
1, red, 392
2, black, 103"""

import pandas
data = pandas.read_csv("Squirrel Census.csv")
numGrey = len(data[data["Primary Fur Color"] == "Gray"])
numRed = len(data[data["Primary Fur Color"] == "Cinnamon"])
numBlack = len(data[data["Primary Fur Color"] == "Black"])
data_dict = {
    "Fur Color": ["Gray", "Red", "Black"],
    "Count": [numGrey, numRed, numBlack]
}
datafr = pandas.DataFrame(data_dict)
datafr.to_csv("SquirrelColorCount.csv")
