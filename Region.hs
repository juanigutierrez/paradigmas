module Region --( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where
import City
import Link 
import Tunel 
import Point
import Quality


data Region = Reg [City] [Link] [Tunel] deriving (Eq,Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg citys links tunnels) city | city `elem` citys = error "la ciudad ya se encuentra en la region"
                                      | otherwise = Reg (city:citys) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada.
linkR (Reg citys links tunnels) cityA cityB quality = Reg citys ((newL cityA cityB quality):links) tunnels

--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--lo complicado es chequear que existe un camino entre la primera ciudad y la ultima que se conecten mediante links

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) cityA cityB = length([tunel | tunel <- tuneles,connectsT cityA cityB tunel]) > 0

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) cityA cityB = length([link | link <- links, linksL cityA cityB link]) > 0 
                                                                
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
delayR (Reg citys links tuneles) cityA cityB | connectedR (Reg citys links tuneles) cityA cityB  = foldr (+) 0 [delayL link | link <- links, linksL cityA cityB link]
                                             | otherwise = error "las ciudades no se encuentran conectadas"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}
availableCapacityForR (Reg citys links tuneles) cityA cityB | linkedR  (Reg citys links tuneles) cityA cityB = capacityL (head [link | link <- links, linksL cityA cityB link])
                                                            | otherwise= error "no existe una link que los conecte"
