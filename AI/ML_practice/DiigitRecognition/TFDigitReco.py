import tensorflow as tf
import numpy as np
from scipy.misc import imread,imsave,imresize
import pickle
import gzip
import  cv2

class CNNModel:
	def __init__(self):
		self.layers = []

	def weights_variable(self,shape):
		initalizer = tf.truncated_normal(shape, stddev=0.1)
		return tf.Variable(initalizer)
	
	def bias_variable(self,shape):
		initalizer = tf.constant(0.1, shape=shape)
		return tf.Variable(initalizer)

	def conv2D(self,x,weights,stride=[1, 1, 1, 1],padding='SAME'):
		return tf.nn.conv2d(x,weights,strides = stride,padding = padding)

	def max_pool2D(self,x,stride = [1, 1, 1, 1],padding = 'SAME'):
		return tf.nn.max_pool(x, ksize=[1, 2, 2, 1],strides=stride, padding=padding)

	def conv_layer(self,input_layer,kernal_size=5,channels=1,layers = 8):
		Weight_conv = self.weights_variable([kernal_size,kernal_size,channels,layers])
		bias_conv = self.bias_variable([layers])
		h_conv = tf.nn.relu(self.conv2D(input_layer,Weight)+bias)
		h_pool = self.max_pool2D(h_conv)
		self.layers.append(h_pool)
		return h_pool

	def dense_layer(self,input_layer,layer_size = 1024):
		weight_dense = self.weights_variable([input_layer.shape[1],layer_size])
		bias_dens = self.bias_variable([layer_size])
		if(activation == 'relu')
			h_dense = tf.nn.relu(tf.matmul(input_layer, weight_dense) + bias_dens)
		elif(activation == 'softmax'):
			h_dense = tf.nn.relu(tf.matmul(input_layer, weight_dense) + bias_dens)		
		self.layers.append(h_dense)
		return h_dense

	def flaten(self,input_layer):
		flat_layer = tf.reshape(input_layer,(input_layer[0],-1))
		self.layers.append(flat_layer)
		return flat_layer

f = gzip.open('mnist.pkl.gz', 'rb')
u = pickle._Unpickler(f)
u.encoding = 'latin1'
train_set, valid_set, test_set = u.load()
f.close()

X_test,y_test = np.array(test_set[0]),np.array(test_set[1])
X_train, y_train = np.array(train_set[0]),np.array(train_set[1])

for img in X_test:
	img =  255*np.reshape(img,(28,28))
	img = img.astype(np.uint8)
	_,img = cv2.threshold(img,127,255,cv2.THRESH_BINARY)
	x,y,w,h = cv2.boundingRect(img)
	img = img[y:y+h,x:x+w]
	img = cv2.blur(img,(3,3))
	img = cv2.copyMakeBorder( img, 2,2,2,2,cv2.BORDER_CONSTANT)
	img = img.astype(np.float64)
	img = imresize(img,(28,28))
