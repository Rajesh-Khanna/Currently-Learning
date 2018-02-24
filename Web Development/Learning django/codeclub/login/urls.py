from django.contrib import admin
from . import views
from django.urls import path

urlpatterns = [
    #login page
    path('',views.index, name='login'),
    #login/crete
    path('create_account/',views.creat, name='create_account'),

]
