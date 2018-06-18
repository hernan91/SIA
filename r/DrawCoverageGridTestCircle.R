library(plotrix)
coveredPoints <- read.csv("/home/hernan/git/SIA/pruebas/M1/pre.csv", header = TRUE, sep = ",", dec = ".")
range= 10
png("/home/hernan/git/SIA/pruebas/M1/rrd.png", width=800, height=800)
plot(coveredPoints, col = 'black', xlim=c(0,60), ylim=c(0,60), plt="s")


draw.circle(11,10,range,border="purple",col=rgb(1,0,0,alpha =0.1),lty=1,lwd=1)
draw.circle(30,11,range,border="purple",col=rgb(1,0,0,alpha =0.1),lty=1,lwd=1)




dev.off()
