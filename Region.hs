module Region --( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where
import City
import Link 
import Tunel 
import Point
import Quality

data Region = Reg [City] [Link] [Tunel]

newR :: [City] -> [Link] -> [Tunel] -> Region
newR city links tuneles = Reg city links tuneles

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg citys links tuneles) city = Reg (city:citys) links tuneles

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg citys links tuneles) cityA cityB quality = Reg citys (newL cityA cityB quality:links) tuneles

--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--este lo tengo q hacer

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg citys links tuneles) cityA cityB |length([tunel | tunel <- tuneles,connectsT cityA cityB tunel])>0 =True
                                                                |otherwise = False


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg citys links tuneles) cityA cityB | length([link | link <- links,linksL cityA cityB tunel])>0 =True
                                                                |otherwise = False
                                                                
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}

