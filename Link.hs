--SOLUCIONAR:
    --NO SEDEBERÍA PODER HACER UNA CONEXIÓN ENTRE LA MISMA CIUDAD

module Link ( Link, newL, linksL, connectsL, capacityL, delayL)
   where

import City
import Quality
import Distribution.Verbosity (verbose)

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 _) = (city == city1) || (city == city2)
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = connectsL city1 link && connectsL city2 link
capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL  (Lin cityA cityB quality)  = (distanceC cityA cityB) * (delayQ quality)