module City ( City, newC, nameC, distanceC)
   where

import Point 

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC "" point = error "La ciudad no puede tener nombre vacío."
newC name point = Cit name point

nameC :: City -> String
nameC (Cit name _ ) = name

ubi :: City -> Point
ubi (Cit _ point) = point

distanceC :: City -> City -> Float
distanceC city1 city2 = difP (ubi(city1)) (ubi(city2))