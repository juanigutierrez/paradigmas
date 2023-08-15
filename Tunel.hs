module Tunel ( Tunel, newT, connectsT, usesT, delayT, delaysT )
   where
import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links)= length [x | x <- links, linksL city1 city2 x] >= 1

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = length [x | x<-links, link == x] == 1

delaysT :: Tunel -> [Float]
delaysT (Tun links) = [delayL link | link <- links]

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
--en este no entendí si quiere la mayor demora de las conexiones, la suma de todas las demoras, o la demora de una conexión random.
--delayT tunel = maximum (delaysT tunel)
delayT tunel = foldr (+) 0 (delaysT tunel)
