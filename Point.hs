module Point ( Point, newP, difP)
   where


data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

corx :: Point -> Float
corx (Poi x _) = fromIntegral(x)--PREGUNTA: ¿Es mejor poner fromIntegral directamente en sqrt o acá?
cory::Point-> Float
cory (Poi _ y) = fromIntegral(y)

difP :: Point -> Point -> Float  -- distancia absoluta
difP point1 point2 = sqrt((((corx point1) - (cory point2)))^2) + (((cory point1) - (cory point2))^2)
