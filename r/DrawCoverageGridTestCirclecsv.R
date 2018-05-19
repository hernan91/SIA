coveredPoints <- read.csv("~/git/SIA/circularGridSolution.csv", header = TRUE, sep = ",", dec = ".")
range= 10
plot(coveredPoints, col = 'black')

draw.circle(10,10,range,border="purple",col=rgb(1,0,0,alpha =0.6),lty=1,lwd=1)
draw.circle(30,30,range,border="purple",col=rgb(1,0,0,alpha =0.6),lty=1,lwd=1)
draw.circle(50,50,range,border="purple",col=rgb(1,0,0,alpha =0.6),lty=1,lwd=1)



#png("~/git/SIA/r/img.png", width=800, height=800)
#dev.off()
