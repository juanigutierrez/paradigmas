module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ name capacity delay
   |name == "" = error "El nombre de Quality no puede ser vacio."
   |capacity < 0 = error "la calidad no puede ser menor a 0"
   |delay < 0 = error "el delay no puede ser menor a 0"
   |otherwise = Qua name capacity delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ qty _) = qty

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ dly) = dly