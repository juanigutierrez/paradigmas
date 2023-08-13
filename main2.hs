import City
import Point
import Quality
import Link
import Tunel

-- Crear ciudades
cityA = newC "City A" (newP 1 1)
cityB = newC "City B" (newP 2 2)

-- Obtener nombre de ciudad
    --name = nameC cityA
-- Calcular distancia entre ciudades
    --distance = distanceC cityA cityB

-- Crear calidad
quality1 = newQ "Type 1" 5 1.2
quality2 = newQ "Type 2" 3 0.8

-- Obtener capacidad y demora de calidad
    --capacity = capacityQ quality1
    --delay = delayQ quality2

-- Crear enlaces
linkAB = newL cityA cityB quality1
linkBA = newL cityB cityA quality2

-- Verificar si una ciudad está en el enlace
    --isConnectedA = connectsL cityA linkAB

-- Obtener capacidad y demora de enlace
    --linkCapacity = capacityL linkBA
    --linkDelay = delayL linkAB

-- Crear enlaces y túnel
tunnelLinks = [linkAB, linkBA]
tunnel = newT tunnelLinks

-- Verificar si dos ciudades están conectadas por el túnel
    --areConnected = connectsT cityA cityB tunnel

-- Verificar si un enlace atraviesa el túnel
    --usesLink = usesT linkAB tunnel

-- Obtener demora máxima en el túnel
    --maxTunnelDelay = delayT tunnel