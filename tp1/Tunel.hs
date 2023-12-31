module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where
import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conecta estas dos ciudades distintas
connectsT cityA cityB (Tun links) 
   | cityA == cityB = error "Ha ingresado la misma ciudad dos veces."
   | otherwise = (cityLinkCount cityA links == 1) && (cityLinkCount cityB links == 1)

cityLinkCount :: City -> [Link] -> Int
cityLinkCount city links= length [link | link <- links, connectsL city link]

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = length [x | x<-links, link == x] == 1

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = foldr (+) 0 [delayL link | link <- links]