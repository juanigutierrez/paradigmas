module Region --( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where
import City
import Link
import Tunel
import Point
import Quality
import System.Win32 (xBUTTON1)

data Region = Reg [City] [Link] [Tunel] deriving (Eq,Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg citys links tunnels) city | city `elem` citys = error "la ciudad ya se encuentra en la region"
                                      | otherwise = Reg (city:citys) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada.
linkR (Reg citys links tunnels) cityA cityB quality
   | length ([link | link<-links, linksL cityA cityB link]) > 0  =  error "No puede ingresar el mismo Link"
   | notElem cityA citys || notElem cityB citys = error "No se puede generear el link, una de las dos ciudades no pertenece a la region."
   | otherwise = Reg citys ((newL cityA cityB quality):links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg citys links tunnels) 
   | notElem cityA citys || notElem cityB citys = error "No se puede generear el tunel, una de las ciudades no pertenece a la region."


citiesR :: [Link] -> [City]
citiesR (x:xs) = (namesL x) ++ citiesR xs

--notrepeted :: [City] -> City
--notrepeted [] = error "No hay ninguna ciudad unica en la lista."
--notrepeted (x:xs) | x `notElem`xs = x
--                  | otherwise notrepeted xs

--firstlink :: [Link] -> City -> Link
--firstlink links (notrepeted(citiesR links)) = connectedR links

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) cityA cityB = length ([tunel | tunel <- tuneles,connectsT cityA cityB tunel]) > 0

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) cityA cityB = length([link | link <- links, linksL cityA cityB link]) > 0 
                                                                
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
delayR (Reg citys links tuneles) cityA cityB | connectedR (Reg citys links tuneles) cityA cityB  = foldr (+) 0 [delayL link | link <- links, linksL cityA cityB link]
                                             | otherwise = error "las ciudades no se encuentran conectadas"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}
availableCapacityForR (Reg citys links tuneles) cityA cityB | linkedR  (Reg citys links tuneles) cityA cityB = capacityL (head [link | link <- links, linksL cityA cityB link])
                                                            | otherwise= error "no existe una link que los conecte"