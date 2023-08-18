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
foundR (Reg cities links tunnels) city | city `elem` cities = error "la ciudad ya se encuentra en la region"
                                      | otherwise = Reg (city:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada.
linkR (Reg cities links tunnels) cityA cityB quality
   | notElem cityA cities || notElem cityB cities = error "No se puede generear el link, una de las dos ciudades no pertenece a la region."
   | not (null ([link | link<-links, linksL cityA cityB link]))  =  error "No puede ingresar el mismo Link"
   | otherwise = Reg cities ((newL cityA cityB quality):links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
--acá tengo que ver:
   --lista de ciudades ingresadas debe ser parte de la región.
   --las ciudades que se desean conectar deben tener links ya ingresados.
   --tengo que ver que no se puedan meter links en mas tuneles que su capacidad permitida.
   
tunelR (Reg cities links tunnels) tunelcities
   | not (all (`elem` tunelcities) cities) = error "Una ciudad ingresada no forma parte de la region."
   | length (connectionL tunelcities links) < (length tunelcities - 1) = error "No hay suficientes links en la región para conectar las ciudades."
   | otherwise = Reg cities links [newT (connectionL tunelcities links)]

connectionL :: [City] -> [Link] -> [Link]
connectionL cities links = eliminarRepetidos [link | cityA <- cities, cityB <- cities, cityA /= cityB , link <- links, linksL cityA cityB link]
--no se si esta función me va a repetir los links.

eliminarRepetidos :: [Link] -> [Link]
eliminarRepetidos [] = []
eliminarRepetidos (x:xs) = [x | y <- xs, x /= y]

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) cityA cityB = not (null ([tunel | tunel <- tuneles,connectsT cityA cityB tunel]))

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) cityA cityB = not (null ([link | link <- links, linksL cityA cityB link]))

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
delayR (Reg cities links tuneles) cityA cityB | connectedR (Reg cities links tuneles) cityA cityB  = foldr (+) 0 [delayL link | link <- links, linksL cityA cityB link]
                                             | otherwise = error "las ciudades no se encuentran conectadas"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}
availableCapacityForR r@(Reg cities links tuneles) cityA cityB 
   | linkedR  r cityA cityB = capacityL (head [link | link <- links, linksL cityA cityB link])
   | otherwise= error "no existe una link que los conecte"