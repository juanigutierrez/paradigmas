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
tunelR region@(Reg cities links tunel) tcities 
    |not (all (`elem` cities) tcities) = error "Una ciudad ingresada no forma parte de la region." -- no anda. solo funciona cunado le metemos todas las ciudades.
    |not (validlinkquantity region tcities) = error "No hay links suficientes para crear el tunel"
    |not (validLinks region linklist) = error "La capacidad de algún link fué exedida."
    |otherwise= Reg cities links ((newT linklist):tunel)
    where
      linklist = create_lista links tcities

validlinkquantity :: Region -> [City] -> Bool
validlinkquantity (Reg _ links tunels) citys = (length (create_lista links citys)) == (length (citys)-1)
--te dice true si se puede crear el tunel o false si no

create_lista :: [Link]->[City]->[Link]
create_lista links []=[]
create_lista links [_]=[]
create_lista links (x:xs:citys) = [link |link <- links,linksL x xs link] ++ create_lista links (xs:citys)

validLinks :: Region -> [Link] -> Bool
validLinks (Reg _ _ []) _ = True
validLinks region tlinks = (length ([link | link<-tlinks, tunnel_count region link < capacityL link])) == (length tlinks)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tuneles) cityA cityB = not (null ([tunel | tunel <- tuneles,connectsT cityA cityB tunel]))

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) cityA cityB = not (null ([link | link <- links, linksL cityA cityB link]))

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora.
delayR (Reg cities links tuneles) cityA cityB 
   | connectedR (Reg cities links tuneles) cityA cityB  = foldr (+) 0 [delayL link | link <- links, linksL cityA cityB link]--da siempre 0
   | otherwise = error "las ciudades no se encuentran conectadas"
                           
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region@(Reg _ links _) cityA cityB 
   |linkage  == [] = error "Las ciudades no estan conectadas." -- ERROR solo conectadas mediante tunel, vinculadas mediante link.
   |otherwise = capacityL (head linkage) - tunnel_count region (head linkage)
   where
      linkage = [link | link <- links, linksL cityA cityB link]

tunnel_count :: Region -> Link -> Int --ELIMINAR: indica cuantas veces un link se encuentra en la lista de túneles.
tunnel_count (Reg _ _ tunnels) link = length ([tunel | tunel <- tunnels, usesT link tunel])