import City
import Point
import Quality
import Link
import Tunel

import Region
-- Crear ciudades
cityA = newC "City A" (newP 1 1)
cityB = newC "City B" (newP 2 2)
cityC = newC "City C" (newP 3 3)

-- Obtener nombre de ciudad
    --name = nameC cityA
-- Calcular distancia entre ciudades
    --distance = distanceC cityA cityB

-- Crear calidad
quality1 = newQ "Type 1" 5 1.0
quality2 = newQ "Type 2" 3 2.2

-- Obtener capacidad y demora de calidad
    --capacity = capacityQ quality1
    --delay = delayQ quality2

-- Crear enlaces
linkAB = newL cityA cityB quality1
linkBA = newL cityB cityA quality2
linkBC = newL cityB cityC quality2

-- Verificar si una ciudad está en el enlace
    --isConnectedA = connectsL cityA linkAB

-- Obtener capacidad y demora de enlace
    --linkCapacity = capacityL linkBA
    --linkDelay = delayL linkAB

-- Crear enlaces y túnel
tunnel1 = newT [linkAB, linkBC]
tunnel2 = newT [linkBC, linkBA]
tunnel3 = newT [linkBC]

-- Verificar si dos ciudades están conectadas por el túnel
    --areConnected = connectsT cityA cityB tunnel

-- Verificar si un enlace atraviesa el túnel
    --usesLink = usesT linkAB tunnel

-- Obtener demora máxima en el túnel
    --maxTunnelDelay = delayT tunnel

region1 = newR
-- Agregar una nueva ciudad a la región
    --newRegion = foundR initialRegion cityC
-- Enlazar dos ciudades en la región con un enlace de calidad
quality3 = newQ "Type 3" 4 1.0
    --linkedRegion = linkR newRegion cityA cityC quality3

-- Verificar si dos ciudades están conectadas por un túnel en la región
--areConnectedInRegion = connectedR linkedRegion cityA cityC
