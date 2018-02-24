from django.http import HttpResponse
from .models import spons
from django.shortcuts import render,redirect
from django.contrib.auth import authenticate, login
from .forms import UserForm
from django.views.generic import View

def index(request):
    all_spons = spons.objects.all()
    html = ''
    context = {
        'all_spons' : all_spons
    }
    return render(request, 'login/home.html', context)

def creat(request):
    return HttpResponse('<h2>Creating account</h2>')

class UserFormView(View):
    form_class = UserForm
    template_name = 'login/home.html'

    def get(self, request):
        form = self.form_class(None)
        return render(request,self.template_name,{'form' : form})

    def post(self, request):
        form = self.form_class(request.POST)

        if form.is_valid():
            user = form.save(commit=False)

            username = form.cleaned_data['username']
            password = form.cleaned_data['password']
            user.set_password(password)
            user.save()

            user = authenticate(username=username, password=password)

            if user is not None:

                if user is active:
                    login(request, user)
                    return redirect('login:index')
            return render(request, 'login/home.html', context)
