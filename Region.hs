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
   | not (null ([link | link<-links, linksL cityA cityB link]))  =  error "No puede ingresar el mismo Link"
   | notElem cityA citys || notElem cityB citys = error "No se puede generear el link, una de las dos ciudades no pertenece a la region."
   | otherwise = Reg citys ((newL cityA cityB quality):links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--acá tengo que ver:
   --lista de ciudades ingresadas debe ser parte de la región.
   --las ciudades que se desean conectar deben tener links ya ingresados.
   --
tunelR (Reg cities links tunnels) tunelcities
   | not (all (`elem` tunelcities) cities) = error "Una ciudad ingresada no forma parte de la region."
   | length (connectionL tunelcities links) < (length tunelcities - 1) = error "No hay suficientes links en la región para conectar las ciudades."
   | otherwise = Reg cities links [newT (connectionL tunelcities links)]

connectionL :: [City] -> [Link] -> [Link]
connectionL cities links = [link | cityA <- cities, cityB <- cities, cityA /= cityB , link <- links, linksL cityA cityB link]
--no se si esta función me va a repetir los links.

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) cityA cityB = not (null ([tunel | tunel <- tuneles,connectsT cityA cityB tunel]))

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) cityA cityB = not (null ([link | link <- links, linksL cityA cityB link]))

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
delayR (Reg citys links tuneles) cityA cityB | connectedR (Reg citys links tuneles) cityA cityB  = foldr (+) 0 [delayL link | link <- links, linksL cityA cityB link]
                                             | otherwise = error "las ciudades no se encuentran conectadas"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}
availableCapacityForR (Reg citys links tuneles) cityA cityB | linkedR  (Reg citys links tuneles) cityA cityB = capacityL (head [link | link <- links, linksL cityA cityB link])
                                                            | otherwise= error "no existe una link que los conecte"