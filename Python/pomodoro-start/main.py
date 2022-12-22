from tkinter import *
from math import *

# ---------------------------- CONSTANTS ------------------------------- #
PINK = "#e2979c"
RED = "#e7305b"
GREEN = "#9bdeac"
YELLOW = "#f7f5dd"
FONT_NAME = "Courier"
WORK_MIN = 25
SHORT_BREAK_MIN = 5
LONG_BREAK_MIN = 20
reps = 0
timer = None

# ---------------------------- TIMER RESET ------------------------------- #
def reset():
    window.after_cancel(timer)
    title_label.config(text='Timer')
    canvas.itemconfig(timer_text, text="00:00")
    checks.config(text="")
    global reps
    reps = 0


# ---------------------------- TIMER MECHANISM ------------------------------- #
def start_timer():
    global reps
    reps += 1
    work = WORK_MIN * 60
    short = SHORT_BREAK_MIN * 60
    long = LONG_BREAK_MIN * 60
    if reps % 8 == 0:
        count_down(long)
        title_label.config(text="Break", fg=RED)
    elif reps % 2 == 0:
        count_down(short)
        title_label.config(text="Break", fg=PINK)
    else:
        count_down(work)
        title_label.config(text="Work", fg=GREEN)


# ---------------------------- COUNTDOWN MECHANISM ------------------------------- #
def count_down(count):
    minutes = floor(count / 60)
    seconds = count % 60
    if seconds < 10:
        seconds = f"0{seconds}"
    canvas.itemconfig(timer_text, text=f"{minutes}:{seconds}")
    if count > 0:
        global timer
        timer = window.after(1000, count_down, count - 1)
    else:
        start_timer()
        marks = ""
        work_sessions = floor(reps / 2)
        for i in range(work_sessions):
            marks += "✔"
        checks.config(text=marks)


# ---------------------------- UI SETUP ------------------------------- #
window = Tk()
window.title("Pomodoro")
window.config(padx=100, pady=50, bg=YELLOW)

title_label = Label(text="Timer", fg=GREEN, bg=YELLOW, font=(FONT_NAME, 50, "bold"))
title_label.grid(column=1, row=0)

canvas = Canvas(width=200, height=224, bg=YELLOW, highlightthickness=0)
tomatoImg = PhotoImage(file="tomato.png")
canvas.create_image(100, 112, image=tomatoImg)
timer_text = canvas.create_text(100, 130, text="00:00", fill="white", font=(FONT_NAME, 35, "bold"))
canvas.grid(column=1, row=1)

start_button = Button(text="Start", highlightthickness=0, command=start_timer)
reset_button = Button(text="Reset", highlightthickness=0, command=reset)
start_button.grid(column=0, row=2)
reset_button.grid(column=2, row=2)

checks = Label(fg=GREEN, bg=YELLOW)
checks.grid(column=1, row=3)

window.mainloop()
