#!usr/bin/python3

"""
@package HAT Sense Binary_clock
@brief A binary clock for the HAT Sense
Small program designed for HAT Sense, based on code forked from
https://github.com/simonmonk/pi_magazine
With some correction to bring the code up to date with Python 3.

Added functionality beyond the basic clock code lifted, specifically:
- Meridiem formatting on/off
- Horizontal or Vertical display
- Start and Stop text and handling
- Joystick and CLI support.
"""

from sense_hat import SenseHat
import time, datetime
import sys
import argparse
import signal
from flask import Flask, jsonify
import threading

# hat contains the main Sense HAT functionality.
hat = SenseHat()


# The various colors for the LEDs on the HAT
year_color = (0, 255, 0)
month_color = (0, 0, 255)
day_color = (255, 0, 0)
hour_color = (0, 255, 0)
minute_color = (0, 0, 255)
second_color = (255, 0, 0)
hundrefths_color = (127, 127, 0)
off = (0, 0, 0)
W = (255, 255, 255)
AM = (0, 155, 255)
PM = (55, 155, 155)

# Boolean helpers and static values:
horizontal = True
meridiem_format = False
scroll_speed = 0.08

hat.clear()

def signal_handler(signal, frame):
	print(f"\nService stopped by signal {signal}")
	hat.show_message(f"{signal}", scroll_speed=scroll_speed)
	sys.exit(0)

signal.signal(signal.SIGINT, signal_handler)
signal.signal(signal.SIGTERM, signal_handler)

def perform_action(args):
	"""
	Function called in order to parse passed arguments when script is run via CLI
	"""
	global horizontal
	global meridiem_format
	if args.meridiem:
		meridiem_format = True
	if args.vertical:
		horizontal = False

parser = argparse.ArgumentParser("Sense HAT CLI")
parser.add_argument("--meridiem", action="store_true", help="Show time in meridiem (AM/PM) format")
parser.add_argument("--vertical", action="store_true", help="Shows binary output vertically instead of horizontally")
args = parser.parse_args()

perform_action(args)

def display_binary(value, row, color):
	"""
	Helper function that is used to iterate value of passed time-part.
	Sets pixel horizontalle (by row)
	"""
	value = int(value)
	binary_str = "{:08b}".format(value)
	for x in range(0, 8):
		if binary_str[x] == '1':
			hat.set_pixel(x, row, color)
		else:
			hat.set_pixel(x, row, off)


def display_binary_vertical(value, column, color):
	"""
	Helper function used to iterate value of passed time-part.
	Sets pixel vertically (by column)
	"""
	value = int(value)
	binary_str = "{:08b}".format(value)
	for x in range(0, 8):
		if binary_str[x] == '1':
			hat.set_pixel(column, x, color)
		else:
			hat.set_pixel(column, x, off)

def switch_listener(event):
	"""
	Listens for events on the HAT Joystick.
	Used to set various settings for the clock (orientation, format)
	"""
	global horizontal
	global meridiem_format  
	if event.action == 'pressed':
		if event.direction == "up":
			horizontal = not horizontal
		if event.direction == "down":
			meridiem_format = not meridiem_format
		hat.clear()


hat.stick.direction_any = switch_listener
try:
	hat.show_message("Programmet starter!", scroll_speed=scroll_speed)
	while True:
		t = datetime.datetime.now()
		hour = t.hour

		# Handle time depending on whether meridiem format (AM/PM) is chosen or not.
		if meridiem_format == True:
			hat.set_pixel(0, 7, W)
			hat.set_pixel(0, 6, PM)
			if hour > 12:
				hat.set_pixel(0, 6, PM)
				hour -= 12
			else:
				hat.set_pixel(0, 7, off)
	# Horizontal:
		if horizontal:
			display_binary(hour, 0, hour_color)
			display_binary(t.minute, 1, minute_color)
			display_binary(t.second, 2, second_color)
		else:
			hour1 = 0
			minute1 = 0
			second1 = 0
			if hour > 9:
				hour1 = int(str(hour)[0])
				hour2 = int(str(hour)[1])
			else:
				hour2 = hour

			if t.minute > 9:
				minute1 = int(str(t.minute)[0])
				minute2 = int(str(t.minute)[1])
			else:
				minute2 = t.minute

			if t.second > 9:
				second1 = int(str(t.second)[0])
				second2 = int(str(t.second)[1])
			else:
				second2 = t.second
			display_binary_vertical(hour1, 2, hour_color)
			display_binary_vertical(hour2, 3, hour_color)
			display_binary_vertical(minute1, 4, minute_color)
			display_binary_vertical(minute2, 5, minute_color)
			display_binary_vertical(second1, 6, second_color)
			display_binary_vertical(second2, 7, second_color)

		time.sleep(0.8)

# Essentially depricated, but kept for historic purposes.
# This is now handled via signal(SIGINT...) instead.        
except KeyboardInterrupt:
	try:
		hat.show_message("Programmet slutter!", scroll_speed=scroll_speed)
	except KeyboardInterrupt:
		print("\nOkay okay, in a rush, are we!?")
		hat.clear()
	hat.clear()
	print("\nExiting...")
	sys.exit(0)

