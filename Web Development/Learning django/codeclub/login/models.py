from django.db import models

class spons(models.Model):
    name = models.CharField(max_length=100)
    pos = models.CharField(max_length=50)
    logo = models.CharField(max_length = 1000)

    def __str__(self):
        return(self.name + ' - ' + self.pos)

class Subcomp(models.Model):
    comp_posts = models.ForeignKey(spons,on_delete=models.CASCADE)
