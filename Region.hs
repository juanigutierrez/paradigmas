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
foundR (Reg citys links tunnels) city = Reg (city:citys) links tunnels
   --revisar si ya esta incluida

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

notrepeted :: [City] -> City
notrepeted [] = error "No hay ninguna ciudad unica en la lista."
notrepeted (x:xs) | x `notElem`xs = x
                  | otherwise notrepeted xs

firstlink :: [Link] -> City -> Link
firstlink links (notrepeted(citiesR links)) = connectedR links

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg citys links tuneles) cityA cityB = length ([tunel | tunel <- tuneles,connectsT cityA cityB tunel]) > 0


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg citys links tuneles) cityA cityB = length ([link | link <- links, linksL cityA cityB link]) > 0

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
   --las ciudades pueden estar conectadas por links y túneles por separado.
   --Preguntar: deberíamos checkear los links de la lista que estan en los tuneles, los que no estén sumarlos y los que estén sumar lo que esta
   --adentro del tunel.
--delayR (Reg citys links tuneles) cityA cityB = foldr (+) 0 [delayT tunel | tunel <- tuneles, connectsT cityA cityB tunel]
   --acá solo estoy sumando las que estan en los tuneles. no estoy conciderando los links que no estan en túneles, pero si los links que no conectan esas ciudades.
delayR (Reg citys links tuneles) cityA cityB = foldr (+) 0 [delayL link | link <- links, linksL cityA cityB link]
   --acá solo estoy sumando los links que conectan dos ciudades y son parte de la lista de links.
   --ver caso donde las ciudades no estan conectadas, que de error.
   --PREGUNTA: todos los links de los túneles, deben estar en la lista de links, y viceversa<?

--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}