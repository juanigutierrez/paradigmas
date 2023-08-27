import Point
import City
import Quality
import Link
import Tunel
import Region
import TestF

------------- TEST DE POINT -------------

point1 = newP 1 1
point2 = newP 2 2
point3 = newP 3 3
point4 = newP 4 4
point5 = newP 5 5
point6 = newP 6 6

testPoint = [difP point1 point2 ==  sqrt 2]

------------- TEST DE CITY -------------


cityA = newC "CityA" point1
cityB = newC "CityB" point2
cityC = newC "CityC" point3
cityD = newC "CityD" point4
cityE = newC "CityE" point5
cityF = newC "CityF" point6
notCity = newC "" point1

testCity = [nameC cityA == "CityA",
            distanceC cityA cityB == sqrt 2,
            testF notCity] -- Da un error al intentar crear una ciudad de nombre vacío 


------------- TEST DE QUALITY -------------

quality1 = newQ "Quality1" 1 1.1
quality2 = newQ "Quality2" 2 2.2
quality3 = newQ "Quality3" 3 3.3
quality4 = newQ "Quality4" 4 4.4
notQuality = newQ "" 0 0.0
notQuality2 = newQ "NotQuality" (-1) 0.0
notQuality3 = newQ "NotQuality" 0 (-0.1)

testQuality = [capacityQ quality1 == 1 ,
            delayQ quality2 == 2.2 , 
            testF  notQuality, --Da error al intentar crear una calidad de nombre vacío.
            testF notQuality2 , -- Da un error al intentar crear una calidad con capacidad negativa
            testF notQuality3] -- Da un error al intentar crear una calidad con delay negativo.

------------- TEST DE LINK -------------

linkAB = newL cityA cityB quality1
linkBC = newL cityB cityC quality2
linkCD = newL cityC cityD quality3
notLink = newL cityA cityA quality1

testLink = [connectsL cityA linkAB , 
            linksL cityB cityC linkBC ,
            capacityL linkAB == 1 , 
            delayL linkBC == sqrt 2 * 2.2,
            testF  notLink , -- Da un error al intentar crear un link entre la misma ciudad.
            testF (linksL cityA cityA linkAB)]  -- Da un error al intentar verificar una conexión entre la misma ciudad.

------------- TEST DE TUNEL -------------

tunnelAD = newT [linkAB,linkBC,linkCD]

testTunel = [connectsT cityA cityD tunnelAD , 
             usesT linkBC tunnelAD , 
             delayT tunnelAD == delayL linkAB + delayL linkBC + delayL linkCD,
             testF (connectsT cityA cityA tunnelAD)] --Da un error al intentar verificar la conexión de un tunel entre la misma ciudad.

------------- TEST DE REGION -------------

region = newR
region1 = foundR region cityA
region2 = foundR region1 cityB
region3 = foundR region2 cityC
region4 = foundR region3 cityD
region5 = foundR region4 cityE

notRegion = foundR region4 cityA

region6 = linkR region5 cityA cityB quality1
region7 = linkR region6 cityB cityC quality2
region8 = linkR region7 cityC cityD quality3

notRegion1 = linkR region8 cityA cityB quality1
notRegion2 = linkR region8 cityA cityF quality1

region9 = tunelR region8 [cityA,cityB,cityC,cityD]

notRegion3 = tunelR region9 [cityA,cityB,cityC,cityD,cityF]
notRegion4 = tunelR region9 [cityA,cityB,cityC,cityD,cityE]
notRegion5 = tunelR region9 [cityA,cityB,cityC]

testRegion = [region /= region1 , --verifica el funcionamiento de foundR, se ha agregado una ciudad.
              testF notRegion , --Da un error al intentar fundar dos veces la misma ciudad.
              region6 /= region7 , --verifica el funcionamiento de linkR, se ha agregado un link.
              testF  notRegion1 , --Da un error al intentar agregar un link ya creado.
              testF notRegion2 , --Da un error al intentar agregar un link con una ciudad que no esta en la región.
              region8 /= region9 , --verifica el funcionamiento de TunnelR, se ha agregado un tunel.
              testF notRegion3 , --Da un error cuando una ciudad no esta en la región.
              testF notRegion4 , --Da un error cuando no exiten links posibles para conectar las ciudades del tunnel.
              testF notRegion5 , --Da un error cuando la capacidad de un link fue exedida.
              connectedR region9 cityA cityD ,
              linkedR region9 cityA cityB ,
              testF (delayR region9 cityA cityC), --Da error cuando se intenta buscar un delay entre dos ciudades no conectadas.
              delayR region9 cityA cityD == delayL linkAB + delayL linkBC + delayL linkCD ,
              testF (availableCapacityForR region9 cityA cityC) , --Da error cuando se intenta buscar la capacidad disponible entre dos ciudades no conectadas.
              availableCapacityForR region9  cityA cityB == 0 ]
