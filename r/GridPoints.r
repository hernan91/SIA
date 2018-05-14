# Load sp package for creating artificial data
library(sp)

# Create sample town points
towns <- data.frame(lon = sample(100), lat = sample(100))
towns <- SpatialPoints(towns)

# Create sample polygon grid
grd <- GridTopology(c(1,1), c(10,10), c(10,10))
polys <- as.SpatialPolygons.GridTopology(grd)

# Plot polygons
plot(polys)

# Add towns (in red colour)
plot(towns, add = TRUE, col = 'red')
