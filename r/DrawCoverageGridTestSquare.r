coveredPoints <- read.csv("~/git/SIA/squareGridSolution.csv", header = TRUE, sep = ",", dec = ".")
plot(coveredPoints, col = 'black')

centerX = 30
centerY = 30
range=10

rect(centerX-range, centerY-range, centerX+range-1, centerY+range-1, col=rgb(1,0,0,alpha =0.6),lty=1,lwd=0.3)