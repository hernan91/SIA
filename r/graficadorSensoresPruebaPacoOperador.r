library(plotrix)
dir1 = "~/git/SIA/pruebas/M1/pre.csv";
preInd = read.csv(dir1, header = TRUE, sep = ",", dec = ".")
dir2 = "~/git/SIA/pruebas/M1/post.csv";
postInd = read.csv(dir2, header = TRUE, sep = ",", dec = ".")

xi = preInd$x
yi = preInd$y
xj = postInd$x
yj = postInd$y

toImage = TRUE;

# Graph the cars vector with all defaults
if(toImage) png("~/git/SIA/pruebas/M1/new.png", width=800, height=800)
plot(xi,yi,pch=20, ylim=c(0,60), xlim=c(0,60))
plot(xj,yj,pch=20, ylim=c(0,60), xlim=c(0,60))


range=10

#  for (i in 1:9){
#    points(x[i],y[i],col=rgb(1,0,0),pch=20)
#    rect(x[i]-range, y[i]-range, x[i]+range, y[i]+range, col=rgb(1,0,0,alpha =0.6),lty=1,lwd=0.3)
# }

for (i in 1:length(xi)){
  #points(xi[i],yi[i],col=rgb(1,0,0),pch=20)
  draw.circle(xi[i],yi[i],range,border="purple",col=rgb(1,0.5,0,alpha =0.05),lty=1,lwd=0.5)
}

for (i in 1:length(xj)){
  #points(xj[i],yj[i],col=rgb(1,0,0),pch=20)
  draw.circle(xj[i],yj[i],range,border="black",col=rgb(0,0,1,alpha =0.2),lty=1,lwd=1)
}






if(toImage) dev.off()