library(plotrix)
dir = "~/git/SIA/pruebas/M1/bestIndLocations.csv";
bestInd = read.csv(dir, header = TRUE, sep = ",", dec = ".")

x = bestInd$x
y = bestInd$y

toImage = TRUE;

if(toImage) png("~/git/SIA/pruebas/M1/bestIndLocations2.png", width=800, height=800)

# Graph the cars vector with all defaults
plot(x,y,pch=20, ylim=c(0,60), xlim=c(0,60))

range=5

#  for (i in 1:9){
#    points(x[i],y[i],col=rgb(1,0,0),pch=20)
#    rect(x[i]-range, y[i]-range, x[i]+range, y[i]+range, col=rgb(1,0,0,alpha =0.6),lty=1,lwd=0.3)
# }

for (i in 1:length(x)){
  points(x[i],y[i],col=rgb(1,0,0),pch=20)
  draw.circle(x[i],y[i],range,border="purple",col=rgb(1,0,0,alpha =0.3),lty=1,lwd=1)
}

if(toImage) dev.off()