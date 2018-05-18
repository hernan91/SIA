library(plotrix)

locationsData = read.csv("~/git/SIA/locations.csv", header = TRUE, sep = ",", dec = ".")
resultData = read.csv("~/git/SIA/solution.csv", header = FALSE, sep = ",", dec = ".")

x = locationsData$x
y = locationsData$y
solution = resultData$V1

if(TRUE){
  png("~/git/SIA/r/img.png", width=800, height=800)  
}
# Graph the cars vector with all defaults
plot(x,y,pch=20, ylim=c(0,60), xlim=c(0,60))

range=10

#  for (i in 1:9){
#    points(x[i],y[i],col=rgb(1,0,0),pch=20)
#    rect(x[i]-range, y[i]-range, x[i]+range, y[i]+range, col=rgb(1,0,0,alpha =0.6),lty=1,lwd=0.3)
# }

for (i in 1:length(solution)){
  if (solution[i]==1){
    points(x[i],y[i],col=rgb(1,0,0),pch=20)
    rect(x[i]-range, y[i]-range, x[i]+range, y[i]+range, col=rgb(1,0,0,alpha =0.6),lty=1,lwd=0.3)
    #draw.circle(x[i],y[i],range,border="purple",col=rgb(1,0,0,alpha =0.6),lty=1,lwd=1)
  }
}

dev.off()

