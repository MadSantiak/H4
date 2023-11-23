from sense_hat import SenseHat
import time, datetime
import sys
import argparse
import signal
from flask import Flask, jsonify
import threading
app = Flask(__name__)
hat = SenseHat()

def api_task():
	@app.route("/humidity", methods=['GET'])
	def get_humidity():
		humidity = hat.get_humidity()
		return jsonify({'humidity': humidity})

	@app.route("/temp", methods=['GET'])
	def get_temp():
		temp = hat.get_temperature()
		return jsonify({'temperature': temp})

api_thread = threading.Thread(target=api_task)
api_thread.start()

if __name__ == '__main__':
	app.run(debug=True, host='0.0.0.0')
