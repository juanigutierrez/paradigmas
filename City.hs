module City ( City, newC, nameC, distanceC )
   where

import Point 

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC name point = Cit name point

nameC :: City -> String
nameC (Cit name _ ) = name

ubi :: City -> Point
ubi (Cit _ point) = point

distanceC :: City -> City -> Float
distanceC city1 city2 = difP (ubi(city1)) (ubi(city2))


--VARIABLES DE PRUEBA. PREGUNTA: ¿COMO HAGO PARA QUE FUNCIONE DESDE UN MAIN?
puntoc1 = newP 1 2
bs = newC "Buenos Aires" puntoc1
puntoc2 = newP 3 4
parana = newC "parana" puntoc2