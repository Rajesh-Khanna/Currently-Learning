from keras.models import Sequential
from keras.layers import Activation
from keras.layers.convolutional import Conv2D,MaxPooling2D
from keras.layers.core import Dense,Dropout, Flatten
from keras.optimizers import Adadelta
from keras.utils import to_categorical
import cv2
from scipy.misc import imsave, imread, imresize
import numpy as np
import pickle
import gzip

f = gzip.open('mnist.pkl.gz', 'rb')
u = pickle._Unpickler(f)
u.encoding = 'latin1'
train_set, valid_set, test_set = u.load()
f.close()
# test_set += valid_set

X_test,y_test = np.array(test_set[0]),np.array(test_set[1])
# valid_set_x, valid_set_y = np.array(valid_set[0]),np.array(valid_set[1])
X_train, y_train = np.array(train_set[0]),np.array(train_set[1])


for img in X_test:
	img =  255*np.reshape(img,(28,28))
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
	img = cv2.copyMakeBorder(img,h+2,2+h,r+2,r+2,cv2.BORDER_CONSTANT)
	img = imresize(img,(28,28))
	img = img.astype(np.float64)
	cv2.imshow("img",img)
	cv2.waitKey(1)

for img in X_train:
	img =  255*np.reshape(img,(28,28))
	img = img.astype(np.uint8)
	_,img = cv2.threshold(img,127,255,cv2.THRESH_BINARY)
	x,y,w,h = cv2.boundingRect(img)
	img = img[y:y+h,x:x+w]
	r = h-w
	r//=2
	if r > 0:
		r//=2
	else:
		r = 0
	img = cv2.copyMakeBorder(img,2,2,r+2,r+2,cv2.BORDER_CONSTANT)
	img = cv2.blur(img,(3,3))
	img = img.astype(np.float64)
	img = imresize(img,(28,28))

y_train = to_categorical(y_train, num_classes=10)
y_test = to_categorical(y_test, num_classes=10)

X_train = np.reshape(X_train,(-1,28,28,1))
X_test = np.reshape(X_test,(-1,28,28,1))

model = Sequential([
	Conv2D(32,(3,3),padding='same',activation = 'relu',input_shape=(28,28,1)),
	MaxPooling2D(pool_size=(2,2)),
	Conv2D(64,(3,3),activation = 'relu'),
	MaxPooling2D(pool_size=(2,2)),
	Flatten(),
	Dense(128,activation = 'relu'),
	Dense(64,activation = 'relu'),
	Dense(10,activation = 'softmax')
	])
model.compile(optimizer = Adadelta(),
	loss = 'categorical_crossentropy',
	metrics = ['accuracy'])
model.fit(X_train,y_train,epochs = 2,batch_size = 50)
model.summary()
model.save('MNISTCONV4.h5')
model.save_weights('MNIST_weightsCONV4.h5')
score = model.evaluate(X_test, y_test)
print(score)