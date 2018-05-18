library(plotrix)

transLocation = read.csv("/home/darkside/git/SIA/locations.csv", header = TRUE, sep = ",", dec = ".")
solution = read.csv("/home/darkside/git/SIA/solution.csv", header = FALSE, sep = ",", dec = ".")

x = transLocation$x
y = transLocation$y


# Graph the cars vector with all defaults
plot(x,y,pch=20, ylim=c(0,60), xlim=c(0,60))

range=10


vectorResult=c(1,1,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,0,1,0,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0)
for (i in 1:length(vectorResult)){
  if (vectorResult[i]==1){
    points(x[i],y[i],col=rgb(1,0,0),pch=20)
    draw.circle(x[i],y[i],range,border="purple",col=rgb(1,0,0,alpha =0.6),lty=1,lwd=1)
  }
}


