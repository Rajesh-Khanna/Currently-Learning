from keras.models import Sequential
from keras.layers import Dense, Activation
from keras.utils import to_categorical
import numpy as np
import pickle
import gzip

model = Sequential()
model.add(Dense(16,input_dim = 784))
model.add(Activation('relu'))
model.add(Dense(32))
model.add(Activation('relu'))
model.add(Dense(10))
model.add(Activation('softmax'))
model.compile(optimizer = 'rmsprop',
	loss = 'categorical_crossentropy',
	metrics = ['accuracy'])

f = gzip.open('mnist.pkl.gz', 'rb')
u = pickle._Unpickler(f)
u.encoding = 'latin1'
train_set, valid_set, test_set = u.load()
f.close()
# test_set += valid_set

X_test,y_test = np.array(test_set[0]),np.array(test_set[1])
# valid_set_x, valid_set_y = np.array(valid_set[0]),np.array(valid_set[1])
X_train, y_train = np.array(train_set[0]),np.array(train_set[1])

y_train = to_categorical(y_train, num_classes=10)
y_test = to_categorical(y_test, num_classes=10)

model.fit(X_train,y_train,validation_split=0.1,epochs = 10,batch_size = 10)
model.summary()
score = model.evaluate(X_test, y_test)
model.save('MNIST2.h5')
model.save_weights('MNIST_weights2.h5')
print(score)
