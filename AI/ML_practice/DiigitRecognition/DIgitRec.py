from flask import Flask,render_template,request
from scipy.misc import imsave, imread, imresize
from keras.models import load_model
import numpy as np
import tensorflow as tf
import base64
import cv2
import codecs,re
app = Flask(__name__)

def convertImage(imgData1):
	imgstr = re.search(b'base64,(.*)',imgData1).group(1)
	#print(imgstr)
	with open('output.png','wb') as output:
		output.write(base64.b64decode(imgstr))

model = load_model("MNISTCONV4.h5")
model.summary()
graph = tf.get_default_graph()

@app.route('/')
def index():
    return render_template("DigitsRecognition.html")

@app.route('/predict/',methods=['GET','POST'])
def PREDICT():
		img = request.get_data() #.decode('utf-8')
		convertImage (img)
		x = imread('output.png',mode='P')
		img = np.invert(x)
		# img =  255*np.reshape(x,(28,28))
		
		img = img.astype(np.uint8)
		_,img = cv2.threshold(img,127,255,cv2.THRESH_BINARY)
		x,y,w,h = cv2.boundingRect(img)
		img = img[y:y+h,x:x+w]
		r = h-w
		if r > 0:
			r//=2
			h = 0
		else:
			r = 0
			h = -r//2
		img = cv2.copyMakeBorder(img,2+h,2+h,r+2,r+2,cv2.BORDER_CONSTANT)
		img = imresize(img,(28,28))
		kernel = np.ones((3,3),np.uint8)
		img = cv2.dilate(img, kernel, iterations=1)
		img = cv2.blur(img,(3,3))
		x = img.astype(np.float64)
		img = 255-x
		# x = np.ceil(x/255)
		# print(len(x))
		imsave('op.png',img)
		x = np.reshape(x,(1,28,28,1))
		# x = x/255
		# x = np.array([np.ceil(x[0])])
		# x = np.array([x,])
		with graph.as_default():
			out = model.predict(x)
			
			# response = np.array_str(np.argmax(out,axis=1))
			# print(out)
			return np.array2string(out[0])


if __name__ == "__main__":
    app.run(debug=True,port=8080)
