{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
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
foundR (Reg cities links tunnels) city
   | not (citypositionisvalid cities city) = error "Ya hay una ciudad en esa ubicación."
   | otherwise = Reg (city:cities) links tunnels

citypositionisvalid :: [City] -> City -> Bool
citypositionisvalid cities newcity = length [city | city <-cities, distanceC city newcity == 0] == 0

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada.
linkR (Reg cities links tunnels) cityA cityB quality
   | notElem cityA cities || notElem cityB cities = error "No se puede generear el link, una de las dos ciudades no pertenece a la region."
   | not (null ([link | link<-links, linksL cityA cityB link]))  =  error "No puede ingresar el mismo Link"
   | otherwise = Reg cities ((newL cityA cityB quality):links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunel) tcities
    |not (all (`elem` cities) tcities) = error "Una ciudad ingresada no forma parte de la region."
    |not (validlinkquantity region tcities) = error "No hay links suficientes para crear el tunel."
    |not (validLinks region linklist) = error "La capacidad de algún link fué exedida."
    |otherwise= Reg cities links (newT linklist:tunel)
    where
      linklist = connectcities links tcities

validlinkquantity :: Region -> [City] -> Bool
validlinkquantity (Reg _ links tunels) citys = length (connectcities links citys) == (length citys-1)

connectcities :: [Link]->[City]->[Link]
connectcities links []=[]
connectcities links [_]=[]
connectcities links (x:xs:citys) = [link |link <- links,linksL x xs link] ++ connectcities links (xs:citys)

validLinks :: Region -> [Link] -> Bool
validLinks (Reg _ _ []) _ = True
validLinks region tlinks = length ([link | link<-tlinks, tunnel_count region link < capacityL link]) == length tlinks

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) cityA cityB = not (null ([tunel | tunel <- tuneles,connectsT cityA cityB tunel]))

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) cityA cityB = not (null ([link | link <- links, linksL cityA cityB link]))

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
delayR (Reg cities links tuneles) cityA cityB
   | connectedR (Reg cities links tuneles) cityA cityB  = head [delayT tunel | tunel <- tuneles, connectsT cityA cityB tunel]
   | otherwise = error "las ciudades no se encuentran conectadas"

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region@(Reg _ links _) cityA cityB
   |null linkage = error "Las ciudades no estan conectadas."
   |otherwise = capacityL (head linkage) - tunnel_count region (head linkage)
   where
      linkage = [link | link <- links, linksL cityA cityB link]

tunnel_count :: Region -> Link -> Int
tunnel_count (Reg _ _ tunnels) link = length ([tunel | tunel <- tunnels, usesT link tunel])