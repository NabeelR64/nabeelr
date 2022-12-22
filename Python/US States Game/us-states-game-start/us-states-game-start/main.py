import turtle
import pandas

screen = turtle.Screen()
screen.title("U.S. States Game")
image = "blank_states_img.gif"
screen.addshape(image)
turtle.shape(image)


correct_guesses = []
data = pandas.read_csv("50_states.csv")
dataList = data["state"].to_list()
while len(correct_guesses) < 50:
    answer_state = screen.textinput(title=f"{len(correct_guesses)}/50 Guess the State", prompt="Whats another state's name? ")
    answer_state = answer_state.title()
    """ This is the old code before list comprehension
    if answer_state == "Exit":
        missing_states = []
        for state in dataList:
            if state not in correct_guesses:
                missing_states.append(state)
        new_data = pandas.DataFrame(missing_states)
        new_data.to_csv("States to learn.csv")
        break
    """
    if answer_state == "Exit":
        missing_states = [state for state in dataList if state not in correct_guesses]
        new_data = pandas.DataFrame(missing_states)
        new_data.to_csv("States to learn.csv")
        break
    if answer_state in dataList:
        t= turtle.Turtle()
        t.hideturtle()
        t.penup()
        correct_guesses.append(answer_state)
        coor = data[data.state == answer_state]
        t.goto(int(coor.x), int(coor.y))
        t.write(answer_state)









screen.exitonclick()