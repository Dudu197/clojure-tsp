(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core])
  (:gen-class))


(def mapa (tsp-core/gerar-cidades))

(println mapa)
(println (tsp-core/distancia mapa 1 2))
(println (tsp-core/distancia mapa 2 3))
(println (tsp-core/distancia mapa 5 4))

(defn -main
  [& args]
  (tsp-core/gerar-cidades)
)
